package com.example.dsa;

/**
 * A hash map data structure utilizing quadratic probing (open addressing). Also
 * called a hash table.
 *
 * @param <K> key data type
 * @param <V> value data type
 */
@SuppressWarnings("unchecked")
public class MyHashMap<K, V> implements MyMap<K, V> {
    /**
     * Internal key array used by this hash map.
     */
    private K[] keys;

    /**
     * Interval value array used by this hash map.
     */
    private V[] values;

    /**
     * Number of key-value pairs in hash map.
     */
    private int size;

    /**
     * Ratio at which hash map is rehashed.
     */
    private final float loadFactor;

    /**
     * Default capacity for a hash map.
     */
    private static final int DEFAULT_CAPACITY = 16;

    /**
     * Default load factor for a hash map.
     */
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * Constructs a hash map instance with a default initial capacity of 16 and
     * a default load factor of 0.75.
     */
    public MyHashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Constructs a hash map instance with the specified initial capacity and a
     * default load factor of 0.75.
     *
     * @param initialCapacity initial capacity of this hash map
     * @throws IllegalArgumentException if initialCapacity is negative
     */
    public MyHashMap(int initialCapacity) throws IllegalArgumentException {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Constructs a hash map instance with the specified initial capacity and
     * load factor.
     *
     * @param initialCapacity initial capacity of this hash map
     * @param inputLoadFactor maximum value of (number of key-value pairs /
     * number of total slots in hash map) before rehashing occurs
     * @throws IllegalArgumentException if initialCapacity is negative or
     * loadFactor is nonpositive
     */
    public MyHashMap(int initialCapacity, float inputLoadFactor)
        throws IllegalArgumentException {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Negative capacity provided");
        }
        if (inputLoadFactor <= 0 || Float.compare(Math.ulp(inputLoadFactor),
            Float.MIN_VALUE) == 0) {
            throw new IllegalArgumentException("Load factor is nonpositive");
        }
        keys = (K[]) new Object[initialCapacity];
        values = (V[]) new Object[initialCapacity];
        loadFactor = inputLoadFactor;
        size = 0;
    }

    @Override
    public void clear() {
        keys = (K[]) new Object[DEFAULT_CAPACITY];
        values = (V[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return getOrDefault(key, null) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        for (V v : values) {
            if (value.equals(v)) {
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
        if (!(object instanceof MyHashMap<?, ?> obj)) {
            return false;
        }
        if (obj.size() != size) {
            return false;
        }
        MyList<?> objKeys = obj.keyList();
        MyList<?> objValues = obj.values();
        return objKeys.equals(keyList()) && objValues.equals(values());
    }

    @Override
    public V get(Object key) {
        return getOrDefault(key, null);
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        if (key == null) {
            return null;
        }
        int mapLength = keys.length;
        int hash = key.hashCode();
        for (int i = 0; i < mapLength; i++) {
            int mapIndex = (hash + i * i) % mapLength;
            K currentKey = keys[mapIndex];
            if (key.equals(currentKey)) {
                return values[mapIndex];
            }
            if (currentKey == null && values[mapIndex] == null) {
                return defaultValue;
            }
        }
        return defaultValue;
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
    public MyList<K> keyList() {
        MyList<K> list = new MyArrayList<>();
        for (K key : keys) {
            if (key != null) {
                list.add(key);
            }
        }
        return list;
    }

    @Override
    public V put(K key, V value) {
        return insert(key, value, null, false, false);
    }

    @Override
    public V putIfAbsent(K key, V value) {
        return insert(key, value, null, true, false);
    }

    @Override
    public V remove(Object key) {
        if (key == null) {
            return null;
        }
        int mapLength = keys.length;
        int hash = key.hashCode();
        for (int i = 0; i < mapLength; i++) {
            int mapIndex = (hash + i * i) % mapLength;
            K currentKey = keys[mapIndex];
            if (key.equals(currentKey)) {
                keys[mapIndex] = null;
                --size;
                return values[mapIndex];
            }
            if (currentKey == null && values[mapIndex] == null) {
                return null;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Object key, Object value) {
        if (key == null || value == null) {
            return false;
        }
        int mapLength = keys.length;
        int hash = key.hashCode();
        for (int i = 0; i < mapLength; i++) {
            int mapIndex = (hash + i * i) % mapLength;
            K currentKey = keys[mapIndex];
            V currentValue = values[mapIndex];
            if (key.equals(currentKey) && value.equals(currentValue)) {
                keys[mapIndex] = null;
                --size;
                return true;
            }
            if (currentKey == null && currentValue == null) {
                return false;
            }
        }
        return false;
    }

    @Override
    public V replace(K key, V value) {
        return insert(key, value, null, false, true);
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        return insert(key, newValue, oldValue, false, true) != null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{");
        for (int i = 0; i < keys.length; i++) {
            K currentKey = keys[i];
            if (currentKey != null) {
                builder.append(currentKey);
                builder.append("=");
                builder.append(values[i]);
                builder.append(", ");
            }
        }
        builder.delete(builder.length() - 2, builder.length());
        builder.append("}");
        return builder.toString();
    }

    @Override
    public MyList<V> values() {
        MyList<V> list = new MyArrayList<>();
        for (V value : values) {
            if (value != null) {
                list.add(value);
            }
        }
        return list;
    }

    /**
     * Internal function used to add or modify a key-value pair in this map.
     *
     * @param key key to add
     * @param newValue value to be associated with key
     * @param oldValue current value to check for (existing key only), leave as
     * null if not applicable
     * @param addOnlyIfAbsent if false, replace current value with specified new
     * value
     * @param addOnlyIfKeyExists if true, only replace value if key already
     * exists
     * @return previous or current value associated with key, or null if either
     * key was not found, key is null, or newValue is null
     */
    private V insert(K key, V newValue, V oldValue, boolean addOnlyIfAbsent,
        boolean addOnlyIfKeyExists) {
        if (key == null || newValue == null) {
            return null;
        }
        int mapLength = keys.length;
        if (mapLength == 0) {
            clear();
        } else if ((float) size > loadFactor * mapLength) {
            resizeMap();
            return insert(key, newValue, oldValue, addOnlyIfAbsent,
                addOnlyIfKeyExists);
        }
        int hash = key.hashCode();
        for (int i = 0; i < mapLength; i++) {
            int mapIndex = (hash + i * i) % mapLength;
            K currentKey = keys[mapIndex];
            if (currentKey == null) {
                if (!addOnlyIfKeyExists) {
                    keys[mapIndex] = key;
                    values[mapIndex] = newValue;
                    ++size;
                }
                return null;
            }
            if (currentKey.equals(key)) {
                V previous = values[mapIndex];
                if ((oldValue == null || oldValue.equals(previous))
                    && !addOnlyIfAbsent) {
                    values[mapIndex] = newValue;
                } else if (oldValue != null) {
                    return null;
                }
                return previous;
            }
        }
        // Needed if loadFactor == 1
        resizeMap();
        return insert(key, newValue, oldValue, addOnlyIfAbsent,
            addOnlyIfKeyExists);
    }

    /**
     * Increases the map size and rehashes the key-value pairs when load factor
     * has been surpassed.
     */
    private void resizeMap() {
        K[] keysCopy = (K[]) new Object[keys.length];
        V[] valuesCopy = (V[]) new Object[values.length];
        System.arraycopy(keys, 0, keysCopy, 0, keys.length);
        System.arraycopy(values, 0, valuesCopy, 0, values.length);
        K[] newKeys = (K[]) new Object[keys.length * 2];
        V[] newValues = (V[]) new Object[values.length * 2];
        keys = newKeys;
        values = newValues;
        for (int i = 0; i < keys.length; i++) {
            put(keysCopy[i], valuesCopy[i]);
        }
    }
}
