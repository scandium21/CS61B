package bearmaps;
import java.util.NoSuchElementException;
import java.util.HashMap;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>{

    private PriorityNode[] items;
    private int size;
    private HashMap<T, Integer> itemPosMap;

    public ArrayHeapMinPQ(int initCapacity) {
        items = new ArrayHeapMinPQ.PriorityNode[initCapacity + 1];
        itemPosMap = new HashMap<>();
        size = 0;
    }

    public ArrayHeapMinPQ() {
        this(1);
    }

    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentExceptionb if item is already present.
     * You may assume that item is never null. */
    public void add(T item, double priority) {
        if (contains(item)) { throw new IllegalArgumentException("Item already exists"); }
        // shrink size of array if more than 3/4 empty
        if (size < items.length / 4) resize(items.length / 2);
        // double size of array if necessary
        if (size == items.length - 1) resize(2 * items.length);
        // insert item to the end of the array
        size += 1;
        items[size] = new PriorityNode(item, priority);
        itemPosMap.put(item, size);
        // arrange new item to its correct position
        swim(size);
    }

    /* Resize the array */
    private void resize(int newcap) {
        PriorityNode[] newarray = new ArrayHeapMinPQ.PriorityNode[newcap];
        for(int i =0; i<=size; i++) {
            newarray[i] = items[i];
        }
        items = newarray;
    }

    /* Arrange items positions to make a min heap*/
    private void swim(int pos) {
        int n = pos;
        while (n > 1){
            if (items[n].getPriority() < items[n/2].getPriority()) {
                swap(n/2, n);
            }
            n /= 2;
        }

    }

    /* Swap child and parent */
    private void swap(int p, int c) {
        itemPosMap.put(items[p].getItem(),c);
        itemPosMap.put(items[c].getItem(),p);
        PriorityNode temp = items[p];
        items[p] = items[c];
        items[c] = temp;
    }

    /* Check if is a min heap*/
    private boolean isMinHeap() {
        for(int i = 1; 2*i+1<=size; i += 1) {
            PriorityNode p = items[i/2+1];
            PriorityNode l = items[i*2];
            PriorityNode r = items[i*2+1];
            if (l!=null && p.getPriority() > l.getPriority()) return false;
            if (r!=null && p.getPriority() > r.getPriority()) return false;
        }
        return true;
    }


    /* Returns true if the PQ contains the given item. */
    public boolean contains(T item) {
        if (item==null) throw new IllegalArgumentException("Argument is null");
        return itemPosMap.containsKey(item);
    }


    /* Returns the item with minimum priority.
       Throws NoSuchElementException if the PQ is empty. */
    public T getSmallest() {
        if (size()==0) { throw new NoSuchElementException("Priority queue underflow"); }
        return items[1].getItem();
    }
    /* Removes and returns the minimum item.
    Throws NoSuchElementException if the PQ is empty. */
    public T removeSmallest() {
        if (size()==0) throw new NoSuchElementException("Priority queue underflow");
        T removed = getSmallest();
        // swap and delete
        swap(1,size);
        items[size] = null;
        size -= 1;
        itemPosMap.remove(removed);
        // sink
        sink(1);
        return removed;
    }

    private void sink(int n) {

        while (2*n <= size) {
            PriorityNode largerChild =
                    (items[2*n].compareTo(items[2*n+1])>0?items[2*n]:items[2*n+1]);
            PriorityNode smallerChild =
                    (largerChild==items[2*n])?items[2*n+1]:items[2*n];
            // larger than smaller child, swap with smaller child
            if (smallerChild!=null && items[n].compareTo(smallerChild)>0) {
                int temp = itemPosMap.get(smallerChild.getItem());
                swap(n, temp);
                n = temp;
                continue;
            }
            // if smaller child is null and larger than largerchild, swap
            else if (largerChild!=null && items[n].compareTo(largerChild)>0) {
                int temp = itemPosMap.get(largerChild.getItem());
                swap(n, temp);
                n = temp;
                continue;
            }
            break;
        }
    }

    /* Returns the number of items in the PQ. */
    public int size() {
        return size;
    }
    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    public void changePriority(T item, double priority) {
        if(item==null) throw new NoSuchElementException("Argument is null");
        int pos = itemPosMap.get(item);
        PriorityNode toChangeP = items[pos];
        double oldP = toChangeP.getPriority();
        toChangeP.setPriority(priority);
        if (oldP > priority) { swim(pos); }
        if (oldP < priority) { sink(pos); }
    }

    /* @source NaiveMinPQ.java */
    private class PriorityNode implements Comparable<PriorityNode> {
        T item;
        double priority;

        PriorityNode(T e, double p) {
            this.item = e;
            this.priority = p;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }


        void setPriority(double priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(PriorityNode other) {
            if (other == null) {
                return 1;
            }
            return Double.compare(this.getPriority(), other.getPriority());
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            } else {
                return ((PriorityNode) o).getItem().equals(getItem());
            }
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }
    }

    /** Prints out a drawing of the given array of Objects assuming it
     *  is a heap starting at index 1. You're welcome to copy and paste
     *  code from this method into your code, just make sure to cite
     *  this with the @source tag. */
    public static void printFancyHeapDrawing(Object[] items) {
        String drawing = fancyHeapDrawingHelper(items, 1, "");
        System.out.println(drawing);
    }

    /* Recursive helper method for toString. */
    private static String fancyHeapDrawingHelper(Object[] items, int index, String soFar) {
        if (index >= items.length || items[index] == null) {
            return "";
        } else {
            String toReturn = "";
            int rightIndex = 2 * index + 1;
            toReturn += fancyHeapDrawingHelper(items, rightIndex, "        " + soFar);
            if (rightIndex < items.length && items[rightIndex] != null) {
                toReturn += soFar + "    /";
            }
            toReturn += "\n" + soFar + items[index] + "\n";
            int leftIndex = 2 * index;
            if (leftIndex < items.length && items[leftIndex] != null) {
                toReturn += soFar + "    \\";
            }
            toReturn += fancyHeapDrawingHelper(items, leftIndex, "        " + soFar);
            return toReturn;
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ArrayHeapMinPQ<Integer> minHeap = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> minHeapN = new NaiveMinPQ<>();
        for (int i = 0; i < 100000; i += 1) {
            minHeap.add(i, 100000 - i);
            //minHeapN.add(i, 100000 - i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end - start) / 1000.0 +  " seconds.");

        long start2 = System.currentTimeMillis();
        for (int j = 0; j < 100000; j += 1) {
            minHeap.changePriority(j, j + 1);
            //minHeapN.changePriority(j, j + 1);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end2 - start2) / 1000.0 +  " seconds.");
    }
}
