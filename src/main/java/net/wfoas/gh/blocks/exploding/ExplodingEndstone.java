package net.wfoas.gh.blocks.exploding;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class ExplodingEndstone extends GameHelperExplodingStone {

	public ExplodingEndstone() {
		super(Material.rock, "exploding_end_stone");
		this.blockHardness = 3.21f;
	}

	public String getHarvestTool(IBlockState state) {
		return "pickaxe";
	}

}
