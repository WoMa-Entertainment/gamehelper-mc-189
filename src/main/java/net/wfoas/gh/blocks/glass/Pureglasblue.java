package net.wfoas.gh.blocks.glass;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.wfoas.gh.blocks.GameHelperModBlock;

public class Pureglasblue extends GameHelperModBlock {
	public Pureglasblue() {
		super(Material.glass, "Pureglasblue");
		this.setHarvestLevel("pickaxe", 3);
		this.setHardness(2f);
		this.setStepSound(Block.soundTypeGlass);
	}
}
