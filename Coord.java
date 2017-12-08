public class Coord {
    public int x, y;

    public Coord(int a, int b) {
        x = a;
        y = b;
    }

    public String toString() {
        return "Coordinate: " +
        Integer.toString(x) +
        ", " +
        Integer.toString(y);
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
