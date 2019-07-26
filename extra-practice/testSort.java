
public class testSort {
    /** Tests the sort method of the Sort class. */
    public static void testSort() {
        String[] input = {"i", "have", "an", "egg"};
        String[] expected = {"an", "egg", "have", "i"};
        Sort.sort(input);
        for (int i = 0; i < input.length; i += 1) {
            if (!input[i].equals(expected[i])) {
                System.out.println("Mismatch in position " + i + ", expected: " + expected[i] + ", but got: " + input[i] + ".");
                org.junit.Assert.assertArrayEquals(expected, input);
                break;
            }
        }
    }

    /** Tests the Smallest method of the Sort class. */
    public static void testSmallest() {
        String[] input = {"i", "have", "an", "egg"};
        String expected = "an";

        String actual = input[Sort.Smallest(input)];
        if (actual != expected) {
            System.out.println("Expected: " + expected + ", but got: " + actual + ".");
        }
        else {
            System.out.println("Passed testSmallest()!");
        }
    }

    /** Tests the swap method of the Sort class */
    public static void testswap() {
        String[] input = {"i", "have", "an", "egg"};
        int a = 0;
        int b = 3;
        Sort.swap(input,a,b);
        String[] expected = {"egg","have","an","i"};

        for (int i = 0; i < input.length; i += 1) {
            if (!input[i].equals(expected[i])) {
                System.out.println("Mismatch in position " + i + ", expected: " + expected[i] + ", but got: " + input[i] + ".");
                org.junit.Assert.assertArrayEquals(expected, input);
                break;
            }
        }
    }

    public static void main(String[] args) {
        //testSort();
        //testSmallest();
        testswap();
    }
}
