/**
 * Implementation of the List interface.
 * 
 * This implementation involves a single-linked list.
 * 
 * @author Joelee Cherrington & Nick Foster
 * @date September 2017
 * 
 * @credit original author: Greg Gagne - February 2017
 *
 */

public class LinkedList implements List {
	// reference to the head of the linked list
	private Node head;

	// number of elements in the list
	private int numberOfElements;

	public LinkedList() {
		head = null;
	}

	/** 
	 * Inner class representing a node in the linked list
	 */

	private class Node
	{
		private Object data;
		private Node next;

		private Node(Object data) {
			this(data,null);
		}

		private Node (Object data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	// Methods

	@Override
	public void add(Object item) {
		// adds (appends) an item to the rear of the list
		Node newNode = new Node(item);
		Node current = head;

		if (isEmpty()) {
			// special case - first element being added to the list
			head = newNode;
		}
		else {
			while (current.next != null) {
				current = current.next;
			}

			// current now references the last item in the list
			current.next = newNode;
		}

		newNode.next = null;
		++numberOfElements;
	}

	@Override
	public boolean add(Object item, int index) {
		if (index == 0) {
			Node newNode = new Node(item, head);
			head = newNode;
			
			++numberOfElements;
			return true;
		}
		else if (index > 0 && index < this.numberOfElements) {
			Node current = head;
			Node previous = current;
			
			for (int i = 0; i < index; ++i) {
				previous = current;
				current = current.next;
			}
			
			Node newNode = new Node(item, current);
			previous.next = newNode;
			
			++numberOfElements;
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean contains(Object item) {
		Node current = head;
		boolean found = false;

		while (current != null && !found) {
			if (current.data.equals(item)) {
				found = true;
			}

			current = current.next;
		}

		return found;

	}

	@Override
	public Object get(int index) {
		if (index == 0) {
			return head.data;
		}
		else if (index > 0 && index < this.numberOfElements) {
			Node current = head;

			for (int i = 0; i < index; ++i) {
				current = current.next;
			}
			
			return current.data;
		}
		else {
			return null;
		}
	}

	@Override
	public boolean remove(Object item) {
		// just in case we can end this early
		if (isEmpty()) return false;
		
		// special case when the head is cut off
		if (head.data.equals(item)) {
			head = head.next;
			
			--numberOfElements;
			return true;
		}
		
		Node current = head;
		Node previous = current;
		for (int i = 0; i < numberOfElements; ++i) {
			if (current.data.equals(item)) {
				previous.next = current.next;
				
				--numberOfElements;
				return true;
			}
			else {
				previous = current;
				current = current.next;
			}
		}

		return false;
	}

	@Override
	public Object remove(int index) {
		Object rv = null;

		if (isEmpty() || index >= numberOfElements) {
			rv = null;
		}
		else if (index == 0) {
			// special case - first element in the list
			rv = head.data;
			head = head.next;
			numberOfElements--;
		}
		else {
			int currentIndex = 0;
			Node current = head;

			while (currentIndex < (index - 1)) {
				current = current.next;
				currentIndex++;
			}

			// current references the node we want to remove
			rv = current.next.data;
			current.next = current.next.next;
			numberOfElements--;
		}

		return rv;
	}

	@Override
	public int getLength() {
		return numberOfElements;
	}

	@Override
	public boolean isEmpty() {
		if (numberOfElements == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int getFrequency(Object item) {
		Node current = head;
		int count = 0;
		
		for (int i = 0; i < numberOfElements; ++i) {
			if (current.data.equals(item)) {
				++count;
			}
			current = current.next;
		}
		
		return count;
	}

	@Override
	public void clear() {
		head = null;
		numberOfElements = 0;

	}

}
