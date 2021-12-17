package org.godfreyslanding;

import java.awt.Color;
import java.awt.Graphics2D;

public class Item {
	int amount = 1;
	int code;
	boolean stackable;
	boolean weapon;
	Color color;
	int damage;
	double width;
	double height;
	Vector knockback;
	int count = 0;
	boolean draw = false;
	String name;
	boolean material;
	ItemsBlocks ib = new ItemsBlocks();
		public Item(boolean stackable, int code, boolean weapon, int damage, double width, double height, Vector knockback, Color color, int light, String name, boolean material) {
		this.stackable = stackable;
		this.code = code;
		this.weapon = weapon;
		this.damage = damage;
		this.height = height;
		this.width = width;
		this.knockback = knockback;
		this.color = color;
		this.name = name;
		this.material = material;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
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
		return ib.blocksFromCode(code, x, y);
	}
		
	@Override
	public String toString() {
		return ""+ amount;
	}
	
	public void draw(Graphics2D g) {
		
	}
	public boolean colidsEntity(Entity e, double x, double y) {
		double xDist = Math.abs(x - e.getX());
		double yDist = Math.abs(y - e.getY());
		double xMax = Math.abs(this.width/2 + e.getWidth()/2);
		double yMax = Math.abs(this.height/2 + e.getHeight()/2);
		if(xDist <= xMax && yDist <= yMax) {
			return true;
		} else {
			return false;
		}
	}
	public void useItem(Entity e, double x, double y, Graphics2D g, boolean lr) {
		boolean colided = this.colidsEntity(e, x, y);
		if(colided) {
			e.setHealth(e.getHealth()-damage);
			e.getVelocity().addVector(knockback);
			e.setStunned(true);
  			if(!lr) {
  				e.getVelocity().xReflect();
  			}
	
		}
	}
	
	public void countUp() {
		count++;
	}
	
	public int getCount() {
		return count;
	}
	public void resetCount() {
		count = 0;
	}
	
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public String getName() {
		return name;
	}
	
}
