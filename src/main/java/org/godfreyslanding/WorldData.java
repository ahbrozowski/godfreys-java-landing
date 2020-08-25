package org.godfreyslanding;

import java.awt.Color;
import java.util.ArrayList;

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
		ArrayList<Double> vals = new ArrayList<Double>();
		
		for(int x = 0; x < blocks.length; x++) {
			
			double r = (noise(x * (1.0/300.0),false) * 1.0 +
	        noise(x * (1.0/150.0),false) * 0.5 +
	        noise(x * (1.0/75.0),false)  * 0.25 +
	        noise(x * (1.0/37.5),false)  * 0.125);
			int n = (int)(r * 100);
			System.out.println(x  +"," + r + "," + n);
			if(x == 500) {
				spawnY =  2*(n + 50);
			}
			for(int y = 0; y < blocks[x].length; y++) {
				
				double r2 = noise2d(x * (1.0/75.0),y * (1.0/75.0),false) * 1.0 + 
						noise2d(x * (1.0/37.5),y * (1.0/37.5),false) * .5 + 
						noise2d(x * (1.0/18.75),y * (1.0/18.75),false) * .25;
				double stone = noise2d(x * (1.0/75.0),y * (1.0/75.0),true) * 1.0 + 
						noise2d(x * (1.0/37.5),y * (1.0/37.5),true) * .5 + 
						noise2d(x * (1.0/18.75),y * (1.0/18.75),true) * .25;
				
				//System.out.println(r2d);
				Vector v2 = new Vector(0,0);
				if(y <= (n + 50)) {
					blocks[x][y] = new Body(2*x,2*y,2,2,v2,Color.CYAN, true, 0);
					
					if(Math.abs(r2) > 2){
						vals.add(Math.abs(r2));
					}
				}else {
					if(Math.abs(r2) <  .4 || Math.abs(r2) > 2) {
						blocks[x][y] = new Body(2*x,2*y,2,2,v2,Color.CYAN, true, 0);
						//System.out.println(x + " " + y);
					} else if(Math.abs(stone) < .5) {
						//System.out.println(x + " " + y);
						blocks[x][y] = new StoneB(2*x,2*y);
					} 
					else {
						blocks[x][y] = new Body(2*x,2*y,2,2,v2,Color.RED, false, 10);
					}
				}
			}
			
		}
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(vals);
		System.out.println(vals.size());
		System.out.println(" ");
	}
	
	double fade(double t) {
		return t*t*t*(t*(t*6.0 -15.0) +10);
		
	}
	static double vals[] = new double[512];
	static double ore[] = new double[512];
	static boolean init = false;
	double grad(double p, boolean isOre) {
		if( init == false) {
			for(int i = 0; i < vals.length; i++) {
				double r = 2* Math.random() -1; 
				vals[i] = r < 0? -1.0:1.0;
			}
			for(int i = 0; i < ore.length; i++) {
				double r = 2* Math.random() -1; 
				ore[i] = r < 0? -1.0:1.0;
			}
			init = true;
		}
		if(isOre) {
			return ore[(int)p];
		} else {
			return vals[(int)p];
		}
	}
	
	public double noise(double p,boolean isOre) {
		double p0 = Math.floor(p);
		double p1 = p0 + 1;
		
		double t = p - p0;
		double fadeT = fade(t);
		
		double g0 = grad(p0,isOre);
		double g1 = grad(p1,isOre);
		
		return (1.0-fadeT)*g0*(p - p0) + fadeT*g1*(p - p1);
		
		
	} 
	
	
	public double[] grad2d(double x, double y, boolean isOre) {
		double[] vec;
		if( init == false) {
			for(int i = 0; i < vals.length; i++) {
				double r = 2* Math.random() -1; 
				vals[i] = r < 0? -1.0:1.0;
			}
			for(int i = 0; i < ore.length; i++) {
				double r = 2* Math.random() -1; 
				ore[i] = r < 0? -1.0:1.0;
			}
			init = true;
		}
		if(isOre) {
			vec = new double[2];
			vec[0] = ore[(int)x];
			vec[1] = ore[(int)y];

		} else {
			
			vec = new double[2];
			vec[0] = vals[(int)x];
			vec[1] = vals[(int)y];
		}
		return vec;
		
			
	}
	
	
	public double noise2d(double x, double y, boolean isOre) {
		  double[] p0 = {Math.floor(x), Math.floor(y)} ;
		  double[] p1 = {1.0 + p0[0], 0.0 + p0[1]};
		  double[] p2 = {0.0 + p0[0], 1.0 + p0[1]};
		  double[] p3 = {1.0 + p0[0], 1.0 + p0[1]};
		  double[] pp0 = {x - p0[0], y - p0[1]}; 
		  double[] pp1 = {x - p1[0], y - p1[1]}; 
		  double[] pp2 = {x - p2[0], y - p2[1]}; 
		  double[] pp3 = {x - p3[0], y - p3[1]}; 
		  /* Look up gradients at lattice points. */
		  double[] g0 = grad2d(p0[0], p0[1], isOre);
		  double[] g1 = grad2d(p1[0], p1[1], isOre);
		  double[] g2 = grad2d(p2[0], p2[1], isOre);
		  double[] g3 = grad2d(p3[0], p3[1], isOre);
		    
		  double t0 = x - p0[0];
		  double fade_t0 = fade(t0); /* Used for interpolation in horizontal direction */

		  double t1 = y - p0[1];
		  double fade_t1 = fade(t1); /* Used for interpolation in vertical direction. */

		  /* Calculate dot products and interpolate.*/
		  double p0p1 = (1.0 - fade_t0) * dot(g0, (pp0)) + fade_t0 * dot(g1, (pp1)); /* between upper two lattice points */
		  double p2p3 = (1.0 - fade_t0) * dot(g2, (pp2)) + fade_t0 * dot(g3, (pp3)); /* between lower two lattice points */
		  
		  /* Calculate final result */
		  return (1.0 - fade_t1) * p0p1 + fade_t1 * p2p3;
	}
	
	
	
	public double dot(double[] a, double[] b) {
		return a[0] * b[0] + a[1] + b[1];
	}
	
	public void setBlocks(Body[][] blocks) {
		this.blocks = blocks;
	}
	public int getSpawnY() {
		return spawnY;
	}
	
}
