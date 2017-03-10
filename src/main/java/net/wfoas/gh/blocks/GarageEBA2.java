package net.wfoas.gh.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class GarageEBA2 extends GameHelperOrientedModBlock {

	public GarageEBA2() {
		super(Material.rock, "GarageEBA2");
		this.setHarvestLevel("pickaxe", 3);
		this.setHardness(2f);
		this.setStepSound(Block.soundTypeStone);
	}
}
