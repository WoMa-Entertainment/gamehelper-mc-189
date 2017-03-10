package net.wfoas.gh.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class DieleEBA3 extends GameHelperModBlock {

	public DieleEBA3() {
		super(Material.rock, "DieleEBA3");
		this.setHarvestLevel("pickaxe", 3);
		this.setHardness(2f);
		this.setStepSound(Block.soundTypeStone);
	}
}
