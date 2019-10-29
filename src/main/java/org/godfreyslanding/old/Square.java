package org.godfreyslanding.old;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.vecmath.Vector2d;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

public class Square implements Sprite{
	int maxSpeed;
	Rectangle bounds;
	Rectangle surround;
	Vector2d xBounce;
	Vector2d yBounce;
	int health;
	boolean rotation;
	List<Entity> found;
	Image img;
	int tick = 0;
	int x;
	int y;
	int lastX;
	int lastY;
	int vx;
	int vy;
	Vector2d v;
	int stlAni;
	boolean jumpUsed;
	Color color;
	Vector2d gravity;
	Vector2d friction;
	JFrame j;
	Mover move;
	Jumper jump;
	private boolean pause;
	public Square() throws IOException {
		maxSpeed = 5;
		stlAni = 0;
		health = 50;
		rotation = false;
		img = ImageIO.read(ClassLoader.getSystemResource("images/Player20x20.png"));
		found = new ArrayList();
		jumpUsed = false;
		friction = new Vector2d();
		x = 400;
		y = 200; 
		lastX = x;
		lastY = y;
		vx = 0;
		vy = 0;
		bounds = new Rectangle(x, y, 20  , 20);
		surround = new Rectangle(x,y,50,50);
		v = new Vector2d(vx,vy);
		color = Color.red;
		gravity = new Vector2d(0,1);
		move = new Mover();
		jump = new Jumper();
		pause = false;
	}
	public Mover getMover() {
		return move;
	}
	public Jumper getJumper() {
		return jump;
	}
	public Rectangle getBounds() {
		//return new Rectangle(x, y, 20  , 20);
		return bounds;
	}
	
	public void update(World w) throws IOException {
		tick++;
		if(tick  > 0) {
			System.out.println(v.getY() + "       " +y);
			
		}
		double vel = v.getX();
		double fricForce = -vel/10;
		if(fricForce < .1 && fricForce > -.1) {
			fricForce = -v.getX();
		}
		friction.setX(fricForce);
		v.add(gravity);
		v.add(friction);
		if(v.getX() > maxSpeed) {
			v.setX(maxSpeed);
		}
		if(v.getX() < -maxSpeed) {
			v.setX(-maxSpeed);
		}
		if(v.getX() == 0) {
			stlAni++;
		} else if(v.getX() != 0){
			img = ImageIO.read(ClassLoader.getSystemResource("images/Player20x20.png"));
			stlAni = 0;
		}
		if( vx > 7) {
			vx = 7;
		} else if(vx < -7) {
			vx = -7;
		}
		
		
		if(stlAni >= 10 && stlAni < 20) {
			img = ImageIO.read(ClassLoader.getSystemResource("images/PlayerStill20x20.png"));
		} else if(stlAni >= 20) {
			img = ImageIO.read(ClassLoader.getSystemResource("images/Player20x20.png"));
			stlAni = 0;
		}
		lastX = x;
		lastY = y;
		x += v.getX();
		y += v.getY();
		
		if (x <= 0 || 780 <= x) { 
			v.sub(xBounce);
			if(x < 0) {
				x = 0;
			} else if(780 < x) {
				x = 780;
			}
		}
		
		if (680 <= y) {
			v.sub(yBounce);
			y = 680;
			gravity.setY(0);
			jumpUsed = false;
		}
		else {
			gravity.setY(1);
		}
		
		List<Entity> inter = w.intersects(this);
		if (inter.size() > 0) {
			collisions(inter);
			
			
		}
		jump(jump);
		move(move);
		jump.Update();
		xBounce = new Vector2d(v.getX(),0);
		yBounce = new Vector2d(0,(int)v.getY() * 1.5);
		
		surround.setLocation(x, y);
		bounds.setLocation(x, y);
	}
	public void draw(Graphics2D g) {
		/*g.setColor(color);
		g.fillRect(x, y, 20, 20); */
		if(rotation) {
			g.drawImage(img, x + 20, y, -20, 20, null);
		} else {
			g.drawImage(img, x, y, null);
		}
	}
	
	public void jump(Jumper l) {
		if(l.getJump() && !jumpUsed) {
			v.setY(-10);
			jumpUsed = !jumpUsed;
			stlAni = 0;
			
		}
	}

	public void move(Mover l) {
			if(l.getVal() == 1) {
				rotation = false;
				v.setX(v.getX()+1);
				stlAni = 0;
			}else if(l.getVal() == 2) {
				rotation = true;
				v.setX(v.getX()-1);
				stlAni = 0;
			}
		}
	
	
	

 
	
	public void collisions(List<Entity> inter) {
		jumpUsed = false;
		int dist = 1000;
		Entity e = null;
		for(int i = 0; i < inter.size(); i++) {
			if(Math.abs((x+y) - (inter.get(i).getX()+inter.get(i).getY())) < dist){
				dist = Math.abs((x+y) - (inter.get(i).getX()+inter.get(i).getY()));
				e = inter.get(i);
			}
		}
		if(lastY <= e.getY()) {
			jumpUsed = false;
			v.setY(0);
			gravity.setY(0);
			y = e.getY() - 20;
		}
	}
	public void onTopOf(Entity e) {
		System.out.print("running");
		if(x < e.getX()) {
			x = e.getX() - 20;
		}
		if(x > e.getX()) {
			x = e.getX() + 10;
		}
	}
	
	public double[][] getSideCenters(Rectangle rect) {
		double[][] sides = new double[4][2];
		double[] top = {rect.getX() + rect.getWidth()/2, rect.getY()};
		double[] bottom = {(rect.getX() + rect.getWidth()/2), rect.getY() + rect.getHeight()};
		double[] right = {rect.getX()+ rect.getWidth(),rect.getY()+ rect.getHeight()/2};
		double[] left = {rect.getX(), (rect.getY() + rect.getHeight()/2)};
		sides[0] = top;
		sides[1] = bottom;
		sides[2] = right;
		sides[3] = left;
		return sides;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	public boolean clickedOn(int x, int y) {
		return false;
	}
}
