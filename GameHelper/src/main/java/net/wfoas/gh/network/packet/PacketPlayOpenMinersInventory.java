package net.wfoas.gh.network.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.gui.GuiHandler;

public class PacketPlayOpenMinersInventory implements IMessage {

	@Override
	public void fromBytes(ByteBuf buf) {
	}

	@Override
	public void toBytes(ByteBuf buf) {
	}

	public static class PacketPlayOpenMinersInventoryHandler
			implements IMessageHandler<PacketPlayOpenMinersInventory, IMessage> {
		@Override
		public IMessage onMessage(final PacketPlayOpenMinersInventory message, final MessageContext ctx) {
			GameHelper.getScheduler().scheduleSyncDelayedTask(new Runnable() {
				@Override
				public void run() {
					if (!ctx.getServerHandler().playerEntity.worldObj.isRemote) {
						ctx.getServerHandler().playerEntity.closeContainer();
						ctx.getServerHandler().playerEntity.openGui(GameHelper.instance, GuiHandler.MINERS_INVENTORY,
								ctx.getServerHandler().playerEntity.worldObj, 0, 0, 0);
					}
				}
			}, 0l);
			return null;
		}

	}
}
