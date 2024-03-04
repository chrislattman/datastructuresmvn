package com.example.dsa;

/**
 * A tree set data structure built upon a tree map.
 *
 * @param <T> data type which much implement Comparable
 */
public class MyTreeSet<T extends Comparable<? super T>> implements MySet<T> {
    /**
     * Internal tree map used by this tree set.
     */
    private final MyTreeMap<T, Object> map;

    /**
     * Default constructor for this tree set.
     */
    public MyTreeSet() {
        map = new MyTreeMap<>();
    }

    @Override
    public boolean add(T element) {
        int currentSize = map.size();
        map.putIfAbsent(element, new Object());
        int newSize = map.size();
        return currentSize != newSize;
    }

    /**
     * Returns the least element greater than or equal to the given element, or
     * null if no such element exists.
     *
     * @param element element to reference
     * @return ceiling element
     */
    public T ceiling(T element) {
        return map.ceilingKey(element);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean contains(Object element) {
        return map.containsKey(element);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (!(object instanceof MyHashSet<?> obj)) {
            return false;
        }
        if (obj.size() != size()) {
            return false;
        }
        T[] elements = (T[]) obj.toArray();
        T[] array = (T[]) toArray();
        for (int i = 0; i < array.length; i++) {
            if (!elements[i].equals(array[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the smallest element.
     *
     * @return first element
     */
    public T first() {
        return map.firstKey();
    }

    /**
     * Returns the greatest element less than or equal to the given element, or
     * null if no such element exists.
     *
     * @param element element to reference
     * @return floor element
     */
    public T floor(T element) {
        return map.floorKey(element);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Returns the least element strictly greater than the given element, or
     * null if no such element exists.
     *
     * @param element element to reference
     * @return next greater element
     */
    public T higher(T element) {
        return map.higherKey(element);
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    /**
     * Returns the largest element.
     *
     * @return last element
     */
    public T last() {
        return map.lastKey();
    }

    /**
     * Returns the greatest element strictly less than the given element, or
     * null if no such element exists.
     *
     * @param element element to reference
     * @return greatest prior element
     */
    public T lower(T element) {
        return map.lowerKey(element);
    }

    @Override
    public boolean remove(Object element) {
        return map.remove(element) != null;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public Object[] toArray() {
        MyArrayList<T> keys = map.keyList();
        Object[] array = new Object[map.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = keys.get(i);
        }
        return array;
    }

    @Override
    public String toString() {
        MyArrayList<T> keys = map.keyList();
        StringBuilder builder = new StringBuilder("[");
        int length = map.size();
        int lastIndex = length - 1;
        for (int i = 0; i < length; i++) {
            builder.append(keys.get(i));
            if (i != lastIndex) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
