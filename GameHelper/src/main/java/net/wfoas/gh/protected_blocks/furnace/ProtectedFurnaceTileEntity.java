package net.wfoas.gh.protected_blocks.furnace;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ITickable;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.protected_blocks.IProtectedBlock;
import net.wfoas.gh.protected_blocks.LockType;

public class ProtectedFurnaceTileEntity extends TileEntityLockable
		implements ITickable, ISidedInventory, IProtectedBlock {
	private static final int[] slotsTop = new int[] { 0 };
	private static final int[] slotsBottom = new int[] { 2, 1 };
	private static final int[] slotsSides = new int[] { 1 };
	/**
	 * The ItemStacks that hold the items currently being used in the furnace
	 */
	private ItemStack[] furnaceItemStacks = new ItemStack[3];
	/** The number of ticks that the furnace will keep burning */
	private int furnaceBurnTime;
	/**
	 * The number of ticks that a fresh copy of the currently-burning item would
	 * keep the furnace burning for
	 */
	private int currentItemBurnTime;
	private int cookTime;
	private int totalCookTime;
	private String furnaceCustomName;

	/**
	 * Returns the number of slots in the inventory.
	 */
	public int getSizeInventory() {
		return this.furnaceItemStacks.length;
	}

	public ItemStack getStackInSlot(int index) {
		return this.furnaceItemStacks[index];
	}

	public ItemStack decrStackSize(int index, int count) {
		if (this.furnaceItemStacks[index] != null) {
			if (this.furnaceItemStacks[index].stackSize <= count) {
				ItemStack itemstack1 = this.furnaceItemStacks[index];
				this.furnaceItemStacks[index] = null;
				return itemstack1;
			} else {
				ItemStack itemstack = this.furnaceItemStacks[index].splitStack(count);

				if (this.furnaceItemStacks[index].stackSize == 0) {
					this.furnaceItemStacks[index] = null;
				}

				return itemstack;
			}
		} else {
			return null;
		}
	}

	public ItemStack removeStackFromSlot(int index) {
		if (this.furnaceItemStacks[index] != null) {
			ItemStack itemstack = this.furnaceItemStacks[index];
			this.furnaceItemStacks[index] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	public void setInventorySlotContents(int index, ItemStack stack) {
		boolean flag = stack != null && stack.isItemEqual(this.furnaceItemStacks[index])
				&& ItemStack.areItemStackTagsEqual(stack, this.furnaceItemStacks[index]);
		this.furnaceItemStacks[index] = stack;

		if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
			stack.stackSize = this.getInventoryStackLimit();
		}

		if (index == 0 && !flag) {
			this.totalCookTime = this.getCookTime(stack);
			this.cookTime = 0;
			this.markDirty();
		}
	}

	public String getName() {
		return this.hasCustomName() ? this.furnaceCustomName : "gamehelper.container.protected_furnace";
	}

	/**
	 * Returns true if this thing is named
	 */
	public boolean hasCustomName() {
		return this.furnaceCustomName != null && this.furnaceCustomName.length() > 0;
	}

	public void setCustomInventoryName(String p_145951_1_) {
		this.furnaceCustomName = p_145951_1_;
	}

	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		NBTTagList nbttaglist = compound.getTagList("Items", 10);
		this.furnaceItemStacks = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound.getByte("Slot");

			if (j >= 0 && j < this.furnaceItemStacks.length) {
				this.furnaceItemStacks[j] = ItemStack.loadItemStackFromNBT(nbttagcompound);
			}
		}

		this.furnaceBurnTime = compound.getShort("BurnTime");
		this.cookTime = compound.getShort("CookTime");
		this.totalCookTime = compound.getShort("CookTimeTotal");
		this.currentItemBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);

		if (compound.hasKey("CustomName", 8)) {
			this.furnaceCustomName = compound.getString("CustomName");
		}
	}

	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setShort("BurnTime", (short) this.furnaceBurnTime);
		compound.setShort("CookTime", (short) this.cookTime);
		compound.setShort("CookTimeTotal", (short) this.totalCookTime);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.furnaceItemStacks.length; ++i) {
			if (this.furnaceItemStacks[i] != null) {
				NBTTagCompound nbttagcompound = new NBTTagCompound();
				nbttagcompound.setByte("Slot", (byte) i);
				this.furnaceItemStacks[i].writeToNBT(nbttagcompound);
				nbttaglist.appendTag(nbttagcompound);
			}
		}

		compound.setTag("Items", nbttaglist);

		if (this.hasCustomName()) {
			compound.setString("CustomName", this.furnaceCustomName);
		}
	}

	/**
	 * Returns the maximum stack size for a inventory slot. Seems to always be
	 * 64, possibly will be extended.
	 */
	public int getInventoryStackLimit() {
		return 64;
	}

	/**
	 * Furnace isBurning
	 */
	public boolean isBurning() {
		return this.furnaceBurnTime > 0;
	}

	@SideOnly(Side.CLIENT)
	public static boolean isBurning(IInventory p_174903_0_) {
		return p_174903_0_.getField(0) > 0;
	}

	/**
	 * Like the old updateEntity(), except more generic.
	 */
	public void update() {
		boolean flag = this.isBurning();
		boolean flag1 = false;

		if (this.isBurning()) {
			--this.furnaceBurnTime;
		}

		if (!this.worldObj.isRemote) {
			if (this.isBurning() || this.furnaceItemStacks[1] != null && this.furnaceItemStacks[0] != null) {
				if (!this.isBurning() && this.canSmelt()) {
					this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);

					if (this.isBurning()) {
						flag1 = true;

						if (this.furnaceItemStacks[1] != null) {
							--this.furnaceItemStacks[1].stackSize;

							if (this.furnaceItemStacks[1].stackSize == 0) {
								this.furnaceItemStacks[1] = furnaceItemStacks[1].getItem()
										.getContainerItem(furnaceItemStacks[1]);
							}
						}
					}
				}

				if (this.isBurning() && this.canSmelt()) {
					++this.cookTime;

					if (this.cookTime == this.totalCookTime) {
						this.cookTime = 0;
						this.totalCookTime = this.getCookTime(this.furnaceItemStacks[0]);
						this.smeltItem();
						flag1 = true;
					}
				} else {
					this.cookTime = 0;
				}
			} else if (!this.isBurning() && this.cookTime > 0) {
				this.cookTime = MathHelper.clamp_int(this.cookTime - 2, 0, this.totalCookTime);
			}

			if (flag != this.isBurning()) {
				flag1 = true;
				ProtectedFurnaceBlock.setState(this.isBurning(), this.worldObj, this.pos);
			}
		}

		if (flag1) {
			this.markDirty();
		}
	}

	public int getCookTime(ItemStack stack) {
		return 200;
	}

	/**
	 * Returns true if the furnace can smelt an item, i.e. has a source item,
	 * destination stack isn't full, etc.
	 */
	private boolean canSmelt() {
		if (this.furnaceItemStacks[0] == null) {
			return false;
		} else {
			ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(this.furnaceItemStacks[0]);
			if (itemstack == null)
				return false;
			if (this.furnaceItemStacks[2] == null)
				return true;
			if (!this.furnaceItemStacks[2].isItemEqual(itemstack))
				return false;
			int result = furnaceItemStacks[2].stackSize + itemstack.stackSize;
			return result <= getInventoryStackLimit() && result <= this.furnaceItemStacks[2].getMaxStackSize(); // Forge
																												// BugFix:
																												// Make
																												// it
																												// respect
																												// stack
																												// sizes
																												// properly.
		}
	}

	/**
	 * Turn one item from the furnace source stack into the appropriate smelted
	 * item in the furnace result stack
	 */
	public void smeltItem() {
		if (this.canSmelt()) {
			ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(this.furnaceItemStacks[0]);

			if (this.furnaceItemStacks[2] == null) {
				this.furnaceItemStacks[2] = itemstack.copy();
			} else if (this.furnaceItemStacks[2].getItem() == itemstack.getItem()) {
				this.furnaceItemStacks[2].stackSize += itemstack.stackSize; // Forge
																			// BugFix:
																			// Results
																			// may
																			// have
																			// multiple
																			// items
			}

			if (this.furnaceItemStacks[0].getItem() == Item.getItemFromBlock(Blocks.sponge)
					&& this.furnaceItemStacks[0].getMetadata() == 1 && this.furnaceItemStacks[1] != null
					&& this.furnaceItemStacks[1].getItem() == Items.bucket) {
				this.furnaceItemStacks[1] = new ItemStack(Items.water_bucket);
			}

			--this.furnaceItemStacks[0].stackSize;

			if (this.furnaceItemStacks[0].stackSize <= 0) {
				this.furnaceItemStacks[0] = null;
			}
		}
	}

	/**
	 * Returns the number of ticks that the supplied fuel item will keep the
	 * furnace burning, or 0 if the item isn't fuel
	 */
	public static int getItemBurnTime(ItemStack p_145952_0_) {
		return TileEntityFurnace.getItemBurnTime(p_145952_0_);
	}

	public static boolean isItemFuel(ItemStack p_145954_0_) {
		return getItemBurnTime(p_145954_0_) > 0;
	}

	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getTileEntity(this.pos) != this ? false
				: player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D,
						(double) this.pos.getZ() + 0.5D) <= 64.0D;
	}

	public void openInventory(EntityPlayer player) {
	}

	public void closeInventory(EntityPlayer player) {
	}

	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return index == 2 ? false : (index != 1 ? true : isItemFuel(stack) || SlotFurnaceFuel.isBucket(stack));
	}

	public int[] getSlotsForFace(EnumFacing side) {
		return side == EnumFacing.DOWN ? slotsBottom : (side == EnumFacing.UP ? slotsTop : slotsSides);
	}

	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		return this.isItemValidForSlot(index, itemStackIn);
	}

	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		if (direction == EnumFacing.DOWN && index == 1) {
			Item item = stack.getItem();

			if (item != Items.water_bucket && item != Items.bucket) {
				return false;
			}
		}

		return true;
	}

	public String getGuiID() {
		return "minecraft:furnace";
	}

	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerProtectedFurnace(playerInventory, this);
	}

	public int getField(int id) {
		switch (id) {
		case 0:
			return this.furnaceBurnTime;
		case 1:
			return this.currentItemBurnTime;
		case 2:
			return this.cookTime;
		case 3:
			return this.totalCookTime;
		default:
			return 0;
		}
	}

	public void setField(int id, int value) {
		switch (id) {
		case 0:
			this.furnaceBurnTime = value;
			break;
		case 1:
			this.currentItemBurnTime = value;
			break;
		case 2:
			this.cookTime = value;
			break;
		case 3:
			this.totalCookTime = value;
		}
	}

	public int getFieldCount() {
		return 4;
	}

	public void clear() {
		for (int i = 0; i < this.furnaceItemStacks.length; ++i) {
			this.furnaceItemStacks[i] = null;
		}
	}

	net.minecraftforge.items.IItemHandler handlerTop = new net.minecraftforge.items.wrapper.SidedInvWrapper(this,
			net.minecraft.util.EnumFacing.UP);
	net.minecraftforge.items.IItemHandler handlerBottom = new net.minecraftforge.items.wrapper.SidedInvWrapper(this,
			net.minecraft.util.EnumFacing.DOWN);
	net.minecraftforge.items.IItemHandler handlerSide = new net.minecraftforge.items.wrapper.SidedInvWrapper(this,
			net.minecraft.util.EnumFacing.WEST);

	@Override
	public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability,
			net.minecraft.util.EnumFacing facing) {
		if (facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			if (facing == EnumFacing.DOWN)
				return (T) handlerBottom;
			else if (facing == EnumFacing.UP)
				return (T) handlerTop;
			else
				return (T) handlerSide;
		return super.getCapability(capability, facing);
	}

	@Override
	public void markDirty() {
		super.markDirty();
	}

	@Override
	public IChatComponent getDisplayName() {
		return this.hasCustomName() ? new ChatComponentText(this.furnaceCustomName)
				: new ChatComponentTranslation(this.getName());
	}

	@Override
	public List<String> getWhitelistedPlayers() {
		List<String> stringList = new ArrayList<String>();
		if (!getTileData().hasKey("ProtectedFurnaceWhitelistedPlayers")) {
			getTileData().setTag("ProtectedFurnaceWhitelistedPlayers", new NBTTagList());
			return stringList;
		}
		NBTTagList list = (NBTTagList) getTileData().getTag("ProtectedFurnaceWhitelistedPlayers");
		for (int i = 0; i < list.tagCount(); i++) {
			stringList.add(list.getStringTagAt(i));
		}
		return stringList;
	}

	@Override
	public UUID getOwner() {
		return UUID.fromString(this.getTileData().getString("ProtectedFurnaceOwner"));
	}

	@Override
	public void setOwner(UUID u) {
		this.getTileData().setString("ProtectedFurnaceOwner", u.toString());
	}

	@Override
	public boolean isPlayerCapableOfOpeningBlock(EntityPlayer ep) {
		if (GameHelper.getUtils().isHacker((EntityPlayerMP) ep))
			return true;
		if (isOwner(ep))
			return true;
		if (getLockType() == LockType.ALL_PLAYERS)
			return true;
		else if (getLockType() == LockType.WHITELISTED_PLAYERS)
			return getWhitelistedPlayers().contains(ep.getUniqueID().toString());
		else if (getLockType() == LockType.ONLY_OWNER)
			return isOwner(ep);
		return false;
	}

	@Override
	public boolean isOwner(EntityPlayer ep) {
		return getOwner().equals(ep.getUniqueID());
	}

	@Override
	public void addWhiteListedPlayer(UUID uid) {
		if (!getTileData().hasKey("ProtectedFurnaceWhitelistedPlayers")) {
			getTileData().setTag("ProtectedFurnaceWhitelistedPlayers", new NBTTagList());
		}
		NBTTagList list = (NBTTagList) getTileData().getTag("ProtectedFurnaceWhitelistedPlayers");
		list.appendTag(new NBTTagString(uid.toString()));
	}

	@Override
	public LockType getLockType() {
		return LockType.getFromId(getTileData().getByte("ProtectedFurnaceLockType"));
	}

	@Override
	public void setLockType(LockType l) {
		getTileData().setByte("ProtectedFurnaceLockType", l.getId());
	}
}