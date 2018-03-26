/*
 * @Author	Nick Foster - working solo :(
 * @Date	October 2017
 */

import java.util.ArrayList;

public class ListQueue<T> implements Queue<T> {
	private ArrayList<T> list;
	
	public ListQueue() {
		this.list = new ArrayList<T>();
	}

	@Override
	public void add(T item) {
		list.add(item);
	}

	@Override
	public T remove() {
		if (list.isEmpty()) return null;
		else return list.remove(0);
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public int size() {
		return list.size();
	}

}
