import javax.swing.JPanel;
import java.awt.Graphics;

class RenderPanel extends JPanel { 
	
	public Level level;
	public Camera camera;

	//scales size of panel
	public static final double SCALE = 300;

	public RenderPanel(Level level, Camera camera) {
		this.level = level;
		this.camera = camera;
	}

	public void update() {
		repaint();
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
               	if(visible(beginVertex) || visible(endVertex))
                	g.drawLine(begin[0], begin[1], end[0], end[1]);	
        	}
        }
    }	

   	//transforms Vertex to space with camera at origin, where +z is in the direction of the camera
    private Vertex transformToCam(Vertex v) {
        //move
       	double cx = v.wx - camera.pos.wx;
        double cy = v.wy - camera.pos.wy;
      	double cz = v.wz - camera.pos.wz;
        //rotate
        double x = cz * Math.sin(camera.yaw) + cx * Math.cos(camera.yaw);
        double y = cy * Math.cos(camera.pitch) - (cz * Math.cos(camera.yaw) - cx * Math.sin(camera.yaw)) * Math.sin(camera.pitch);
        double z = cy * Math.sin(camera.pitch) + (cz * Math.cos(camera.yaw) - cx * Math.sin(camera.yaw)) * Math.cos(camera.pitch);
        return new Vertex(x, y, z);
    }

    //transforms from camera space to projection plane by flattening the z
    private int[] transformToProj(Vertex v) {
    	int x = (int) ((v.wx / Math.abs(v.wz)) * SCALE);
        int y = (int) ((v.wy / Math.abs(v.wz)) * SCALE);
        return new int[] {x, y};
    }	

    //cleans up coords and makes them ready to display
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