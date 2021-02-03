package org.godfreyslanding;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Crafting {
	
	ArrayList<Recipe> recipes = new ArrayList<Recipe>();
	ArrayList<Item> canCraft = new ArrayList<Item>();

	public Crafting() {
		super();
		int[][] i = {{2,5}};
		recipes.add(new Recipe(i, new TorchP()));
		
	}
	
	public void draw(Graphics2D g) {
		
	}
	
	
}
