package net.wfoas.gh.potionbow;

import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

@Cancelable
public class PotionNockEvent extends PlayerEvent {
	public ItemStack result;

	public PotionNockEvent(EntityPlayer player, ItemStack result) {
		super(player);
		this.result = result;
	}
}