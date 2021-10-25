package org.godfreyslanding;

public abstract class Biome {
	int x;
	int y;
	int width;
	int height;
	Entity[] monsters;
	int priority;
	public Biome(int x, int y, int width, int height, int priority) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		// TODO Ask Andrew monsters is not passed in this assigning the field to iteslf
		this.monsters = monsters;
		this.priority = priority;
		
	}
	
	public abstract SpawnPackage getPackage(Player p, Time t, double x, double y);
	
	public boolean containsBody(Body b) {
		boolean isInX = x < (int)b.getX()/2 && (int)b.getX()/2 <= x+width;
		boolean isInY = y < (int)b.getY()/2 && (int)b.getY()/2 <= y+height;
		return isInX && isInY;
	}
	
	
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public Body getSkyBlock(double x,double y) {
		return new Sky(x,y);
	}
	
	
	
}
