package net.wfoas.gh.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ParkschildEBA extends GameHelperModBlock {

	public ParkschildEBA() {
		super(Material.rock, "ParkschildEBA");
		this.setHarvestLevel("pickaxe", 3);
		this.setHardness(2f);
		this.setStepSound(Block.soundTypeStone);
	}
}
