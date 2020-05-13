package org.godfreyslanding;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GodfreysLanding extends JPanel{
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final int TITLE_BAR_HEIGHT = 23;
	public static final int FRAME_RATE = 60;
	public static final int MILLIS_PER_FRAME = 1000 / FRAME_RATE; 
	
	World world;
	static WorldData w;
	double avgDrawTime = 0;
	double drawCount = 0;
	
	public GodfreysLanding() {
		w = new WorldData(new Body[1000][1000]);
		world = new World(w);
		JFrame frame = new JFrame();
		frame.setTitle("Godfrey's Landing");
		frame.setSize(WIDTH, HEIGHT + TITLE_BAR_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setVisible(true);
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
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
		int width = this.getWidth();
		int height = this.getHeight();
		
		frame.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				GodfreysLanding.this.world.mousePressed(e);				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				GodfreysLanding.this.world.mouseReleased(e);
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		Timer timer = new Timer(MILLIS_PER_FRAME, e -> {
			GodfreysLanding.this.tick();
		});
		timer.start();
	//	this.tick();
		
	}
	
	
	private void tick() {
		world.update(MILLIS_PER_FRAME/1000.0f);
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
	
	
	public static void main(String[] args) throws InterruptedException {
		new GodfreysLanding();
		Thread.sleep(1000000);
	}

}
