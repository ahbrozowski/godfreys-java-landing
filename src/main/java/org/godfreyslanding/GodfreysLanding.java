package org.godfreyslanding;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GodfreysLanding extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 800;
	
	public static final int HEIGHT = 800;
	
	public static final int FRAME_RATE = 50;
	
	public static final int MILLIS_PER_FRAME = 1000 / FRAME_RATE; 
	
	
	World world;

	double avgDrawTime = 0;
	double drawCount = 0;
	
	
	public GodfreysLanding() throws IOException {
		
		world = new World();
		
		JFrame frame = new JFrame();
		frame.add(this);
		frame.setTitle("Godfrey's Landing");
		frame.setSize(WIDTH,HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		Timer timer = new Timer(MILLIS_PER_FRAME, e -> this.tick());
		timer.start();
		
		
		
	}
	
	private void tick() {
		world.update();
		this.repaint();
	}	
	
	@Override
	protected void paintComponent(Graphics graphics) {
		long start = System.nanoTime();

		super.paintComponent(graphics);
		
		Graphics2D g = (Graphics2D)graphics;
		world.draw(g);
		
		boolean print = drawCount % FRAME_RATE == 0;
	
		long end = System.nanoTime();
		drawCount++;
		
		long elapsed = end - start;
		
		avgDrawTime = avgDrawTime + (elapsed - avgDrawTime)/drawCount;
		
		if (print) {
			System.err.println(String.format("Millis Per Frame:  %s Avg Draw Time: %s", MILLIS_PER_FRAME, avgDrawTime / 1000000L));
		}
		
	}



	public static void main(String[] args) throws IOException {
		new GodfreysLanding();
	}

}
