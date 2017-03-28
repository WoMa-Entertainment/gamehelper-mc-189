package net.wfoas.gh.ads;

import net.minecraft.util.ChatComponentText;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.dropsapi.pdr.ChatColor;
import net.wfoas.gh.omapi.GameHelperAPI;
import net.wfoas.gh.scheduler.GHScheduler;
import net.wfoas.gh.scheduler.GHSchedulerServer;

public class AdHandler {
	public static void enableAdEcoSystem() {
		GameHelperAPI.ghAPI().ghScheduler().scheduleSyncRepeatingDelayedTask(new Runnable() {
			@Override
			public void run() {
				GameHelper.getUtils()
						.broadcast(new ChatComponentText(ChatColor.GOLD + "" + ChatColor.MAGIC + "|" + ChatColor.RESET
								+ "" + ChatColor.GRAY + "Werbevermietung" + ChatColor.GOLD + "" + ChatColor.MAGIC + "|"
								+ ChatColor.RESET + " " + ChatColor.DARK_GRAY + "Hier könnte ihre Werbung stehen."));
			}
		}, 5 * 60 * 20l, 60 * 20l);
	}
}
