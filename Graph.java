import java.util.*;
import java.awt.*;

public class Graph extends Canvas {
    Vector<Coord> points;
    Vector<LineSegment> segments;
    Coord size;       // graph size
    DCoord sizeMultiplier; // How much values need to be multiplied by
                            // to get true coordinates based on graph size
                            // eg size.x / width

    // Keep track of coordinates with a max and min to make comparisons take O(1);
    Coord xMax;
    Coord yMax;
    Coord yMin;
    Coord xMin;

    long axisWidth;   // axis width  = max x - min x
    long axisHeight;  // axis height = max y - min y

    public Graph() {
        points = new Vector<Coord>();
        segments = new Vector<LineSegment>();
    }

    public void addPoint(Coord point) {
        if(xMax == null || yMax == null || yMin == null || xMin == null) {
            xMax = point;
            yMax = point;
            xMin = point;
            yMin = point;
        }

        points.add(point);
        recalculateMaxMin(point);
    }

    public void update(Graphics g) {

        // regenerate needed info
        generateSegments();
        recalculateSize();

        // erase old contents:
        g.setColor(getBackground());
        g.fillRect(0, 0, (int)size.x, (int)size.y);

        // draw
        g.setColor(Color.black);
        for(LineSegment seg : segments) {
            seg.drawSegment(g, this);
        }
    }

    // check points and find largest and smallest on x and y, add 10% to each side
    void recalculateSize() {
        size = new Coord(getWidth(), getHeight());
        System.out.println("Axis height: " + axisHeight);
        System.out.println("Axis size height: " + size.y);
        System.out.println("Axis width: " + axisWidth);
        System.out.println("Axis size width: " + size.x);
        if(size.y == 0 || size.x == 0 || axisWidth == 0 || axisHeight == 0)
            return;
        System.out.println(axisWidth/(double)size.x);
        System.out.println(axisHeight/size.y);
        sizeMultiplier = new DCoord(((double)axisWidth / (double)size.x), (axisHeight) / size.y);
    }

    void recalculateMaxMin(Coord compare) {
        if(compare.x > xMax.x)
            xMax = compare;
        if(compare.y > yMax.y)
            yMax = compare;
        if(compare.x < xMin.x)
            xMin = compare;
        if(compare.y < yMin.y && yMin.y > 0)
            yMin = compare;
        // System.out.println("Y max: " + yMax.y);
        recalculateAxes();
    }

    void recalculateAxes() {
        axisWidth = xMax.x - xMin.x;
        while(yMax.y - yMin.y < 0)
        {

        }
        System.out.println("WTF: " + yMax.y + " And smaller: " + yMin.y);
        axisHeight = yMax.y - yMin.y;
    }

    // generates segments vector
    void generateSegments() {
        segments.clear();
        for(int i = 0; i < points.size() - 1; i++) {
            segments.add(generateLineSegment(points.get(i), points.get(i+1)));
        }
    }

    // generates single segment
    LineSegment generateLineSegment(Coord a, Coord b) {
        return new LineSegment(a.x, a.y, b.x, b.y);
    }
}

class LineSegment {
    long x1, y1, x2, y2, dx, dy;
    boolean isPositive = false;

    public LineSegment(long a1, long b1, long a2, long b2) {
        x1 = a1;
        y1 = b1;
        x2 = a2;
        y2 = b2;

        dx = x2-x1;
        dy = y2-y1;
        if(dy > 0)
            isPositive = true;
    }

    public void drawSegment(Graphics g, Graph graph) {
        int sx1, sy1, sx2, sy2;
        g.setColor(findGrowthColor());
        // log("Size multiplier: " + graph.sizeMultiplier.x);
        sx1 = (int)(x1 / graph.sizeMultiplier.x);
        sy1 = (int)(graph.size.y - (y1 / graph.sizeMultiplier.y));
        sx2 = (int)(x2 / graph.sizeMultiplier.x);
        sy2 = (int)(graph.size.y - (y2 / graph.sizeMultiplier.y));

        log("Original point: " + y1 + " new point: " + sy1);
        // log("Graph size: " + Integer.toString(graph.size.y));
        // log("Graph sizeMulti: " + Double.toString(graph.sizeMultiplier.y));
        // log("New point: " + sy1);
        // log(" ");
        // System.out.println("Trying to draw segment: " +sx1+ " " +sy1+ " " +sx2+ " " +sy2);

        g.drawLine(sx1, sy1, sx2, sy2);
    }

    Color findGrowthColor() {
        if(isPositive)
            return Color.black;
        else return Color.red;
    }

    public String toString() {
        return "Segment: (" +x1+ ", " +y1+ "), (" +x2+ ", " +y2+ ")";
    }

    public String toStringScaled(Graph g) {
        double xMult = g.sizeMultiplier.x;
        double yMult = g.sizeMultiplier.y;
        return "Segment: (" +x1*xMult+ ", " +y1*yMult+ "), (" +x2*xMult+ ", " +y2*yMult+ ")";
    }

    public void log(String s) {
        System.out.println(s);
    }
}
