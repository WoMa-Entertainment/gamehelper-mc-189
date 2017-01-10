package net.wfoas.gh.minersinventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.wfoas.gh.minersinventory.layer.MinersInventoryHelper;

public class MinersInventory implements IInventory {

	public static final int INV_SIZE = 37;
	public static final int MINERS_HELMET_LIGHT = 0, MINERS_BACKPACK = 1, MINERS_BELT_ITEM_SLOT = 2,
			MINERS_DOLCH_LASCHE_ITEM_SLOT = 3;

	EntityPlayer player;

	IChatComponent containerName;

	public MinersInventory(EntityPlayer ep) {
		containerName = new ChatComponentTranslation("gamehelper.container.minersinv");
		this.player = ep;
		this.readFromNBT();
	}

	/**
	 * Armor: Slots 0, 1, 2, 3 <br>
	 * + 0: Minerlampe + 1: Minerrucksack + 2: Werkzeuggürtel + 3: Dolchlasche
	 * Minersbelt: Slots 4, 5, 6, 7, 8 <br>
	 * Dolchlasche: Slot 9 <br>
	 * MinersBackpack: Slots 10 - 36<br>
	 */
	// private ItemStack[][] inventory = new ItemStack[4][27];
	private ItemStack[] inv = new ItemStack[37];
	private ItemStack[] armor = new ItemStack[4];
	private ItemStack[] minersbelt = new ItemStack[5];
	private ItemStack[] dolch = new ItemStack[1];
	private ItemStack[] minersbackpack = new ItemStack[27];

	@Override
	public void markDirty() {
		for (int i = 0; i < getSizeInventory(); ++i) {
			if (getStackInSlot(i) != null && getStackInSlot(i).stackSize == 0) {
				setInventorySlotContents(i, null);
			}
		}
		writeToNBT();
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	public void readFromNBT() {
		NBTTagCompound nbttc = MinersInventoryHelper.returnNBTTagCompoundForMinersInventory(player);
		if (nbttc == null)
			return;
		if (nbttc.hasNoTags())
			return;
		// System.out.println("No-Tags");
		// headlight
		NBTTagCompound hl = (NBTTagCompound) nbttc.getTag("miners_helmet");
		if (hl != null) {
			ItemStack head_light = ItemStack.loadItemStackFromNBT(hl);
			if (head_light != null)
				this.forceSetInventorySlotContentsNotSave(0, head_light);
			else
				nbttc.removeTag("miners_helmet");
		}
		// backpack
		NBTTagCompound bp = (NBTTagCompound) nbttc.getTag("miners_backpack");
		if (bp != null) {
			ItemStack backpack = ItemStack.loadItemStackFromNBT(bp);
			if (backpack != null) {
				if (!backpack.hasTagCompound()) {
					backpack.setTagCompound(new NBTTagCompound());
					backpack.getTagCompound().setTag("items", new NBTTagCompound());
				}
				// backpack.getTagCompound().removeTag("items");
				NBTTagCompound nbttl = (NBTTagCompound) backpack.getTagCompound().getTag("items");
				// for (int index = 0; index < 27; index++) {
				// if (minersbackpack[index] != null)
				// ((NBTTagList) backpack.getTagCompound().getTag("items"))
				// .appendTag(minersbackpack[index].writeToNBT(new
				// NBTTagCompound()));
				// }
				// for (int index = 0; index < 5; index++) {
				// if (minersbelt[index] != null)
				// ((NBTTagCompound) belt.getTagCompound().getTag("items"))
				// .setTag("slot_" + index, (minersbelt[index].writeToNBT(new
				// NBTTagCompound())));
				// }
				// for (int i = 0; i < nbttl.tagCount(); i++) {
				for (int index = 0; index < 27; index++) {
					minersbackpack[index] = ItemStack.loadItemStackFromNBT(nbttl.getCompoundTag("slot_" + index));
				}
				// nbttc.setTag("miners_backpack", backpack.writeToNBT(new
				// NBTTagCompound()));
				this.forceSetInventorySlotContentsNotSave(1, backpack);
			} else
				nbttc.removeTag("miners_backpack");
		}
		// belt
		NBTTagCompound b = (NBTTagCompound) nbttc.getTag("miners_belt");
		if (b != null) {
			ItemStack belt = ItemStack.loadItemStackFromNBT(b);
			if (belt != null) {
				if (!belt.hasTagCompound()) {
					belt.setTagCompound(new NBTTagCompound());
					belt.getTagCompound().setTag("items", new NBTTagCompound());
				}
				// belt.getTagCompound().removeTag("items");
				NBTTagCompound nbttl = (NBTTagCompound) belt.getTagCompound().getTag("items");
				// for (int index = 0; index < 5; index++) {
				// if (minersbelt[index] != null)
				// ((NBTTagList) belt.getTagCompound().getTag("items"))
				// .appendTag(minersbelt[index].writeToNBT(new
				// NBTTagCompound()));
				// }
				// for(int i = 0;)
				for (int index = 0; index < 5; index++) {
					minersbelt[index] = ItemStack.loadItemStackFromNBT(nbttl.getCompoundTag("slot_" + index));
				}
				// // nbttc.setTag("miners_belt", belt.writeToNBT(new
				// NBTTagCompound()));
				this.forceSetInventorySlotContentsNotSave(2, belt);
			} else {
				nbttc.removeTag("miners_belt");
			}
		}
		// dolchlasche
		NBTTagCompound d = (NBTTagCompound) nbttc.getTag("dagger");
		if (d != null) {
			ItemStack dolchlasche = ItemStack.loadItemStackFromNBT(d);
			if (dolchlasche != null) {
				if (!dolchlasche.hasTagCompound()) {
					dolchlasche.setTagCompound(new NBTTagCompound());
					// dolchlasche.getTagCompound().setTag("item", new
					// NBTTagList());
				}
				// backpack.getTagCompound().removeTag("items");
				// for (int index = 0; index < 5; index++) {
				// if (minersbelt[index] != null)
				// ((NBTTagList) backpack.getTagCompound().getTag("items"))
				// .appendTag(minersbelt[index].writeToNBT(new
				// NBTTagCompound()));
				// }
				// if (dolch[0] != null)
				// dolchlasche.getTagCompound().setTag("item",
				// dolch[0].writeToNBT(new NBTTagCompound()));
				// nbttc.setTag("dagger", dolchlasche.writeToNBT(new
				// NBTTagCompound()));
				NBTTagCompound dolchlc = (NBTTagCompound) dolchlasche.getTagCompound().getTag("item");
				if (dolchlc != null)
					dolch[0] = ItemStack.loadItemStackFromNBT(dolchlc);
				this.forceSetInventorySlotContentsNotSave(3, dolchlasche);
			} else {
				nbttc.removeTag("dagger");
			}
		}
	}

	public boolean hasMinersHelmet() {
		return getStackInSlot(MINERS_HELMET_LIGHT) != null;
	}

	public boolean hasMinersBackpack() {
		return getStackInSlot(MINERS_BACKPACK) != null;
	}

	public boolean hasMinersBelt() {
		return getStackInSlot(MINERS_BELT_ITEM_SLOT) != null;
	}

	public boolean hasMinersDaggerSlot() {
		return getStackInSlot(MINERS_DOLCH_LASCHE_ITEM_SLOT) != null;
	}

	public void writeToNBT() {
		// System.out.println("Will write to NBT");
		NBTTagCompound nbttc = MinersInventoryHelper.returnNBTTagCompoundForMinersInventory(player);
		ItemStack head_light = this.getStackInSlot(0);
		// headlight
		if (head_light != null)
			nbttc.setTag("miners_helmet", head_light.writeToNBT(new NBTTagCompound()));
		else
			nbttc.removeTag("miners_helmet");
		// backpack
		ItemStack backpack = this.getStackInSlot(1);
		if (backpack != null) {
			if (!backpack.hasTagCompound()) {
				backpack.setTagCompound(new NBTTagCompound());
				// backpack.getTagCompound().setTag("items", new
				// NBTTagCompound());
			}
			backpack.getTagCompound().removeTag("items");
			backpack.getTagCompound().setTag("items", new NBTTagCompound());
			for (int index = 0; index < 27; index++) {
				if (minersbackpack[index] != null)
					((NBTTagCompound) backpack.getTagCompound().getTag("items")).setTag("slot_" + index,
							minersbackpack[index].writeToNBT(new NBTTagCompound()));
			}
			nbttc.setTag("miners_backpack", backpack.writeToNBT(new NBTTagCompound()));
		} else
			nbttc.removeTag("miners_backpack");
		// belt
		ItemStack belt = this.getStackInSlot(2);
		if (belt != null) {
			if (!belt.hasTagCompound()) {
				belt.setTagCompound(new NBTTagCompound());
				belt.getTagCompound().setTag("items", new NBTTagCompound());
			}
			belt.getTagCompound().removeTag("items");
			belt.getTagCompound().setTag("items", new NBTTagCompound());
			for (int index = 0; index < 5; index++) {
				if (minersbelt[index] != null)
					((NBTTagCompound) belt.getTagCompound().getTag("items")).setTag("slot_" + index,
							(minersbelt[index].writeToNBT(new NBTTagCompound())));
			}
			nbttc.setTag("miners_belt", belt.writeToNBT(new NBTTagCompound()));
		} else {
			nbttc.removeTag("miners_belt");
		}
		ItemStack dolchlasche = this.getStackInSlot(3);
		if (dolchlasche != null) {
			if (!dolchlasche.hasTagCompound()) {
				dolchlasche.setTagCompound(new NBTTagCompound());
				// dolchlasche.getTagCompound().setTag("item", new
				// NBTTagList());
			}
			if (dolch[0] != null)
				dolchlasche.getTagCompound().setTag("item", dolch[0].writeToNBT(new NBTTagCompound()));
			else
				dolchlasche.getTagCompound().removeTag("item");
			nbttc.setTag("dagger", dolchlasche.writeToNBT(new NBTTagCompound()));
		} else {
			nbttc.removeTag("dagger");
		}
		// System.out.println("Has been written to NBT");
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		if (index >= INV_SIZE)
			return null;
		if (index < 0)
			return null;
		if (index <= 3 && index >= 0)
			return armor[index];
		if (index <= 8 && index >= 4)
			return minersbelt[index - 4];
		if (index == 9)
			return dolch[index - 9];
		if (index <= 36 && index >= 10) {
			return minersbackpack[index - 10];
		}
		return null;
	}

	@Override
	public void clear() {
		for (int i = 0; i < INV_SIZE; i++) {
			forceSetInventorySlotContentsNotSave(i, null);
		}
		this.markDirty();
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if (index >= INV_SIZE)
			return;
		if (index < 0)
			return;
		if (index <= 3 && index >= 0) {
			armor[index] = stack;
			if (index == MINERS_BACKPACK) {
				for (int i = 0; i < 27; i++)
					forceSetInventorySlotContentsNotSave(i + 10, null);
				if (stack == null)
					;
				if (stack != null) {
					if (!stack.hasTagCompound()) {
						stack.setTagCompound(new NBTTagCompound());
						stack.getTagCompound().setTag("items", new NBTTagCompound());
					}
					// backpack.getTagCompound().removeTag("items");
					NBTTagCompound nbttl = (NBTTagCompound) stack.getTagCompound().getTag("items");
					// for (int index = 0; index < 27; index++) {
					// if (minersbackpack[index] != null)
					// ((NBTTagList) backpack.getTagCompound().getTag("items"))
					// .appendTag(minersbackpack[index].writeToNBT(new
					// NBTTagCompound()));
					// }
					// for (int index = 0; index < 5; index++) {
					// if (minersbelt[index] != null)
					// ((NBTTagCompound) belt.getTagCompound().getTag("items"))
					// .setTag("slot_" + index,
					// (minersbelt[index].writeToNBT(new
					// NBTTagCompound())));
					// }
					// for (int i = 0; i < nbttl.tagCount(); i++) {
					if (nbttl != null)
						for (int i = 0; i < 27; i++) {
							minersbackpack[i] = ItemStack.loadItemStackFromNBT(nbttl.getCompoundTag("slot_" + i));
						}
					// nbttc.setTag("miners_backpack", backpack.writeToNBT(new
					// NBTTagCompound()));
				}
				this.markDirty();
			} else if (index == MINERS_BELT_ITEM_SLOT) {
				for (int i = 0; i < 5; i++)
					minersbelt[i] = null;
				if (stack == null)
					;
				if (stack != null) {
					if (!stack.hasTagCompound()) {
						stack.setTagCompound(new NBTTagCompound());
						stack.getTagCompound().setTag("items", new NBTTagCompound());
					}
					// backpack.getTagCompound().removeTag("items");
					NBTTagCompound nbttl = (NBTTagCompound) stack.getTagCompound().getTag("items");
					// for (int index = 0; index < 27; index++) {
					// if (minersbackpack[index] != null)
					// ((NBTTagList) backpack.getTagCompound().getTag("items"))
					// .appendTag(minersbackpack[index].writeToNBT(new
					// NBTTagCompound()));
					// }
					// for (int index = 0; index < 5; index++) {
					// if (minersbelt[index] != null)
					// ((NBTTagCompound) belt.getTagCompound().getTag("items"))
					// .setTag("slot_" + index,
					// (minersbelt[index].writeToNBT(new
					// NBTTagCompound())));
					// }
					// for (int i = 0; i < nbttl.tagCount(); i++) {
					if (nbttl != null)
						for (int i = 0; i < 5; i++) {
							minersbelt[i] = ItemStack.loadItemStackFromNBT(nbttl.getCompoundTag("slot_" + i));
						}
					// nbttc.setTag("miners_backpack", backpack.writeToNBT(new
					// NBTTagCompound()));
				}
				this.markDirty();
			} else if (index == MINERS_DOLCH_LASCHE_ITEM_SLOT) {
				dolch[0] = null;
				if (stack == null)
					;
				if (stack != null) {
					if (!stack.hasTagCompound()) {
						stack.setTagCompound(new NBTTagCompound());
						stack.getTagCompound().setTag("item", new NBTTagCompound());
					}
					// backpack.getTagCompound().removeTag("items");
					// for (int index = 0; index < 5; index++) {
					// if (minersbelt[index] != null)
					// ((NBTTagList) backpack.getTagCompound().getTag("items"))
					// .appendTag(minersbelt[index].writeToNBT(new
					// NBTTagCompound()));
					// }
					// if (dolch[0] != null)
					// dolchlasche.getTagCompound().setTag("item",
					// dolch[0].writeToNBT(new NBTTagCompound()));
					// nbttc.setTag("dagger", dolchlasche.writeToNBT(new
					// NBTTagCompound()));
					NBTTagCompound nbt = (NBTTagCompound) stack.getTagCompound().getTag("item");
					if (nbt != null)
						dolch[0] = ItemStack.loadItemStackFromNBT(nbt);
					// this.forceSetInventorySlotContentsNotSave(3, stack);
				}
				this.markDirty();
			}
		}
		if (index <= 8 && index >= 4)
			minersbelt[index - 4] = stack;
		if (index == 9)
			dolch[index - 9] = stack;
		if (index <= 36 && index >= 10) {
			minersbackpack[index - 10] = stack;
		}
		if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
			stack.stackSize = this.getInventoryStackLimit();
		}
		this.markDirty();
	}

	private void forceSetInventorySlotContentsNotSave(int index, ItemStack stack) {
		if (index >= INV_SIZE)
			return;
		if (index < 0)
			return;
		if (index <= 3 && index >= 0)
			armor[index] = stack;
		if (index <= 8 && index >= 4)
			minersbelt[index - 4] = stack;
		if (index == 9)
			dolch[index - 9] = stack;
		if (index <= 36 && index >= 10) {
			minersbackpack[index - 10] = stack;
		}
		if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
			stack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getName() {
		return ((ChatComponentTranslation) containerName).getFormattedText();
	}

	@Override
	public boolean hasCustomName() {
		return true;
	}

	@Override
	public IChatComponent getDisplayName() {
		return containerName;
	}

	@Override
	public int getSizeInventory() {
		return INV_SIZE;
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		// if (this.getStackInSlot(index) != null) {
		// ItemStack itemstack;
		//
		// if (this.getStackInSlot(index).stackSize <= count) {
		// itemstack = this.getStackInSlot(index);
		// this.forceSetInventorySlotContentsNotSave(index, null);
		// this.markDirty();
		// return itemstack;
		// } else {
		// itemstack = this.getStackInSlot(index).splitStack(count);
		//
		// if (this.getStackInSlot(index).stackSize == 0) {
		// this.forceSetInventorySlotContentsNotSave(index, null);
		// }
		//
		// this.markDirty();
		// return itemstack;
		// }
		// } else {
		// return null;
		// }
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			if (stack.stackSize > amount) {
				stack = stack.splitStack(amount);
				// Don't forget this line or your inventory will not be saved!
				markDirty();
			} else {
				// this method also calls onInventoryChanged, so we don't need
				// to call it again
				setInventorySlotContents(slot, null);
			}
		}
		return stack;
	}

	@Override
	// public ItemStack getStackInSlotOnClosing(int index) {
	// if (this.getStackInSlot(index) != null) {
	// ItemStack itemstack = this.getStackInSlot(index);
	// this.forceSetInventorySlotContentsNotSave(index, null);
	// return itemstack;
	// } else {
	// return null;
	// }
	// }
	public ItemStack removeStackFromSlot(int slot) {
		ItemStack stack = getStackInSlot(slot);
		setInventorySlotContents(slot, null);
		return stack;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {
	}

	@Override
	public void closeInventory(EntityPlayer player) {
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
}
