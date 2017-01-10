package net.wfoas.gh.enchantment.event;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.GameHelperCoreModule;

public class DestructionEvent {
	@SubscribeEvent
	public void blockDestroy(BlockEvent.HarvestDropsEvent hde) {
		if (!hde.world.isRemote) {
			if (hde.harvester != null) {
				if (GameHelper.getUtils().hasEnchantment(hde.harvester.getHeldItem(),
						GameHelperCoreModule.ENCH_DESTRUCTION)) {
					String tool = hde.state.getBlock().getHarvestTool(hde.state);
					if (tool != null && hde.harvester.getHeldItem().getItem() instanceof ItemTool) {
						ItemTool it = (ItemTool) hde.harvester.getHeldItem().getItem();
						if (it.canHarvestBlock(hde.state.getBlock()))
							tool = null;
					}
					if (hde.state.getBlock() == Blocks.stonebrick) {
						if (tool == null) {
							hde.drops.clear();
							hde.drops.add(new ItemStack(Blocks.stonebrick, 1, 2));
						}
					} else if (hde.state.getBlock() == Blocks.stone) {
						if (tool == null) {
							hde.drops.clear();
							hde.drops.add(new ItemStack(Blocks.cobblestone, 1));
						}
					} else if (hde.state.getBlock() == Blocks.cobblestone) {
						if (tool == null) {
							hde.drops.clear();
							hde.drops.add(new ItemStack(Blocks.gravel, 1));
						}
					} else if (hde.state.getBlock() == Blocks.gravel) {
						// if (tool == null) {
						hde.drops.clear();
						hde.drops.add(new ItemStack(Blocks.sand, 1));
						// }
					} else if (hde.state.getBlock() == Blocks.dirt) {
						// if (tool == null) {
						hde.drops.clear();
						hde.drops.add(new ItemStack(Blocks.dirt, 1, 1));
						// }
					}
				}
			}
		}
	}
}
