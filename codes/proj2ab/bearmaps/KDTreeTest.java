package bearmaps;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    @Test
    public void testNearest() {

        List<Point> list = new LinkedList<>();
        int sampleSize = 10000;
        Point target = new Point(Math.random()*sampleSize, Math.random()*sampleSize);

        for (int i = 0; i < sampleSize; i++) {
            list.add(new Point(Math.random()*sampleSize, Math.random()*sampleSize));
        }
        KDTree kdt1 = new KDTree(list);
        NaivePointSet nn = new NaivePointSet(list);

        Point retkd = kdt1.nearest(target.getX(), target.getY());
        Point ret = nn.nearest(target.getX(), target.getY());

        assertEquals(ret.getX(),retkd.getX(),0.01);
        assertEquals(ret.getY(),retkd.getY(),0.01);
    }
}
