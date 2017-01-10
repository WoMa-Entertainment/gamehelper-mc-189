package net.wfoas.gh.effectorbs;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class FuriousOrb extends EffectOrb {

	public FuriousOrb() {
		super("furious_orb");
	}

	@Override
	public void onPlayerUpdate(ItemStack stack, World world, EntityPlayer p, int itemSlot, boolean isSel) {
		p.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 2*20, 1));
		p.addPotionEffect(new PotionEffect(Potion.resistance.id, 2*20, 1));
	}

}
