package net.wfoas.gh.villager;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.CustomNetworkEvent;
import net.minecraftforge.fml.common.network.NetworkHandshakeEstablished;
import net.wfoas.gh.network.NetworkHandler;
import net.wfoas.gh.network.packet.PacketPlaySyncVillagerProfessionIds;

public class GHVillagerSyncWithServer {
	@EventHandler // net.minecraftforge.fml.common.network.FMLEmbeddedChannel
	public void serverConnected(CustomNetworkEvent event) {
		if (event.wrappedEvent instanceof NetworkHandshakeEstablished) {
			NetworkHandshakeEstablished handshake = (NetworkHandshakeEstablished) event.wrappedEvent;
			// handshake.dispatcher.manager.
		}
	}

	@EventHandler
	public void afterConnect(PlayerEvent.PlayerLoggedInEvent e) {
		NetworkHandler.sendToSpecificPlayer(new PacketPlaySyncVillagerProfessionIds(), (EntityPlayerMP) e.player);
	}
}
