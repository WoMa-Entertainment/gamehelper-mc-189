package net.wfoas.gh.blocks.pressureplates;

import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.BlockPressurePlate.Sensitivity;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.wfoas.gh.GHModItemUpdater;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.blocks.IGHModBlock;

public class GameHelperModPressurePlate extends BlockPressurePlate implements IGHModBlock, GHModItemUpdater {
	String name;

	public GameHelperModPressurePlate(Material materialIn, String name, Sensitivity sensitivityIn) {
		super(materialIn, sensitivityIn);
		this.name = name;
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
