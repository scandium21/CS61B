public class RotatingSLList<Item> extends SLList<Item> {

    public void rotateRight(SLList<Item> s) {
        s.addFirst(s.removeLast());
    }

}