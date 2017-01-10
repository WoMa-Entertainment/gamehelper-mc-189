package net.wfoas.gh.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.wfoas.gh.network.gui.RemoteGuiOpener;

public class MobileWorkbenchItem extends GameHelperModItem {

	public MobileWorkbenchItem() {
		super("mobile_crafting_table");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
		if (!worldIn.isRemote) {
			RemoteGuiOpener.openCraftingTable((EntityPlayerMP) playerIn);
		}
		return itemStackIn;
	}

}
