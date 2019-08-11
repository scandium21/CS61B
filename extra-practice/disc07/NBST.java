
public class NBST {
    int val;
    NBST left;
    NBST right;

    public NBST(int v, NBST l, NBST r) {
        val = v;
        left = l;
        right = r;
    }

    public static NBST put(NBST r, int v) {
        if (r == null) {
            return new NBST(v, null, null);
        }
        if (v < r.val) {
            r.left = put(r.left, v);
        } else {
            r.right = put(r.right, v);
        }
        return r;
    }


    public static boolean isBST(NBST T) {
        return isBSTHelper(T, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean isBSTHelper(NBST T, int min, int max) {
        if (T==null) {
            return true;
        } else if (T.val < min || T.val > max) {
            return false;
        } else {
            return isBSTHelper(T.left, min, T.val) && isBSTHelper(T.right, T.val, max);
        }
    }

    public static void main(String[] args) {
        NBST tree1 = new NBST(10,null,null);
        NBST.put(tree1, 5);
        NBST.put(tree1, 11);
        NBST.put(tree1, 4);
        tree1.left.right = new NBST(12,null,null);
        boolean istree = NBST.isBST(tree1);
        System.out.println("tree1 is a BST: "+istree);
    }
}