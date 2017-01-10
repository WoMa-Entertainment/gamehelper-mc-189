package net.wfoas.gh.dropsapi.pdr;

import net.minecraft.potion.PotionEffect;

public class PotionContainer {
	PotionEffect poe;
	public PotionContainer(PotionEffect poe){
		this.poe = poe;
		System.out.println("[POTIONC]" + this.poe.getAmplifier() + this.poe.getDuration() + "wow");
	}
	
	public PotionEffect getPEffect(){
		return poe;
	}
}
