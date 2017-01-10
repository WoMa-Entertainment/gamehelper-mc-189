package net.wfoas.gh.trade;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public class TradeInventory implements IInventory {
	public static final int SIZE = 54;
	ItemStack[] T1_ITEMS = new ItemStack[27];
	ItemStack[] T2_ITEMS = new ItemStack[27];
	EntityPlayer T1, T2;

	public TradeInventory(EntityPlayerMP t1, EntityPlayerMP t2) {
		this.T1 = t1;
		this.T2 = t2;
	}

	@Override
	public String getName() {
		return "gamehelper.container.tradeinv.title";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public IChatComponent getDisplayName() {
		return new ChatComponentTranslation(getName());
	}

	@Override
	public int getSizeInventory() {
		return SIZE;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		if (index < 0 || index >= 54)
			return null;
		if (index >= 27) {
			return T2_ITEMS[index - 27];
		} else {
			return T1_ITEMS[index];
		}
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return null;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		if (index < 0 || index >= 54)
			return null;
		if (index >= 27) {
			ItemStack is = T2_ITEMS[index - 27];
			T2_ITEMS[index - 27] = null;
			return is;
		} else {
			ItemStack is = T1_ITEMS[index];
			T1_ITEMS[index] = null;
			return is;
		}
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if (index < 0 || index >= 54)
			return;
		if (index >= 27) {
			T2_ITEMS[index - 27] = stack;
		} else {
			T1_ITEMS[index] = stack;
		}
	}

	@Override
	public int getInventoryStackLimit() {
		return 0;
	}

	@Override
	public void markDirty() {

	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return false;
	}

	@Override
	public void openInventory(EntityPlayer player) {

	}

	@Override
	public void closeInventory(EntityPlayer player) {
		dropWholeInventoryContent();
	}

	private void dropWholeInventoryContent() {
		for (int i = 0; i < SIZE / 2; i++) {
			T1.dropPlayerItemWithRandomChoice(removeStackFromSlot(i), false);
		}
		for (int i = SIZE / 2; i < SIZE; i++) {
			T2.dropPlayerItemWithRandomChoice(removeStackFromSlot(i), false);
		}
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {

	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {

	}

}
