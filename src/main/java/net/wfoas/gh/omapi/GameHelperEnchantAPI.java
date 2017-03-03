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

	}

	public List<Item> helmetList = new ArrayList<Item>();
	public List<Item> chestplateList = new ArrayList<Item>();
	public List<Item> leggingsList = new ArrayList<Item>();
	public List<Item> bootsList = new ArrayList<Item>();
	public Enchantment[] chestplateEnchants, leggingsEnchants, helmetEnchants, bootsEnchants, swordEnchants,
			otherMaterialEnchants, shovelEnchants, fnsEnchants, axeEnchants, bowEnchants, pickaxeSilk,
			pickaxeFortuneSmelting, pickaxeElse, rodEnchants, hoeEnchants, shearsEnchants;

	public List<Item> shovelList = new ArrayList<Item>();
	public List<Item> swordList = new ArrayList<Item>();
	public List<Item> pickaxeList = new ArrayList<Item>();
	public List<Item> axeList = new ArrayList<Item>();
	public List<Item> hoeList = new ArrayList<Item>();
	public List<Item> fnsList = new ArrayList<Item>();
	public List<Item> rodList = new ArrayList<Item>();
	public List<Item> bowList = new ArrayList<Item>();
	public List<Item> shearsList = new ArrayList<Item>();

	public void setupEnchLists() {
		// helmet
		helmetList.add(Items.leather_helmet);
		helmetList.add(Items.chainmail_helmet);
		helmetList.add(Items.diamond_helmet);
		helmetList.add(Items.golden_helmet);
		helmetList.add(Items.iron_helmet);
		helmetList.add(GameHelperCoreModule.EMERALD_HELMET);
		helmetList.add(GameHelperCoreModule.RUBY_HELMET);
		helmetList.add(GameHelperCoreModule.SAPPHIRE_HELMET);
		helmetList.add(GameHelperCoreModule.AMETHYST_HELMET);
		// chestplates
		chestplateList.add(Items.leather_chestplate);
		chestplateList.add(Items.chainmail_chestplate);
		chestplateList.add(Items.diamond_chestplate);
		chestplateList.add(Items.golden_chestplate);
		chestplateList.add(Items.iron_chestplate);
		chestplateList.add(GameHelperCoreModule.EMERALD_CHESTPLATE);
		chestplateList.add(GameHelperCoreModule.RUBY_CHESTPLATE);
		chestplateList.add(GameHelperCoreModule.SAPPHIRE_CHESTPLATE);
		chestplateList.add(GameHelperCoreModule.AMETHYST_CHESTPLATE);
		// leggings
		leggingsList.add(Items.leather_leggings);
		leggingsList.add(Items.chainmail_leggings);
		leggingsList.add(Items.diamond_leggings);
		leggingsList.add(Items.golden_leggings);
		leggingsList.add(Items.iron_leggings);
		leggingsList.add(GameHelperCoreModule.EMERALD_LEGGINGS);
		leggingsList.add(GameHelperCoreModule.RUBY_LEGGINGS);
		leggingsList.add(GameHelperCoreModule.SAPPHIRE_LEGGINGS);
		leggingsList.add(GameHelperCoreModule.AMETHYST_LEGGINGS);
		// boots
		bootsList.add(Items.leather_boots);
		bootsList.add(Items.chainmail_boots);
		bootsList.add(Items.diamond_boots);
		bootsList.add(Items.golden_boots);
		bootsList.add(Items.iron_boots);
		bootsList.add(GameHelperCoreModule.EMERALD_BOOTS);
		bootsList.add(GameHelperCoreModule.RUBY_BOOTS);
		bootsList.add(GameHelperCoreModule.SAPPHIRE_BOOTS);
		bootsList.add(GameHelperCoreModule.AMETHYST_BOOTS);
		// sword
		swordList.add(Items.wooden_sword);
		swordList.add(Items.stone_sword);
		swordList.add(Items.diamond_sword);
		swordList.add(Items.golden_sword);
		swordList.add(Items.iron_sword);
		swordList.add(GameHelperCoreModule.EMERALD_SWORD);
		swordList.add(GameHelperCoreModule.RUBY_SWORD);
		swordList.add(GameHelperCoreModule.SAPPHIRE_SWORD);
		swordList.add(GameHelperCoreModule.AMETHYST_SWORD);
		// pickaxe
		pickaxeList.add(Items.wooden_pickaxe);
		pickaxeList.add(Items.stone_pickaxe);
		pickaxeList.add(Items.diamond_pickaxe);
		pickaxeList.add(Items.golden_pickaxe);
		pickaxeList.add(Items.iron_pickaxe);
		pickaxeList.add(GameHelperCoreModule.EMERALD_PICKAXE);
		pickaxeList.add(GameHelperCoreModule.RUBY_PICKAXE);
		pickaxeList.add(GameHelperCoreModule.SAPPHIRE_PICKAXE);
		pickaxeList.add(GameHelperCoreModule.AMETHYST_PICKAXE);
		// pickaxeList.add(GameHelper.EMERALD_pickaxe);
		// pickaxeList.add(GameHelper.RUBY_pickaxe);
		// pickaxeList.add(GameHelper.SAPPHIRE_pickaxe);
		// axe
		axeList.add(Items.wooden_axe);
		axeList.add(Items.stone_axe);
		axeList.add(Items.diamond_axe);
		axeList.add(Items.golden_axe);
		axeList.add(Items.iron_axe);
		axeList.add(GameHelperCoreModule.EMERALD_AXE);
		axeList.add(GameHelperCoreModule.RUBY_AXE);
		axeList.add(GameHelperCoreModule.SAPPHIRE_AXE);
		axeList.add(GameHelperCoreModule.AMETHYST_AXE);
		// axeList.add(GameHelper.EMERALD_axe);
		// axeList.add(GameHelper.RUBY_axe);
		// axeList.add(GameHelper.SAPPHIRE_axe);
		// hoe
		hoeList.add(Items.wooden_hoe);
		hoeList.add(Items.stone_hoe);
		hoeList.add(Items.diamond_hoe);
		hoeList.add(Items.golden_hoe);
		hoeList.add(Items.iron_hoe);
		hoeList.add(GameHelperCoreModule.EMERALD_AXE);
		hoeList.add(GameHelperCoreModule.RUBY_AXE);
		hoeList.add(GameHelperCoreModule.SAPPHIRE_AXE);
		hoeList.add(GameHelperCoreModule.AMETHYST_AXE);
		// GameHelper.GH_MODULE.hoeList.add(GameHelper.EMERALD_hoe);
		// GameHelper.GH_MODULE.hoeList.add(GameHelper.RUBY_hoe);
		// GameHelper.GH_MODULE.hoeList.add(GameHelper.SAPPHIRE_hoe);
		// shovel
		shovelList.add(Items.wooden_shovel);
		shovelList.add(Items.stone_shovel);
		shovelList.add(Items.diamond_shovel);
		shovelList.add(Items.golden_shovel);
		shovelList.add(Items.iron_shovel);
		shovelList.add(GameHelperCoreModule.EMERALD_SHOVEL);
		shovelList.add(GameHelperCoreModule.RUBY_SHOVEL);
		shovelList.add(GameHelperCoreModule.SAPPHIRE_SHOVEL);
		shovelList.add(GameHelperCoreModule.AMETHYST_SHOVEL);
		// shovelList.add(GameHelper.EMERALD_shovel);
		// shovelList.add(GameHelper.RUBY_shovel);
		// shovelList.add(GameHelper.SAPPHIRE_shovel);
		// fns
		fnsList.add(Items.flint_and_steel);
		// rod
		rodList.add(Items.fishing_rod);
		// bow
		bowList.add(Items.bow);
		// shears
		shearsList.add(Items.shears);

		chestplateEnchants = new Enchantment[] { EnchantmentFinder.PROTECTION.toFMLEnchantment(),
				EnchantmentFinder.PROJECTILE_PROTECTION.toFMLEnchantment(),
				EnchantmentFinder.FIRE_PROTECTION.toFMLEnchantment(),
				EnchantmentFinder.BLAST_PROTECTION.toFMLEnchantment(), EnchantmentFinder.THORNS.toFMLEnchantment(),
				EnchantmentFinder.UNBREAKING.toFMLEnchantment(), EnchantmentFinder.SOULBOUND.toFMLEnchantment() };
		helmetEnchants = new Enchantment[] { EnchantmentFinder.PROTECTION.toFMLEnchantment(),
				EnchantmentFinder.PROJECTILE_PROTECTION.toFMLEnchantment(),
				EnchantmentFinder.FIRE_PROTECTION.toFMLEnchantment(),
				EnchantmentFinder.BLAST_PROTECTION.toFMLEnchantment(), EnchantmentFinder.THORNS.toFMLEnchantment(),
				EnchantmentFinder.UNBREAKING.toFMLEnchantment(), EnchantmentFinder.SOULBOUND.toFMLEnchantment(),
				EnchantmentFinder.AQUA_AFFINITY.toFMLEnchantment(), EnchantmentFinder.RESPIRATION.toFMLEnchantment() };
		leggingsEnchants = new Enchantment[] { EnchantmentFinder.PROTECTION.toFMLEnchantment(),
				EnchantmentFinder.PROJECTILE_PROTECTION.toFMLEnchantment(),
				EnchantmentFinder.FIRE_PROTECTION.toFMLEnchantment(),
				EnchantmentFinder.BLAST_PROTECTION.toFMLEnchantment(), EnchantmentFinder.THORNS.toFMLEnchantment(),
				EnchantmentFinder.UNBREAKING.toFMLEnchantment(), EnchantmentFinder.SOULBOUND.toFMLEnchantment() };
		bootsEnchants = new Enchantment[] { EnchantmentFinder.PROTECTION.toFMLEnchantment(),
				EnchantmentFinder.PROJECTILE_PROTECTION.toFMLEnchantment(),
				EnchantmentFinder.FIRE_PROTECTION.toFMLEnchantment(),
				EnchantmentFinder.BLAST_PROTECTION.toFMLEnchantment(), EnchantmentFinder.THORNS.toFMLEnchantment(),
				EnchantmentFinder.UNBREAKING.toFMLEnchantment(), EnchantmentFinder.SOULBOUND.toFMLEnchantment(),
				EnchantmentFinder.FEATHER_FALLING.toFMLEnchantment(),
				EnchantmentFinder.DEPTH_STRIDER.toFMLEnchantment() };
		pickaxeSilk = new Enchantment[] { EnchantmentFinder.EFFICIENCY.toFMLEnchantment(),
				EnchantmentFinder.SILK_TOUCH.toFMLEnchantment(), EnchantmentFinder.UNBREAKING.toFMLEnchantment(),
				EnchantmentFinder.SOULBOUND.toFMLEnchantment(), EnchantmentFinder.XPBOOST.toFMLEnchantment() };
		pickaxeFortuneSmelting = new Enchantment[] { EnchantmentFinder.FORTUNE.toFMLEnchantment(),
				EnchantmentFinder.EFFICIENCY.toFMLEnchantment(), EnchantmentFinder.SMELTING.toFMLEnchantment(),
				EnchantmentFinder.UNBREAKING.toFMLEnchantment(), EnchantmentFinder.SOULBOUND.toFMLEnchantment(),
				EnchantmentFinder.XPBOOST.toFMLEnchantment(), EnchantmentFinder.DESTRUCTION.toFMLEnchantment() };
		pickaxeElse = new Enchantment[] { EnchantmentFinder.FORTUNE.toFMLEnchantment(),
				EnchantmentFinder.EFFICIENCY.toFMLEnchantment(), EnchantmentFinder.SILK_TOUCH.toFMLEnchantment(),
				EnchantmentFinder.SMELTING.toFMLEnchantment(), EnchantmentFinder.UNBREAKING.toFMLEnchantment(),
				EnchantmentFinder.SOULBOUND.toFMLEnchantment(), EnchantmentFinder.XPBOOST.toFMLEnchantment(),
				EnchantmentFinder.DESTRUCTION.toFMLEnchantment() };
		swordEnchants = new Enchantment[] { EnchantmentFinder.SHARPNESS.toFMLEnchantment(),
				EnchantmentFinder.BANE_OF_ARTHROPODS.toFMLEnchantment(), EnchantmentFinder.KNOCKBACK.toFMLEnchantment(),
				EnchantmentFinder.LOOTING.toFMLEnchantment(), EnchantmentFinder.FIRE_ASPECT.toFMLEnchantment(),
				EnchantmentFinder.SMITE.toFMLEnchantment(), EnchantmentFinder.SOULBOUND.toFMLEnchantment(),
				EnchantmentFinder.XPBOOST.toFMLEnchantment(), EnchantmentFinder.HEAD_LOOT.toFMLEnchantment(),
				EnchantmentFinder.UNBREAKING.toFMLEnchantment() };
		bowEnchants = new Enchantment[] { EnchantmentFinder.POWER.toFMLEnchantment(),
				EnchantmentFinder.FLAME.toFMLEnchantment(), EnchantmentFinder.PUNCH.toFMLEnchantment(),
				EnchantmentFinder.INFINITY.toFMLEnchantment(), EnchantmentFinder.UNBREAKING.toFMLEnchantment(),
				EnchantmentFinder.SOULBOUND.toFMLEnchantment(), EnchantmentFinder.XPBOOST.toFMLEnchantment() };
		rodEnchants = new Enchantment[] { EnchantmentFinder.LURE.toFMLEnchantment(),
				EnchantmentFinder.LUCK_OF_THE_SEA.toFMLEnchantment(), EnchantmentFinder.UNBREAKING.toFMLEnchantment(),
				EnchantmentFinder.SOULBOUND.toFMLEnchantment() };
		hoeEnchants = new Enchantment[] { EnchantmentFinder.UNBREAKING.toFMLEnchantment(),
				EnchantmentFinder.SOULBOUND.toFMLEnchantment() };
		axeEnchants = new Enchantment[] { EnchantmentFinder.EFFICIENCY.toFMLEnchantment(),
				EnchantmentFinder.UNBREAKING.toFMLEnchantment(), EnchantmentFinder.SMELTING.toFMLEnchantment(),
				EnchantmentFinder.SOULBOUND.toFMLEnchantment(), EnchantmentFinder.HEAD_LOOT.toFMLEnchantment() };
		shovelEnchants = new Enchantment[] { EnchantmentFinder.EFFICIENCY.toFMLEnchantment(),
				EnchantmentFinder.SMELTING.toFMLEnchantment(), EnchantmentFinder.UNBREAKING.toFMLEnchantment(),
				EnchantmentFinder.SOULBOUND.toFMLEnchantment() };
		fnsEnchants = new Enchantment[] { EnchantmentFinder.UNBREAKING.toFMLEnchantment(),
				EnchantmentFinder.SOULBOUND.toFMLEnchantment() };
		shearsEnchants = new Enchantment[] { EnchantmentFinder.UNBREAKING.toFMLEnchantment(),
				EnchantmentFinder.SOULBOUND.toFMLEnchantment() };
		otherMaterialEnchants = new Enchantment[] { EnchantmentFinder.SOULBOUND.toFMLEnchantment() };
	}

	public boolean isArmorChestLeggings(Item m) {
		return chestplateList.contains(m) || leggingsList.contains(m);
	}

	public boolean isArmorBoots(Item m) {
		return bootsList.contains(m);
	}

	public boolean isArmorHelmet(Item m) {
		return helmetList.contains(m);
	}

	public boolean isShovel(Item m) {
		return shovelList.contains(m);
	}

	public boolean isPickaxe(Item m) {
		return pickaxeList.contains(m);
	}

	public boolean isAxe(Item m) {
		return axeList.contains(m);
	}

	public boolean isHoe(Item m) {
		return hoeList.contains(m);
	}

	public boolean isBow(Item m) {
		return bowList.contains(m);
	}

	public boolean isFishingRod(Item m) {
		return rodList.contains(m);
	}

	public boolean isFNS(Item m) {
		return fnsList.contains(m);
	}

	public boolean isShear(Item m) {
		return shearsList.contains(m);
	}

	public boolean isSword(Item m) {
		return swordList.contains(m);
	}

	public Enchantment[] getItemBoundEnchantments(ItemStack is) {
		Item m = is.getItem();
		if (isArmorChestLeggings(m)) {
			return chestplateEnchants;
		} else if (isArmorBoots(m)) {
			return bootsEnchants;
		} else if (isArmorHelmet(m)) {
			return helmetEnchants;
		} else if (isAxe(m)) {
			return axeEnchants;
		} else if (isBow(m)) {
			return bowEnchants;
		} else if (isFishingRod(m)) {
			return rodEnchants;
		} else if (isFNS(m)) {
			return fnsEnchants;
		} else if (isHoe(m)) {
			return hoeEnchants;
		} else if (isPickaxe(m)) {
			if (GameHelper.getUtils().hasEnchantment(is, EnchantmentFinder.SILK_TOUCH.toFMLEnchantment())) {
				return pickaxeSilk;
			} else if (GameHelper.getUtils().hasEnchantment(is, EnchantmentFinder.FORTUNE.toFMLEnchantment())
					|| GameHelper.getUtils().hasEnchantment(is, EnchantmentFinder.SMELTING.toFMLEnchantment())) {
				return pickaxeFortuneSmelting;
			} else {
				return pickaxeElse;
			}
		} else if (isShear(m)) {
			return shearsEnchants;
		} else if (isShovel(m)) {
			return shovelEnchants;
		} else if (isSword(m)) {
			return swordEnchants;
		} else {
			return otherMaterialEnchants;
		}
	}
}
