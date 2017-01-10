package net.wfoas.gh.network.securedlogin;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketPlayPasswordCloseDialog implements IMessage {

	@Override
	public void fromBytes(ByteBuf buf) {
	}

	@Override
	public void toBytes(ByteBuf buf) {
	}

	public static class PacketPlayPasswordCloseDialogHandler
			implements IMessageHandler<PacketPlayPasswordCloseDialog, IMessage> {

		@Override
		public IMessage onMessage(PacketPlayPasswordCloseDialog message, MessageContext ctx) {
			return null;
		}
	}
}