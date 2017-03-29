package net.wfoas.gh.food;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.wfoas.gh.GHModItemUpdater;
import net.wfoas.gh.GameHelper;

public class GameHelperModFood extends ItemFood implements GHModItemUpdater {
	String s;

	public GameHelperModFood(String s, int amount, float saturation, boolean isWolfFood) {
		super(amount, saturation, isWolfFood);
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