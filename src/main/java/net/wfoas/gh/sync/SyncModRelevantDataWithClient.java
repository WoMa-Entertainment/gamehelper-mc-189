package net.wfoas.gh.sync;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.network.NetworkHandler;
import net.wfoas.gh.network.packet.PacketPlaySyncVillagerProfessionIds;
import net.wfoas.gh.tabheaderfooter.TabHeaderFooterPacketManipulator;
import net.wfoas.gh.villager.VillagerRegistrar;

public class SyncModRelevantDataWithClient {
	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onPlayerJoin(final PlayerLoggedInEvent event) {
		if (!event.player.worldObj.isRemote) {
			GameHelper.getScheduler().scheduleSyncDelayedTask(new Runnable() {
				@Override
				public void run() {
					// owned worlds + online players
					EntityPlayerMP playerMP = (EntityPlayerMP) event.player;
					PacketPlaySyncVillagerProfessionIds villagersync = new PacketPlaySyncVillagerProfessionIds(
							VillagerRegistrar.getStringMap());
					NetworkHandler.sendToSpecificPlayer(villagersync, playerMP);
					TabHeaderFooterPacketManipulator.sendIfActive(playerMP);
				}
			}, 20l);
		}
	}
}
