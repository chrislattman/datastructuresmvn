package com.example.dsa;

/**
 * Interface which defines methods for maps.
 *
 * @param <K> key data type
 * @param <V> value data type
 */
public interface MyMap<K, V> {
    /**
     * Empties this map of all key-value pairs.
     */
     void clear();

    /**
     * Checks if a key is in this map.
     *
     * @param key key to check for
     * @return true if key was found, false otherwise
     */
     boolean containsKey(Object key);

    /**
     * Checks if a value is in this map.
     *
     * @param value value to check for
     * @return true if value was found, false otherwise
     */
     boolean containsValue(Object value);

    /**
     * Compares an object with this map for equality.
     *
     * @param object object to compare to this map
     * @return true if object and this map are equal
     */
    @Override
     boolean equals(Object object);

    /**
     * Returns the value associated with the specified key.
     *
     * @param key key to search for
     * @return value associated with key, or null if key was not found
     */
     V get(Object key);

    /**
     * Returns the value associated with the specified key, or a default value
     * if the key was not found.
     *
     * @param key key to search for
     * @param defaultValue value to return if key was not found in this map
     * @return value associated with key, or defaultValue if key was not found
     */
     V getOrDefault(Object key, V defaultValue);

    /**
     * Returns the Object class-generated hash code of this map.
     *
     * @return hash code of this map
     */
    @Override
     int hashCode();

    /**
     * Checks if this map has no key-value pairs.
     *
     * @return true if this map is empty, false otherwise
     */
     boolean isEmpty();

    /**
     * Returns a copy of the keys in this map.
     *
     * Note: the Java API provides a different method called keySet() which
     * returns a set of the keys.
     *
     * @return a list of the keys
     */
     MyList<K> keyList();

    /**
     * Inserts a key-value pair into this map. Null keys and null values are not
     * permitted.
     *
     * @param key key to add
     * @param value value associated with key
     * @return previous value associated with key, or null if either key was not
     * found, key is null, or value is null
     */
     V put(K key, V value);

    /**
     * Inserts a key-value pair into this map only if key was not found. Null
     * keys and null values are not permitted.
     *
     * @param key key to add
     * @param value value associated with key
     * @return current value associated with key, or null if either key was not
     * found, key is null, or value is null
     */
     V putIfAbsent(K key, V value);

    /**
     * Removes a key-value pair from this map.
     *
     * @param key key to remove
     * @return value associated with key prior to removal, or null if key was
     * not found
     */
     V remove(Object key);

    /**
     * Removes a key-value pair from this map only if the key's current value
     * matches the specified value.
     *
     * @param key key to remove
     * @param value value to check for
     * @return true if the specific key-value pair was found, false otherwise
     */
     boolean remove(Object key, Object value);

    /**
     * Replaces the value for a key with another value. Null values are not
     * permitted.
     *
     * @param key key to modify value of
     * @param value new value for key
     * @return previous value associated with key, or null if key was not found
     * or value is null
     */
     V replace(K key, V value);

    /**
     * Replaces the value for a key only if the key's current value matches the
     * specified old value.
     * Null values are not permitted.
     *
     * @param key key to modify value of
     * @param oldValue old value for key
     * @param newValue new value for key
     * @return true if the key was found and had a value of oldValue, false
     * otherwise
     */
     boolean replace(K key, V oldValue, V newValue);

    /**
     * Returns the number of key-value pairs in this map.
     *
     * @return size of map
     */
     int size();

    /**
     * Returns a string representation of this map, e.g.
     * "{key1=value1, key2=value2, key3=value3, ..., keyN=valueN}".
     *
     * @return string form of this map
     */
    @Override
     String toString();

    /**
     * Returns a copy of the values in this map.
     *
     * Note: this method differs from the official Java API.
     * Modifications made to the list will *not* be reflected in this map.
     *
     * @return a list of the values
     */
     MyList<V> values();
}
