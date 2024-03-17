import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

/*
 * TCSS 342 - Spring 2023
 * Assignment 5 - Binary Search Tree
 */
public class UniqueWords {
    public BookReader book;
    public MyBinarySearchTree<String> bstOfUniqueWords;
    public MyArrayList<String> alOfUniqueWords;

    public MyLinkedList<String> llOfUniqueWords;

    public MyOrderedList<String> olOfUniqueWords;
    public MyTrie trieOfUniqueWords;


    public UniqueWords() throws IOException {
        this.book = new BookReader("./WarAndPeace.txt");
        bstOfUniqueWords = new MyBinarySearchTree<>();
        this.alOfUniqueWords = new MyArrayList<>();
        this.llOfUniqueWords = new MyLinkedList<>();
        this.olOfUniqueWords = new MyOrderedList<>();
        this.trieOfUniqueWords = new MyTrie();
    }
    //Adds the unique words to a MyBinarySearchTree.
    //For each word in the list stored in book:
    //i. Check to see if the word is stored in the binary search tree of unique
    //words.
    //If it is not, add it to the binary search tree.
    // Otherwise, skip this word.
    //Calls toString from the binary search tree to extract the words in order.
    //This method should time both steps and output each runtime to the console as well as
    //the number of unique words, height of the binary search tree and the number of
    //comparisons made. This method should run in time O(nlogn) where n is the number of
    //words in the book.
    public void addUniqueWordsToBST(){
        Instant addStartTime = Instant.now();
        String currentWord = book.words.first();
        while(currentWord!=null) {
            if (bstOfUniqueWords.find(currentWord) != currentWord) {
                bstOfUniqueWords.add(currentWord);
            }
            currentWord = book.words.next();

        }
        Instant addEndTime = Instant.now();
        Instant traverseStartTime = Instant.now();
        bstOfUniqueWords.toString();

        Instant traverseEndTime = Instant.now();
        System.out.println("Adding unique words to a binary search tree... in " +  Duration.between(addStartTime , addEndTime).toMillis() + "ms.\n");
        System.out.println(bstOfUniqueWords.size()+" unique words");
        System.out.println("The binary search tree had a height of "+ bstOfUniqueWords.height()+" and made "+bstOfUniqueWords.comparisons+ " comparisons");
        System.out.println("Traversing the binary search tree... in "+ Duration.between(traverseStartTime , traverseEndTime).toMillis() + "ms.\n");
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
