package org.godfreyslanding;

public class BoundingRect {
	int leftEdge;
	int rightEdge;
	int top;
	int bottom;
	int width;
	int height;
	public BoundingRect(int width, int height, int x, int y) {
		this.width = width; 
		this.height = height;
		update(x,y);
	}
	
	public void update(int x,int y) {
		leftEdge = (int)(x - width/2);
		rightEdge = (int)(x + width/2+1);
		top = (int)(y - height/2);
		bottom = (int)(y + height/2+1);
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
	
	
	
	
}