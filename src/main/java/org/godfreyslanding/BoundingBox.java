package org.godfreyslanding;

public class BoundingBox {
	int leftEdge;
	int rightEdge;
	int top;
	int bottom;
	int size;
	public BoundingBox(int size, int x, int y) {
		this.size = size; 
		update(x,y);
	}
	
	public void update(int x,int y) {
		leftEdge = (int)(x - size);
		rightEdge = (int)(x + size+1);
		top = (int)(y - size);
		bottom = (int)(y + size+1);
		if(top < 0) { top = 0;}
		if(leftEdge < 0) { leftEdge = 0;}
		
	}
	
	public void correct(Body[][] blocks) {
		if(rightEdge >= blocks.length) { rightEdge = blocks.length - 1;}
		if(bottom >= blocks[0].length) { bottom = blocks[0].length - 1;}
	}
	
	public int getLeftEdge() {
		return leftEdge;
	}
	public void setLeftEdge(int leftEdge) {
		this.leftEdge = leftEdge;
	}
	public int getRightEdge() {
		return rightEdge;
	}
	public void setRightEdge(int rightEdge) {
		this.rightEdge = rightEdge;
	}
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	public int getBottom() {
		return bottom;
	}
	public void setBottom(int bottom) {
		this.bottom = bottom;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	
	
}
