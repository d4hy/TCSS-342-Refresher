import java.io.File;
import java.io.FileReader;

public class BookReader {

    public String book;

    public MyLinkedList<String> words = new MyLinkedList<>();

    public BookReader(final String fileName) {
        readBook(fileName);
    }

    public void readBook(final String fileName) {
        final long startTime = System.currentTimeMillis();


        final File file = new File(fileName);
        try (FileReader fileReader = new FileReader(file)) {
            final char[] fileChars = new char[(int) file.length()];
            fileReader.read(fileChars);
            this.book = new String(fileChars);

            final long endTime = System.currentTimeMillis();
            System.out.println("Reading input file \"" + fileName + "\"..."
                    + fileChars.length + " characters read in "
                    + (endTime - startTime) / 1000.0 + " seconds.\n");


            parseWords();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void parseWords() {
        final long startTime = System.currentTimeMillis();

        StringBuilder wordBuffer = new StringBuilder();
        for (final char c : book.toCharArray()) {
            if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || c == '\'') {
                wordBuffer.append(c);
            } else {
                if (!wordBuffer.toString().isBlank()) {
                    words.addBefore(wordBuffer.toString());
                    wordBuffer = new StringBuilder();
                }
            }
        }

        final long endTime = System.currentTimeMillis();
        System.out.println("Adding words to a linked list... in " + (endTime - startTime) / 1000.0 + " seconds");
        System.out.println(words.size() + " words detected.\n");

    }

}
