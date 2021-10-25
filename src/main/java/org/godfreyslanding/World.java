package org.godfreyslanding;

 import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JFrame;

public class World {
	Body[][] blocks;
	Player myPlayer;
	int width;
	int height;
	boolean breaking = false;
	boolean placing = false;
	boolean swinging = false;
	int spawnY;
	Graphics2D g;
	Time time;
	ArrayList<Entity> entities;
	int spawnCap = 10;
	ArrayList<Biome> biomes;
	int slowdown = 0;
	BoundingRect screen;
	BoundingRect spawn;
	//shows crafting menu
	boolean showCM = false;
	boolean showCursor = false;
	JFrame frame;
	
	public World(WorldData w, JFrame frame) {
		this.frame = frame;
		biomes = w.getBiomes();
		blocks = w.getBlocks();
		entities = new ArrayList<>();
		Vector v2 = new Vector(0,0);
		spawnY = w.getSpawnY();
		myPlayer = new Player(1000,spawnY,2,4,v2, Color.BLUE, this.frame);
		Item p = new StoneP();
		p.setAmount(5);
		myPlayer.getInventory().addItem(p);
		Item t = new TorchP();
		t.setAmount(50);
		myPlayer.getInventory().addItem(new Sword());
		myPlayer.getInventory().addItem(t);
		myPlayer.setSpawn(1000, spawnY);
		System.out.print(spawnY);
		//entities.add(new Zombie(1020.0, (double)spawnY - 10, myPlayer));
		time = w.getT();
		//System.out.print(blocks[500][spawnY].getAir());
		screen = new BoundingRect(10,10, (int)myPlayer.getY()/2,(int)myPlayer.getY()/2 );
		spawn = new BoundingRect(20,20, (int)myPlayer.getY()/2,(int)myPlayer.getY()/2 );
	}

	public void update(float t) {
		time.update();
		slowdown++;
		//System.out.println(myPlayer.getX() + " " + myPlayer.getY());
		myPlayer.setColidedR(false);		
		myPlayer.setColidedL(false);
		boolean gravity = true;
		BoundingBox bBox = myPlayer.getBBox();
		bBox.correct(blocks);
		for(int x = bBox.getLeftEdge(); x < bBox.getRightEdge(); x++) {
			for(int y = bBox.getTop(); y < bBox.getBottom(); y++) {
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
					body.update(false,time);
					myPlayer.colidesBlock(side, body);
				}
					
			}
		} 
		
		for(int i = 0; i < entities.size(); i++) {
			Entity ent = entities.get(i);
			if(myPlayer.collisionCheck(ent) && !myPlayer.isInvinc() && myPlayer.getHealth() > 0) {
				if(ent.getX() > myPlayer.getX()) {
					myPlayer.getVelocity().addX(-ent.getKnockback().getX());
					myPlayer.getVelocity().addY(ent.getKnockback().getY());
					System.out.println("right");
				}
				if(ent.getX() < myPlayer.getX()) {
					myPlayer.getVelocity().addX(ent.getKnockback().getX());
					myPlayer.getVelocity().addY(ent.getKnockback().getY());
					System.out.println("left");
				}
				myPlayer.damage(ent.getDamage());
				System.out.println(ent.getDamage());
				myPlayer.setStunned(true);
			}
			if(ent.getHealth() <= 0) {
				entities.remove(i);
				i--;
			}
			
			
			if(ent.calcDist(myPlayer) > 100) {
				entities.remove(i);
				i--;
			}
		}
		if(breaking) {
			breakBlock();
		}
		if(placing) {
			placeBlock();
		}
		if(swinging) {
			Inventory inv = myPlayer.getInventory();
	  		Item item = myPlayer.getSelectedItem();
	  		if(item != null) {
				swing();
		  		item.countUp();
		  		if(item.getCount() > 30) {
		  			item.resetCount();
		  			swinging = false;
		  		}
	  		} else {
	  			swinging = false;
	  		}
		}
		
		if(myPlayer.getVelocity().getY() >= 1) {
			phaseGuard();
		}
		if(myPlayer.getHealth() <= 0) {
			if(spawnY < 0) {
				spawnY = 0;
			} 
			if(!blocks[500][spawnY/2].getAir()) {
				blocks[500][spawnY/2].setAir(true);
				blocks[500][spawnY/2].setColor(Color.CYAN);
				
			}
		}
		entityPhysics();
		myPlayer.update(gravity, time);
		
		if(entities.size() < spawnCap ) {
			int rate = 30 + (10 * entities.size());
			int r = (int)(Math.random() * rate);
					
			if(r == rate/2) {    
				enemySpawn();
			}
		}
	}
	
	public void draw(Graphics2D g, int width, int height) {
		
		int globalLightValue = calcGlobeLight();
		ArrayList<Body> ls = new ArrayList<>(); 
		ls.add(myPlayer);
		this.g = g;
		this.width = width;
		this.height = height;
		AffineTransform old = g.getTransform();
		double x = myPlayer.getX();
		double y = myPlayer.getY();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,this.width,this.height);
		g.translate((width/2)-10*x, (height/2)-10*y);
		screen.setHeight(height/20 + 2);
		screen.setWidth(width/20 + 2);
		screen.update((int)myPlayer.getX()/2,(int)myPlayer.getY()/2);
		Point p = getMouseP();
		int px = p.x;
		int py = p.y;
		boolean xfits = (px >= 0) && (px < blocks.length);
		boolean yfits = (py >= 0) && (py < blocks[0].length);
		for(int i = screen.getLeftEdge(); i < screen.getRightEdge(); i++) {
			for(int j = screen.getTop(); j < screen.getBottom(); j++) {
				
				Body body = blocks[i][j];
				body.setDarkLevels(8);
				if(body.getLight() > 0) {
					ls.add(body);
				} 
				if(body.isGl()) {
					body.setLight(globalLightValue);
				}
				
			}
		}
		

		
		for(Body body: ls) {
			for(int i = (int)(body.getX()/2 - body.getLight()); i <= body.getX()/2 + body.getLight(); i++) {
				if(i < 0) { i = 0;}
				if(i >= blocks.length) { i = blocks.length - 1;}
				for(int j = (int)(body.getY()/2 - body.getLight()); j <= body.getY()/2 + body.getLight(); j++) {
					if(j < 0) { j = 0;}
					if(j >= blocks[0].length) { j = blocks[0].length - 1;}
					Body b =  blocks[i][j];
					int distX = Math.abs((int)b.getX()/2 - (int)body.getX()/2);
					int distY = Math.abs((int)b.getY()/2 - (int)body.getY()/2);
					int dist = (distX + distY);
					int o = 8-body.getLight() + dist;
					if(b.getDarklevels() > o) { 
						b.setDarkLevels(o);
					}
				}
			}
		}
		
		for(int i = screen.getLeftEdge(); i < screen.getRightEdge(); i++) { 
			for(int j = screen.getTop(); j < screen.getBottom(); j++) {
				Body body = blocks[i][j];
				if(body.getDarklevels() < 8) {
					body.draw(g,width,height);
				}
				//body.drawShadow(g);
			}
		}
		for(Body entity: entities) {
			entity.draw(g,width,height);
			if (entity.getLight() > 0) {
				ls.add(entity);
			}
		}
		
		if(yfits && xfits) {
			blocks[px][py].drawHighlight(g);
		}
		//System.out.println(leftEdge + " " + rightEdge + " " + top + " " + bottom);
		/* x for(Body[] block: blocks){
			for(Body body: block){
				body.draw(g);
			}
		} */ 
		myPlayer.draw(g,width,height); 
		g.setTransform(old);
		
		
		if(showCM) {
			myPlayer.getCraft().draw(g, this.width, this.height);
			showCursor = true;
		}else {
			showCursor = false;
		}
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
        	myPlayer.setItemSelected(0);
        }
        if(e.getKeyCode() == KeyEvent.VK_2){
        	myPlayer.setItemSelected(1);
        }
        if(e.getKeyCode() == KeyEvent.VK_3){
        	myPlayer.setItemSelected(2);
        }
        if(e.getKeyCode() == KeyEvent.VK_4){
        	myPlayer.setItemSelected(3);
        }
        if(e.getKeyCode() == KeyEvent.VK_5){
        	myPlayer.setItemSelected(4);
        }
        if(e.getKeyCode() == KeyEvent.VK_6){
        	myPlayer.setItemSelected(5);
        }
        if(e.getKeyCode() == KeyEvent.VK_7){
        	myPlayer.setItemSelected(6);
        }
        if(e.getKeyCode() == KeyEvent.VK_8){
        	myPlayer.setItemSelected(7);
        }
        if(e.getKeyCode() == KeyEvent.VK_9){
        	myPlayer.setItemSelected(8);
        }
        if(e.getKeyCode() == KeyEvent.VK_0){
        	myPlayer.setItemSelected(9);
        }
        if(e.getKeyCode() == KeyEvent.VK_K){
        	myPlayer.damage(100);
        }
        if(e.getKeyCode() == KeyEvent.VK_T){
            // System.out.println("Key Pressed" + e.getKeyCode());
             System.err.println(time.getHour()); 
         }
        if(e.getKeyCode() == KeyEvent.VK_I){
        	showCM = !showCM;
        	myPlayer.getCraft().getOptions();
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
			if (myPlayer.isHoldingWeapon()) {
				swinging = true;
			} else {
				breaking = true;
			}
		}
		Point p = MouseInfo.getPointerInfo().getLocation();
		if(((p.x)/10 + myPlayer.getX() - width/20) > myPlayer.getX()) {
			myPlayer.setLookingRight(true);
			
		} else {
			myPlayer.setLookingRight(false);
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
		Point p = getMouseP();
		int px = p.x;
		int py = p.y;
		boolean xfits = (px >= 0) && (px < blocks.length);
		boolean yfits = (py >= 0) && (py < blocks[0].length);
		//System.out.println(x + " " + y);
		if(xfits && yfits) {
			Body b = blocks[px][py];
			if(!b.getAir()) {
				if(b.getHealth() == 0) {
					Vector v2 = new Vector(0,0);
					blocks[px][py] = new Body(b.getX(),b.getY(),2,2,v2,Color.LIGHT_GRAY, true, 0, 0, false);
					for(Biome biome: biomes) {
						if(biome.containsBody(b)) {
							blocks[px][py] = biome.getSkyBlock(b.getX(),b.getY()); 
						}
					}
					myPlayer.getInventory().addItem(b.item(1));
				} else {
					b.setHealth(b.getHealth() - 1);
				}
			}
		}	
	}
	
	public void placeBlock() {
		Point p = getMouseP();
		int px = p.x;
		int py = p.y;
		boolean xfits = (px >= 0) && (px < blocks.length);
		boolean yfits = (py >= 0) && (py < blocks[0].length);
		Inventory inv = myPlayer.getInventory();
		boolean clickedOnPlayer= false;
		if(myPlayer.collisionCheck(blocks[px][py])) {
			 clickedOnPlayer= true;
		}
		Toolbar tb = inv.getToolbar();
		if(myPlayer.isHoldingStackable()) {
			if(xfits && yfits && !clickedOnPlayer) {
				Body b = blocks[px][py];
				if(b.getAir()) {
					blocks[px][py] = myPlayer.getSelectedItem().place(2*(px), 2*(py));
					myPlayer.removeSelectedItem();
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
	
	
	public void entityPhysics() {
		for(int i = 0; i < entities.size(); i++) {
			boolean gravity = true;
			Entity entity = entities.get(i);
			BoundingBox bBox = entity.getBBox();
			bBox.correct(blocks);
			for(int x = bBox.getLeftEdge(); x < bBox.getRightEdge(); x++) {
				for(int y = bBox.getTop(); y < bBox.getBottom(); y++) {
					Body body = blocks[x][y];
					if(!body.getAir() && entity.collisionCheck(body)) {
						int side = entity.collisionSide(body);
						if(side == 1) {
							gravity = false;
						}
						entity.colidesBlock(side, body);
					}
				}
			}
			entity.update(gravity,time);
			
		}
	}
	
	public void swing() {
  		Item item = myPlayer.getSelectedItem();
  		int c = item.getCount();
  		for(Entity ent : entities){
  			int sX = (int)myPlayer.getX() + (int)myPlayer.getWidth() - (int)item.width;
			int sY = (int)myPlayer.getY() + (int)myPlayer.getHeight() - (int)item.height;
  			if(c%5 == 0) {
  				double ix = sX - myPlayer.getWidth();
  				if(myPlayer.isLookingRight()){
  					ix = sX + myPlayer.getWidth();
  				}
  				item.useItem(ent,ix, sY - myPlayer.getHeight()/4, g, myPlayer.isLookingRight());
  				//System.out.println(ent.getHealth());
  			}
  		}
  		if(c < 29) {
  			item.draw = true;
  		}else {
  			item.draw = false;
  		}
  	}
	
	public int calcGlobeLight() {
		int timeDay = time.getTimeDay();
		int dayLength = time.getDayLength();
		double c = dayLength/24.0;
		double h = timeDay/c;
		int lowest = 8;
		for(int i = 1; i <= 8; i++) {
			if(h < i/2.0 || h > (14 - i/2.0)) {
				if(i < lowest) {
					lowest = i;
				}
			}
		}
		return lowest;
		
	}
	
	public void enemySpawn() {
		Biome b = biomes.get(0);
		for(int i = 0; i < biomes.size(); i++) {
			if(biomes.get(i).containsBody(myPlayer)) {
				if(b.getPriority() <= biomes.get(i).getPriority()) {
					b = biomes.get(i);
				}
			}
		}
		int r = (int)(Math.random() * 20) - 10;
		double x = myPlayer.getX() + r;
		double y = Math.round(myPlayer.getY());
		SpawnPackage pack = b.getPackage(myPlayer, time, x, y);
		if(pack.canSpawn()) {
			findSpawn(pack.getMob());
			
		}
	}
	
	public void findSpawn(Entity e) {
		boolean found = false;
		int rx = 0;
		int ry = 0;
		spawn.setWidth(screen.getWidth() + 20);
		spawn.setHeight(screen.getHeight() + 20);
		int numLoops = 0;
		while(!found) {
			rx = (int)(Math.random() * spawn.getWidth()) - spawn.getWidth()/2;
			ry =(int)(Math.random() * spawn.getHeight()) - spawn.getHeight()/2;
			boolean fitsX = (rx < -screen.getWidth()/2 || rx > screen.getWidth()/2);
			boolean fitsY = (ry < -screen.getHeight()/2 || ry > screen.getHeight()/2); 
			
			//System.out.println(numLoops);
			numLoops++;
			if(fitsX && fitsY) {
				found = true;
			}

		}
		int x =(int)(myPlayer.getX()/2) + rx;
		int y = (int)(myPlayer.getY()/2) + ry;
		if(y < 0) {y = 0;}
		if(x < 0) { x = 0;}
		if( x >= blocks.length) { x = blocks.length - 1;}
		if(y >= blocks[0].length) { y = blocks[0].length - 1;}
		boolean empty = blocks[x][y].getAir();
		while(!empty) {
			int r = (int)(Math.random() * 2); 
			if(r == 0) {
				if(rx < 0) {
					rx--;
				}else {
					rx++;
				}	
			} else {
				if(ry < 0) {
					ry--;
				}else {
					ry++;
				}	
			}
			x =(int)(myPlayer.getX()/2) + rx;
			y = (int)(myPlayer.getY()/2) + ry;
			if(y < 0) {break;}
			if(x < 0) { break;}
			if( x >= blocks.length) { break;}
			if(y >= blocks[0].length) { break;}
			empty = blocks[x][y].getAir();
		}
		if(empty) {
			e.setX(myPlayer.getX() + rx);
			e.setY(myPlayer.getY() + ry);
			entities.add(e); 
		}

		

		
	}

	public boolean showCursor() {
		return showCursor;
		
	}
	
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public void mouseClicked() {
		if(showCM) {
			myPlayer.getCraft().clicked();
		}
		
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		if(showCM) {
			myPlayer.getCraft().scroll(e);
		}
	}
	
	public Point getMouseP() {
		Point p = MouseInfo.getPointerInfo().getLocation();
		Point fP = frame.getLocation();
		int top = frame.getInsets().top;
		int px = ((p.x)/10 + (int)(myPlayer.getX()) - width/20 )/2 -(int)fP.x/20;
		int py = ((p.y - top)/10 + (int)(myPlayer.getY()) - height/20)/2 - (int)fP.y/20;
		Point mP = new Point(px,py);
		return mP;
	}
	
	
}