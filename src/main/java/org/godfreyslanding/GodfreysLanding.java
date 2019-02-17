package org.godfreyslanding;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GodfreysLanding extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 800;
	
	public static final int HEIGHT = 800;
	
	public static final int FRAME_RATE = 50;
	
	public static final int MILLIS_PER_FRAME = 1000 / FRAME_RATE; 
	
	
	int tick;
	Rectangle bird;
	
	
	public GodfreysLanding() {
		
		bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
		
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
		tick++;
		
		if (tick % 1 == 0) {
			bird.y += 2;
		}
		
		
		this.repaint();
	}
	
	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		
		Graphics2D g = (Graphics2D)graphics;
		g.setColor(Color.red);
		g.fill(bird);
		
	}



	public static void main(String[] args) {
		new GodfreysLanding();
	}

}
