package net.wfoas.gh.protected_blocks.chest;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;

import java.lang.ref.WeakReference;

import com.google.common.base.Objects;

public class GHDoubleChestHandler extends WeakReference<ProtectedChestTileEntity> implements IItemHandler {
	// Dummy cache value to signify that we have checked and definitely found no
	// adjacent chests BlockFurnace
	public static final GHDoubleChestHandler NO_ADJACENT_CHESTS_INSTANCE = new GHDoubleChestHandler(null, null, false);
	private final boolean mainChestIsUpper;
	private final ProtectedChestTileEntity mainChest;
	private final int hashCode;

	public GHDoubleChestHandler(ProtectedChestTileEntity mainChest, ProtectedChestTileEntity other,
			boolean mainChestIsUpper) {
		super(other);
		this.mainChest = mainChest;
		this.mainChestIsUpper = mainChestIsUpper;
		hashCode = Objects.hashCode(mainChestIsUpper ? mainChest : other) * 31
				+ Objects.hashCode(!mainChestIsUpper ? mainChest : other);
	}

	public static GHDoubleChestHandler get(ProtectedChestTileEntity chest) {
		World world = chest.getWorld();
		BlockPos pos = chest.getPos();
		if (world == null || pos == null || !world.isBlockLoaded(pos))
			return null; // Still loading

		Block blockType = chest.getBlockType();

		EnumFacing[] horizontals = EnumFacing.HORIZONTALS;
		for (int i = horizontals.length - 1; i >= 0; i--) // Use reverse order
															// so we can return
															// early
		{
			EnumFacing enumfacing = horizontals[i];
			BlockPos blockpos = pos.offset(enumfacing);
			Block block = world.getBlockState(blockpos).getBlock();

			if (block == blockType) {
				TileEntity otherTE = world.getTileEntity(blockpos);

				if (otherTE instanceof ProtectedChestTileEntity) {
					ProtectedChestTileEntity otherChest = (ProtectedChestTileEntity) otherTE;
					return new GHDoubleChestHandler(chest, otherChest, enumfacing != net.minecraft.util.EnumFacing.WEST
							&& enumfacing != net.minecraft.util.EnumFacing.NORTH);

				}
			}
		}
		return NO_ADJACENT_CHESTS_INSTANCE; // All alone
	}

	public ProtectedChestTileEntity getChest(boolean accessingUpper) {
		if (accessingUpper == mainChestIsUpper)
			return mainChest;
		else {
			return getOtherChest();
		}
	}

	private ProtectedChestTileEntity getOtherChest() {
		ProtectedChestTileEntity tileEntityChest = get();
		return tileEntityChest != null && !tileEntityChest.isInvalid() ? tileEntityChest : null;
	}

	@Override
	public int getSlots() {
		return 27 * 2;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		boolean accessingUpperChest = slot < 27;
		int targetSlot = accessingUpperChest ? slot : slot - 27;
		ProtectedChestTileEntity chest = getChest(accessingUpperChest);
		return chest != null ? chest.getStackInSlot(targetSlot) : null;
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
		boolean accessingUpperChest = slot < 27;
		int targetSlot = accessingUpperChest ? slot : slot - 27;
		ProtectedChestTileEntity chest = getChest(accessingUpperChest);
		return chest != null ? chest.getSingleChestHandler().insertItem(targetSlot, stack, simulate) : stack;
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		boolean accessingUpperChest = slot < 27;
		int targetSlot = accessingUpperChest ? slot : slot - 27;
		ProtectedChestTileEntity chest = getChest(accessingUpperChest);
		return chest != null ? chest.getSingleChestHandler().extractItem(targetSlot, amount, simulate) : null;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		GHDoubleChestHandler that = (GHDoubleChestHandler) o;

		if (hashCode != that.hashCode)
			return false;

		final ProtectedChestTileEntity otherChest = getOtherChest();
		if (mainChestIsUpper == that.mainChestIsUpper)
			return Objects.equal(mainChest, that.mainChest) && Objects.equal(otherChest, that.getOtherChest());
		else
			return Objects.equal(mainChest, that.getOtherChest()) && Objects.equal(otherChest, that.mainChest);
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	public boolean needsRefresh() {
		if (this == NO_ADJACENT_CHESTS_INSTANCE)
			return false;
		ProtectedChestTileEntity tileEntityChest = get();
		return tileEntityChest == null || tileEntityChest.isInvalid();
	}
}