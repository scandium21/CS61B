/** Modify the code below so that the max method of DMSList works properly. 
  * Assume all numbers inserted into DMSList are positive. 
  * You may not change anything in the given code. */
public class DMSList {
    private IntNode sentinel;

    public DMSList() {
        sentinel = new IntNode(-1000, new LastIntNode());
    }
    public class IntNode {
        public int item;
        public IntNode next;
        public IntNode(int i, IntNode h) {
            item = i;
            next = h;
        }
        public int max() {
            return Math.max(item, next.max());
        }
    }
    
    /**  */
    public class LastIntNode extends IntNode {
        public LastIntNode() {
            super(0, null);
        }

        @Override
        public int max() {
            return 0;
        }
    }

    /** Returns 0 if list is empty. Otherwise, returns the max element. */
    public int max() {
        return sentinel.next.max(); // this means that sentinel next will always points to a node, which in this case is the LastIntNode
    }

    public static void main(String[] args) {
        DMSList dlist = new DMSList();
        System.out.println(dlist.max());
    }
        
}