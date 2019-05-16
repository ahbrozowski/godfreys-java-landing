package org.godfreyslanding;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public interface Entity {
	
	public Rectangle bounds();
	
	public void update(World w);

	public void draw(Graphics2D g);
	

}
