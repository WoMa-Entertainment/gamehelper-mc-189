package net.wfoas.gh;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.wfoas.gh.armor.GHModItemArmor;
import net.wfoas.gh.bigsword.BigswordItem;
import net.wfoas.gh.blocks.GameHelperModBlock;
import net.wfoas.gh.blocks.IGHModBlock;
import net.wfoas.gh.creativetab.GameHelperTab;
import net.wfoas.gh.dropsapi.pdr.EnchantmentFinder;
import net.wfoas.gh.enchaltar.EnchantmentAltar;
import net.wfoas.gh.flowers.Flowers;
import net.wfoas.gh.instench.InstantEnchantmentTable;
import net.wfoas.gh.items.GameHelperModItem;
import net.wfoas.gh.items.GameHelperModSword;
import net.wfoas.gh.items.ItemTelescope;
import net.wfoas.gh.items.MobileWorkbenchItem;
import net.wfoas.gh.items.PotionBow;
import net.wfoas.gh.items.TradeItems;
import net.wfoas.gh.items.tools.ItemGHAxeTool;
import net.wfoas.gh.items.tools.ItemGHHoeTool;
import net.wfoas.gh.items.tools.ItemGHPickaxeTool;
import net.wfoas.gh.items.tools.ItemGHShovelTool;
import net.wfoas.gh.minersinventory.items.IronDagger;
import net.wfoas.gh.op_anvil.OPAnvil;
import net.wfoas.gh.unchant.UnchantmentTable;
import net.wfoas.gh.worlddimensionsutils.DimensionBlock;

public class GameHelperCoreModule {
	public static GameHelperModBlock Purebrown;
	public static GameHelperModBlock Pureblue;
	public static EnchantmentAltar ENCH_ALTAR;
	public static InstantEnchantmentTable INST_ENCH;
	public static UnchantmentTable UNCH_TABL;

	public static Enchantment ENCH_SOULBOUND;
	public static Enchantment ENCH_SMELTING;
	public static Enchantment ENCH_XPBOOST;
	public static Enchantment ENCH_HEADLOOT;
	public static Enchantment ENCH_ZOOM;
	public static Enchantment ENCH_DESTRUCTION;
	public static Enchantment ENCH_LUMBER;

	public static ArmorMaterial EMERALD;
	public static ArmorMaterial SAPPHIRE;
	public static ArmorMaterial RUBY;
	public static ArmorMaterial RAINBOW;
	public static ArmorMaterial SPIRAL;
	public static ArmorMaterial AMETHYST;

	public static GameHelperModItem BACKPACK, ENDER_BACKPACK, WORLD_TELEPORTER, BIG_BACKPACK, ULTRA_BACKPACK;
	public static OPAnvil OP_ANVIL;
	public static BigswordItem GOLD_BS, DIAMOND_BS, STONE_BS, WOOD_BS, IRON_BS, EMERALD_BS, SAPPHIRE_BS, RUBY_BS;
	public static GameHelperModSword AMETHYST_SWORD, EMERALD_SWORD, SAPPHIRE_SWORD, RUBY_SWORD;
	public static ItemGHPickaxeTool EMERALD_PICKAXE, SAPPHIRE_PICKAXE, RUBY_PICKAXE, AMETHYST_PICKAXE;
	public static ItemGHAxeTool EMERALD_AXE, SAPPHIRE_AXE, RUBY_AXE, AMETHYST_AXE;
	public static ItemGHShovelTool EMERALD_SHOVEL, SAPPHIRE_SHOVEL, RUBY_SHOVEL, AMETHYST_SHOVEL;
	public static ItemGHHoeTool EMERALD_HOE, SAPPHIRE_HOE, RUBY_HOE, AMETHYST_HOE;
	public static GHModItemArmor EMERALD_HELMET, EMERALD_CHESTPLATE, EMERALD_LEGGINGS, EMERALD_BOOTS;
	public static GHModItemArmor RUBY_HELMET, RUBY_CHESTPLATE, RUBY_LEGGINGS, RUBY_BOOTS;
	public static GHModItemArmor SAPPHIRE_HELMET, SAPPHIRE_CHESTPLATE, SAPPHIRE_LEGGINGS, SAPPHIRE_BOOTS;
	public static GHModItemArmor AMETHYST_HELMET, AMETHYST_CHESTPLATE, AMETHYST_LEGGINGS, AMETHYST_BOOTS;

	public static PotionBow POTION_BOW;
	public static GameHelperModItem SEL_1, SEL_2;
	public static MobileWorkbenchItem MOBILE_WORKDBENCH;
	public static IronDagger IRON_DAGGER;
	public static GameHelperModItem OBSIDIAN_SHARD, OBSIDIAN_STICKS;
	public static DimensionBlock DIMENSION_BLOCK;
	public static GameHelperModItem MINERS_LAMP, MINERS_BACKPACK, MINERS_TOOLS_BELT, MINERS_DAGGER;
	public static ToolMaterial BIG_GOLD, BIG_DIAMOND, BIG_STONE, BIG_WOOD, BIG_IRON, T_EMERALD, BIG_EMERALD, T_RUBY,
			BIG_RUBY, T_SAPPHIRE, BIG_SAPPHIRE, TROLLING_MATERIAL, T_AMETHYST;
	public static GameHelperModItem SAPPHIRE_ITEM, RUBY_ITEM, AMETHYST_ITEM;
	public static GameHelperModBlock SAPPHIRE_BLOCK, RUBY_BLOCK, SAPPHIRE_ORE, RUBY_ORE, AMETHYST_BLOCK, AMETHYST_ORE;
	public static ItemTelescope TELESCOPE;
	public static GameHelperModItem TRADE_ITEM;
	public static CreativeTabs TAB_GAMEHELPER;
	public static IGHModBlock SEC_CHEST, SEC_FURNACE, SEC_FURNACE_LIT;
	public static GameHelperModBlock MARBLE, MARBLE_BRICK, BASALT, BASALT_BRICK, BASALT_COBBLE;

	public void registerTab() {
		TAB_GAMEHELPER = new GameHelperTab("ghTab");
		BACKPACK.updateInitEvent(TAB_GAMEHELPER);
		BIG_BACKPACK.updateInitEvent(TAB_GAMEHELPER);
		ULTRA_BACKPACK.updateInitEvent(TAB_GAMEHELPER);
		ENDER_BACKPACK.updateInitEvent(TAB_GAMEHELPER);
		WORLD_TELEPORTER.updateInitEvent(TAB_GAMEHELPER);
		GOLD_BS.updateInitEvent(TAB_GAMEHELPER);
		DIAMOND_BS.updateInitEvent(TAB_GAMEHELPER);
		STONE_BS.updateInitEvent(TAB_GAMEHELPER);
		WOOD_BS.updateInitEvent(TAB_GAMEHELPER);
		IRON_BS.updateInitEvent(TAB_GAMEHELPER);
		EMERALD_BS.updateInitEvent(TAB_GAMEHELPER);
		SAPPHIRE_BS.updateInitEvent(TAB_GAMEHELPER);
		RUBY_BS.updateInitEvent(TAB_GAMEHELPER);
		ENCH_ALTAR.updateInitEvent(TAB_GAMEHELPER);
		INST_ENCH.updateInitEvent(TAB_GAMEHELPER);
		UNCH_TABL.updateInitEvent(TAB_GAMEHELPER);
		SEC_CHEST.updateInitEvent(TAB_GAMEHELPER);
		SEC_FURNACE.updateInitEvent(TAB_GAMEHELPER);
		OP_ANVIL.updateInitEvent(TAB_GAMEHELPER);
		EMERALD_HELMET.updateInitEvent(TAB_GAMEHELPER);
		EMERALD_CHESTPLATE.updateInitEvent(TAB_GAMEHELPER);
		EMERALD_LEGGINGS.updateInitEvent(TAB_GAMEHELPER);
		EMERALD_BOOTS.updateInitEvent(TAB_GAMEHELPER);
		RUBY_HELMET.updateInitEvent(TAB_GAMEHELPER);
		RUBY_CHESTPLATE.updateInitEvent(TAB_GAMEHELPER);
		RUBY_LEGGINGS.updateInitEvent(TAB_GAMEHELPER);
		RUBY_BOOTS.updateInitEvent(TAB_GAMEHELPER);
		SAPPHIRE_HELMET.updateInitEvent(TAB_GAMEHELPER);
		SAPPHIRE_CHESTPLATE.updateInitEvent(TAB_GAMEHELPER);
		SAPPHIRE_LEGGINGS.updateInitEvent(TAB_GAMEHELPER);
		SAPPHIRE_BOOTS.updateInitEvent(TAB_GAMEHELPER);
		AMETHYST_HELMET.updateInitEvent(TAB_GAMEHELPER);
		AMETHYST_CHESTPLATE.updateInitEvent(TAB_GAMEHELPER);
		AMETHYST_LEGGINGS.updateInitEvent(TAB_GAMEHELPER);
		AMETHYST_BOOTS.updateInitEvent(TAB_GAMEHELPER);
		SAPPHIRE_ITEM.updateInitEvent(TAB_GAMEHELPER);
		SAPPHIRE_BLOCK.updateInitEvent(TAB_GAMEHELPER);
		SAPPHIRE_ORE.updateInitEvent(TAB_GAMEHELPER);
		RUBY_ITEM.updateInitEvent(TAB_GAMEHELPER);
		RUBY_BLOCK.updateInitEvent(TAB_GAMEHELPER);
		RUBY_ORE.updateInitEvent(TAB_GAMEHELPER);
		AMETHYST_ITEM.updateInitEvent(TAB_GAMEHELPER);
		AMETHYST_BLOCK.updateInitEvent(TAB_GAMEHELPER);
		AMETHYST_ORE.updateInitEvent(TAB_GAMEHELPER);
		EMERALD_SWORD.updateInitEvent(TAB_GAMEHELPER);
		SAPPHIRE_SWORD.updateInitEvent(TAB_GAMEHELPER);
		RUBY_SWORD.updateInitEvent(TAB_GAMEHELPER);
		AMETHYST_SWORD.updateInitEvent(TAB_GAMEHELPER);
		EMERALD_PICKAXE.updateInitEvent(TAB_GAMEHELPER);
		SAPPHIRE_PICKAXE.updateInitEvent(TAB_GAMEHELPER);
		RUBY_PICKAXE.updateInitEvent(TAB_GAMEHELPER);
		AMETHYST_PICKAXE.updateInitEvent(TAB_GAMEHELPER);
		EMERALD_AXE.updateInitEvent(TAB_GAMEHELPER);
		SAPPHIRE_AXE.updateInitEvent(TAB_GAMEHELPER);
		RUBY_AXE.updateInitEvent(TAB_GAMEHELPER);
		AMETHYST_AXE.updateInitEvent(TAB_GAMEHELPER);
		EMERALD_SHOVEL.updateInitEvent(TAB_GAMEHELPER);
		SAPPHIRE_SHOVEL.updateInitEvent(TAB_GAMEHELPER);
		RUBY_SHOVEL.updateInitEvent(TAB_GAMEHELPER);
		AMETHYST_SHOVEL.updateInitEvent(TAB_GAMEHELPER);
		EMERALD_HOE.updateInitEvent(TAB_GAMEHELPER);
		SAPPHIRE_HOE.updateInitEvent(TAB_GAMEHELPER);
		RUBY_HOE.updateInitEvent(TAB_GAMEHELPER);
		AMETHYST_HOE.updateInitEvent(TAB_GAMEHELPER);
		OP_ANVIL.updateInitEvent(TAB_GAMEHELPER);
		OBSIDIAN_SHARD.updateInitEvent(TAB_GAMEHELPER);
		OBSIDIAN_STICKS.updateInitEvent(TAB_GAMEHELPER);
		TELESCOPE.updateInitEvent(TAB_GAMEHELPER);
		TRADE_ITEM.updateInitEvent(TAB_GAMEHELPER);
		DIMENSION_BLOCK.updateInitEvent(TAB_GAMEHELPER);
		IRON_DAGGER.updateInitEvent(TAB_GAMEHELPER);
		MARBLE.updateInitEvent(TAB_GAMEHELPER);
		MARBLE_BRICK.updateInitEvent(TAB_GAMEHELPER);
		BASALT.updateInitEvent(TAB_GAMEHELPER);
		BASALT_BRICK.updateInitEvent(TAB_GAMEHELPER);
		BASALT_COBBLE.updateInitEvent(TAB_GAMEHELPER);
		TradeItems.updateInitFireCallEventsAll(TAB_GAMEHELPER);
		MINERS_LAMP.updateInitEvent(TAB_GAMEHELPER);
		MINERS_BACKPACK.updateInitEvent(TAB_GAMEHELPER);
		MINERS_TOOLS_BELT.updateInitEvent(TAB_GAMEHELPER);
		MINERS_DAGGER.updateInitEvent(TAB_GAMEHELPER);
		Flowers.updateInitFlowers(TAB_GAMEHELPER);
		MOBILE_WORKDBENCH.updateInitEvent(TAB_GAMEHELPER);
		POTION_BOW.updateInitEvent(TAB_GAMEHELPER);
		SEL_1.updateInitEvent(TAB_GAMEHELPER);
		Pureblue.updateInitEvent(TAB_GAMEHELPER);
		Purebrown.updateInitEvent(TAB_GAMEHELPER);
		SEL_2.updateInitEvent(TAB_GAMEHELPER);
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

	public static void setupEnchLists() {
		// helmet
		GameHelper.GH_MODULE.helmetList.add(Items.leather_helmet);
		GameHelper.GH_MODULE.helmetList.add(Items.chainmail_helmet);
		GameHelper.GH_MODULE.helmetList.add(Items.diamond_helmet);
		GameHelper.GH_MODULE.helmetList.add(Items.golden_helmet);
		GameHelper.GH_MODULE.helmetList.add(Items.iron_helmet);
		GameHelper.GH_MODULE.helmetList.add(GameHelperCoreModule.EMERALD_HELMET);
		GameHelper.GH_MODULE.helmetList.add(GameHelperCoreModule.RUBY_HELMET);
		GameHelper.GH_MODULE.helmetList.add(GameHelperCoreModule.SAPPHIRE_HELMET);
		GameHelper.GH_MODULE.helmetList.add(GameHelperCoreModule.AMETHYST_HELMET);
		// chestplates
		GameHelper.GH_MODULE.chestplateList.add(Items.leather_chestplate);
		GameHelper.GH_MODULE.chestplateList.add(Items.chainmail_chestplate);
		GameHelper.GH_MODULE.chestplateList.add(Items.diamond_chestplate);
		GameHelper.GH_MODULE.chestplateList.add(Items.golden_chestplate);
		GameHelper.GH_MODULE.chestplateList.add(Items.iron_chestplate);
		GameHelper.GH_MODULE.chestplateList.add(GameHelperCoreModule.EMERALD_CHESTPLATE);
		GameHelper.GH_MODULE.chestplateList.add(GameHelperCoreModule.RUBY_CHESTPLATE);
		GameHelper.GH_MODULE.chestplateList.add(GameHelperCoreModule.SAPPHIRE_CHESTPLATE);
		GameHelper.GH_MODULE.chestplateList.add(GameHelperCoreModule.AMETHYST_CHESTPLATE);
		// leggings
		GameHelper.GH_MODULE.leggingsList.add(Items.leather_leggings);
		GameHelper.GH_MODULE.leggingsList.add(Items.chainmail_leggings);
		GameHelper.GH_MODULE.leggingsList.add(Items.diamond_leggings);
		GameHelper.GH_MODULE.leggingsList.add(Items.golden_leggings);
		GameHelper.GH_MODULE.leggingsList.add(Items.iron_leggings);
		GameHelper.GH_MODULE.leggingsList.add(GameHelperCoreModule.EMERALD_LEGGINGS);
		GameHelper.GH_MODULE.leggingsList.add(GameHelperCoreModule.RUBY_LEGGINGS);
		GameHelper.GH_MODULE.leggingsList.add(GameHelperCoreModule.SAPPHIRE_LEGGINGS);
		GameHelper.GH_MODULE.leggingsList.add(GameHelperCoreModule.AMETHYST_LEGGINGS);
		// boots
		GameHelper.GH_MODULE.bootsList.add(Items.leather_boots);
		GameHelper.GH_MODULE.bootsList.add(Items.chainmail_boots);
		GameHelper.GH_MODULE.bootsList.add(Items.diamond_boots);
		GameHelper.GH_MODULE.bootsList.add(Items.golden_boots);
		GameHelper.GH_MODULE.bootsList.add(Items.iron_boots);
		GameHelper.GH_MODULE.bootsList.add(GameHelperCoreModule.EMERALD_BOOTS);
		GameHelper.GH_MODULE.bootsList.add(GameHelperCoreModule.RUBY_BOOTS);
		GameHelper.GH_MODULE.bootsList.add(GameHelperCoreModule.SAPPHIRE_BOOTS);
		GameHelper.GH_MODULE.bootsList.add(GameHelperCoreModule.AMETHYST_BOOTS);
		// sword
		GameHelper.GH_MODULE.swordList.add(Items.wooden_sword);
		GameHelper.GH_MODULE.swordList.add(Items.stone_sword);
		GameHelper.GH_MODULE.swordList.add(Items.diamond_sword);
		GameHelper.GH_MODULE.swordList.add(Items.golden_sword);
		GameHelper.GH_MODULE.swordList.add(Items.iron_sword);
		GameHelper.GH_MODULE.swordList.add(GameHelperCoreModule.EMERALD_SWORD);
		GameHelper.GH_MODULE.swordList.add(GameHelperCoreModule.RUBY_SWORD);
		GameHelper.GH_MODULE.swordList.add(GameHelperCoreModule.SAPPHIRE_SWORD);
		GameHelper.GH_MODULE.swordList.add(GameHelperCoreModule.AMETHYST_SWORD);
		// pickaxe
		GameHelper.GH_MODULE.pickaxeList.add(Items.wooden_pickaxe);
		GameHelper.GH_MODULE.pickaxeList.add(Items.stone_pickaxe);
		GameHelper.GH_MODULE.pickaxeList.add(Items.diamond_pickaxe);
		GameHelper.GH_MODULE.pickaxeList.add(Items.golden_pickaxe);
		GameHelper.GH_MODULE.pickaxeList.add(Items.iron_pickaxe);
		GameHelper.GH_MODULE.pickaxeList.add(GameHelperCoreModule.EMERALD_PICKAXE);
		GameHelper.GH_MODULE.pickaxeList.add(GameHelperCoreModule.RUBY_PICKAXE);
		GameHelper.GH_MODULE.pickaxeList.add(GameHelperCoreModule.SAPPHIRE_PICKAXE);
		GameHelper.GH_MODULE.pickaxeList.add(GameHelperCoreModule.AMETHYST_PICKAXE);
		// GameHelper.GH_MODULE.pickaxeList.add(GameHelper.EMERALD_pickaxe);
		// GameHelper.GH_MODULE.pickaxeList.add(GameHelper.RUBY_pickaxe);
		// GameHelper.GH_MODULE.pickaxeList.add(GameHelper.SAPPHIRE_pickaxe);
		// axe
		GameHelper.GH_MODULE.axeList.add(Items.wooden_axe);
		GameHelper.GH_MODULE.axeList.add(Items.stone_axe);
		GameHelper.GH_MODULE.axeList.add(Items.diamond_axe);
		GameHelper.GH_MODULE.axeList.add(Items.golden_axe);
		GameHelper.GH_MODULE.axeList.add(Items.iron_axe);
		GameHelper.GH_MODULE.axeList.add(GameHelperCoreModule.EMERALD_AXE);
		GameHelper.GH_MODULE.axeList.add(GameHelperCoreModule.RUBY_AXE);
		GameHelper.GH_MODULE.axeList.add(GameHelperCoreModule.SAPPHIRE_AXE);
		GameHelper.GH_MODULE.axeList.add(GameHelperCoreModule.AMETHYST_AXE);
		// GameHelper.GH_MODULE.axeList.add(GameHelper.EMERALD_axe);
		// GameHelper.GH_MODULE.axeList.add(GameHelper.RUBY_axe);
		// GameHelper.GH_MODULE.axeList.add(GameHelper.SAPPHIRE_axe);
		// hoe
		GameHelper.GH_MODULE.hoeList.add(Items.wooden_hoe);
		GameHelper.GH_MODULE.hoeList.add(Items.stone_hoe);
		GameHelper.GH_MODULE.hoeList.add(Items.diamond_hoe);
		GameHelper.GH_MODULE.hoeList.add(Items.golden_hoe);
		GameHelper.GH_MODULE.hoeList.add(Items.iron_hoe);
		GameHelper.GH_MODULE.hoeList.add(GameHelperCoreModule.EMERALD_AXE);
		GameHelper.GH_MODULE.hoeList.add(GameHelperCoreModule.RUBY_AXE);
		GameHelper.GH_MODULE.hoeList.add(GameHelperCoreModule.SAPPHIRE_AXE);
		GameHelper.GH_MODULE.hoeList.add(GameHelperCoreModule.AMETHYST_AXE);
		// GameHelper.GH_MODULE.hoeList.add(GameHelper.EMERALD_hoe);
		// GameHelper.GH_MODULE.hoeList.add(GameHelper.RUBY_hoe);
		// GameHelper.GH_MODULE.hoeList.add(GameHelper.SAPPHIRE_hoe);
		// shovel
		GameHelper.GH_MODULE.shovelList.add(Items.wooden_shovel);
		GameHelper.GH_MODULE.shovelList.add(Items.stone_shovel);
		GameHelper.GH_MODULE.shovelList.add(Items.diamond_shovel);
		GameHelper.GH_MODULE.shovelList.add(Items.golden_shovel);
		GameHelper.GH_MODULE.shovelList.add(Items.iron_shovel);
		GameHelper.GH_MODULE.shovelList.add(GameHelperCoreModule.EMERALD_SHOVEL);
		GameHelper.GH_MODULE.shovelList.add(GameHelperCoreModule.RUBY_SHOVEL);
		GameHelper.GH_MODULE.shovelList.add(GameHelperCoreModule.SAPPHIRE_SHOVEL);
		GameHelper.GH_MODULE.shovelList.add(GameHelperCoreModule.AMETHYST_SHOVEL);
		// GameHelper.GH_MODULE.shovelList.add(GameHelper.EMERALD_shovel);
		// GameHelper.GH_MODULE.shovelList.add(GameHelper.RUBY_shovel);
		// GameHelper.GH_MODULE.shovelList.add(GameHelper.SAPPHIRE_shovel);
		// fns
		GameHelper.GH_MODULE.fnsList.add(Items.flint_and_steel);
		// rod
		GameHelper.GH_MODULE.rodList.add(Items.fishing_rod);
		// bow
		GameHelper.GH_MODULE.bowList.add(Items.bow);
		// shears
		GameHelper.GH_MODULE.shearsList.add(Items.shears);

		GameHelper.GH_MODULE.chestplateEnchants = new Enchantment[] { EnchantmentFinder.PROTECTION.toFMLEnchantment(),
				EnchantmentFinder.PROJECTILE_PROTECTION.toFMLEnchantment(),
				EnchantmentFinder.FIRE_PROTECTION.toFMLEnchantment(),
				EnchantmentFinder.BLAST_PROTECTION.toFMLEnchantment(), EnchantmentFinder.THORNS.toFMLEnchantment(),
				EnchantmentFinder.UNBREAKING.toFMLEnchantment(), EnchantmentFinder.SOULBOUND.toFMLEnchantment() };
		GameHelper.GH_MODULE.helmetEnchants = new Enchantment[] { EnchantmentFinder.PROTECTION.toFMLEnchantment(),
				EnchantmentFinder.PROJECTILE_PROTECTION.toFMLEnchantment(),
				EnchantmentFinder.FIRE_PROTECTION.toFMLEnchantment(),
				EnchantmentFinder.BLAST_PROTECTION.toFMLEnchantment(), EnchantmentFinder.THORNS.toFMLEnchantment(),
				EnchantmentFinder.UNBREAKING.toFMLEnchantment(), EnchantmentFinder.SOULBOUND.toFMLEnchantment(),
				EnchantmentFinder.AQUA_AFFINITY.toFMLEnchantment(), EnchantmentFinder.RESPIRATION.toFMLEnchantment() };
		GameHelper.GH_MODULE.leggingsEnchants = new Enchantment[] { EnchantmentFinder.PROTECTION.toFMLEnchantment(),
				EnchantmentFinder.PROJECTILE_PROTECTION.toFMLEnchantment(),
				EnchantmentFinder.FIRE_PROTECTION.toFMLEnchantment(),
				EnchantmentFinder.BLAST_PROTECTION.toFMLEnchantment(), EnchantmentFinder.THORNS.toFMLEnchantment(),
				EnchantmentFinder.UNBREAKING.toFMLEnchantment(), EnchantmentFinder.SOULBOUND.toFMLEnchantment() };
		GameHelper.GH_MODULE.bootsEnchants = new Enchantment[] { EnchantmentFinder.PROTECTION.toFMLEnchantment(),
				EnchantmentFinder.PROJECTILE_PROTECTION.toFMLEnchantment(),
				EnchantmentFinder.FIRE_PROTECTION.toFMLEnchantment(),
				EnchantmentFinder.BLAST_PROTECTION.toFMLEnchantment(), EnchantmentFinder.THORNS.toFMLEnchantment(),
				EnchantmentFinder.UNBREAKING.toFMLEnchantment(), EnchantmentFinder.SOULBOUND.toFMLEnchantment(),
				EnchantmentFinder.FEATHER_FALLING.toFMLEnchantment(),
				EnchantmentFinder.DEPTH_STRIDER.toFMLEnchantment() };
		GameHelper.GH_MODULE.pickaxeSilk = new Enchantment[] { EnchantmentFinder.EFFICIENCY.toFMLEnchantment(),
				EnchantmentFinder.SILK_TOUCH.toFMLEnchantment(), EnchantmentFinder.UNBREAKING.toFMLEnchantment(),
				EnchantmentFinder.SOULBOUND.toFMLEnchantment(), EnchantmentFinder.XPBOOST.toFMLEnchantment() };
		GameHelper.GH_MODULE.pickaxeFortuneSmelting = new Enchantment[] { EnchantmentFinder.FORTUNE.toFMLEnchantment(),
				EnchantmentFinder.EFFICIENCY.toFMLEnchantment(), EnchantmentFinder.SMELTING.toFMLEnchantment(),
				EnchantmentFinder.UNBREAKING.toFMLEnchantment(), EnchantmentFinder.SOULBOUND.toFMLEnchantment(),
				EnchantmentFinder.XPBOOST.toFMLEnchantment(), EnchantmentFinder.DESTRUCTION.toFMLEnchantment() };
		GameHelper.GH_MODULE.pickaxeElse = new Enchantment[] { EnchantmentFinder.FORTUNE.toFMLEnchantment(),
				EnchantmentFinder.EFFICIENCY.toFMLEnchantment(), EnchantmentFinder.SILK_TOUCH.toFMLEnchantment(),
				EnchantmentFinder.SMELTING.toFMLEnchantment(), EnchantmentFinder.UNBREAKING.toFMLEnchantment(),
				EnchantmentFinder.SOULBOUND.toFMLEnchantment(), EnchantmentFinder.XPBOOST.toFMLEnchantment(),
				EnchantmentFinder.DESTRUCTION.toFMLEnchantment() };
		GameHelper.GH_MODULE.swordEnchants = new Enchantment[] { EnchantmentFinder.SHARPNESS.toFMLEnchantment(),
				EnchantmentFinder.BANE_OF_ARTHROPODS.toFMLEnchantment(), EnchantmentFinder.KNOCKBACK.toFMLEnchantment(),
				EnchantmentFinder.LOOTING.toFMLEnchantment(), EnchantmentFinder.FIRE_ASPECT.toFMLEnchantment(),
				EnchantmentFinder.SMITE.toFMLEnchantment(), EnchantmentFinder.SOULBOUND.toFMLEnchantment(),
				EnchantmentFinder.XPBOOST.toFMLEnchantment(), EnchantmentFinder.HEAD_LOOT.toFMLEnchantment(),
				EnchantmentFinder.UNBREAKING.toFMLEnchantment() };
		GameHelper.GH_MODULE.bowEnchants = new Enchantment[] { EnchantmentFinder.POWER.toFMLEnchantment(),
				EnchantmentFinder.FLAME.toFMLEnchantment(), EnchantmentFinder.PUNCH.toFMLEnchantment(),
				EnchantmentFinder.INFINITY.toFMLEnchantment(), EnchantmentFinder.UNBREAKING.toFMLEnchantment(),
				EnchantmentFinder.SOULBOUND.toFMLEnchantment(), EnchantmentFinder.XPBOOST.toFMLEnchantment() };
		GameHelper.GH_MODULE.rodEnchants = new Enchantment[] { EnchantmentFinder.LURE.toFMLEnchantment(),
				EnchantmentFinder.LUCK_OF_THE_SEA.toFMLEnchantment(), EnchantmentFinder.UNBREAKING.toFMLEnchantment(),
				EnchantmentFinder.SOULBOUND.toFMLEnchantment() };
		GameHelper.GH_MODULE.hoeEnchants = new Enchantment[] { EnchantmentFinder.UNBREAKING.toFMLEnchantment(),
				EnchantmentFinder.SOULBOUND.toFMLEnchantment() };
		GameHelper.GH_MODULE.axeEnchants = new Enchantment[] { EnchantmentFinder.EFFICIENCY.toFMLEnchantment(),
				EnchantmentFinder.UNBREAKING.toFMLEnchantment(), EnchantmentFinder.SMELTING.toFMLEnchantment(),
				EnchantmentFinder.SOULBOUND.toFMLEnchantment(), EnchantmentFinder.HEAD_LOOT.toFMLEnchantment() };
		GameHelper.GH_MODULE.shovelEnchants = new Enchantment[] { EnchantmentFinder.EFFICIENCY.toFMLEnchantment(),
				EnchantmentFinder.SMELTING.toFMLEnchantment(), EnchantmentFinder.UNBREAKING.toFMLEnchantment(),
				EnchantmentFinder.SOULBOUND.toFMLEnchantment() };
		GameHelper.GH_MODULE.fnsEnchants = new Enchantment[] { EnchantmentFinder.UNBREAKING.toFMLEnchantment(),
				EnchantmentFinder.SOULBOUND.toFMLEnchantment() };
		GameHelper.GH_MODULE.shearsEnchants = new Enchantment[] { EnchantmentFinder.UNBREAKING.toFMLEnchantment(),
				EnchantmentFinder.SOULBOUND.toFMLEnchantment() };
		GameHelper.GH_MODULE.otherMaterialEnchants = new Enchantment[] {
				EnchantmentFinder.SOULBOUND.toFMLEnchantment() };
	}

	public static boolean isArmorChestLeggings(Item m) {
		return GameHelper.GH_MODULE.chestplateList.contains(m) || GameHelper.GH_MODULE.leggingsList.contains(m);
	}

	static public boolean isArmorBoots(Item m) {
		return GameHelper.GH_MODULE.bootsList.contains(m);
	}

	static public boolean isArmorHelmet(Item m) {
		return GameHelper.GH_MODULE.helmetList.contains(m);
	}

	static public boolean isShovel(Item m) {
		return GameHelper.GH_MODULE.shovelList.contains(m);
	}

	static public boolean isPickaxe(Item m) {
		return GameHelper.GH_MODULE.pickaxeList.contains(m);
	}

	static public boolean isAxe(Item m) {
		return GameHelper.GH_MODULE.axeList.contains(m);
	}

	static public boolean isHoe(Item m) {
		return GameHelper.GH_MODULE.hoeList.contains(m);
	}

	static public boolean isBow(Item m) {
		return GameHelper.GH_MODULE.bowList.contains(m);
	}

	static public boolean isFishingRod(Item m) {
		return GameHelper.GH_MODULE.rodList.contains(m);
	}

	static public boolean isFNS(Item m) {
		return GameHelper.GH_MODULE.fnsList.contains(m);
	}

	static public boolean isShear(Item m) {
		return GameHelper.GH_MODULE.shearsList.contains(m);
	}

	static public boolean isSword(Item m) {
		return GameHelper.GH_MODULE.swordList.contains(m);
	}

	public static Enchantment[] getItemBoundEnchantments(ItemStack is) {
		Item m = is.getItem();
		if (isArmorChestLeggings(m)) {
			return GameHelper.GH_MODULE.chestplateEnchants;
		} else if (isArmorBoots(m)) {
			return GameHelper.GH_MODULE.bootsEnchants;
		} else if (isArmorHelmet(m)) {
			return GameHelper.GH_MODULE.helmetEnchants;
		} else if (isAxe(m)) {
			return GameHelper.GH_MODULE.axeEnchants;
		} else if (isBow(m)) {
			return GameHelper.GH_MODULE.bowEnchants;
		} else if (isFishingRod(m)) {
			return GameHelper.GH_MODULE.rodEnchants;
		} else if (isFNS(m)) {
			return GameHelper.GH_MODULE.fnsEnchants;
		} else if (isHoe(m)) {
			return GameHelper.GH_MODULE.hoeEnchants;
		} else if (isPickaxe(m)) {
			if (GameHelper.getUtils().hasEnchantment(is, EnchantmentFinder.SILK_TOUCH.toFMLEnchantment())) {
				return GameHelper.GH_MODULE.pickaxeSilk;
			} else if (GameHelper.getUtils().hasEnchantment(is, EnchantmentFinder.FORTUNE.toFMLEnchantment())
					|| GameHelper.getUtils().hasEnchantment(is, EnchantmentFinder.SMELTING.toFMLEnchantment())) {
				return GameHelper.GH_MODULE.pickaxeFortuneSmelting;
			} else {
				return GameHelper.GH_MODULE.pickaxeElse;
			}
		} else if (isShear(m)) {
			return GameHelper.GH_MODULE.shearsEnchants;
		} else if (isShovel(m)) {
			return GameHelper.GH_MODULE.shovelEnchants;
		} else if (isSword(m)) {
			return GameHelper.GH_MODULE.swordEnchants;
		} else {
			return GameHelper.GH_MODULE.otherMaterialEnchants;
		}
	}
}