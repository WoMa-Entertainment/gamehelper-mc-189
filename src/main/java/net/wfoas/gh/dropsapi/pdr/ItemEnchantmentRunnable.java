package net.wfoas.gh.dropsapi.pdr;

import net.minecraft.item.ItemStack;

public abstract class ItemEnchantmentRunnable implements ItemModifyRunnable{
//	String name;
	EnchantmentContainer enchc;
	public ItemEnchantmentRunnable(EnchantmentContainer enchc) {
//		this.name = name;
		this.enchc = enchc;
	}
	
//	public String getName(){
//		return name;
//	}
	
	public EnchantmentContainer getEnchC(){
		return enchc;
	}
	
	@Override
	public abstract void run(ItemStack is);
}
