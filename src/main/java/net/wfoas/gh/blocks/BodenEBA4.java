package net.wfoas.gh.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BodenEBA4 extends GameHelperModBlock {

	public BodenEBA4() {
		super(Material.rock, "BodenEBA4");
		this.setHarvestLevel("pickaxe", 3);
		this.setHardness(2f);
		this.setStepSound(Block.soundTypeStone);
	}
}
