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
		boolean done = false;
		Item[] bar  =  toolbar.getItems();
		int index = this.findItem(item);
		int index2 =  this.findItem(null);
		if(index >= bar.length && index < bar.length + inventory.length) {
			int indexNew = index - bar.length;
			inventory[indexNew].setAmount(inventory[indexNew].getAmount()+ item.getAmount());
			done = true;
		} else if(index >= 0 && index < bar.length) {
			bar[index].setAmount(bar[index].getAmount()+ item.getAmount());
			done = true;
		}
		if(!done) {
			if(index >= bar.length && index < bar.length + inventory.length) {
				int index2New = index2 - bar.length;
				inventory[index2New] = item;
			} else if(index2 >= 0 && index < bar.length) {
				bar[index2] = item;
			}
		}
		
	}
	public int has(Item i) {
		int num = 0;
		Item[] t = toolbar.getItems();
		for(Item item: t) {
			if(item.getCode() == i.getCode()) {
				num = item.getAmount();
			}
		}
		for(Item item: inventory) {
			if(item.getCode() == i.getCode()) {
				num = item.getAmount();
			}
		}
		return num;
	}
	
	public int has(int i) {
		int num = 0;
		Item[] t = toolbar.getItems();
		for(Item item: t) {
			if(item != null && item.getCode() == i) {
				num = item.getAmount();
			}
		}
		for(Item item: inventory) {
			if(item != null && item.getCode() == i) {
				num = item.getAmount();
			}
		}
		return num;
	}

	public Item[] getInventory() {
		return inventory;
	}
	public void setInventory(Item[] inventory) {
		this.inventory = inventory;
	}


	public Toolbar getToolbar() {
		return toolbar;
	} 
	
	public int findItem(Item a) {
		Item[] bar = toolbar.getItems();
		int placement = 0;
		for(int i = 0; i < bar.length; i++) {
			if(a == null) {
				if(bar[i] == null) {
					return i;
				}
			} else if(bar[i] != null && a.getCode() == bar[i].getCode()) {
				return i;
			}
		}
		for(int i = 0; i < inventory.length; i++) {
			if(a == null) {
				if(bar[i] == null) {
					return i+bar.length;
				}
			} else if(inventory[i] != null && a.getCode() == inventory[i].getCode()) {
				return i+bar.length;
			}
		}
		return -1;
	}
	
	public int findItem(int code) {
		Item[] bar = toolbar.getItems();
		for(int i = 0; i < bar.length; i++) {
			if(bar[i] != null && code == bar[i].getCode()) {
				return i;
			}
		}
		for(int i = 0; i < inventory.length; i++) {
			if(inventory[i] != null && code == inventory[i].getCode()) {
				return i+bar.length;
			}
		}
		return -1;
	}
	
	public void removeItem(int code, int num) {
		Item[] bar = toolbar.getItems();
		int index = this.findItem(code);
		if(index >= bar.length && index < bar.length + inventory.length) {
			if(inventory[index-bar.length].getAmount() <= num) {
				inventory[index-bar.length] = null;
			} else {
				inventory[index-bar.length].setAmount(inventory[index-bar.length].getAmount() - num);
			}
		} else if(index >= 0 && index < bar.length) {
			if(bar[index].getAmount() <= num) {
				bar[index] = null;
			} else {
				bar[index].setAmount(bar[index].getAmount() - num);
			}
		}
		
	}
	
	
}


