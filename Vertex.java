class Vertex {

    public double wx, wy, wz;

    public Vertex(double x, double y, double z) {
        wx = x;
        wy = y;
        wz = z;
    }

    @Override
    public String toString() {
        return "{" + wx + ", " + wy + ", " + wz + "}";
    }
}