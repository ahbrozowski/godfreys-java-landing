package org.godfreyslanding;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GodfreysLanding extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 800;
	
	public static final int HEIGHT = 600;
	
	public static final int TITLE_BAR_HEIGHT = 23;
	
	public static final int FRAME_RATE = 60;
	
	public static final int MILLIS_PER_FRAME = 1000 / FRAME_RATE; 
	
	
	GLWorld world;

	double avgDrawTime = 0;
	double drawCount = 0;
	
	
	public GodfreysLanding() throws IOException {
		
		world = new GLWorld(); 
		JFrame frame = new JFrame();
		frame.setTitle("Godfrey's Landing");
		frame.setSize(WIDTH, HEIGHT + TITLE_BAR_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setVisible(true);
		
		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				GodfreysLanding.this.world.keyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				GodfreysLanding.this.world.keyReleased(e);
			}
			
		});
		
		Timer timer = new Timer(MILLIS_PER_FRAME, e -> {
			this.tick();
		});
		timer.start();
		
		
	}
	
	private void tick() {
		world.update(MILLIS_PER_FRAME/1000.0f, 8, 3);
		this.repaint();
	}	
	
	@Override
	protected void paintComponent(Graphics graphics) {
		long start = System.nanoTime();

		super.paintComponent(graphics);
		
		Graphics2D g = (Graphics2D)graphics;
		world.draw(g,this.getWidth(),this.getHeight());
		
		boolean print = drawCount % FRAME_RATE == 0;
	
		long end = System.nanoTime();
		drawCount++;
		
		long elapsed = end - start;
		
		avgDrawTime = avgDrawTime + (elapsed - avgDrawTime)/drawCount;
		
		if (print) {
			System.err.println(String.format("Millis Per Frame:  %s Avg Draw Time: %s", MILLIS_PER_FRAME, avgDrawTime / 1000000L));
		}
		
	}



	public static void main(String[] args) throws IOException{
		new GodfreysLanding();
	}

}
