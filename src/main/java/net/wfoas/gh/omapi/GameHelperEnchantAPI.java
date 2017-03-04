package net.wfoas.gh.omapi;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.GameHelperCoreModule;
import net.wfoas.gh.dropsapi.pdr.EnchantmentFinder;

public class GameHelperEnchantAPI {
	protected GameHelperEnchantAPI() {
		chestplateEnchantsList = new ArrayList<Enchantment>();
		leggingsEnchantsList = new ArrayList<Enchantment>();
		helmetEnchantsList = new ArrayList<Enchantment>();
		bootsEnchantsList = new ArrayList<Enchantment>();
		swordEnchantsList = new ArrayList<Enchantment>();
		otherMaterialEnchantsList = new ArrayList<Enchantment>();
		shovelEnchantsList = new ArrayList<Enchantment>();
		fnsEnchantsList = new ArrayList<Enchantment>();
		axeEnchantsList = new ArrayList<Enchantment>();
		bowEnchantsList = new ArrayList<Enchantment>();
		pickaxeSilkList = new ArrayList<Enchantment>();
		pickaxeFortuneSmeltingList = new ArrayList<Enchantment>();
		pickaxeElseList = new ArrayList<Enchantment>();
		rodEnchantsList = new ArrayList<Enchantment>();
		hoeEnchantsList = new ArrayList<Enchantment>();
		shearsEnchantsList = new ArrayList<Enchantment>();
	}

	protected static final Enchantment[] ENCH_ARRAY = new Enchantment[0];

	protected List<Enchantment> chestplateEnchantsList, leggingsEnchantsList, helmetEnchantsList, bootsEnchantsList,
			swordEnchantsList, otherMaterialEnchantsList, shovelEnchantsList, fnsEnchantsList, axeEnchantsList,
			bowEnchantsList, pickaxeSilkList, pickaxeFortuneSmeltingList, pickaxeElseList, rodEnchantsList,
			hoeEnchantsList, shearsEnchantsList;

	protected Enchantment[] chestplateEnchants, leggingsEnchants, helmetEnchants, bootsEnchants, swordEnchants,
			otherMaterialEnchants, shovelEnchants, fnsEnchants, axeEnchants, bowEnchants, pickaxeSilk,
			pickaxeFortuneSmelting, pickaxeElse, rodEnchants, hoeEnchants, shearsEnchants;

	public void defaultSetup() {
		chestplateEnchantsList.clear();
		leggingsEnchantsList.clear();
		helmetEnchantsList.clear();
		bootsEnchantsList.clear();
		swordEnchantsList.clear();
		otherMaterialEnchantsList.clear();
		shovelEnchantsList.clear();
		fnsEnchantsList.clear();
		axeEnchantsList.clear();
		bowEnchantsList.clear();
		pickaxeSilkList.clear();
		pickaxeFortuneSmeltingList.clear();
		pickaxeElseList.clear();
		rodEnchantsList.clear();
		hoeEnchantsList.clear();
		shearsEnchantsList.clear();
		//
		chestplateEnchantsList.add(EnchantmentFinder.PROTECTION.toFMLEnchantment());
		chestplateEnchantsList.add(EnchantmentFinder.PROJECTILE_PROTECTION.toFMLEnchantment());
		chestplateEnchantsList.add(EnchantmentFinder.FIRE_PROTECTION.toFMLEnchantment());
		chestplateEnchantsList.add(EnchantmentFinder.BLAST_PROTECTION.toFMLEnchantment());
		chestplateEnchantsList.add(EnchantmentFinder.THORNS.toFMLEnchantment());
		chestplateEnchantsList.add(EnchantmentFinder.UNBREAKING.toFMLEnchantment());
		chestplateEnchantsList.add(EnchantmentFinder.SOULBOUND.toFMLEnchantment());
		//
		helmetEnchantsList.add(EnchantmentFinder.PROTECTION.toFMLEnchantment());
		helmetEnchantsList.add(EnchantmentFinder.PROJECTILE_PROTECTION.toFMLEnchantment());
		helmetEnchantsList.add(EnchantmentFinder.FIRE_PROTECTION.toFMLEnchantment());
		helmetEnchantsList.add(EnchantmentFinder.BLAST_PROTECTION.toFMLEnchantment());
		helmetEnchantsList.add(EnchantmentFinder.THORNS.toFMLEnchantment());
		helmetEnchantsList.add(EnchantmentFinder.UNBREAKING.toFMLEnchantment());
		helmetEnchantsList.add(EnchantmentFinder.SOULBOUND.toFMLEnchantment());
		helmetEnchantsList.add(EnchantmentFinder.AQUA_AFFINITY.toFMLEnchantment());
		helmetEnchantsList.add(EnchantmentFinder.RESPIRATION.toFMLEnchantment());
		//
		leggingsEnchantsList.add(EnchantmentFinder.PROTECTION.toFMLEnchantment());
		leggingsEnchantsList.add(EnchantmentFinder.PROJECTILE_PROTECTION.toFMLEnchantment());
		leggingsEnchantsList.add(EnchantmentFinder.FIRE_PROTECTION.toFMLEnchantment());
		leggingsEnchantsList.add(EnchantmentFinder.BLAST_PROTECTION.toFMLEnchantment());
		leggingsEnchantsList.add(EnchantmentFinder.THORNS.toFMLEnchantment());
		leggingsEnchantsList.add(EnchantmentFinder.UNBREAKING.toFMLEnchantment());
		leggingsEnchantsList.add(EnchantmentFinder.SOULBOUND.toFMLEnchantment());
		//
		bootsEnchantsList.add(EnchantmentFinder.PROTECTION.toFMLEnchantment());
		bootsEnchantsList.add(EnchantmentFinder.PROJECTILE_PROTECTION.toFMLEnchantment());
		bootsEnchantsList.add(EnchantmentFinder.FIRE_PROTECTION.toFMLEnchantment());
		bootsEnchantsList.add(EnchantmentFinder.BLAST_PROTECTION.toFMLEnchantment());
		bootsEnchantsList.add(EnchantmentFinder.THORNS.toFMLEnchantment());
		bootsEnchantsList.add(EnchantmentFinder.UNBREAKING.toFMLEnchantment());
		bootsEnchantsList.add(EnchantmentFinder.SOULBOUND.toFMLEnchantment());
		bootsEnchantsList.add(EnchantmentFinder.FEATHER_FALLING.toFMLEnchantment());
		bootsEnchantsList.add(EnchantmentFinder.DEPTH_STRIDER.toFMLEnchantment());
		//
		pickaxeSilkList.add(EnchantmentFinder.EFFICIENCY.toFMLEnchantment());
		pickaxeSilkList.add(EnchantmentFinder.SILK_TOUCH.toFMLEnchantment());
		pickaxeSilkList.add(EnchantmentFinder.UNBREAKING.toFMLEnchantment());
		pickaxeSilkList.add(EnchantmentFinder.SOULBOUND.toFMLEnchantment());
		pickaxeSilkList.add(EnchantmentFinder.XPBOOST.toFMLEnchantment());
		//
		pickaxeFortuneSmeltingList.add(EnchantmentFinder.FORTUNE.toFMLEnchantment());
		pickaxeFortuneSmeltingList.add(EnchantmentFinder.EFFICIENCY.toFMLEnchantment());
		pickaxeFortuneSmeltingList.add(EnchantmentFinder.SMELTING.toFMLEnchantment());
		pickaxeFortuneSmeltingList.add(EnchantmentFinder.UNBREAKING.toFMLEnchantment());
		pickaxeFortuneSmeltingList.add(EnchantmentFinder.SOULBOUND.toFMLEnchantment());
		pickaxeFortuneSmeltingList.add(EnchantmentFinder.XPBOOST.toFMLEnchantment());
		pickaxeFortuneSmeltingList.add(EnchantmentFinder.DESTRUCTION.toFMLEnchantment());
		//
		pickaxeElseList.add(EnchantmentFinder.FORTUNE.toFMLEnchantment());
		pickaxeElseList.add(EnchantmentFinder.EFFICIENCY.toFMLEnchantment());
		pickaxeElseList.add(EnchantmentFinder.SILK_TOUCH.toFMLEnchantment());
		pickaxeElseList.add(EnchantmentFinder.SMELTING.toFMLEnchantment());
		pickaxeElseList.add(EnchantmentFinder.UNBREAKING.toFMLEnchantment());
		pickaxeElseList.add(EnchantmentFinder.SOULBOUND.toFMLEnchantment());
		pickaxeElseList.add(EnchantmentFinder.XPBOOST.toFMLEnchantment());
		pickaxeElseList.add(EnchantmentFinder.DESTRUCTION.toFMLEnchantment());
		//
		swordEnchantsList.add(EnchantmentFinder.SHARPNESS.toFMLEnchantment());
		swordEnchantsList.add(EnchantmentFinder.BANE_OF_ARTHROPODS.toFMLEnchantment());
		swordEnchantsList.add(EnchantmentFinder.KNOCKBACK.toFMLEnchantment());
		swordEnchantsList.add(EnchantmentFinder.LOOTING.toFMLEnchantment());
		swordEnchantsList.add(EnchantmentFinder.FIRE_ASPECT.toFMLEnchantment());
		swordEnchantsList.add(EnchantmentFinder.SMITE.toFMLEnchantment());
		swordEnchantsList.add(EnchantmentFinder.SOULBOUND.toFMLEnchantment());
		swordEnchantsList.add(EnchantmentFinder.XPBOOST.toFMLEnchantment());
		swordEnchantsList.add(EnchantmentFinder.HEAD_LOOT.toFMLEnchantment());
		swordEnchantsList.add(EnchantmentFinder.UNBREAKING.toFMLEnchantment());
		//
		bowEnchantsList.add(EnchantmentFinder.POWER.toFMLEnchantment());
		bowEnchantsList.add(EnchantmentFinder.FLAME.toFMLEnchantment());
		bowEnchantsList.add(EnchantmentFinder.PUNCH.toFMLEnchantment());
		bowEnchantsList.add(EnchantmentFinder.INFINITY.toFMLEnchantment());
		bowEnchantsList.add(EnchantmentFinder.UNBREAKING.toFMLEnchantment());
		bowEnchantsList.add(EnchantmentFinder.SOULBOUND.toFMLEnchantment());
		bowEnchantsList.add(EnchantmentFinder.XPBOOST.toFMLEnchantment());
		//
		rodEnchantsList.add(EnchantmentFinder.LURE.toFMLEnchantment());
		rodEnchantsList.add(EnchantmentFinder.LUCK_OF_THE_SEA.toFMLEnchantment());
		rodEnchantsList.add(EnchantmentFinder.UNBREAKING.toFMLEnchantment());
		rodEnchantsList.add(EnchantmentFinder.SOULBOUND.toFMLEnchantment());
		//
		hoeEnchantsList.add(EnchantmentFinder.UNBREAKING.toFMLEnchantment());
		hoeEnchantsList.add(EnchantmentFinder.SOULBOUND.toFMLEnchantment());
		//
		axeEnchantsList.add(EnchantmentFinder.EFFICIENCY.toFMLEnchantment());
		axeEnchantsList.add(EnchantmentFinder.UNBREAKING.toFMLEnchantment());
		axeEnchantsList.add(EnchantmentFinder.SMELTING.toFMLEnchantment());
		axeEnchantsList.add(EnchantmentFinder.SOULBOUND.toFMLEnchantment());
		axeEnchantsList.add(EnchantmentFinder.HEAD_LOOT.toFMLEnchantment());
		//
		shovelEnchantsList.add(EnchantmentFinder.EFFICIENCY.toFMLEnchantment());
		shovelEnchantsList.add(EnchantmentFinder.SMELTING.toFMLEnchantment());
		shovelEnchantsList.add(EnchantmentFinder.UNBREAKING.toFMLEnchantment());
		shovelEnchantsList.add(EnchantmentFinder.SOULBOUND.toFMLEnchantment());
		//
		fnsEnchantsList.add(EnchantmentFinder.UNBREAKING.toFMLEnchantment());
		fnsEnchantsList.add(EnchantmentFinder.SOULBOUND.toFMLEnchantment());
		//
		shearsEnchantsList.add(EnchantmentFinder.UNBREAKING.toFMLEnchantment());
		shearsEnchantsList.add(EnchantmentFinder.SOULBOUND.toFMLEnchantment());
		//
		otherMaterialEnchantsList.add(EnchantmentFinder.SOULBOUND.toFMLEnchantment());
	}

	public void rebuildEnchantmentLists() {
		chestplateEnchants = new Enchantment[0];
		chestplateEnchants = chestplateEnchantsList.toArray(chestplateEnchants);
		helmetEnchants = new Enchantment[0];
		helmetEnchants = helmetEnchantsList.toArray(helmetEnchants);
		leggingsEnchants = new Enchantment[0];
		leggingsEnchants = leggingsEnchantsList.toArray(leggingsEnchants);
		bootsEnchants = new Enchantment[0];
		bootsEnchants = bootsEnchantsList.toArray(bootsEnchants);
		pickaxeSilk = new Enchantment[0];
		pickaxeSilk = pickaxeSilkList.toArray(pickaxeSilk);
		pickaxeFortuneSmelting = new Enchantment[0];
		pickaxeFortuneSmelting = pickaxeFortuneSmeltingList.toArray(pickaxeFortuneSmelting);
		pickaxeElse = new Enchantment[0];
		pickaxeElse = pickaxeElseList.toArray(pickaxeElse);
		swordEnchants = new Enchantment[0];
		swordEnchants = swordEnchantsList.toArray(swordEnchants);
		bowEnchants = new Enchantment[0];
		bowEnchants = bowEnchantsList.toArray(bowEnchants);
		rodEnchants = new Enchantment[0];
		rodEnchants = rodEnchantsList.toArray(rodEnchants);
		hoeEnchants = new Enchantment[0];
		hoeEnchants = hoeEnchantsList.toArray(hoeEnchants);
		axeEnchants = new Enchantment[0];
		axeEnchants = axeEnchantsList.toArray(axeEnchants);
		shovelEnchants = new Enchantment[0];
		shovelEnchants = shovelEnchantsList.toArray(shovelEnchants);
		fnsEnchants = new Enchantment[0];
		fnsEnchants = fnsEnchantsList.toArray(fnsEnchants);
		shearsEnchants = new Enchantment[0];
		shearsEnchants = shearsEnchantsList.toArray(shearsEnchants);
		otherMaterialEnchants = new Enchantment[0];
		otherMaterialEnchants = otherMaterialEnchantsList.toArray(otherMaterialEnchants);
	}

	public Enchantment[] getItemBoundEnchantments(ItemStack is) {
		Item m = is.getItem();
		if (GameHelperAPI.ghAPI().ghItemAPI().isArmorChestLeggings(m)) {
			return chestplateEnchants;
		} else if (GameHelperAPI.ghAPI().ghItemAPI().isArmorBoots(m)) {
			return bootsEnchants;
		} else if (GameHelperAPI.ghAPI().ghItemAPI().isArmorHelmet(m)) {
			return helmetEnchants;
		} else if (GameHelperAPI.ghAPI().ghItemAPI().isAxe(m)) {
			return axeEnchants;
		} else if (GameHelperAPI.ghAPI().ghItemAPI().isBow(m)) {
			return bowEnchants;
		} else if (GameHelperAPI.ghAPI().ghItemAPI().isFishingRod(m)) {
			return rodEnchants;
		} else if (GameHelperAPI.ghAPI().ghItemAPI().isFlintAndSteel(m)) {
			return fnsEnchants;
		} else if (GameHelperAPI.ghAPI().ghItemAPI().isHoe(m)) {
			return hoeEnchants;
		} else if (GameHelperAPI.ghAPI().ghItemAPI().isPickaxe(m)) {
			if (GameHelper.getUtils().hasEnchantment(is, EnchantmentFinder.SILK_TOUCH.toFMLEnchantment())) {
				return pickaxeSilk;
			} else if (GameHelper.getUtils().hasEnchantment(is, EnchantmentFinder.FORTUNE.toFMLEnchantment())
					|| GameHelper.getUtils().hasEnchantment(is, EnchantmentFinder.SMELTING.toFMLEnchantment())) {
				return pickaxeFortuneSmelting;
			} else {
				return pickaxeElse;
			}
		} else if (GameHelperAPI.ghAPI().ghItemAPI().isShear(m)) {
			return shearsEnchants;
		} else if (GameHelperAPI.ghAPI().ghItemAPI().isShovel(m)) {
			return shovelEnchants;
		} else if (GameHelperAPI.ghAPI().ghItemAPI().isSword(m)) {
			return swordEnchants;
		} else {
			return otherMaterialEnchants;
		}
	}
}
