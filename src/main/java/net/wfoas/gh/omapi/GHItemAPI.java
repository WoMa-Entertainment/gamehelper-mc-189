package net.wfoas.gh.omapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.wfoas.gh.GameHelperCoreModule;

public class GHItemAPI {

	protected GHItemAPI() {
		helmetList = new ArrayList<Item>();
		chestplateList = new ArrayList<Item>();
		leggingsList = new ArrayList<Item>();
		bootsList = new ArrayList<Item>();
		shovelList = new ArrayList<Item>();
		swordList = new ArrayList<Item>();
		pickaxeList = new ArrayList<Item>();
		axeList = new ArrayList<Item>();
		hoeList = new ArrayList<Item>();
		fnsList = new ArrayList<Item>();
		rodList = new ArrayList<Item>();
		bowList = new ArrayList<Item>();
		shearsList = new ArrayList<Item>();
		ghit = new HashMap<Item, GHItemType>();
	}

	// Armor
	public List<Item> helmetList;
	public List<Item> chestplateList;
	public List<Item> leggingsList;
	public List<Item> bootsList;
	// Tools & Weapons
	public List<Item> shovelList;
	public List<Item> swordList;
	public List<Item> pickaxeList;
	public List<Item> axeList;
	public List<Item> hoeList;
	public List<Item> fnsList;
	public List<Item> rodList;
	public List<Item> bowList;
	public List<Item> shearsList;
	Map<Item, GHItemType> ghit;

	public void defaultSetupItemLists() {
		ghit.clear();
		helmetList.clear();
		chestplateList.clear();
		leggingsList.clear();
		bootsList.clear();
		shovelList.clear();
		swordList.clear();
		pickaxeList.clear();
		axeList.clear();
		hoeList.clear();
		fnsList.clear();
		rodList.clear();
		bowList.clear();
		shearsList.clear();
		// helmet
		addItem(Items.leather_helmet, GHItemType.ARMOR_HELMET);
		addItem(Items.chainmail_helmet, GHItemType.ARMOR_HELMET);
		addItem(Items.diamond_helmet, GHItemType.ARMOR_HELMET);
		addItem(Items.golden_helmet, GHItemType.ARMOR_HELMET);
		addItem(Items.iron_helmet, GHItemType.ARMOR_HELMET);
		addItem(GameHelperCoreModule.EMERALD_HELMET, GHItemType.ARMOR_HELMET);
		addItem(GameHelperCoreModule.RUBY_HELMET, GHItemType.ARMOR_HELMET);
		addItem(GameHelperCoreModule.SAPPHIRE_HELMET, GHItemType.ARMOR_HELMET);
		addItem(GameHelperCoreModule.AMETHYST_HELMET, GHItemType.ARMOR_HELMET);
		// chestplates
		addItem(Items.leather_chestplate, GHItemType.ARMOR_CHESTPLATE);
		addItem(Items.chainmail_chestplate, GHItemType.ARMOR_CHESTPLATE);
		addItem(Items.diamond_chestplate, GHItemType.ARMOR_CHESTPLATE);
		addItem(Items.golden_chestplate, GHItemType.ARMOR_CHESTPLATE);
		addItem(Items.iron_chestplate, GHItemType.ARMOR_CHESTPLATE);
		addItem(GameHelperCoreModule.EMERALD_CHESTPLATE, GHItemType.ARMOR_CHESTPLATE);
		addItem(GameHelperCoreModule.RUBY_CHESTPLATE, GHItemType.ARMOR_CHESTPLATE);
		addItem(GameHelperCoreModule.SAPPHIRE_CHESTPLATE, GHItemType.ARMOR_CHESTPLATE);
		addItem(GameHelperCoreModule.AMETHYST_CHESTPLATE, GHItemType.ARMOR_CHESTPLATE);
		// leggings
		addItem(Items.leather_leggings, GHItemType.ARMOR_LEGGINGS);
		addItem(Items.chainmail_leggings, GHItemType.ARMOR_LEGGINGS);
		addItem(Items.diamond_leggings, GHItemType.ARMOR_LEGGINGS);
		addItem(Items.golden_leggings, GHItemType.ARMOR_LEGGINGS);
		addItem(Items.iron_leggings, GHItemType.ARMOR_LEGGINGS);
		addItem(GameHelperCoreModule.EMERALD_LEGGINGS, GHItemType.ARMOR_LEGGINGS);
		addItem(GameHelperCoreModule.RUBY_LEGGINGS, GHItemType.ARMOR_LEGGINGS);
		addItem(GameHelperCoreModule.SAPPHIRE_LEGGINGS, GHItemType.ARMOR_LEGGINGS);
		addItem(GameHelperCoreModule.AMETHYST_LEGGINGS, GHItemType.ARMOR_LEGGINGS);
		// boots
		addItem(Items.leather_boots, GHItemType.ARMOR_BOOTS);
		addItem(Items.chainmail_boots, GHItemType.ARMOR_BOOTS);
		addItem(Items.diamond_boots, GHItemType.ARMOR_BOOTS);
		addItem(Items.golden_boots, GHItemType.ARMOR_BOOTS);
		addItem(Items.iron_boots, GHItemType.ARMOR_BOOTS);
		addItem(GameHelperCoreModule.EMERALD_BOOTS, GHItemType.ARMOR_BOOTS);
		addItem(GameHelperCoreModule.RUBY_BOOTS, GHItemType.ARMOR_BOOTS);
		addItem(GameHelperCoreModule.SAPPHIRE_BOOTS, GHItemType.ARMOR_BOOTS);
		addItem(GameHelperCoreModule.AMETHYST_BOOTS, GHItemType.ARMOR_BOOTS);
		// sword
		addItem(Items.wooden_sword, GHItemType.WEAPON_SWORD);
		addItem(Items.stone_sword, GHItemType.WEAPON_SWORD);
		addItem(Items.diamond_sword, GHItemType.WEAPON_SWORD);
		addItem(Items.golden_sword, GHItemType.WEAPON_SWORD);
		addItem(Items.iron_sword, GHItemType.WEAPON_SWORD);
		addItem(GameHelperCoreModule.EMERALD_SWORD, GHItemType.WEAPON_SWORD);
		addItem(GameHelperCoreModule.RUBY_SWORD, GHItemType.WEAPON_SWORD);
		addItem(GameHelperCoreModule.SAPPHIRE_SWORD, GHItemType.WEAPON_SWORD);
		addItem(GameHelperCoreModule.AMETHYST_SWORD, GHItemType.WEAPON_SWORD);
		// pickaxe
		addItem(Items.wooden_pickaxe, GHItemType.TOOL_PICKAXE);
		addItem(Items.stone_pickaxe, GHItemType.TOOL_PICKAXE);
		addItem(Items.diamond_pickaxe, GHItemType.TOOL_PICKAXE);
		addItem(Items.golden_pickaxe, GHItemType.TOOL_PICKAXE);
		addItem(Items.iron_pickaxe, GHItemType.TOOL_PICKAXE);
		addItem(GameHelperCoreModule.EMERALD_PICKAXE, GHItemType.TOOL_PICKAXE);
		addItem(GameHelperCoreModule.RUBY_PICKAXE, GHItemType.TOOL_PICKAXE);
		addItem(GameHelperCoreModule.SAPPHIRE_PICKAXE, GHItemType.TOOL_PICKAXE);
		addItem(GameHelperCoreModule.AMETHYST_PICKAXE, GHItemType.TOOL_PICKAXE);
		// axe
		addItem(Items.wooden_axe, GHItemType.TOOL_AXE);
		addItem(Items.stone_axe, GHItemType.TOOL_AXE);
		addItem(Items.diamond_axe, GHItemType.TOOL_AXE);
		addItem(Items.golden_axe, GHItemType.TOOL_AXE);
		addItem(Items.iron_axe, GHItemType.TOOL_AXE);
		addItem(GameHelperCoreModule.EMERALD_AXE, GHItemType.TOOL_AXE);
		addItem(GameHelperCoreModule.RUBY_AXE, GHItemType.TOOL_AXE);
		addItem(GameHelperCoreModule.SAPPHIRE_AXE, GHItemType.TOOL_AXE);
		addItem(GameHelperCoreModule.AMETHYST_AXE, GHItemType.TOOL_AXE);
		// hoe
		addItem(Items.wooden_hoe, GHItemType.TOOL_HOE);
		addItem(Items.stone_hoe, GHItemType.TOOL_HOE);
		addItem(Items.diamond_hoe, GHItemType.TOOL_HOE);
		addItem(Items.golden_hoe, GHItemType.TOOL_HOE);
		addItem(Items.iron_hoe, GHItemType.TOOL_HOE);
		addItem(GameHelperCoreModule.EMERALD_AXE, GHItemType.TOOL_HOE);
		addItem(GameHelperCoreModule.RUBY_AXE, GHItemType.TOOL_HOE);
		addItem(GameHelperCoreModule.SAPPHIRE_AXE, GHItemType.TOOL_HOE);
		addItem(GameHelperCoreModule.AMETHYST_AXE, GHItemType.TOOL_HOE);
		// shovel
		addItem(Items.wooden_shovel, GHItemType.TOOL_SHOVEL);
		addItem(Items.stone_shovel, GHItemType.TOOL_SHOVEL);
		addItem(Items.diamond_shovel, GHItemType.TOOL_SHOVEL);
		addItem(Items.golden_shovel, GHItemType.TOOL_SHOVEL);
		addItem(Items.iron_shovel, GHItemType.TOOL_SHOVEL);
		addItem(GameHelperCoreModule.EMERALD_SHOVEL, GHItemType.TOOL_SHOVEL);
		addItem(GameHelperCoreModule.RUBY_SHOVEL, GHItemType.TOOL_SHOVEL);
		addItem(GameHelperCoreModule.SAPPHIRE_SHOVEL, GHItemType.TOOL_SHOVEL);
		addItem(GameHelperCoreModule.AMETHYST_SHOVEL, GHItemType.TOOL_SHOVEL);
		// fns
		addItem(Items.flint_and_steel, GHItemType.TOOL_FLINTANDSTEEL);
		// rod
		addItem(Items.fishing_rod, GHItemType.TOOL_FISHING_ROD);
		// bow
		addItem(Items.bow, GHItemType.WEAPON_BOW);
		// shears
		addItem(Items.shears, GHItemType.TOOL_SHEARS);
	}

	public void addItem(Item item, GHItemType type) {
		ghit.put(item, type);
		switch (type) {
		case ARMOR_BOOTS:
			bootsList.add(item);
			break;
		case ARMOR_CHESTPLATE:
			chestplateList.add(item);
			break;
		case ARMOR_HELMET:
			helmetList.add(item);
			break;
		case ARMOR_LEGGINGS:
			leggingsList.add(item);
			break;
		case TOOL_AXE:
			axeList.add(item);
			break;
		case TOOL_FISHING_ROD:
			rodList.add(item);
			break;
		case TOOL_FLINTANDSTEEL:
			fnsList.add(item);
			break;
		case TOOL_HOE:
			hoeList.add(item);
			break;
		case TOOL_PICKAXE:
			pickaxeList.add(item);
			break;
		case TOOL_SHEARS:
			shearsList.add(item);
			break;
		case TOOL_SHOVEL:
			shovelList.add(item);
			break;
		case WEAPON_BOW:
			bowList.add(item);
			break;
		case WEAPON_SWORD:
			swordList.add(item);
			break;
		default:
			break;
		}
	}

	GHItemType getType(Item im) {
		return ghit.get(im);
	}

	public boolean isArmorChestLeggings(Item m) {
		return chestplateList.contains(m) || leggingsList.contains(m);
	}

	public boolean isArmorChestplate(Item m) {
		return chestplateList.contains(m);
	}

	public boolean isArmorLeggings(Item m) {
		return leggingsList.contains(m);
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

	public boolean isFlintAndSteel(Item m) {
		return fnsList.contains(m);
	}

	public boolean isShear(Item m) {
		return shearsList.contains(m);
	}

	public boolean isSword(Item m) {
		return swordList.contains(m);
	}

}