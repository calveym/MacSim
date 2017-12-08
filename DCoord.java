public class DCoord {
    public double x;
    public long y;

    public DCoord(double a, long b) {
        x = a;
        y = b;
    }

    public String toString() {
        return "Coordinate: " +
        Double.toString(x) +
        ", " +
        Long.toString(y);
    }
}
