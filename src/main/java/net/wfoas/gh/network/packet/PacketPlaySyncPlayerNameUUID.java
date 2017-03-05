package net.wfoas.gh.network.packet;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.wfoas.gh.network.NetworkUtils;
import net.wfoas.gh.playernameuuid.ClientSidePlayerNameUUIDDataBase;
import net.wfoas.gh.playernameuuid.PlayerNameUUID;
import net.wfoas.gh.proxies.ClientProxy;

public class PacketPlaySyncPlayerNameUUID implements IMessage {

	public NBTTagCompound data = null;

	@Override
	public void fromBytes(ByteBuf buf) {
		data = NetworkUtils.readNBTTagCompoundFromBuffer(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		NBTTagCompound data = new NBTTagCompound();
		NBTTagList nbttl = new NBTTagList();
		for (Entry<UUID, String> entries : PlayerNameUUID.playerNameUUIDMap.entrySet()) {
			NBTTagCompound pnuuid = new NBTTagCompound();
			pnuuid.setString("name", entries.getValue());
			pnuuid.setLong("uuid_msb", entries.getKey().getMostSignificantBits());
			pnuuid.setLong("uuid_lsb", entries.getKey().getLeastSignificantBits());
			nbttl.appendTag(pnuuid);
		}
		data.setTag("player_name_uuid_list", nbttl);
		NetworkUtils.writeNBTTagCompoundToBuffer(data, buf);
	}

	public static class PlayerPlaySyncPlayerNameUUIDHandler
			implements IMessageHandler<PacketPlaySyncPlayerNameUUID, IMessage> {
		@Override
		public IMessage onMessage(PacketPlaySyncPlayerNameUUID message, MessageContext ctx) {
			ClientProxy.playerNameUUIDdb = new ClientSidePlayerNameUUIDDataBase(message);
			return null;
		}
	}
}