package net.wfoas.gh.minersinventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.wfoas.gh.minersinventory.slots.MinersBackpackInventorySlot;
import net.wfoas.gh.minersinventory.slots.MinersBeltInventorySlot;
import net.wfoas.gh.minersinventory.slots.MinersDaggerInventorySlot;
import net.wfoas.gh.minersinventory.slots.MinersLightInventorySlot;

public class MinersInventoryContainer extends Container {

	private static final int INV_START = MinersInventory.INV_SIZE, INV_END = INV_START + 26, HOTBAR_START = INV_END + 1,
			HOTBAR_END = HOTBAR_START + 8;

	InventoryPlayer playersInv;
	MinersInventory minersInv;

	public MinersInventoryContainer(InventoryPlayer ep, MinersInventory mi) {
		this.playersInv = ep;
		this.minersInv = mi;
		// wearable slots minersinv
		this.addSlotToContainer(new MinersLightInventorySlot(mi, 0, 8, 8));
		this.addSlotToContainer(new MinersBackpackInventorySlot(mi, 1, 8, 23 + 3));
		this.addSlotToContainer(new MinersBeltInventorySlot(mi, 2, 8, 40 + 4));
		this.addSlotToContainer(new MinersDaggerInventorySlot(mi, 3, 8, 57 + 5));
		// belt-slots
		this.addSlotToContainer(new MinersBeltInventorySlot.MinersBeltPickaxeInventorySlot(mi, 4, 85 - 3, 12 + 6));
		this.addSlotToContainer(new MinersBeltInventorySlot.MinersBeltAxeInventorySlot(mi, 5, 102 - 2, 12 + 6));
		this.addSlotToContainer(new MinersBeltInventorySlot.MinersBeltShovelInventorySlot(mi, 6, 119 - 1, 12 + 6));
		this.addSlotToContainer(new MinersBeltInventorySlot.MinersBeltLadderInventorySlot(mi, 7, 136, 12 + 6));
		this.addSlotToContainer(new MinersBeltInventorySlot.MinersBeltBlockInventorySlot(mi, 8, 153 + 1, 12 + 6));
		// daggerslot
		this.addSlotToContainer(new MinersDaggerInventorySlot.DaggerInventorySlot(mi, 9, 85 - 3, 36 + 18));
		// minersbackpack
		for (int i = 0; i < MinersInventory.INV_SIZE - 10; ++i) {
			Slot s = this.addSlotToContainer(new MinersBackpackInventorySlot.MinersBackpackIngredientInventorySlot(mi,
					i + 10, 8 + (18 * (i % 9)), 18 + (18 * (i / 9)) + 50 + 16 - 2 + 2));
			// System.out.println(s.slotNumber);
		}
		// System.out.println("---END:BACKPACK---");
		// Player Inventory, Slot 9-35, Slot IDs 9-35
		// for (int y = 0; y < 3; ++y) {
		// for (int x = 0; x < 9; ++x) {
		// Slot s = this.addSlotToContainer(new Slot(ep, x + y * 9 + 9, 8 + x *
		// 18, 84 + y * 18));
		// System.out.println(s.slotNumber);
		// }
		// }
		// System.out.println("---END:PLAYERINV---");
		// Player Inventory, Slot 0-8, Slot IDs 36-44
		// for (int x = 0; x < 9; ++x) {
		// Slot s = this.addSlotToContainer(new Slot(ep, x, 8 + x * 18, 142));
		// System.out.println(s.slotNumber);
		// }
		// System.out.println("---END:QUICKBAR---");
		// System.out.println(ep.getSizeInventory());
		int i;
		for (i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(ep, j + i * 9 + 9, 11 + 8 + j * 18 - 9 - 2, 84 + 56 + i * 18 + 8 + 2));
			}
		}

		// PLAYER ACTION BAR - uses default locations for standard action bar
		// texture file
		for (i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(ep, i, 11 + 8 + i * 18 - 9 - 2, 56 + 142 + 8 + 2));
		}
		// System.out.println(this.inventorySlots.size());
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int index) {
		// System.out.println(this.inventorySlots.size());
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
		}
		return itemstack;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void putStacksInSlots(ItemStack[] p_75131_1_) {
		for (int i = 0; i < p_75131_1_.length; ++i) {
			this.getSlot(i).putStack(p_75131_1_[i]);
		}
	}
}