package net.wfoas.gh.protected_blocks.chest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.protected_blocks.IProtectedBlock;
import net.wfoas.gh.protected_blocks.LockType;

public class ProtectedChestTileEntity extends TileEntityLockable implements ITickable, IInventory, IProtectedBlock {
	private ItemStack[] chestContents = new ItemStack[27];
	/** Determines if the check for adjacent chests has taken place. */
	public boolean adjacentChestChecked;
	/** Contains the chest tile located adjacent to this one (if any) */
	public ProtectedChestTileEntity adjacentChestZNeg;
	/** Contains the chest tile located adjacent to this one (if any) */
	public ProtectedChestTileEntity adjacentChestXPos;
	/** Contains the chest tile located adjacent to this one (if any) */
	public ProtectedChestTileEntity adjacentChestXNeg;
	/** Contains the chest tile located adjacent to this one (if any) */
	public ProtectedChestTileEntity adjacentChestZPos;
	/** The current angle of the lid (between 0 and 1) */
	public float lidAngle;
	/** The angle of the lid last tick */
	public float prevLidAngle;
	/** The number of players currently using this chest */
	public int numPlayersUsing;
	/** Server sync counter (once per 20 ticks) */
	private int ticksSinceSync;
	private String customName;

	public ProtectedChestTileEntity() {
	}

	public ProtectedChestTileEntity owner(EntityPlayer epmp) {
		getTileData().setString("ProtectedChestOwner", epmp.getUniqueID().toString());
		return this;
	}

	@SideOnly(Side.CLIENT)
	public ProtectedChestTileEntity(int chestType) {
	}

	public int getSizeInventory() {
		return 27;
	}

	public ItemStack getStackInSlot(int index) {
		return this.chestContents[index];
	}

	public ItemStack decrStackSize(int index, int count) {
		if (this.chestContents[index] != null) {
			if (this.chestContents[index].stackSize <= count) {
				ItemStack itemstack1 = this.chestContents[index];
				this.chestContents[index] = null;
				this.markDirty();
				return itemstack1;
			} else {
				ItemStack itemstack = this.chestContents[index].splitStack(count);

				if (this.chestContents[index].stackSize == 0) {
					this.chestContents[index] = null;
				}

				this.markDirty();
				return itemstack;
			}
		} else {
			return null;
		}
	}

	public ItemStack removeStackFromSlot(int index) {
		if (this.chestContents[index] != null) {
			ItemStack itemstack = this.chestContents[index];
			this.chestContents[index] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	public void setInventorySlotContents(int index, ItemStack stack) {
		this.chestContents[index] = stack;

		if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
			stack.stackSize = this.getInventoryStackLimit();
		}

		this.markDirty();
	}

	public String getName() {
		return this.hasCustomName() ? this.getName() : "tile.gamehelper.protected_chest.name";
	}

	public boolean hasCustomName() {
		return this.customName != null && this.customName.length() > 0;
	}

	public void setCustomName(String name) {
		this.customName = name;
	}

	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		NBTTagList nbttaglist = compound.getTagList("Items", 10);
		this.chestContents = new ItemStack[this.getSizeInventory()];

		if (compound.hasKey("CustomName", 8)) {
			this.customName = compound.getString("CustomName");
		}

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound.getByte("Slot") & 255;

			if (j >= 0 && j < this.chestContents.length) {
				this.chestContents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound);
			}
		}
	}

	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.chestContents.length; ++i) {
			if (this.chestContents[i] != null) {
				NBTTagCompound nbttagcompound = new NBTTagCompound();
				nbttagcompound.setByte("Slot", (byte) i);
				this.chestContents[i].writeToNBT(nbttagcompound);
				nbttaglist.appendTag(nbttagcompound);
			}
		}

		compound.setTag("Items", nbttaglist);

		if (this.hasCustomName()) {
			compound.setString("CustomName", this.customName);
		}
	}

	/**
	 * Returns the maximum stack size for a inventory slot. Seems to always be
	 * 64, possibly will be extended.
	 */
	public int getInventoryStackLimit() {
		return 64;
	}

	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getTileEntity(this.pos) != this ? false
				: player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D,
						(double) this.pos.getZ() + 0.5D) <= 64.0D;
	}

	public void updateContainingBlockInfo() {
		super.updateContainingBlockInfo();
		this.adjacentChestChecked = false;
		doubleChestHandler = null;
	}

	@SuppressWarnings("incomplete-switch")
	private void func_174910_a(ProtectedChestTileEntity chestTe, EnumFacing side) {
		if (chestTe.isInvalid()) {
			this.adjacentChestChecked = false;
		} else if (this.adjacentChestChecked) {
			switch (side) {
			case NORTH:

				if (this.adjacentChestZNeg != chestTe) {
					this.adjacentChestChecked = false;
				}

				break;
			case SOUTH:

				if (this.adjacentChestZPos != chestTe) {
					this.adjacentChestChecked = false;
				}

				break;
			case EAST:

				if (this.adjacentChestXPos != chestTe) {
					this.adjacentChestChecked = false;
				}

				break;
			case WEST:

				if (this.adjacentChestXNeg != chestTe) {
					this.adjacentChestChecked = false;
				}
			}
		}
	}

	public void checkForAdjacentChests() {
		if (!this.adjacentChestChecked) {
			this.adjacentChestChecked = true;
			this.adjacentChestXNeg = this.getAdjacentChest(EnumFacing.WEST);
			this.adjacentChestXPos = this.getAdjacentChest(EnumFacing.EAST);
			this.adjacentChestZNeg = this.getAdjacentChest(EnumFacing.NORTH);
			this.adjacentChestZPos = this.getAdjacentChest(EnumFacing.SOUTH);
		}
	}

	protected ProtectedChestTileEntity getAdjacentChest(EnumFacing side) {
		BlockPos blockpos = this.pos.offset(side);

		if (this.isChestAt(blockpos)) {
			TileEntity tileentity = this.worldObj.getTileEntity(blockpos);

			if (tileentity instanceof ProtectedChestTileEntity) {
				ProtectedChestTileEntity tileentitychest = (ProtectedChestTileEntity) tileentity;
				tileentitychest.func_174910_a(this, side.getOpposite());
				return tileentitychest;
			}
		}

		return null;
	}

	private boolean isChestAt(BlockPos posIn) {
		if (this.worldObj == null) {
			return false;
		} else {
			Block block = this.worldObj.getBlockState(posIn).getBlock();
			return block instanceof ProtectedChestTileEntityBlock;
		}
	}

	/**
	 * Like the old updateEntity(), except more generic.
	 */
	public void update() {
		this.checkForAdjacentChests();
		int i = this.pos.getX();
		int j = this.pos.getY();
		int k = this.pos.getZ();
		++this.ticksSinceSync;

		if (!this.worldObj.isRemote && this.numPlayersUsing != 0 && (this.ticksSinceSync + i + j + k) % 200 == 0) {
			this.numPlayersUsing = 0;
			float f = 5.0F;

			for (EntityPlayer entityplayer : this.worldObj.getEntitiesWithinAABB(EntityPlayer.class,
					new AxisAlignedBB((double) ((float) i - f), (double) ((float) j - f), (double) ((float) k - f),
							(double) ((float) (i + 1) + f), (double) ((float) (j + 1) + f),
							(double) ((float) (k + 1) + f)))) {
				if (entityplayer.openContainer instanceof ContainerProtectedChest) {
					IInventory iinventory = ((ContainerProtectedChest) entityplayer.openContainer)
							.getLowerChestInventory();

					if (iinventory == this || iinventory instanceof InventoryLargeProtectedChest
							&& ((InventoryLargeProtectedChest) iinventory).isPartOfLargeChest(this)) {
						++this.numPlayersUsing;
					}
				}
			}
		}

		this.prevLidAngle = this.lidAngle;
		float f1 = 0.1F;

		if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F && this.adjacentChestZNeg == null
				&& this.adjacentChestXNeg == null) {
			double d1 = (double) i + 0.5D;
			double d2 = (double) k + 0.5D;

			if (this.adjacentChestZPos != null) {
				d2 += 0.5D;
			}

			if (this.adjacentChestXPos != null) {
				d1 += 0.5D;
			}

			this.worldObj.playSoundEffect(d1, (double) j + 0.5D, d2, "random.chestopen", 0.5F,
					this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
		}

		if (this.numPlayersUsing == 0 && this.lidAngle > 0.0F || this.numPlayersUsing > 0 && this.lidAngle < 1.0F) {
			float f2 = this.lidAngle;

			if (this.numPlayersUsing > 0) {
				this.lidAngle += f1;
			} else {
				this.lidAngle -= f1;
			}

			if (this.lidAngle > 1.0F) {
				this.lidAngle = 1.0F;
			}

			float f3 = 0.5F;

			if (this.lidAngle < f3 && f2 >= f3 && this.adjacentChestZNeg == null && this.adjacentChestXNeg == null) {
				double d3 = (double) i + 0.5D;
				double d0 = (double) k + 0.5D;

				if (this.adjacentChestZPos != null) {
					d0 += 0.5D;
				}

				if (this.adjacentChestXPos != null) {
					d3 += 0.5D;
				}

				this.worldObj.playSoundEffect(d3, (double) j + 0.5D, d0, "random.chestclosed", 0.5F,
						this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
			}

			if (this.lidAngle < 0.0F) {
				this.lidAngle = 0.0F;
			}
		}
	}

	public boolean receiveClientEvent(int id, int type) {
		if (id == 1) {
			this.numPlayersUsing = type;
			return true;
		} else {
			return super.receiveClientEvent(id, type);
		}
	}

	public void openInventory(EntityPlayer player) {
		if (!player.isSpectator()) {
			if (this.numPlayersUsing < 0) {
				this.numPlayersUsing = 0;
			}

			++this.numPlayersUsing;
			this.worldObj.addBlockEvent(this.pos, this.getBlockType(), 1, this.numPlayersUsing);
			this.worldObj.notifyNeighborsOfStateChange(this.pos, this.getBlockType());
			this.worldObj.notifyNeighborsOfStateChange(this.pos.down(), this.getBlockType());
		}
	}

	public void closeInventory(EntityPlayer player) {
		if (!player.isSpectator() && this.getBlockType() instanceof ProtectedChestTileEntityBlock) {
			--this.numPlayersUsing;
			this.worldObj.addBlockEvent(this.pos, this.getBlockType(), 1, this.numPlayersUsing);
			this.worldObj.notifyNeighborsOfStateChange(this.pos, this.getBlockType());
			this.worldObj.notifyNeighborsOfStateChange(this.pos.down(), this.getBlockType());
		}
	}

	/**
	 * Returns true if automation is allowed to insert the given stack (ignoring
	 * stack size) into the given slot.
	 */
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}

	/**
	 * invalidates a tile entity
	 */
	public void invalidate() {
		super.invalidate();
		this.updateContainingBlockInfo();
		this.checkForAdjacentChests();
	}

	public String getGuiID() {
		return "minecraft:chest";
	}

	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		// return new ContainerChest(playerInventory, this, playerIn);
		return new ContainerProtectedChest(playerInventory, this, playerIn);
	}

	public int getField(int id) {
		return 0;
	}

	public void setField(int id, int value) {
	}

	public int getFieldCount() {
		return 0;
	}

	public void clear() {
		for (int i = 0; i < this.chestContents.length; ++i) {
			this.chestContents[i] = null;
		}
	}

	public GHDoubleChestHandler doubleChestHandler;

	@Override
	public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability,
			net.minecraft.util.EnumFacing facing) {
		if (capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if (doubleChestHandler == null || doubleChestHandler.needsRefresh())
				doubleChestHandler = GHDoubleChestHandler.get(this);
			if (doubleChestHandler != null && doubleChestHandler != GHDoubleChestHandler.NO_ADJACENT_CHESTS_INSTANCE)
				return (T) doubleChestHandler;
		}
		return super.getCapability(capability, facing);
	}

	public net.minecraftforge.items.IItemHandler getSingleChestHandler() {
		return super.getCapability(net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
	}

	@Override
	public List<String> getWhitelistedPlayers() {
		List<String> stringList = new ArrayList<String>();
		if (!getTileData().hasKey("ProtectedChestWhitelistedPlayers")) {
			getTileData().setTag("ProtectedChestWhitelistedPlayers", new NBTTagList());
			return stringList;
		}
		NBTTagList list = (NBTTagList) getTileData().getTag("ProtectedChestWhitelistedPlayers");
		for (int i = 0; i < list.tagCount(); i++) {
			stringList.add(list.getStringTagAt(i));
		}
		return stringList;
	}

	@Override
	public UUID getOwner() {
		return UUID.fromString(this.getTileData().getString("ProtectedChestOwner"));
	}

	@Override
	public void setOwner(UUID u) {
		this.getTileData().setString("ProtectedChestOwner", u.toString());
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
		if (!getTileData().hasKey("ProtectedChestWhitelistedPlayers")) {
			getTileData().setTag("ProtectedChestWhitelistedPlayers", new NBTTagList());
		}
		NBTTagList list = (NBTTagList) getTileData().getTag("ProtectedChestWhitelistedPlayers");
		list.appendTag(new NBTTagString(uid.toString()));
	}

	@Override
	public LockType getLockType() {
		return LockType.getFromId(getTileData().getByte("ProtectedChestLockType"));
	}

	@Override
	public void setLockType(LockType l) {
		getTileData().setByte("ProtectedChestLockType", l.getId());
	}
}
