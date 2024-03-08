package src;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyArrayListTest
{
	private MyArrayList<Integer> list;
	
	@BeforeEach
	public final void setup()
	{
		list = new MyArrayList<Integer>();
	}
	
	@Test
	public final void size_0()
	{
		assertEquals(0, list.size(), "size_0 fail");	
	}
	
	@Test
	public final void size_1()
	{
		list.insert(1, 0);
		assertEquals(1, list.size(), "size_1 failed");
	}
	
	@Test
	public final void insert_1()
	{
		list.insert(0, 0);
		list.insert(1, 1);
		list.insert(2, 2);
		assertEquals("[0, 1, 2]", list.toString(), "insert failed");	
	}
	
	@Test
	public final void insert_2()
	{
		list.insert(0, 2);
		list.insert(1, 1);
		list.insert(2, 0);
		assertEquals("[2]", list.toString(), "insert failed");	
	}
	
	@Test
	public final void index_in_bound()
	{
		list.insert(1, 0);
		assertEquals(1, list.size(), "index_in_bound failed");	
	}
	
	@Test
	public final void index_out_of_bound_positive()
	{
		list.insert(1, 1);
		assertEquals(0, list.size(), "index_out_of_bound_positive failed");	
	}
	
	@Test
	public final void index_out_of_bound_negative()
	{
		list.insert(1, -1);
		assertEquals(0, list.size(), "index_out_of_bound_negative failed");	
	}
	
	@Test
	public final void resize()
	{
		insert_twenty_items(list);
		assertEquals(20, list.size(), "resize failed");	
	}
	
	@Test
	public final void contains()
	{
		insert_ten_items(list);
		for (int index = 0; index < 10; index++)
			assertTrue(list.contains(index), "contains failed");
	}
	
	@Test
	public final void not_contain()
	{
		insert_ten_items(list);
		for (int index = 10; index < 20; index++)
			assertFalse(list.contains(index), "not_contain failed");
	}
	
	@Test
	public final void contains_after_expand()
	{
		insert_twenty_items(list);
		for (int index = 0; index < 20; index++)
			assertTrue(list.contains(index), "contains_after_expand failed");
	}
	
	@Test
	public final void not_contain_after_expand()
	{
		insert_twenty_items(list);
		for (int index = 20; index < 40; index++)
			assertFalse(list.contains(index), "not_contain_after_expand failed");
	}
	
	@Test
	public final void index_of_has_value()
	{
		insert_ten_items_values_added_5(list);
		for (int index = 0; index < 10; index++)
			assertEquals(index, list.indexOf(index+5), "index_of_has_value failed");
	}
	
	@Test
	public final void index_of_value_not_found()
	{
		insert_ten_items_values_added_5(list);
		for (int index = 0; index < 5; index++)
			assertEquals(-1, list.indexOf(index), "index_of_value_not_found failed");
		for (int index = 15; index < 20; index++)
			assertEquals(-1, list.indexOf(index), "index_of_value_not_found failed");
	}
	
	@Test
	public final void get_in_bound()
	{
		insert_ten_items(list);
		for (int index = 0; index < 10; index++)
			assertEquals(Integer.valueOf(index), list.get(index), "get_in_bound failed");
	}
	
	@Test
	public final void get_out_bound()
	{
		insert_ten_items(list);
		for (int index = -5; index < 0; index++)
			assertEquals(null, list.get(index), "get_out_bound failed");
		for (int index = 10; index < 15; index++)
			assertEquals(null, list.get(index), "get_out_bound failed");
	}
	
	@Test
	public final void set_in_bound()
	{
		insert_ten_items(list);
		list.set(0, -1);
		list.set(2, -1);
		list.set(4, -1);
		list.set(6, -1);
		list.set(8, -1);
		for (int index = 0; index < 10; index++)
			if (index % 2 == 0)
				assertEquals(Integer.valueOf(-1), list.get(index), "set_in_bound failed");
			else
				assertEquals(Integer.valueOf(index), list.get(index), "set_in_bound failed");
	}
	
	@Test
	public final void set_out_bound()
	{
		insert_ten_items(list);
		list.set(-1, -1);
		list.set(-2, -1);
		list.set(-3, -1);
		list.set(10, -1);
		list.set(11, -1);
		for (int index = 0; index < 10; index++)
			assertEquals(Integer.valueOf(index), list.get(index), "set_out_bound failed");
	}
	
	@Test
	public final void empty()
	{
		assertTrue(list.isEmpty(), "empty failed");	
	}
	
	@Test
	public final void not_empty()
	{
		list.insert(1, 0);
		assertFalse(list.isEmpty(), "not_empty failed");	
	}
	
	@Test
	public final void not_empty_more_items()
	{
		insert_ten_items(list);
		assertFalse(list.isEmpty(), "not_empty_more_items failed");	
	}
	
	@Test
	public final void not_empty_after_resize()
	{
		insert_twenty_items(list);
		assertFalse(list.isEmpty(), "not_empty_after_resize failed");	
	}
	
	@Test
	public final void to_string_1()
	{
		insert_ten_items(list);
		assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]", list.toString(), "to_string_1 failed");	
	}
	
	@Test
	public final void to_string_2()
	{
		insert_ten_items(list);
		assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]", list.toString(), "to_string_1 failed");	
		
		list.set(3, -1);
		list.set(6, -1);
		list.set(9, -1);
		assertEquals("[0, 1, 2, -1, 4, 5, -1, 7, 8, -1]", list.toString(), "to_string_1 failed");
		
		list.insert(-2, 0);
		list.insert(-2, 6);
		assertEquals("[-2, 0, 1, 2, -1, 4, -2, 5, -1, 7, 8, -1]", list.toString(), "to_string_1 failed");
		
		list.remove(2);
		list.remove(6);
		list.remove(8);
		assertEquals("[-2, 0, 2, -1, 4, -2, -1, 7, -1]", list.toString(), "to_string_1 failed");
	}
	
	// ---------- helper methods ---------
	
	public final void insert_ten_items(MyArrayList<Integer> list)
	{
		for (int index = 0; index < 10; index++)
			list.insert(index, index);
	}
	
	public final void insert_ten_items_values_added_5(MyArrayList<Integer> list)
	{
		for (int index = 0; index < 10; index++)
			list.insert(index+5, index);
	}
	
	public final void insert_twenty_items(MyArrayList<Integer> list)
	{
		for (int index = 0; index < 20; index++)
			list.insert(index, index);
	}
}


