package net.wfoas.gh.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BodenEBA3 extends GameHelperModBlock {

	public BodenEBA3() {
		super(Material.rock, "BodenEBA3");
		this.setHarvestLevel("pickaxe", 3);
		this.setHardness(2f);
		this.setStepSound(Block.soundTypeStone);
	}
}
