package net.wfoas.gh.blocks.glass;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Neonglaswhite extends GameHelperModGlass {
	public Neonglaswhite() {
		super(Material.glass, "Neonglaswhite");
		this.setHarvestLevel("pickaxe", 3);
		this.setHardness(2f);
		this.setStepSound(Block.soundTypeGlass);
	}
}
