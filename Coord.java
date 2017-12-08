public class Coord {
    public long x, y;

    public Coord(long a, long b) {
        x = a;
        y = b;
    }

    public String toString() {
        return "Coordinate: " +
        Long.toString(x) +
        ", " +
        Long.toString(y);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coord)) return false;

        Coord other = (Coord) o;

        if (x != other.x || y != other.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        Double x1 = (double)x;
        Double y1 = (double)y;
        return x1.hashCode() + y1.hashCode();
    }
}
