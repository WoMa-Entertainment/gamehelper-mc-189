package net.wfoas.gh.changechanter;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.wfoas.gh.GameHelperCoreModule;

public class ChangeChanterContainer extends Container {
	public IInventory tableInventory;
	private BlockPos position;
	private Random rand;
	public int[] enchantLevels;
	public int[] field_178151_h;
	public EntityPlayer entityPlayer;

	@SideOnly(Side.CLIENT)
	public ChangeChanterContainer(InventoryPlayer playerInv) {
		this(playerInv, BlockPos.ORIGIN);
	}

	public ChangeChanterContainer(InventoryPlayer playerInv, BlockPos pos) {
		entityPlayer = playerInv.player;
		this.tableInventory = new InventoryBasic("ChangeChanter", true, 2) {
			@Override
			public int getInventoryStackLimit() {
				return 64;
			}

			@Override
			public void markDirty() {
				super.markDirty();
				ChangeChanterContainer.this.onCraftMatrixChanged(this);
			}
		};
		this.rand = new Random();
		this.enchantLevels = new int[3];
		this.field_178151_h = new int[] { -1, -1, -1 };
		this.position = pos;
		this.addSlotToContainer(new Slot(this.tableInventory, 0, 16 + 1, 35) {
			private static final String __OBFID = "CL_00001747";

			@Override
			public boolean isItemValid(ItemStack stack) {
				return true;
			}

			@Override
			public int getSlotStackLimit() {
				return 1;
			}
		});
		this.addSlotToContainer(new Slot(this.tableInventory, 1, 16 + 1, 59 + 2) {
			private static final String __OBFID = "CL_00002185";
			java.util.List<ItemStack> ores = net.minecraftforge.oredict.OreDictionary.getOres("gemLapis");

			@Override
			public boolean isItemValid(ItemStack stack) {
				for (ItemStack ore : ores)
					if (net.minecraftforge.oredict.OreDictionary.itemMatches(ore, stack, false))
						return true;
				return false;
			}
		});
		int i;

		for (i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(
						new Slot(playerInv, j + i * 9 + 9, 8 + j * 18 + 34 + 4 + 2, 84 + i * 18 + 28 + 1));
			}
		}

		for (i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(playerInv, i, 8 + i * 18 + 34 + 4 + 2, 142 + 28 + 1));
		}
	}

	@Override
	public void onContainerClosed(EntityPlayer playerIn) {
		super.onContainerClosed(playerIn);

		if (!playerIn.worldObj.isRemote) {
			for (int i = 0; i < this.tableInventory.getSizeInventory(); ++i) {
				ItemStack itemstack = this.tableInventory.removeStackFromSlot(i);

				if (itemstack != null) {
					playerIn.dropPlayerItemWithRandomChoice(itemstack, false);
				}
			}
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return playerIn.worldObj.getBlockState(this.position).getBlock() != GameHelperCoreModule.ENCH_ALTAR ? false
				: playerIn.getDistanceSq(this.position.getX() + 0.5D, this.position.getY() + 0.5D,
						this.position.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index == 0) {
				if (!this.mergeItemStack(itemstack1, 2, 38, true)) {
					return null;
				}
			} else if (index == 1) {
				if (!this.mergeItemStack(itemstack1, 2, 38, true)) {
					return null;
				}
			} else if (itemstack1.getItem() == Items.dye
					&& EnumDyeColor.byDyeDamage(itemstack1.getMetadata()) == EnumDyeColor.BLUE) {
				if (!this.mergeItemStack(itemstack1, 1, 2, true)) {
					return null;
				}
			} else {
				if (((Slot) this.inventorySlots.get(0)).getHasStack()
						|| !((Slot) this.inventorySlots.get(0)).isItemValid(itemstack1)) {
					return null;
				}

				if (itemstack1.hasTagCompound() && itemstack1.stackSize == 1) {
					((Slot) this.inventorySlots.get(0)).putStack(itemstack1.copy());
					itemstack1.stackSize = 0;
				} else if (itemstack1.stackSize >= 1) {
					((Slot) this.inventorySlots.get(0))
							.putStack(new ItemStack(itemstack1.getItem(), 1, itemstack1.getMetadata()));
					--itemstack1.stackSize;
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

			slot.onPickupFromSlot(playerIn, itemstack1);
		}

		return itemstack;
	}
}