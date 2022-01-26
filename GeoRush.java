import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener;

class GeoRush {
	
	public static void main(String[] args) {

		List<Model> one = new ArrayList<>();
		Level levelOne = new Level(one);
		Camera cam = new Camera(new Vertex(0, 2, 0), 0, 0);

		RenderPanel panel = new RenderPanel(levelOne, cam);
		panel.setFocusable(true);

		for (int i = 0; i < 20; i++) 
			one.add(new Cube(new Vertex(0, 0, i), 1));
		one.add(new Spike(new Vertex(0, 1, 10), 1));

        JFrame testFrame = new JFrame("GeoRush");
        testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel.setPreferredSize(new Dimension((int) (2 * panel.SCALE), (int) (2 * panel.SCALE)));
        testFrame.getContentPane().add(panel, BorderLayout.CENTER);
        testFrame.pack();
        testFrame.setVisible(true);
        testFrame.setResizable(false);

       	Engine e = new Engine(panel);
       	e.start();
	}
 
}

class Engine extends Thread {

    RenderPanel r;
    private double cdx, cdy, cdz;
    private double dx, dy, dz;
    private double dpitch, dyaw;

    public Engine(RenderPanel r) {
    	this.r = r;
    }
    public void run() {
   		r.addKeyListener(new MovementListener());
       	while(true) {
            try {
                Thread.sleep(10);
           	} catch (InterruptedException e) {
                e.printStackTrace();
            }
            dz = cdx * r.camera.getOrientation()[2][0] + cdy * r.camera.getOrientation()[2][1] + cdz * r.camera.getOrientation()[2][2];
            dx = cdx * r.camera.getOrientation()[0][0] + cdy * r.camera.getOrientation()[0][1] + cdz * r.camera.getOrientation()[0][2];
            r.camera.getPos().wz += dz;
            r.camera.getPos().wx += dx;
            r.camera.setYaw(r.camera.getYaw() + dyaw);
            r.repaint();
        }
    }

    class MovementListener implements KeyListener {
    	@Override
        public void keyPressed(KeyEvent e) {
           	switch (e.getKeyCode()) {
            	case KeyEvent.VK_W:
            		cdz = 0.1;
            		break; 
            	case KeyEvent.VK_S:
            		cdz = -0.1;
            		break;
            	case KeyEvent.VK_A:
            		cdx = -0.1;
            		break; 
            	case KeyEvent.VK_D:
            		cdx = 0.1;
            		break;
            	case KeyEvent.VK_Q:
            		dyaw = 0.03;
            		break; 
            	case KeyEvent.VK_E:
            		dyaw = -0.03;
            		break;
            	case KeyEvent.VK_Z:
            		r.camera.setYaw(0);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
           		case KeyEvent.VK_W:
            		cdz = 0;
            		break; 
            	case KeyEvent.VK_S:
            		cdz = 0;
            		break;
            	case KeyEvent.VK_A:
            		cdx = 0;
            		break; 
            	case KeyEvent.VK_D:
            		cdx = 0;
            		break;
            	case KeyEvent.VK_Q:
            		dyaw = 0;
            		break; 
            	case KeyEvent.VK_E:
            		dyaw = 0;
            		break;
            } 
        }

        @Override
        public void keyTyped(KeyEvent e) {
            //nothing
        }
    }
}