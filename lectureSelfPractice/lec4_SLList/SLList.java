/** An SLList is a list of integers, which hides the terrible truth
 *  of the nakedness within.
 */
public class SLList {
    
    private static class IntNode {
        public int item;
        public IntNode next;
    
        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }
    
    /** The first item (if it exists) is at sentinel.next */
    private IntNode sentinel;
    private int size;

    /**Creates an empty SLList */
    public SLList() {
        sentinel = new IntNode(27, null);
        size = 0;
    }

    public SLList(int x) {
        sentinel = new IntNode(27, null);
        sentinel.next = new IntNode(x,null);
        size = 1;
    } 

    /** Adds x to the front of the list*/
    public void addFirst(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
        size += 1;
    }

    /** Returns the first item in the list*/
    public int getFirst() {
        return sentinel.next.item;
    }

    /** Adds an item to the end of the list.  */
    public void addLast (int x) {
     
        IntNode p = sentinel;
        while (p.next!=null) {
            p = p.next;
        }
        p.next = new IntNode(x, null);
    
        size += 1;
    }

    public int size() {
        return size;
    }

    /** Return size of the list iteratively */
    public int sizeI () {
        IntNode p = sentinel.next;
        int size = 0;
        while (p!=null) {
            p = p.next;
            size ++;
        }
        return size;
    }

    public static void main(String[] args) {
        /* Creates a list of one integer, namely 10 */
        SLList L = new SLList();
        L.addLast(3);
        L.addFirst(8);
        //L.addFirst(6);
        //L.first.next.next = L.first.next;
        System.out.println(L.getFirst());
        System.out.println("Size of SLList is: "+L.sizeI());
        System.out.println("Size of SLList starting at first is: "+L.size());
    }
}