package org.godfreyslanding;

import java.awt.Color;

public class ItemsBlocks {

	public ItemsBlocks() {
			
	}
		
	public  Item itemFromCode(int code) {
		switch(code) {
			case 1: return new Item(true, 1, false, 0, 1, 1, new Vector(0,0), Color.RED, 0, "placeable", true);
			case 2: return new Item(true, 2, false, 0, 1, 1, new Vector(0,0), Color.DARK_GRAY, 0, "stone", true);
			case 3: return new Item(false, 3, true, 10, 3, 3, new Vector(.4,-.4), Color.LIGHT_GRAY, 0, "sword", false);
			case 4: return new Item(true, 4, false, 0, 1, 1, new Vector(0,0), Color.WHITE, 0, "torch", false);
			case 5: return new Item(true, 5, false, 0, 1, 1, new Vector(0,0), new Color(101,67,33), 0, "wood", true);
			case 6: return new Item(true, 6, false, 0, 1, 1, new Vector(0,0), new Color(167,41,6), 0, "iron ore", true);
			case 7: return new Item(true, 7, false, 0, 1, 1, new Vector(0,0), new Color(5,65,0), 0, "grass", true);
			case 8: return new Item(true, 8, false, 0, 1, 1, new Vector(0,0), new Color(155, 118, 83), 0, "dirt", true);
			case 9: return new Item(true, 9, false, 0, 1, 1, new Vector(0,0), new Color(128, 0, 0), 0, "work bench", true);
			case 10: return new Item(false, 10, true, 3, 3, 3, new Vector(.6,-.6), Color.ORANGE , 0, "pickaxe", false);
		}
		return null;
	}
	
	public Body blocksFromCode(int code, int x, int y) {
		Vector v2 = new Vector(0,0);
		switch(code) {
		case 1: return new Body(x,y,2,2,v2,Color.RED, false, 10,0, false, 1);
		case 2: return new Body(x, y, 2, 2, new Vector(0,0), Color.darkGray, false, 15, 0, false, 2);
		case 4: return new Body(x, y, 2, 2, new Vector(0,0), Color.WHITE, false, 1, 20, false, 4);
		case 5: return new Body(x, y, 2, 2, new Vector(0,0), new Color(101,67,33), false, 10, 0, false, 5);
		case 6: return new Body(x, y, 2, 2, new Vector(0,0), new Color(167,41,6), false, 20, 0, false, 6);
		case 7: return new Body(x, y, 2, 2, new Vector(0,0), new Color(5,65,0), false, 15, 0, false, 7);
		case 8: return new Body(x, y, 2, 2, new Vector(0,0), new Color(155, 118, 83), false, 5, 0, false, 8);
		case 9: return new Body(x, y, 2, 2, new Vector(0,0), new Color(128, 0, 0), false, 15, 0, false, 9);
	}
	return null;
	}
}
