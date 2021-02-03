package org.godfreyslanding;

public class Recipe {
		int[][] ingredients;
		Item item;
		
		public Recipe(int[][] ingredients, Item item) {
			this.ingredients = ingredients;
			this.item = item;
		}
		
		
		public boolean hasIngredients(Inventory inventory) {
			Item[] tb = inventory.getToolbar().getItems();
			Item[] inv = inventory.getInventory();
			for(int[] ing: ingredients) {
				for(Item item: tb) {
					if(item.getCode() == ing[0] && item.getCount() >= ing[1]) {
						return true;
					}
				} 
				for(Item item: inv) {
					if(item.getCode() == ing[0] && item.getCount() >= ing[1]) {
						return true;
					}
				}
				
			}
			
			return false;
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

		
		
}
