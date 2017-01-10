package net.wfoas.gh.luckyblocksmodule;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.wfoas.gh.GameHelper;

public class LuckyBlocksCreativeTab extends CreativeTabs {
	
	

	public LuckyBlocksCreativeTab(int index) {
		super(index, "luckyTab");
	}
	public LuckyBlocksCreativeTab() {
		super("luckyTab");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return LuckyBlocksModule.RAINBOW_GEM;
	}

}
