package net.wfoas.gh.blocks.exploding;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class ExplodingNetherrack extends GameHelperExplodingStone {

	public ExplodingNetherrack() {
		super(Material.rock, "exploding_netherrack");
		this.blockHardness = 0.61f;
	}

	public String getHarvestTool(IBlockState state) {
		return "pickaxe";
	}

}
