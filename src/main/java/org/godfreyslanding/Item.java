package org.godfreyslanding;

import java.awt.Color;

public class Item {
	int amount = 1;
	int code;
	boolean stackable;
	
	public Item(boolean stackable, int code) {
		this.stackable = stackable;
		this.code = code;
	}
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public boolean isStackable() {
		return stackable;
	}

	public void setStackable(boolean stackable) {
		this.stackable = stackable;
	}


	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public Body place(int x, int y) {
		Vector v2 = new Vector(0,0);
		return new Body(x,y,2,2,v2,Color.GREEN, false, 10);
	}
		
	@Override
	public String toString() {
		return ""+ amount;
	}
	
	
}
