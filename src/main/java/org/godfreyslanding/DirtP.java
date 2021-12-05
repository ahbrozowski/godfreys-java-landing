package org.godfreyslanding;

import java.awt.Color;

public class DirtP extends Item {
	
	public DirtP() {
		super(true, 8, false, 0, 1, 1, new Vector(0,0), new Color(155, 118, 83), 0, "dirt", true);
		// TODO Auto-generated constructor stub
	}
	@Override
	public Body place(int x, int y) {
		Vector v2 = new Vector(0,0);
		return new DirtB(x,y);
	}
}
