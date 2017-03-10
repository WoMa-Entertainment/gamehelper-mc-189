package net.wfoas.gh.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class SteinEBA extends GameHelperModBlock {

	public SteinEBA() {
		super(Material.rock, "SteinEBA");
		this.setHarvestLevel("pickaxe", 3);
		this.setHardness(2f);
		this.setStepSound(Block.soundTypeStone);
	}
}
