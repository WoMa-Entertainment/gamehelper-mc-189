package net.wfoas.gh.network.securedlogin;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.network.NetworkHandler;
import net.wfoas.gh.omapi.GameHelperAPI;

public class PlayerConnectedSecuredLogin {
	@SubscribeEvent
	public void login(final PlayerLoggedInEvent event) {
		if (!event.player.worldObj.isRemote) {
			LoginDataHolder.add((EntityPlayerMP) event.player);
			GameHelperAPI.ghAPI().ghScheduler().scheduleSyncDelayedTask(new Runnable() {
				public void run() {
					NetworkHandler.sendToSpecificPlayer(new PacketPlayPasswordAuthRequest(),
							(EntityPlayerMP) event.player);
				}
			}, 1l);
			GameHelperAPI.ghAPI().ghScheduler().scheduleSyncDelayedTask(new Runnable() {
				public void run() {
					NetworkHandler.sendToSpecificPlayer(new PacketPlayPasswordAuthRequest(),
							(EntityPlayerMP) event.player);
				}
			}, 10l);
			GameHelperAPI.ghAPI().ghScheduler().scheduleSyncDelayedTask(new Runnable() {
				public void run() {
					NetworkHandler.sendToSpecificPlayer(new PacketPlayPasswordAuthRequest(),
							(EntityPlayerMP) event.player);
				}
			}, 20l);
		}
	}

	@SubscribeEvent
	public void logout(PlayerLoggedOutEvent event) {
		if (!event.player.worldObj.isRemote) {
			if (LoginDataHolder.contains((EntityPlayerMP) event.player)) {
				LoginDataHolder.remove((EntityPlayerMP) event.player);
			}
		}
	}
}
