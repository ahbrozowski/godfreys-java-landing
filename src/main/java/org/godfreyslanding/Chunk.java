package org.godfreyslanding;

import java.util.ArrayList;

public class Chunk {
	
	private String type;
	private Block[][] blocks;
	private static final int SIZEX = 100;
	private static final int SIZEY = 100;
	public Chunk(String type) {
		this.type = type;
		blocks = chunkGen();
	}

	private Block[][] chunkGen(){
		Block[][] blocks = new Block[SIZEX][SIZEY];
		for(int x = 0; x < SIZEX; x++) {
			for(int y = 0; y < SIZEY; y++) {
				
			}
		}
		return blocks;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Chunk [type=" + type + "]";
	}
}
