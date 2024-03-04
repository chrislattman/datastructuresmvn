package com.datastructures.dsa;

/**
 * A priority queue data structure. Also called a (binary) heap.
 *
 * @param <T> data type which much implement Comparable
 */
@SuppressWarnings("unchecked")
public class MyPriorityQueue<T extends Comparable<? super T>>
    implements MyQueue<T> {
    /**
     * Internal array used by this priority queue.
     */
    private T[] array;

    /**
     * Number of elements in internal array.
     */
    private int size;

    /**
     * Whether this priority queue is backed by a min heap or a max heap.
     */
    private final boolean isMinHeap;

    /**
     * Default capacity for a priority queue.
     */
    private static final int DEFAULT_CAPACITY = 11;

    /**
     * Size at which the internal array should be shrunk if it is too sparse.
     */
    private final int minArrLenThreshold = 100;

    /**
     * Constructs a min heap with a default initial capacity of 11.
     */
    public MyPriorityQueue() {
        this(DEFAULT_CAPACITY, true);
    }

    /**
     * Constructs a min heap with a specified initial capacity.
     *
     * @param initialCapacity initial capacity of this priority queue
     * @throws IllegalArgumentException if initialCapacity is negative
     */
    public MyPriorityQueue(int initialCapacity)
        throws IllegalArgumentException {
        this(initialCapacity, true);
    }

    /**
     * Constructs either a min heap or max heap with a default initial capacity
     * of 11.
     *
     * @param inputIsMinHeap if true, this priority queue will be a min heap;
     * otherwise, it will be a max heap
     */
    public MyPriorityQueue(boolean inputIsMinHeap) {
        this(DEFAULT_CAPACITY, inputIsMinHeap);
    }

    /**
     * Constructs either a min heap or a max heap with a specified initial
     * capacity.
     *
     * @param initialCapacity initial capacity of this priority queue
     * @param inputIsMinHeap if true, this priority queue will be a min heap;
     * otherwise, it will be a max heap
     * @throws IllegalArgumentException if initialCapacity is negative
     */
    public MyPriorityQueue(int initialCapacity, boolean inputIsMinHeap)
        throws IllegalArgumentException {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Negative capacity provided");
        }
        array = (T[]) new Comparable[initialCapacity];
        size = 0;
        this.isMinHeap = inputIsMinHeap;
    }

    @Override
    public void clear() {
        array = (T[]) new Comparable[DEFAULT_CAPACITY];
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
        if (!(object instanceof MyPriorityQueue<?> obj)) {
            return false;
        }
        if (obj.size() != size) {
            return false;
        }
        T[] items = (T[]) obj.toArray();
        for (int i = 0; i < size; i++) {
            if (!items[i].equals(array[i])) {
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
            int i;
            for (i = 0; i < array.length; i++) {
                if (array[i] == null) {
                    array[i] = element;
                    break;
                }
            }
            int parentIndex = i;
            while (parentIndex > 0) {
                if (parentIndex % 2 == 0) {
                    parentIndex--;
                }
                parentIndex /= 2;
                T parent = array[parentIndex];
                if (isMinHeap && element.compareTo(parent) < 0
                    || !isMinHeap && element.compareTo(parent) > 0) {
                    array[parentIndex] = element;
                    array[i] = parent;
                    i = parentIndex;
                } else {
                    break;
                }
            }
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
        T head = array[0];
        array[0] = array[size - 1];
        array[size - 1] = null;
        --size;
        checkCapacity();
        int currentIndex = 0;
        int bestIndex = getBestIndex(1);
        while (bestIndex > 0) {
            T current = array[currentIndex];
            T best = array[bestIndex];
            if (isMinHeap && current.compareTo(best) > 0
                || !isMinHeap && current.compareTo(best) < 0) {
                array[currentIndex] = best;
                array[bestIndex] = current;
                currentIndex = bestIndex;
                bestIndex = getBestIndex(currentIndex * 2 + 1);
            } else {
                break;
            }
        }
        return head;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object[] toArray() {
        Object[] arrayCopy = new Object[array.length];
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
            array = (T[]) new Comparable[DEFAULT_CAPACITY];
        } else if (size == array.length) {
            T[] newArray = (T[]) new Comparable[size * 2];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        } else if (array.length > minArrLenThreshold
                   && size * 2 < array.length) {
            T[] newArray = (T[]) new Comparable[size / 2];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    /**
     * Returns the index of the lesser element of the internal array if this is
     * a min heap. Otherwise, getBestIndex returns the index of the greater
     * element. Indices should be distinct and increasing.
     *
     * @param leftIndex left array index
     * @return the index of the desired element, or -1 if: leftIndex is out of
     * bounds, array[leftIndex] and array[leftIndex + 1] are both null, or
     * array[array.length - 1] is null; in a non-null tie, left element wins
     */
    private int getBestIndex(int leftIndex) {
        if (leftIndex >= array.length) {
            return -1;
        }

        T left = array[leftIndex];
        int rightIndex = leftIndex + 1;
        if (rightIndex < array.length) {
            T right = array[rightIndex];
            if (left == null && right == null) {
                return -1;
            } else if (left == null) {
                return rightIndex;
            } else if (right == null) {
                return leftIndex;
            } else if (isMinHeap && left.compareTo(right) < 0
                       || !isMinHeap && left.compareTo(right) > 0) {
                return leftIndex;
            } else if (left.compareTo(right) == 0) {
                return leftIndex;
            } else {
                return rightIndex;
            }
        } else if (left == null) {
            return -1;
        } else {
            return leftIndex;
        }
    }
}
