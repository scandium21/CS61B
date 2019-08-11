package es.datastructur.synthesizer;
import java.util.Iterator;

//DONE: Make sure to that this class and all of its methods are public
//DONE: Make sure to add the override tag for all overridden methods
//DONE: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T>  {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // DONE: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    /**
     * return size of the buffer.
     */
    @Override
    public int capacity() {
        return rb.length;
    }


    /**
     * return number of items currently in the buffer.
     */
    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        // DONE: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        } else {
            rb[last] = x;
            fillCount += 1;
            last = (last + 1) % capacity();
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        // DONE: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if (isEmpty())
        {
            throw new RuntimeException("Ring buffer overflow");
        } else {
            T returnItem = rb[first];
            first = (first + 1) % capacity();
            fillCount -= 1;
            return returnItem;
        }

    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        // DONE: Return the first item. None of your instance variables should
        //       change.
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        } else {
            return rb[first];
        }

    }

    // DONE: When you get to part 4, implement the needed code to support
    //       iteration and equals.
    private class ARBIterator implements Iterator<T>{
        int curr = first;
        int count = 0;


        @Override
        public boolean hasNext() {
            return (count >= fillCount());
        }

        @Override
        public T next() {
            T returnItem = rb[curr];
            curr = (curr+1) % capacity();
            count += 1;
            return returnItem;
        }
    }

    @Override
    public Iterator<T> iterator () {
        return new ARBIterator();
    }

    @Override
    public boolean equals (Object o) {
        // Compare to itself.
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }

        if (o.getClass() != getClass()) {
            return false;
        } else if (this.capacity() != ((ArrayRingBuffer)o).capacity()) {
            return false;
        } else if (this.fillCount() != ((ArrayRingBuffer)o).fillCount()) {
            return false;
        } else {
            Iterator iter1 = ((ArrayRingBuffer)o).iterator();
            for (int i = 0; i<fillCount(); i+=1) {
                if (iter1.next() != this.iterator().next()) {
                    return false;
                }
            }
        }
        return true;
    }

}
    // DONE: Remove all comments that say TODO when you're done.
