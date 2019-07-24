/** Implement SLList.insert(int x, int pos) */
public class disc03 {
    public class SLList<LochNess> {	
        private class StuffNode {
            public LochNess item;
            public StuffNode next;
    
            public StuffNode(LochNess i, StuffNode n) {
                item = i;
                next = n;
            }
        } 
    
        private StuffNode sentinel;
        private int size;

        public SLList() {
            sentinel = (LochNess) new Object(null);
            size = 0;
        }
    
        public SLList(LochNess x) {
            sentinel = (LochNess) new Object(null);
            sentinel.next = new StuffNode(x, null);
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
    
         /** Adds an item to the end of the list. */
         public void addLast(LochNess x) {
             size += 1;
    
             StuffNode p = sentinel.next;
    
             /* Move p until it reaches the end of the list. */
             while (p.next != null) {
                 p = p.next;
             }
    
             p.next = new StuffNode(x, null);
         }
    
         public int size() {
             return size;
         }

         public void insert(LochNess x, int pos) {
             if (sentinel.next == null || pos == 0) {
                addFirst(x);
             }
             else if (pos >= size) {
                addLast(x);
             }
             else {
                 StuffNode p = sentinel.next;
                 for (int i=1;i<pos;i++) {
                     p = p.next;
                 }
                 p.next = (LochNess) new Object(x,p.next);
             }
             size += 1;
         }

        /** Reverses the elements in SLList iteratively*/
        public void reverse() {
            if (sentinel.next == null || sentinel.next.next == null) {
                return;
            }
            
            IntNode ptr = sentinel.next;
            sentinel.next = null;
            
            while (ptr != null) {
                IntNode temp = ptr.next;
                ptr.next = sentinel;
                sentinel = ptr;
                ptr = temp;
            }
        }

        private StuffNode reverseR(StuffNode first) {
            if (first == null || first.next == null)
                return first;
            return first;
        }

        /** Reverses the elements in SLList recursively*/
        public void reverseR() {
            sentinel.next = reverseR(sentinel.next);
        }

    }
}