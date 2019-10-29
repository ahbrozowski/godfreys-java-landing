package org.godfreyslanding;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class GLWorld  {
	
	final Vec2 gravity;
	final World world;
	
	List<Player> players = new ArrayList<Player>();
	
	public GLWorld() {
		gravity = new Vec2(0.0f, 10.0f);
		world = new World(gravity);
		
		initEntities();
	}
	
	public void initEntities() {
		
		BodyDef groundBodyDef = new BodyDef();
		groundBodyDef.position.set(0.0f, 70.0f);
		
		Body groundBody = world.createBody(groundBodyDef);
		
		PolygonShape groundBox = new PolygonShape();
		
		groundBox.setAsBox(80.0f, 10.0f);
		
		groundBody.createFixture(groundBox, 0.0f);
		
		
		for(int i = 0; i < 10; i++) {
			Player p = new Player((40.0f + i), 40.0f - 4*i, 2.0f, 2.0f);
			p.init(this);
			players.add(p);
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

	public void draw(Graphics2D g) {
		for(Player p : players) {
			p.draw(g);
		}
	}
	
	
	
	

}
