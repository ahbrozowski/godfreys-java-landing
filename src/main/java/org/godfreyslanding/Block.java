package org.godfreyslanding;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

public class Block implements Entity{

	int x;
	int y;
	Image img;
	
	public Block(int x, int y, Image img) throws IOException {
		this.x = x;
		this.y = y;
		this.img = img;
	}
	
	public void update() {
	}

	public void draw(Graphics2D g) {
		g.drawImage(img, x, y, null);
	}

	
}
