package net.wfoas.gh.blocks.glass;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Neonglasmagenta extends GameHelperModGlass {
	public Neonglasmagenta() {
		super(Material.glass, "Neonglasmagenta");
		this.setHarvestLevel("pickaxe", 3);
		this.setHardness(2f);
		this.setStepSound(Block.soundTypeGlass);
	}
}
