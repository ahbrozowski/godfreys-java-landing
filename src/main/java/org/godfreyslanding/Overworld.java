package org.godfreyslanding;

public class Overworld extends Biome {
	public Overworld(int x, int y, int width, int height) {
		super(x, y, width, height, 1);
		// TODO Auto-generated constructor stub
	}
	@Override
	public SpawnPackage getPackage(Player p, Time t, double x, double y) {
		// TODO Auto-generated method stub
		double h = t.getHour();
		SpawnPackage pack = new SpawnPackage(true, new Zombie(x,y,p));
		if(h > 0 && h < 14) { 
			pack.setSpawn(false);
		}
		return pack;
	}
	
	@Override 
	public Body getSkyBlock(double x,double y) {
		return new Sky(x,y);
	}
	
	

}
