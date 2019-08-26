import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyTrieSet implements TrieSet61B {

    private static final int R = 128; // ASCII
    private Node root = new Node(); // root of trie

    private class Node {
        private boolean isKey = false;
        private Node[] next = new Node[R];

        /** Return the keys in Node[] next*/
        private List<Character> getChars() {
            List<Character> chars = new ArrayList<>();
            for (int i=0;i<next.length;i++) {
                if(next[i]!=null) {
                    chars.add((char)i);
                }
            }
            return chars;
        }

        private Node getNode(char c) {
            for(int i=0;i<next.length;i++) {
                if(i==(int)c) {
                    return next[i];
                }
            }
            return null;
        }
    }


    /** Clears all items out of Trie */
    public void clear() {
        root.next = new Node[R];
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    public boolean contains(String key) {
        if (key==null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        Node p = root;
        for (char c: key.toCharArray()) {
            if(p.next[c]==null || p == null) {
                return false;
            }
            p = p.next[c];
        }
        if (!p.isKey) { return false;}
        return true;
    }

    /** Inserts string KEY into Trie */
    public void add(String key) {
        if (key==null) {
            throw new IllegalArgumentException("argument to add() is null");
        }
        Node p = root;
        //if (contains(key)) { return; }
        for (char c: key.toCharArray()) {
            if(p.next[c]==null) {
                p.next[c] = new Node();
            }
            p = p.next[c];
        }
        p.isKey = true;
    }

    /** Returns a list of all words that start with PREFIX */
    public List<String> keysWithPrefix(String prefix) {
        if (prefix==null) {
            throw new IllegalArgumentException("argument is null");
        }
        List<String> results = new ArrayList<>();
        for(char p : prefix.toCharArray()) {
            if (root.next[p]==null || root ==null) { return results;}
            root = root.next[p];
        }
        if (root.isKey) results.add(prefix);
        for(char c:root.getChars()) {
            colHelp(prefix+c, results, root.getNode(c));
        }

        return results;
    }


    /** Helper method to collect keys with PREFIX */
    private void colHelp(String s, List<String> results, Node r) {
        if(r==null) { throw new IllegalArgumentException("argument is null"); }
        if(r.isKey) { results.add(s); }
        for(char c:r.getChars()) {
            colHelp(s+c, results, r.getNode(c));
        }
    }

    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        String[] saStrings = new String[]{"same", "sam", "sad", "sap"};
        String[] otherStrings = new String[]{"a", "awls", "hello"};

        MyTrieSet t = new MyTrieSet();
        for (String s: saStrings) {
            t.add(s);
        }
        for (String s: otherStrings) {
            t.add(s);
        }

        List<String> keys = t.keysWithPrefix("sa");
        for (String s: saStrings) {
            System.out.println(keys.contains(s));
        }
        for (String s: otherStrings) {
            System.out.println(keys.contains(s));
        }
        for (String s: keys) {
            System.out.println(s);
        }
    }
}
