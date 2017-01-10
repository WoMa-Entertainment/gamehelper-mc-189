package net.wfoas.gh.scheduler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class GHSchedulerClient implements GHScheduler {

	private volatile long scheduleid;
	private ConcurrentHashMap<Long, GHTask> scheduled = new ConcurrentHashMap();

	@Override
	public long scheduleSyncDelayedTask(Runnable runn, long ticks) {
		long schId = this.scheduleid++;
		this.scheduled.put(Long.valueOf(schId), new GHTask(ticks, runn));
		return schId;
	}

	private void readdGHRepeatingDelayedTaskTask(GHRepeatingDelayedTask ghr) {
		ghr.ticks = ghr.period;
		this.scheduled.put(ghr.schId, ghr);
	}

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			for (Map.Entry<Long, GHTask> entry : this.scheduled.entrySet()) {
				GHTask sch = entry.getValue();
				if (sch instanceof GHRepeatingTask) {
					sch.runnable.run();
					continue;
				}
				sch.ticks -= 1L;
				if (sch.ticks < 0L) {
					try {
						sch.runnable.run();
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (entry.getValue() instanceof GHRepeatingTask) {

					} else if (entry.getValue() instanceof GHRepeatingDelayedTask) {
						GHRepeatingDelayedTask ghr = (GHRepeatingDelayedTask) entry.getValue();
						this.scheduled.remove(entry.getKey());
						readdGHRepeatingDelayedTaskTask(ghr);
					} else {
						this.scheduled.remove(entry.getKey());
					}
				}
			}
		}
	}

	@Override
	public long scheduleSyncRepeatingDelayedTask(Runnable runn, long period, long delay) {
		long schId = this.scheduleid++;
		this.scheduled.put(Long.valueOf(schId), new GHRepeatingDelayedTask(delay, runn, period, schId));
		return schId;
	}

	@Override
	public GHTask cancelGHTask(long schId) {
		if (scheduled.containsKey(Long.valueOf(schId))) {
			return scheduled.remove(Long.valueOf(schId));
		}
		return null;
	}

	@Override
	public long scheduleSyncTickingTask(Runnable runn) {
		long schId = this.scheduleid++;
		this.scheduled.put(Long.valueOf(schId), new GHRepeatingTask(runn, schId));
		return schId;
	}
}
