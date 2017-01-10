package net.wfoas.gh.dropsapi.pdr;

public abstract class PlayerDropRunnableExplosion extends PlayerDropRunnable {

	float expp;
	boolean smoke;
	public PlayerDropRunnableExplosion(boolean useplayerloc, float explosionpower, boolean isSmoking) {
		super(useplayerloc);
		this.expp = explosionpower;
	}
	
	public float getExpP(){
		return expp;
	}
	
	public boolean isSmoking(){
		return smoke;
	}

}