package net.wfoas.gh.items.tools;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemHoe;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.wfoas.gh.GHModItemUpdater;
import net.wfoas.gh.GameHelper;

public class ItemGHHoeTool extends ItemHoe implements GHModItemUpdater {

	String s;

	public ItemGHHoeTool(String s, ToolMaterial material) {
		super(material);
		this.s = s;
		this.setUnlocalizedName(GameHelper.MODID + "." + s);
		GameRegistry.registerItem(this, s);
	}

	@Override
	public void updateInitEvent(CreativeTabs tab) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(this, 0,
				new ModelResourceLocation(GameHelper.MODID + ":" + getName(), "inventory"));
		this.setCreativeTab(tab);
	}

	public String getName() {
		return s;
	}

}
