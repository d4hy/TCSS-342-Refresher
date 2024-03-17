import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class HuffmanEncoder {

    protected String inputFileName = "./WarAndPeace.txt";

    protected String outputFileName = "./WarAndPeace-compressed.bin";

    protected String codesFileName = "./WarAndPeace-codes.txt";

    protected BookReader book = new BookReader(inputFileName);
    protected MyHashTable<String, Integer> frequenciesHash = new MyHashTable<>(book.wordsAndSeparators.size());
    protected MyHashTable<String, String> codesHash = new MyHashTable<>(book.wordsAndSeparators.size());
    protected MyOrderedList<FrequencyNode> frequencies = new MyOrderedList<>();
    protected HuffmanNode huffmanTree;

    protected MyOrderedList<CodeNode> codes = new MyOrderedList<>();
    protected byte[] encodedText;
    protected boolean wordCodes;


    public HuffmanEncoder(final boolean wordCodes) {
        this.wordCodes = wordCodes;

        Instant startTime = Instant.now();
        countFrequency();
        Instant endTime = Instant.now();
        System.out.println("Counting frequencies of words and separators... " + frequenciesHash.size()
                + " unique words and separators found in " + Duration.between(startTime, endTime).toMillis() + "ms");


        startTime = Instant.now();
        buildTree();
        extractCodes(huffmanTree, "");

        endTime = Instant.now();
        System.out.println("Building a Huffman tree and reading codes... in " + Duration.between(startTime, endTime).toMillis() + "ms");

        startTime = Instant.now();
        encode();
        endTime = Instant.now();
        System.out.println("Encoding message... in " + Duration.between(startTime, endTime).toMillis() + "ms");


        startTime = Instant.now();
        writeFiles();
        endTime = Instant.now();
        System.out.println("Writing compressed file... " + encodedText.length + " bytes written in " + Duration.between(startTime, endTime).toMillis() + "ms");


    }


    public HuffmanEncoder() {
        this(true);


    }

    protected void countFrequency() {
        if (wordCodes) {
            for (int i = 0; i < book.wordsAndSeparators.size(); i++) {
                final String key = i == 0 ? book.wordsAndSeparators.first() : book.wordsAndSeparators.next();
                Integer value = frequenciesHash.get(key);
                value = value == null ? 1 : value + 1;
                frequenciesHash.put(key, value);

            }

        } else {
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
    }

    protected void buildTree() {
        final MyPriorityQueue<HuffmanNode> queue = new MyPriorityQueue<>();

        if (wordCodes) {
            for (int i = 0; i < frequenciesHash.size(); i++) {
                final String key = frequenciesHash.keys.get(i);
                queue.insert(new HuffmanNode(key, frequenciesHash.get(key)));
            }
        } else {
            for (int i = 0; i < frequencies.size(); i++) {
                final FrequencyNode freqNode = frequencies.get(i);
                queue.insert(new HuffmanNode(freqNode.character, freqNode.count));
            }
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
        if (wordCodes) {
            if (node.word != null) {
                codesHash.put(node.word, code);
                return;
            }
        } else {
            if (node.character != null) {
                codes.add(new CodeNode(node.character, code));
                return;
            }

        }
        extractCodes(node.left, code + "0");
        extractCodes(node.right, code + "1");
    }


    protected void encode() {
        final StringBuilder sb = new StringBuilder();
        if (wordCodes) {
            for (int i = 0; i < book.wordsAndSeparators.size(); i++) {
                final String key = i == 0 ? book.wordsAndSeparators.first() : book.wordsAndSeparators.next();
                final String code = codesHash.get(key);
                if (code != null) {
                    sb.append(code);
                }
            }
        } else {
            for (final char c : book.book.toCharArray()) {
                final CodeNode code = codes.binarySearch(new CodeNode(c, null));
                if (code != null) {
                    sb.append(code.code);
                }
            }
        }

        final String binaryString = sb.toString();
        encodedText = new byte[Math.ceilDiv(binaryString.length(), 8)];

        for (int i = 0; i < binaryString.length(); i += 8) {
            final String bits = binaryString.substring(i, Math.min(i + 8, binaryString.length()));
            encodedText[i / 8] = (byte) Integer.parseInt(bits, 2);
        }

    }

    protected void writeFiles() {

        try {
            final FileOutputStream fWriter = new FileOutputStream(outputFileName);
            fWriter.write(encodedText);
            fWriter.close();

            final FileOutputStream fWriter2 = new FileOutputStream(codesFileName);
            fWriter2.write(wordCodes ? codesHash.toString().getBytes() : codes.toString().getBytes());
            fWriter2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    protected class FrequencyNode implements Comparable<FrequencyNode> {

        public Character character;

        public Integer count = 1;


        public FrequencyNode(final Character c) {
            this.character = c;
        }

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

        public String word;


        public HuffmanNode(final String word, final Integer wt) {
            this.word = word;
            this.weight = wt;
            this.character = null;
            this.left = null;
            this.right = null;
        }


        public HuffmanNode(final Character ch, final Integer wt) {
            this.character = ch;
            this.weight = wt;
            this.word = null;
            this.left = null;
            this.right = null;
        }


        public HuffmanNode(final HuffmanNode l, final HuffmanNode r) {
            this.character = null;
            this.word = null;

            this.weight = (l == null || r == null) ? 0 : l.weight + r.weight;
            this.left = l;
            this.right = r;
        }


        @Override
        public int compareTo(final HuffmanNode otherNode) {
            return this.weight.compareTo(otherNode.weight);
        }

        @Override
        public String toString() {
            return (wordCodes ? this.word : this.character) + ":" + this.weight;
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
