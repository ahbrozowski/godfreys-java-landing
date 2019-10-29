package org.godfreyslanding.old;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

public interface Entity {
	
	public Rectangle getBounds();
	
	public void update(World w) throws IOException;

	public void draw(Graphics2D g);
	
	public int getX();
	
	public int getY();

	public boolean clickedOn(int x, int y);
	

}
