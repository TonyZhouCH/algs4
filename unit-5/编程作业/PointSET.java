import java.util.TreeSet;
import java.util.Stack;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.SET;

public class PointSET {
	private SET<Point2D> pointSet;

	public PointSET() {
		pointSet = new SET<Point2D>();
	}

	public boolean isEmpty() {
		return pointSet.isEmpty();
	}

	public int size() {
		return pointSet.size();
	}

	public void insert(Point2D p) {
		pointSet.add(p);
	}

	public  boolean contains(Point2D p) {
		return pointSet.contains(p);
	}

	public void draw() {
		for (Point2D p : pointSet) {
			p.draw();
		}
	}

	public Iterable<Point2D> range(RectHV rect) {
		Stack<Point2D> stack = new Stack<Point2D>();

		for (Point2D p : pointSet) {
			if (rect.contains(p)) {
				stack.push(p);
			}
		}

		return stack;
	}

	public Point2D nearest(Point2D p) {
		if (isEmpty()) {
			return null;
		}

		Point2D minToP = null;

		for (Point2D q : pointSet) {
			double dis = p.distanceSquaredTo(q);

			if (minToP == null || dis < p.distanceSquaredTo(minToP)) {
				minToP = q;
			}
		}

		return minToP;	
	}

	public static void main(String[] args) {
		
	}
}