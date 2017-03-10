package net.wfoas.gh.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class GarageEBA3 extends GameHelperOrientedModBlock {

	public GarageEBA3() {
		super(Material.rock, "GarageEBA3");
		this.setHarvestLevel("pickaxe", 3);
		this.setHardness(2f);
		this.setStepSound(Block.soundTypeStone);
	}
}
