package net.wfoas.gh.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class DieleEBA2 extends GameHelperModBlock {

	public DieleEBA2() {
		super(Material.rock, "DieleEBA2");
		this.setHarvestLevel("pickaxe", 3);
		this.setHardness(2f);
		this.setStepSound(Block.soundTypeStone);
	}
}
