package net.wfoas.gh.blocks.stairs;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.wfoas.gh.GHModItemUpdater;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.blocks.IGHModBlock;

public class GameHelperModStairs extends BlockStairs implements IGHModBlock, GHModItemUpdater {

	String name;

	public GameHelperModStairs(IBlockState modelState) {
		super(modelState);
		if (modelState.getBlock() instanceof IGHModBlock) {
			name = ((IGHModBlock) modelState.getBlock()).getName() + "_stairs";
		} else {
			name = modelState.getBlock().getRegistryName() + "_stairs";
		}
		this.setUnlocalizedName(GameHelper.MODID + "." + name);
		GameRegistry.registerBlock(this, name);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void updateInitEvent(CreativeTabs tab) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(this), 0,
				new ModelResourceLocation(GameHelper.MODID + ":" + getName(), "inventory"));
		updateCreativeTab(tab);
	}

	@Override
	public void updateCreativeTab(CreativeTabs tab) {
		this.setCreativeTab(tab);
	}

}
