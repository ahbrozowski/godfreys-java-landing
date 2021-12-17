package org.godfreyslanding;

import java.awt.Color;

public class GrassB extends Body {
	public GrassB(double x, double y) {
		super(x, y, 2, 2, new Vector(0,0), new Color(5,65,0), false, 15, 0, false, 7);
		// TODO Auto-generated constructor stub
	}
	public Item item(int n) {
		Item p = new GrassP();
		p.setAmount(n);
		return p;
	}

}
