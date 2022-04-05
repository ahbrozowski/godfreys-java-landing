package org.godfreyslanding;

import java.awt.Color;

public class Placeable extends Item {
	
	
	public Placeable(boolean stackable) {
		super(stackable, 1, false, 0, 1, 1, new MyVector(0,0), Color.RED, 0, "placeable", true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Body place(int x, int y) {
		MyVector v2 = new MyVector(0,0);
		return new Body(x,y,2,2,v2,Color.RED, false, 10,0, false, 1);
	}

}
