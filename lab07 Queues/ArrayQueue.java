/*
 * @Author	Nick Foster - working solo :(
 * @Date	October 2017
 */

public class ArrayQueue<T> implements Queue<T> {
	private static final int CAPACITY_MULTIPLIER = 2;
	private static final int DEFAULT_SIZE = 10;

	private T[] contents;
	private int numberOfElements = 0;
	private int front = 0;
	private int rear = 0;

	public ArrayQueue() {
		this(DEFAULT_SIZE);
	}

	@SuppressWarnings("unchecked")
	public ArrayQueue(int i) {
		this.contents = (T[]) new Object[i];
	}

	@Override
	public void add(T item) {
		// make sure there's room for the new thing
		ensureCapacity();
		
		if (numberOfElements == 0) {
			contents[0] = item;
			front = 0;
			rear = 0;
		} else if (rear + 1 > contents.length - 1) {
			contents[0] = item;
			rear = 0;
		} else {
			contents[++rear] = item;
		}
		
		++numberOfElements;
	}

	@Override
	public T remove() {
		if (numberOfElements == 0) {
			return null;
		} else if (front + 1 < contents.length - 1) {
			--numberOfElements;
			return contents[front++];
		} else {
			int temp = front;
			front = 0;
			--numberOfElements;
			return contents[temp];
		}
	}

	@Override
	public boolean isEmpty() {
		boolean isEmpty = false;
		if (numberOfElements == 0) {
			isEmpty = true;
		}

		return isEmpty;
	}

	@Override
	public int size() {
		return numberOfElements;
	}

	private void ensureCapacity() {
		if (numberOfElements == (contents.length - 1)) {
			T[] tempArray = (T[]) new Object[(numberOfElements + 1) * CAPACITY_MULTIPLIER];

			int oldIndex = front;
			for (int i = 0; i < numberOfElements; ++i) {
				if (oldIndex > (contents.length - 1))
					oldIndex = 0;
				
				tempArray[i] = contents[oldIndex++];
				rear = i;
			}

			contents = tempArray;
			front = 0;
		}
	}

}
