package net.wfoas.gh.craftingresearchfacilities;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.wfoas.gh.blocks.GameHelperModBlock;

public class CraftingResearchTable extends GameHelperModBlock {

	public CraftingResearchTable() {
		super(Material.wood, "crafting_research_table");
		this.setHardness(2.6f);
	}

	@Override
	public boolean isToolEffective(String type, IBlockState state) {
		return "axe".equals(type);
	}

}
