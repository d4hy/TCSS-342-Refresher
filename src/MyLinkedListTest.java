package src;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class MyLinkedListTest
{
	private MyLinkedList<Integer> list;
	
	@BeforeEach
	public final void setup()
	{
		list = new MyLinkedList<Integer>();
	}

	@Test
	public final void add_before()
	{
		list.addBefore(1);
		list.addBefore(2);
		list.addBefore(3);
		list.addBefore(4);
		assertEquals("[1, 2, 3, 4]", list.toString(), "add_before fail");
		assertEquals(null, list.current(), "add_before fail");
		assertEquals(Integer.valueOf(1), list.first(), "add_before fail");
		assertEquals(Integer.valueOf(1), list.current(), "add_before fail");
		assertEquals(Integer.valueOf(2), list.next(), "add_before fail");
		assertEquals(Integer.valueOf(2), list.current(), "add_before fail");
		assertEquals(4, list.size(), "add_before fail");
	}

	@Test
	public final void add_after()
	{
		list.addAfter(1);
		list.addAfter(2);
		list.addAfter(3);
		list.addAfter(4);
		assertEquals("[]", list.toString(), "add_after fail");
		assertEquals(0, list.size(), "add_after fail");
	}

	@Test
	public final void add_after_1()
	{
		list.addBefore(1);
		list.first();
		list.addAfter(2);
		list.addAfter(3);
		list.addAfter(4);
		assertEquals("[1, 4, 3, 2]", list.toString(), "add_after_1 fail");
		assertEquals(4, list.size(), "add_after_1 fail");
	}

	@Test
	public final void remove()
	{
		insert_ten_items(list);
		assertEquals(null, list.remove(), "remove fail");
		assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]", list.toString(), "remove fail");
		assertEquals(Integer.valueOf(0), list.first(), "remove fail");
		assertEquals(Integer.valueOf(0), list.remove(), "remove fail");
		assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9]", list.toString(), "remove fail");
		assertEquals(Integer.valueOf(2), list.next(), "remove fail");
		assertEquals(Integer.valueOf(3), list.next(), "remove fail");
		assertEquals(Integer.valueOf(4), list.next(), "remove fail");
		assertEquals(Integer.valueOf(4), list.remove(), "remove fail");
		assertEquals("[1, 2, 3, 5, 6, 7, 8, 9]", list.toString(), "remove fail");
		assertEquals(8, list.size(), "remove fail");
	}

	@Test
	public final void contains_and_not()
	{
		insert_ten_items(list);
		assertTrue(list.contains(3), "contains_and_not fail");
		assertFalse(list.contains(100), "contains_and_not fail");
		assertEquals(Integer.valueOf(0), list.first(), "contains_and_not fail");
		assertEquals(Integer.valueOf(1), list.next(), "contains_and_not fail");
		assertEquals(Integer.valueOf(2), list.next(), "contains_and_not fail");
		assertEquals(Integer.valueOf(3), list.next(), "contains_and_not fail");
		assertEquals(Integer.valueOf(3), list.remove(), "contains_and_not fail");
		assertFalse(list.contains(3), "contains_and_not fail");
		assertEquals("[0, 1, 2, 4, 5, 6, 7, 8, 9]", list.toString(), "contains_and_not fail");
	}

	@Test
	public final void empty()
	{
		assertTrue(list.isEmpty(), "empty fail");
		assertEquals("[]", list.toString(), "empty fail");
	}

	@Test
	public final void not_empty()
	{
		insert_ten_items(list);
		assertFalse(list.isEmpty(), "not_empty fail");
		assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]", list.toString(), "not_empty fail");
	}

	@Test
	public final void from_instruction()
	{
		assertEquals("[]", list.toString(), "from_instruction fail");
		list.addBefore(1);
		assertEquals("[1]", list.toString(), "from_instruction fail");
		list.addBefore(2);
		assertEquals("[1, 2]", list.toString(), "from_instruction fail");
		list.addBefore(3);
		assertEquals("[1, 2, 3]", list.toString(), "from_instruction fail");
		assertEquals(Integer.valueOf(1), list.first(), "from_instruction fail");
		list.addAfter(4);
		assertEquals("[1, 4, 2, 3]", list.toString(), "from_instruction fail");
		assertEquals(Integer.valueOf(1), list.current(), "from_instruction fail");
		assertEquals(Integer.valueOf(4), list.next(), "from_instruction fail");
		assertEquals("[1, 4, 2, 3]", list.toString(), "from_instruction fail");
		assertEquals(Integer.valueOf(4), list.current(), "from_instruction fail");
		assertEquals("[1, 4, 2, 3]", list.toString(), "from_instruction fail");
		assertEquals(Integer.valueOf(2), list.next(), "from_instruction fail");
		assertEquals("[1, 4, 2, 3]", list.toString(), "from_instruction fail");
		assertEquals(Integer.valueOf(2), list.current(), "from_instruction fail");
		list.addBefore(5);
		assertEquals("[1, 4, 5, 2, 3]", list.toString(), "from_instruction fail");
		list.addBefore(6);
		assertEquals("[1, 4, 5, 6, 2, 3]", list.toString(), "from_instruction fail");
		assertEquals(Integer.valueOf(2), list.remove(), "from_instruction fail");
		assertEquals("[1, 4, 5, 6, 3]", list.toString(), "from_instruction fail");
	}
	
	// ---------- helper methods ---------
	
	public final void insert_ten_items(MyLinkedList<Integer> list)
	{
		for (int index = 0; index < 10; index++)
			list.addBefore(index);
	}
}


