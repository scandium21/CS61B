package es.datastructur.synthesizer;

import java.util.LinkedList;

//Note: This file will not compile until you complete task 1 (BoundedQueue).
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        // DONE: Create a buffer with capacity = SR / frequency. You'll need to
        //       cast the result of this division operation into an int. For
        //       better accuracy, use the Math.round() function before casting.
        //       Your buffer should be initially filled with zeros.
        buffer = new ArrayRingBuffer((int)Math.round(SR / frequency));
        for (int i = 0; i < buffer.capacity(); i += 1) {
            buffer.enqueue(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        // DONE: Dequeue everything in buffer, and replace with random numbers
        //       between -0.5 and 0.5. You can get such a number by using:
        //       double r = Math.random() - 0.5;
        //
        //       Make sure that your random numbers are different from each
        //       other.
        // Self Note: can use 1 loop to dequeue and enqueue
        //        for (int i = 0; i < buffer.capacity(); i += 1) {
        //            buffer.dequeue();
        //            double r = Math.random() - 0.5;
        //            buffer.enqueue(r);
        //        }
        while (!buffer.isEmpty()) {
            buffer.dequeue();
        }
        LinkedList<Double> checkRepeat = new LinkedList<>();
        while (!buffer.isFull()) {
            double r = Math.random() - 0.5;
            if (!checkRepeat.contains(r)) {
                buffer.enqueue(r);
            }
            checkRepeat.add(r);
        }
    }

    public void print() {
        System.out.println("First double in buffer: "+buffer.peek());
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        // DONE: Dequeue the front sample and enqueue a new sample that is
        //       the average of the two multiplied by the DECAY factor.
        //       Do not call StdAudio.play().
        Double oldfirst = buffer.dequeue();
        Double newlast = (oldfirst + buffer.peek())/2 * DECAY;
        buffer.enqueue(newlast);

    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        // DONE: Return the correct thing.
        return buffer.peek();
    }
}
    // DONE: Remove all comments that say TODO when you're done.
