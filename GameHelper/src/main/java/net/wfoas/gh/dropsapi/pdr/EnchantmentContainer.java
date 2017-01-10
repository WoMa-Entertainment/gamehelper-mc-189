package net.wfoas.gh.dropsapi.pdr;

import net.minecraft.enchantment.Enchantment;

public class EnchantmentContainer {
	int level;
	Enchantment ench;
	public EnchantmentContainer(Enchantment ench, int level){
		this.ench = ench;
		this.level = level;
		System.out.println("[ENCHC]" + level + "EnchLevel + " + ench.toString());
	}
	
	public Enchantment getEnchantment(){
		return ench;
	}
	
	public int getLevel(){
		return level;
	}
}
