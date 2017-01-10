package net.wfoas.gh.effectorbs;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class FireOrb extends EffectOrb {

	public FireOrb() {
		super("fire_orb");
	}

	@Override
	public void onPlayerUpdate(ItemStack stack, World world, EntityPlayer p, int itemSlot, boolean isSel) {
		p.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 2*20, 0));
	}

}
