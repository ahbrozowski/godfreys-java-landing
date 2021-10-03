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
		int foundNull = 40;
		Item[] t  =  toolbar.getItems();
		for(int i = 0; i < t.length; i++) {
			if(t[i] == null) {
				if(i < foundNull) {
					foundNull = i;
				}
			} else if((item.getCode() == t[i].getCode()) && item.isStackable()) {
				t[i].setAmount(t[i].getAmount()+item.getAmount());
				return;
			}
		}
		if(foundNull < 10) {
			t[foundNull] = item;
		} else {
			return;
		}
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
