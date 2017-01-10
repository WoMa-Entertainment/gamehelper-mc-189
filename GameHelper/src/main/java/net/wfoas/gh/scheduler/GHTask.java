package net.wfoas.gh.scheduler;

public class GHTask {
	long ticks;
	Runnable runnable;

	public GHTask(long ticks, Runnable runnable) {
		this.ticks = ticks;
		this.runnable = runnable;
	}
}
