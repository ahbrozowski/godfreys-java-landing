package org.godfreyslanding;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Slime implements Sprite{
	int x;
	int y;
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 20  , 20);
		
	}

	@Override
	public void update(World w) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean clickedOn(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

}
