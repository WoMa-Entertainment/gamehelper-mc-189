package net.wfoas.gh.scheduler;

public class GHRepeatingTask extends GHTask {

	long schId;

	public GHRepeatingTask(Runnable runnable, long schId) {
		super(-1l, runnable);
		this.schId = schId;
	}

}
