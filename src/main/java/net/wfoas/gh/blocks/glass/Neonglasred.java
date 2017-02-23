package net.wfoas.gh.blocks.glass;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Neonglasred extends GameHelperModGlass {
	public Neonglasred() {
		super(Material.glass, "Neonglasred");
		this.setHarvestLevel("pickaxe", 3);
		this.setHardness(2f);
		this.setStepSound(Block.soundTypeGlass);
	}
}
