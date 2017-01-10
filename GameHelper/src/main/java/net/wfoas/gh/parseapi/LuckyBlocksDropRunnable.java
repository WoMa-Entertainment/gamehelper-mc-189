package net.wfoas.gh.parseapi;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;

public interface LuckyBlocksDropRunnable {
	void executeLuckyBlockDrop(EntityPlayer player, BlockPos pos);
}
