package net.wfoas.gh.blocks.glass;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Darkglaspurple extends GameHelperModGlass {
	public Darkglaspurple() {
		super(Material.glass, "Darkglaspurple");
		this.setHarvestLevel("pickaxe", 3);
		this.setHardness(2f);
		this.setStepSound(Block.soundTypeGlass);
	}
}
