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
	int light;
	Shadow s;
	boolean shadow = false;
	boolean gl;
	BoundingBox bBox;
	public Body(double x, double y, double width, double height, Vector velocity, Color color, Boolean air, int health, int light, boolean gl) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.velocity = velocity;
		theFix = false;
		this.color = color;
		this.air = air;
		this.health = health;
		this.light = light;
		s = new Shadow(x,y, 8);
		this.gl = gl;
		bBox = new BoundingBox(5,(int)(this.x/2),(int)(this.y/2));
	}
	

	public boolean isGl() {
		return gl;
	}


	public void setGl(boolean gl) {
		this.gl = gl;
	}


	public int getLight() {
		return light;
	}


	public void setLight(int light) {
		this.light = light;
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
	
	public double calcDist(Body b) {
		double xDist = Math.abs(this.x - b.getX());
		double yDist = Math.abs(this.y - b.getY());
		double hDist = Math.sqrt((Math.pow(xDist, 2) + Math.pow(yDist, 2) ));
		return hDist;
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

	public void update(boolean gravity, Time t) {
		if(gravity) {
			velocity.gravity();
		}
		velocity.movement(this);
	}
	
	
	public void draw(Graphics2D g, int tabW, int tabH) {
		shadow = false;
		AffineTransform old = g.getTransform();
		try {
			g.translate(10 * this.x, 10 * this.y);
		// g.rotate(angle);
			drawBody(g, tabW, tabH);
				
		} finally {
			g.setTransform(old);
		}
	}


	public void drawBody(Graphics2D g, int winW, int winH) {
		int x = (int) Math.round(10 * (-this.width / 2.0));
		int y = (int) Math.round(10 * (-this.height / 2.0));
		int w = (int) Math.round(10 * this.width);
		int h = (int) Math.round(10 * this.height);
		Color c = g.getColor();
		g.setColor(color);
		g.fillRect(x, y, w, h);
		g.setColor(c);
	}
	
	public void drawShadow(Graphics2D g) {
			s.draw(g);
			shadow = true;
	}
	public double getX() {
		return x;
	}

	public int getOpacity() {
		return s.getOpacity();
	}
	public void setOpacity(int o) {
		s.setOpacity(o);
	}


	public boolean isShadow() {
		return shadow;
	}


	public void setShadow(boolean shadow) {
		this.shadow = shadow;
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


	public void colidesBlock(int side, Body b) {
		// TODO Auto-generated method stub
		
	}
	public BoundingBox getBBox() {
		bBox.update((int)(this.x/2),(int)(this.y/2));
		return bBox;
	}
	
}
