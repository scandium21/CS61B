package bearmaps;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class KDTree implements PointSet {

    private Node root;
    private double best;
    private HashMap<Double, Point> bestPoint;

    private class Node {
        Point p;
        Node left;
        Node right;
        // determines if x or y is the comparator
        int depth;

        Node (Point p, Node l, Node r, int depth) {
            this.p = p;
            this.left = l;
            this.right = r;
            this.depth = depth;
        }

        Point point() { return p; }
        int getDepth() { return depth; }
    }

    public KDTree(List<Point> points) {
        Collections.shuffle(points);
        /* arrange the points into a k-d tree */
        for(Point p : points) {
            root = addHelper(root,p,0);
        }
        best = Double.MAX_VALUE;
        bestPoint = new HashMap<>();
        bestPoint.put(best, null);
    }

    private Node addHelper(Node n, Point pt, int dep) {
        if (n == null) { n = new Node(pt, null, null, dep); }
        // compare x when at even depth
        else if (n.getDepth() % 2 == 0) {
            if (pt.getX() > n.point().getX()) {
                n.right = addHelper(n.right, pt, n.getDepth() + 1);
            } else {
                n.left = addHelper(n.left, pt, n.getDepth() + 1);
            }
        }
        // compare y when at odd depth
        else if (n.getDepth() % 2 == 1) {
            if (pt.getY() > n.point().getY()) {
                n.right = addHelper(n.right, pt, n.getDepth() + 1);
            } else {
                n.left = addHelper(n.left, pt, n.getDepth() + 1);
            }
        }
        return n;
    }


    private boolean checkLeft(Node r, Point t) {
        // if x is the comparator
        if(r.getDepth()%2==0) {
            // explore the left side when target x is smaller
            return (r.point().getX() > t.getX());
        }
        // if current node is the y comparator
        else {
            // explore the lower side when target y is smaller
            return (r.point().getY() > t.getY());
        }
    }


    private boolean worthExplore(Point t, Node r) {
        if (r.getDepth()%2 == 0) {
            return (best > Math.abs(t.getX() - r.point().getX()));
        } else {
            return (best > Math.abs(t.getY() - r.point().getY()));
        }
    }

    private void updateBest(Node r, Point t) {
        if (Point.distance(r.point(), t) < best) {
            bestPoint.remove(best);
            best = Point.distance(r.point(), t);
            bestPoint.put(best,r.point());
        }
    }

    private void nearest(Node r, Point target) {
        if (r!=null) {
            updateBest(r, target);
            if (checkLeft(r, target)) {
                nearest(r.left, target);
                if (worthExplore(target,r)) { nearest(r.right,target); }
            } else {
                nearest(r.right, target);
                if (worthExplore(target,r)) { nearest(r.left,target); }
            }
        }
    }


    public Point nearest(double x, double y) {
        Point target = new Point(x,y);
        nearest(root,target);
        return bestPoint.get(best);
    }

    public static void main(String[] args) {

        List<Point> list = new LinkedList<>();
        int sampleSize = 100000;
        Point target = new Point(Math.random()*sampleSize, Math.random()*sampleSize);

        long start3 = System.currentTimeMillis();
        for (int i = 0; i < sampleSize; i++) {
            list.add(new Point(Math.random()*sampleSize, Math.random()*sampleSize));
        }
        long end3 = System.currentTimeMillis();
        System.out.println("Making list - Total time elapsed: " + (end3 - start3) / 1000.0 +  " seconds.");

        long start4 = System.currentTimeMillis();
        KDTree kdt1 = new KDTree(list);
        long end4 = System.currentTimeMillis();
        System.out.println("Making k-d tree - Total time elapsed: " + (end4 - start4) / 1000.0 +  " seconds.");
        NaivePointSet nn = new NaivePointSet(list);

        System.out.println("Sample size: "+sampleSize);

        long start = System.currentTimeMillis();
        Point retkd = kdt1.nearest(target.getX(), target.getY());
        long end = System.currentTimeMillis();
        System.out.println("k-d Tree - Total time elapsed: " + (end - start) / 1000.0 +  " seconds.");

        long start2 = System.currentTimeMillis();
        Point ret = nn.nearest(target.getX(), target.getY());
        long end2 = System.currentTimeMillis();
        System.out.println("NaivePointSet - Total time elapsed: " + (end2 - start2) / 1000.0 +  " seconds.");

        System.out.println("Target x: "+target.getX());
        System.out.println("Target y: "+target.getY());
        System.out.println("Best distance is: "+kdt1.best);
        System.out.println("k-d Tree x: " + ret.getX());
        System.out.println("k-d Tree y: " + ret.getY());
        System.out.println("NPS x: " +retkd.getX());
        System.out.println("NPS y: " +retkd.getY());
    }
}
