package net.wfoas.gh.scheduler;

public class GHRepeatingDelayedTask extends GHTask {

	long period, schId;

	public GHRepeatingDelayedTask(long ticks, Runnable runnable, long period, long schId) {
		super(ticks, runnable);
		this.period = period;
		this.schId = schId;
	}

}
