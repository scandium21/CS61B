import java.util.*;
import java.util.function.Predicate;
import java.util.NoSuchElementException;

public class FilteredList<T> implements Iterable<T> {
    public List<T> curr;
    public Predicate<T> f;

    public FilteredList (List<T> L, Predicate<T> filter) {
        curr = L;
        f = filter;
    }

    @Override
    public Iterator<T> iterator() {
        return new FilteredListIterator();
    }

    public class FilteredListIterator<T> implements Iterator<T> {
        public List<T> curr;
        public Predicate<T> f;
        int index;

        public FilteredListIterator(List<T> L, Predicate<T> filter) {
            curr = L;
            f = filter;
            index = 0; 
        }

        @Override
        public boolean hasNext() {
            while (!curr.isEmpty() && !f.test(curr.get(index))) {
                index += 1;
            }
            return index < curr.size();
        }

        @Override
        public T Next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T item = curr.get(index);
            index += 1;
            return item;
        }
    }
}