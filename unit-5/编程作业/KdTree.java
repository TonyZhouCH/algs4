import java.util.TreeSet;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;


public class KdTree {
	private static class Node {
		private Point2D p;
		//private RectHV rect;
		private Node lb;
		private Node rt;
		private boolean isVertical;

		public Node(Point2D p, Node lb, Node rt, boolean isVertical) {
			this.p = p;
			//this.rect = rect;
			this.lb = lb;
			this.rt = rt;
			this.isVertical = isVertical;
		}
	}

	private static final RectHV container = new RectHV(0, 0, 1, 1);  //because the point given is in the container;
	private Node root;
	private int size;

	public KdTree() {
		this.root = null;
		this.size = 0;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public int size() {
		return this.size();
	}

	public void insert(Point2D p) {
		root = insert(root, p, true);
	}

	private Node insert(Node h, Point2D q, boolean isVertical) {
		if (h == null) {
			size++;
			return new Node(q, null, null, isVertical);
		}

		if (q.x() == h.p.x() && q.y() == h.p.y()) {
			return h;
		}

		if (h.isVertical && q.x() < h.p.x() || !h.isVertical && q.y() < h.p.y()) {
			h.lb = insert(h.lb, q, !h.isVertical);
		} else {
			h.rt = insert(h.rt, q, !h.isVertical);
		}

		return h;
	}

	public boolean contains(Point2D p) {
		return contains(root, p);
	}

	private boolean contains(Node h, Point2D p) {
		if (h == null) {
			return false;
		}

		if (p.x() == h.p.x() && p.y() == h.p.y()) {
			return true;
		}

		if (h.isVertical && p.x() < h.p.x() || !h.isVertical && p.y() < h.p.y()) {
			return contains(h.lb, p);
		} else {
			return contains(h.rt, p);
		}
	}
	

	public void draw() {
		StdDraw.setScale(0, 1);  
        StdDraw.setPenColor(StdDraw.BLACK);  
        StdDraw.setPenRadius();  
        container.draw();  
  
        draw(root, container);  
	}

	private void draw(Node h, RectHV rect) {
		if (h == null) {
			return;
		}

		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(0.01);
		
		new Point2D(h.p.x(), h.p.y()).draw();  
        Point2D min, max;  
        if (h.isVertical) {  
            StdDraw.setPenColor(StdDraw.RED);  
            min = new Point2D(h.p.x(), rect.ymin());  
            max = new Point2D(h.p.x(), rect.ymax());  
        } else {  
            StdDraw.setPenColor(StdDraw.BLUE);  
            min = new Point2D(rect.xmin(), h.p.y());  
            max = new Point2D(rect.xmax(), h.p.y());  
        }  
  
        StdDraw.setPenRadius();  
        // draw that division line  
        min.drawTo(max);  
  
        // recursively draw children  
        draw(h.lb, leftRect(rect, h));  
        draw(h.rt, rightRect(rect, h));    
	}

	private RectHV leftRect(RectHV rect, Node h) {     // find the left rect or bottom rect, which are both "left" rect
		if (h.isVertical) { // left rect
			return new RectHV(rect.xmin(), rect.ymin(), h.p.x(), rect.ymax());
		} else { //bottom rect
			return new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), h.p.y());
		}
	}

	private RectHV rightRect(RectHV rect, Node h) {    // find the right rect or top rect, which are both "right" rect
		if(h.isVertical) { // right rect
			return new RectHV(h.p.x(), rect.ymin(), rect.xmax(), rect.ymax());
		} else { // top rect
			return new RectHV(rect.xmin(), h.p.y(), rect.xmax(), rect.ymax());
		}
	}

	
    public Iterable<Point2D> range(RectHV rect) {
    	TreeSet<Point2D> rangeSet = new TreeSet<Point2D>();
    	range(root, container, rect, rangeSet);   
    	return rangeSet;
    } 

    private void range(Node h, RectHV nrect, RectHV rect, TreeSet<Point2D> rangeSet) { //the nrect is to detect whether the rect is intersect with it
    	if (h == null) {
    		return;
    	}

    	if (rect.intersects(nrect)) {
    		if(rect.contains(h.p)) {
    			rangeSet.add(h.p);
            }
    		range(h.lb, leftRect(nrect, h), rect, rangeSet);
    		range(h.rt, rightRect(nrect, h), rect, rangeSet);
    	}
    }
  
    
    public Point2D nearest(Point2D p) {
    	return nearest(root, container, p, null);
    }

    private Point2D nearest(Node h, RectHV rect, Point2D p, Point2D nearestPoint) {
    	if (h == null) {
    		return nearestPoint;
    	}

    	double pToNode = 0.0;
    	double pToRect = 0.0;
    	RectHV left = null;
    	RectHV right = null;

    	if (nearestPoint != null) {
    		pToNode = p.distanceSquaredTo(nearestPoint);
    		pToRect = rect.distanceSquaredTo(p);
    	}

    	if (nearestPoint == null || pToNode > pToRect) {
    		if (nearestPoint == null || pToNode > p.distanceSquaredTo(h.p)) {
    			nearestPoint = h.p;
    		}
    		if (h.isVertical) {
    			left =  leftRect(rect, h);
    			right = rightRect(rect, h);

    			if (p.x() < h.p.x()) {
    				nearestPoint = nearest(h.lb, left, p, nearestPoint); //first search in the left,much possibility that the nearestPoint is in the area
    				nearestPoint = nearest(h.rt, right, p, nearestPoint);
    			} else {
    				nearestPoint = nearest(h.rt, right, p, nearestPoint);
    				nearestPoint = nearest(h.lb, left, p, nearestPoint);
    			}
    		} else {
    			left = leftRect(rect, h);
    			right = rightRect(rect, h);

    			if (p.y() < h.p.y()) {
    				nearestPoint = nearest(h.lb, left, p, nearestPoint);
    				nearestPoint = nearest(h.rt, right, p, nearestPoint);
    			} else {
    				nearestPoint = nearest(h.rt, right,  p, nearestPoint);
    				nearestPoint = nearest(h.lb, left,  p, nearestPoint);
    			}
    		}
    	}

    	return nearestPoint;
    }
}