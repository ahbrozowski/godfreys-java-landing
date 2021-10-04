package org.godfreyslanding;

public class Recipe {
		int[][] ingredients;
		Item item;
		
		public Recipe(int[][] ingredients, Item item) {
			this.ingredients = ingredients;
			this.item = item;
		}
		
		
		public boolean hasIngredients(Inventory inventory) {
			boolean has = true;
			for(int[] ing: ingredients) {
				if(inventory.has(ing[0]) < ing[1]) {
					return false;
				} 
				
			}
			
			return true;
		}


		public int[][] getIngredients() {
			return ingredients;
		}


		public Item getItem() {
			return item;
		}


		public void setItem(Item item) {
			this.item = item;
		}
		
		
		public void setIngredients(int[][] ingredients) {
			this.ingredients = ingredients;
		}

		public String getName() {
			return item.getName();
		}
		
}
