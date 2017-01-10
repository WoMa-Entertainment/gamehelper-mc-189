package net.wfoas.gh.scheduler;

public interface GHScheduler {
	long scheduleSyncDelayedTask(Runnable runn, long ticks);

	long scheduleSyncRepeatingDelayedTask(Runnable runn, long period, long delay);

	long scheduleSyncTickingTask(Runnable runn);

	GHTask cancelGHTask(long schId);
}
