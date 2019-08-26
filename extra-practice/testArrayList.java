import java.util.ArrayList;
import java.util.List;

public class testArrayList {
    public static void main(String[] args) {
        List<Integer> arrl1 = new ArrayList<>();
        List<Integer> arrl2 = new ArrayList<>(2);
        List<Integer> arrl3 = new ArrayList<>();
        for (int i=0; i<10;i++) {
            if (i < 6) {
                arrl2.add(i);
            }
            arrl3.add(i,i);
        }
        System.out.println("arrl1 size: "+arrl1.size());
        System.out.println("arrl2 size: "+arrl2.size());
        System.out.println("arrl3 size: "+arrl3.size());
    }
}