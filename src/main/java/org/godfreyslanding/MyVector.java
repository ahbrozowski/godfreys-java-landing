package org.godfreyslanding;

public class MyVector {
	double x;
	double y;
	public MyVector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	
	public void xReflect() {
		x = -x;
	}
	public void yReflect() {
		y = -y;
	}
	
	public void addVector(MyVector v) {
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
		MyVector v = new MyVector(0,.03);
		
		this.addVector(v);
	}
	
	public MyVector floor() {
		return new MyVector(Math.floor(this.x), Math.floor(this.y));
	}
	
	public MyVector add(MyVector v) {
		return new MyVector(this.x + v.getX(), this.y + v.getX());
	}
	public MyVector sub(MyVector v) {
		return new MyVector(this.x - v.getX(), this.y - v.getX());
	}
	
	public double dot(MyVector v) {
		return this.x * v.getX() + this.y * v.getX();
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
	}
	@Override
	public String toString() {
		return "Vector [x=" + x + ", y=" + y + "]";
	}
	
	
}
