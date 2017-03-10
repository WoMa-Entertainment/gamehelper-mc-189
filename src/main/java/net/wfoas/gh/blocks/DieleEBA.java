package net.wfoas.gh.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class DieleEBA extends GameHelperOrientedModBlock {

	public DieleEBA() {
		super(Material.rock, "DieleEBA");
		this.setHarvestLevel("pickaxe", 3);
		this.setHardness(2f);
		this.setStepSound(Block.soundTypeStone);
	}
}
