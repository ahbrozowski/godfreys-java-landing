package org.godfreyslanding;

import java.awt.Color;
import java.awt.Graphics2D;

public class Square implements Entity{

	int x;
	int y;
	int vx;
	int vy;
	Color color;

	public Square() {
		x = 400;
		y = 400;
		vx = 0;
		vy = 2;
		color = Color.red;
	}
	
	public void update() {
		x += vx;
		y += vy;
	}

	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillRect(x - 10, y - 10, 20, 20);
	}
	
}
