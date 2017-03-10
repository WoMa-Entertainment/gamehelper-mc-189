package net.wfoas.gh.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BodenEBA2 extends GameHelperModBlock {

	public BodenEBA2() {
		super(Material.rock, "BodenEBA2");
		this.setHarvestLevel("pickaxe", 3);
		this.setHardness(2f);
		this.setStepSound(Block.soundTypeStone);
	}
}
