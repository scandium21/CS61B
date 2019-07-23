## Disc03

### 1. Static Shock

Write what the main method will print out once it is executed. It might be helpful
to draw box and pointer diagrams to keep track of variables.

```
public class Shock {
    public static int bang;
    public static Shock baby;
 
public Shock() {
    this.bang = 100;
}
 
public Shock (int num) {
    this.bang = num;
    baby = starter();
    this.bang += num;
}
 
public static Shock starter() {
    Shock gear = new Shock();
    return gear;
}
 
public static void shrink(Shock statik) {
    statik.bang -= 1;
}
 
public static void main(String[] args) {
    Shock gear = new Shock(200);
    System.out.println(gear.bang); __400______ // Answer: 300
    shrink(gear);
    shrink(starter());
    System.out.println(gear.bang); __399______// Answer: 99
    }
}
```

**Answer: 300, 99. Note that all the variables (bang and baby) are static, so we only need to track
what their current values are to answer this question. We really only need to keep
track of changes to bang.**

### 2. Horse-o-Scope

Given the following program, draw out the box and pointer diagram that results
from executing the main method. What is the output printed by the program?

```
public class Horse {
    Horse same;
    String jimmy;

    public Horse(String lee) {
        jimmy = lee;
    }

    public Horse same(Horse horse) {
        if (same != null) {
            Horse same = horse;
            same.same = horse;
            same = horse.same;
        }
        return same.same;
    }

    public static void main(String[] args) {
        Horse horse = new Horse("you've been");
        Horse cult = new Horse("horsed");
        cult.same = cult;
        cult = cult.same(horse);
        System.out.println(cult.jimmy);
        System.out.println(horse.jimmy);
    }
}
```
Program Output:

you've been

you've been

**Answer: horsed  you've been**


### 3. Give em the 'Ol Switcheroo

For each function call in the main method, write out the x and y values of both
foobar and baz after executing that line

```
public class Foo {
    public int x, y;

    public Foo (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static void switcheroo (Foo a, Foo b) {
        Foo temp = a;
        a = b;
        b = temp;
    }

    public static void fliperoo (Foo a, Foo b) {
        Foo temp = new Foo(a.x, a.y);
        a.x = b.x;
        a.y = b.y;
        b.x = temp.x;
        b.y = temp.y;
    }

    public static void swaperoo (Foo a, Foo b) {
        Foo temp = a;
        a.x = b.x;
        a.y = b.y;
        b.x = temp.x;
        b.y = temp.y;
    }

    public static void main (String[] args) {
        Foo foobar = new Foo(10, 20);
        Foo baz = new Foo(30, 40);
        switcheroo(foobar, baz); foobar.x: _10__ foobar.y: _20__ baz.x: _30__ baz.y: _40__
        fliperoo(foobar, baz); foobar.x: _30__ foobar.y: _40__ baz.x: _10__ baz.y: _20__
        swaperoo(foobar, baz); foobar.x: _10__ foobar.y: _20__ baz.x: _10__ baz.y: _20__
    }
}
```

### 4. Quik Maths

What would the contents of the array be after being run through these functions in
the main method?

```
public class QuikMaths {
    public static void mulitplyBy3(int[] A) {
        for (int x: A) {
            x = x * 3;
        }
    }

    public static void multiplyBy2(int[] A) {
        int[] B = A;
        for (int i = 0; i < B.length; i+= 1) {
            B[i] *= 2;
        }
    }

    public static void swap (int A, int B ) {
        int temp = B;
        B = A;
        A = temp;
    }

    public static void main(String[] args) {
        int[] arr;
        arr = new int[]{2, 3, 3, 4};
        multiplyBy3(arr);

        /* Value of arr: {__6,9,9,12______________________} */ // Answer: {2,3,3,4} --> int x is local variable!!!

        arr = new int[]{2, 3, 3, 4};
        multiplyBy2(arr);

        /* Value of arr: {__4,6,6,8______________________} */

        int a = 6;
        int b = 7;
        swap(a, b);

        /* Value of a: __6_____ Value of b: __7_____ */
    }
}
```

