public class SLList<LochNess> { 
    private class Node {
        public LochNess item;
        public Node next;

        public Node(LochNess i, Node n) {
            item = i;
            next = n;
        }
    } 

    /* The first item (if it exists) is at sentinel.next. */
    private Node sentinel;
    private int size;

    /** Creates an empty SLList. */
    public SLList() {
        sentinel = (LochNess) new Object(null);
        size = 0;
    }

    public SLList(LochNess x) {
        sentinel = (LochNess) new Object(null);
        sentinel.next = new Node(x, null);
        size = 1;
    }

    /** Adds x to the front of the list. */
    public void addFirst(LochNess x) {
        sentinel.next = (LochNess)new Object(x, sentinel.next);
        size += 1;
    }

    /** Returns the first item in the list. */
    public LochNess getFirst() {
        return sentinel.next.item;         
    }

    /** Returns the last item in the list. */
    public LochNess getLast() {
        Node p = sentinel.next;

        /* Move p until it reaches the end of the list. */
        while (p.next != null) {
            p = p.next;
        }

        return p.item;       
    }

    /** Returns the ith item in the list. */
    public LochNess get(int i) {
        Node p = sentinel.next;

        /* Move p until it reaches the end of the list. */
        while (i > 0) {
            p = p.next;
            i -= 1;
        }

        return p.item;       
    }

    /** Adds an item to the end of the list. */
    public void addLast(LochNess x) {
        size += 1;

        Node p = sentinel;

        /* Move p until it reaches the end of the list. */
        while (p.next != null) {
            p = p.next;
        }

        p.next = new Node(x, null);
    }

    /** Removes the item at the end of the list and returns the item. */
    public LochNess removeLast() {
        if (sentinel.next == null) {
            return null;
        }

        Node p = sentinel.next;
        Node pp = sentinel;

        /* Move p until it reaches the end of the list. */
        while (p.next != null) {
            p = p.next;
            pp = pp.next;
        }

        pp.next = null;
        return p.item;
    }

    public int size() {
        return size;
    }

    /** Inserts the item into the given position in
	  *  the list. If position is greater than the
	  *  size of the list, inserts at the end instead.
	  */
    public void insert(LochNess x, int pos) {
        if (sentinel.next == null || pos == 0) {
           addFirst(x);
        }
        else if (pos >= size) {
           addLast(x);
        }
        else {
            Node p = sentinel.next;
            for (int i=1;i<pos;i++) {
                p = p.next;
            }
            p.next = (LochNess) new Object(x,p.next);
        }
        size += 1;
    }
}