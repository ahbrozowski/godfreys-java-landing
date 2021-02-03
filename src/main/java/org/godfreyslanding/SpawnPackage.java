package org.godfreyslanding;

public class SpawnPackage {
	boolean spawn;
	Entity mob;
	
	public SpawnPackage(boolean spawn, Entity mob) {
		super();
		this.spawn = spawn;
		this.mob = mob;
	}
	
	public boolean canSpawn() {
		return spawn;
	}
	public void setSpawn(boolean spawn) {
		this.spawn = spawn;
	}
	public Entity getMob() {
		return mob;
	}
	public void setMob(Entity mob) {
		this.mob = mob;
	}
	
}
