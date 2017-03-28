package net.wfoas.gh.network.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.ILockableContainer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.GameHelperCoreModule;
import net.wfoas.gh.network.NetworkUtils;
import net.wfoas.gh.omapi.GameHelperAPI;
import net.wfoas.gh.protected_blocks.ProtectedBlockWrapper;
import net.wfoas.gh.protected_blocks.chest.GuiContainerProtectedChest;
import net.wfoas.gh.protected_blocks.chest.InventoryLargeProtectedChest;
import net.wfoas.gh.protected_blocks.chest.ProtectedChestTileEntity;
import net.wfoas.gh.protected_blocks.chest.ProtectedChestTileEntityBlock;

public class PacketPlayInformServerAboutNewPermissions implements IMessage {
	public PacketPlayInformServerAboutNewPermissions() {
	}

	int worldId = 0;
	BlockPos pos = null;
	NBTTagCompound data = null;

	public PacketPlayInformServerAboutNewPermissions(ProtectedBlockWrapper wrapper) {
		worldId = wrapper.w.provider.getDimensionId();
		pos = wrapper.posi;
		data = wrapper.nbt_prot_data;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		worldId = buf.readInt();
		pos = NetworkUtils.readBlockPos(buf);
		data = NetworkUtils.readNBTTagCompoundFromBuffer(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(worldId);
		NetworkUtils.writeBlockPos(pos, buf);
		NetworkUtils.writeNBTTagCompoundToBuffer(data, buf);
	}

	public static class PacketPlayInformServerAboutNewPermissionsHandler
			implements IMessageHandler<PacketPlayInformServerAboutNewPermissions, IMessage> {

		@Override
		public IMessage onMessage(final PacketPlayInformServerAboutNewPermissions message, final MessageContext ctx) {
			GameHelperAPI.ghAPI().ghScheduler().scheduleSyncDelayedTask(new Runnable() {
				@Override
				public void run() {
					if (ctx.getServerHandler().playerEntity.worldObj.getTileEntity(new BlockPos(message.pos)) != null) {
						TileEntity te = ctx.getServerHandler().playerEntity.worldObj
								.getTileEntity(new BlockPos(message.pos));
						te.getTileData().merge(message.data);
						if (te instanceof ProtectedChestTileEntity) {
							ProtectedChestTileEntity pcte = (ProtectedChestTileEntity) te;
							ILockableContainer oc = ((ProtectedChestTileEntityBlock) GameHelperCoreModule.SEC_CHEST)
									.getLockableContainer(ctx.getServerHandler().playerEntity.worldObj,
											new BlockPos(message.pos));
							if (oc instanceof InventoryLargeProtectedChest) {
								InventoryLargeProtectedChest lpc = (InventoryLargeProtectedChest) oc;
								if (lpc.getILockContainerProtected()[0] instanceof ProtectedChestTileEntity) {
									ProtectedChestTileEntity te1 = (ProtectedChestTileEntity) lpc
											.getILockContainerProtected()[0];
									te1.getTileData().merge(message.data);
								}
								if (lpc.getILockContainerProtected()[1] instanceof ProtectedChestTileEntity) {
									ProtectedChestTileEntity te1 = (ProtectedChestTileEntity) lpc
											.getILockContainerProtected()[1];
									te1.getTileData().merge(message.data);
								}
							}
						}
					}
				}
			}, 1l);
			return null;
		}
	}
}