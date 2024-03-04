package com.example.dsa;

/**
 * An array queue data structure (FIFO).
 *
 * @param <T> data type
 */
@SuppressWarnings("unchecked")
public class MyArrayQueue<T> implements MyQueue<T> {
    /**
     * Internal array used by this array queue.
     */
    private T[] array;

    /**
     * Number of elements in internal array.
     */
    private int size;

    /**
     * Default capacity for an array queue.
     */
    private static final int DEFAULT_CAPACITY = 16;

    /**
     * Size at which the internal array should be shrunk if it is too sparse.
     */
    private final int minArrLenThreshold = 100;

    /**
     * Constructs an array queue instance with a default initial capacity of 16.
     */
    public MyArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs an array queue instance with a specified initial capacity.
     *
     * @param initialCapacity initial capacity of this array queue
     * @throws IllegalArgumentException if initialCapacity is negative
     */
    public MyArrayQueue(int initialCapacity) throws IllegalArgumentException {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Negative capacity provided");
        }
        array = (T[]) new Object[initialCapacity];
        size = 0;
    }

    @Override
    public void clear() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (!(object instanceof MyArrayQueue<?> obj)) {
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

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void offer(T element) {
        if (element != null) {
            checkCapacity();
            array[size] = element;
            ++size;
        }
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return array[0];
    }

    @Override
    public T poll() {
        if (isEmpty()) {
            return null;
        }
        T element = array[0];
        --size;
        System.arraycopy(array, 1, array, 0, size);
        checkCapacity();
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object[] toArray() {
        Object[] arrayCopy = new Object[size];
        System.arraycopy(array, 0, arrayCopy, 0, size);
        return arrayCopy;
    }

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
     * in offer and poll methods.
     */
    private void checkCapacity() {
        if (array.length == 0) {
            array = (T[]) new Object[DEFAULT_CAPACITY];
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
