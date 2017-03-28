package net.wfoas.gh.network.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.gui.GuiHandler;
import net.wfoas.gh.omapi.GameHelperAPI;

public class PacketPlayOpenOwnWorldDialog implements IMessage {

	@Override
	public void fromBytes(ByteBuf buf) {
	}

	@Override
	public void toBytes(ByteBuf buf) {
	}

	public static class PacketPlayOpenOwnWorldDialogHandler
			implements IMessageHandler<PacketPlayOpenOwnWorldDialog, IMessage> {

		@Override
		public IMessage onMessage(final PacketPlayOpenOwnWorldDialog message, final MessageContext ctx) {
			GameHelperAPI.ghAPI().ghScheduler().scheduleSyncDelayedTask(new Runnable() {
				@Override
				public void run() {
					Minecraft.getMinecraft().thePlayer.openGui(GameHelper.instance, GuiHandler.OWNWORLD_DIALOG,
							Minecraft.getMinecraft().thePlayer.getEntityWorld(), 0, 0, 0);
				}
			}, 0l);
			return null;
		}
	}
}