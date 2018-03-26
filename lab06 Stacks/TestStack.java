/**
 * Unit tests to test implementation of Stack interface.
 */
import static org.junit.Assert.*;

import org.junit.Test;


public class TestStack {

	/**
	 * Tests an array-based version of the STack
	 */
	@Test
	public void testArrayStack() {
		
		Stack<String> s = new ArrayStack<String>();
		
		assertTrue(s.isEmpty());
		assertNull(s.peek());
		assertNull(s.pop());
		
		s.push("apple");
		s.push("banana");
		s.push("cherry");
		
		assertFalse(s.isEmpty());
		
		assertEquals("cherry", s.peek());
		assertEquals("cherry", s.pop());
		assertEquals("banana", s.peek());
		assertEquals("banana", s.pop());
		assertEquals("apple", s.peek());
		assertEquals("apple", s.pop());
		
		assertTrue(s.isEmpty());
		assertNull(s.pop());

		s.push("donut");

		assertFalse(s.isEmpty());

		assertEquals("donut", s.pop());
	}

	/**
	 * Tests a list-based version of the stack
	 */
	@Test
	public void testListStack() {
		
		Stack<String> s = new ListStack<String>();
		
		assertTrue(s.isEmpty());
		assertNull(s.peek());
		assertNull(s.pop());
		
		s.push("apple");
		s.push("banana");
		s.push("cherry");
		
		assertFalse(s.isEmpty());
		
		assertEquals("cherry", s.peek());
		assertEquals("cherry", s.pop());
		assertEquals("banana", s.peek());
		assertEquals("banana", s.pop());
		assertEquals("apple", s.peek());
		assertEquals("apple", s.pop());
		
		assertTrue(s.isEmpty());
		assertNull(s.pop());

		s.push("donut");

		assertFalse(s.isEmpty());

		assertEquals("donut", s.pop());
	}

	@Test
	public void testManiacalArrayStack() {
		// performs a stress-test of the implementations

		Stack<Integer> s = new ArrayStack<Integer>();

		for (int i = 0; i < 100000; i++) {
			s.push(new Integer(i));
		}

		for (int i = 99999; i >= 0; i--) {
			assertEquals(new Integer(i), s.pop());
		}
	}
	
	@Test
	public void testManiacalListStack() {
		// performs a stress-test of the implementation
		
		Stack<Integer> s = new ListStack<Integer>();
		
		for (int i = 0; i < 100000; i++) {
			s.push(new Integer(i));
		}

		for (int i = 99999; i >= 0; i--) {
			assertEquals(new Integer(i), s.pop());
		}
	}

}
