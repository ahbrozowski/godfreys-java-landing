package org.godfreyslanding;

import java.awt.Color;

public class Zombie extends Entity {
	static Color color = new Color(160, 192, 144);
	boolean canJump = true;
	boolean stop = false;
	int stunCount = 0;
	boolean cantBeUnstunned = false;
	public Zombie(double x, double y, Player p) {
		super(x, y, 2, 2, new Vector(0,0), color, 50, 10, new Vector(.5,-.6), 3, p);
		// TODO Auto-generated constructor stub
	}
	
	


	public void lookForPlayer() {
		double pX = p.getX();
		double pY = p.getY();
		if(canJump && stop) {
			velocity.setY(-.8);
			y = y - .01;
			canJump = false;
			
		}
		if(pX < x) {
			if(!stop && velocity.getX() != -.1) {
				velocity.setX(-.1);
				x = x - .01;
			}
		} else {
			if(!stop && velocity.getX() != .1) {
				velocity.setX(.1);
				x = x + .01;
			}
		}
		
		
	}
	
	@Override
	public void colidesBlock(int side, Body b) {
			if(side == 3) {
				velocity.setY(0);
				y = (+b.height/2 + b.getY()) + height/2;
			}
			if(side == 1) {
				velocity.setY(0);
				y = (-b.height/2 + b.getY()) - height/2;
				canJump = true;
				if(!cantBeUnstunned) {
					stunCount = 10;
					stunned = false;
				}
			}
			if(side == 2) {
				velocity.setX(0);
				x = (-b.width/2 + b.getX()) - width/2;
				stop = true;
				
			}
			if(side == 0) {
				velocity.setX(0);
				x = (b.width/2 + b.getX()) + width/2;
				stop = true;
				
			}
			if(side == 4) {
			}
	}
	
	@Override
	public void update(boolean gravity,Time t) {
		super.update(gravity,t);
		this.lookForPlayer();
		stop = false;
		if(stunned) {
			stop = true;
			stunCount--;
		}
		
		if(stunCount > 1) {
			cantBeUnstunned= true;
		} else {
			cantBeUnstunned= false;
		}
	}




	@Override
	public String toString() {
		return "Zombie [canJump=" + canJump + ", stop=" + stop + ", stunned=" + stunned + ", damage=" + damage
				+ ", x=" + x + ", y=" + y+ ", velocity=" + velocity + "]";
	}


}
