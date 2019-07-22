public class SLList {
    public class IntNode {
        public int item;
        public IntNode next;
        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    /* The first item (if it exists) is at sentinel.next. */
    private IntNode sentinel; 
    private int size;

    public SLList() {
        sentinel = new IntNode(27, null);
        size = 0;
    }
    
    public SLList(int x) {
        sentinel = new IntNode(27, null);
        sentinel.next = new IntNode(x, null);
        size = 1;
    }

    /** Adds an item to the front of the list. */
    public void addFirst(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
        size += 1;
    }    

    /** Retrieves the front item from the list. */
    public int getFirst() {
        return sentinel.next.item;
    }

    /** Adds an item to the end of the list. */
    private void addLast(int x, IntNode n) {
        /* Your Code Here! */
        if (n.next == null) {
            n.next =  new IntNode(x,null);
            return;
        }
        addLast(x, n.next);
    }

    public void addLast(int x) {
        addLast(x, this.sentinel);
        size += 1;
    }

    /** Returns the number of items in the list using recursion. */
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        /* Creates a list of one integer, namely 10 */
        SLList L = new SLList();
        //L.addFirst(20);
        //L.addFirst(2);
        L.addLast(200);
        System.out.println(L.size());
    }

}