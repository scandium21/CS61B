import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.Iterator;

public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    // for iterator method
    private HashSet<K> keySet;
    // number of key-value pairs
    private int n;
    // hash table buckets
    private int m;
    private double loadFactor;
    private ArrayList<Entry<K,V>> buckets;

    private class Entry<K,V> {
        K key;
        V val;
        Entry next;

        public Entry(){key=null;val=null;next=null; }
        public Entry(K k, V v, Entry n) { key = k; val = v; next = n; }

        public K getKey() { return key; }
        public V getValue() { return val; }

        public V get (K k) {
            Entry p = this;
            while (p.key!=null && p!=null) {
                if (p.key.equals(k)) {
                    return val;
                }
                p = p.next;
            }
            return null;
        }

        public void setValue(V v) { val = v; }

        public Entry<K,V> delete(K k) {
            Entry p = this;
            Entry temp = null;
            while (p.next!=null) {

                if (p.key.equals(k) && temp!=null) {
                    temp.next = p.next;
                    return temp;
                }
                temp = p;
                p = p.next;
            }
            return p;
        }
    }

    public MyHashMap() { this(INITIAL_CAPACITY, LOAD_FACTOR); }
    public MyHashMap(int initialSize) { this(initialSize, LOAD_FACTOR); }
    public MyHashMap(int initialSize, double loadFactor) {
        if (initialSize < 1 || loadFactor <= 0)
            throw new IllegalArgumentException();
        m = initialSize;
        n= 0;
        this.loadFactor = loadFactor;
        buckets = new ArrayList<>(m);
        for (int i=0; i<m; i+=1) {
            buckets.add(new Entry<>());
        }
        keySet = new HashSet<>(m);
    }

    /** Removes all of the mappings from this map. */
    public void clear() {
        MyHashMap empty = new MyHashMap(buckets.size());
        copyFrom(empty);
    }

    /** Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        if (key==null) throw new IllegalArgumentException("argument is null");
        return get(key)!=null;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        if (key==null) throw new IllegalArgumentException("argument is null");
        int i = hash(key);
        return buckets.get(i).get(key);
    }

    /* hash value between 0 and m-1 */
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    /** Returns the number of key-value mappings in this map. */
    public int size() {
        return n;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("first argument is null");
        if (value == null) { remove(key); return; }

        // if key exists, update value
        int i = hash(key);
        Entry<K,V> e = find(key,buckets.get(i));
        if(e!=null) {
            e.setValue(value);
        } else {
            // can I use buckets.get(i).addFirst(key,value) ?
            buckets.set(i, new Entry<>(key, value, buckets.get(i)));
            keySet.add(key);
            n += 1;
            // double table size if loadFactor is below 0.75
            if (n > loadFactor * m) { resize(); }
        }
    }

    public Entry<K,V> find(K key, Entry<K,V> head) {
        Entry<K,V> e = head;
        while (head!=null) {
            if (key == null && e.key == null || key.equals(e.key)) {
                return e;
            }
            head = head.next;
        }
        return null;
    }

    public void resize() {
        MyHashMap newMap = new MyHashMap(2*m,0.75);
        for(K k : keySet) {
            V v = get(k);
            newMap.put(k,v);
        }
        copyFrom(newMap);
    }

    public void copyFrom(MyHashMap<K,V> D) {
        m = D.m;
        n = D.n;
        buckets = D.buckets;
        loadFactor = D.loadFactor;
    }

    /** Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
        return keySet;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public V remove(K key) {
        if (key == null) throw new IllegalArgumentException("first argument is null");
        if(containsKey(key)) {
            keySet.remove(key);
            n -= 1;
            V val = get(key);
            // find the bucket the key is in
            int i = hash(key);
            buckets.set(i,buckets.get(i).delete(key));
            return val;
        } else {
            return null;
        }
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    public V remove(K key, V value) {
        if (key == null) throw new IllegalArgumentException("first argument is null");
        if (!get(key).equals(value)) throw new IllegalArgumentException("no such KV pair");
        if(containsKey(key)) {
            keySet.remove(key);
            n -= 1;
            V val = get(key);
            // find the bucket the key is in
            int i = hash(key);
            buckets.set(i,buckets.get(i).delete(key));
            return val;
        } else {
            return null;
        }
    }

    @Override
    public Iterator iterator() {
        return keySet().iterator();
    }

    public static void main(String[] args) {
        MyHashMap<String, String> q = new MyHashMap<String, String>();
        q.put("c", "a");
        q.put("b", "a");
        q.put("a", "a");
        q.put("d", "a");
        q.put("e", "a"); // a b c d e
        System.out.println(null != q.remove("c"));
        System.out.println(q.containsKey("c"));
        System.out.println(q.containsKey("a"));
        System.out.println(q.containsKey("b"));
        System.out.println(q.containsKey("d"));
        System.out.println(q.containsKey("e"));
    }

}
