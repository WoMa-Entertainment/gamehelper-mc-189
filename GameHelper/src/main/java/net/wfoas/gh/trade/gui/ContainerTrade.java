package net.wfoas.gh.trade.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerTrade extends Container {

	public ContainerTrade(Slot[] _27Slots, Slot[] _other_27Slots) {
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

}
