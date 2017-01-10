package net.wfoas.gh.dropsapi.pdr;

import net.minecraft.enchantment.Enchantment;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.GameHelperCoreModule;

public enum EnchantmentFinder {
	PROTECTION(Enchantment.protection), FIRE_PROTECTION(Enchantment.fireProtection), BLAST_PROTECTION(
			Enchantment.blastProtection), PROJECTILE_PROTECTION(Enchantment.projectileProtection), FEATHER_FALLING(
					Enchantment.featherFalling), RESPIRATION(Enchantment.respiration), THORNS(
							Enchantment.thorns), DEPTH_STRIDER(Enchantment.depthStrider), SHARPNESS(
									Enchantment.sharpness), AQUA_AFFINITY(Enchantment.aquaAffinity), SMITE(
											Enchantment.smite), BANE_OF_ARTHROPODS(
													Enchantment.baneOfArthropods), KNOCKBACK(
															Enchantment.knockback), FIRE_ASPECT(
																	Enchantment.fireAspect), LOOTING(
																			Enchantment.looting), EFFICIENCY(
																					Enchantment.efficiency), SILK_TOUCH(
																							Enchantment.silkTouch), UNBREAKING(
																									Enchantment.unbreaking), FORTUNE(
																											Enchantment.fortune), POWER(
																													Enchantment.power), PUNCH(
																															Enchantment.punch), FLAME(
																																	Enchantment.flame), INFINITY(
																																			Enchantment.infinity), LUCK_OF_THE_SEA(
																																					Enchantment.luckOfTheSea), LURE(
																																							Enchantment.lure), SOULBOUND(
																																									GameHelperCoreModule.ENCH_SOULBOUND), SMELTING(
																																											GameHelperCoreModule.ENCH_SMELTING), XPBOOST(
																																													GameHelperCoreModule.ENCH_XPBOOST), ZOOM(
																																															GameHelperCoreModule.ENCH_ZOOM), HEAD_LOOT(
																																																	GameHelperCoreModule.ENCH_HEADLOOT), DESTRUCTION(
																																																			GameHelperCoreModule.ENCH_DESTRUCTION);
	;
	Enchantment ench;

	// http://minecraft-de.gamepedia.com
	EnchantmentFinder(Enchantment ench) {
		this.ench = ench;
	}

	public static Enchantment findByName(String s) {
		for (EnchantmentFinder en2 : EnchantmentFinder.values()) {
			if (en2.name().equalsIgnoreCase(s)) {
				return en2.toFMLEnchantment();
			}
		}
		return null;
	}

	public Enchantment toFMLEnchantment() {
		return ench;
	}

}