package net.wfoas.gh.protected_blocks.brewstand;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.wfoas.gh.GHModItemUpdater;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.blocks.IGHModBlock;
import net.wfoas.gh.items.GameHelperModItem;

public class ItemBrewingStandProtected extends ItemBlock implements GHModItemUpdater {

	String s;

	public ItemBrewingStandProtected(Block bl) {
		super(bl);
		s = "protected_brewing_stand";
		this.setUnlocalizedName(GameHelper.MODID + "." + s);
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