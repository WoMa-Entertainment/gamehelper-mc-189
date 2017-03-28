package net.wfoas.gh.network.packet;

import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.network.NetworkUtils;
import net.wfoas.gh.omapi.GameHelperAPI;
import net.wfoas.gh.villager.VillagerRegistrar;

public class PacketPlaySyncVillagerProfessionIds implements IMessage {

	Map<Integer, String> map;

	public PacketPlaySyncVillagerProfessionIds(Map<Integer, String> villagermap) {
		this.map = villagermap;
	}

	public PacketPlaySyncVillagerProfessionIds() {
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		byte[] s = new byte[buf.readInt()];
		buf.readBytes(s);
		map = NetworkUtils.deserializeMapIntStringFromBytes(s);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		byte[] s = NetworkUtils.serializeMapIntStringToBytes(map);
		buf.writeInt(s.length);
		buf.writeBytes(s);
	}

	public static class PacketPlaySyncVillagerProfessionIdsClientHandler
			implements IMessageHandler<PacketPlaySyncVillagerProfessionIds, IMessage> {
		@Override
		public IMessage onMessage(final PacketPlaySyncVillagerProfessionIds message, MessageContext ctx) {
			GameHelperAPI.ghAPI().ghScheduler().scheduleSyncDelayedTask(new Runnable() {
				@Override
				public void run() {
					GameHelper.getLogger().log(Level.INFO, "Syncing villager professions with server");
					for (Map.Entry<Integer, String> ntry : message.map.entrySet()) {
						GameHelper.getLogger().log(Level.DEBUG,
								"ID: " + ntry.getKey() + ", Villager: " + ntry.getValue());
					}
					VillagerRegistrar.registerProfessionListClientSide(message.map);
				}
			}, 1l);
			return null;
		}
	}
}
