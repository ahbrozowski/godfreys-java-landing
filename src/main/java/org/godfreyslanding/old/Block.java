package org.godfreyslanding.old;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;

public class Block implements Entity{
	int x;
	int y;
	Image img;
	int tick = 0;
	public Block(int x, int y, Image img) throws IOException {
		this.x = x;
		this.y = y;
		this.img = img;
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, img.getWidth(null), img.getHeight(null));
	}
	
	public boolean clickedOn(int x, int y) {
		return this.getBounds().contains(x, y);
	}

	public void update(World w) {
	}

	public void draw(Graphics2D g) {
		g.drawImage(img, x, y, null);
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
