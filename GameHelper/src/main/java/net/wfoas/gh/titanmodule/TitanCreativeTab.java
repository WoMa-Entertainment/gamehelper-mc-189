package net.wfoas.gh.titanmodule;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.wfoas.gh.GameHelper;

public class TitanCreativeTab extends CreativeTabs {

	public TitanCreativeTab(int index) {
		super(index, "titanTab");
	}
	public TitanCreativeTab() {
		super("titanTab");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return TitanModule.TITAN_SWORD;
	}

}
