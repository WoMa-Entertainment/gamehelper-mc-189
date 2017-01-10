package net.wfoas.gh.dropsapi.pdr;

public abstract class PlayerDropRunnableEntity extends PlayerDropRunnable {

	EntityType et;
	String usename;
	public PlayerDropRunnableEntity(boolean useplayerloc,
			String usename, EntityType et) {
		super(useplayerloc);
		this.usename = usename;
		this.et = et;
	}
	
	public String getUsename(){
		return usename;
	}
	
	public EntityType getET(){
		return et;
	}

}
