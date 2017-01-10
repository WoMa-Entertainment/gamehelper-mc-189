package net.wfoas.gh.blocks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.wfoas.gh.GameHelper;

public interface IGHModBlock {
	public String getName();
	
	public void updateInitEvent(CreativeTabs tab);
	
	public void updateCreativeTab(CreativeTabs tab);
}
