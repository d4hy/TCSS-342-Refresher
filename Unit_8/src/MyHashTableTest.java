import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyHashTableTest
{
	private MyHashTable<Integer, Integer> hashtable;
	private UniqueWords un;
	@BeforeEach
	public final void setup()
	{
		hashtable = new MyHashTable<Integer, Integer>(32768);
	}

	@Test
	public final void put_0()
	{
		hashtable.put(0, 10);
		assertEquals("[0:10]", hashtable.toString(), "put_0 failed");
		assertEquals(1, hashtable.size(), "put_0 failed");
		assertEquals(1, hashtable.comparisons, "put_0 failed");
		assertEquals(1, hashtable.maxProbe, "put_0 failed");
	}

	@Test
	public final void put_1()
	{
		hashtable.put(0, 10);
		hashtable.put(1, 11);
		assertEquals("[0:10, 1:11]", hashtable.toString(), "put_1 failed");
		assertEquals(2, hashtable.size(), "put_1 failed");
		assertEquals(2, hashtable.comparisons, "put_1 failed");
		assertEquals(1, hashtable.maxProbe, "put_1 failed");
	}

	@Test
	public final void put_2()
	{
		hashtable.put(1, 11);
		hashtable.put(0, 10);
		assertEquals("[0:10, 1:11]", hashtable.toString(), "put_2 failed");
		assertEquals(2, hashtable.size(), "put_2 failed");
		assertEquals(2, hashtable.comparisons, "put_2 failed");
		assertEquals(1, hashtable.maxProbe, "put_2 failed");
	}

	@Test
	public final void put_3()
	{
		hashtable.put(0, 10);
		hashtable.put(32768, 10);
		assertEquals("[0:10, 32768:10]", hashtable.toString(), "put_3 failed");
		assertEquals(2, hashtable.size(), "put_3 failed");
		assertEquals(3, hashtable.comparisons, "put_3 failed");
		assertEquals(2, hashtable.maxProbe, "put_3 failed");
	}

	@Test
	public final void put_4()
	{
		hashtable.put(32768, 10);
		hashtable.put(0, 10);
		assertEquals("[32768:10, 0:10]", hashtable.toString(), "put_4 failed");
		assertEquals(2, hashtable.size(), "put_4 failed");
		assertEquals(3, hashtable.comparisons, "put_4 failed");
		assertEquals(2, hashtable.maxProbe, "put_4 failed");
	}

	@Test
	public final void put_5()
	{
		hashtable.put(0, 10);
		hashtable.put(32768, 10);
		hashtable.put(1, 11);
		assertEquals("[0:10, 32768:10, 1:11]", hashtable.toString(), "put_5 failed");
		assertEquals(3, hashtable.size(), "put_5 failed");
		assertEquals(5, hashtable.comparisons, "put_5 failed");
		assertEquals(2, hashtable.maxProbe, "put_5 failed");
	}

	@Test
	public final void put_6()
	{
		hashtable.put(0, 10);
		hashtable.put(1, 11);
		hashtable.put(32768, 10);
		assertEquals("[0:10, 1:11, 32768:10]", hashtable.toString(), "put_6 failed");
		assertEquals(3, hashtable.size(), "put_6 failed");
		assertEquals(5, hashtable.comparisons, "put_6 failed");
		assertEquals(3, hashtable.maxProbe, "put_6 failed");
	}

	@Test
	public final void put_7()
	{
		hashtable.put(0, 10);
		hashtable.put(1, 11);
		hashtable.put(2, 12);
		hashtable.put(32768, 10);
		assertEquals("[0:10, 1:11, 2:12, 32768:10]", hashtable.toString(), "put_7 failed");
		assertEquals(4, hashtable.size(), "put_7 failed");
		assertEquals(7, hashtable.comparisons, "put_7 failed");
		assertEquals(4, hashtable.maxProbe, "put_7 failed");
	}

	@Test
	public final void put_8()
	{
		hashtable.put(0, 10);
		hashtable.put(1, 11);
		hashtable.put(32768, 10);
		hashtable.put(2, 12);
		assertEquals("[0:10, 1:11, 32768:10, 2:12]", hashtable.toString(), "put_8 failed");
		assertEquals(4, hashtable.size(), "put_8 failed");
		assertEquals(7, hashtable.comparisons, "put_8 failed");
		assertEquals(3, hashtable.maxProbe, "put_8 failed");
	}

	@Test
	public final void put_9()
	{
		hashtable.put(0, 10);
		hashtable.put(2, 12);
		hashtable.put(32769, 11);
		assertEquals("[0:10, 32769:11, 2:12]", hashtable.toString(), "put_9 failed");
		assertEquals(3, hashtable.size(), "put_9 failed");
		assertEquals(3, hashtable.comparisons, "put_9 failed");
		assertEquals(1, hashtable.maxProbe, "put_9 failed");
	}

	@Test
	public final void put_10()
	{
		hashtable.put(0, 10);
		hashtable.put(32769, 11);
		hashtable.put(2, 12);
		assertEquals("[0:10, 32769:11, 2:12]", hashtable.toString(), "put_10 failed");
		assertEquals(3, hashtable.size(), "put_10 failed");
		assertEquals(3, hashtable.comparisons, "put_10 failed");
		assertEquals(1, hashtable.maxProbe, "put_10 failed");
	}

	@Test
	public final void put_a()
	{
		insert_ten_items_asc(hashtable);
		assertEquals("[0:0, 1:1, 2:2, 3:3, 4:4, 5:5, 6:6, 7:7, 8:8, 9:9]", hashtable.toString(), "put_a failed");
		assertEquals(10, hashtable.size(), "put_a failed");
		assertEquals(10, hashtable.comparisons, "put_a failed");
		assertEquals(1, hashtable.maxProbe, "put_a failed");
	}

	@Test
	public final void put_d()
	{
		insert_ten_items_des(hashtable);
		assertEquals("[0:0, 1:1, 2:2, 3:3, 4:4, 5:5, 6:6, 7:7, 8:8, 9:9]", hashtable.toString(), "put_d failed");
		assertEquals(10, hashtable.size(), "put_d failed");
		assertEquals(10, hashtable.comparisons, "put_d failed");
		assertEquals(1, hashtable.maxProbe, "put_d failed");
	}

	@Test
	public final void put_r()
	{
		insert_ten_items_ran(hashtable);
		assertEquals("[0:0, 1:1, 2:2, 3:3, 4:4, 5:5, 6:6, 7:7, 8:8, 9:9]", hashtable.toString(), "put_r failed");
		assertEquals(10, hashtable.size(), "put_r failed");
		assertEquals(10, hashtable.comparisons, "put_r failed");
		assertEquals(1, hashtable.maxProbe, "put_r failed");
	}

	@Test
	public final void put_u()
	{
		insert_ten_items_ran(hashtable);
		assertEquals("[0:0, 1:1, 2:2, 3:3, 4:4, 5:5, 6:6, 7:7, 8:8, 9:9]", hashtable.toString(), "put_u failed");
		assertEquals(10, hashtable.size(), "put_u failed");
		assertEquals(10, hashtable.comparisons, "put_u failed");
		assertEquals(1, hashtable.maxProbe, "put_u failed");
		insert_ten_items_ran_diff_val(hashtable);
		assertEquals("[0:0, 1:10, 2:20, 3:30, 4:40, 5:50, 6:60, 7:70, 8:80, 9:90]", hashtable.toString(), "put_u failed");
		assertEquals(10, hashtable.size(), "put_u failed");
		assertEquals(20, hashtable.comparisons, "put_u failed");
		assertEquals(1, hashtable.maxProbe, "put_u failed");
	}

	@Test
	public final void get_0()
	{
		assertEquals(null, hashtable.get(0), "get_0 failed");
		assertEquals(0, hashtable.size(), "get_0 failed");
		assertEquals(0, hashtable.comparisons, "get_0 failed");
		assertEquals(0, hashtable.maxProbe, "get_0 failed");
	}

	@Test
	public final void get_1()
	{
		hashtable.put(0, 10);
		assertEquals(10, (int)hashtable.get(0), "get_1 failed");
		assertEquals(1, hashtable.size(), "get_1 failed");
		assertEquals(1, hashtable.comparisons, "get_1 failed");
		assertEquals(1, hashtable.maxProbe, "get_1 failed");
	}

	@Test
	public final void get_2()
	{
		hashtable.put(0, 10);
		hashtable.put(1, 11);
		assertEquals(null, hashtable.get(2), "get_2 failed");
		assertEquals(10, (int)hashtable.get(0), "get_2 failed");
		assertEquals(Integer.valueOf(11), hashtable.get(1), "get_2 failed");
		assertEquals(null, hashtable.get(2), "get_2 failed");
		assertEquals(2, hashtable.size(), "get_2 failed");
		assertEquals(2, hashtable.comparisons, "get_2 failed");
		assertEquals(1, hashtable.maxProbe, "get_2 failed");
	}

	@Test
	public final void get_3()
	{
		hashtable.put(0, 10);
		hashtable.put(1, 11);
		hashtable.put(32768, 10);
		assertEquals(10, (int)hashtable.get(0), "get_3 failed");
		assertEquals(11, (int)hashtable.get(1), "get_3 failed");
		assertEquals(10, (int)hashtable.get(32768), "get_3 failed");
		assertEquals(3, hashtable.size(), "get_3 failed");
		assertEquals(5, hashtable.comparisons, "get_3 failed");
		assertEquals(3, hashtable.maxProbe, "get_3 failed");
	}

	@Test
	public final void unique_words()
	{
		un = new UniqueWords();
		un.addUniqueWordsToHashTable();
		assertEquals(true, un.book.book.length() == 3291642 || un.book.book.length() == 3291623, "unique_words failed");
		assertEquals(570240, un.book.words.size(), "unique_words failed");
		assertEquals(20228, un.hashOfUniqueWords.size(), "unique_words failed");
		assertEquals(40430, un.hashOfUniqueWords.comparisons, "unique_words failed");
		assertEquals(191, un.hashOfUniqueWords.maxProbe, "unique_words failed");
	}

	// ---------- helper methods ---------

	public final void insert_ten_items_asc(MyHashTable<Integer, Integer> hashtable)
	{
		for (int index = 0; index < 10; index++)
			hashtable.put(index, index % 32768);
	}

	public final void insert_ten_items_des(MyHashTable<Integer, Integer> hashtable)
	{
		for (int index = 9; index >= 0; index--)
			hashtable.put(index, index % 32768);
	}

	public final void insert_ten_items_ran(MyHashTable<Integer, Integer> hashtable)
	{
		int array[] = { 5, 3, 7, 1, 4, 6, 8, 2, 0, 9 };
		for (int index = 0; index < array.length; index++)
			hashtable.put(index, index % 32768);
	}

	public final void insert_ten_items_ran_diff_val(MyHashTable<Integer, Integer> hashtable)
	{
		int array[] = { 5, 3, 7, 1, 4, 6, 8, 2, 0, 9 };
		for (int index = 0; index < array.length; index++)
			hashtable.put(index, index * 10);
	}

	// helper function for old unit testing library
	public final void assertTest(String test_name, String actual_value, String ... expected_values)
	{
		for (String expected_value : expected_values)
			if (expected_value.equals(actual_value))
				return;

		assertEquals(test_name, String.format("%s", to_string(expected_values), actual_value), hashtable.toString());
	}

	public final String to_string(String ... values)
	{
		int length = values.length;
		if (length == 0)
			return "";

		if (length == 1)
			return values[0];

		StringBuilder builder = new StringBuilder();
		builder.append(values[0]);
		for (int index = 1; index < length; index++)
			builder.append(" OR ").append(values[index]);
		return builder.toString();
	}
}

