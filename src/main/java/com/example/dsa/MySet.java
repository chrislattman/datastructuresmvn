package com.example.dsa;

/**
 * Interface which defines methods for sets.
 *
 * @param <T> data type
 */
public interface MySet<T> {
    /**
     * Inserts an element into this set if it is not already there.
     *
     * @param element element to add
     * @return true if element was not present, false otherwise
     */
     boolean add(T element);

    /**
     * Empties this set of all elements.
     */
     void clear();

    /**
     * Checks if an element is in this set.
     *
     * @param element element to check for
     * @return true if found, false otherwise
     */
     boolean contains(Object element);

    /**
     * Compares an object with this set for equality.
     *
     * @param object object to compare to this set
     * @return true if object and this set are equal
     */
    @Override
     boolean equals(Object object);

    /**
     * Returns the Object class-generated hash code of this set.
     *
     * @return hash code of this set
     */
    @Override
     int hashCode();

    /**
     * Checks if this set has no elements.
     *
     * @return true if this set is empty, false otherwise
     */
     boolean isEmpty();

    /**
     * Removes an element from this set.
     *
     * @param element element to remove
     * @return true if element found, false otherwise
     */
     boolean remove(Object element);

    /**
     * Returns the number of elements in this set.
     *
     * @return size of set
     */
     int size();

    /**
     * Returns an array containing all the elements in this set.
     *
     * @return array of set elements
     */
     Object[] toArray();

    /**
     * Returns a string representation of this set, e.g.
     * "[element1, element2, element3, ..., elementN]".
     *
     * @return string form of this set
     */
    @Override
     String toString();
}
