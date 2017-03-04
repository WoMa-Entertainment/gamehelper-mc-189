package net.wfoas.gh.minersinventory.slots;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.GameHelperCoreModule;
import net.wfoas.gh.minersinventory.MinersInventory;
import net.wfoas.gh.minersinventory.MinersInventorySlot;
import net.wfoas.gh.omapi.GameHelperAPI;

public class MinersBeltInventorySlot extends MinersInventorySlot {

	public MinersBeltInventorySlot(MinersInventory inv, int index, int xPos, int yPos) {
		super(inv, index, xPos, yPos);
	}

	@Override
	public boolean isItemValid(ItemStack itemstack) {
		return itemstack.getItem() == GameHelperCoreModule.MINERS_TOOLS_BELT;
	}

	public static class MinersBeltPickaxeInventorySlot extends MinersInventorySlot {

		public MinersBeltPickaxeInventorySlot(MinersInventory inv, int index, int xPos, int yPos) {
			super(inv, index, xPos, yPos);
		}

		@Override
		public boolean isItemValid(ItemStack itemstack) {
			return GameHelperAPI.ghAPI().ghItemAPI().isPickaxe(itemstack.getItem())
					&& this.minersInventory.hasMinersBelt();
		}

		@SideOnly(value = Side.CLIENT)
		@Override
		public boolean canBeHovered() {
			return this.minersInventory.hasMinersBelt();
		}
	}

	public static class MinersBeltAxeInventorySlot extends MinersInventorySlot {

		public MinersBeltAxeInventorySlot(MinersInventory inv, int index, int xPos, int yPos) {
			super(inv, index, xPos, yPos);
		}

		@Override
		public boolean isItemValid(ItemStack itemstack) {
			return GameHelperAPI.ghAPI().ghItemAPI().isAxe(itemstack.getItem()) && this.minersInventory.hasMinersBelt();
		}

		@SideOnly(value = Side.CLIENT)
		@Override
		public boolean canBeHovered() {
			return this.minersInventory.hasMinersBelt();
		}
	}

	public static class MinersBeltShovelInventorySlot extends MinersInventorySlot {

		public MinersBeltShovelInventorySlot(MinersInventory inv, int index, int xPos, int yPos) {
			super(inv, index, xPos, yPos);
		}

		@Override
		public boolean isItemValid(ItemStack itemstack) {
			return GameHelperAPI.ghAPI().ghItemAPI().isShovel(itemstack.getItem())
					&& this.minersInventory.hasMinersBelt();
		}

		@SideOnly(value = Side.CLIENT)
		@Override
		public boolean canBeHovered() {
			return this.minersInventory.hasMinersBelt();
		}
	}

	public static class MinersBeltLadderInventorySlot extends MinersInventorySlot {

		public MinersBeltLadderInventorySlot(MinersInventory inv, int index, int xPos, int yPos) {
			super(inv, index, xPos, yPos);
		}

		@Override
		public boolean isItemValid(ItemStack itemstack) {
			return itemstack.getItem() instanceof ItemBlock && ((ItemBlock) itemstack.getItem()).getBlock() != null
					&& GameHelperAPI.ghAPI().ghMinersInventoryAPI()
							.isMinersBeltLadderInvSlotItemValid(itemstack.getItem())
					&& this.minersInventory.hasMinersBelt();
		}

		@SideOnly(value = Side.CLIENT)
		@Override
		public boolean canBeHovered() {
			return this.minersInventory.hasMinersBelt();
		}
	}

	public static class MinersBeltBlockInventorySlot extends MinersInventorySlot {

		public MinersBeltBlockInventorySlot(MinersInventory inv, int index, int xPos, int yPos) {
			super(inv, index, xPos, yPos);
		}

		@Override
		public boolean isItemValid(ItemStack itemstack) {
			return itemstack.getItem() instanceof ItemBlock && this.minersInventory.hasMinersBelt();
		}

		@SideOnly(value = Side.CLIENT)
		@Override
		public boolean canBeHovered() {
			return this.minersInventory.hasMinersBelt();
		}
	}

	@SideOnly(value = Side.CLIENT)
	@Override
	public boolean canBeHovered() {
		return true;
	}
}
