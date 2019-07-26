public class SLList<Item> implements List61B<Item> { 
    private class Node {
        public Item item;
        public Node next;

        public Node(Item i, Node n) {
            item = i;
            next = n;
        }
    } 

    /* The first item (if it exists) is at sentinel.next. */
    private Node sentinel;
    private int size;

    /** Creates an empty SLList. */
    public SLList() {
        sentinel = new Node(null,null);
        size = 0;
    }

    public SLList(Item x) {
        sentinel = new Node(null,null);
        sentinel.next = new Node(x, null);
        size = 1;
    }

    /** Adds x to the front of the list. */
    @Override
    public void addFirst(Item x) {
        sentinel.next = new Node(x,null);
        size += 1;
    }

    /** Returns the first item in the list. */
    @Override
    public Item getFirst() {
        return sentinel.next.item;         
    }

    /** Returns the last item in the list. */
    @Override
    public Item getLast() {
        Node p = sentinel.next;

        /* Move p until it reaches the end of the list. */
        while (p.next != null) {
            p = p.next;
        }

        return p.item;       
    }

    /** Returns the back node of our list. */
	private Node getLastNode() {
        Node p = sentinel;

        /* Move p until it reaches the end. */
        while (p.next != null) {
            p = p.next;
        }
        return p;
    }

    /** Returns the ith item in the list. */
    @Override
    public Item get(int i) {
        Node p = sentinel.next;

        /* Move p until it reaches the end of the list. */
        while (i > 0) {
            p = p.next;
            i -= 1;
        }

        return p.item;       
    }

    /** Adds an item to the end of the list. */
    @Override
    public void addLast(Item x) {
        size += 1;

        Node p = sentinel;

        /* Move p until it reaches the end of the list. */
        while (p.next != null) {
            p = p.next;
        }

        p.next = new Node(x, null);
    }

    /** Removes the item at the end of the list and returns the item. */
    @Override
    public Item removeLast() {
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

    @Override
    public int size() {
        return size;
    }

    /** Inserts the item into the given position in
	  *  the list. If position is greater than the
	  *  size of the list, inserts at the end instead.
      */
    @Override
    public void insert(Item x, int pos) {
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
            p.next = new Node(x,p.next);
        }
        size += 1;
    }

    @Override
    public void print() {
        for (Node p = sentinel.next; p != null; p = p.next) {
            System.out.print(p.item + " ");
        }
    }
}