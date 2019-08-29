package bearmaps;

import java.util.HashMap;
import java.util.List;

public class NaivePointSet implements PointSet {

    List<Point> points;

    public NaivePointSet(List<Point> points) {
        this.points = points;
    }

    public Point nearest(double x, double y) {
        HashMap<Double, Point> min = new HashMap();
        double mind = Double.MAX_VALUE;
        for(Point p : points) {
            double dist = Point.distance(p,new Point(x,y));
            if (dist <= mind) {
                min.remove(mind);
                mind = dist;
                min.put(mind,p);
            }
        }
        return min.get(mind);
    }

    public static void main(String[] args) {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2

        System.out.println(ret.getX()); // evaluates to 3.3
        System.out.println(ret.getY()); // evaluates to 4.4
    }
}
