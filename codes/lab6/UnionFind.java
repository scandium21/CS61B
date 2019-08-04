import java.util.NoSuchElementException;

public class UnionFind {

    // TODO - Add instance variables?
    int[] vindex;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        // TODO
        vindex = new int[n];
        for (int i = 0; i < vindex.length; i++) {
            vindex[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        // TODO
        if (vertex < 0 || vertex >= vindex.length) {
            throw new NoSuchElementException("Not a valid vertex.");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        // TODO
        int size = -1 * (parent(find(v1)));
        return size;
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        // TODO
        return vindex[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO
        if (find(v1)==find(v2))
            return true;
        return false;
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        // TODO
        int r1 = find(v1);
        int r2 = find(v2);
        int size1 = sizeOf(v1);
        int size2 = sizeOf(v2);
        // compare size of trees v1 and v2 belong to
        // connect the smaller tree to the root of the larger
        if (size1 <= size2) {
            vindex[r1] = r2;
            vindex[r2] -= size1;
        } else {
            vindex[r2] = r1;
            vindex[r1] -= size2;
        }

    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        // TODO
        if (vindex[vertex] < 0)
            return vertex;
        return find(vindex[vertex]);
    }

}
