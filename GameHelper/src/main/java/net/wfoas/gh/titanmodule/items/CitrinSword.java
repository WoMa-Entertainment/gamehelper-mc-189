package net.wfoas.gh.titanmodule.items;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.dropsapi.pdr.ChatColor;
import net.wfoas.gh.items.GameHelperModSword;
import net.wfoas.gh.titanmodule.TitanModule;

public class CitrinSword extends GameHelperModSword {

	public CitrinSword() {
		super("citrin_sword", TitanModule.CITRIN_MATERIAL);
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		super.hitEntity(stack, target, attacker);
		if (!target.worldObj.isRemote)
			if (GameHelper.MOD_RANDOM.nextInt(4) == 1) {
				target.addPotionEffect(new PotionEffect(Potion.poison.id, 5 * 20, 2));
			}
		return true;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
		super.addInformation(stack, playerIn, tooltip, advanced);
		tooltip.add(ChatColor.YELLOW + "25% Chance, den Gegner zu vergiften");
	}
}
