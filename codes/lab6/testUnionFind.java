import org.junit.Test;
import static org.junit.Assert.*;

public class testUnionFind {

    int set = 15;
    UnionFind u = new UnionFind(set);

    @Test
    public void testUnion() {
        u.union(2,3);
        u.union(4,5);
        u.union(5,7);
        u.union(8,9);
        u.union(9,7);
        int r2 = u.find(2);

        assertEquals(3, r2);
        assertEquals(5, u.find(5));
        assertTrue(u.connected(2,3));
        assertTrue(u.connected(4,5));
        assertFalse(u.connected(3,7));
        assertTrue(u.connected(4,7));
    }

}
