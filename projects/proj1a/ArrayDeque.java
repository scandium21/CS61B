
public class ArrayDeque <Type> {
    private Type[] items;
    private int size;

    /** Creates an empty array list deque */
    public ArrayDeque() {
        items = (Type[]) new Object[8];
        size = 0;
    }

    /** Creates a deep copy of 'other' */
    public ArrayDeque(ArrayDeque other) {
        ArrayDeque newCopy = new ArrayDeque();
        newCopy.size = other.size;

    }

    /** Resizes array to larger capacity */
    private void resize(int cap) {
        Type[] a = (Type[]) new Object[cap];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    /** Adds an item of certain Type to the front of the deque */
    public void addFirst(Type item) {
        if(size == items.length) {
            resize(size * 2);
        }
        items[0] = item;
        size += 1;
    }

    /** Adds an item of type Type to the back of the deque */
    public void addLast(Type item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[size] = item;
        size += 1;
    }
    /** Returns true if deque is empty, false otherwise */
    public boolean isEmpty() {
        if (items[0]==null)
            return true;
        return false;
    }

    /** Returns the number of items in the deque */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last,
     * separated by a space. Once all the items have been printed,
     * print out a new line  */
    public void printDeque() {
        for(int i =0; i<size; i++) {
            System.out.print(items[i]+" ");
        }

        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null */
    public Type removeFirst() {
        if (isEmpty())
            return null;
        Type item = items[0];
        for (int i =0; i<size-1; i++) {
            items[i] = items[i+1];
        }
        items[size] = null;
        size -= 1;
        return item;
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null */
    public Type removeLast() {
        if (isEmpty())
            return null;
        Type item = items[size];
        items[size] = null;
        size -= 1;
        return item;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!  */
    public Type get(int index) {
        if (index >= size || index < 0)
            return null;
        return items[index];
    }



}
