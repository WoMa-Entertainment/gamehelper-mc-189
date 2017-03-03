package net.wfoas.gh.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.util.ResourceLocation;
import net.wfoas.gh.GameHelperCoreModule;
import net.wfoas.gh.omapi.GameHelperAPI;

public class EnchantmentXPBoost extends Enchantment {
	public EnchantmentXPBoost(int enchID, ResourceLocation enchName, int enchWeight) {
		super(enchID, enchName, enchWeight, EnumEnchantmentType.ALL);
		this.setName("xp_boost");
	}

	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return 12;
	}

	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return 50;
	}

	@Override
	public int getMaxLevel() {
		return 1;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		if ((stack.getItem() instanceof ItemSword) || GameHelperAPI.ghEnchantAPI().isSword(stack.getItem()))
			return true;
		if (stack.getItem() instanceof ItemTool || GameHelperAPI.ghEnchantAPI().isPickaxe(stack.getItem())
				|| GameHelperAPI.ghEnchantAPI().isAxe(stack.getItem())
				|| GameHelperAPI.ghEnchantAPI().isShovel(stack.getItem()))
			return true;
		else
			return false;
	}
}