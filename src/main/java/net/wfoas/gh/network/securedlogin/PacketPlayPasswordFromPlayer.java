package net.wfoas.gh.network.securedlogin;

import io.netty.buffer.ByteBuf;
import net.minecraft.command.CommandServerKick;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.config.GHConfigKey;
import net.wfoas.gh.network.NetworkHandler;

public class PacketPlayPasswordFromPlayer implements IMessage {
	public PacketPlayPasswordFromPlayer() {
	}

	String pass;

	public PacketPlayPasswordFromPlayer(String enteredpass) {
		pass = enteredpass;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		byte[] array = new byte[buf.readInt()];
		buf.readBytes(array);
		pass = new String(array, GameHelper.UTF_8);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		byte[] array = pass.getBytes(GameHelper.UTF_8);
		buf.writeInt(array.length);
		buf.writeBytes(array);
	}

	public static class PacketPlayPasswordFromPlayerHandler
			implements IMessageHandler<PacketPlayPasswordFromPlayer, IMessage> {
		@Override
		public IMessage onMessage(PacketPlayPasswordFromPlayer message, MessageContext ctx) {
			if (message.pass.equals(GameHelper.instance.CONFIG.getString(GHConfigKey.LOGIN_PASS))) {
				NetworkHandler.sendToSpecificPlayer(new PacketPlayPasswordCloseDialog(),
						ctx.getServerHandler().playerEntity);
				LoginDataHolder.remove(ctx.getServerHandler().playerEntity);
				ctx.getServerHandler().playerEntity
						.addChatMessage(new ChatComponentTranslation("gamehelper.msg.correctpassword"));
			} else {
				GameHelper.getUtils().kickPlayer("gamehelper.msg.wrongpassword", ctx.getServerHandler().playerEntity);
			}
			return null;
		}
	}
}