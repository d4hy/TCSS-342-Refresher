

/*
 * TCSS 342 - Spring 2023
 * Assignment 8 - Compressed Literature 3
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
    // The constructor should call the helper methods in the correct
    //order to carry out Huffman?s algorithm.
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
   // This method counts the frequency of each character in the book
   //and stores it in frequencies.
   //It does so by following these steps:
   //i. Iterate through the text character by character.
   // Search frequencies for the FrequencyNode that contains the
   //current character.
   // If it doesn?t exist, make a new FrequencyNode and add it
   //to the list.
   // If it does exist, update the count.
   // Searching for a node requires creating a temporary search node or a node
   //whose only purpose is to help us find the node we want. I usually do that with a
   //line like this:
   //node = frequencies.binarySearch(new FrequencyNode(ch));
   // This method should output the time it takes to count the frequencies.

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
    //This method builds the Huffman tree and extracts the codes from it,
    //storing them in codes.
    // It does so by following these steps:
    //i. Create a single Huffman node for each character weighted by its count.
    //ii. Add all the nodes to a priority queue.
    //iii. Merge Huffman nodes until only a single tree remains.
    //iv. Store the root of the remaining tree in huffmanTree.
    //v. Extract the codes from the tree and store them in codes using the
    //recursive helper function like this:
    //extractCodes(huffmanTree,"");
    // This method should output the time it takes to build the tree and extract the
    //codes
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
    // A recursive method that traverses the Huffman tree to extract the
    //codes stored in it.
    //This method will conduct a recursive depth-first traversal of the Huffman tree.
    // The path of left and right moves is stored in the code parameter by adding '0' for
    //left traversals and '1'for right traversals. For instance:
    //extractCodes(node.left, code + '0');
    //extractCodes(node.right, code + '1');
    protected void extractCodes(final HuffmanNode node, final String code) {
        if (node.character != null) {
            codes.add(new CodeNode(node.character, code));
            return;
        }

        extractCodes(node.left, code + "0");
        extractCodes(node.right, code + "1");

    }

    //Uses the book and codes to create encodedText.
    // It does so by following these steps:
    //i. For each character in the text, append the character's code from codes
    //to a string builder.
    //ii. Convert the string of codes from step i) into a list of bytes and store it in
    //encodedText.
    // You can convert a string of '0's and '1's to a byte with this line:
    //byte b = (byte)Integer.parseInt(str,2);
    // It should output the time it takes to encode the text.
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

    //Uses the book and codes to create encodedText.
    //It does so by following these steps:
    //i. For each character in the text, append the character's code from codes
    //to a string builder.
    //ii. Convert the string of codes from step i) into a list of bytes and store it in
    //encodedText.
    // You can convert a string of '0's and '1's to a byte with this line:
    //byte b = (byte)Integer.parseInt(str,2);
    // It should output the time it takes to encode the text.
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

        // - The character that this node stores.
        //Only leaves store characters.
        // Internal nodes have null characters.
        public Character character;
        //The weight of the Huffman tree rooted at this node.
        public Integer weight;

        //The root of the left sub-tree.
        public HuffmanNode left;

        //The root of the right sub-tree.
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

        //The character that this node stores the code for.
        public Character character;
        //The code assigned to this character.
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
