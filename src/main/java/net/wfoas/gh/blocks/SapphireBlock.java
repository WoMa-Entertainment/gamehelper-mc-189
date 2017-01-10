package net.wfoas.gh.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class SapphireBlock extends GameHelperModBlock {

	public SapphireBlock() {
		super(Material.rock, "sapphire_block");
		this.setHarvestLevel("pickaxe", 2);
		this.setHardness(4.5f);
		this.setStepSound(Block.soundTypeStone);
	}

}
