package net.wfoas.gh.luckyblocksmodule;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.wfoas.gh.blocks.GameHelperModBlock;

public class RainbowBlock extends GameHelperModBlock {

	public RainbowBlock(String name) {
		super(Material.rock, name);
	}

	@Override
	public void updateInitEvent(CreativeTabs ct) {
		super.updateInitEvent(ct);
	}
	
}