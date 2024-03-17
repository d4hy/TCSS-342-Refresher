/*
 * TCSS 342 - Spring 2023
 * Assignment 4 - Compressed Literature
 */
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;


public class BookReader {
    /** The book as a string **/
    public String book;
    public MyLinkedList<String> words = new MyLinkedList<>();

    public BookReader(String filename) throws IOException {
        readBook(filename);
        parseWords();
    }

    public void readBook(String filename) throws IOException {
        System.out.print("Reading input file \"" + filename + "\"...");
        Instant start = Instant.now();
        book = Files.readString(Paths.get(filename));
        Instant end = Instant.now();
        System.out.println("\rRead input file \"" + filename + "\" with "
                + book.length() + " characters in " + Duration.between(start, end).toMillis() + "ms.\n");
    }

    public void parseWords() {
        System.out.print("Adding words to linked list... ");
        Instant start = Instant.now();
        StringBuilder currentWord = new StringBuilder();
        for (int i = 0; i < book.length(); i++) {
            Character ch = book.charAt(i);
            if((ch.compareTo('A') >= 0 && ch.compareTo('Z') <= 0) || (ch.compareTo('a') >= 0 && ch.compareTo('z') <= 0)
                    || (ch.compareTo('0') >= 0 && ch.compareTo('9') <= 0) || ch.equals('\'') ) {
                currentWord.append(ch);
            } else {
                if (currentWord.length() > 0) {
                    words.addBefore(currentWord.toString()); // TODO: Would just do, addToEnd, but not currently O(1)
                    words.next();
                    currentWord.setLength(0); // reset string builder
                }
            }
        }
        Instant end = Instant.now();
        System.out.println("\rAdded words to linked list in " + Duration.between(start, end).toMillis() + "ms");
        System.out.println(words.size() + " words detected.\n");
    }

    @Override
    public String toString() {
        return words.toString() + " Words Detected: " + words.size();
    }
}