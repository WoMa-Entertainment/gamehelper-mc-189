package net.wfoas.gh.effectorbs;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.wfoas.gh.items.GameHelperModItem;

public abstract class EffectOrb extends GameHelperModItem {

	public EffectOrb(String s) {
		super(s);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (entityIn instanceof EntityPlayer)
			onPlayerUpdate(stack, worldIn, ((EntityPlayer) entityIn), itemSlot, isSelected);
	}

	public abstract void onPlayerUpdate(ItemStack stack, World world, EntityPlayer p, int itemSlot, boolean isSel);
}
