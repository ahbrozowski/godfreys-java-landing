package org.godfreyslanding.old;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Jumper extends JFrame implements KeyListener {
	boolean jump;
	boolean left;
	boolean right;
	Jumper(){
		jump = false;
		
	}
	public boolean getJump(){
		return jump;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
            System.out.println("Key Pressed" + e.getKeyCode());
            jump = true;;       
        }

    }

	@Override
	public void keyReleased(KeyEvent e) {
			
	}
	public void Update() {
		jump = false;
		left = false;
		right = false;
	}

}
