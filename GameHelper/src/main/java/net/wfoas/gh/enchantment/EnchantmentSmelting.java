package net.wfoas.gh.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.ResourceLocation;
import net.wfoas.gh.GameHelperCoreModule;

public class EnchantmentSmelting extends Enchantment {
	public EnchantmentSmelting(int enchID, ResourceLocation enchName, int enchWeight) {
		super(enchID, enchName, enchWeight, EnumEnchantmentType.DIGGER);
		this.setName("smelting");
		this.type = EnumEnchantmentType.DIGGER;
	}

	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return 10;
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
		if (ench.effectId == GameHelperCoreModule.ENCH_DESTRUCTION.effectId)
			return false;
		if (ench.effectId == silkTouch.effectId)
			return false;
		return true;
	}
}