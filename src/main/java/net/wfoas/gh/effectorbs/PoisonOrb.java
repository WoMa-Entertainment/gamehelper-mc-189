package net.wfoas.gh.effectorbs;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class PoisonOrb extends EffectOrb {

	public PoisonOrb() {
		super("poison_orb");
	}

	@Override
	public void onPlayerUpdate(ItemStack stack, World world, EntityPlayer p, int itemSlot, boolean isSel) {
		if(!p.isPotionActive(Potion.poison))
			p.addPotionEffect(new PotionEffect(Potion.poison.id, 2*20, 1));
	}

}
