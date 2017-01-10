package net.wfoas.gh.protected_blocks.tabs;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.wfoas.gh.items.TradeItems;

public class ProtectedBlock {
	public static Item getIconForBlockPosition(World w, BlockPos te) {
		if (w.getBlockState(te).equals(Blocks.air.getDefaultState()))
			return TradeItems.CANCEL_ITEM;
		return Item.getItemFromBlock(w.getBlockState(te).getBlock());
	}
}
