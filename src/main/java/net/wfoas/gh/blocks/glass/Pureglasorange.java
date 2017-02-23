package net.wfoas.gh.blocks.glass;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.wfoas.gh.blocks.GameHelperModBlock;

public class Pureglasorange extends GameHelperModBlock {
	public Pureglasorange() {
		super(Material.glass, "Pureglasorange");
		this.setHarvestLevel("pickaxe", 3);
		this.setHardness(2f);
		this.setStepSound(Block.soundTypeGlass);
	}

}
