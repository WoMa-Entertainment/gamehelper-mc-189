package net.wfoas.gh.shitmodule;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.wfoas.gh.GameHelper;

public class ShitTab extends CreativeTabs {
	
	

	public ShitTab(int index) {
		super(index, "shitTab");
	}
	public ShitTab() {
		super("shitTab");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return ShitModule.BONG;
	}

}
