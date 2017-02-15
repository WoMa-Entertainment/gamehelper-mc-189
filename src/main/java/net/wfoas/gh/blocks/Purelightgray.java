package net.wfoas.gh.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Purelightgray extends GameHelperModBlock {
	public Purelightgray() {
		super(Material.rock, "Purelightgray");
		this.setHarvestLevel("pickaxe", 3);
		this.setHardness(2f);
		this.setStepSound(Block.soundTypeStone);
	}}
