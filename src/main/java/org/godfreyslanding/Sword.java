package org.godfreyslanding;

import java.awt.Color;
import java.awt.Graphics2D;

public class Sword extends Item {
	static Vector knockback = new Vector(.4,-.4); 
	public Sword() {
		super(false, 3, true, 10, 3, 3, knockback, Color.LIGHT_GRAY, 0, "sword", false);
		// TODO Auto-generated constructor stub
	}

}
