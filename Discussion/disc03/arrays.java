public class arrays {
    
    /** A method that inserts an int item into an int[] arr at the given position.
     * The method should return the resulting array.  */
    public static int[] insert(int[] arr, int item, int position) {
        int[] result = new int[arr.length+1];
        position = Math.min(arr.length, position);
        for(int i =0;i<position;i++) {
            result[i] = arr[i];
        }
        result[position] = item;
        for (int j = position+1;j<result.length;j++) {
            result[j] = arr[j-1];
        }
        return result;
    }

    public static void PrintArray(int[] arr) {
        for(int i =0;i<arr.length;i++) {
            System.out.print(arr[i]+ " ");
        }
        System.out.println();
    }

    /** destructively reverses the items in arr */
    public static void reverse(int[] arr) {
        for(int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
    }
    /**  a non-destructive method replicate(int[] arr) that replaces the 
     * number at index i with arr[i] copies of itself */
    public static int[] replicate(int[] arr) {
        int size = 0;
        for (int i = 0; i < arr.length; i++) {
            size += arr[i];
        }
        int[] result = new int[size];
        int n = 0;
        for (int j = 0; j < arr.length; j++) {
            for (int k = 0; k < arr[j]; k++) {
                result[n] = arr[j];
                n += 1;
            }
        }
        return result;
    }

    /** Write a method flatten that takes in a 2-D array x and returns a 1-D array that 
     * contains all of the arrays in x concatenated together.
     * For example, flatten({{1, 2, 3}, {}, {7, 8}}) should return {1, 2, 3, 7, 8}. */
    public static int[] flatten(int[][] x) {
        int totalLength = 0;

        for (int r = 0; r < x.length; r++) {
            totalLength += x[r].length;
        }

        int[] a = new int[totalLength];
        int aIndex = 0;
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[i].length; j++) {
                a[aIndex] = x[i][j];
                aIndex += 1;
            }
        }
        
        return a;
    }

    public static void main(String[] args) {
        int[] arr = {2,3,4,5,6};
        int[][] arr2d = {{1, 2, 3}, {}, {7, 8}};
        int[] after = insert(arr, 123, 3);
        PrintArray(after);
        reverse(arr);
        PrintArray(arr);
        PrintArray(replicate(arr));
        PrintArray(flatten(arr2d));
    }
}