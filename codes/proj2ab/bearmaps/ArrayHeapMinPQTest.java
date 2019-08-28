package bearmaps;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {

    // test add
    @Test
    public void testAdd (){
        ArrayHeapMinPQ<Integer> minHeap = new ArrayHeapMinPQ();
        minHeap.add(1, 1);
        minHeap.add(2, 2);
        minHeap.add(3, 3);
        minHeap.add(4, 4);
        minHeap.add(5, 0.3);
        minHeap.add(6, 2);
        assertTrue(minHeap.size() == 6);
    }

    @Test
    public void testContains() {
        ArrayHeapMinPQ<Integer> minHeap = new ArrayHeapMinPQ<>();
        minHeap.add(1, 1);
        minHeap.add(2, 2);
        minHeap.add(3, 3);
        minHeap.add(4, 4);
        minHeap.add(5, 0.3);
        minHeap.add(6, 2);
        assertTrue(minHeap.contains(2));
        assertTrue(minHeap.contains(4));
        assertFalse(minHeap.contains(0));
    }

    @Test
    public void testGetSmallest() {
        ArrayHeapMinPQ<Integer> minHeap = new ArrayHeapMinPQ<>();
        minHeap.add(1, 1);
        minHeap.add(2, 2);
        minHeap.add(3, 3);
        minHeap.add(4, 4);
        minHeap.add(5, 0.3);
        minHeap.add(6, 2);
        assertEquals(5, minHeap.getSmallest(),0.1);
    }

    @Test
    public void testRemoveSmallest() {
        ArrayHeapMinPQ<Integer> minHeap = new ArrayHeapMinPQ<>();
        minHeap.add(1, 1);
        minHeap.add(2, 2);
        minHeap.add(3, 3);
        minHeap.add(4, 4);
        minHeap.add(5, 0.3);
        minHeap.add(6, 2);
        assertEquals(5, minHeap.removeSmallest(),0.1);
        assertEquals(1,minHeap.removeSmallest(),0.1);
    }

    @Test
    public void testChangePriority() {
        ArrayHeapMinPQ<Integer> minHeap = new ArrayHeapMinPQ<>();
        minHeap.add(1, 1);
        minHeap.add(2, 2);
        minHeap.add(3, 3);
        minHeap.add(4, 4);
        minHeap.add(5, 0.3);
        minHeap.add(6, 2);
        assertEquals(5, minHeap.removeSmallest(),0.1);
        assertEquals(1,minHeap.removeSmallest(),0.1);
        minHeap.changePriority(4,0.1);
        assertEquals(4, minHeap.getSmallest(),0.1);
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ArrayHeapMinPQ<Integer> minHeap = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 100000; i += 1) {
            minHeap.add(i, 100000 - i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end - start) / 1000.0 +  " seconds.");

        long start2 = System.currentTimeMillis();
        for (int j = 0; j < 100000; j += 1) {
            minHeap.changePriority(j, j + 1);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end2 - start2) / 1000.0 +  " seconds.");
    }

}
