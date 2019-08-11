package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Double> arb = new ArrayRingBuffer<>(10);
        int capa = 10;
        arb.enqueue(0.5);
        arb.enqueue(0.8);
        arb.enqueue(0.9);
        int fill = 3;
        assertEquals(capa, arb.capacity());
        assertEquals(fill, arb.fillCount());

        double a = arb.dequeue();
        double b = arb.dequeue();
        int numItem = arb.fillCount();
        arb.enqueue(0.77);
        double p = arb.peek();


        assertEquals(a, 0.5,0.01);
        assertEquals(b, 0.8,0.01);
        assertEquals(1, numItem);
        assertEquals(0.9, p, 0.01);


        while (!arb.isFull()) {
            arb.enqueue(Math.random() - 0.5);
        }

        assertTrue(arb.isFull());

        while (!arb.isEmpty()) {
            arb.dequeue();
        }
        assertTrue(arb.isEmpty());

    }
}
