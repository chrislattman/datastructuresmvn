package com.datastructures.dsa;

/**
 * A singly linked list data structure.
 *
 * @param <T> data type
 */
public class MyLinkedList<T> implements MyList<T> {
    /**
     * Internal head node used by this linked list.
     */
    private Node head;

    /**
     * Number of connected nodes in this linked list.
     */
    private int size;

    /**
     * Default constructor for this linked list.
     */
    public MyLinkedList() {
        clear();
    }

    @Override
    public void add(int index, T element) throws IndexOutOfBoundsException {
        checkIndex(index, size + 1);
        if (isEmpty()) {
            head = new Node();
            head.data = element;
        } else {
            Node newNode = new Node();
            newNode.data = element;
            if (index == 0) {
                newNode.next = head;
                head = newNode;
            } else {
                Node current = head;
                int stop = index - 1;
                for (int i = 0; i < stop; i++) {
                    current = current.next;
                }
                newNode.next = current.next;
                current.next = newNode;
            }
        }
        ++size;
    }

    @Override
    public void add(T element) {
        add(size, element);
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public boolean contains(T element) {
        for (Node current = head; current != null; current = current.next) {
            if (element.equals(current.data)) {
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
        if (!(object instanceof MyLinkedList<?> obj)) {
            return false;
        }
        if (obj.size() != size) {
            return false;
        }
        Node current = head;
        for (int i = 0; i < size; i++) {
            if (!obj.get(i).equals(current.data)) {
                return false;
            }
            current = current.next;
        }
        return true;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        checkIndex(index, size);
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public int indexOf(T element) {
        Node current = head;
        for (int i = 0; i < size; i++) {
            if (element.equals(current.data)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int lastIndexOf(T element) {
        int index = -1;
        Node current = head;
        for (int i = 0; i < size; i++) {
            if (element.equals(current.data)) {
                index = i;
            }
            current = current.next;
        }
        return index;
    }

    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        checkIndex(index, size);
        if (index == 0) {
            T oldValue = head.data;
            head = head.next;
            --size;
            return oldValue;
        }
        Node current = head;
        int stop = index - 1;
        for (int i = 0; i < stop; i++) {
            current = current.next;
        }
        T oldValue = current.next.data;
        current.next = current.next.next;
        --size;
        return oldValue;
    }

    @Override
    public boolean removeElement(T element) {
        if (element == head.data) {
            head = head.next;
            --size;
            return true;
        }
        for (Node current = head; current != null; current = current.next) {
            Node nextNode = current.next;
            if (nextNode != null && element.equals(nextNode.data)) {
                current.next = nextNode.next;
                --size;
                return true;
            }
        }
        return false;
    }

    @Override
    public T set(int index, T element) throws IndexOutOfBoundsException {
        checkIndex(index, size);
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        T oldValue = current.data;
        current.data = element;
        return oldValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node current = head;
        for (int i = 0; i < size; i++) {
            array[i] = current.data;
            current = current.next;
        }
        return array;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for (Node current = head; current != null; current = current.next) {
            builder.append(current.data);
            if (current.next != null) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
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

    /**
     * Internal node object used by this linked list.
     */
    private class Node {
        /**
         * Data stored in this node.
         */
        private T data = null;

        /**
         * Node following this node.
         */
        private Node next = null;
    }
}
