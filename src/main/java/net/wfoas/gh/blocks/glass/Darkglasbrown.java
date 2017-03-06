package net.wfoas.gh.blocks.glass;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Darkglasbrown extends GameHelperModGlass {
	public Darkglasbrown() {
		super(Material.glass, "Darkglasbrown");
		this.setHarvestLevel("pickaxe", 3);
		this.setHardness(2f);
		this.setStepSound(Block.soundTypeGlass);
	}
}
