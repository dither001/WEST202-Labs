/** 
 * An interface for a bag ADT.
 *
 * A bag is an unordered collection of elements 
 * that may contain duplicates.
 *
 * @author Greg Gagne February 2017.
 */

public interface Bag<T>
{
    /** 
     * Adds a new element to the bag.
     */
    public void add(T element);

    /**
     * Adds an array of elements to the bag.
     */
    public void addAll(T[] elements);

    /**
     * Determines whether a bag contains a specified element.
     *
     * Returns true if bag contains element, false otherwise.
     */
    public boolean contains(T element);

    /**
     * Returns the number of elements in the bag.
     */
    public int getSize();

    /**
     * Returns the capacity of the bag.
     */
    public int getCapacity();

    /**
     * Removes the specified element from the bag.
     */
    public void remove(T element);
    
    /**
     * Provides an iteration of the elements in the bag.
     * The elements are returned in no particular order.
     */
    public java.util.Iterator<T> iterator();
}
