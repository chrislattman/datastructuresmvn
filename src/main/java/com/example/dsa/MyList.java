package com.example.dsa;

/**
 * Interface which defines methods for lists.
 *
 * @param <T> data type
 */
public interface MyList<T> {
    /**
     * Inserts an element at the specified index.
     *
     * @param index index to add element
     * @param element element to add
     * @throws IndexOutOfBoundsException if index is out of bounds (index must
     * be between 0 and size(), inclusive)
     */
    void add(int index, T element) throws IndexOutOfBoundsException;

    /**
     * Inserts an element at the end of this list.
     *
     * @param element element to add
     */
    void add(T element);

    /**
     * Empties this list of all elements.
     */
    void clear();

    /**
     * Checks if an element is in this list.
     *
     * @param element element to check for
     * @return true if found, false otherwise
     */
    boolean contains(T element);

    /**
     * Compares an object with this list for equality.
     *
     * @param object object to compare to this list
     * @return true if object and this list are equal
     */
    @Override
    boolean equals(Object object);

    /**
     * Retrieves, but does not remove, an element from this list at the
     * specified index.
     *
     * @param index index to retrieve element from
     * @return element found
     * @throws IndexOutOfBoundsException if index is out of bounds (index must
     * be between 0 and size() - 1, inclusive)
     */
    T get(int index) throws IndexOutOfBoundsException;

    /**
     * Returns the Object class-generated hash code of this list.
     *
     * @return hash code of this list
     */
    @Override
    int hashCode();

    /**
     * Returns the index of the first occurrence of an element found in this
     * list.
     *
     * @param element element to search for
     * @return index of the first occurrence of element, or -1 if not found
     */
    int indexOf(T element);

    /**
     * Checks if this list has no elements.
     *
     * @return true if this list is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Returns the index of the last occurrence of an element found in this
     * list.
     *
     * @param element element to search for
     * @return index of the last occurrence of element, or -1 if not found
     */
    int lastIndexOf(T element);

    /**
     * Retrieves and removes an element from this list at a specified index.
     *
     * @param index index to remove element from
     * @return element found at index
     * @throws IndexOutOfBoundsException if index is out of bounds (index must
     * be between 0 and size() - 1, inclusive)
     */
    T remove(int index) throws IndexOutOfBoundsException;

    /**
     * Removes the first occurrence of an element from this list.
     *
     * @param element element to remove first occurrence of
     * @return true if successful, false otherwise
     */
    boolean removeElement(T element);

    /**
     * Sets the existing element at a specified index to a new value.
     *
     * @param index index to set element at
     * @param element new value to set existing element to
     * @return old value of the element at position index
     * @throws IndexOutOfBoundsException if index is out of bounds (index must
     * be between 0 and size() - 1, inclusive)
     */
    T set(int index, T element) throws IndexOutOfBoundsException;

    /**
     * Returns the number of elements in this list.
     *
     * @return size of list
     */
    int size();

    /**
     * Returns an array containing all the elements in this list.
     *
     * @return array of list elements
     */
    Object[] toArray();

    /**
     * Returns a string representation of this list, e.g.
     * "[element1, element2, element3, ..., elementN]".
     *
     * @return string form of this list
     */
    @Override
    String toString();
}
