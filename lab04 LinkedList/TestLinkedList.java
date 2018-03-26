import static org.junit.Assert.*;

import org.junit.Test;

public class TestLinkedList {

	@Test
	public void testClear() {
		List list = new LinkedList();
		
		assertTrue(list.isEmpty());
		
		list.add("apples");
		list.add("bananas");
		list.add("cherries");
		
		assertFalse(list.isEmpty());
		
		list.clear();
		
		assertTrue(list.isEmpty());
	}

	@Test
	public void testAdd() {
		List list = new LinkedList();
		
		assertTrue(list.isEmpty());
		
		list.add("apples");
		list.add("bananas");
		list.add("cherries");
		
		assertEquals(3,list.getLength());
		
		assertTrue(list.contains("apples"));
		assertTrue(list.contains("bananas"));
		assertTrue(list.contains("cherries"));
		assertFalse(list.contains("donuts"));
		
		assertEquals("apples",list.get(0));
		assertEquals("bananas",list.get(1));
		assertEquals("cherries",list.get(2));
		assertNull(list.get(3));
	}

	@Test
	public void testGet() {
		List list = new LinkedList();
		
		list.add("apples");
		assertEquals("apples",list.get(0));
		
		list.add("bananas");
		list.add("cherries");
		list.add("donuts");
		
		assertEquals("apples",list.get(0));
		assertEquals("bananas",list.get(1));
		assertEquals("cherries",list.get(2));
		assertEquals("donuts",list.get(3));
		
		assertNull(list.get(4));
	}
	
	@Test
	public void testAddIndex() {
		List list = new LinkedList();
		
		list.add("apple");		
		list.add("banana");
		list.add("cherries",1);
		list.add("donut",0);
		
		assertEquals("donut",list.get(0));
		assertEquals("apple",list.get(1));
		assertEquals("cherries",list.get(2));
		assertEquals("banana",list.get(3));
	}
	
	
	
	@Test
	public void testRemoveIndex() {
		List list = new LinkedList();
		
		assertNull(list.remove(0));
		
		list.add("apples");
		list.add("bananas");
		list.add("cherries");
		list.add("donuts");
		
		assertEquals("apples",list.remove(0));
		assertEquals("bananas",list.remove(0));
		assertEquals("cherries",list.remove(0));
		assertEquals("donuts",list.remove(0));
		
		assertTrue(list.isEmpty());
		assertEquals(0,list.getLength());
		
		list.add("apples");
		list.add("bananas");
		list.add("cherries");
		list.add("donuts");
		list.add("eggplant");
		
		assertEquals("apples",list.remove(0));
		assertEquals("cherries",list.remove(1));
		assertEquals("eggplant",list.remove(2));
		
		assertFalse(list.isEmpty());
		assertEquals(2,list.getLength());
		
		assertEquals("bananas",list.get(0));
		assertEquals("donuts",list.get(1));
	}
	
	@Test
	public void testRemoveObject() {
		List list;
		
		// first test with an empty list	
		list = new LinkedList();
		assertFalse(list.remove("apples"));
		
		// test with a list of size 1	
		
		list.add("apples");
		assertTrue(list.remove("apples"));
		assertTrue(list.isEmpty());
		assertEquals(0,list.getLength());
		
		list.add("apples");
		assertFalse(list.remove("bananas"));
		assertFalse(list.isEmpty());
		assertEquals(1,list.getLength());
		
		
		// test with list of size 2
		list = new LinkedList();
		
		list.add("apples");
		list.add("bananas");
		assertEquals(2,list.getLength());
		assertTrue(list.remove("apples"));
		assertEquals("bananas", list.get(0));
		
		// test with larger list
		list = new LinkedList();
		
		list.add("apples");
		list.add("bananas");
		list.add("cherries");
		list.add("donuts");
		list.add("eggplant");
		
		assertTrue(list.remove("apples"));
		assertTrue(list.remove("cherries"));
		assertTrue(list.remove("eggplant"));
		assertEquals(2,list.getLength());
		assertEquals("bananas", list.get(0));
		assertEquals("donuts", list.get(1));
		
		// test for non-existent items
		list = new LinkedList();
		
		list.add("apples");
		list.add("bananas");
		list.add("cherries");
		list.add("donuts");
		list.add("eggplant");
		
		assertFalse(list.remove("fruitcake"));
	}
	
	@Test
	public void testFrequency() {
		List list = new LinkedList();
		
		assertEquals(0, list.getFrequency("apples"));
		
		list.add("apples");
		list.add("bananas");
		list.add("apples");
		list.add("donuts");
		list.add("apples");
		
		assertEquals(3, list.getFrequency("apples"));
		assertEquals(1, list.getFrequency("bananas"));
		assertEquals(1, list.getFrequency("donuts"));
	}
}
