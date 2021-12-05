package org.godfreyslanding.older;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Mover extends JFrame implements KeyListener {
	int val;
	Mover(){
		val = 0;
	}
	public int getVal() {
		return val;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_D){
            System.out.println("Key Pressed" + e.getKeyCode());
            val = 1;       
        }
        
        if(e.getKeyCode() == KeyEvent.VK_A){
            System.out.println("Key Pressed" + e.getKeyCode());
            val = 2;       
        }
    }

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_D){
            System.out.println("Key Pressed" + e.getKeyCode());
            val = 0;       
        }
		if(e.getKeyCode() == KeyEvent.VK_A){
            System.out.println("Key Pressed" + e.getKeyCode());
            val = 0;       
        }
		
	}
}
