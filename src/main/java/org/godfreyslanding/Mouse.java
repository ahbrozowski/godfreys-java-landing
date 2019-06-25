package org.godfreyslanding;

import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse extends Frame implements MouseListener {

	int x;
	int y;
	int button;
	public Mouse(){
		x = 1000;
		y = 1000;
		button = 0;
	}
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getButton(){
		return button;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == 1) {
			x = e.getX();
			y = e.getY() - 25;
			button = 1;
		}
		if(e.getButton() == 3) {
			x = e.getX();
			y = e.getY() - 25;
			button = 3;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	public void update() {
		button = 0;
		x = 1000;
		y = 1000;
	}

}
