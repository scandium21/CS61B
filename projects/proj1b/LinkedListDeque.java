public class LinkedListDeque<Type> implements Deque<Type> {

    private class Node {
        public Type item;
        public Node prev;
        public Node next;

        public Node(Type i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private int size;
    private Node sentinel;

    /** Creates an empty linked list deque */
    public LinkedListDeque() {
        sentinel = new Node(null,null,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /** Creates a deep copy of 'other' */
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new Node(null,null,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = other.size();

        for (int i = 0; i<other.size(); i += 1) {
            addLast((Type) other.get(i));
        }
    }

    /** Adds an item of certain Type to the front of the deque */
    public void addFirst(Type item) {
        Node front = new Node(item,sentinel,sentinel.next);
        sentinel.next.prev = front;
        sentinel.next = front;
        size += 1;
    }

    /** Adds an item of type Type to the back of the deque */
    public void addLast(Type item) {
        Node last = new Node(item,sentinel.prev,sentinel);
        sentinel.prev.next = last;
        sentinel.prev = last;
        size += 1;
    }


    /** Returns the number of items in the deque */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last,
     * separated by a space. Once all the items have been printed,
     * print out a new line  */
    public void printDeque() {
        Node p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item+ " ");
            p = p.next;
        }

        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null */
    public Type removeFirst() {
        if (isEmpty())
            return null;
        Type removed = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return removed;
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null */
    public Type removeLast() {
        if (isEmpty())
            return null;
        Type removed = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return removed;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!  */
    public Type get(int index) {
        if (index >= size || index < 0)
            return null;
        Node p = sentinel.next;
        while (index != 0) {
            index -= 1;
            p = p.next;
        }
        return p.item;
    }

    /** Same as get, but uses recursion */
    private Type getRecursive(int index, Node p) {
        if (index == 0)
            return p.item;
        return getRecursive(index-1, p.next);
    }

    public Type getRecursive(int index) {
        if (index >= size || index < 0)
            return null;
        return getRecursive(index, sentinel.next);
    }


}
