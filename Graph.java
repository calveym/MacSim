import java.util.*;
import java.awt.*;

public class Graph extends Canvas{
    Vector<Coord> points;
    Vector<LineSegment> segments;

    public Graph() {
        points = new Vector<Coord>();
    }

    public void addPoint(Coord point) {
        points.add(point);
    }

    public void update(Graphics g) {
        generateSegments();
        for(LineSegment seg : segments) {
            seg.drawSegment(g, this);
        }
    }

    // generates segments vector
    public void generateSegments() {
        segments.clear();
        for(int i = 0; i < points.size() - 1; i++) {
            segments.add(generateLineSegment(points.get(i), points.get(i+1)));
        }
    }

    // generates single segment
    public LineSegment generateLineSegment(Coord a, Coord b) {
        return new LineSegment(a.x, a.y, b.x, b.y);
    }
}

class LineSegment {
    int x1, y1, x2, y2, dx, dy;
    boolean isPositive = false;

    public LineSegment(int a1, int b1, int a2, int b2) {
        x1 = a1;
        y1 = b1;
        x2 = a2;
        y2 = b2;

        dx = x2-x1;
        dy = y2-y1;
        if(dx > 0 && dy > 0)
            isPositive = true;
    }

    public void drawSegment(Graphics g, Graph graph) {
        g.setColor(findGrowthColor());
    }

    Color findGrowthColor() {
        if(isPositive)
            return Color.black;
        else return Color.red;
    }
}
