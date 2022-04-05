package org.godfreyslanding;

import java.awt.Color;

public class Entity extends Body {
	boolean stunned = false;
	int damage;
	MyVector knockback;
	Player p;
	public Entity(double x, double y, double width, double height, MyVector velocity, Color color,int health, int damage, MyVector knockback, int light, Player p) {
		super(x, y, width, height, velocity, color,false, health, light,false, -1);
		this.knockback = knockback;
		this.damage = damage;
		this.p = p;
		// TODO Auto-generated constructor stub
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public MyVector getKnockback() {
		return knockback;
	}
	public void setKnockback(MyVector knockback) {
		this.knockback = knockback; 
	}
	
	public boolean isStunned() {
		return stunned;
	}

	public void setStunned(boolean stunned) {
		this.stunned = stunned;
	}
	
	public Player getP() {
		return p;
	}

	public void setP(Player p) {
		this.p = p;
	}

}
