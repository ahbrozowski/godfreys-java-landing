package org.godfreyslanding;

import java.util.ArrayList;
import java.util.Arrays;

public class WorldData {
	private String name;
	private Block[][] blocks;
	
	@Override
	public String toString() {
		return "WorldData [name=" + name + ", blocks=" + Arrays.toString(blocks) + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Block[][] getBlocks() {
		return blocks;
	}
	public void setBlocks(Block[][] blocks) {
		this.blocks = blocks;
	}
	
	
	
	

}
