package net.wfoas.gh.titanmodule.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.blocks.GameHelperModBlock;
import net.wfoas.gh.titanmodule.TitanModule;

public class CitrinOre extends GameHelperModBlock {

	public CitrinOre() {
		super(Material.rock, "citrin_ore");
		this.setHarvestLevel("pickaxe", 2);
		this.setHardness(2.95f);
		this.setStepSound(Block.soundTypeStone);
		this.setCreativeTab(TitanModule.TITAN_TAB);
	}

}
