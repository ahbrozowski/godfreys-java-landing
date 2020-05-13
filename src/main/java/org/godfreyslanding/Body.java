package org.godfreyslanding;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Body {
	double x;
	double y;
	double width;
	double height;
	Vector velocity;
	static boolean theFix;
	Color color;
	boolean air;
	int health;
	int code;
	public Body(double x, double y, double width, double height, Vector velocity, Color color, Boolean air, int health) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.velocity = velocity;
		theFix = false;
		this.color = color;
		this.air = air;
		this.health = health;
	}
	

	public int getHealth() {
		return health;
	}


	public void setHealth(int health) {
		this.health = health;
	}


	public boolean getAir() {
		return air;
	}


	public void setAir(boolean air) {
		this.air = air;
	}


	public boolean collisionCheck(Body body) {
		double xDist = Math.abs(this.x - body.getX());
		double yDist = Math.abs(this.y - body.getY());
		double xMax = Math.abs(this.width/2 + body.getWidth()/2);
		double yMax = Math.abs(this.height/2 + body.getHeight()/2);
		//System.out.println(xDist + " " + yDist + " " + xMax + " " + yMax);
		if(xDist <= xMax && yDist <= yMax) {
			return true;
		} else{
			theFix = false;
			return false;
		}
		
	}
	
	public int collisionSide(Body body) {
		double xDist = this.x - body.getX();
		double yDist = -(this.y - body.getY());
		double xMax = Math.abs(this.width/2 + body.getWidth()/2);
		double yMax = Math.abs(this.height/2 + body.getHeight()/2);
		
		double angle = (float) Math.atan2(yDist, xDist); 
		double brAngle = (float) Math.atan2(-yMax, xMax);
		double blAngle = (float) Math.atan2(-yMax, -xMax);
		double tlAngle = (float) Math.atan2(yMax, -xMax);
		double trAngle = (float) Math.atan2(yMax, xMax);
		int placement = 4;
		//0 means the collision happened on the right of the object
		//1 means the collision happened on the top of the object
		//2 means the collision happened on the left of the object
		//3 means the collision happened on the bottom of the object
		//System.out.print(angle + " " + trAngle + " " + tlAngle + " " + blAngle + " " + brAngle);
		if(angle < trAngle && angle > brAngle) {
			placement = 0;
		}
		else if(angle < tlAngle && angle > trAngle) {
			placement = 1;
		}
		else if((angle > tlAngle && angle <= 3.1415928) || (angle > -3.1415928 && angle < blAngle)) {
			placement = 2;
		}
		else if(angle < brAngle && angle > blAngle) {
			placement = 3;
		} else {
			placement = 4;
			
			
		}
		return placement;		
		
	}

	public void update(boolean gravity) {
		if(gravity) {
			velocity.gravity();
		}
		velocity.movement(this);
	}
	
	public void drawHUB(Graphics2D g, int width,int height) {
		
	}
	
	public void draw(Graphics2D g, int tabW, int tabH) {
		AffineTransform old = g.getTransform();
		try {
			g.translate(10 * x, 10 * y);
			// g.rotate(angle);
			drawHUB(g, tabW, tabH);
			int x = (int) Math.round(10 * (-this.width / 2.0));
			int y = (int) Math.round(10 * (-this.height / 2.0));
			int w = (int) Math.round(10 * width);
			int h = (int) Math.round(10 * height);
			Color c = g.getColor();
			g.setColor(color);
			g.fillRect(x, y, w, h);
			g.setColor(c);

		} finally {
			g.setTransform(old);
		}
	}
	
	public double getX() {
		return x;
	}

	public void setX(double f) {
		this.x = f;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
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

	public void setHeight(double hight) {
		this.height = hight;
	}


	public Vector getVelocity() {
		return velocity;
	}
	

	public void setVelocity(Vector velocity) {
		this.velocity = velocity;
	}
	public void setColor( Color color) {
		this.color = color;
	}
	public Item item(int n) {
		Item p = new Placeable(true);
		p.setAmount(n);
		return p;
	}
}
