package datastructures;

/**
 * A hashFunction map is a data structure used to implement an associative array, a structure that can map keys to values.
 * A hashFunction table uses a hashFunction function to compute an index into an array of buckets or slots,
 * from which the desired value can be found.
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public class HashMap<K, V> {

    private Entry<K,V>[] array;
    int capacity;

    public HashMap(int size) {
        capacity = size;
        array = new Entry[capacity];
    }

    /**
     * Associates the specified value with the specified key in this map.
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     */
    public void put(K key, V value) {
        if (key == null) {
            return;
        }
        int hash = hashFunction(key);
        Entry<K,V> newEntry = new Entry<>(key, value, null);
        if (array[hash] == null) {
            array[hash] = newEntry;
        } else {
            Entry<K,V> previous = null;
            Entry<K,V> current = array[hash];
            while (current != null) {
                if (current.key.equals(key)) {
                    if (previous == null) {
                        newEntry.next = current.next;
                        array[hash] = newEntry;
                        return;
                    } else {
                        newEntry.next = current.next;
                        previous.next = newEntry;
                        return;
                    }
                }
                previous = current;
                current = current.next;
            }
            previous.next = newEntry;
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if this map contains no mapping for the key
     */
    public V get(K key) {
        int hash = hashFunction(key);
        if (array[hash] == null) {
            return null;
        } else {
            Entry<K,V> tmp = array[hash];
            while (tmp != null) {
                if (tmp.key.equals(key)) {
                    return tmp.value;
                }
                tmp = tmp.next;
            }
            return null;
        }
    }


    /**
     * Removes the mapping for the specified key from this map if present.
     * @param key key whose mapping is to be removed from the map
     * @return boolean value true, if the value associated with key was removed, false otherwise.
     */
    public boolean remove(K key) {
        int hash = hashFunction(key);
        if (array[hash] == null) {
            return false;
        } else {
            Entry<K,V> previous = null;
            Entry<K,V> current = array[hash];
            while (current != null) {
                if (current.key.equals(key)) {
                    if (previous == null) {
                        array[hash] = array[hash].next;
                        return true;
                    } else {
                        previous.next = current.next;
                        return true;
                    }
                }
                previous = current;
                current = current.next;
            }
            return false;
        }

    }

    /**
     * Hash function to find appropriate location in array to store value.
     * @param key key that is to be hashed.
     * @return hash value as int.
     */
    private int hashFunction(K key) {
        return (Math.abs(31 * key.hashCode() % capacity));
    }


    /**
     * Class for hash map entries.
     * @param <K> the type of keys maintained by this entry.
     * @param <V> the type of values of this entry.
     */
    class Entry<K, V> {
        K key;
        V value;
        Entry<K,V> next;

        /**
         * Initializes a new entry.
         * @param key key of the entry.
         * @param value value of the entry.
         * @param next next entry in chain.
         */
        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
