package org.godfreyslanding;

public class Inventory {
	Toolbar toolbar;
	Item[] inventory;
	public Inventory(Toolbar toolbar, Item[] inventory) {
		super();
		this.toolbar = toolbar;
		this.inventory = inventory;
	}
	
	
	public void addItem(Item item) {
		this.getToolbar().addItem(item);
	}


	public Item[] getInventory() {
		return inventory;
	}
	public void setInventory(Item[] inventory) {
		this.inventory = inventory;
	}


	Toolbar getToolbar() {
		return toolbar;
	} 
	
	
	
}
