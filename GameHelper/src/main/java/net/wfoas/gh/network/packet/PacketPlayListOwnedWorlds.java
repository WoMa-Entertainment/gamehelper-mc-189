package net.wfoas.gh.network.packet;

import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.proxies.ClientProxy;
import net.wfoas.gh.world.owner.WorldOwners;

public class PacketPlayListOwnedWorlds implements IMessage {

	public PacketPlayListOwnedWorlds() {
	}

	List<String> worlds;

	public PacketPlayListOwnedWorlds(EntityPlayerMP player) {
		worlds = WorldOwners.getAllForPlayer(player);
	}

	public List<String> getWorlds() {
		return worlds;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		if (worlds == null) {
			buf.writeByte(-1);
			return;
		}
		buf.writeByte(worlds.size());
		for (String s : worlds) {
			byte[] arry = s.getBytes(GameHelper.UTF_8);
			buf.writeInt(arry.length);
			buf.writeBytes(arry);
		}
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		if (worlds == null) {
			worlds = new ArrayList<String>();
		}
		int size = buf.readByte();
		if(size == -1)
			return;
		for (int i = 0; i < size; i++) {
			int bytes = buf.readInt();
			byte[] array = new byte[bytes];
			worlds.add(new String(array, GameHelper.UTF_8));
		}
	}

	public static class PacketPlayListOwnedWorldsHandler
			implements IMessageHandler<PacketPlayListOwnedWorlds, IMessage> {
		@Override
		public IMessage onMessage(final PacketPlayListOwnedWorlds message, MessageContext ctx) {
			ClientProxy.ownedWorlds.clear();
			ClientProxy.ownedWorlds.addAll(message.worlds);
			return null;
		}
	}
}
