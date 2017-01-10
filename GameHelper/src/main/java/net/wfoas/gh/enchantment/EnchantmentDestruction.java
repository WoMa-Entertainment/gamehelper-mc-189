package net.wfoas.gh.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.wfoas.gh.GameHelperCoreModule;

public class EnchantmentDestruction extends Enchantment {
	public EnchantmentDestruction(int enchID, ResourceLocation enchName, int enchWeight) {
		super(enchID, enchName, enchWeight, EnumEnchantmentType.DIGGER);
		this.setName("enchantment_destruction");
		this.type = EnumEnchantmentType.DIGGER;
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
	public boolean canApplyTogether(Enchantment ench) {
		if (!super.canApplyTogether(ench))
			return false;
		if (ench.effectId == silkTouch.effectId)
			return false;
		// if (ench.effectId == fortune.effectId)
		// return false;
		if (ench.effectId == GameHelperCoreModule.ENCH_SMELTING.effectId)
			return false;
		return true;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		return this.type.canEnchantItem(stack.getItem());
	}
}