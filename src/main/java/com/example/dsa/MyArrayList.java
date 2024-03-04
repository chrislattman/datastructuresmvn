package com.example.dsa;

/**
 * An array list data structure.
 *
 * @param <T> data type
 */
@SuppressWarnings("unchecked")
public class MyArrayList<T> implements MyList<T> {
    /**
     * Internal array used by this array list.
     */
    private T[] array;

    /**
     * Number of elements in internal array.
     */
    private int size;

    /**
     * Default capacity for an array list.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Size at which the internal array should be shrunk if it is too sparse.
     */
    private final int minArrLenThreshold = 100;

    /**
     * Constructs an array list instance with a default initial capacity of 10.
     */
    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs an array list instance with a specified initial capacity.
     *
     * @param initialCapacity initial capacity of this array list
     * @throws IllegalArgumentException if initialCapacity is negative
     */
    public MyArrayList(int initialCapacity) throws IllegalArgumentException {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Negative capacity provided");
        }
        array = (T[]) new Object[initialCapacity];
        size = 0;
    }

    @Override
    public void add(int index, T element) throws IndexOutOfBoundsException {
        checkIndex(index, size + 1);
        checkCapacity();
        if (index < size) {
            System.arraycopy(array, index, array, index + 1, size - index);
        }
        array[index] = element;
        ++size;
    }

    @Override
    public void add(T element) {
        add(size, element);
    }

    @Override
    public void clear() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(array[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (!(object instanceof MyArrayList<?> obj)) {
            return false;
        }
        if (obj.size() != size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!obj.get(i).equals(array[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        checkIndex(index, size);
        return array[index];
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int lastIndexOf(T element) {
        for (int i = size - 1; i >= 0; i--) {
            if (element.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        checkIndex(index, size);
        T element = array[index];
        --size;
        System.arraycopy(array, index + 1, array, index, size - index);
        checkCapacity();
        return element;
    }

    @Override
    public boolean removeElement(T element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(array[i])) {
                --size;
                System.arraycopy(array, i + 1, array, i, size - i);
                checkCapacity();
                return true;
            }
        }
        return false;
    }

    @Override
    public T set(int index, T element) throws IndexOutOfBoundsException {
        checkIndex(index, size);
        T oldValue = array[index];
        array[index] = element;
        return oldValue;
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
     * in add and remove methods.
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

    /**
     * Helper function for index validation.
     *
     * @param index index to validate
     * @param upperBound value that index must be strictly less than
     * @throws IndexOutOfBoundsException if index is out of bounds
     */
    private void checkIndex(int index, int upperBound)
        throws IndexOutOfBoundsException {
        if (index < 0 || index >= upperBound) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
    }
}
