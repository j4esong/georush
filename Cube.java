import java.util.List;
import java.util.ArrayList;

class Cube extends Model {

    public Vertex center;

    public Cube(Vertex center, double size) {
        super(new ArrayList<>());
        this.center = center;
        Vertex v1 = new Vertex(center.wx - 0.5 * size, center.wy - 0.5 * size, center.wz - 0.5 * size);
        Vertex v2 = new Vertex(center.wx - 0.5 * size, center.wy + 0.5 * size, center.wz - 0.5 * size);
        Vertex v3 = new Vertex(center.wx + 0.5 * size, center.wy + 0.5 * size, center.wz - 0.5 * size);
        Vertex v4 = new Vertex(center.wx + 0.5 * size, center.wy - 0.5 * size, center.wz - 0.5 * size);
        Vertex v5 = new Vertex(center.wx - 0.5 * size, center.wy - 0.5 * size, center.wz + 0.5 * size);
        Vertex v6 = new Vertex(center.wx - 0.5 * size, center.wy + 0.5 * size, center.wz + 0.5 * size);
        Vertex v7 = new Vertex(center.wx + 0.5 * size, center.wy + 0.5 * size, center.wz + 0.5 * size);
        Vertex v8 = new Vertex(center.wx + 0.5 * size, center.wy - 0.5 * size, center.wz + 0.5 * size);
        lines.add(new Line(v1, v2));
        lines.add(new Line(v2, v3));
        lines.add(new Line(v3, v4));
        lines.add(new Line(v4, v1));
        lines.add(new Line(v5, v6));
        lines.add(new Line(v6, v7));
        lines.add(new Line(v7, v8));
        lines.add(new Line(v8, v5));
        lines.add(new Line(v1, v5));
        lines.add(new Line(v2, v6));
        lines.add(new Line(v3, v7));
        lines.add(new Line(v4, v8));
    }

}