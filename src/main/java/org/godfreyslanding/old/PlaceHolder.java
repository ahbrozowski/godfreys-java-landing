package org.godfreyslanding.old;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class PlaceHolder implements Entity {
	
	public Rectangle getBounds() {
		return new Rectangle(1,1,1,1);
	}
	
	public void update(World w) {}

	public void draw(Graphics2D g) {}
	
	public int getX() {
		return 1;
	}
	
	public int getY() {
		return 1;

	} 
	public boolean clickedOn(int x, int y) {
		return false;
	}

}
