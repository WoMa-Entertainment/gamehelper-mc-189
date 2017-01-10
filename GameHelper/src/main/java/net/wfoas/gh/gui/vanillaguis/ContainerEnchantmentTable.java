package net.wfoas.gh.gui.vanillaguis;

import java.util.List;
import java.util.Random;

import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerEnchantmentTable extends Container {
	public IInventory tableInventory;
	private World worldPointer;
	private Random rand;
	public int xpSeed;
	public int[] enchantLevels;
	public int[] field_178151_h;
	public int enchantmentPower = 0;
	private static final String __OBFID = "CL_00001745";
	protected int x, y, z;

	public ContainerEnchantmentTable(InventoryPlayer playerInv, World worldIn, int x, int y, int z) {
		this(playerInv, worldIn, 0, x, y, z);
	}

	/**
	 * @param enchantmentPower
	 *            EnchantmentPower = maxLevel / 2 [30 > x && -1 < x]
	 */
	public ContainerEnchantmentTable(InventoryPlayer playerInv, World worldIn, int enchantmentPower, int x, int y,
			int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.enchantmentPower = enchantmentPower;
		this.tableInventory = new InventoryBasic("Enchant", true, 2) {
			private static final String __OBFID = "CL_00001746";

			public int getInventoryStackLimit() {
				return 64;
			}

			public void markDirty() {
				super.markDirty();
				ContainerEnchantmentTable.this.onCraftMatrixChanged(this);
			}
		};
		this.rand = new Random();
		this.enchantLevels = new int[3];
		this.field_178151_h = new int[] { -1, -1, -1 };
		this.worldPointer = worldIn;
		this.xpSeed = playerInv.player.getXPSeed();
		this.addSlotToContainer(new Slot(this.tableInventory, 0, 15, 47) {

			public boolean isItemValid(ItemStack stack) {
				return true;
			}

			public int getSlotStackLimit() {
				return 1;
			}
		});
		this.addSlotToContainer(new Slot(this.tableInventory, 1, 35, 47) {
			java.util.List<ItemStack> ores = net.minecraftforge.oredict.OreDictionary.getOres("gemLapis");

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
				this.addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(playerInv, i, 8 + i * 18, 142));
		}
	}

	public void addCraftingToCrafters(ICrafting listener) {
		// super.addCraftingToCrafters(listener);
		super.onCraftGuiOpened(listener);
		listener.sendProgressBarUpdate(this, 0, this.enchantLevels[0]);
		listener.sendProgressBarUpdate(this, 1, this.enchantLevels[1]);
		listener.sendProgressBarUpdate(this, 2, this.enchantLevels[2]);
		listener.sendProgressBarUpdate(this, 3, this.xpSeed & -16);
		listener.sendProgressBarUpdate(this, 4, this.field_178151_h[0]);
		listener.sendProgressBarUpdate(this, 5, this.field_178151_h[1]);
		listener.sendProgressBarUpdate(this, 6, this.field_178151_h[2]);
	}

	/**
	 * Looks for changes made in the container, sends them to every listener.
	 */
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < this.crafters.size(); ++i) {
			ICrafting icrafting = (ICrafting) this.crafters.get(i);
			icrafting.sendProgressBarUpdate(this, 0, this.enchantLevels[0]);
			icrafting.sendProgressBarUpdate(this, 1, this.enchantLevels[1]);
			icrafting.sendProgressBarUpdate(this, 2, this.enchantLevels[2]);
			icrafting.sendProgressBarUpdate(this, 3, this.xpSeed & -16);
			icrafting.sendProgressBarUpdate(this, 4, this.field_178151_h[0]);
			icrafting.sendProgressBarUpdate(this, 5, this.field_178151_h[1]);
			icrafting.sendProgressBarUpdate(this, 6, this.field_178151_h[2]);
		}
	}

	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		if (id >= 0 && id <= 2) {
			this.enchantLevels[id] = data;
		} else if (id == 3) {
			this.xpSeed = data;
		} else if (id >= 4 && id <= 6) {
			this.field_178151_h[id - 4] = data;
		} else {
			super.updateProgressBar(id, data);
		}
	}

	public void onCraftMatrixChanged(IInventory inventoryIn) {
		if (inventoryIn == this.tableInventory) {
			ItemStack itemstack = inventoryIn.getStackInSlot(0);
			int i;

			if (itemstack != null && itemstack.isItemEnchantable()) {
				if (!this.worldPointer.isRemote) {
					i = 0;
					int j;
					float power = this.enchantmentPower;

					this.rand.setSeed((long) this.xpSeed);

					for (j = 0; j < 3; ++j) {
						this.enchantLevels[j] = EnchantmentHelper.calcItemStackEnchantability(this.rand, j, (int) power,
								itemstack);
						this.field_178151_h[j] = -1;

						if (this.enchantLevels[j] < j + 1) {
							this.enchantLevels[j] = 0;
						}
					}

					for (j = 0; j < 3; ++j) {
						if (this.enchantLevels[j] > 0) {
							List list = this.func_178148_a(itemstack, j, this.enchantLevels[j]);

							if (list != null && !list.isEmpty()) {
								EnchantmentData enchantmentdata = (EnchantmentData) list
										.get(this.rand.nextInt(list.size()));
								this.field_178151_h[j] = enchantmentdata.enchantmentobj.effectId
										| enchantmentdata.enchantmentLevel << 8;
							}
						}
					}

					this.detectAndSendChanges();
				}
			} else {
				for (i = 0; i < 3; ++i) {
					this.enchantLevels[i] = 0;
					this.field_178151_h[i] = -1;
				}
			}
		}
	}

	public boolean enchantItem(EntityPlayer playerIn, int id) {
		ItemStack itemstack = this.tableInventory.getStackInSlot(0);
		ItemStack itemstack1 = this.tableInventory.getStackInSlot(1);
		int j = id + 1;

		if ((itemstack1 == null || itemstack1.stackSize < j) && !playerIn.capabilities.isCreativeMode) {
			return false;
		} else if (this.enchantLevels[id] > 0 && itemstack != null
				&& (playerIn.experienceLevel >= j && playerIn.experienceLevel >= this.enchantLevels[id]
						|| playerIn.capabilities.isCreativeMode)) {
			if (!this.worldPointer.isRemote) {
				List list = this.func_178148_a(itemstack, id, this.enchantLevels[id]);
				boolean flag = itemstack.getItem() == Items.book;

				if (list != null) {
					playerIn.removeExperienceLevel(j);

					if (flag) {
						itemstack.setItem(Items.enchanted_book);
					}

					for (int k = 0; k < list.size(); ++k) {
						EnchantmentData enchantmentdata = (EnchantmentData) list.get(k);

						if (flag) {
							Items.enchanted_book.addEnchantment(itemstack, enchantmentdata);
						} else {
							itemstack.addEnchantment(enchantmentdata.enchantmentobj, enchantmentdata.enchantmentLevel);
						}
					}

					if (!playerIn.capabilities.isCreativeMode) {
						itemstack1.stackSize -= j;

						if (itemstack1.stackSize <= 0) {
							this.tableInventory.setInventorySlotContents(1, (ItemStack) null);
						}
					}

					this.tableInventory.markDirty();
					this.xpSeed = playerIn.getXPSeed();
					this.onCraftMatrixChanged(this.tableInventory);
				}
			}

			return true;
		} else {
			return false;
		}
	}

	private List func_178148_a(ItemStack p_178148_1_, int p_178148_2_, int p_178148_3_) {
		this.rand.setSeed((long) (this.xpSeed + p_178148_2_));
		List list = EnchantmentHelper.buildEnchantmentList(this.rand, p_178148_1_, p_178148_3_);

		if (p_178148_1_.getItem() == Items.book && list != null && list.size() > 1) {
			list.remove(this.rand.nextInt(list.size()));
		}

		return list;
	}

	@SideOnly(Side.CLIENT)
	public int getLapisAmount() {
		ItemStack itemstack = this.tableInventory.getStackInSlot(1);
		return itemstack == null ? 0 : itemstack.stackSize;
	}

	public void onContainerClosed(EntityPlayer playerIn) {
		super.onContainerClosed(playerIn);

		if (!this.worldPointer.isRemote) {
			for (int i = 0; i < this.tableInventory.getSizeInventory(); ++i) {
				ItemStack itemstack = this.tableInventory.removeStackFromSlot(i);

				if (itemstack != null) {
					playerIn.dropPlayerItemWithRandomChoice(itemstack, false);
				}
			}
		}
	}

	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

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