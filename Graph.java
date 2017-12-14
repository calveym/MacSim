import java.util.*;
import java.awt.*;

public class Graph extends Canvas {

    Image offscreen;
    Coord offscreensize;
    Graphics g2;

    Vector<Coord> points;
    Vector<LineSegment> segments;
    Coord size; // graph pixel dimensions

    // Keep track of coordinates with a max and min to make comparisons take O(1);
    Coord xMax;
    Coord yMax;
    Coord yMin;
    Coord xMin;

    long axisWidth;   // axis width  = max x - min x
    long axisHeight;  // axis height = max y - min y
    long axisHeightScale; // value to divide by to get graph coords
    double axisWidthScale;

    public Graph() {
        points = new Vector<Coord>();
        segments = new Vector<LineSegment>();
        //yMin = new Coord(0, 0);
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
        recalculateAxes();

        if ((offscreen == null)
            || (size.x != offscreensize.x)
            || (size.y != offscreensize.y)) {
            offscreen = createImage((int)size.x, (int)size.y);
            offscreensize = size;
            g2 = offscreen.getGraphics();
            g2.setFont(getFont());
        }

        // erase old contents:
        g2.setColor(getBackground());
        g2.fillRect(0, 0, (int)size.x, (int)size.y);

        // now, draw as usual, but use g2 instead of g
        g2.setColor(Color.black);
        for(LineSegment seg : segments) {
            seg.drawSegment(g2, this);
        }

        // finally, draw the image on top of the old one
        g.drawImage(offscreen, 0, 0, null);
    }

    void recalculateMaxMin(Coord compare) {
        if(compare.x > xMax.x)
            xMax = compare;
        if(compare.y > yMax.y)
            yMax = compare;
        if(compare.x < xMin.x)
            xMin = compare;
        if(compare.y < yMin.y)
            yMin = compare;
        // System.out.println("Y min: " + yMin.y);
        recalculateAxes();
    }

    void recalculateAxes() {
        size = new Coord(getWidth(), getHeight());
        axisWidth = xMax.x - xMin.x;
        // System.out.println("WTF: " + yMax.y + " And smaller: " + yMin.y);
        axisHeight = yMax.y - yMin.y;
        axisHeightScale = axisHeight / size.y;
        axisWidthScale = axisWidth / (double)size.x;
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
        sx1 = (int)(x1 / graph.axisWidthScale);
        sy1 = (int)(graph.size.y - ((y1 - graph.yMin.y) / (graph.axisHeight/600)));
        sx2 = (int)(x2 / graph.axisWidthScale);
        sy2 = (int)(graph.size.y - ((y2 - graph.yMin.y) / (graph.axisHeight/600)));

        // log("Original point: " + y1 + " new point: " + sy1);
        // log("Ymax: " + graph.yMax.y);
        // log("sy1= (" + Double.toString(graph.size.y) + " - (" + y1 + " / " + graph.axisHeightScale + "))");
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
        return "Segment: (" +x1*g.axisWidthScale+ ", " +y1*g.axisHeightScale+ "), (" +x2*g.axisWidthScale+ ", " +y2*g.axisHeightScale+ ")";
    }

    public void log(String s) {
        System.out.println(s);
    }
}
