package net.wfoas.gh.blocks.glass;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.wfoas.gh.blocks.GameHelperModBlock;

public class Pureglas extends GameHelperModBlock {
	public Pureglas() {
		super(Material.glass, "Pureglas");
		this.setHarvestLevel("pickaxe", 3);
		this.setHardness(2f);
		this.setStepSound(Block.soundTypeGlass);
	}

}
