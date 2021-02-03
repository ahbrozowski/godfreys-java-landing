package org.godfreyslanding;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Shadow {
	int opacity;
	double x;
	double y;
	public Shadow(double x, double y, int opacity) {
		this.opacity = opacity;
		this.x = x;
		this.y = y;
	}
	public double getX() {
		return x;
	}
	public void setX(double x2) {
		this.x = x2;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public int getOpacity() {
		return opacity;
	}
	public void setOpacity(int opacity) {
		this.opacity = opacity;
	}
	public void draw(Graphics2D g) {
		if(opacity > 8) {
			opacity = 8;
		} else if (opacity < 0) {
			opacity = 0;
		}
		
		AffineTransform old = g.getTransform();
		try {
			g.translate(10 * x -20, 10 * y-20);
			g.setColor(new Color(0,0,0, opacity *  32 - (int)(opacity * 1/8.0)));
			g.fillRect(10, 10, 20, 20);
		} finally {
			g.setTransform(old);
		}
		
	}
	
}
