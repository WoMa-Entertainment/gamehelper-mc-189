package net.wfoas.gh.effectorbs;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EmptyOrb extends EffectOrb {

	public EmptyOrb() {
		super("empty_orb");
	}

	@Override
	public void onPlayerUpdate(ItemStack stack, World world, EntityPlayer p, int itemSlot, boolean isSel) {
//		p.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 2, 1));
	}

}
