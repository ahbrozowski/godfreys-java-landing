package org.godfreyslanding;

import java.awt.Color;

public class WorkBenchP extends Item {
	
	public WorkBenchP() {
		super(true, 9, false, 0, 1, 1, new Vector(0,0), new Color(128, 0, 0), 0, "work bench", true);
		// TODO Auto-generated constructor stub
	}
	@Override
	public Body place(int x, int y) {
		Vector v2 = new Vector(0,0);
		return new WorkBenchB(x,y);
	}
}
