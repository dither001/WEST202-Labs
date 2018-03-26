//Benjamin Simpson, Nick Foster
import java.util.ArrayList;

public class ListStack<T> implements Stack<T> {

	private ArrayList<T> list = new ArrayList<T>();
	private int numberOfElements = 0;
	
	@Override
	public void push(T item) {
		list.add(0, item);
		numberOfElements++;
	}

	@Override
	public T pop() {
		if (isEmpty()) {
			return null;
		} else {

			T obj = list.get(0);
			list.remove(0);
			return obj;
		}
	}

	@Override
	public T peek() {
		if (isEmpty()) {
			return null;
		} else {

			return list.get(0);
		}
	}

	@Override
	public boolean isEmpty() {
		if (list.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	public int size() {
		return numberOfElements;
	}
}
