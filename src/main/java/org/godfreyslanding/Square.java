package org.godfreyslanding;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Square implements Sprite {

	int x;
	int y;
	int vx;
	int vy;
	Color color;

	public Square() {
		x = 400;
		y = 200;
		vx = 1;
		vy = 2;
		color = Color.red;
	}
	
	public Rectangle bounds() {
		return new Rectangle(x - 10, y - 10, 20, 20);
	}
	
	public void update(World w) {
		x += vx;
		y += vy;
		
		if (w.intersects(this)) {
			vx = -vx;
			vy = -vy;		
		}
		
		
		if (x <= 10 || 790 <= x) {
			vx = -vx;
		}
		if (y <= 10 || 790 <= y) {
			vy = - vy;
		}
		
		
	}

	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillRect(x - 10, y - 10, 20, 20);
	}
	
}
