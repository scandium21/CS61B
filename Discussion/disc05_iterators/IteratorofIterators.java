import java.util.*;

public class IteratorofIterators implements Iterator<Integer> {
    LinkedList<Integer> l = new LinkedList<>();

    public IteratorofIterators(List<Iterator<Integer>> a) {
        int i = 0;
        while (!a.isEmpty()) {
            Iterator<Integer> curr = a.get(i);
            if (!curr.hasNext()) {
                a.remove(curr);
                i -= 1;
            } else {
                l.add(curr.next());
            }
            if (a.isEmpty()) { //could've removed the last Iterator
                break;
            }
            i = (i+1) % a.size();
        }
    }

    @Override
    public boolean hasNext() {
        return !l.isEmpty();
    }

    @Override
    public Integer next() {
        return l.removeFirst();
    }

    public static void main(String[] args) {
        int l = 4;
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();
        ArrayList<Integer> z = new ArrayList<>();
        for (int i = 0; i < l; i++) {
            x.add(i);
            y.add(i+l);
            z.add(i+2*l);
        }
        Iterator<Integer> xi = x.iterator();
        Iterator<Integer> yi = y.iterator();
        Iterator<Integer> zi = z.iterator();
        List<Iterator<Integer>> a = List.of(xi,yi,zi);

        Iterator test = new IteratorofIterators(a);
        while (test.hasNext()) {
            System.out.println(test.next());
        }

        // for (Iterator<Integer> i : a) {
        //     while (i.hasNext()) {
        //         System.out.println(i.next());
        //     }
        // }
    }
}