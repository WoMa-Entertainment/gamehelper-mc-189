package net.wfoas.gh.dropsapi.pdr;

public abstract class PlayerDropRunnableWithBroadcast extends PlayerDropRunnable{
	protected String broadcast;
	
	public PlayerDropRunnableWithBroadcast(String tobroad) {
		super(false);
		this.broadcast = tobroad;
	}

}