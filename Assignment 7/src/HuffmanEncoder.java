

/*
 * TCSS 342 - Spring 2023
 * Assignment 4 - Compressed Literature
 */
import java.io.FileOutputStream;
import java.io.IOException;
public class HuffmanEncoder {
     //filename for the uncompressed input file
    protected String inputFileName = "./WarAndPeace.txt";
    //filename for the compressed output file
    protected String outputFileName = "./WarAndPeace-compressed.bin";
    //filename for the codes output file
    protected String codesFileName = "./WarAndPeace-codes.txt";

    //A book reader initialized with the inputFileName.
    protected BookReader book;
    //An ordered list that stores the frequency of each character in the
    //input file.
    protected MyOrderedList<FrequencyNode> frequencies;


    //The root HuffmanNode of the Huffman tree.
    protected HuffmanNode huffmanTree;
    //An ordered list that stores the codes assigned to each character by the
    //Huffman algorithm.
    protected MyOrderedList<CodeNode> codes;

    //The encoded binary string stored as an array of bytes.
    protected byte[] encodedText;
    public HuffmanEncoder() throws IOException {
        book = new BookReader(inputFileName);
        frequencies = new MyOrderedList<>();
        codes = new MyOrderedList<>();
        long startTime = System.currentTimeMillis();
        countFrequency();
        long endTime = System.currentTimeMillis();
        System.out.println("Counting frequencies of characters... " + frequencies.size()
                + " unique characters found in " + (endTime - startTime) / 1000.0 + " seconds\n");


        startTime = System.currentTimeMillis();
        buildTree();
        extractCodes(huffmanTree, "");
        endTime = System.currentTimeMillis();
        System.out.println("Building a Huffman tree and reading codes... in" + (endTime - startTime) / 1000.0 + " seconds\n");

        startTime = System.currentTimeMillis();
        encode();
        endTime = System.currentTimeMillis();
        System.out.println("Encoding message... in" + (endTime - startTime) / 1000.0 + " seconds\n");


        startTime = System.currentTimeMillis();
        writeFiles();
        endTime = System.currentTimeMillis();
        System.out.println("Writing compressed file... " + encodedText.length + " bytes written in " + (endTime - startTime) / 1000.0 + " seconds");


    }
    //This method counts the frequency of each character in the book
    //and stores it in frequencies.
    protected void countFrequency() {
        for (final char c : book.book.toCharArray()) {

            final FrequencyNode freqNode = new FrequencyNode(c);
            final FrequencyNode searchedNode = frequencies.binarySearch(freqNode);

            if (searchedNode == null) {
                frequencies.add(freqNode);
            } else {
                searchedNode.count++;
            }
        }
    }

    protected void buildTree() {
        final MyPriorityQueue<HuffmanNode> queue = new MyPriorityQueue<>();

        for (int i = 0; i < frequencies.size(); i++) {
            final FrequencyNode freqNode = frequencies.get(i);
            queue.insert(new HuffmanNode(freqNode.character, freqNode.count));
        }


        while (true) {
            final HuffmanNode left = queue.removeMin();
            final HuffmanNode right = queue.removeMin();

            final HuffmanNode parent = new HuffmanNode(left, right);

            queue.insert(parent);

            if (queue.size() == 1) {
                huffmanTree = parent;
                break;
            }
        }


    }

    protected void extractCodes(final HuffmanNode node, final String code) {
        if (node.character != null) {
            codes.add(new CodeNode(node.character, code));
            return;
        }

        extractCodes(node.left, code + "0");
        extractCodes(node.right, code + "1");

    }


    protected void encode() {
        final StringBuilder sb = new StringBuilder();
        for (final char c : book.book.toCharArray()) {
            final CodeNode code = codes.binarySearch(new CodeNode(c, null));

            if (code != null) {
                sb.append(code.code);
            }

        }


        final String binaryString = sb.toString();
        encodedText = new byte[Math.ceilDiv(binaryString.length(), 8)];
        for (int i = 0; i < binaryString.length(); i += 8) {
            final String eightBits = binaryString.substring(i, Math.min(i + 8, binaryString.length()));
            encodedText[i / 8] = (byte) Integer.parseInt(eightBits, 2);
        }

    }

    protected void writeFiles() {

        try {
            final FileOutputStream fWriter = new FileOutputStream(outputFileName);
            fWriter.write(encodedText);
            fWriter.close();

            final FileOutputStream fWriter2 = new FileOutputStream(codesFileName);
            fWriter2.write(codes.toString().getBytes());
            fWriter2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    protected class FrequencyNode implements Comparable<FrequencyNode> {
        //The character that this count is for.
        public Character character;
        //The count for this character.
        public Integer count = 1;


        public FrequencyNode(final Character c) {
            this.character = c;
        }
        // This method compares the nodes based on the character stored in
        //them.

        @Override
        public int compareTo(final FrequencyNode other) {
            return this.character.compareTo(other.character);
        }

        @Override
        public String toString() {
            return this.character + ":" + this.count;
        }
    }

    protected class HuffmanNode implements Comparable<HuffmanNode> {

        public Character character;

        public Integer weight;

        public HuffmanNode left;

        public HuffmanNode right;


        public HuffmanNode(final Character ch, final Integer wt) {
            this.character = ch;
            this.weight = wt;
            this.left = null;
            this.right = null;
        }


        public HuffmanNode(final HuffmanNode l, final HuffmanNode r) {
            this.character = null;
            this.weight = l.weight + r.weight;
            this.left = l;
            this.right = r;
        }


        @Override
        public int compareTo(final HuffmanNode otherNode) {
            return this.weight.compareTo(otherNode.weight);
        }

        @Override
        public String toString() {
            return this.character + ":" + this.weight;
        }
    }

    protected class CodeNode implements Comparable<CodeNode> {

        public Character character;

        public String code;


        public CodeNode() {
            this.character = null;
            this.code = null;
        }

        public CodeNode(final Character ch, final String co) {
            this.character = ch;
            this.code = co;
        }


        @Override
        public int compareTo(final CodeNode otherNode) {
            return this.character.compareTo(otherNode.character);
        }

        @Override
        public String toString() {
            return this.character + ":" + this.code;


        }
    }
}
