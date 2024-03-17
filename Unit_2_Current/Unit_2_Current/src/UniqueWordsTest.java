import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class UniqueWordsTest
{
	private UniqueWords un;
	private MyArrayList<Integer> testArray;
	private MyLinkedList<Integer> testLinkedList;

	static private Random random = new Random();
	@BeforeEach
	public final void setup()
	{
		testArray = new MyArrayList<>();
		testLinkedList = new MyLinkedList<>();
	}
	
	@Test
	public final void unique_words() throws Exception
	{
		un = new UniqueWords();
		un.addUniqueWordsToArrayList();
		un.addUniqueWordsToLinkedList();
		un.addUniqueWordsToOrderedList();
		assertEquals(true, un.book.book.length() == 3291642 || un.book.book.length() == 3291623, "unique_words failed");
		assertEquals(570240, un.book.words.size(), "unique_words failed");
		assertEquals(20228, un.alOfUniqueWords.size(), "unique_words failed");
		assertEquals(20228, un.llOfUniqueWords.size(), "unique_words failed");
		assertEquals(20228, un.olOfUniqueWords.size(), "unique_words failed");
		assertEquals(1065066710, un.alOfUniqueWords.comparisons, "unique_words failed");
		assertEquals(1065066710, un.llOfUniqueWords.comparisons, "unique_words failed");
	}

	@Test
	public final void testSortArrayList_descending() throws Exception {
		for (int index = 5; index > 0; index--) {
			testArray.insert(index, 5 - index);
		}
		testArray.sort();
		assertEquals("[1, 2, 3, 4, 5]", testArray.toString(), "Test sort for descending array fails");
	}

	@Test
	public final void testSortArrayList_empty() throws Exception {
		testArray.sort();
		assertEquals("[]", testArray.toString(), "Test sort for empty array fails");
	}

	@Test
	public final void testSortArrayList_duplicate() throws Exception {
		testArray.insert(3, 0);
		testArray.insert(1, 1);
		testArray.insert(2, 2);
		testArray.insert(3, 3);
		testArray.insert(2, 4);
		testArray.insert(1, 5);
		testArray.sort();
		assertEquals("[1, 1, 2, 2, 3, 3]", testArray.toString(), "Test sort for duplicate values fails");
	}

	@Test
	public final void testSortArray_random() throws Exception {
		MyArrayList<Integer> longlist = longList();
		MyArrayList<Integer> randomList = randomize(longList());
		randomList.sort();
		assertEquals(longlist.toString(),randomList.toString(),"Test sort long list failed.");

		randomList = randomize(longList());
		randomList.sort();
		assertEquals(longlist.toString(),randomList.toString(),"Test sort long list failed.");

		randomList = randomize(longList());
		randomList.sort();
		assertEquals(longlist.toString(),randomList.toString(),"Test sort long list failed.");
	}

	@Test
	public final void testSortLinkedList_descending() throws Exception {
		testLinkedList.addBefore(4);
		testLinkedList.addBefore(3);
		testLinkedList.addBefore(2);
		testLinkedList.addBefore(1);

		testLinkedList.sort();
		assertEquals("[1, 2, 3, 4]", testLinkedList.toString(), "Test sort for descending values linked list fails");
	}

	@Test
	public final void testSortLinkedList_empty() throws Exception {
		testLinkedList.sort();
		assertEquals("[]", testLinkedList.toString(), "Test sort for empty linked list fails");
	}

	@Test
	public final void testSortLinkedList_duplicated() throws Exception {
		testLinkedList.addBefore(3);
		testLinkedList.addBefore(1);
		testLinkedList.addBefore(2);
		testLinkedList.addBefore(1);
		testLinkedList.addBefore(3);
		testLinkedList.addBefore(2);
		testLinkedList.sort();
		assertEquals("[1, 1, 2, 2, 3, 3]", testLinkedList.toString(), "Test sort for duplicate values for linked list fails");
	}

	@Test
	public final void testSortLinkedList_random() throws Exception {
		MyLinkedList<Integer> longlist = linkAList(longList());
		MyLinkedList<Integer> randomList = linkAList(randomize(longList()));
		randomList.sort();
		assertEquals(longlist.toString(),randomList.toString(),"Test sort long list failed.");
		randomList = linkAList(randomize(longList()));
		randomList.sort();
		assertEquals(longlist.toString(),randomList.toString(),"Test sort long list failed.");
		randomList = linkAList(randomize(longList()));
		randomList.sort();
		assertEquals(longlist.toString(),randomList.toString(),"Test sort long list failed.");
	}

	public static MyLinkedList<Integer> linkAList(MyArrayList<Integer> aList) {
		MyLinkedList<Integer> returner = new MyLinkedList<>();

		for(int i = 0; i < aList.size(); i++) {
			returner.addBefore(aList.get(i));
		}

		return returner;
	}
	public static MyArrayList<Integer> longList() {
		MyArrayList<Integer> longlist = new MyArrayList<>();

		for(int i = 0; i < 1000; i++) {
			longlist.insert(i,i);
		}

		return longlist;
	}

	public static MyArrayList<Integer> randomize(MyArrayList<Integer> mal) {
		MyArrayList<Integer> returner = new MyArrayList<>();
		while(!mal.isEmpty()) {
			Integer randex = random.nextInt(mal.size());
			returner.insert(mal.remove(randex),returner.size());
		}
		return returner;
	}
}


