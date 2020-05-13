package org.godfreyslanding;

 import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class World {
	Body[][] blocks;
	Player myPlayer;
	int width;
	int height;
	boolean breaking = false;
	boolean placing = false;
	public World(WorldData w) {
		blocks = w.getBlocks();
		Vector v2 = new Vector(0,0);
		int spawnY = w.getSpawnY();
		myPlayer = new Player(1000,spawnY,2,2,v2, Color.BLUE, false);
		Item p = new StoneP();
		p.setAmount(100);
		myPlayer.getInventory().addItem(p);
		myPlayer.setSpawn(1000, spawnY);
		System.out.print(spawnY);
		//System.out.print(blocks[500][spawnY].getAir());
	}

	public void update(float t) {
		//System.out.println(myPlayer.getX() + " " + myPlayer.getY());
		myPlayer.setColidedR(false);
		myPlayer.setColidedL(false);
		boolean gravity = true;
		int leftEdge = (int)(myPlayer.getX()/2 - 5);
		int rightEdge = (int)(myPlayer.getX()/2 + 6);
		int top = (int)(myPlayer.getY()/2 - 5);
		int bottom = (int)(myPlayer.getY()/2 + 6);
		if(leftEdge < 0) { leftEdge = 0;}
		if(rightEdge >= blocks.length) { rightEdge = blocks.length - 1;}
		if(top < 0) { top = 0;}
		if(bottom >= blocks[0].length) { bottom = blocks[0].length - 1;}
		for(int x = leftEdge; x < rightEdge; x++) {
			for(int y = top; y < bottom; y++) {
				Body body = blocks[x][y];
				if(!body.getAir() && myPlayer.collisionCheck(body)) {
					int side = myPlayer.collisionSide(body);
					if(side == 1) {
						gravity = false;
						if(myPlayer.getVelocity().getY() > 2) {
							System.out.println(myPlayer.getVelocity().getY());
							myPlayer.damage((int)(30 *(myPlayer.getVelocity().getY() - 2)));
						}
					}
					if(side == 2 || side == 0) {
						//System.out.println(side);
					}
					body.update(false);
					myPlayer.colidesBlock(side, body);
				}
					
			}
		} 
		if(breaking) {
			breakBlock();
		}
		if(placing) {
			placeBlock();
		}
		
		if(myPlayer.getVelocity().getY() >= 1) {
			phaseGuard();
		}
		myPlayer.update(gravity);
	}
	
	public void draw(Graphics2D g, int width, int height) {
		this.width = width;
		this.height = height;
		AffineTransform old = g.getTransform();
		double x = myPlayer.getX();
		double y = myPlayer.getY();
		
		g.translate((width/2)-10*x, (height/2)-10*y);
		
		int leftEdge = (int)(myPlayer.getX()/2)- (width/40)-3;
		int rightEdge = (int)(myPlayer.getX()/2) + (width/40)+3;
		int top = (int)(myPlayer.getY()/2 - (height/40)-3);
		int bottom = (int)(myPlayer.getY()/2 + (height/40)+3);
		if(leftEdge < 0) { leftEdge = 0;}
		if(rightEdge >= blocks.length) { rightEdge = blocks.length - 1;}
		if(top < 0) { top = 0;}
		if(bottom >= blocks[0].length) { bottom = blocks[0].length - 1;}
		for(int i = leftEdge; i < rightEdge; i++) {
			for(int j = top; j < bottom; j++) {
				Body body = blocks[i][j];
				body.draw(g,width,height);
			}
		} 
		//System.out.println(leftEdge + " " + rightEdge + " " + top + " " + bottom);
		/* x for(Body[] block: blocks){
			for(Body body: block){
				body.draw(g);
			}
		} */ 
		myPlayer.draw(g,width,height);
		g.setTransform(old);
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
           // System.out.println("Key Pressed" + e.getKeyCode());
            myPlayer.jump();      
        }
		
        if(e.getKeyCode() == KeyEvent.VK_D){
           // System.out.println("Key Pressed" + e.getKeyCode());
            myPlayer.startRight();
        }
        
        if(e.getKeyCode() == KeyEvent.VK_A){
            //System.out.println("Key Pressed" + e.getKeyCode());
            myPlayer.startLeft();
        }
        if(e.getKeyCode() == KeyEvent.VK_1){
        	myPlayer.inventory.setItemSelected(0);
        }
        if(e.getKeyCode() == KeyEvent.VK_2){
        	myPlayer.inventory.setItemSelected(1);
        }
        if(e.getKeyCode() == KeyEvent.VK_3){
        	myPlayer.inventory.setItemSelected(2);
        }
        if(e.getKeyCode() == KeyEvent.VK_4){
        	myPlayer.inventory.setItemSelected(3);
        }
        if(e.getKeyCode() == KeyEvent.VK_5){
        	myPlayer.inventory.setItemSelected(4);
        }
        if(e.getKeyCode() == KeyEvent.VK_6){
        	myPlayer.inventory.setItemSelected(5);
        }
        if(e.getKeyCode() == KeyEvent.VK_7){
        	myPlayer.inventory.setItemSelected(6);
        }
        if(e.getKeyCode() == KeyEvent.VK_8){
        	myPlayer.inventory.setItemSelected(7);
        }
        if(e.getKeyCode() == KeyEvent.VK_9){
        	myPlayer.inventory.setItemSelected(8);
        }
        if(e.getKeyCode() == KeyEvent.VK_0){
        	myPlayer.inventory.setItemSelected(9);
        }
        if(e.getKeyCode() == KeyEvent.VK_K){
        	myPlayer.damage(100);
        }
	
	}
	
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_D){
           // System.out.println("Key Released" + e.getKeyCode());
            myPlayer.stopRight();
        }
		
		if(e.getKeyCode() == KeyEvent.VK_A){
           // System.out.println("Key Released" + e.getKeyCode());
            myPlayer.stopLeft();
        }
	}

	public void mousePressed(MouseEvent e) {
		if(e.getButton() == 3) {
			placing = true;
		}
		if(e.getButton() == 1) {
			breaking = true;
		}
	}


	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == 1) {
			breaking = false;
		}
		
		if(e.getButton() == 3) {
			placing = false;
		}
	}
	
	
	public void breakBlock() {
		Point p = MouseInfo.getPointerInfo().getLocation();
		int x = (p.x + 12)/10 + (int)myPlayer.getX() - width/20;
		int y = (p.y - 35)/10 + (int)myPlayer.getY() - height/20;
		boolean xfits = (x/2 >= 0) && (x/2 < blocks.length);
		boolean yfits = (y/2 >= 0) && (y/2 < blocks[0].length);
		//System.out.println(x + " " + y);
		if(xfits && yfits) {
			Body b = blocks[x/2][y/2];
			if(!b.getAir()) {
				if(b.getHealth() == 0) {
					b.setAir(true);
					b.setColor(Color.CYAN);
					myPlayer.getInventory().addItem(b.item(1));
				} else {
					b.setHealth(b.getHealth() - 1);
				}
			}
		}	
	}
	
	public void placeBlock() {
		Point p = MouseInfo.getPointerInfo().getLocation();
		int x = (p.x)/10 + (int)(myPlayer.getX() + 1) - width/20;
		int y = (p.y-20)/10 + (int)(myPlayer.getY() - 1) - height/20;
		boolean xfits = (x/2 >= 0) && (x/2 < blocks.length);
		boolean yfits = (y/2 >= 0) && (y/2 < blocks[0].length);
		Inventory inv = myPlayer.getInventory();
		boolean clickedOnPlayer = ((x >= myPlayer.getX() - 1 && x <= myPlayer.getX() + 1) && (y >= myPlayer.getY() - 1 && y <= myPlayer.getY() + 1)); 
		if(inv.toolBar[inv.getItemSelected()] != null &&  inv.toolBar[inv.getItemSelected()].isStackable()) {
			if(xfits && yfits && !clickedOnPlayer) {
				Body b = blocks[x/2][y/2];
				if(b.getAir()) {
					blocks[x/2][y/2] = inv.toolBar[inv.getItemSelected()].place(2*(x/2), 2*(y/2));
					inv.removeItem(1);
				}
			}
		}
	}
	
	public void phaseGuard() {
		int x = (int)(myPlayer.getX()/2);
		int y = (int)((myPlayer.getY() + myPlayer.getVelocity().getY())/2);
		boolean xfits = (x >= 0) && (x < blocks.length);
		boolean yfits = (y >= 0) && (y < blocks[0].length);
		if((xfits && yfits)) {
			if(!(blocks[x][y].getAir())) { 
				myPlayer.setY(blocks[x][y].getY() - myPlayer.getVelocity().getY() - 2);

			}
		}
	}
	
}
