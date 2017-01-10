package net.wfoas.gh.titanmodule.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.blocks.GameHelperModBlock;
import net.wfoas.gh.titanmodule.TitanModule;

public class TitanOre extends GameHelperModBlock{

	public TitanOre() {
		super(Material.rock, "titan_ore");
		this.setHarvestLevel("pickaxe", 2);
		this.setHardness(3.5f);
		this.setStepSound(Block.soundTypeStone);
		this.setCreativeTab(TitanModule.TITAN_TAB);
		
	}
	
    @Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune){
    	return TitanModule.TITAN_CRYSTAL;
    }
    
    @Override
	public int quantityDropped(IBlockState state, int fortune, Random random)
	{
	    return 1 + random.nextInt(fortune + 1);
	}

}
