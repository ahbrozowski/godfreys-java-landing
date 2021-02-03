package org.godfreyslanding;

public class Vector {
	double x;
	double y;
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	
	public void xReflect() {
		x = -x;
	}
	public void yReflect() {
		y = -y;
	}
	
	public void addVector(Vector v) {
		this.x += v.getX();
		this.y += v.getY();
	}
	
	public void accelerate(int x,int y) {
		this.x = this.x+x;
		this.y = this.y+y;
	}
	
	public void movement(Body body) {
		double x = body.getX();
		double y = body.getY();

		body.setX(x + this.x);
		body.setY(y + this.y);
	}
	
	public void gravity() {
		Vector v = new Vector(0,.03);
		
		this.addVector(v);
		
	}
	
	public void addX(double d) {
		x = x + d;
	}
	public void addY(double d) {
		y = y + d;
	}
	
	public double getX() {
		return x;
	}
	public void setX(double d) {
		this.x = d;
	}
	public double getY() {
		return y;
	}
	public void setY(double d) {
		this.y = d;
	};
	@Override
	public String toString() {
		return "Vector [x=" + x + ", y=" + y + "]";
	}
	
	
}
