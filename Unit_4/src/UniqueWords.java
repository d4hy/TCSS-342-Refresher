import java.io.IOException;

/*
 * TCSS 342 - Spring 2023
 * Extra Assignment 3 - Trie
 */
public class UniqueWords {

    public BookReader book;

    public MyArrayList<String> alOfUniqueWords;

    public MyLinkedList<String> llOfUniqueWords;

    public MyOrderedList<String> olOfUniqueWords;
    public MyTrie trieOfUniqueWords;


    public UniqueWords() throws IOException {
        this.book = new BookReader("./WarAndPeace.txt");
        this.alOfUniqueWords = new MyArrayList<>();
        this.llOfUniqueWords = new MyLinkedList<>();
        this.olOfUniqueWords = new MyOrderedList<>();
        this.trieOfUniqueWords = new MyTrie();
    }

    public void addUniqueWordsToLinkedList() {
        final long addStartTime = System.currentTimeMillis();


        String currentWord = book.words.first();
        while (currentWord != null) {
            if (!llOfUniqueWords.contains(currentWord)) {
                llOfUniqueWords.addBefore(currentWord);
            }
            currentWord = book.words.next();
        }
        final long addEndTime = System.currentTimeMillis();
        System.out.println("Adding unique words to a linked list... in " + (addEndTime - addStartTime) / 1000.0 + " seconds.");
        System.out.println(llOfUniqueWords.size() + " unique words found.");
        System.out.println(llOfUniqueWords.comparisons + " comparisons were made.");


        final long sortStartTime = System.currentTimeMillis();
        llOfUniqueWords.sort();
        final long sortEndTime = System.currentTimeMillis();
        System.out.println("Bubble-sorting linked list... in " + (sortEndTime - sortStartTime) / 1000.0 + " seconds.\n");



    }

    public void addUniqueWordsToArrayList() {
        final long addStartTime = System.currentTimeMillis();

        String currentWord = book.words.first();
        while (currentWord != null) {
            if (!alOfUniqueWords.contains(currentWord)) {
                alOfUniqueWords.insert(currentWord, alOfUniqueWords.size());
            }
            currentWord = book.words.next();
        }

        final long addEndTime = System.currentTimeMillis();
        System.out.println("Adding unique words to an array list... in " + (addEndTime - addStartTime) / 1000.0 + " seconds.");
        System.out.println(alOfUniqueWords.size() + " unique words found.");
        System.out.println(alOfUniqueWords.comparisons + " comparisons were made.");

        final long sortStartTime = System.currentTimeMillis();
        alOfUniqueWords.sort();
        final long sortEndTime = System.currentTimeMillis();
        System.out.println("Merge-sorting... in " + (sortEndTime - sortStartTime) / 1000.0 + " seconds.\n");



    }

    public void addUniqueWordsToOrderedList() {
        final long startTime = System.currentTimeMillis();

        String currentWord = book.words.first();
        while (currentWord != null) {
            if (olOfUniqueWords.binarySearch(currentWord) == null) {
                olOfUniqueWords.add(currentWord);
            }
            currentWord = book.words.next();
        }

        final long endTime = System.currentTimeMillis();

        System.out.println("Adding words to an ordered list... in " + (endTime - startTime) / 1000.0 + " seconds.");
        System.out.println(olOfUniqueWords.size() + " unique words were found.");
        System.out.println(olOfUniqueWords.comparisons + " comparisons were made.");

    }
    public void addUniqueWordsToTrie() {
        final long startTime = System.currentTimeMillis();


        String currentWord = book.words.first();
        while (currentWord != null) {
            if (!trieOfUniqueWords.find(currentWord)) {
                trieOfUniqueWords.insert(currentWord);
            }

            currentWord = book.words.next();
        }

        final long endTime = System.currentTimeMillis();

        System.out.println("Adding unique words to a trie... in " + (endTime - startTime) / 1000.0 + " seconds.");
        System.out.println(trieOfUniqueWords.size() + " unique words were found.");
        System.out.println(trieOfUniqueWords.comparisons + " comparisons were made.");

    }






}
