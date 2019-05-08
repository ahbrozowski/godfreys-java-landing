package org.godfreyslanding;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class World {
	
	private static final String[] imageResources = new String[] {
		"images/Dirt10x10.png",
		"images/Grass10x10.png",
		"images/stone10x10.png",
		"images/wood10x10.png"
	};
	
	private List<Entity> entities;
	private Image[] images;
	
	public World() throws IOException{
		images = new Image[imageResources.length];
		
		for(int i = 0; i < imageResources.length; i++) {
			images[i] = ImageIO.read( ClassLoader.getSystemResource(imageResources[i]));
		}
		
		entities = new ArrayList<>();
		for(int i = 0; i < 800; i+=10) {
			entities.add(new Block(i, 400, images[0]));
			entities.add(new Block(i, 410, images[1]));
			entities.add(new Block(i, 420, images[2]));
			entities.add(new Block(i, 430, images[3]));
		}
		entities.add(new Square());

	}
	
	public void update() {
		entities.forEach(Entity::update);
	}

	public void draw(Graphics2D g) {
		entities.forEach(e -> e.draw(g));
		g.setColor(Color.black);
		g.drawRect(0, 0, 800, 800);
	}

}
