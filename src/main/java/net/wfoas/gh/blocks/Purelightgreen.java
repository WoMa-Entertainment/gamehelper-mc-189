package net.wfoas.gh.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Purelightgreen extends GameHelperModBlock {
	public Purelightgreen() {
		super(Material.rock, "Purelightgreen");
		this.setHarvestLevel("pickaxe", 3);
		this.setHardness(2f);
		this.setStepSound(Block.soundTypeStone);
}}
