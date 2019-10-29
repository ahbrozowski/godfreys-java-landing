package org.godfreyslanding.old;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class Box2DSample {

	public static void main(String[] args) {
		
		Vec2 gravity = new Vec2(0.0f, -10.0f);
		
		World world = new World(gravity);
		
		
		BodyDef groundBodyDef = new BodyDef();
		groundBodyDef.position.set(0.0f, -10.0f);
		
		Body groundBody = world.createBody(groundBodyDef);
		
		PolygonShape groundBox = new PolygonShape();
		
		groundBox.setAsBox(50.0f, 10.0f);
		
		groundBody.createFixture(groundBox, 0.0f);
		
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DYNAMIC;
		bodyDef.position.set(0.0f, 4.0f);
		Body body = world.createBody(bodyDef);
		
		PolygonShape dynamicBox = new PolygonShape();
		dynamicBox.setAsBox(1.0f, 1.0f);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = dynamicBox;
		fixtureDef.density = 1.0f;
		fixtureDef.friction = 0.3f;
		
		body.createFixture(fixtureDef);
		
		float timeStep = 1.0f / 60.0f;
		
		int velocityIterations = 6;
		int positionIterations = 2;
		
		for (int i = 0; i < 60; i++) {
			world.step(timeStep, velocityIterations, positionIterations);
			Vec2 position = body.getPosition();
			float angle = body.getAngle();
			System.err.println(String.format("%4.2f %4.2f %4.2f", position.x, position.y, angle));
		}
		
		
		
	}

}
