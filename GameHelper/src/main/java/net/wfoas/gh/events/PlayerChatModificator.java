package net.wfoas.gh.events;

import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.dropsapi.pdr.ChatColor;

public class PlayerChatModificator {

	@SubscribeEvent
	public void onChat(ServerChatEvent e) {
		e.setComponent(new ChatComponentText(GameHelper.getUtils().getRankFormattedName(e.player) + ChatColor.GRAY
				+ ": " + ChatColor.GRAY + e.message));
	}
}
