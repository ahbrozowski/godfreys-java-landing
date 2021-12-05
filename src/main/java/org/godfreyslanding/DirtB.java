package org.godfreyslanding;

import java.awt.Color;

public class DirtB extends Body {
	public DirtB(double x, double y) {
		super(x, y, 2, 2, new Vector(0,0), new Color(155, 118, 83), false, 5, 0, false);
		// TODO Auto-generated constructor stub
	}
	public Item item(int n) {
		Item p = new DirtP();
		p.setAmount(n);
		return p;
	}

}
