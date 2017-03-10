package net.wfoas.gh.blocks.stairs;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.wfoas.gh.GHModItemUpdater;
import net.wfoas.gh.blocks.IGHModBlock;

public class GameHelperModStairs extends BlockStairs implements IGHModBlock, GHModItemUpdater {

	String name;

	protected GameHelperModStairs(IBlockState modelState) {
		super(modelState);
		if (modelState.getBlock() instanceof IGHModBlock) {
			name = ((IGHModBlock) modelState.getBlock()).getName() + "_stairs";
		}
		GameRegistry.registerBlock(this, name);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void updateInitEvent(CreativeTabs tab) {

	}

	@Override
	public void updateCreativeTab(CreativeTabs tab) {

	}

}
