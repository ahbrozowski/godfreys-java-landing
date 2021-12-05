package org.godfreyslanding;

import java.awt.Color;

public class WoodB extends Body {
	public WoodB(double x, double y) {
		super(x, y, 2, 2, new Vector(0,0), new Color(101,67,33), false, 10, 0, false);
		// TODO Auto-generated constructor stub
	}
	public Item item(int n) {
		Item p = new WoodP();
		p.setAmount(n);
		return p;
	}

}
