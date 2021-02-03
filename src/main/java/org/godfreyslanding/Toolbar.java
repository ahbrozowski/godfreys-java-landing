package org.godfreyslanding;

import java.awt.Graphics2D;

public class Toolbar {
	private Item[] items;  
	private int itemSelected = 0;
	
	
	public Toolbar() {
		this.items = new Item[10];
	}

	public Item[] getItems() {
		return this.items;
	}

	public void addItem(Item item) {
		int foundNull = 10;
		for(int i = 0; i < getItems().length; i++) {
			if(getItems()[i] == null) {
				if(i < foundNull) {
					foundNull = i;
				}
			} else if((item.getCode() == getItems()[i].getCode()) && item.isStackable()) {
				getItems()[i].setAmount(getItems()[i].getAmount()+item.getAmount());
				return;
			}
		}
		if(foundNull < 10) {
			getItems()[foundNull] = item;
		} else {
			return;
		}
	}

	public void removeItem(int index, int num) {
		getItems()[index].setAmount(getItems()[index].getAmount()- num);
		if(getItems()[index].getAmount() <= 0) {
			getItems()[index] = null;
		}
	}

	public int getItemSelected() {
		return itemSelected;
	}

	public void setItemSelected(int itemSelected) {
		this.itemSelected = itemSelected;
	}

	public void draw(Graphics2D g, int width, int height) {
		Item[] items = getItems();
		for(int i = 0; i < items.length; i++) {
			if(items[i] != null) {
				g.drawString(items[i].toString(), 30*i + 40-width/2, 40 - height/2 ) ;
			}
		}
	}

	public Item getSelectedItem() {
		return getItems()[getItemSelected()];
	}

	public boolean isHoldingWeapon() {
		return getItems()[getItemSelected()] != null && !getItems()[getItemSelected()].isStackable();
	}

	public boolean isHoldingStackable() {
		return getItems()[getItemSelected()] != null &&  getItems()[getItemSelected()].isStackable();
	}


	public void removeSelectedItem() {
		removeItem(getItemSelected(), 1);
	}


}
