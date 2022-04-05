package org.godfreyslanding;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class Player extends Body {
	boolean canJump = true;
	boolean movingR = false;
	boolean movingL = false;
	boolean colidedR = false;
	boolean colidedL = false;
	boolean isInvinc = false;
	boolean stunned = false;
	double deceleration = .03;
	int invinc = 30;
	int spawnY = 0;
	int spawnX = 0;
	int deathClock = 0;
	boolean drawDied = false;
	Toolbar toolbar;
	Inventory inventory;
	double maxHealth;
	double lastX = 0.0;
	boolean lookingRight = true;
	Crafting craft;


	public Player(double x, double y, double width, double height, MyVector velocity, Color color, JFrame frame) {
		super(x, y, width, height, velocity, color, false, 100, 9, false, -1);
		toolbar = new Toolbar();
		inventory = new Inventory(toolbar, new Item[20]);
		maxHealth = 100.0;
		craft = new Crafting(frame, inventory);
	}

	
	public Inventory getInventory() {
		return inventory;
	}


	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	

	@Override
	public void colidesBlock(int side, Body b) {
		lastX = x;
			if(side == 3) {
				velocity.setY(0);
				y = (+b.height/2 + b.getY()) + height/2;
			}
			if(side == 1) {
				velocity.setY(0);
				y = (-b.height/2 + b.getY()) - height/2;
				canJump = true;
			}
			if(side == 2) {
				colidedR = true;
				velocity.setX(0);
				x = (-b.width/2 + b.getX()) - width/2;
				
			}
			if(side == 0) {
				colidedL = true;
				velocity.setX(0);
				x = (b.width/2 + b.getX()) + width/2;
				
			}
			if(side == 4) {
			}
	}
	
	public void jump() {
		if(canJump && !stunned) {
			velocity.setY(-.8);
			y += -.1;
			canJump = false;
		}
		
	}

	public void startRight() {
		if(!movingR) {
			movingR = true;
		}
	}

	public void startLeft() {
		if(!movingL) {
			movingL = true;
		}
	}

	public void stopRight() {
		movingR = false;

		
	}

	public void stopLeft() {
		movingL = false;
		
		
	}
	public double speed() {
		return(x - lastX);
	}


	public void setColidedR(boolean b) {
		colidedR = b;		
	}
	public void setColidedL(boolean b) {
		colidedL = b;		
	}

	public void xMovement() {
		if(!colidedR && movingR && !stunned) {
			velocity.setX(.2);
		} else if(!colidedL && movingL && !stunned) {
			velocity.setX(-.2); 
		} else if(!colidedL && !colidedR && !movingR && !movingL && velocity.getX() != 0) {
			if(Math.abs(velocity.getX()) > deceleration) {
				if(velocity.getX() < 0) {
				velocity.addX(deceleration);
				} else if(velocity.getX() > 0) {
					velocity.addX(-deceleration);
				}
			} else {
				velocity.setX(0);
			}
		}else {
			velocity.setX(0);
		}
	}
	
	public boolean isMovingR() {
		return movingR;
	}


	public void setMovingR(boolean movingR) {
		this.movingR = movingR;
	}


	public boolean isInvinc() {
		return isInvinc;
	}


	public void setStunned(boolean isInvinc) {
		this.isInvinc = isInvinc;
	}


	public void damage(int n) {
		health = health - n;
	}
	
	public void drawHUD(Graphics2D g, int width, int height) {
		//System.out.println(width + " " + height);
		Toolbar tb = this.toolbar;
		tb.draw(g, width, height);
		g.setColor(new Color(255, 215, 0));
		g.setStroke(new java.awt.BasicStroke(2));
		g.drawRect(width/2 - 111, 39 - height/2, 101, 21);
		g.setColor(new Color(220,20,60));
		g.fillRect(width/2 - 110, 40 - height/2 , (int)((health/maxHealth) * 100), 20);
		g.setColor(Color.WHITE);
		g.drawString(health +"/" + (int)maxHealth,width/2 -75 , 20 - height/2);
		
		
		if(drawDied) {
			g.setColor(color.black);
			g.drawString("You Died", 0, 0);
		}
	}


	public void drawItem(int x, int y, Graphics2D g, int width, int height) {
		Item item = getToolbar().getSelectedItem();
		
		if(item != null && item.draw) {
			g.setColor(item.getColor());
			int sX = x + (int)this.width*5 - (int)item.getWidth()*5;
			int sY = y + (int)this.height*5 - (int)item.getHeight()*5;
			
			int iX = sX - (int)this.width*5;
			if(lookingRight) {
				iX = sX + (int)this.width*5;
			}
			
			g.fillRect(iX, sY - (int)this.height *2, (int)item.getWidth() * 10, (int)item.getHeight() * 10);
			g.setColor(item.getColor());
		}
	}


	public Toolbar getToolbar() {
		return this.toolbar;
	} 
	
	
	@Override
	public void drawBody(Graphics2D g, int winW, int winH) {
		drawHUD(g, winW, winH);
		int x = (int) Math.round(10 * (-this.width / 2.0));
		int y = (int) Math.round(10 * (-this.height / 2.0));
		int w = (int) Math.round(10 * this.width);
		int h = (int) Math.round(10 * this.height);
		drawItem(x,y, g, winW, winH);
		Color c = g.getColor();
		g.setColor(color);
		g.fillRect(x, y, w, h);
		g.setColor(c);
	}


	@Override
	public void draw(Graphics2D g, int width, int height) {
		super.draw(g,width,height);
	}

	@Override
	public void update(boolean gravity, Time t) {
		if(isInvinc && invinc > 0 ) {
			if(invinc < 20 && stunned) {
				stunned = false;
			}
			invinc--;
			deceleration = .008;
		} else{
			isInvinc = false;
			invinc = 30;
			deceleration = .03;
		}
		
		if(health <= 0) {
			died();
		}
		if(gravity) {
			velocity.gravity();
		}
		
		xMovement();
		velocity.movement(this);
	}


	public void setSpawn(int spawnX, int spawnY) {
		this.spawnY = spawnY;
		this.spawnX = spawnX;
		
		
	}
	
	public void died() {
		drawDied = true;
		deathClock++;
		colidedR = true;
		colidedL = true;
		canJump = false;
		if(deathClock == 300) {
			drawDied = false;
			deathClock = 0;
			health = (int) maxHealth;
			x = spawnX;
			y = spawnY;
			System.out.println(x + " " + y);
			colidedR = false;
			colidedL = false;
			canJump = true;
		}
	}

	public boolean isLookingRight() {
		return lookingRight;
	}


	public void setLookingRight(boolean lookingRight) {
		this.lookingRight = lookingRight;
	}


	public void setItemSelected(int itemSelected) {
		this.toolbar.setItemSelected(itemSelected);
		
	}
	public boolean isHoldingWeapon() {
		return this.toolbar.isHoldingWeapon();
	}
	
	public boolean isHoldingStackable() {
		return this.toolbar.isHoldingStackable();
	}


	public Item getSelectedItem() {
		return this.toolbar.getSelectedItem();
	}


	public void removeSelectedItem() {
		this.toolbar.removeSelectedItem();
		
	}
	public Crafting getCraft() {
		return this.craft;
	}


	public boolean isHoldingPickAxe() {
		return this.toolbar.isHoldingPickAxe();
	}
}
