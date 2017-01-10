package net.wfoas.gh.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.ResourceLocation;

public class EnchantmentHeadloot extends Enchantment
{
    private static final String __OBFID = "CL_00000100";

    public EnchantmentHeadloot(int enchID, ResourceLocation enchName, int enchWeight)
    {
        super(enchID, enchName, enchWeight, EnumEnchantmentType.WEAPON);
        this.setName("headloot");
        this.type = EnumEnchantmentType.WEAPON;
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    @Override
	public int getMinEnchantability(int enchantmentLevel)
    {
        return 12;
    }

    /**
     * Returns the maximum value of enchantability nedded on the enchantment level passed.
     */
    @Override
	public int getMaxEnchantability(int enchantmentLevel)
    {
        return 50;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    @Override
	public int getMaxLevel()
    {
        return 1;
    }
}