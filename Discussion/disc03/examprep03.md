### 1. Flatten

Write a method flatten that takes in a 2-D array x and returns a 1-D array that
contains all of the arrays in x concatenated together.
For example, flatten({{1, 2, 3}, {}, {7, 8}}) should return {1, 2, 3, 7, 8}.

```
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
```

### 2. Skippify

Suppose we have the following IntList class, as defined in lecture and lab, with an
added skippify function.
Suppose that we define two IntLists as follows.
```
IntList A = IntList.list(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
IntList B = IntList.list(9, 8, 7, 6, 5, 4, 3, 2, 1);
```
Fill in the method skippify such that the result of calling skippify on A and B
are as below:

\- After calling A.skippify(), A: (1, 3, 6, 10)

\- After calling B.skippify(), B: (9, 7, 4)

```
public class IntList {
    public int first;
    public IntList rest;

    @Override
    public boolean equals(Object o) { ... }
    public static IntList list(int... args) { ... }

    public void skippify() {
        IntList p = this;
        int n = 1;
        while (p != null) {
            IntList next = p.rest;
            for (int i = 0; i < n; i++) {
                if (next == null) {
                    break;
                }
                next = next.rest;
            }
            p.rest = next;
            p = next;
            n += 1;
        }
    }
}
```

### 3. Sans
Fill in the blanks below to correctly implement `ilsans` and `dilsans`.
```
public class IntList {
    public int first;
    public IntList rest;
    public IntList (int f, IntList r) {
        this.first = f;
        this.rest = r;
    }

    /** Non-destructively creates a copy of x that contains no occurences of y.*/
    public static IntList ilsans(IntList x, int y) {
        if (x == null) {
            return null;
        }
        if (x.first == y) {
            return ilsans(x.rest, y);
        }
        return new IntList(x.first, ilsan(x.rest,y));
    }

    /** Destructively modify and return x to contain no occurences of y, 
    without using the keyward "new". */
    public static IntList dilsans(IntList x, int y) {
        if (x == null) {
            return null;
        }
        x.rest = dilsans(x.rest,y);
        if (x.first == y) {
            return x.rest;
        }
        return x;

}
```