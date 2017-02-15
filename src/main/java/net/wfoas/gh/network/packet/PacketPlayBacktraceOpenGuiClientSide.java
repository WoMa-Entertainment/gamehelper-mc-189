package net.wfoas.gh.network.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.network.NetworkHandler;
import net.wfoas.gh.network.gui.RemoteGuiOpener;
import net.wfoas.gh.protected_blocks.ProtectedBlocksRegistry;

public class PacketPlayBacktraceOpenGuiClientSide implements IMessage {

	int id, x, y, z;

	public PacketPlayBacktraceOpenGuiClientSide(int id, int x, int y, int z) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public PacketPlayBacktraceOpenGuiClientSide() {
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		id = buf.readInt();
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(id);
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
	}

	public static class PacketPlayBacktraceOpenGuiClientSideHandler
			implements IMessageHandler<PacketPlayBacktraceOpenGuiClientSide, IMessage> {

		@Override
		public IMessage onMessage(final PacketPlayBacktraceOpenGuiClientSide message, final MessageContext ctx) {
			ProtectedBlocksRegistry.checkDataAndUpdateData(message.id, message.x, message.y, message.z,
					ctx.getServerHandler().playerEntity.worldObj, ctx);
			GameHelper.getScheduler().scheduleSyncDelayedTask(new Runnable() {
				@Override
				public void run() {
					// ctx.getServerHandler().playerEntity.worldObj
					// .getTileEntity(new BlockPos(message.x, message.y,
					// message.z)).markDirty();
					// ctx.getServerHandler().sendPacket(ctx.getServerHandler().playerEntity.worldObj
					// .getTileEntity(new BlockPos(message.x, message.y,
					// message.z)).getDescriptionPacket());
					RemoteGuiOpener.openRemoteClientGUI(ctx.getServerHandler().playerEntity, message.id, message.x,
							message.y, message.z);
				}
			}, 1l);
			return null;
		}

	}

}
