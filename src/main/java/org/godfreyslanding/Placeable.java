package org.godfreyslanding;

import java.awt.Color;

public class Placeable extends Item {
	
	
	public Placeable(boolean stackable) {
		super(stackable, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Body place(int x, int y) {
		Vector v2 = new Vector(0,0);
		return new Body(x,y,2,2,v2,Color.RED, false, 10);
	}

}
