package net.wfoas.gh.network.securedlogin;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayerMP;
import net.wfoas.gh.network.securedlogin.timeout.PlayerLoginTimeOut;

public class LoginDataHolder {
	protected static List<EntityPlayerMP> currentlyLoggingOnPlayers = new ArrayList<EntityPlayerMP>();

	public static void add(EntityPlayerMP player) {
		synchronized (currentlyLoggingOnPlayers) {
			currentlyLoggingOnPlayers.add(player);
			PlayerLoginTimeOut.queuePlayerForTimeOut(player);
		}
	}

	public static void remove(EntityPlayerMP player) {
		synchronized (currentlyLoggingOnPlayers) {
			currentlyLoggingOnPlayers.remove(player);
		}
	}

	public static boolean contains(EntityPlayerMP player) {
		synchronized (currentlyLoggingOnPlayers) {
			return currentlyLoggingOnPlayers.contains(player);
		}
	}
}
