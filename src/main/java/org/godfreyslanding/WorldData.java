package org.godfreyslanding;

import java.awt.Color;
import java.util.ArrayList;

public class WorldData {
	Body[][] blocks;
	int spawnY = 0;
	Time t;
	ArrayList<Biome> biomes = new ArrayList<>();
	public WorldData(Body[][] blocks) {
		t = new Time();
		this.blocks = blocks;
	}
	
	public WorldData(int spawnY, byte[][] codes) {
	    Body[][] blocks = new Body[codes.length][codes[0].length];
	    biomes.add(new Overworld(0,0, codes.length, 100));
	    for(int x = 0; x < codes.length; x++) {
	        for(int y = 0; y < codes.length; y++) {
	            int code = codes[x][y];
	            blocks[x][y] = code == 0 ? makeSkyBlock(2*x, 2*y) : Body.fromCode(code, 2*x, 2*y);
	        }
	    }
	    this.blocks = blocks;
	    this.t = new Time();
	    this.spawnY = spawnY;

	}

	public Body[][] getBlocks() {
		return blocks;
	}

	public void worldGen() {
		
		ArrayList<Double> vals = new ArrayList<>();
		biomes.add(new Overworld(0,0, blocks.length, 100));
		double avg = 0;
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
				
				//System.out.print(noise2d(x,y,false)+ " ");
				double r2 = noise2d(x * (1.0/75.0),y * (1.0/75.0),false) * 1.0 + 
						noise2d(x * (1.0/37.5),y * (1.0/37.5),false) * .5 + 
						noise2d(x * (1.0/18.75),y * (1.0/18.75),false) * .25;
				
				avg += Math.abs(r2);
				double stone = noise2d(x * (1.0/75.0),y * (1.0/75.0),true) * 1.0 + 
						noise2d(x * (1.0/37.5),y * (1.0/37.5),true) * .5 + 
						noise2d(x * (1.0/18.75), y * (1.0/18.75),true) * .25;
				
				//System.out.println(r2d);
				if(y <= (n + 50)) {
					blocks[x][y] = new Sky(2*x,2*y);
					
				 }else {
					if(r2 <  0) {
					    blocks[x][y] = makeSkyBlock(2*x, 2*y);
						//System.out.println(x + " " + y);
					} else if(stone > 0) {
						//System.out.println(x + " " + y);
						blocks[x][y] = Body.fromCode(2, 2*x, 2*y);
					} 
					else {
						blocks[x][y] = Body.fromCode(8, 2*x, 2*y);
					}
				}
			}
			
		}
		
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(vals);
		System.out.println(vals.size());
		System.out.println(" ");
		System.out.print(avg/1000000 + " ");
	}

    private Body makeSkyBlock(int x2, int y2) {
        Body block = new Body(x2,y2,2,2,new MyVector(0,0),Color.LIGHT_GRAY, true, 0, 0, false, 0);
        if(biomes.get(0).containsBody(block)) {
        	block = biomes.get(0).getSkyBlock(x2,y2);
        }
        return block;
    }
	
	double fade(double t) {
		return t*t*t*(t*(t*6.0 -15.0) +10);
		
	}
	static double vals[] = new double[512];
	static int vals2d[] = new int[1024];
	static int ore[] = new int[1024];
	static boolean init = false;
	static boolean init2d = false;
	double grad(double p, boolean isOre) {
		if( init == false) {
			for(int i = 0; i < vals.length; i++) {
				double r = 2* Math.random() -1; 
				vals[i] = r < 0? -1.0:1.0;
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
	
	
	public double grad2d(int g, double x, double y) {
		//System.out.println(g+ " " + x + " " + y);
		switch(g & 3){
    	case 0: return x + y;
    	case 1: return -x + y;
    	case 2: return x - y;
    	case 3: return -x - y;
    	default: return 0;
    	}
			
	}
	
	public static void generateNonRepArray(int[] a) {
		
		for(int i = 0; i < a.length; i++) {
			boolean done = false;
			while(!done) {
				double rand = Math.random() * a.length;
				if(a[(int)rand] == 0) {
					a[(int)rand] = i;
					done = true;
				}
			}
		} System.out.println();
	}
	
	public double noise2d(double x, double y, boolean isOre) {
		
		int xi = (int) Math.floor(x) & 511;
    	int yi = (int) Math.floor(y) & 511;
    	if( init2d == false) {
    		int[] temp = new int[512];
    		generateNonRepArray(temp);
			for (int i=0; i < vals2d.length/2 -1 ; i++) { 
				vals2d[vals2d.length/2+i] = vals2d[i] = temp[i];
    		}
			int[] temp2 = new int[512];
    		generateNonRepArray(temp2);
			for (int i=0; i < ore.length/2 -1 ; i++) { 
				ore[ore.length/2+i] = ore[i] = temp2[i];
    		}
			init2d = true;
		}
    	
  

    	int g1;
    	int g2;
    	int g3;
    	int g4; 
    	if(isOre) {
    		g1 = ore[ore[xi] + yi];
    		g2 = ore[ore[xi + 1] + yi];
    		g3 = ore[ore[xi] + yi + 1];
    		g4 = ore[ore[xi + 1] + yi + 1];
    	}
    	else {
    		g1 = vals2d[vals2d[xi] + yi];
    		g2 = vals2d[vals2d[xi + 1] + yi];
    		g3 = vals2d[vals2d[xi] + yi + 1];
    		g4 = vals2d[vals2d[xi + 1] + yi + 1];
    	}
    	double xf = x - Math.floor(x);
    	double yf = y - Math.floor(y);
    	
    	double d1 = grad2d(g1, xf, yf);
    	double d2 = grad2d(g2, xf - 1, yf);
    	double d3 = grad2d(g3, xf, yf - 1);
    	double d4 = grad2d(g4, xf - 1, yf - 1);
    	
    	double u = fade(xf);
    	double v = fade(yf);
    	
    	double x1Inter = lerp(u, d1, d2);
    	double x2Inter = lerp(u, d3, d4);
    	double yInter = lerp(v, x1Inter, x2Inter);
    	
    	return yInter;
	}
	
	private static double lerp(double amount, double left, double right) {
		return ((1 - amount) * left + amount * right);
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

	public Time getT() {
		return t;
	}

	public void setT(Time t) {
		this.t = t;
	}

	public ArrayList<Biome> getBiomes() {
		return biomes;
	}

	public void setBiomes(ArrayList<Biome> biomes) {
		this.biomes = biomes;
	}
	
	
	
}
