package net.wfoas.gh.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.wfoas.gh.GHModItemUpdater;
import net.wfoas.gh.GameHelper;

public class GameHelperModBlock extends Block implements GHModItemUpdater, IGHModBlock {

	String name;

	public GameHelperModBlock(Material materialIn, String name) {
		super(materialIn);
		this.name = name;
		this.setUnlocalizedName(GameHelper.MODID + "." + name);
		GameRegistry.registerBlock(this, name);
	}

	public void updateCreativeTab(CreativeTabs tab) {
		this.setCreativeTab(tab);
	}

	// BlockChest
	public String getName() {
		return name;
	}

	@Override
	public void updateInitEvent(CreativeTabs tab) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(this), 0,
				new ModelResourceLocation(GameHelper.MODID + ":" + getName(), "inventory"));
		// RenderItem ri = Minecraft.getMinecraft().getRenderItem();
		// ri.getItemModelMesher().register(Item.getItemFromBlock(this), 0, new
		// ModelResourceLocation(GameHelper.MODID + ":" + this.getName(),
		// "inventory"));
		// ri.getItemModelMesher().register(Item.getItemFromBlock(this), 0, new
		// ModelResourceLocation(GameHelper.MODID + ":" + this.getName(),
		// "facing=south"));
		// ri.getItemModelMesher().register(Item.getItemFromBlock(this), 0, new
		// ModelResourceLocation(GameHelper.MODID + ":" + this.getName(),
		// "facing=north"));
		// ri.getItemModelMesher().register(Item.getItemFromBlock(this), 0, new
		// ModelResourceLocation(GameHelper.MODID + ":" + this.getName(),
		// "facing=west"));
		// ri.getItemModelMesher().register(Item.getItemFromBlock(this), 0, new
		// ModelResourceLocation(GameHelper.MODID + ":" + this.getName(),
		// "facing=east"));
		updateCreativeTab(tab);
	}

}
