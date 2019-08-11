

public class BST {
    TreeNode root;

    

    private TreeNode put(TreeNode r, int v) {
        if (r == null) {
            return new TreeNode (v, null, null);
        }
        if (v < r.val) {
            r.left = put(r.left, v);
        } else {
            r.right = put(r.right, v);
        }
        return r;
    }

    public void put(int value) {
        root = put(root, value);
    }

    public static boolean isBST(TreeNode T) {
        return isBSTHelper(T, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean isBSTHelper(TreeNode T, int min, int max) {
        if (T==null) {
            return true;
        } else if (T.val < min || T.val > max) {
            return false;
        } else {
            return isBSTHelper(T.left, min, T.val) && isBSTHelper(T.left, T.val, max);
        }
    }

    public static void main(String[] args) {
        BST tree1 = new BST();
        BST tree2 = new BST();
        tree1.put(10);
        tree1.put(5);
        tree1.put(11);
        tree1.put(4);
        tree2.put(12);
        tree1.root.left.right = new TreeNode(12,null,null);
        boolean istree = BST.isBST(tree1.root);
        System.out.println("tree1 is a BST: "+istree);
    }
}