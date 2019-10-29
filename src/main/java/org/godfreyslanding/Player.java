package org.godfreyslanding;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

public class Player {
	
	Body body;
	float x; 
	float y;
	float width;
	float height;
	
	public Player(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void init(GLWorld glworld) {
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DYNAMIC;
		bodyDef.position.set(this.x, this.y);
		
		PolygonShape dynamicBox = new PolygonShape();
		dynamicBox.setAsBox(this.width/2.0f, this.height/2.0f);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = dynamicBox;
		fixtureDef.density = (float)(10.0 - Math.random()*10.0);
		fixtureDef.friction = 0.3f;
		fixtureDef.restitution = 0.7f;
		
		body = glworld.createBody(bodyDef, fixtureDef);
		
	}

	public void draw(Graphics2D g) {
		
		Vec2 position = body.getPosition();
		float angle = body.getAngle();
		
		//System.err.printf("%4.2f %4.2f %4.2f%n",position.x, position.y, angle);

		AffineTransform old = g.getTransform();
		g.translate(10*position.x, 10*position.y);
		g.rotate(angle);
		
		int x = Math.round(10*(-this.width/2.0f));
		int y = Math.round(10*(-this.height/2.0f));
		int w = Math.round(10*width);
		int h = Math.round(10*height);
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillRect(x, y, w, h);
		g.setColor(c);
		
		g.setTransform(old);
		

		
	}

}
