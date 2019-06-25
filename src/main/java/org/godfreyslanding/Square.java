package org.godfreyslanding;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

public class Square implements Sprite{
	int health;
	boolean rotation;
	List<Entity> found;
	Image img;
	int tick = 0;
	int x;
	int y;
	int vx;
	int vy;
	int stlAni;
	boolean jumpUsed;
	Color color;
	int gravity;
	int friction;
	JFrame j;
	Mover move;
	Jumper jump;
	public Square() throws IOException {
		stlAni = 0;
		health = 50;
		rotation = false;
		img = ImageIO.read(ClassLoader.getSystemResource("images/Player20x20.png"));
		found = new ArrayList();
		jumpUsed = false;
		friction = vx/4;
		x = 400;
		y = 200; 
		vx = 0;
		vy = 0;
		color = Color.red;
		gravity = 1;
		move = new Mover();
		jump = new Jumper();
	}
	public Mover getMover() {
		return move;
	}
	public Jumper getJumper() {
		return jump;
	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, 20  , 20);
	}
	
	public void update(World w) throws IOException {
		vy += gravity;
		vx -= friction;
		if(vx == 0) {
			stlAni++;
		} else if(vx != 0){
			img = ImageIO.read(ClassLoader.getSystemResource("images/Player20x20.png"));
			stlAni = 0;
		}
		List<Entity> inter = w.intersects(this);
		if (inter.size() > 0) {
			for(int i = 0; i < inter.size(); i++) {
				Entity e = inter.get(i);
				Rectangle sect = e.getBounds().intersection(this.getBounds());
				if(e.getY() > y) {
					vy = vy/2;
					y = e.getY() - 20;
					gravity = 0;
					jumpUsed = false;
				}
				else if(e.getX() > x) {
					System.out.print(x);
					vx = -vx;
					if(x + 20 < e.getX()) {
						x = e.getX() + 20;
					} else if(getX() < x) {
						x = getX();
					}
				}
			}
		}
		jump(jump);
		move(move);
		jump.Update();
		if( vx > 7) {
			vx = 7;
		} else if(vx < -7) {
			vx = -7;
		}
		
		/* For the corner method:
		0 = top right corner
		1 = top left corner
		2 = bottom right corner
		3 = bottom left corner
		
		Note, in order for the getCorner method to work, all bounds 
		must have the x be in the top right corner */
	
				
			/*int[] closestCorners = checkFourCorners(e);
			int thisEntityCorner = closestCorners[1];
			int oEntityCorner = closestCorners[0];
			switch(oEntityCorner) {
				case 0:
					switch(thisEntityCorner) {
					case 0: vy = -vy;
							break;
					case 1: vy = -vy;
							break;
					case 2: vx = -vx;
							break;
					case 3: vx = -vx;
							break;
					}
					break;
				case 1:
					switch(thisEntityCorner) {
					case 0: vy = -vy;
							break;
					case 1: vy = -vy;
							break;
					case 2: vx = -vx;
							break;
					case 3: vx = -vx;
							break;
					}
					break;
				case 2:
					switch(thisEntityCorner) {
					case 0: vx = -vx;
							break;
					case 1: vx = -vx;
							break;
					case 2: vy = -vy;
							break;
					case 3: vy = -vy;
							break;
					}
					break;
				case 4:
					switch(thisEntityCorner) {
					case 0: vy = -vy;
							break;
					case 1: vy = -vy;
							break;
					case 2: vx = -vx;
							break;
					case 3: vx = -vx;
							break;
					}
					break;
			} 
			x += vx;
			y += vy;
			
				
		
			if(isOnXAxis(w.intersects(this).get(0))) {
				vx = -vx;
				
			} else if(isOnYAxis(w.intersects(this).get(0))) {
				vy = -vy;
				
			} else {
				if(isOnXAxis(w.intersects(this).get(1))) {
					vx = -vx;
					
				} else if(isOnYAxis(w.intersects(this).get(1))) {
					vy = -vy;
				}
			} 
		} */
		if(stlAni >= 10 && stlAni < 20) {
			img = ImageIO.read(ClassLoader.getSystemResource("images/PlayerStill20x20.png"));
		} else if(stlAni >= 20) {
			img = ImageIO.read(ClassLoader.getSystemResource("images/Player20x20.png"));
			stlAni = 0;
		}
		x += vx;
		y += vy;
		if(vx == 0) {
			friction = 0;
		} else{
			friction = vx/32;
		}
		
		if(friction == 0) {
			if(vx < 0) {
				friction = -1;
			}
			else if(vx > 0) {
				friction = 1;
			}
		}
			
		if (x <= 0 || 780 <= x) { 
			vx = -vx;
			if(x < 0) {
				x = 0;
			} else if(780 < x) {
				x = 780;
			}
		}
		
		if (680 <= y) {
			vy = -vy/2;
			y = 680;
			gravity = 0;
			jumpUsed = false;
		}
		else {
			gravity = 1;
		}
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
			vy = -10;
			jumpUsed = !jumpUsed;
			stlAni = 0;
		}
	}
	
	public void move(Mover l) {
		if(l.getVal() == 1) {
			rotation = false;
			vx += 2;
			stlAni = 0;
		} else if(l.getVal() == 2) {
			rotation = true;
			vx -= 2;
			stlAni = 0;
		}
	}
	
	
	
	
	
 
	
	
	
	public double[][] getSideCenters(Rectangle rect) {
		double[][] corners = new double[4][2];
		double[] tRRect = {rect.getX(), rect.getY()};
		double[] tLRect = {(rect.getX() + rect.getWidth()), rect.getY()};
		double[] bRRect = {rect.getX(),(rect.getY() + rect.getHeight())};
		double[] bLRect = {(rect.getX() + rect.getWidth()), (rect.getY() + rect.getHeight())};
		corners[0] = tRRect;
		corners[1] = tLRect;
		corners[2] = bRRect;
		corners[3] = bLRect;
		return corners;
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
