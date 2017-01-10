package net.wfoas.gh.network.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketPlayRequestOwnedWorlds implements IMessage {

	@Override
	public void fromBytes(ByteBuf buf) {
	}

	@Override
	public void toBytes(ByteBuf buf) {
	}

	public static class PacketPlayRequestOwnedWorldsHandler
			implements IMessageHandler<PacketPlayRequestOwnedWorlds, PacketPlayListOwnedWorlds> {

		@Override
		public PacketPlayListOwnedWorlds onMessage(PacketPlayRequestOwnedWorlds message, MessageContext ctx) {
			return null;
		}

	}
}
