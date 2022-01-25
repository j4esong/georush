import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent; 

class GeoRush {
	
	public static void main(String[] args) {
		
		List<Model> one = new ArrayList<>();
		Level levelOne = new Level(one);
		Camera cam = new Camera(new Vertex(0, 2, 0), 0, 0);

		RenderPanel panel = new RenderPanel(levelOne, cam);

		Cube cube = new Cube(new Vertex(0, 0, 3), 1);
		one.add(cube);

        JFrame testFrame = new JFrame();
        testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel.setPreferredSize(new Dimension((int) (2 * panel.SCALE), (int) (2 * panel.SCALE)));
        testFrame.getContentPane().add(panel, BorderLayout.CENTER);
        testFrame.pack();
        testFrame.setVisible(true);

       	Engine e = new Engine(panel);
       	e.start();
	}
 
}

class Engine extends Thread {
    RenderPanel r;
    public Engine(RenderPanel r) {
    	this.r = r;
    }
    public void run() {
       	while(true) {
            try {
                Thread.sleep(10);
           	} catch (InterruptedException e) {
                e.printStackTrace();
            }
            r.camera.pos.wz += 0.02;
            r.repaint();
        }
    }
}