import javax.swing.JPanel;
import java.awt.Graphics;

class RenderPanel extends JPanel {

    //to do: flatten everything with cam space z > RENDER_DISTANCE to a new plane at z = RENDER_DISTANCE.
    //Line is drawn as long as either point is within render distance.

    public Level level;
    public Camera camera;

    public static final double SCALE = 300;             //scales size of panel
    public static final double RENDER_DISTANCE = 10;    //not implemented yet

    public RenderPanel(Level level, Camera camera) {
        this.level = level;
        this.camera = camera;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Model m : level.board) {
            for (Line l : m.lines) {
                Vertex beginVertex = transformToCam(l.begin);
                Vertex endVertex = transformToCam(l.end);
                int[] begin = transformToDisplay(transformToProj(beginVertex));
                int[] end = transformToDisplay(transformToProj(endVertex));
                g.setColor(m.color);
                if (visible(beginVertex) || visible(endVertex))
                    g.drawLine(begin[0], begin[1], end[0], end[1]);
            }
        }
    }

    //transforms Vertex to space with camera at origin, where +z is in the direction of the camera
    private Vertex transformToCam(Vertex v) {
        //move
        double cx = v.wx - camera.getPos().wx;
        double cy = v.wy - camera.getPos().wy;
        double cz = v.wz - camera.getPos().wz;
        //rotate
        double x = cz * Math.sin(camera.getYaw()) + cx * Math.cos(camera.getYaw());
        double y = cy * Math.cos(camera.getPitch()) - (cz * Math.cos(camera.getYaw()) - cx * Math.sin(camera.getYaw())) * Math.sin(camera.getPitch());
        double z = cy * Math.sin(camera.getPitch()) + (cz * Math.cos(camera.getYaw()) - cx * Math.sin(camera.getYaw())) * Math.cos(camera.getPitch());
        return new Vertex(x, y, z);
    }

    //transforms from camera space to projection plane by flattening the z
    private int[] transformToProj(Vertex v) {
        //minimum is 0.00001 to avoid problems with dividing by zero
        int x = (int) ((v.wx / ((Math.abs(v.wz) > 0.00001) ? Math.abs(v.wz) : 0.00001)) * SCALE);
        int y = (int) ((v.wy / ((Math.abs(v.wz) > 0.00001) ? Math.abs(v.wz) : 0.00001)) * SCALE);
        return new int[] {x, y};
    }

    //cleans up coords and makes them ready to display on JPanel
    private int[] transformToDisplay(int[] coords) {
        int x = (int) SCALE + coords[0];
        int y = (int) SCALE - coords[1];
        return new int[] {x, y};
    }

    //given vertex in camera space, determines if in vision or not
    private boolean visible(Vertex v) {
        return v.wz > 0;
    }

}