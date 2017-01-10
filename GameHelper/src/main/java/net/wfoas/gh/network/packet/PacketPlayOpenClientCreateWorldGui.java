package net.wfoas.gh.network.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.gui.GuiHandler;

public class PacketPlayOpenClientCreateWorldGui implements IMessage {
	public PacketPlayOpenClientCreateWorldGui() {
	}

	@Override
	public void fromBytes(ByteBuf buf) {
	}

	@Override 
	public void toBytes(ByteBuf buf) {
	}

	public static class PacketPlayOpenClientCreateWorldGuiHandler
			implements IMessageHandler<PacketPlayOpenClientCreateWorldGui, IMessage> {

		@Override
		public IMessage onMessage(final PacketPlayOpenClientCreateWorldGui message, MessageContext ctx) {
			// IThreadListener mainThread = (WorldServer)
			// ctx.getServerHandler().playerEntity.worldObj; // or
			// Minecraft.getMinecraft()
			// on
			// the
			// client
			Minecraft.getMinecraft().thePlayer.openGui(GameHelper.instance, GuiHandler.CREATE_IMPORT_WORLD_DIALOG,
					Minecraft.getMinecraft().thePlayer.getEntityWorld(), 0, 0, 0);
			return null; // no response in this case
		}

	}
}
