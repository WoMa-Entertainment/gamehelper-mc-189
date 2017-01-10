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

public class PacketPlayListOnlinePlayers implements IMessage {

	List<String> onlinePlayers;

	public PacketPlayListOnlinePlayers() {
		onlinePlayers = new ArrayList<String>();
		for (EntityPlayerMP ep : GameHelper.getUtils().getOnlinePlayers()) {
			onlinePlayers.add(ep.getName());
		}
		System.out.println(onlinePlayers);
	}

	public List<String> getOnlinePlayers() {
		return onlinePlayers;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		if (onlinePlayers == null || onlinePlayers.isEmpty()) {
			buf.writeByte(-1);
			return;
		}
		buf.writeByte(onlinePlayers.size());
		for (String s : onlinePlayers) {
			byte[] arry = s.getBytes(GameHelper.UTF_8);
			buf.writeInt(arry.length);
			buf.writeBytes(arry);
		}
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		if (onlinePlayers == null) {
			onlinePlayers = new ArrayList<String>();
		}
		int size = buf.readByte();
		if(size == -1)
			return;
		for (int i = 0; i < size; i++) {
			int bytes = buf.readInt();
			byte[] array = new byte[bytes];
			onlinePlayers.add(new String(array, GameHelper.UTF_8));
		}
	}

	public static class PacketPlayListOnlinePlayersHandler
			implements IMessageHandler<PacketPlayListOnlinePlayers, IMessage> {
		@Override
		public IMessage onMessage(final PacketPlayListOnlinePlayers message, MessageContext ctx) {
			ClientProxy.onlinePlayers.clear();
			ClientProxy.onlinePlayers.addAll(message.onlinePlayers);
			return null;
		}
	}
}
