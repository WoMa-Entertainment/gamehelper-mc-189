package net.wfoas.gh.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.wfoas.gh.GameHelperCoreModule;

public class AmethystOre extends GameHelperModBlock {

	public AmethystOre() {
		super(Material.rock, "amethyst_ore");
		this.setHarvestLevel("pickaxe", 3);
		this.setHardness(5f);
		this.setStepSound(Block.soundTypeStone);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return GameHelperCoreModule.AMETHYST_ITEM;
	}

	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random) {
		return 1 + random.nextInt(fortune + 1);
	}
}
