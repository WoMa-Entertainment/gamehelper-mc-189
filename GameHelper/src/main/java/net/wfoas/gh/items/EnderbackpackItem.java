package net.wfoas.gh.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EnderbackpackItem extends GameHelperModItem {

	public EnderbackpackItem() {
		super("ender_backpack");
		setMaxStackSize(1);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		player.displayGUIChest(player.getInventoryEnderChest());
		return itemstack;
	}

}
