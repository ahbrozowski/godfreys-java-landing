package org.godfreyslanding.old;

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
	private List<Square> players;
	private Image[] images;
	private Mouse m;
	public World() throws IOException{
		images = new Image[imageResources.length];
		m = new Mouse();
		for(int i = 0; i < imageResources.length; i++) {
			images[i] = ImageIO.read( ClassLoader.getSystemResource(imageResources[i]));
		}
		
		entities = new ArrayList<>();
		for(int i = 0; i < 800; i+=10) {
			entities.add(new Block(i, 400, images[0]));
			entities.add(new Block(i, 410, images[1]));
			entities.add(new Block(i, 420, images[2]));
			entities.add(new Block(i, 430, images[3]));
			
			entities.add(new Block(i, 10, images[0]));
			entities.add(new Block(i, 20, images[1]));
			entities.add(new Block(i, 30, images[2]));
			entities.add(new Block(i, 40, images[3]));
		}
		for(int i = 0; i < 400;i+=10) {
			entities.add(new Block(200, i, images[0]));
		}
		
		sprites = new ArrayList<>();
		players = new ArrayList<>();
		players.add(new Square());
	}
	public Mouse getMouse() {
		return m;
	}
	public void clicked(Image img) throws IOException {
		int x = m.getX();
		int y = m.getY();
		int blockX = x - (x % 10);
		int blockY = y - (y % 10);
		boolean clickedOn = false;
		boolean placed = false;
		for(int i = 0; i < entities.size(); i++) {
			if(entities.get(i).clickedOn(x,y)) {
				clickedOn = true;
				if(m.getButton() == 1) {
					placed = true;
					entities.remove(i);
					System.out.print("Block destoyed");
				} else {
					System.out.print("Left Click to break me");
				}
			}
		}
		if(m.getButton() == 3 && !placed && !clickedOn) {
			entities.add(new Block(blockX , blockY, img));
		}
		m.update();
	}
	public List<Entity> intersects(Sprite sprite) {
		
		Rectangle bounds = sprite.getBounds();
		List<Entity> hitEntities = new ArrayList<Entity>();
		for(Entity e : entities) {
			if (e.getBounds().intersects(bounds)) {
				System.err.println("Found an entity!");
				hitEntities.add(e);
			}
		}
		
		for(Sprite s : sprites) {
			if (s != sprite && s.getBounds().intersects(bounds)) {
				System.err.println("Found a sprite!");
				hitEntities.add(s);
			}
		}
		
		return hitEntities;

		
	}

	public List<Entity> intersectingEntities(Sprite sprite) {

		Rectangle bounds = sprite.getBounds();
		
		return entities.stream()
				.filter(e -> e.getBounds().intersects(bounds))
				.collect(Collectors.toList());
		
	}
	
	public Square getSquare() {
		return players.get(0);
	}
	
	
	public void update()  {
		entities.forEach(e -> {
			try {
				e.update(this);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		sprites.forEach(s -> {
			try {
				s.update(this);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		players.forEach(p -> {
			try {
				p.update(this);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		try {
			clicked(images[1]);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	public void draw(Graphics2D g) {
		entities.forEach(e -> e.draw(g));
		sprites.forEach(s -> s.draw(g));
		players.forEach(p -> p.draw(g));
		g.setColor(Color.black);
		g.drawRect(0, 0, 800, 700);
	}

	public Rectangle bounds() {
		return new Rectangle(0, 0, 800, 800);
		
	}

}
