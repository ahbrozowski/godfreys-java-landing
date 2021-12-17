package org.godfreyslanding;

import java.awt.Color;

public class TorchB extends Body {
	public TorchB(double x, double y) {
		super(x, y, 2, 2, new Vector(0,0), Color.WHITE, false, 1, 20, false, 4);
		// TODO Auto-generated constructor stub
	}
	public Item item(int n) {
		Item p = new TorchP();
		p.setAmount(n);
		return p;
	}

}
