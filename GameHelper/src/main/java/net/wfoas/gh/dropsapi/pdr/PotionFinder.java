package net.wfoas.gh.dropsapi.pdr;

import net.minecraft.potion.Potion;

public enum PotionFinder {
	SPEED(Potion.moveSpeed), SLOW(Potion.moveSlowdown), FAST_DIGGING(Potion.digSpeed), 
	SLOW_DIGGING(Potion.digSlowdown), INCREASE_DAMAGE(Potion.damageBoost), 
	HEAL(Potion.heal), HARM(Potion.harm), JUMP(Potion.jump), CONFUSION(Potion.confusion),
	REGENERATION(Potion.regeneration), DAMAGE_RESISTANCE(Potion.resistance), 
	FIRE_RESISTANCE(Potion.fireResistance), WATER_BREATHING(Potion.waterBreathing),
	INVISIBILITY(Potion.invisibility), BLINDNESS(Potion.blindness), NIGHT_VISION(Potion.nightVision),
	HUNGER(Potion.hunger), WEAKNESS(Potion.weakness), POISON(Potion.poison),
	WITHER(Potion.wither), HEALTH_BOOST(Potion.healthBoost), ABSORPTION(Potion.absorption),
	SATURATION(Potion.saturation);
	
	final Potion pet;
	PotionFinder(Potion pet){
		this.pet = pet;
	}
	
	public Potion toFMLEffect(){
		return pet;
	}
}
