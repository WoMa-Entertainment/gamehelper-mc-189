package net.wfoas.gh.network.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.omapi.GameHelperAPI;

public class PacketPlayOpenPlayersInventory implements IMessage {

	@Override
	public void fromBytes(ByteBuf buf) {
	}

	@Override
	public void toBytes(ByteBuf buf) {
	}

	public static class PacketPlayOpenMinersInventoryHandler
			implements IMessageHandler<PacketPlayOpenPlayersInventory, IMessage> {
		@Override
		public IMessage onMessage(final PacketPlayOpenPlayersInventory message, final MessageContext ctx) {
			GameHelperAPI.ghAPI().ghScheduler().scheduleSyncDelayedTask(new Runnable() {
				@Override
				public void run() {
					if (!ctx.getServerHandler().playerEntity.worldObj.isRemote) {
						ctx.getServerHandler().playerEntity.closeContainer();
					}
				}
			}, 0l);
			return null;
		}

	}
}
