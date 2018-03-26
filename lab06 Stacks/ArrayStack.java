//Benjamin Simpson, Nick Foster
public class ArrayStack<T> implements Stack<T> {

	private static final int CAPACITY_MULTIPLIER = 2;
	private static final int DEFAULT_CAPACITY = 15;

	
	private int numberOfElements = 0;
	private T[] array = (T[]) new Object[DEFAULT_CAPACITY];

	@Override
	public void push(T item) {
		ensureCapacity();
		array[numberOfElements] = item;
		numberOfElements++;
	}

	@Override
	public T pop() {
		if (isEmpty())
			return null;

		return array[--numberOfElements];

	}

	@Override
	public T peek() {
		if (numberOfElements == 0)
			return null;

		return array[numberOfElements - 1];
	}

	@Override
	public boolean isEmpty() {
		if (numberOfElements == 0)
			return true;

		return false;

	}

	public int size() {
		return numberOfElements;
	}
	
	private void ensureCapacity() {
		if (numberOfElements == (array.length - 1)) {
			T[] newArray = (T[]) new Object[(numberOfElements + 1) * CAPACITY_MULTIPLIER];
			System.arraycopy(array, 0, newArray, 0, numberOfElements);
			array = newArray;
		}
	}

}
