import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class BookReader {

    public String book;

    public MyLinkedList<String> words = new MyLinkedList<>();

    public MyLinkedList<String> wordsAndSeparators = new MyLinkedList<>();

    public BookReader(final String fileName) {
        readBook(fileName);
        parseWords();
    }


    public void readBook(final String fileName) {
        final long startTime = System.currentTimeMillis();

        final StringBuilder sb = new StringBuilder();

        try (Scanner scanner = new Scanner(new File(fileName), StandardCharsets.UTF_8)) {
            scanner.useDelimiter("\\Z");
            while (scanner.hasNext()) {
                sb.append(scanner.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        book = sb.toString();

        final long endTime = System.currentTimeMillis();
        System.out.println("Reading input file \"" + fileName + "\"..."
                + book.length() + " characters read in "
                + (endTime - startTime) / 1000.0 + " seconds.\n");

    }


    public void parseWords() {
        final long startTime = System.currentTimeMillis();


        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < book.length(); i++) {
            final char ch = book.charAt(i);
            if ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9') || ch == '\'') {
                sb.append(ch);


            } else if (!sb.isEmpty()) {
                words.addBefore(sb.toString());

                wordsAndSeparators.addBefore(sb.toString());
                wordsAndSeparators.addBefore(String.valueOf(ch));

                sb = new StringBuilder();
            } else {
                wordsAndSeparators.addBefore(String.valueOf(ch));
            }
        }

        final long endTime = System.currentTimeMillis();
        System.out.println("Adding words to a linked list... in " + (endTime - startTime) / 1000.0 + " seconds");
        System.out.println(words.size() + " words detected.\n");
    }

}
