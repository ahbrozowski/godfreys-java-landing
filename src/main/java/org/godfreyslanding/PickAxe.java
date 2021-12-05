package org.godfreyslanding;

import java.awt.Color;
import java.awt.Graphics2D;

public class PickAxe extends Item {
	static Vector knockback = new Vector(.6,-.6); 
	
	public PickAxe() {
		super(false, 10, true, 3, 3, 3, knockback, Color.ORANGE , 0, "pickaxe", false);
		
	}

}
