package org.godfreyslanding;

import java.awt.Color;
import java.awt.Graphics2D;

public class Item {
    
    public static Item fromCode(int code) {
        switch(code) {
            case 1: return new Item(true, 1, false, 0, 1, 1, new MyVector(0,0), Color.RED, 0, "placeable", true);
            case 2: return new Item(true, 2, false, 0, 1, 1, new MyVector(0,0), Color.DARK_GRAY, 0, "stone", true);
            case 3: return new Item(false, 3, true, 10, 3, 3, new MyVector(.4,-.4), Color.LIGHT_GRAY, 0, "sword", false);
            case 4: return new Item(true, 4, false, 0, 1, 1, new MyVector(0,0), Color.WHITE, 0, "torch", false);
            case 5: return new Item(true, 5, false, 0, 1, 1, new MyVector(0,0), new Color(101,67,33), 0, "wood", true);
            case 6: return new Item(true, 6, false, 0, 1, 1, new MyVector(0,0), new Color(167,41,6), 0, "iron ore", true);
            case 7: return new Item(true, 7, false, 0, 1, 1, new MyVector(0,0), new Color(5,65,0), 0, "grass", true);
            case 8: return new Item(true, 8, false, 0, 1, 1, new MyVector(0,0), new Color(155, 118, 83), 0, "dirt", true);
            case 9: return new Item(true, 9, false, 0, 1, 1, new MyVector(0,0), new Color(128, 0, 0), 0, "work bench", true);
            case 10: return new Item(false, 10, true, 3, 3, 3, new MyVector(.6,-.6), Color.ORANGE , 0, "pickaxe", false);
        }
        return null;
    }
    
	int amount = 1;
	int code;
	boolean stackable;
	boolean weapon;
	Color color;
	int damage;
	double width;
	double height;
	MyVector knockback;
	int count = 0;
	boolean draw = false;
	String name;
	boolean material;
	
	public Item(boolean stackable, int code, boolean weapon, int damage, double width, double height, MyVector knockback, Color color, int light, String name, boolean material) {
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
		return Body.fromCode(code, x, y);
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
