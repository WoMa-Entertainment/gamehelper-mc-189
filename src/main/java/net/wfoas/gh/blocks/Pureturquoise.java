package net.wfoas.gh.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Pureturquoise extends GameHelperModBlock {
	public Pureturquoise() {
		super(Material.rock, "Pureturquoise");
		this.setHarvestLevel("pickaxe", 3);
		this.setHardness(2f);
		this.setStepSound(Block.soundTypeStone);
}}
