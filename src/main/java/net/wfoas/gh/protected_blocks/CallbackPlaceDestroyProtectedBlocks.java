package net.wfoas.gh.protected_blocks;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.GameHelperCoreModule;
import net.wfoas.gh.notifysettings.NotifyTable;
import net.wfoas.gh.protected_blocks.chest.ProtectedChestTileEntity;
import net.wfoas.gh.protected_blocks.furnace.ProtectedFurnaceTileEntity;

public class CallbackPlaceDestroyProtectedBlocks {
	public void place(BlockEvent.PlaceEvent p) {
		if (p.world.isRemote)
			return;
		// if (p.placedBlock.getBlock().equals(GameHelperCoreModule.SEC_CHEST))
		// {
		// if (p.getResult() != Result.DENY) {
		// p.setResult(Result.DENY);
		// }
		// }
	}

	public void destroy(BlockEvent.BreakEvent b) {
		if (b.world.isRemote)
			return;
		if (ProtectedBlocksRegistry.isProtectedBlock(b.state.getBlock())) {
			TileEntity tileEntity = b.world.getTileEntity(b.pos);
			if (tileEntity instanceof ProtectedChestTileEntity)
				if (((ProtectedChestTileEntity) tileEntity).isOwner(b.getPlayer())) {
					b.setResult(Result.DENY);
					NotifyTable.notifyPlayer((EntityPlayerMP) b.getPlayer(),
							new ChatComponentTranslation("gamehelper.error.protected_chest.noperm.destroy"));
					EntityPlayerMP target = GameHelper.getUtils()
							.getEntityPlayerByUUID(((ProtectedChestTileEntity) tileEntity).getOwner());
					if (target != null) {
						NotifyTable.notifyPlayer(target, new ChatComponentTranslation(
								"gamehelper.error.protected_chest.destroy.warning", b.getPlayer().getName()));
					}
				}
			if (tileEntity instanceof ProtectedFurnaceTileEntity)
				if (((ProtectedFurnaceTileEntity) tileEntity).isOwner(b.getPlayer())) {
					b.setResult(Result.DENY);
					NotifyTable.notifyPlayer((EntityPlayerMP) b.getPlayer(),
							new ChatComponentTranslation("gamehelper.error.protected_chest.noperm.destroy"));
					EntityPlayerMP target = GameHelper.getUtils()
							.getEntityPlayerByUUID(((ProtectedChestTileEntity) tileEntity).getOwner());
					if (target != null) {
						NotifyTable.notifyPlayer(target, new ChatComponentTranslation(
								"gamehelper.error.protected_chest.destroy.warning", b.getPlayer().getName()));
					}
				}
		}
	}
}