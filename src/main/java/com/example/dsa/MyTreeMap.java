package com.example.dsa;

/**
 * A tree map data structure based on an AVL tree.
 *
 * @param <K> key data type which much implement Comparable
 * @param <V> value data type
 */
public class MyTreeMap<K extends Comparable<? super K>, V>
    implements MyMap<K, V> {
    /**
     * Internal root node used by this tree map.
     */
    private Node root;

    /**
     * Number of connected nodes in this tree map.
     */
    private int size;

    /**
     * The previous value associated with a key for an insertion or removal
     * operation.
     */
    private V previousValue;

    /**
     * Default constructor for this tree map.
     */
    public MyTreeMap() {
        clear();
    }

    /**
     * Returns the least key greater than or equal to the given key, or null if
     * no such key exists.
     *
     * @param key key to reference
     * @return ceiling key
     */
    public K ceilingKey(K key) {
        // lazy implementation
        if (key != null) {
            MyArrayList<K> keys = keyList();
            for (int i = 0; i < size; i++) {
                if (keys.get(i).compareTo(key) >= 0) {
                    return keys.get(i);
                }
            }
        }
        return null;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
        previousValue = null;
    }

    @Override
    public boolean containsKey(Object key) {
        return getOrDefault(key, null) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        return findValue(root, value);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (!(object instanceof MyTreeMap<?, ?> obj)) {
            return false;
        }
        if (obj.size() != size) {
            return false;
        }
        MyArrayList<?> keys = obj.keyList();
        MyArrayList<?> values = obj.values();
        return keys.equals(keyList()) && values.equals(values());
    }

    /**
     * Returns the smallest key.
     *
     * @return first key
     */
    public K firstKey() {
        K key = null;
        Node current = root;
        while (current.left != null) {
            key = current.key;
            current = current.left;
        }
        return key;
    }

    /**
     * Returns the greatest key less than or equal to the given key, or null if
     * no such key exists.
     *
     * @param key key to reference
     * @return floor key
     */
    public K floorKey(K key) {
        // lazy implementation
        if (key != null) {
            MyArrayList<K> keys = keyList();
            for (int i = size - 1; i >= 0; i--) {
                if (keys.get(i).compareTo(key) <= 0) {
                    return keys.get(i);
                }
            }
        }
        return null;
    }

    @Override
    public V get(Object key) {
        return getOrDefault(key, null);
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        V value = findKey(root, key);
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Returns the least key strictly greater than the given key, or null if no
     * such key exists.
     *
     * @param key key to reference
     * @return next greater key
     */
    public K higherKey(K key) {
        // lazy implementation
        if (key != null) {
            MyArrayList<K> keys = keyList();
            for (int i = 0; i < size; i++) {
                if (keys.get(i).compareTo(key) > 0) {
                    return keys.get(i);
                }
            }
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public MyArrayList<K> keyList() {
        MyArrayList<K> keys = new MyArrayList<>();
        collectKeys(root, keys);
        return keys;
    }

    /**
     * Returns the largest key.
     *
     * @return last key
     */
    public K lastKey() {
        K key = null;
        Node current = root;
        while (current.right != null) {
            key = current.key;
            current = current.right;
        }
        return key;
    }

    /**
     * Returns the greatest key strictly less than the given key, or null if no
     * such key exists.
     *
     * @param key key to reference
     * @return greatest prior key
     */
    public K lowerKey(K key) {
        // lazy implementation
        if (key != null) {
            MyArrayList<K> keys = keyList();
            for (int i = size - 1; i >= 0; i--) {
                if (keys.get(i).compareTo(key) < 0) {
                    return keys.get(i);
                }
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        if (key != null && value != null) {
            previousValue = null;
            root = insert(root, key, value, null, false, false);
            return previousValue;
        }
        return null;
    }

    @Override
    public V putIfAbsent(K key, V value) {
        if (key != null && value != null) {
            previousValue = null;
            root = insert(root, key, value, null, true, false);
            return previousValue;
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public V remove(Object key) {
        if (key != null) {
            previousValue = null;
            root = delete(root, (K) key, null);
            return previousValue;
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object key, Object value) {
        if (key != null && value != null) {
            previousValue = null;
            root = delete(root, (K) key, (V) value);
            return previousValue != null;
        }
        return false;
    }

    @Override
    public V replace(K key, V value) {
        if (key != null && value != null) {
            previousValue = null;
            root = insert(root, key, value, null, false, true);
            return previousValue;
        }
        return null;
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        if (key != null && newValue != null) {
            previousValue = null;
            root = insert(root, key, newValue, oldValue, false, true);
            return previousValue != null;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{");
        MyArrayList<K> keys = keyList();
        MyArrayList<V> values = values();
        int lastIndex = size - 1;
        for (int i = 0; i < size; i++) {
            builder.append(keys.get(i));
            builder.append("=");
            builder.append(values.get(i));
            if (i != lastIndex) {
                builder.append(", ");
            }
        }
        builder.append("}");
        return builder.toString();
    }

    @Override
    public MyArrayList<V> values() {
        MyArrayList<V> values = new MyArrayList<>();
        collectValues(root, values);
        return values;
    }

    /**
     * Performs an in-order traversal of the nodes in this tree map and stores
     * the found keys in a list.
     *
     * @param node current node
     * @param list list to add keys to
     */
    private void collectKeys(Node node, MyArrayList<K> list) {
        if (node != null) {
            collectKeys(node.left, list);
            list.add(node.key);
            collectKeys(node.right, list);
        }
    }

    /**
     * Performs an in-order traversal of the nodes in this tree map and stores
     * the found values in a list.
     *
     * @param node current node
     * @param list list to add values to
     */
    private void collectValues(Node node, MyArrayList<V> list) {
        if (node != null) {
            collectValues(node.left, list);
            list.add(node.value);
            collectValues(node.right, list);
        }
    }

    /**
     * Helper method for getOrDefault.
     *
     * @param node current node
     * @param key key to search for
     * @return value associated with key, or null if key was not found
     */
    @SuppressWarnings("unchecked")
    private V findKey(Node node, Object key) {
        if (node == null || key == null) {
            return null;
        }

        if (node.key.equals(key)) {
            return node.value;
        } else if (node.key.compareTo((K) key) > 0) {
            return findKey(node.left, key);
        } else {
            return findKey(node.right, key);
        }
    }

    /**
     * Helper method for containsValue.
     *
     * @param node current node
     * @param value value to search for
     * @return true if value was found, false otherwise
     */
    private boolean findValue(Node node, Object value) {
        if (node == null || value == null) {
            return false;
        }
        if (node.value.equals(value)) {
            return true;
        }
        return findValue(node.left, value) || findValue(node.right, value);
    }

    /**
     * Internal function used to add or modify a key-value pair in this map.
     *
     * @param node current node
     * @param key key to add
     * @param newValue value to be associated with key
     * @param oldValue current value to check for (existing key only), leave as
     * null if not applicable
     * @param addOnlyIfAbsent if false, replace current value with specified new
     * value
     * @param addOnlyIfKeyExists if true, only replace value if key already
     * exists
     * @return root node
     */
    private Node insert(Node node, K key, V newValue, V oldValue,
        boolean addOnlyIfAbsent, boolean addOnlyIfKeyExists) {
        if (node == null && !addOnlyIfKeyExists) {
            ++size;
            node = new Node();
            node.key = key;
            node.value = newValue;
            return node;
        } else if (node == null) {
            return null;
        }

        if (key.equals(node.key)) {
            previousValue = node.value;
            if ((oldValue == null || oldValue.equals(previousValue))
                && !addOnlyIfAbsent) {
                node.value = newValue;
            } else if (oldValue != null) {
                previousValue = null;
            }
            return node;
        } else if (key.compareTo(node.key) < 0) {
            node.left = insert(node.left, key, newValue, oldValue,
                addOnlyIfAbsent, addOnlyIfKeyExists);
        } else {
            node.right = insert(node.right, key, newValue, oldValue,
                addOnlyIfAbsent, addOnlyIfKeyExists);
        }

        node.balanceFactor = height(node.left) - height(node.right);
        if (Math.abs(node.balanceFactor) > 1) {
            node = balanceSubtree(node);
        }
        return node;
    }

    /**
     * Internal function used to remove a key-value pair in this map.
     *
     * @param node current node
     * @param key key to remove
     * @param value current value to check for, leave as null if not applicable
     * @return root node
     */
    private Node delete(Node node, K key, V value) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) < 0) {
            node.left = delete(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = delete(node.right, key, value);
        } else if (key.equals(node.key)
                   && (value == null || value.equals(node.value))) {
            if (node.left == null) {
                previousValue = node.value;
                --size;
                return node.right;
            } else if (node.right == null) {
                previousValue = node.value;
                --size;
                return node.left;
            } else {
                Node successor = node.right;
                while (successor.left != null) {
                    successor = successor.left;
                }
                node.key = successor.key;
                node.value = successor.value;
                node.right = delete(node.right, successor.key, successor.value);
            }
        } else {
            return node;
        }

        node.balanceFactor = height(node.left) - height(node.right);
        if (Math.abs(node.balanceFactor) > 1) {
            node = balanceSubtree(node);
        }
        return node;
    }

    /**
     * Returns the height of a subtree rooted at the specified node.
     *
     * @param node root of subtree to get height of
     * @return height of subtree
     */
    private int height(Node node) {
        if (node == null) {
            return -1;
        }
        if (node.left == null && node.right == null) {
            return 0;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    /**
     * Balances the subtree of a given node. There are 4 types of rotations:
     * left rotation, right rotation, left-right rotation, and right-left
     * rotation.
     *
     * @param node root of subtree to balance
     * @return possibly modified root node of subtree
     */
    private Node balanceSubtree(Node node) {
        if (node.balanceFactor > 1) {
            if (node.left.balanceFactor <= -1) {
                // if this is called, then it becomes left-right
                node.left = leftRotation(node.left);
            }
            node = rightRotation(node);
        } else if (node.balanceFactor < -1) {
            if (node.right.balanceFactor >= 1) {
                // if this is called, then it becomes right-left
                node.right = rightRotation(node.right);
            }
            node = leftRotation(node);
        }
        return node;
    }

    /**
     * Performs a left rotation of the subtree rooted at the specified node.
     *
     * @param node root of subtree to rotate
     * @return modified root node of subtree
     */
    private Node leftRotation(Node node) {
        Node newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        return newRoot;
    }

    /**
     * Performs a right rotation of the subtree rooted at the specified node.
     *
     * @param node root of subtree to rotate
     * @return modified root node of subtree
     */
    private Node rightRotation(Node node) {
        Node newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        return newRoot;
    }

    /**
     * Internal node object used by this tree map.
     */
    private class Node {
        /**
         * Key for this node.
         */
        private K key = null;

        /**
         * Value associated with key.
         */
        private V value = null;

        /**
         * Left leaf/child node of this node.
         */
        private Node left = null;

        /**
         * Right leaf/child node of this node.
         */
        private Node right = null;

        /**
         * Difference in height between left subtree and right subtree of this
         * node.
         */
        private int balanceFactor = 0;
    }
}
