package net.wfoas.gh.blocks.glass;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Darkglaslightblue extends GameHelperModGlass {
	public Darkglaslightblue() {
		super(Material.glass, "Darkglaslightblue");
		this.setHarvestLevel("pickaxe", 3);
		this.setHardness(2f);
		this.setStepSound(Block.soundTypeGlass);
	}
}
