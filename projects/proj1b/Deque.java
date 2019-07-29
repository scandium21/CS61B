

public interface Deque<Type> {

    public void addFirst(Type item);
    public void addLast(Type item);
    /** Returns true if deque is empty, false otherwise */
    public default boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    };
    public int size();
    public void printDeque();
    public Type removeFirst();
    public Type removeLast();
    public Type get(int index);
}
