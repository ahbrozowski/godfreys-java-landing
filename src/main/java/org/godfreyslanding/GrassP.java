package org.godfreyslanding;

import java.awt.Color;

public class GrassP extends Item {
	
	public GrassP() {
		super(true, 7, false, 0, 1, 1, new Vector(0,0), new Color(5,65,0), 0, "grass", true);
		// TODO Auto-generated constructor stub
	}
	@Override
	public Body place(int x, int y) {
		Vector v2 = new Vector(0,0);
		return new GrassB(x,y);
	}
}
