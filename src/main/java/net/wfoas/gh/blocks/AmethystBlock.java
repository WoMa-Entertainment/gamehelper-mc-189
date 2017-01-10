package net.wfoas.gh.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class AmethystBlock extends GameHelperModBlock {

	public AmethystBlock() {
		super(Material.rock, "amethyst_block");
		this.setHarvestLevel("pickaxe", 3);
		this.setHardness(4.5f);
		this.setStepSound(Block.soundTypeStone);
	}

}
