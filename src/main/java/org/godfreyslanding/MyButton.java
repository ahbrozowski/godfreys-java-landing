package org.godfreyslanding;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;

import javax.swing.JFrame;

public class MyButton {
	
	int height;
	int width;
	int x;
	int y;
	boolean mouseOver = false;
	Color color;
	int clickedAni = 0;
	int percentDarken = 0;
	JFrame frame; 
	public MyButton(int x, int y, int width, int height, Color color,JFrame frame) {
		super();
		this.frame = frame;
		this.height = height;
		this.width = width;
		this.x = x;
		this.y = y;
		this.color = color;
		
	}
	
	public void update() {
		Point p = MouseInfo.getPointerInfo().getLocation();
		Point f = frame.getLocation();
		int top = frame.getInsets().top;
		double px = p.getX() - f.getX();
		double py = p.getY() - f.getY() - top;
		boolean fitsX = px >= x && px <= (x+width);
		boolean fitsY = py >= y && py <= (y+height);
		
		mouseOver = fitsX && fitsY;
	}
	
	public boolean clicked() {
		this.update();
		if(mouseOver) {
			clickedAni = 40;
		}
		return mouseOver;
		
	}
	public Color darken(int percent) {
		
		int rGradiant = color.getRed() * (100 - percent)/100;
		int gGradiant = color.getGreen() * (100 - percent)/100;
		int bGradiant = color.getBlue() * (100 - percent)/100;
		return new Color(rGradiant, gGradiant, bGradiant);
	} 
	public void draw(Graphics2D g) {
		percentDarken = 0;
		this.update();
		if(mouseOver) {
			increasePD(20);
		}
		if(clickedAni > 0) {
			increasePD(clickedAni);
			clickedAni--;
		} 
		if(percentDarken < 0) {percentDarken = 0;}
		g.setColor(darken(percentDarken));
		g.fillRect(x, y, width, height);
	}
	

	public void increasePD(int p) {
		if(p > percentDarken) {
			percentDarken = p;
		}
			
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
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

	
	
	
	
	
	
	
}
