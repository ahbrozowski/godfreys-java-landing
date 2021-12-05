package org.godfreyslanding;

import java.awt.Color;

public class IronOreB extends Body {
	public IronOreB(double x, double y) {
		super(x, y, 2, 2, new Vector(0,0), new Color(167,41,6), false, 20, 0, false);
		// TODO Auto-generated constructor stub
	}
	public Item item(int n) {
		Item p = new IronOreP();
		p.setAmount(n);
		return p;
	}

}
