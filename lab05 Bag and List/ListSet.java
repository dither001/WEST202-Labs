import java.util.ArrayList;
import java.util.Iterator;

//import ArraySet.ArraySetIterator;

public class ListSet<T> implements Set<T> {
	private ArrayList<T> elements;
	
	public ListSet() {
		elements = new ArrayList<T>();
	}

	@Override
	public void add(T element) {
		this.elements.add(element);
	}

	@Override
	public void addAll(T[] elements) {
		for (T el : elements) {
			this.elements.add(el);
		}
	}

	@Override
	public boolean contains(T element) {
		return this.elements.contains(element);
	}

	@Override
	public int getSize() {
		return this.elements.size();
	}

	@Override
	public void remove(T element) {
		this.elements.remove(element);
	}

	@Override
	public Set<T> union(Set<T> anotherSet) {
		for (int i = 0; i < this.getSize(); ++i) {
			if (! (anotherSet.contains(elements.get(i))))
				anotherSet.add(elements.get(i));
		}
		
		return anotherSet;
	}

	@Override
	public Set<T> intersection(Set<T> anotherSet) {
		Set<T> s = new ListSet<T>();
		
		for (int i = 0; i <= anotherSet.getSize(); ++i) {
			if (anotherSet.contains(elements.get(i))) {
				s.add(elements.get(i));
			}
		}
				
		return s;
	}

	@Override
	public Set<T> difference(Set<T> anotherSet) {		
		Set<T> s = new ListSet<T>();
		
		for (int i = 0; i <= anotherSet.getSize(); ++i) {
			if (!(anotherSet.contains(elements.get(i)))) {
				s.add(elements.get(i));
			}
		}
		
		return s;
	}

	@Override
	public Iterator<T> iterator() {
		return this.elements.listIterator();
	}
	
}
