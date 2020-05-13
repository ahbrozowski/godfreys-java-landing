package org.godfreyslanding;

import java.awt.Color;

public class StoneB extends Body {
	public StoneB(double x, double y) {
		super(x, y, 2, 2, new Vector(0,0), Color.darkGray, false, 10);
		// TODO Auto-generated constructor stub
	}
	public Item item(int n) {
		Item p = new StoneP();
		p.setAmount(n);
		return p;
	}

}
