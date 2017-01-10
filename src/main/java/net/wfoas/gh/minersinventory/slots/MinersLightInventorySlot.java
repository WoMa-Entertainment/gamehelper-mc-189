package net.wfoas.gh.minersinventory.slots;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.wfoas.gh.GameHelperCoreModule;
import net.wfoas.gh.minersinventory.MinersInventory;
import net.wfoas.gh.minersinventory.MinersInventorySlot;

public class MinersLightInventorySlot extends MinersInventorySlot {

	public MinersLightInventorySlot(MinersInventory inv, int index, int xPos, int yPos) {
		super(inv, index, xPos, yPos);
	}

	@Override
	public boolean isItemValid(ItemStack itemstack) {
		return itemstack.getItem() == GameHelperCoreModule.MINERS_LAMP;
	}

	@SideOnly(value = Side.CLIENT)
	@Override
	public boolean canBeHovered() {
		return true;
	}

}
