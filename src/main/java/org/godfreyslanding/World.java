package org.godfreyslanding;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

public class World  {
	
	private static final String[] imageResources = new String[] {
		"images/Dirt10x10.png",
		"images/Grass10x10.png",
		"images/stone10x10.png",
		"images/wood10x10.png"
	};
	
	private List<Entity> entities;
	private List<Sprite> sprites;
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
		
		sprites = new ArrayList<>();
		sprites.add(new Square());

	}
	
	public boolean intersects(Sprite sprite) {
		
		Rectangle bounds = sprite.bounds();
		
		boolean found = false;
		
		for(Entity e : entities) {
			if (e.bounds().intersects(bounds)) {
				System.err.println("Found an entity!");
				found = true;
			}
		}
		
		for(Sprite s : sprites) {
			if (s != sprite && s.bounds().intersects(bounds)) {
				System.err.println("Found a sprite!");
				found = true;
			}
		}
		
		return found;

		
	}

	public List<Entity> intersectingEntities(Sprite sprite) {

		Rectangle bounds = sprite.bounds();
		
		return entities.stream()
				.filter(e -> e.bounds().intersects(bounds))
				.collect(Collectors.toList());
		
	}
	
	
	
	public void update() {
		entities.forEach(e -> e.update(this));
		sprites.forEach(s -> s.update(this));
	}

	public void draw(Graphics2D g) {
		entities.forEach(e -> e.draw(g));
		sprites.forEach(s -> s.draw(g));
		g.setColor(Color.black);
		g.drawRect(0, 0, 800, 800);
	}

	public Rectangle bounds() {
		return new Rectangle(0, 0, 800, 800);
		
	}

}
