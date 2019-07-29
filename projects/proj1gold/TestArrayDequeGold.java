import static org.junit.Assert.*;
import org.junit.Test;

/**@source AssertEqualStringDemo.java */
public class TestArrayDequeGold {
    @Test
    public void testArrayDeque() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();
        String errormessage = "\n";

        for (int i = 0; i < 7; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                sad1.addLast(i);
                ads1.addLast(i);
                errormessage += "addLast("+i+")\n";
            } else {
                sad1.addFirst(i);
                ads1.addFirst(i);
                errormessage += "addFirst("+i+")\n";
            }
        }

        //sad1.printDeque();
        while(!sad1.isEmpty() && !ads1.isEmpty()) {
            Integer actual = sad1.removeLast();
            errormessage += "removeLast()\n";
            Integer expected = ads1.removeLast();
            assertEquals(errormessage, expected, actual);
        }
    }
}
