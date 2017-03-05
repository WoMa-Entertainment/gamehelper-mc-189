package net.wfoas.gh.events;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.wfoas.gh.playernameuuid.PlayerNameUUID;
import net.wfoas.gh.selectiontool.SelectionProperties;

public class PlayerJoin {

	@SubscribeEvent
	public void playerJoin(PlayerLoggedInEvent plie) {

		// if (GameHelper.instance.CONFIG.getBoolean("sql"))
		// plie.
		if (!plie.player.worldObj.isRemote) {
			// NetworkHandler.sendToSpecificPlayer(new
			// PacketPlayListOwnedWorlds((EntityPlayerMP) plie.player),
			// (EntityPlayerMP) plie.player);
			// NetworkHandler.sendToSpecificPlayer(new
			// PacketPlayListOnlinePlayers(), (EntityPlayerMP) plie.player);
			PlayerNameUUID.addPlayer(plie.player);
			plie.player.refreshDisplayName();
			SelectionProperties.setItem("pos_1_x", -1, plie.player);
			SelectionProperties.setItem("pos_1_y", -1, plie.player);
			SelectionProperties.setItem("pos_1_z", -1, plie.player);
			SelectionProperties.setItem("pos_2_x", -1, plie.player);
			SelectionProperties.setItem("pos_2_y", -1, plie.player);
			SelectionProperties.setItem("pos_2_z", -1, plie.player);
			PlayerNameUUID.syncWithEveryClient();
		}
	}
}