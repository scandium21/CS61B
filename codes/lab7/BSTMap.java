import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable <K> , V> implements Map61B<K, V>{

    private Node root;
    private int size = 0;

    private class Node {
        K k;
        V val;
        Node left, right;

        public Node (K key, V value, Node l, Node r) {
            k = key;
            val = value;
            left = l;
            right = r;
        }
    }

    /** Removes all of the mappings from this map. */
    public void clear() {
        root = null;
        size = 0;
    }

    /* Private get() helper */
    private Node get(Node r, K key) {
        if (r == null) {
            return null;
        } else if (key.compareTo(r.k) == 0) {
            return r;
        } else if (key.compareTo(r.k) < 0) {
            return get(r.left, key);
        } else {
            return get(r.right, key);
        }
    }

    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return (get(root, key)!=null);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (containsKey(key)) {
            return get(root, key).val;
        }
        return null;
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    /* Private put() helper */
    private Node put(Node r, K key, V value) {
        if (r == null) {
            return new Node (key, value, null, null);
        }
        if (key.compareTo(r.k) < 0) {
            r.left = put(r.left, key, value);
        } else {
            r.right = put(r.right, key, value);
        }
        return r;
    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        root = put(root, key, value);
        size += 1;
    }

    /* Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator iterator() {
        throw  new UnsupportedOperationException();
    }


}
