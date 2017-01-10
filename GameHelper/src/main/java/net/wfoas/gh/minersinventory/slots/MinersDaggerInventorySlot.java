package net.wfoas.gh.minersinventory.slots;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.wfoas.gh.GameHelperCoreModule;
import net.wfoas.gh.minersinventory.MinersInventory;
import net.wfoas.gh.minersinventory.MinersInventorySlot;

public class MinersDaggerInventorySlot extends MinersInventorySlot {

	public MinersDaggerInventorySlot(MinersInventory inv, int index, int xPos, int yPos) {
		super(inv, index, xPos, yPos);
	}

	@Override
	public boolean isItemValid(ItemStack itemstack) {
		return itemstack.getItem() == GameHelperCoreModule.MINERS_DAGGER;
	}

	public static class DaggerInventorySlot extends MinersInventorySlot {

		public DaggerInventorySlot(MinersInventory inv, int index, int xPos, int yPos) {
			super(inv, index, xPos, yPos);
		}

		@Override
		public boolean isItemValid(ItemStack itemstack) {
			return itemstack.getItem() == GameHelperCoreModule.IRON_DAGGER
					&& this.minersInventory.hasMinersDaggerSlot();
		}

		@SideOnly(value = Side.CLIENT)
		@Override
		public boolean canBeHovered() {
			return this.minersInventory.hasMinersDaggerSlot();
		}
	}

	@SideOnly(value = Side.CLIENT)
	@Override
	public boolean canBeHovered() {
		return true;
	}
}
