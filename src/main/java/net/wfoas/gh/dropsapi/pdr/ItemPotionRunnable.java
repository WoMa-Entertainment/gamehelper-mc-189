package net.wfoas.gh.dropsapi.pdr;

import net.minecraft.item.ItemStack;

public abstract class ItemPotionRunnable implements ItemModifyRunnable{
	String name;
	PotionContainer enchc;
	public ItemPotionRunnable(String name, PotionContainer enchc) {
		this.name = name;
		this.enchc = enchc;
	}
	
	public String getName(){
		return name;
	}
	
	public PotionContainer getPotionC(){
		return enchc;
	}
	
	@Override
	public abstract void run(ItemStack is);
}
