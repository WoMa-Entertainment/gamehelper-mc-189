package net.wfoas.gh.op_anvil;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.block.BlockAnvil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.wfoas.gh.GameHelperCoreModule;

public class ContainerOPAnvil extends Container {
	private static final Logger logger = LogManager.getLogger();
	private IInventory outputSlot;
	private IInventory inputSlots;
	private World theWorld;
	public int maximumCost;
	public int materialCost;
	private String repairedItemName;
	private final EntityPlayer thePlayer;
	final BlockPos pos;

	public ContainerOPAnvil(InventoryPlayer playerInventory, final World worldIn, EntityPlayer player,
			final BlockPos pos) {
		this.outputSlot = new InventoryCraftResult();
		this.inputSlots = new InventoryBasic("Repair", true, 1) {
			public void markDirty() {
				super.markDirty();
				ContainerOPAnvil.this.onCraftMatrixChanged(this);
			}

			@Override
			public int getInventoryStackLimit() {
				return 64;
			}
		};
		this.pos = pos;
		this.theWorld = worldIn;
		this.thePlayer = player;
		this.addSlotToContainer(new Slot(this.inputSlots, 0, 51, 47));
		this.addSlotToContainer(new Slot(this.outputSlot, 1, 109, 47) {
			public boolean isItemValid(ItemStack stack) {
				return false;
			}

			public boolean canTakeStack(EntityPlayer playerIn) {
				return (playerIn.capabilities.isCreativeMode
						|| playerIn.experienceLevel >= ContainerOPAnvil.this.maximumCost)
						&& ContainerOPAnvil.this.maximumCost > 0 && this.getHasStack();
			}

			public void onPickupFromSlot(EntityPlayer playerIn, ItemStack stack) {
				if (!playerIn.capabilities.isCreativeMode) {
					playerIn.addExperienceLevel(-20);
				}
				float breakChance = net.minecraftforge.common.ForgeHooks.onAnvilRepair(playerIn, stack,
						ContainerOPAnvil.this.inputSlots.getStackInSlot(0).copy(),
						ContainerOPAnvil.this.inputSlots.getStackInSlot(0).copy());
				ContainerOPAnvil.this.inputSlots.setInventorySlotContents(0, (ItemStack) null);
				if (ContainerOPAnvil.this.pos == null) {
					return;
				}
				IBlockState iblockstate = worldIn.getBlockState(ContainerOPAnvil.this.pos);
				if (!playerIn.capabilities.isCreativeMode && !worldIn.isRemote
						&& iblockstate.getBlock().equals(GameHelperCoreModule.OP_ANVIL)
						&& playerIn.getRNG().nextFloat() < breakChance) {
					int l = ((Integer) iblockstate.getValue(BlockAnvil.DAMAGE)).intValue();
					++l;

					if (l > 2) {
						worldIn.setBlockToAir(ContainerOPAnvil.this.pos);
						worldIn.playAuxSFX(1020, ContainerOPAnvil.this.pos, 0);
					} else {
						worldIn.setBlockState(ContainerOPAnvil.this.pos,
								iblockstate.withProperty(BlockAnvil.DAMAGE, Integer.valueOf(l)), 2);
						worldIn.playAuxSFX(1021, ContainerOPAnvil.this.pos, 0);
					}
				} else if (!worldIn.isRemote) {
					worldIn.playAuxSFX(1021, ContainerOPAnvil.this.pos, 0);
				}
				ContainerOPAnvil.this.maximumCost = 20;
			}
		});
		int i;
		for (i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		for (i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(playerInventory, i, 8 + i * 18, 142));
		}
	}

	public void onCraftMatrixChanged(IInventory inventoryIn) {
		super.onCraftMatrixChanged(inventoryIn);

		if (inventoryIn == this.inputSlots) {
			this.updateRepairOutput();
		}
	}

	public static boolean onAnvilChange(ContainerOPAnvil container, ItemStack left, IInventory outputSlot, String name,
			int baseCost) {
		ItemStack toRep = left.copy();
		AnvilUpdateEvent e = new AnvilUpdateEvent(left.copy(), left.copy(), name, baseCost);
		outputSlot.setInventorySlotContents(0, null);
		if (MinecraftForge.EVENT_BUS.post(e))
			return false;
		if (e.output == null) {
			if (!toRep.isItemStackDamageable() || !toRep.isItemDamaged()) {
				container.maximumCost = 0;
				return false;
			}
			ItemStack out = toRep.copy();
			out.setItemDamage(0);
			outputSlot.setInventorySlotContents(0, out);
			container.maximumCost = 20;
			return false;
		}
		return false;
	}

	public void updateRepairOutput() {
		ItemStack itemstack = this.inputSlots.getStackInSlot(0);
		if (itemstack == null) {
			this.outputSlot.setInventorySlotContents(0, (ItemStack) null);
		} else {
			ItemStack itemstack1 = itemstack.copy();
			if (itemstack1 != null) {
				onAnvilChange(this, itemstack, outputSlot, repairedItemName, 0);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		if (id == 0) {
			this.maximumCost = data;
		}
	}

	public void onContainerClosed(EntityPlayer playerIn) {
		super.onContainerClosed(playerIn);

		if (!this.theWorld.isRemote) {
			for (int i = 0; i < this.inputSlots.getSizeInventory(); ++i) {
				ItemStack itemstack = this.inputSlots.removeStackFromSlot(i);

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
			if (index == 1) {
				if (!this.mergeItemStack(itemstack1, 2, 38, true)) {
					return null;
				}
				slot.onSlotChange(itemstack1, itemstack);
			} else if (index != 0) {
				if (index >= 2 && index < 38 && !this.mergeItemStack(itemstack1, 0, 1, false)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 2, 38, false)) {
				return null;
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