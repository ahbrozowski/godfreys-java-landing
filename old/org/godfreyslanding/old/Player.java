package org.godfreyslanding.old;

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
	boolean canJump;
	int moving;
	public Player(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void init(GLWorld glworld) {
		moving = 0;
		canJump = true;
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DYNAMIC;
		bodyDef.position.set(this.x, this.y);
		bodyDef.fixedRotation = true;
		PolygonShape dynamicBox = new PolygonShape();
		dynamicBox.setAsBox(this.width/2.0f, this.height/2.0f);
		;
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = dynamicBox;
		fixtureDef.density = 1.0f;
		fixtureDef.restitution = .3f;
		
		body = glworld.createBody(bodyDef, fixtureDef);
		
	}
	void move() {
		Vec2 vel = body.getLinearVelocity();
		int LEFT = 2;
		int RIGHT = 1;
		if(Math.abs(vel.x) < 15) {
			if(moving == RIGHT) {
				if(vel.x < 0) {
				body.setLinearVelocity(new Vec2(0, vel.y));
				}
				body.applyForce(new Vec2(50,0), body.getWorldCenter());
			}
			else if(moving == LEFT) {
				if(vel.x > 0) {
					body.setLinearVelocity(new Vec2(0, vel.y));
				}
				body.applyForce(new Vec2(-50,0), body.getWorldCenter());			
			}
		}
	}
	
	void jump() {
		body.applyLinearImpulse(new Vec2(0,-50), body.getWorldCenter());
	} 
	
	public void draw(Graphics2D g,boolean print) {
		move();
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
		g.setColor(Color.RED);
		g.fillRect(x, y, w, h);
		g.setColor(c);
		
		g.setTransform(old);


		
	}

	public Vec2 getPosition() {
		return body.getPosition();
	}

	public void startRight() {
		moving = 1;
		
		
	}

	public void startLeft() {
		moving = 2;
	
		
	}
	
	public void stopLeft() {
		moving = 0;
		Vec2 vel = body.getLinearVelocity();
		body.setLinearVelocity(new Vec2(0, vel.y));
		
	}

	public void stopRight() {
		moving = 0;
		Vec2 vel = body.getLinearVelocity();
		body.setLinearVelocity(new Vec2(0, vel.y));	
		
		
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

}
