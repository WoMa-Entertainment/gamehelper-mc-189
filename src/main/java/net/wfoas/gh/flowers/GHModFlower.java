package net.wfoas.gh.flowers;

import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase.FlowerEntry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.wfoas.gh.GHModItemUpdater;
import net.wfoas.gh.GameHelper;

public class GHModFlower extends BlockBush implements GHModItemUpdater {

	String name;

	public GHModFlower(String name) {
		super(Material.plants);
		this.name = name;
		this.setUnlocalizedName(GameHelper.MODID + "." + name);
		GameRegistry.registerBlock(this, name);
		this.setStepSound(soundTypeGrass);
	}

	@Override
	public void updateInitEvent(CreativeTabs tab) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(this), 0,
				new ModelResourceLocation(GameHelper.MODID + ":" + getName(), "inventory"));
		updateCreativeTab(tab);
	}

	public void updateCreativeTab(CreativeTabs tab) {
		this.setCreativeTab(tab);
	}

	public String getName() {
		return name;
	}
}
