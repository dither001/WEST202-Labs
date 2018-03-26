/**
 * Implementation of DictionaryInterface using Linear Probing
 * 
 * @author Destin West & Nick Foster - November 2017
 */

public class HashDictionaryLinear<K, V> implements DictionaryInterface<K, V> {

	// initial size of hash table
	private static int DEFAULT_SIZE = 13;

	// When capacity exceeds this threshold, a new addition will trigger rehashing
	private static double CAPACITY_THRESHOLD = 0.67;

	// the number of elements in the hash table
	private int numberOfElements;

	// the hash table
	private TableElement<K, V>[] dictionary;

	// the current capacity of the hash table
	// this is a prime number
	private int currentCapacity;

	/**
	 * Inner class representing an element in the hash table This consists of a
	 * [Key:Value] mapping
	 *
	 * @param <K>
	 *            Key
	 * @param <V>
	 *            Value
	 */
	@SuppressWarnings("hiding")
	private class TableElement<K, V> {
		private K key;
		private V value;
		private boolean removed;

		private TableElement(K key, V value) {
			this.key = key;
			this.value = value;
			this.removed = false;
		}

		/**
		 * Two TableElement objects are equals if they both have the same key
		 */
		@SuppressWarnings("unchecked")
		public boolean equals(Object other) {
			boolean flag = false;

			if (other instanceof TableElement) {
				TableElement<K, V> candidate = (TableElement<K, V>) other;

				if ((this.getKey()).equals(candidate.getKey()))
					flag = true;
			}

			return flag;
		}

		private K getKey() {
			return key;
		}

		private V getValue() {
			return value;
		}

		private void setKey(K key) {
			this.key = key;
		}

		private void setValue(V value) {
			this.value = value;
		}
		
		private boolean removed() {
			return this.removed;
		}
		
		private void markForRemoval() {
			--numberOfElements;
			this.removed = true;
		}
	}
	
	public HashDictionaryLinear() {
		this(DEFAULT_SIZE);
	}

	@SuppressWarnings("unchecked")
	public HashDictionaryLinear(int size) {
		if (size < 0)
			throw new IllegalArgumentException();

		dictionary = (TableElement<K, V>[]) new TableElement[size];
		numberOfElements = 0;
		currentCapacity = size;
	}

	/**
	 * Returns the hash value in the range [0 .. currentCapacity-1]
	 * 
	 * @param key
	 * @return int
	 */
	private int hashValue(K key) {
		return (Math.abs(key.hashCode()) % currentCapacity);
	}

	/**
	 * This calls the appropriate hashing strategy
	 */
	public V put(K key, V value) {
		return linearProbing(key, value);
	}

	/**
	 * Private helper method that implements appropriate hashing strategy
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	private V linearProbing(K key, V value) {

		// re-hash if necessary
		ensureCapacity();

		// create the new element
		TableElement<K, V> element = new TableElement<K, V>(key, value);

		// get the hash value for the specified key
		int hash = hashValue(key);

		// use a simple linear probing
		boolean updated = false;
		while (dictionary[hash] != null) {
			// checks first to see if element is marked for removal
			if (dictionary[hash].removed()) {
				break;
			}
			// checks next to see if element should be updated
			else if (dictionary[hash].getKey().equals(key)) {
				dictionary[hash].setValue(value);
				updated = true;
				break;
			}
			// all other cases
			else {
				hash = (hash + getProbe()) % currentCapacity;
			}
		}
		
		// expected operation to add new element
		if (!updated) {
			numberOfElements++;
			dictionary[hash] = element;
		}
		
		return value;
	}

	public V get(K key) {
		int hash = hashValue(key);
		return getHelper(key, hash, 0);
	}

	private V getHelper(K key, int hash, int count) {
		if (count > currentCapacity) {
			return null;
		}
		// TODO - break recursion in case marked for removal
		else if (dictionary[hash] != null && dictionary[hash].getKey().equals(key) && dictionary[hash].removed() != true) {
			return dictionary[hash].getValue();
		} else {
			hash = (hash + getProbe()) % currentCapacity;
			return getHelper(key, hash, count + 1);
		}
	}

	public boolean contains(K key) {
		int hash = hashValue(key);
		return containsHelper(key, hash, 0);
	}
	
	private boolean containsHelper (K key, int hash, int count) {
		if (count > currentCapacity) {
			return false;
		}
		// TODO - break recursion in case marked for removal
		else if (dictionary[hash] != null && dictionary[hash].getKey().equals(key) && dictionary[hash].removed() != true) {
			return true;
		} else {
			hash = (hash + getProbe()) % currentCapacity;
			return containsHelper(key, hash, count + 1);
		}
	}

	public V remove(K key) {
		if (numberOfElements < 1 || contains(key) != true) {
			return null;
		} else {
			int hash = hashValue(key);
			return removeHelper(key, hash, 0);
		}
	}
	
	private V removeHelper(K key, int hash, int count) {
		if (count > currentCapacity) {
			return null;
		} else if (dictionary[hash] != null && dictionary[hash].getKey().equals(key) && dictionary[hash].removed() != true) {
			dictionary[hash].markForRemoval();
			return dictionary[hash].getValue();
		} else {
			hash = (hash + getProbe()) % currentCapacity;
			return removeHelper(key, hash, count + 1);
		}
	}
	
	

	public int size() {
		return numberOfElements;
	}

	/**
	 * returns the next prime number that is least 2 larger than the current prime
	 * number.
	 */
	private int getNextPrime(int currentPrime) {
		// first we double the size of the current prime + 1
		currentPrime *= 2;
		currentPrime += 1;

		while (!isPrime(currentPrime))
			currentPrime++;

		return currentPrime;
	}

	/**
	 * Helper method that tests if an integer value is prime.
	 * 
	 * @param candidate
	 * @return True if candidate is prime, false otherwise.
	 */
	private boolean isPrime(int candidate) {
		boolean isPrime = true;

		// numbers <= 1 or even are not prime
		if ((candidate <= 1))
			isPrime = false;
		// 2 or 3 are prime
		else if ((candidate == 2) || (candidate == 3))
			isPrime = true;
		// even numbers are not prime
		else if ((candidate % 2) == 0)
			isPrime = false;
		// an odd integer >= 5 is prime if not evenly divisible
		// by every odd integer up to its square root
		// Source: Carrano.
		else {
			for (int i = 3; i <= Math.sqrt(candidate) + 1; i += 2)
				if (candidate % i == 0) {
					isPrime = false;
					break;
				}
		}

		return isPrime;
	}

	/**
	 * re-hash the elements in the dictionary
	 */
	@SuppressWarnings({ "unchecked" })
	private void rehash() {		
		currentCapacity= getNextPrime(currentCapacity);
		TableElement<K, V>[] newD = (TableElement<K, V>[]) new TableElement[currentCapacity];
		
		int hash = 0;
		boolean updated = false;
		K key;
		V value;
		TableElement<K, V> element;
		for (int i = 0; i < dictionary.length; ++i) {
			updated = false;
			if (dictionary[i] != null) {
				key = dictionary[i].getKey();
				value = dictionary[i].getValue();
				element = new TableElement<K, V>(key, value);
				hash = hashValue(key);
				
				while (newD[hash] != null) {
					if (newD[hash].getKey().equals(key)) {
						newD[hash].setValue(value);
						updated = true;
						break;
					} else {
						hash = (hash + getProbe()) % currentCapacity;
					}
				}
				
				if (!updated) {
					newD[hash] = element;
				}
			}
		}
		
		dictionary = newD;
		return;
	}
	
	/**
	 * Return the current load factor
	 * 
	 * @return
	 */
	private double getLoadFactor() {
		return numberOfElements / (double) currentCapacity;
	}

	/**
	 * Ensure there is capacity to perform an addition
	 */
	private void ensureCapacity() {
		double loadFactor = getLoadFactor();

		if (loadFactor >= CAPACITY_THRESHOLD)
			rehash();
	}

	public Set<K> keySet() {
		ArraySet<K> newSet = new ArraySet<K>();
		
		for (int i = 0; i < dictionary.length; ++i) {
			if (dictionary[i] != null && dictionary[i].removed != true) {
				newSet.add(dictionary[i].getKey());
			}
		}
		
		return newSet;
	}

	public Set<V> valueSet() {
		ArraySet<V> newSet = new ArraySet<V>();
		
		for (int i = 0; i < dictionary.length; ++i) {
			if (dictionary[i] != null && dictionary[i].removed != true) {
				newSet.add(dictionary[i].getValue());
			}
		}
		
		return newSet;
	}
	
	private int getProbe() {
		return 1;
	}
	
}
