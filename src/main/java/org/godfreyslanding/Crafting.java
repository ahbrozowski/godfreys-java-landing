package org.godfreyslanding;

import java.awt.Button;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;


public class Crafting {
	
	ArrayList<Recipe> recipes = new ArrayList<>();
	ArrayList<Recipe> canCraft = new ArrayList<>();
	Inventory inv;
	int width = 600;
	int height = 400;
	Color color = Color.PINK;
	int oldX;
	int oldY;
	int scrollPosition = 0;
	int sClicks = 0;
	JFrame frame;
	MyButton craft;
	MyButton up;
	MyButton down;


	public Crafting(JFrame frame, Inventory inv) {
		super();
		this.inv = inv;
		this.frame = frame;
		int[][] i = {{2,1}};
		recipes.add(new Recipe(i, new TorchP()));
		craft = new MyButton(100, 100, 200, 100, Color.WHITE, frame);
		up = new MyButton(120, 60, 100, 40, Color.BLUE,frame);
		down = new MyButton(120, 60, 100, 40, Color.RED,frame);
		
		
		
	}
	
	public void getOptions() {
		for(Recipe r: recipes) {
			if(r.hasIngredients(inv)) {
				canCraft.add(r);
			}
		}
	}
	
	public void draw(Graphics2D g, int width, int height) {
		int x = width/2 - this.width/2;
		int y = height/2 - this.height/2;
		
		
		craft.setY(y + 100);
		craft.setX(x + 100);
		g.setColor(color);
		g.fillRect(x, y, this.width, this.height);
		drawOptions(g,x,y);
		craft.draw(g);
		
		
		
	}
	
	public void drawOptions(Graphics2D g, int x, int y) {
		int n = 5;
		up.setX(x+ (5*this.width)/6 - up.getWidth()/2);
		up.setY(y + this.height/2 - up.getHeight()/2 + 50*(-n/2 - 1) );
		up.draw(g);
		down.setX(x+ (5*this.width)/6 - down.getWidth()/2);
		down.setY(y + this.height/2 - down.getHeight()/2 + 50*(n/2 + 1) );
		down.draw(g);
		for(int i = 0; i < n; i++) {
			int index = scrollPosition + (n/2) - i;
			if(index >= 0 && index < canCraft.size()) {
				
				int boxW = 100;
				int boxH = 40;
				int boxX= x+ (5*this.width)/6 - boxW/2;
				int boxY = (y + this.height/2 -boxH/2 ) +50*(n/2 - i);
				
				FontMetrics font = g.getFontMetrics(g.getFont());
				String text = canCraft.get(index).getName();
				
				int tX = boxX + boxW/2 - font.stringWidth(text)/2;
				int tY = boxY +boxH/2 - font.getHeight()/2 + font.getAscent();
				
				if(index == scrollPosition) {
					g.setColor(Color.DARK_GRAY);
				} else {
					g.setColor(Color.GRAY);
				}
				
				g.fillRect(boxX, boxY, boxW, boxH); 
				
				g.setColor(color.WHITE);
				g.drawString(canCraft.get(index).getName(), tX, tY);
				
			}
		}
	}

	public void clicked() {
		if(craft.clicked()) {
			System.out.print("clicked");
		}
		if(up.clicked()) {
			scrollPosition--;
		}
		if(down.clicked()) {
			scrollPosition++;
		} 
		if(craft.clicked()) {
			this.craft();
		}
		if(scrollPosition < 0) {scrollPosition = 0;}
		if(scrollPosition > canCraft.size() -1) {scrollPosition = canCraft.size() -1;}
	}



	public void scroll(MouseWheelEvent e) {
		int reqC = 3;
		sClicks = sClicks + e.getWheelRotation();
		if(e.getWheelRotation() != 0 && sClicks/e.getWheelRotation() < 0) {
			sClicks = 0;
		}
		if(sClicks >= reqC) {
			sClicks = 0;
			scrollPosition++;
		} if(sClicks <= -reqC) {
			sClicks = 0;
			scrollPosition--;
		}
		
		if(scrollPosition < 0) {scrollPosition = 0;}
		if(scrollPosition > canCraft.size() -1) {scrollPosition = canCraft.size() -1;}
	}
	
	public void craft() {
		if(scrollPosition >= 0) {
			Recipe r = canCraft.get(scrollPosition);
			int[][] ings = r.getIngredients();
			for(int[] ing: ings) {
				inv.removeItem(ing[0], ing[1]);
			}
			inv.addItem(r.getItem());
			
			for(int i = 0; i < canCraft.size(); i++) {
				if(!r.hasIngredients(inv)) {
					canCraft.remove(scrollPosition);
					i--;
				}
			
			}
		}
	}

}