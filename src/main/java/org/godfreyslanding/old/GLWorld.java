package org.godfreyslanding.old;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class GLWorld  {
	WorldData w;
	Image image;
	final Vec2 gravity;
	final World world;
	Player myPlayer;
	Block[][] blocks;
	ArrayList<Block> oldBlocks = new ArrayList<Block>();
	ArrayList<Block> madeBlocks = new ArrayList<Block>();
	float lastX;
	public GLWorld(WorldData w) throws IOException {
		blocks = w.getBlocks();
		gravity = new Vec2(0.0f, 10.0f);
		world = new World(gravity);
		this.w = w;
		initEntities();
	}
	
	public void initEntities() throws IOException {
		blocks = w.getBlocks();
		image = ImageIO.read( ClassLoader.getSystemResource("images/TitleGL.png"));
	
		myPlayer = new Player(20,20,2.0f,2.0f);
		myPlayer.init(this);
		for(int x = 0; x < blocks.length; x++) {
			for(int y = 0; y < blocks[x].length; y++ ) {
				if(blocks[x][y] != null) {
					blocks[x][y].init(this);
				}
			}
		}
		
	}
	public Body createBody(BodyDef bodyDef, FixtureDef fixtureDef) {
		
		Body body = this.world.createBody(bodyDef);
		body.createFixture(fixtureDef);
		
		return body;
		
	}

	public void update(float timeStep, int velocityIterations, int positionIterations) {
		world.step(timeStep, velocityIterations, positionIterations);
		
	}
	
	public void draw(Graphics2D g, int width, int height) {
		Vec2 position = myPlayer.getPosition();

		g.translate((width/2)-10*position.x, (height/2)-10*position.y);
		g.drawImage(image, 0, 0, null);
		
		int lBound = (int) (position.x-30);
		int rBound = (int) (position.x+30);
		if(lBound < 0 ) {
			 lBound = 0;
		}
		if(rBound > blocks.length ) {
			rBound = blocks.length;
		}
		int min = 10000;
		for(int x = lBound; x < rBound; x++) {
			if(x < min) {
				min = x;
			}
			lBound = (int) (position.x-25);
			rBound = (int) (position.x+25);
			if(lBound < 0 ) {
				 lBound = 0;
			}
			if(rBound > blocks.length ) {
				rBound = blocks.length;
			}
			
			for(int y = 0; y < blocks[x].length; y++) {
				if(blocks[x][y] != null) {
					blocks[x][y].draw(g,true);
				}
			}
		}
		
		myPlayer.draw(g,true);
	}

	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
            System.out.println("Key Pressed" + e.getKeyCode());
            myPlayer.jump();      
        }
		
        if(e.getKeyCode() == KeyEvent.VK_D){
            System.out.println("Key Pressed" + e.getKeyCode());
            myPlayer.startRight();
        }
        
        if(e.getKeyCode() == KeyEvent.VK_A){
            System.out.println("Key Pressed" + e.getKeyCode());
            myPlayer.startLeft();
        }


		
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_D){
            System.out.println("Key Released" + e.getKeyCode());
            myPlayer.stopRight();
        }
		if(e.getKeyCode() == KeyEvent.VK_A){
            System.out.println("Key Released" + e.getKeyCode());
            myPlayer.stopLeft();
        }
	}
	
	
	
	

}
