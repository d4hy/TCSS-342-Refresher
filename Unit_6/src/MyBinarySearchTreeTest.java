
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyBinarySearchTreeTest
{
	private MyBinarySearchTree<Integer> tree;
	private UniqueWords un;

	@BeforeEach
	public final void setup()
	{
		tree = new MyBinarySearchTree<Integer>();
	}
	
	@Test
	public final void insert_e()
	{
		assertEquals("[]", tree.toString(), "insert_e failed");
	}
	
	@Test
	public final void insert_0()
	{
		tree.add(0);
		assertEquals("[0:H0]", tree.toString(), "insert_0 failed");
	}
	
	@Test
	public final void insert_1()
	{
		tree.add(0);
		tree.add(1);
		assertEquals("[0:H1, 1:H0]", tree.toString(), "insert_1 failed");
	}
	
	@Test
	public final void insert_2()
	{
		tree.add(1);
		tree.add(0);
		assertEquals("[0:H0, 1:H1]", tree.toString(), "insert_2 failed");
	}
	
	@Test
	public final void insert_3()
	{
		tree.add(0);
		tree.add(1);
		tree.add(2);
		assertEquals("[0:H2, 1:H1, 2:H0]", tree.toString(), "insert_3 failed");
	}
	
	@Test
	public final void insert_4()
	{
		tree.add(0);
		tree.add(2);
		tree.add(1);
		assertEquals("[0:H2, 1:H0, 2:H1]", tree.toString(), "insert_4 failed");
	}
	
	@Test
	public final void insert_5()
	{
		tree.add(1);
		tree.add(0);
		tree.add(2);
		assertEquals("[0:H0, 1:H1, 2:H0]", tree.toString(), "insert_5 failed");
	}
	
	@Test
	public final void insert_6()
	{
		tree.add(1);
		tree.add(2);
		tree.add(0);
		assertEquals("[0:H0, 1:H1, 2:H0]", tree.toString(), "insert_6 failed");
	}
	
	@Test
	public final void insert_7()
	{
		tree.add(2);
		tree.add(0);
		tree.add(1);
		assertEquals("[0:H1, 1:H0, 2:H2]", tree.toString(), "insert_7 failed");
	}
	
	@Test
	public final void insert_8()
	{
		tree.add(2);
		tree.add(1);
		tree.add(0);
		assertEquals("[0:H0, 1:H1, 2:H2]", tree.toString(), "insert_8 failed");
	}
	
	@Test
	public final void insert_9()
	{
		insert_ten_items_asc(tree);
		assertEquals("[0:H9, 1:H8, 2:H7, 3:H6, 4:H5, 5:H4, 6:H3, 7:H2, 8:H1, 9:H0]", tree.toString(), "insert_9 failed");
	}
	
	@Test
	public final void insert_10()
	{
		insert_ten_items_des(tree);
		assertEquals("[0:H0, 1:H1, 2:H2, 3:H3, 4:H4, 5:H5, 6:H6, 7:H7, 8:H8, 9:H9]", tree.toString(), "insert_10 failed");
	}
	
	@Test
	public final void insert_11()
	{
		insert_ten_items_ran(tree);
		assertEquals("[0:H0, 1:H1, 2:H0, 3:H2, 4:H0, 5:H3, 6:H0, 7:H2, 8:H1, 9:H0]", tree.toString(), "insert_11 failed");
	}
	
	@Test
	public final void height_0()
	{
		assertEquals(-1, tree.height(), "height_0 failed");
	}
	
	@Test
	public final void height_1()
	{
		tree.add(0);
		assertEquals(0, tree.height(), "height_1 failed");
	}
	
	@Test
	public final void height_2()
	{
		tree.add(0);
		tree.add(1);
		assertEquals(1, tree.height(), "height_2 failed");
	}
	
	@Test
	public final void height_3()
	{
		tree.add(0);
		tree.add(1);
		tree.add(2);
		assertEquals(2, tree.height(), "height_3 failed");
	}
	
	@Test
	public final void height_4()
	{
		insert_ten_items_asc(tree);
		assertEquals(9, tree.height(), "height_4 failed");
	}
	
	@Test
	public final void height_5()
	{
		insert_ten_items_des(tree);
		assertEquals(9, tree.height(), "height_5 failed");
	}
	
	@Test
	public final void height_6()
	{
		insert_ten_items_ran(tree);
		assertEquals(3, tree.height(), "height_6 failed");
	}

	@Test
	public final void remove_0()
	{
		tree.remove(0);
		assertEquals("[]", tree.toString(), "remove_0 failed");
	}

	@Test
	public final void remove_1()
	{
//		System.setProperty("BST_Remove", "GO_LEFT_FIND_MAX");
//		System.setProperty("BST_Remove", "GO_RIGHT_FIND_MIN");
		insert_ten_items_ran(tree);
		tree.remove(10);
		assertTest("remove_1 failed", tree.toString(),
				"[0:H0, 1:H1, 2:H0, 3:H2, 4:H0, 5:H3, 6:H0, 7:H2, 8:H1, 9:H0]",
				"[0:H0, 1:H1, 2:H0, 3:H2, 4:H0, 5:H3, 6:H0, 7:H2, 8:H1, 9:H0]");
		assertEquals(tree.size(), 10, "remove_1 failed");
		tree.remove(0);
		assertTest("remove_1 failed", tree.toString(),
				"[1:H1, 2:H0, 3:H2, 4:H0, 5:H3, 6:H0, 7:H2, 8:H1, 9:H0]",
				"[1:H1, 2:H0, 3:H2, 4:H0, 5:H3, 6:H0, 7:H2, 8:H1, 9:H0]");
		assertEquals(tree.size(), 9, "remove_1 failed");
		tree.remove(5);
		assertTest("remove_1 failed", tree.toString(),
				"[1:H1, 2:H0, 3:H2, 4:H3, 6:H0, 7:H2, 8:H1, 9:H0]",
				"[1:H1, 2:H0, 3:H2, 4:H0, 6:H3, 7:H2, 8:H1, 9:H0]");
		assertEquals(tree.size(), 8, "remove_1 failed");
		tree.remove(9);
		assertTest("remove_1 failed", tree.toString(),
				"[1:H1, 2:H0, 3:H2, 4:H3, 6:H0, 7:H1, 8:H0]",
				"[1:H1, 2:H0, 3:H2, 4:H0, 6:H3, 7:H1, 8:H0]");
		assertEquals(tree.size(), 7, "remove_1 failed");
		tree.remove(3);
		assertTest("remove_1 failed", tree.toString(),
				"[1:H1, 2:H0, 4:H2, 6:H0, 7:H1, 8:H0]",
				"[1:H1, 2:H0, 4:H2, 6:H3, 7:H1, 8:H0]",
				"[1:H0, 2:H1, 4:H0, 6:H2, 7:H1, 8:H0]");
		assertEquals(tree.size(), 6, "remove_1 failed");
		tree.remove(7);
		assertTest("remove_1 failed", tree.toString(),
				"[1:H1, 2:H0, 4:H2, 6:H1, 8:H0]",
				"[1:H1, 2:H0, 4:H2, 6:H0, 8:H1]",
				"[1:H1, 2:H0, 4:H2, 6:H3, 8:H0]",
				"[1:H0, 2:H1, 4:H0, 6:H2, 8:H0]");
		assertEquals(tree.size(), 5, "remove_1 failed");
		tree.remove(1);
		assertTest("remove_1 failed", tree.toString(),
				"[2:H0, 4:H2, 6:H1, 8:H0]",
				"[2:H0, 4:H2, 6:H0, 8:H1]",
				"[2:H0, 4:H1, 6:H2, 8:H0]",
				"[2:H1, 4:H0, 6:H2, 8:H0]");
		assertEquals(tree.size(), 4, "remove_1 failed");
		tree.remove(8);
		assertTest("remove_1 failed", tree.toString(),
				"[2:H0, 4:H1, 6:H0]",
				"[2:H0, 4:H1, 6:H2]",
				"[2:H1, 4:H0, 6:H2]");
		assertEquals(tree.size(), 3, "remove_1 failed");
		tree.remove(4);
		assertTest("remove_1 failed", tree.toString(),
				"[2:H1, 6:H0]",
				"[2:H0, 6:H1]");
		assertEquals(tree.size(), 2, "remove_1 failed");
		tree.remove(1);
		assertTest("remove_1 failed", tree.toString(),
				"[2:H1, 6:H0]",
				"[2:H0, 6:H1]");
		assertEquals(tree.size(), 2, "remove_1 failed");
		tree.remove(2);
		assertTest("remove_1 failed", tree.toString(),
				"[6:H0]",
				"[6:H0]");
		assertEquals(tree.size(), 1, "remove_1 failed");
		tree.remove(6);
		assertEquals("[]", tree.toString(), "remove_1 failed");
		assertEquals(tree.size(), 0, "remove_1 failed");
		tree.remove(0);
		assertEquals("[]", tree.toString(), "remove_1 failed");
		assertEquals(tree.size(), 0, "remove_1 failed");
	}

	@Test
	public final void find_0()
	{
		assertEquals("[]", tree.toString(), "find_0 failed");
		assertEquals(null, tree.find(0), "find_0 failed");
		assertEquals(1, tree.comparisons, "find_0 failed");
	}

	@Test
	public final void find_1()
	{
		insert_ten_items_asc(tree);
		assertEquals("[0:H9, 1:H8, 2:H7, 3:H6, 4:H5, 5:H4, 6:H3, 7:H2, 8:H1, 9:H0]", tree.toString(), "find_1 failed");
		assertEquals(null, tree.find(10), "find_1 failed");
		assertEquals(11, tree.comparisons, "find_1 failed");
		assertEquals(Integer.valueOf(0), tree.find(0), "find_1 failed");
		assertEquals(12, tree.comparisons, "find_1 failed");
		assertEquals(Integer.valueOf(9), tree.find(9), "find_1 failed");
		assertEquals(22, tree.comparisons, "find_1 failed");
		assertEquals(Integer.valueOf(5), tree.find(5), "find_1 failed");
		assertEquals(28, tree.comparisons, "find_1 failed");
	}

	@Test
	public final void find_2()
	{
		insert_ten_items_des(tree);
		assertEquals("[0:H0, 1:H1, 2:H2, 3:H3, 4:H4, 5:H5, 6:H6, 7:H7, 8:H8, 9:H9]", tree.toString(), "find_2 failed");
		assertEquals(null, tree.find(10), "find_2 failed");
		assertEquals(2, tree.comparisons, "find_2 failed");
		assertEquals(Integer.valueOf(0), tree.find(0), "find_2 failed");
		assertEquals(12, tree.comparisons, "find_2 failed");
		assertEquals(Integer.valueOf(9), tree.find(9), "find_2 failed");
		assertEquals(13, tree.comparisons, "find_2 failed");
		assertEquals(Integer.valueOf(5), tree.find(5), "find_2 failed");
		assertEquals(18, tree.comparisons, "find_2 failed");
	}

	@Test
	public final void find_3()
	{
		insert_ten_items_ran(tree);
		assertEquals("[0:H0, 1:H1, 2:H0, 3:H2, 4:H0, 5:H3, 6:H0, 7:H2, 8:H1, 9:H0]", tree.toString(), "find_3 failed");
		assertEquals(null, tree.find(10), "find_3 failed");
		assertEquals(5, tree.comparisons, "find_3 failed");
		assertEquals(Integer.valueOf(0), tree.find(0), "find_3 failed");
		assertEquals(9, tree.comparisons, "find_3 failed");
		assertEquals(Integer.valueOf(1), tree.find(1), "find_3 failed");
		assertEquals(12, tree.comparisons, "find_3 failed");
		assertEquals(Integer.valueOf(2), tree.find(2), "find_3 failed");
		assertEquals(16, tree.comparisons, "find_3 failed");
		assertEquals(Integer.valueOf(3), tree.find(3), "find_3 failed");
		assertEquals(18, tree.comparisons, "find_3 failed");
		assertEquals(Integer.valueOf(4), tree.find(4), "find_3 failed");
		assertEquals(21, tree.comparisons, "find_3 failed");
		assertEquals(Integer.valueOf(5), tree.find(5), "find_3 failed");
		assertEquals(22, tree.comparisons, "find_3 failed");
		assertEquals(Integer.valueOf(6), tree.find(6), "find_3 failed");
		assertEquals(25, tree.comparisons, "find_3 failed");
		assertEquals(Integer.valueOf(7), tree.find(7), "find_3 failed");
		assertEquals(27, tree.comparisons, "find_3 failed");
		assertEquals(Integer.valueOf(8), tree.find(8), "find_3 failed");
		assertEquals(30, tree.comparisons, "find_3 failed");
		assertEquals(Integer.valueOf(9), tree.find(9), "find_3 failed");
		assertEquals(34, tree.comparisons, "find_3 failed");
	}

	@Test
	public final void empty_and_non_empty()
	{
		assertEquals(true, tree.isEmpty(), "empty failed");
		insert_ten_items_ran(tree);
		assertEquals(false, tree.isEmpty(), "non_empty failed");
	}
	@Test
	public final void unique_words() throws Exception
	{
		un = new UniqueWords();
		un.addUniqueWordsToBST();
		assertEquals(true, un.book.book.length() == 3291642 || un.book.book.length() == 3291623, "unique_words failed");
		assertEquals(570240, un.book.words.size(), "unique_words failed");
		assertEquals(20228, un.bstOfUniqueWords.size(), "unique_words failed");
		assertEquals(6914511, un.bstOfUniqueWords.comparisons, "unique_words failed");
		assertEquals(40, un.bstOfUniqueWords.root.height, "unique_words failed");
	}

	// ---------- helper methods ---------

	public final void insert_ten_items_asc(MyBinarySearchTree<Integer> tree)
	{
		for (int index = 0; index < 10; index++)
			tree.add(index);
	}

	public final void insert_ten_items_des(MyBinarySearchTree<Integer> tree)
	{
		for (int index = 9; index >= 0; index--)
			tree.add(index);
	}

	public final void insert_ten_items_ran(MyBinarySearchTree<Integer> tree)
	{
		int array[] = { 5, 3, 7, 1, 4, 6, 8, 2, 0, 9 };
		for (int index = 0; index < array.length; index++)
			tree.add(array[index]);
	}

	// helper function for old unit testing library
	public final void assertTest(String test_name, String actual_value, String ... expected_values)
	{
		for (String expected_value : expected_values)
			if (expected_value.equals(actual_value))
				return;

		assertEquals(String.format("%s", to_string(expected_values), actual_value), tree.toString(), test_name);
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

