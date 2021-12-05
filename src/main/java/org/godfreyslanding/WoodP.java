package org.godfreyslanding;

import java.awt.Color;

public class WoodP extends Item {
	
	public WoodP() {
		super(true, 5, false, 0, 1, 1, new Vector(0,0), new Color(101,67,33), 0, "wood", true);
		// TODO Auto-generated constructor stub
	}
	@Override
	public Body place(int x, int y) {
		Vector v2 = new Vector(0,0);
		return new WoodB(x,y);
	}
}
