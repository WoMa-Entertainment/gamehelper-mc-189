package net.wfoas.gh.blocks.glass;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Neonglaspurple extends GameHelperModGlass {
	public Neonglaspurple() {
		super(Material.glass, "Neonglaspurple");
		this.setHarvestLevel("pickaxe", 3);
		this.setHardness(2f);
		this.setStepSound(Block.soundTypeGlass);
	}
}
