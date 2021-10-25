package org.godfreyslanding;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Sword extends Item {
	static Vector knockback = new Vector(.4,-.4); 
	public Sword() {
		super(false, 3, true, 10, 3, 3, knockback, Color.LIGHT_GRAY, 0, "sword", false);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void useItem(Entity e, double x, double y, Graphics2D g, boolean lr) {
		boolean colided = this.colidsEntity(e, x, y);
		if(colided) {
			e.setHealth(e.getHealth()-damage);
			e.getVelocity().addVector(knockback);
			e.setStunned(true);
  			if(!lr) {
  				e.getVelocity().xReflect();
  			}
	
		}
	}
	

	@Override
	public boolean colidsEntity(Entity e, double x, double y) {
		double xDist = Math.abs(x - e.getX());
		double yDist = Math.abs(y - e.getY());
		double xMax = Math.abs(this.width/2 + e.getWidth()/2);
		double yMax = Math.abs(this.height/2 + e.getHeight()/2);
		if(xDist <= xMax && yDist <= yMax) {
			return true;
		} else {
			return false;
		}
	}


}
