package net.wfoas.gh.effectorbs;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class HealingOrb extends EffectOrb {

	public HealingOrb() {
		super("healing_orb");
	}

	@Override
	public void onPlayerUpdate(ItemStack stack, World world, EntityPlayer p, int itemSlot, boolean isSel) {
		if(!p.isPotionActive(Potion.regeneration))
			p.addPotionEffect(new PotionEffect(Potion.regeneration.id, 2*20, 3));
	}

}