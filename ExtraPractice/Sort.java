public class Sort {
    /** Sorts strings destructively. */
    public static void sort(String[] x) { 
        // find the smallest item
        int index = Smallest(x);
        // move it to the front
        swap(x,0,index);
        // selection sort the rest (using recursion?)
        
    }

    /** Returns the smallest string in x */
    public static int Smallest(String[] x) {
        int smallestIndex = 0;
        for (int i = 0; i < x.length; i++) {
            int result = x[i].compareTo(x[smallestIndex]);
            if (result < 0) {
                smallestIndex = i;
            }
        }
        return smallestIndex;
    }

    /** Swap the smallest to the front of array. */
    public static void swap(String[] x, int a, int b) {
        String temp = x[a];
        x[a] = x[b];
        x[b] = temp;
    }


}