package net.wfoas.gh.minersinventory.layer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.wfoas.gh.minersinventory.MinersInventory;

public class MinersInventoryHelper {

	public static final String MINERS_INVENTORY = "miners_inventory";

	public static NBTTagCompound returnNBTTagCompoundForMinersInventory(EntityPlayer ep) {
		if (ep == null)
			return null;
		NBTTagCompound nbttc = (NBTTagCompound) ep.getEntityData().getTag(MINERS_INVENTORY);
		if (nbttc == null) {
			ep.getEntityData().setTag(MINERS_INVENTORY, new NBTTagCompound());
			nbttc = (NBTTagCompound) ep.getEntityData().getTag(MINERS_INVENTORY);
		}
		return nbttc;
	}

	public static NBTTagCompound returnNBTTagCompoundBackpack(EntityPlayer ep) {
		MinersInventory mi = getMinersInventory(ep);
		ItemStack stack = mi.getStackInSlot(MinersInventory.MINERS_BACKPACK);
		if (stack == null)
			return null;
		if (stack.getTagCompound() == null)
			stack.setTagCompound(new NBTTagCompound());
		return stack.getTagCompound();
	}

	public static NBTTagCompound returnNBTTagCompoundSlot(EntityPlayer ep, int slot) {
		MinersInventory mi = getMinersInventory(ep);
		ItemStack stack = mi.getStackInSlot(slot);
		if (stack == null)
			return null;
		if (stack.getTagCompound() == null)
			stack.setTagCompound(new NBTTagCompound());
		return stack.getTagCompound();
	}

	public static MinersInventory getMinersInventory(EntityPlayer ep) {
		return new MinersInventory(ep);
	}
}
