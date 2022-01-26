import java.util.List;
import java.util.ArrayList;

class Spike extends Model {

    public Vertex center;

    public Spike(Vertex center, double size) {
        super(new ArrayList<>());
        this.center = center;
        Vertex v1 = new Vertex(center.wx - 0.5 * size, center.wy - 0.5 * size, center.wz - 0.5 * size);
        Vertex v2 = new Vertex(center.wx + 0.5 * size, center.wy - 0.5 * size, center.wz - 0.5 * size);
        Vertex v3 = new Vertex(center.wx - 0.5 * size, center.wy - 0.5 * size, center.wz + 0.5 * size);
        Vertex v4 = new Vertex(center.wx + 0.5 * size, center.wy - 0.5 * size, center.wz + 0.5 * size);
        Vertex tip = new Vertex(center.wx, center.wy + 0.5 * size, center.wz);
        lines.add(new Line(v1, v2));
        lines.add(new Line(v2, v4));
        lines.add(new Line(v4, v3));
        lines.add(new Line(v3, v1));
        lines.add(new Line(v1, tip));
        lines.add(new Line(v2, tip));
        lines.add(new Line(v3, tip));
        lines.add(new Line(v4, tip));
        color = color.RED;
    }

}