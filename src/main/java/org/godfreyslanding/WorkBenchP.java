package org.godfreyslanding;

import java.awt.Color;

public class WorkBenchP extends Item {
	
	public WorkBenchP() {
		super(true, 2, false, 0, 1, 1, new Vector(0,0), Color.DARK_GRAY, 0, "stone", true);
		// TODO Auto-generated constructor stub
	}
	@Override
	public Body place(int x, int y) {
		Vector v2 = new Vector(0,0);
		return new StoneB(x,y);
	}
}
