package org.godfreyslanding;

import java.awt.Color;

public class WorkBenchB extends Body {
	public WorkBenchB(double x, double y) {
		super(x, y, 2, 2, new Vector(0,0), Color.darkGray, false, 15, 0, false);
		// TODO Auto-generated constructor stub
	}
	public Item item(int n) {
		Item p = new StoneP();
		p.setAmount(n);
		return p;
	}

}
