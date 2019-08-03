import java.util.Iterator;
import java.util.NoSuchElementException;

public class KthIntList implements Iterator <Integer> {
    public int k;
    private IntList curList;
    private boolean hasNext;

    public KthIntList (IntList I, int k) {
        this.k = k;
        this.curList = I;
        this.hasNext = true;
    }

    public boolean hasNext() {
        return this.hasNext;
    }

    public Integer next() {
    
        int n = 0;
        Integer returnitem = curList.item;
        while (curList!=null && n < k) {
            curList = curList.next;
        }
        if (curList==null) {
            throw new NoSuchElementException();
        }
        hasNext = (curListList != null);
        return curList.item;
    }
}