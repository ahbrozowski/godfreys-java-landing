package org.godfreyslanding;

import java.awt.Color;

public class TorchP extends Item {

	public TorchP() {
		super(true, 4, false, 0, 1, 1, new Vector(0,0), Color.WHITE, 0);
		
		// TODO Auto-generated constructor stub
	}
	public Body place(int x, int y) {
		Vector v2 = new Vector(0,0);
		return new TorchB(x,y);
	}

}
