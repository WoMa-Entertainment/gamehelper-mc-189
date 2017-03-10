package net.wfoas.gh.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class WandEBA extends GameHelperModBlock {

	public WandEBA() {
		super(Material.rock, "WandEBA");
		this.setHarvestLevel("pickaxe", 3);
		this.setHardness(2f);
		this.setStepSound(Block.soundTypeStone);
	}
}
