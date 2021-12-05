package org.godfreyslanding;

import java.awt.Color;

public class IronOreP extends Item {
	
	public IronOreP() {
		super(true, 6, false, 0, 1, 1, new Vector(0,0), new Color(167,41,6), 0, "iron ore", true);
		// TODO Auto-generated constructor stub
	}
	@Override
	public Body place(int x, int y) {
		Vector v2 = new Vector(0,0);
		return new IronOreB(x,y);
	}
}
