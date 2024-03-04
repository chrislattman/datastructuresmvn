package com.example.dsa;

/**
 * Interface which defines methods for queues.
 *
 * @param <T> data type
 */
public interface MyQueue<T> {
    /**
     * Empties this queue of all elements.
     */
     void clear();

    /**
     * Compares an object with this queue for equality.
     *
     * @param object object to compare to this queue
     * @return true if object and this queue are equal
     */
    @Override
     boolean equals(Object object);

    /**
     * Returns the Object class-generated hash code of this queue.
     *
     * @return hash code of this queue
     */
    @Override
     int hashCode();

    /**
     * Checks if this queue has no elements.
     *
     * @return true if this queue is empty, false otherwise
     */
     boolean isEmpty();

    /**
     * Inserts an element to this queue. Null elements are not permitted.
     *
     * @param element element to add
     */
     void offer(T element);

    /**
     * Retrieves, but does not remove, the element at the front of this queue.
     *
     * @return element at the front of this queue, or null if queue is empty
     */
     T peek();

    /**
     * Retrieves and removes the element at the front of this queue.
     *
     * @return element at the front of this queue, or null if queue is empty
     */
     T poll();

    /**
     * Returns the number of elements in this queue.
     *
     * @return size of queue
     */
     int size();

    /**
     * Returns an array containing all the elements in this queue.
     *
     * @return array of queue elements
     */
     Object[] toArray();

    /**
     * Returns a string representation of this queue, e.g.
     * "[element1, element2, element3, ..., elementN]".
     *
     * @return string form of this queue
     */
    @Override
     String toString();
}
