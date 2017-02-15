package net.wfoas.gh.protected_blocks.chest;

import java.util.List;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.LockCode;
import net.wfoas.gh.protected_blocks.IProtectedBlock;
import net.wfoas.gh.protected_blocks.LockType;

public class InventoryLargeProtectedChest implements ILockableContainer, IProtectedBlock {
	private String name;
	private ILockableContainer upperChest;
	private ILockableContainer lowerChest;

	public ILockableContainer[] getILockContainerProtected() {
		return new ILockableContainer[] { upperChest, lowerChest };
	}

	public InventoryLargeProtectedChest(String nameIn, ILockableContainer upperChestIn,
			ILockableContainer lowerChestIn) {
		this.name = nameIn;

		if (upperChestIn == null) {
			upperChestIn = lowerChestIn;
		}

		if (lowerChestIn == null) {
			lowerChestIn = upperChestIn;
		}

		this.upperChest = upperChestIn;
		this.lowerChest = lowerChestIn;

		if (upperChestIn.isLocked()) {
			lowerChestIn.setLockCode(upperChestIn.getLockCode());
		} else if (lowerChestIn.isLocked()) {
			upperChestIn.setLockCode(lowerChestIn.getLockCode());
		}
	}

	public int getSizeInventory() {
		return this.upperChest.getSizeInventory() + this.lowerChest.getSizeInventory();
	}

	public boolean isPartOfLargeChest(IInventory inventoryIn) {
		return this.upperChest == inventoryIn || this.lowerChest == inventoryIn;
	}

	public String getName() {
		return this.upperChest.hasCustomName() ? this.upperChest.getName()
				: (this.lowerChest.hasCustomName() ? this.lowerChest.getName() : this.name);
	}

	public boolean hasCustomName() {
		return this.upperChest.hasCustomName() || this.lowerChest.hasCustomName();
	}

	public IChatComponent getDisplayName() {
		return (IChatComponent) (this.hasCustomName() ? new ChatComponentText(this.getName())
				: new ChatComponentTranslation(this.getName(), new Object[0]));
	}

	public ItemStack getStackInSlot(int index) {
		return index >= this.upperChest.getSizeInventory()
				? this.lowerChest.getStackInSlot(index - this.upperChest.getSizeInventory())
				: this.upperChest.getStackInSlot(index);
	}

	public ItemStack decrStackSize(int index, int count) {
		return index >= this.upperChest.getSizeInventory()
				? this.lowerChest.decrStackSize(index - this.upperChest.getSizeInventory(), count)
				: this.upperChest.decrStackSize(index, count);
	}

	public ItemStack removeStackFromSlot(int index) {
		return index >= this.upperChest.getSizeInventory()
				? this.lowerChest.removeStackFromSlot(index - this.upperChest.getSizeInventory())
				: this.upperChest.removeStackFromSlot(index);
	}

	public void setInventorySlotContents(int index, ItemStack stack) {
		if (index >= this.upperChest.getSizeInventory()) {
			this.lowerChest.setInventorySlotContents(index - this.upperChest.getSizeInventory(), stack);
		} else {
			this.upperChest.setInventorySlotContents(index, stack);
		}
	}

	public int getInventoryStackLimit() {
		return this.upperChest.getInventoryStackLimit();
	}

	public void markDirty() {
		this.upperChest.markDirty();
		this.lowerChest.markDirty();
	}

	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.upperChest.isUseableByPlayer(player) && this.lowerChest.isUseableByPlayer(player);
	}

	public void openInventory(EntityPlayer player) {
		this.upperChest.openInventory(player);
		this.lowerChest.openInventory(player);
	}

	public void closeInventory(EntityPlayer player) {
		this.upperChest.closeInventory(player);
		this.lowerChest.closeInventory(player);
	}

	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}

	public int getField(int id) {
		return 0;
	}

	public void setField(int id, int value) {
	}

	public int getFieldCount() {
		return 0;
	}

	public boolean isLocked() {
		return this.upperChest.isLocked() || this.lowerChest.isLocked();
	}

	public void setLockCode(LockCode code) {
		this.upperChest.setLockCode(code);
		this.lowerChest.setLockCode(code);
	}

	public LockCode getLockCode() {
		return this.upperChest.getLockCode();
	}

	public String getGuiID() {
		return this.upperChest.getGuiID();
	}

	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerProtectedChest(playerInventory, this, playerIn);
	}

	public void clear() {
		this.upperChest.clear();
		this.lowerChest.clear();
	}

	private boolean invlpt(ILockableContainer i) {
		return i instanceof InventoryLargeProtectedChest;
	}

	private boolean sprotch(ILockableContainer i) {
		return i instanceof ProtectedChestTileEntity;
	}

	private boolean isIProtectedBlock(ILockableContainer i) {
		return i instanceof IProtectedBlock;
	}

	private IProtectedBlock protB(ILockableContainer i) {
		return (IProtectedBlock) i;
	}

	@Override
	public UUID getOwner() {
		return isIProtectedBlock(upperChest) ? protB(upperChest).getOwner() : null;
	}

	@Override
	public void setOwner(UUID u) {
		protB(upperChest).setOwner(u);
		protB(lowerChest).setOwner(u);
	}

	@Override
	public boolean isPlayerCapableOfOpeningBlock(EntityPlayer ep) {
		return protB(lowerChest).isPlayerCapableOfOpeningBlock(ep)
				|| protB(upperChest).isPlayerCapableOfOpeningBlock(ep);
	}

	@Override
	public boolean isOwner(EntityPlayer ep) {
		return protB(upperChest).isOwner(ep) && protB(lowerChest).isOwner(ep);
	}

	@Override
	public LockType getLockType() {
		return protB(upperChest).getLockType();
	}

	@Override
	public List<String> getWhitelistedPlayers() {
		return protB(upperChest).getWhitelistedPlayers();
	}

	@Override
	public void addWhiteListedPlayer(UUID uid) {
		protB(upperChest).addWhiteListedPlayer(uid);
		protB(lowerChest).addWhiteListedPlayer(uid);
	}

	@Override
	public void setLockType(LockType l) {
		protB(upperChest).setLockType(l);
		protB(lowerChest).setLockType(l);
	}

	@Override
	public void removeWhiteListedPlayer(UUID uid) {
		protB(upperChest).removeWhiteListedPlayer(uid);
		protB(lowerChest).removeWhiteListedPlayer(uid);
	}

	@Override
	public Packet getCUDescrPacket() {
		return null;
	}
}