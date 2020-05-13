package org.godfreyslanding;

public class Inventory {
	Item[] toolBar;
	Item[] inventory;
	int itemSelected = 0;
	public Inventory(Item[] toolBar, Item[] inventory) {
		super();
		this.toolBar = toolBar;
		this.inventory = inventory;
	}
	
	
	public void addItem(Item item) {
		int foundNull = 10;
		for(int i = 0; i < toolBar.length; i++) {
			if(toolBar[i] == null) {
				if(i < foundNull) {
					foundNull = i;
				}
			} else if((item.getCode() == toolBar[i].getCode()) && item.isStackable()) {
				toolBar[i].setAmount(toolBar[i].getAmount()+item.getAmount());
				return;
			}
		}
		if(foundNull < 10) {
			toolBar[foundNull] = item;
		} else {
			return;
		}
	}
	public void removeItem(int n) {
		toolBar[itemSelected].setAmount(toolBar[itemSelected].getAmount()- n);
		if(toolBar[itemSelected].getAmount() <= 0) {
			toolBar[itemSelected] = null;
		}
	}
	public Item[] getToolBar() {
		return toolBar;
	}
	public void setToolBar(Item[] toolBar) {
		this.toolBar = toolBar;
	}
	public Item[] getInventory() {
		return inventory;
	}
	public void setInventory(Item[] inventory) {
		this.inventory = inventory;
	}


	public int getItemSelected() {
		return itemSelected;
	}


	public void setItemSelected(int itemSelected) {
		this.itemSelected = itemSelected;
	} 
	
	
	
}
