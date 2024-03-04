package com.datastructures.dsa;

/**
 * A stack data structure (LIFO).
 *
 * @param <T> data type
 */
@SuppressWarnings("unchecked")
public class MyStack<T> {
    /**
     * Internal array used by this stack.
     */
    private T[] array;

    /**
     * Number of elements in internal array.
     */
    private int size;

    /**
     * Default capacity for a stack.
     */
    private final int defaultCapacity = 10;

    /**
     * Size at which the internal array should be shrunk if it is too sparse.
     */
    private final int minArrLenThreshold = 100;

    /**
     * Default constructor for this stack.
     */
    public MyStack() {
        clear();
    }

    /**
     * Empties this stack of all elements.
     */
    public void clear() {
        array = (T[]) new Object[defaultCapacity];
        size = 0;
    }

    /**
     * Compares an object with this stack for equality.
     *
     * @param object object to compare to this stack
     * @return true if object and this stack are equal
     */
    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (!(object instanceof MyStack<?> obj)) {
            return false;
        }
        if (obj.size() != size) {
            return false;
        }
        T[] elements = (T[]) obj.toArray();
        for (int i = 0; i < size; i++) {
            if (!elements[i].equals(array[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the Object class-generated hash code of this stack.
     *
     * @return hash code of this stack
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Checks if this stack has no elements.
     *
     * @return true if this stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Retrieves, but does not remove, the element at the top of this stack.
     *
     * @return topmost element on stack, or null if stack is empty
     */
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return array[size - 1];
    }

    /**
     * Retrieves and removes the element at the top of this stack.
     *
     * @return topmost element on stack, or null if stack is empty
     */
    public T pop() {
        if (isEmpty()) {
            return null;
        }
        T element = array[size - 1];
        --size;
        checkCapacity();
        return element;
    }

    /**
     * Inserts an element at the top of this stack.
     *
     * @param element element to add
     */
    public void push(T element) {
        checkCapacity();
        array[size] = element;
        ++size;
    }

    /**
     * Returns the number of elements in this stack.
     *
     * @return size of stack
     */
    public int size() {
        return size;
    }

    /**
     * Returns an array containing all the elements in this stack.
     *
     * @return array of stack elements
     */
    public Object[] toArray() {
        Object[] arrayCopy = new Object[size];
        System.arraycopy(array, 0, arrayCopy, 0, size);
        return arrayCopy;
    }

    /**
     * Returns a string representation of this stack, e.g.
     * "[element1, element2, element3, ..., elementN]".
     *
     * @return string form of this stack
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        int lastIndex = size - 1;
        for (int i = 0; i < size; i++) {
            builder.append(array[i]);
            if (i != lastIndex) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }

    /**
     * Doubles or halves the size of the internal array depending on size. Used
     * in pop and push methods.
     */
    private void checkCapacity() {
        if (array.length == 0) {
            array = (T[]) new Object[defaultCapacity];
        } else if (size == array.length) {
            T[] newArray = (T[]) new Object[size * 2];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        } else if (array.length > minArrLenThreshold
                   && size * 2 < array.length) {
            T[] newArray = (T[]) new Object[size / 2];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }
}
