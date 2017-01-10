package net.wfoas.gh.backpack.tiny;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerItem extends Container {

	public final InventoryItem inventory;
	public final EntityPlayer player;
	private static final int INV_START = InventoryItem.INV_SIZE, INV_END = INV_START + 26, HOTBAR_START = INV_END + 1,
			HOTBAR_END = HOTBAR_START + 8;

	public ContainerItem(EntityPlayer par1Player, InventoryPlayer inventoryPlayer, InventoryItem inventoryItem) {
		this.player = par1Player;
		this.inventory = inventoryItem;
		int i;
		for (i = 0; i < InventoryItem.INV_SIZE; ++i) {
//			this.addSlotToContainer(new SlotItemInv(this.inventory, i, 8 + (18 * (i / 3)), 18 + (18 * (i % 3))));
			this.addSlotToContainer(new SlotItemInv(this.inventory, i, 8 + (18 * (i % 9)), 18 + (18 * (i / 9))));
		}
		for (i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 11 + 8 + j * 18, 1 + 84 + i * 18));
			}
		}

		for (i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(inventoryPlayer, i, 11 + 8 + i * 18, 1 + 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return inventory.isUseableByPlayer(entityplayer);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int index) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index < INV_START) {
				if (!this.mergeItemStack(itemstack1, INV_START, HOTBAR_END + 1, true)) {
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			} else {
				if (index >= INV_START) {
					if (!this.mergeItemStack(itemstack1, 0, INV_START, false)) {
						return null;
					}
				}
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
		}

		return itemstack;
	}

	@Override
	public ItemStack slotClick(int slot, int button, int flag, EntityPlayer player) {
		if (slot >= 0 && getSlot(slot) != null && getSlot(slot).getStack() == player.getHeldItem()) {
			return null;
		}
		return super.slotClick(slot, button, flag, player);
	}
}
