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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WorldTest {
    
	@Override
	public String toString() {
		return "Block [body=" + body + ", x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + "]";
	}

	Body body;
	float x; 
	float y;
	float width;
	float height;
	@JsonCreator
	public WorldTest(@JsonProperty("x")float x, @JsonProperty("y")float y, @JsonProperty("width")float width, @JsonProperty("height")float height) {
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
		bodyDef.allowSleep = true;
		bodyDef.awake = false;
		PolygonShape box = new PolygonShape();
		box.setAsBox(this.width/2.0f, this.height/2.0f, this.getPosition(), 0);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = box;
		
		
		body = glworld.createBody(bodyDef, fixtureDef);
		
	}
	
	
	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public void draw(Graphics2D g,boolean print) {
		Vec2 position = body.getPosition();
		if(print) {
			//System.err.printf("%4.2f %4.2f %4.2f%n",position.x, position.y, angle);
		}
		AffineTransform old = g.getTransform();
		g.translate(10*position.x, 10*position.y);
		
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
	@JsonIgnore
	public Vec2 getPosition() {
		return body.getPosition();

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
