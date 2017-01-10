package net.wfoas.gh.uncraftingtable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class UncraftingTableContainer extends Container {

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return false;
	}

}
