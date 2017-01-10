package net.wfoas.gh.armor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.wfoas.gh.GHModItemUpdater;
import net.wfoas.gh.GameHelper;

public class GHModItemArmor extends ItemArmor implements GHModItemUpdater {
	String s;

	public GHModItemArmor(String unlocname, ArmorMaterial material, int renderIndex, int armorType) {
		super(material, renderIndex, armorType);
		this.s = unlocname;
		this.setUnlocalizedName(GameHelper.MODID + "." + s);
		GameRegistry.registerItem(this, unlocname);
	}

	@Override
	public void updateInitEvent(CreativeTabs tab) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(this, 0,
				new ModelResourceLocation(GameHelper.MODID + ":" + s, "inventory"));
		this.setCreativeTab(tab);
	}

	public String getName() {
		return s;
	}

}
