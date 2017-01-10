package net.wfoas.gh.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.util.ResourceLocation;
import net.wfoas.gh.GameHelperCoreModule;

public class EnchantmentXPBoost extends Enchantment {
	public EnchantmentXPBoost(int enchID, ResourceLocation enchName, int enchWeight) {
		super(enchID, enchName, enchWeight, EnumEnchantmentType.ALL);
		this.setName("xp_boost");
	}

	/**
	 * Returns the minimal value of enchantability needed on the enchantment
	 * level passed.
	 */
	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return 12;
	}

	/**
	 * Returns the maximum value of enchantability nedded on the enchantment
	 * level passed.
	 */
	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return 50;
	}

	/**
	 * Returns the maximum level that the enchantment can have.
	 */
	@Override
	public int getMaxLevel() {
		return 1;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		if ((stack.getItem() instanceof ItemSword) || GameHelperCoreModule.isSword(stack.getItem()))
			return true;
		if (stack.getItem() instanceof ItemTool || GameHelperCoreModule.isPickaxe(stack.getItem())
				|| GameHelperCoreModule.isAxe(stack.getItem()) || GameHelperCoreModule.isShovel(stack.getItem()))
			return true;
		else
			return false;
	}
}