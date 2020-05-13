package org.godfreyslanding;

import java.awt.Color;

public class WorldData {
	Body[][] blocks;
	int spawnY = 0;
 
	public WorldData(Body[][] blocks) {
		this.blocks = blocks;
		worldGen();
	}

	public Body[][] getBlocks() {
		return blocks;
	}

	public void worldGen() {
		for(int x = 0; x < blocks.length; x++) {
			
			double r = (noise(x * (1.0/300.0)) * 1.0 +
	        noise(x * (1.0/150.0)) * 0.5 +
	        noise(x * (1.0/75.0))  * 0.25 +
	        noise(x * (1.0/37.5))  * 0.125);
			int n = (int)(r * 100);
			System.out.println(x  +"," + r + "," + n + "," + (n + 50));
			if(x == 500) {
				spawnY =  2*(n + 50);
			}
			for(int y = 0; y < blocks[x].length; y++) {
				Vector v2 = new Vector(0,0);
				if(y <= (n + 50)) {
					blocks[x][y] = new Body(2*x,2*y,2,2,v2,Color.CYAN, true, 0);
				}else {
					blocks[x][y] = new Body(2*x,2*y,2,2,v2,Color.RED, false, 10);
				}
			}
		}
		
	}
	
	double fade(double t) {
		return t*t*t*(t*(t*6.0 -15.0) +10);
		
	}
	static double vals[] = new double[512];
	static boolean init = false;
	double grad(double p) {
		if( init == false) {
			for(int i = 0; i < vals.length; i++) {
				double r = 2* Math.random() -1; 
				vals[i] = r < 0? -1.0:1.0;
			}
			init = true;
		}
		return vals[(int)p];
		
	}
	
	public double noise(double p) {
		double p0 = Math.floor(p);
		double p1 = p0 + 1;
		
		double t = p - p0;
		double fadeT = fade(t);
		
		double g0 = grad(p0);
		double g1 = grad(p1);
		
		return (1.0-fadeT)*g0*(p - p0) + fadeT*g1*(p - p1);
		
		
	} 
	
	public void setBlocks(Body[][] blocks) {
		this.blocks = blocks;
	}
	public int getSpawnY() {
		return spawnY;
	}
	
}
