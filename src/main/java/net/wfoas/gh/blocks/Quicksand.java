package net.wfoas.gh.blocks;

import net.minecraft.block.material.Material;

public class Quicksand extends GameHelperModBlock {

	public Quicksand() {
		super(Material.sand, "quicksand");
		this.setStepSound(soundTypeSand);
		this.setHardness(0.45f);
	}

}
