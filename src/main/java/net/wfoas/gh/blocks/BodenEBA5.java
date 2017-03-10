package net.wfoas.gh.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BodenEBA5 extends GameHelperModBlock {

	public BodenEBA5() {
		super(Material.rock, "BodenEBA5");
		this.setHarvestLevel("pickaxe", 3);
		this.setHardness(2f);
		this.setStepSound(Block.soundTypeStone);
	}
}
