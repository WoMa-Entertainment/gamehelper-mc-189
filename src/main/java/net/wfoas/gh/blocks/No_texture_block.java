package net.wfoas.gh.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class No_texture_block extends GameHelperModBlock {
	public No_texture_block() {
		super(Material.rock, "No_texture_block");
		this.setHarvestLevel("pickaxe", 3);
		this.setHardness(3f);
		this.setStepSound(Block.soundTypeStone);
	}
}
