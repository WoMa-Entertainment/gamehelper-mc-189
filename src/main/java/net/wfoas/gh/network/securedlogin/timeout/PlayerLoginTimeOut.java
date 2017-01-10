package net.wfoas.gh.network.securedlogin.timeout;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Semaphore;

import de.winston.utils.ListEntry;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentTranslation;
import net.wfoas.gh.GameHelper;

public class PlayerLoginTimeOut {
	static List<Map.Entry<EntityPlayerMP, Long>> playerMpTimeOut = new ArrayList<Map.Entry<EntityPlayerMP, Long>>();
	protected static long taskId;
	// static Semaphore _mutex = new Semaphore(1);

	public static void queuePlayerForTimeOut(EntityPlayerMP playerMP, long ticks) {
		synchronized (playerMpTimeOut) {
			playerMpTimeOut.add(new ListEntry(playerMP, Long.valueOf(ticks)));
		}
	}

	public static void queuePlayerForTimeOut(EntityPlayerMP playerMP) {
		queuePlayerForTimeOut(playerMP, 2400);
	}

	public static void startLoginTimeOutQueueTask() {
		taskId = GameHelper.getScheduler().scheduleSyncTickingTask(new Runnable() {
			@Override
			public void run() {
				// System.out.println("update");
				updateThisTick();
			}
		});
	}

	public static void stopServer() {
		GameHelper.getScheduler().cancelGHTask(taskId);

	}

	protected static void updateThisTick() {
		synchronized (playerMpTimeOut) {
			ListIterator<Entry<EntityPlayerMP, Long>> itr = playerMpTimeOut.listIterator();
			while (itr.hasNext()) {
				Map.Entry<EntityPlayerMP, Long> ntry = itr.next();
				if (ntry.getValue() <= 0l) {
					System.out.println("timeout: " + ntry.getKey().getName());
					// ntry.getKey().playerNetServerHandler
					// .onDisconnect(new
					// ChatComponentTranslation("gamehelper.msg.password.timeout"));
					// playerMpTimeOut.listIterator().remove();
				} else {
					ntry.setValue(Long.valueOf(ntry.getValue() - 1l));
					System.out.println(ntry.getKey().getName() + ": " + ntry.getValue());
				}
			}
		}
	}
}