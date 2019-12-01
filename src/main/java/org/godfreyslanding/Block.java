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

public class Block {
    
	Body body;
	float x; 
	float y;
	float width;
	float height;
	public Block(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void init(GLWorld glworld) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.STATIC;
		bodyDef.position.set(this.x, this.y);
		bodyDef.fixedRotation = true;
		PolygonShape box = new PolygonShape();
		box.setAsBox(this.width/2.0f, this.height/2.0f);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = box;
		
		body = glworld.createBody(bodyDef, fixtureDef);
		
	}
	
	public void draw(Graphics2D g,boolean print) {
		Vec2 position = body.getPosition();
		float angle = body.getAngle();
		if(print) {
			//System.err.printf("%4.2f %4.2f %4.2f%n",position.x, position.y, angle);
		}
		AffineTransform old = g.getTransform();
		g.translate(10*position.x, 10*position.y);
		g.rotate(angle);
		
		int x = Math.round(10*(-this.width/2.0f));
		int y = Math.round(10*(-this.height/2.0f));
		int w = Math.round(10*width);
		int h = Math.round(10*height);
		Color c = g.getColor();
		g.setColor(Color.GREEN);
		g.fillRect(x, y, w, h);
		g.setColor(c);
		
		g.setTransform(old);


		
	}

	public Vec2 getPosition() {
		return body.getPosition();
	}



}
