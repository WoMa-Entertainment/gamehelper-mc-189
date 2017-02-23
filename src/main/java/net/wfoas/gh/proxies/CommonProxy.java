package net.wfoas.gh.proxies;

import java.io.File;

import org.apache.logging.log4j.Level;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.GameHelperCoreModule;
import net.wfoas.gh.GameHelperServer;
import net.wfoas.gh.armor.GHAmethystArmor;
import net.wfoas.gh.armor.GHEmeraldArmor;
import net.wfoas.gh.armor.GHRubyArmor;
import net.wfoas.gh.armor.GHSaphirreArmor;
import net.wfoas.gh.bigsword.BigswordItem;
import net.wfoas.gh.blocks.AmethystBlock;
import net.wfoas.gh.blocks.AmethystOre;
import net.wfoas.gh.blocks.GameHelperModBlock;
import net.wfoas.gh.blocks.No_texture_block;
import net.wfoas.gh.blocks.Pureblack;
import net.wfoas.gh.blocks.Pureblue;
import net.wfoas.gh.blocks.Purebrown;
import net.wfoas.gh.blocks.Purecyan;
import net.wfoas.gh.blocks.Puregray;
import net.wfoas.gh.blocks.Puregreen;
import net.wfoas.gh.blocks.Purelightblue;
import net.wfoas.gh.blocks.Purelightgray;
import net.wfoas.gh.blocks.Purelime;
import net.wfoas.gh.blocks.Puremagenta;
import net.wfoas.gh.blocks.Pureorange;
import net.wfoas.gh.blocks.Purepink;
import net.wfoas.gh.blocks.Purepurple;
import net.wfoas.gh.blocks.Purered;
import net.wfoas.gh.blocks.Purewhite;
import net.wfoas.gh.blocks.Pureyellow;
import net.wfoas.gh.blocks.RubyBlock;
import net.wfoas.gh.blocks.RubyOre;
import net.wfoas.gh.blocks.SapphireBlock;
import net.wfoas.gh.blocks.SapphireOre;
import net.wfoas.gh.blocks.Tileblack;
import net.wfoas.gh.blocks.Tileblue;
import net.wfoas.gh.blocks.Tilebrown;
import net.wfoas.gh.blocks.Tilecyan;
import net.wfoas.gh.blocks.Tilegray;
import net.wfoas.gh.blocks.Tilegreen;
import net.wfoas.gh.blocks.Tilemagenta;
import net.wfoas.gh.blocks.Tileorange;
import net.wfoas.gh.blocks.Tilepink;
import net.wfoas.gh.blocks.Tilepurple;
import net.wfoas.gh.blocks.Tilered;
import net.wfoas.gh.blocks.Tilewhite;
import net.wfoas.gh.blocks.Tileyellow;
import net.wfoas.gh.blocks.glass.Neonglas;
import net.wfoas.gh.blocks.glass.Neonglasblack;
import net.wfoas.gh.blocks.glass.Neonglasblue;
import net.wfoas.gh.blocks.glass.Neonglasbrown;
import net.wfoas.gh.blocks.glass.Neonglascyan;
import net.wfoas.gh.blocks.glass.Neonglasgray;
import net.wfoas.gh.blocks.glass.Neonglasgreen;
import net.wfoas.gh.blocks.glass.Neonglaslightblue;
import net.wfoas.gh.blocks.glass.Neonglaslime;
import net.wfoas.gh.blocks.glass.Neonglasmagenta;
import net.wfoas.gh.blocks.glass.Neonglasorange;
import net.wfoas.gh.blocks.glass.Neonglaspink;
import net.wfoas.gh.blocks.glass.Neonglaspurple;
import net.wfoas.gh.blocks.glass.Neonglasred;
import net.wfoas.gh.blocks.glass.Neonglassilver;
import net.wfoas.gh.blocks.glass.Neonglaswhite;
import net.wfoas.gh.blocks.glass.Neonglasyellow;
import net.wfoas.gh.blocks.glass.Pureglas;
import net.wfoas.gh.blocks.glass.Pureglasblack;
import net.wfoas.gh.blocks.glass.Pureglasblue;
import net.wfoas.gh.blocks.glass.Pureglasbrown;
import net.wfoas.gh.blocks.glass.Pureglascyan;
import net.wfoas.gh.blocks.glass.Pureglasgray;
import net.wfoas.gh.blocks.glass.Pureglasgreen;
import net.wfoas.gh.blocks.glass.Pureglaslightblue;
import net.wfoas.gh.blocks.glass.Pureglaslime;
import net.wfoas.gh.blocks.glass.Pureglasmagenta;
import net.wfoas.gh.blocks.glass.Pureglasorange;
import net.wfoas.gh.blocks.glass.Pureglaspink;
import net.wfoas.gh.blocks.glass.Pureglaspurple;
import net.wfoas.gh.blocks.glass.Pureglasred;
import net.wfoas.gh.blocks.glass.Pureglassilver;
import net.wfoas.gh.blocks.glass.Pureglasyellow;
import net.wfoas.gh.config.DefaultConfig;
import net.wfoas.gh.config.GHConfig;
import net.wfoas.gh.dagger.throwable.ThrowableDagger;
import net.wfoas.gh.enchaltar.EnchantmentAltar;
import net.wfoas.gh.enchaltar.TileEntityEnchantmentAltar;
import net.wfoas.gh.enchantment.EnchantmentDestruction;
import net.wfoas.gh.enchantment.EnchantmentHeadloot;
import net.wfoas.gh.enchantment.EnchantmentSmelting;
import net.wfoas.gh.enchantment.EnchantmentSoulbound;
import net.wfoas.gh.enchantment.EnchantmentXPBoost;
import net.wfoas.gh.enchantment.EnchantmentZoom;
import net.wfoas.gh.events.EventRegistar;
import net.wfoas.gh.flowers.Flowers;
import net.wfoas.gh.gui.GuiHandler;
import net.wfoas.gh.instench.InstantEnchantmentTable;
import net.wfoas.gh.instench.TileEntityInstantEnchantmentTable;
import net.wfoas.gh.items.BackpackItem;
import net.wfoas.gh.items.BigBackpackItem;
import net.wfoas.gh.items.EnderbackpackItem;
import net.wfoas.gh.items.GameHelperModItem;
import net.wfoas.gh.items.GameHelperModSword;
import net.wfoas.gh.items.ItemTelescope;
import net.wfoas.gh.items.MobileWorkbenchItem;
import net.wfoas.gh.items.PotionBow;
import net.wfoas.gh.items.SelectionTool;
import net.wfoas.gh.items.SelectionTool2;
import net.wfoas.gh.items.TradeItems;
import net.wfoas.gh.items.UltraBackpackItem;
import net.wfoas.gh.items.WorldTeleporterItem;
import net.wfoas.gh.items.tools.ItemGHAxeTool;
import net.wfoas.gh.items.tools.ItemGHHoeTool;
import net.wfoas.gh.items.tools.ItemGHPickaxeTool;
import net.wfoas.gh.items.tools.ItemGHShovelTool;
import net.wfoas.gh.minersinventory.items.IronDagger;
import net.wfoas.gh.minersinventory.items.MinersBackpack;
import net.wfoas.gh.minersinventory.items.MinersDagger;
import net.wfoas.gh.minersinventory.items.MinersHelmetLight;
import net.wfoas.gh.minersinventory.items.MinersToolsBelt;
import net.wfoas.gh.op_anvil.OPAnvil;
import net.wfoas.gh.potionbow.EntityShotPotion;
import net.wfoas.gh.protected_blocks.chest.ProtectedChestTileEntity;
import net.wfoas.gh.protected_blocks.chest.ProtectedChestTileEntityBlock;
import net.wfoas.gh.protected_blocks.furnace.ProtectedFurnaceBlock;
import net.wfoas.gh.protected_blocks.furnace.ProtectedFurnaceTileEntity;
import net.wfoas.gh.recipes.RecipeManager;
import net.wfoas.gh.thermalinventory.armor.GameHelperThermalArmorBootsItem;
import net.wfoas.gh.thermalinventory.armor.GameHelperThermalArmorChestplateItem;
import net.wfoas.gh.thermalinventory.armor.GameHelperThermalArmorHelmetItem;
import net.wfoas.gh.thermalinventory.armor.GameHelperThermalArmorLeggingsItem;
import net.wfoas.gh.unchant.TileEntityUnchantmentTable;
import net.wfoas.gh.unchant.UnchantmentTable;
import net.wfoas.gh.villager.VillagerRegistrar;
import net.wfoas.gh.villager.entity.GHVillager;
import net.wfoas.gh.worlddimensionsutils.DimensionBlock;
import net.wfoas.gh.worldgenerator.GHWorldGenerator;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event, GameHelper gh) {
		gh.cfgDataFolder = new File(event.getModConfigurationDirectory(), "gamehelper");
		gh.cfgDataFolder.mkdirs();
		gh.defaultConfig = new DefaultConfig();
		gh.CONFIG = new GHConfig(new File(gh.cfgDataFolder, "config.properties"));
		GameHelperCoreModule.T_RUBY = EnumHelper.addToolMaterial("T_RUBY", 3, 4500, 14.0F, 5.0F, 27);
		GameHelperCoreModule.BIG_RUBY = EnumHelper.addToolMaterial("BIG_RUBY", 0, 4500, 14.0F, 14.0F, 27);
		GameHelperCoreModule.T_SAPPHIRE = EnumHelper.addToolMaterial("T_SAPPHIRE", 3, 4000, 13.0F, 4.5F, 26);
		GameHelperCoreModule.BIG_SAPPHIRE = EnumHelper.addToolMaterial("BIG_SAPPHIRE", 0, 4000, 13.0F, 13.0F, 26);
		GameHelperCoreModule.T_EMERALD = EnumHelper.addToolMaterial("T_EMERALD", 3, 3000, 12.0F, 4.0F, 25);
		GameHelperCoreModule.T_AMETHYST = EnumHelper.addToolMaterial("T_AMETHYST", 4, 6000, 15F, 3.5F, 45);
		GameHelperCoreModule.BIG_EMERALD = EnumHelper.addToolMaterial("BIG_EMERALD", 0, 3000, 12.0F, 12.0F, 25);
		GameHelperCoreModule.BIG_GOLD = EnumHelper.addToolMaterial("BIG_GOLD", 0, 32, 12.0F, 4.0F, 22);
		GameHelperCoreModule.BIG_DIAMOND = EnumHelper.addToolMaterial("BIG_DIAMOND", 3, 1561, 8.0F, 10.0F, 10);
		GameHelperCoreModule.BIG_STONE = EnumHelper.addToolMaterial("BIG_STONE", 1, 131, 4.0F, 6.0F, 5);
		GameHelperCoreModule.BIG_WOOD = EnumHelper.addToolMaterial("BIG_WOOD", 0, 59, 2.0F, 4.0F, 15);
		GameHelperCoreModule.BIG_IRON = EnumHelper.addToolMaterial("BIG_IRON", 2, 250, 6.0F, 8.0F, 14);
		GameHelperCoreModule.TROLLING_MATERIAL = EnumHelper.addToolMaterial("TROLLING_MATERIAL", 0, -1, 2.0F, -3.0F,
				15);
		GameHelperCoreModule.EMERALD = EnumHelper.addArmorMaterial("emerald", "gamehelper:emerald", 35,
				new int[] { 3, 8, 6, 3 }, 25);
		GameHelperCoreModule.AMETHYST = EnumHelper.addArmorMaterial("amethyst", "gamehelper:amethyst", 40,
				new int[] { 3, 8, 6, 3 }, 45);
		GameHelperCoreModule.SAPPHIRE = EnumHelper.addArmorMaterial("saphirre", "gamehelper:sapphire", 36,
				new int[] { 3, 8, 6, 3 }, 26);
		GameHelperCoreModule.RUBY = EnumHelper.addArmorMaterial("ruby", "gamehelper:ruby", 37, new int[] { 3, 8, 6, 3 },
				27);
		GameHelperCoreModule.RAINBOW = EnumHelper.addArmorMaterial("rainbow", "gamehelper:rainbow", 38,
				new int[] { 3, 8, 6, 3 }, 30);
		GameHelperCoreModule.SPIRAL = EnumHelper.addArmorMaterial("spiral", "gamehelper:spiral", -1,
				new int[] { 3, 8, 6, 3 }, 1);
		GameHelperCoreModule.ENCH_SOULBOUND = new EnchantmentSoulbound(70,
				new ResourceLocation(GameHelper.MODID, "soulbound"), 10);
		GameHelperCoreModule.ENCH_SMELTING = new EnchantmentSmelting(71,
				new ResourceLocation(GameHelper.MODID, "smelting"), 15);
		GameHelperCoreModule.ENCH_XPBOOST = new EnchantmentXPBoost(72,
				new ResourceLocation(GameHelper.MODID, "xp_boost"), 13);
		GameHelperCoreModule.ENCH_HEADLOOT = new EnchantmentHeadloot(73,
				new ResourceLocation(GameHelper.MODID, "headloot"), 18);
		GameHelperCoreModule.ENCH_ZOOM = new EnchantmentZoom(74, new ResourceLocation(GameHelper.MODID, "zoom"), 13);
		GameHelperCoreModule.ENCH_DESTRUCTION = new EnchantmentDestruction(75,
				new ResourceLocation(GameHelper.MODID, "destruction"), 15);
		GameHelperCoreModule.ENCH_ALTAR = new EnchantmentAltar();
		GameHelperCoreModule.INST_ENCH = new InstantEnchantmentTable();
		GameHelperCoreModule.UNCH_TABL = new UnchantmentTable();
		EventRegistar.registerEventClass();
		GameHelperCoreModule.SAPPHIRE_ITEM = new GameHelperModItem("sapphire");
		GameHelperCoreModule.RUBY_ITEM = new GameHelperModItem("ruby");
		GameHelperCoreModule.AMETHYST_ITEM = new GameHelperModItem("amethyst");
		GameHelper.TITAN_MODULE.preInitServer(event);
		GameHelper.LUCKY_MODULE.preInitServer(event);
		GameHelperCoreModule.OP_ANVIL = new OPAnvil();
		GameHelperCoreModule.BACKPACK = new BackpackItem();
		GameHelperCoreModule.BIG_BACKPACK = new BigBackpackItem();
		GameHelperCoreModule.ULTRA_BACKPACK = new UltraBackpackItem();
		GameHelperCoreModule.ENDER_BACKPACK = new EnderbackpackItem();
		GameHelperCoreModule.WORLD_TELEPORTER = new WorldTeleporterItem();
		GameHelperCoreModule.OBSIDIAN_STICKS = new GameHelperModItem("obsidian_stick");
		GameHelperCoreModule.OBSIDIAN_SHARD = new GameHelperModItem("obsidian_shard");
		GameHelperCoreModule.GOLD_BS = new BigswordItem("gold_Bigsword", GameHelperCoreModule.BIG_GOLD);
		GameHelperCoreModule.DIAMOND_BS = new BigswordItem("diamond_Bigsword", GameHelperCoreModule.BIG_DIAMOND);
		GameHelperCoreModule.STONE_BS = new BigswordItem("stone_Bigsword", GameHelperCoreModule.BIG_STONE);
		GameHelperCoreModule.WOOD_BS = new BigswordItem("wood_Bigsword", GameHelperCoreModule.BIG_WOOD);
		GameHelperCoreModule.IRON_BS = new BigswordItem("iron_Bigsword", GameHelperCoreModule.BIG_IRON);
		GameHelperCoreModule.EMERALD_BS = new BigswordItem("emerald_Bigsword", GameHelperCoreModule.BIG_EMERALD);
		GameHelperCoreModule.SAPPHIRE_BS = new BigswordItem("sapphire_Bigsword", GameHelperCoreModule.BIG_SAPPHIRE);
		GameHelperCoreModule.RUBY_BS = new BigswordItem("ruby_Bigsword", GameHelperCoreModule.BIG_RUBY);
		GameHelperCoreModule.EMERALD_HELMET = new GHEmeraldArmor("emerald_helmet", 1, 0);
		GameHelperCoreModule.EMERALD_CHESTPLATE = new GHEmeraldArmor("emerald_chestplate", 1, 1);
		GameHelperCoreModule.EMERALD_LEGGINGS = new GHEmeraldArmor("emerald_leggings", 2, 2);
		GameHelperCoreModule.EMERALD_BOOTS = new GHEmeraldArmor("emerald_boots", 1, 3);
		GameHelperCoreModule.RUBY_HELMET = new GHRubyArmor("ruby_helmet", 1, 0);
		GameHelperCoreModule.RUBY_CHESTPLATE = new GHRubyArmor("ruby_chestplate", 1, 1);
		GameHelperCoreModule.RUBY_LEGGINGS = new GHRubyArmor("ruby_leggings", 2, 2);
		GameHelperCoreModule.RUBY_BOOTS = new GHRubyArmor("ruby_boots", 1, 3);
		GameHelperCoreModule.AMETHYST_HELMET = new GHAmethystArmor("amethyst_helmet", 1, 0);
		GameHelperCoreModule.AMETHYST_CHESTPLATE = new GHAmethystArmor("amethyst_chestplate", 1, 1);
		GameHelperCoreModule.AMETHYST_LEGGINGS = new GHAmethystArmor("amethyst_leggings", 2, 2);
		GameHelperCoreModule.AMETHYST_BOOTS = new GHAmethystArmor("amethyst_boots", 1, 3);
		GameHelperCoreModule.SAPPHIRE_HELMET = new GHSaphirreArmor("sapphire_helmet", 1, 0);
		GameHelperCoreModule.SAPPHIRE_CHESTPLATE = new GHSaphirreArmor("sapphire_chestplate", 1, 1);
		GameHelperCoreModule.SAPPHIRE_LEGGINGS = new GHSaphirreArmor("sapphire_leggings", 2, 2);
		GameHelperCoreModule.SAPPHIRE_BOOTS = new GHSaphirreArmor("sapphire_boots", 1, 3);
		GameHelperCoreModule.SAPPHIRE.customCraftingMaterial = GameHelperCoreModule.SAPPHIRE_ITEM;
		GameHelperCoreModule.AMETHYST_SWORD = new GameHelperModSword("amethyst_sword", GameHelperCoreModule.T_AMETHYST);
		GameHelperCoreModule.EMERALD_SWORD = new GameHelperModSword("emerald_sword", GameHelperCoreModule.T_EMERALD);
		GameHelperCoreModule.SAPPHIRE_SWORD = new GameHelperModSword("sapphire_sword", GameHelperCoreModule.T_SAPPHIRE);
		GameHelperCoreModule.RUBY_SWORD = new GameHelperModSword("ruby_sword", GameHelperCoreModule.T_RUBY);
		GameHelperCoreModule.EMERALD_PICKAXE = new ItemGHPickaxeTool("emerald_pickaxe", GameHelperCoreModule.T_EMERALD);
		GameHelperCoreModule.SAPPHIRE_PICKAXE = new ItemGHPickaxeTool("sapphire_pickaxe",
				GameHelperCoreModule.T_SAPPHIRE);
		GameHelperCoreModule.RUBY_PICKAXE = new ItemGHPickaxeTool("ruby_pickaxe", GameHelperCoreModule.T_RUBY);
		GameHelperCoreModule.AMETHYST_PICKAXE = new ItemGHPickaxeTool("amethyst_pickaxe",
				GameHelperCoreModule.T_AMETHYST);
		//
		GameHelperCoreModule.EMERALD_AXE = new ItemGHAxeTool("emerald_axe", GameHelperCoreModule.T_EMERALD);
		GameHelperCoreModule.SAPPHIRE_AXE = new ItemGHAxeTool("sapphire_axe", GameHelperCoreModule.T_SAPPHIRE);
		GameHelperCoreModule.RUBY_AXE = new ItemGHAxeTool("ruby_axe", GameHelperCoreModule.T_RUBY);
		GameHelperCoreModule.AMETHYST_AXE = new ItemGHAxeTool("amethyst_axe", GameHelperCoreModule.T_AMETHYST);
		//
		GameHelperCoreModule.EMERALD_SHOVEL = new ItemGHShovelTool("emerald_shovel", GameHelperCoreModule.T_EMERALD);
		GameHelperCoreModule.SAPPHIRE_SHOVEL = new ItemGHShovelTool("sapphire_shovel", GameHelperCoreModule.T_SAPPHIRE);
		GameHelperCoreModule.RUBY_SHOVEL = new ItemGHShovelTool("ruby_shovel", GameHelperCoreModule.T_RUBY);
		GameHelperCoreModule.AMETHYST_SHOVEL = new ItemGHShovelTool("amethyst_shovel", GameHelperCoreModule.T_AMETHYST);
		//
		GameHelperCoreModule.EMERALD_HOE = new ItemGHHoeTool("emerald_hoe", GameHelperCoreModule.T_EMERALD);
		GameHelperCoreModule.SAPPHIRE_HOE = new ItemGHHoeTool("sapphire_hoe", GameHelperCoreModule.T_SAPPHIRE);
		GameHelperCoreModule.RUBY_HOE = new ItemGHHoeTool("ruby_hoe", GameHelperCoreModule.T_RUBY);
		GameHelperCoreModule.AMETHYST_HOE = new ItemGHHoeTool("amethyst_hoe", GameHelperCoreModule.T_AMETHYST);

		GameHelperCoreModule.RUBY.customCraftingMaterial = GameHelperCoreModule.RUBY_ITEM;
		GameHelperCoreModule.EMERALD.customCraftingMaterial = Items.emerald;
		GameHelperCoreModule.T_SAPPHIRE.setRepairItem(new ItemStack(GameHelperCoreModule.SAPPHIRE_ITEM));
		GameHelperCoreModule.T_EMERALD.setRepairItem(new ItemStack(Items.emerald));
		GameHelperCoreModule.T_RUBY.setRepairItem(new ItemStack(GameHelperCoreModule.RUBY_ITEM));
		GameHelperCoreModule.T_AMETHYST.setRepairItem(new ItemStack(GameHelperCoreModule.AMETHYST_ITEM));
		GameHelperCoreModule.BIG_SAPPHIRE.setRepairItem(new ItemStack(GameHelperCoreModule.SAPPHIRE_BLOCK));
		GameHelperCoreModule.BIG_EMERALD.setRepairItem(new ItemStack(Blocks.emerald_block));
		GameHelperCoreModule.BIG_RUBY
				.setRepairItem(new ItemStack(Item.getItemFromBlock(GameHelperCoreModule.RUBY_BLOCK)));
		GameHelperCoreModule.BIG_WOOD.setRepairItem(new ItemStack(Item.getItemFromBlock(Blocks.crafting_table)));
		GameHelperCoreModule.BIG_GOLD.setRepairItem(new ItemStack(Item.getItemFromBlock(Blocks.gold_block)));
		GameHelperCoreModule.BIG_STONE.setRepairItem(new ItemStack(Item.getItemFromBlock(Blocks.furnace)));
		GameHelperCoreModule.BIG_IRON.setRepairItem(new ItemStack(Item.getItemFromBlock(Blocks.iron_block)));
		GameHelperCoreModule.BIG_DIAMOND.setRepairItem(new ItemStack(Item.getItemFromBlock(Blocks.diamond_block)));
		GameHelperCoreModule.SAPPHIRE_ORE = new SapphireOre();
		GameHelperCoreModule.RUBY_ORE = new RubyOre();
		GameHelperCoreModule.SAPPHIRE_BLOCK = new SapphireBlock();
		GameHelperCoreModule.RUBY_BLOCK = new RubyBlock();
		GameHelperCoreModule.AMETHYST_BLOCK = new AmethystBlock();
		GameHelperCoreModule.AMETHYST_ORE = new AmethystOre();
		GameHelperCoreModule.TELESCOPE = new ItemTelescope();
		GameHelperCoreModule.TRADE_ITEM = new GameHelperModItem("trade_item");
		TradeItems.instanciateAll();
		GameHelperCoreModule.SEC_CHEST = new ProtectedChestTileEntityBlock();
		GameHelperCoreModule.SEC_FURNACE = new ProtectedFurnaceBlock(false);
		GameHelperCoreModule.SEC_FURNACE_LIT = new ProtectedFurnaceBlock(true);
		GameHelperCoreModule.DIMENSION_BLOCK = new DimensionBlock();
		GameHelperCoreModule.IRON_DAGGER = new IronDagger();
		GameHelperCoreModule.MINERS_LAMP = new MinersHelmetLight();
		GameHelperCoreModule.MINERS_BACKPACK = new MinersBackpack();
		GameHelperCoreModule.MINERS_TOOLS_BELT = new MinersToolsBelt();
		GameHelperCoreModule.MINERS_DAGGER = new MinersDagger();
		GameHelperCoreModule.MARBLE = new GameHelperModBlock(Material.rock, "marble");
		GameHelperCoreModule.MARBLE_BRICK = new GameHelperModBlock(Material.rock, "marble_brick");
		GameHelperCoreModule.BASALT = new GameHelperModBlock(Material.rock, "basalt");
		GameHelperCoreModule.BASALT_BRICK = new GameHelperModBlock(Material.rock, "basalt_brick");
		GameHelperCoreModule.BASALT_COBBLE = new GameHelperModBlock(Material.rock, "basalt_cobblestone");
		GameRegistry.registerWorldGenerator(new GHWorldGenerator(), 0);
		// VillagerRegistrar.registerVillagerProfessions();
		Flowers.addFlowers();
		GameHelperCoreModule.MOBILE_WORKDBENCH = new MobileWorkbenchItem();
		GameHelperCoreModule.POTION_BOW = new PotionBow();
		GameHelperCoreModule.SEL_1 = new SelectionTool();
		GameHelperCoreModule.Pureblue = new Pureblue();
		GameHelperCoreModule.Purebrown = new Purebrown();
		GameHelperCoreModule.Purelightgray = new Purelightgray();
		GameHelperCoreModule.Purelime = new Purelime();
		GameHelperCoreModule.Pureyellow = new Pureyellow();
		GameHelperCoreModule.Purelightblue = new Purelightblue();
		GameHelperCoreModule.Puregray = new Puregray();
		GameHelperCoreModule.Puregreen = new Puregreen();
		GameHelperCoreModule.Puremagenta = new Puremagenta();
		GameHelperCoreModule.Pureorange = new Pureorange();
		GameHelperCoreModule.Purepink = new Purepink();
		GameHelperCoreModule.Purered = new Purered();
		GameHelperCoreModule.Pureblack = new Pureblack();
		GameHelperCoreModule.Purecyan = new Purecyan();
		GameHelperCoreModule.Purepurple = new Purepurple();
		GameHelperCoreModule.Purewhite = new Purewhite();
		GameHelperCoreModule.Pureglasblack = new Pureglasblack();
		GameHelperCoreModule.Pureglasblue = new Pureglasblue();
		GameHelperCoreModule.Pureglasbrown = new Pureglasbrown();
		GameHelperCoreModule.Pureglascyan = new Pureglascyan();
		GameHelperCoreModule.Pureglasgray = new Pureglasgray();
		GameHelperCoreModule.Pureglasgreen = new Pureglasgreen();
		GameHelperCoreModule.Pureglaslightblue = new Pureglaslightblue();
		GameHelperCoreModule.Pureglaslime = new Pureglaslime();
		GameHelperCoreModule.Pureglasmagenta = new Pureglasmagenta();
		GameHelperCoreModule.Pureglasorange = new Pureglasorange();
		GameHelperCoreModule.Pureglaspink = new Pureglaspink();
		GameHelperCoreModule.Pureglaspurple = new Pureglaspurple();
		GameHelperCoreModule.Pureglasred = new Pureglasred();
		GameHelperCoreModule.Pureglassilver = new Pureglassilver();
		GameHelperCoreModule.Pureglasyellow = new Pureglasyellow();
		GameHelperCoreModule.Pureglas = new Pureglas();
		GameHelperCoreModule.Neonglassilver = new Neonglassilver();
		GameHelperCoreModule.Neonglaswhite = new Neonglaswhite();
		GameHelperCoreModule.Neonglasblack = new Neonglasblack();
		GameHelperCoreModule.Neonglaspink = new Neonglaspink();
		GameHelperCoreModule.Neonglasorange = new Neonglasorange();
		GameHelperCoreModule.Neonglasmagenta = new Neonglasmagenta();
		GameHelperCoreModule.Neonglaspurple = new Neonglaspurple();
		GameHelperCoreModule.Neonglasred = new Neonglasred();
		GameHelperCoreModule.Neonglasgreen = new Neonglasgreen();
		GameHelperCoreModule.Neonglasgray = new Neonglasgray();
		GameHelperCoreModule.Neonglasyellow = new Neonglasyellow();
		GameHelperCoreModule.Neonglascyan = new Neonglascyan();
		GameHelperCoreModule.Neonglasbrown = new Neonglasbrown();
		GameHelperCoreModule.Neonglasblue = new Neonglasblue();
		GameHelperCoreModule.Neonglaslightblue = new Neonglaslightblue();
		GameHelperCoreModule.Neonglaslime = new Neonglaslime();
		GameHelperCoreModule.Neonglas = new Neonglas();
		GameHelperCoreModule.No_texture_block = new No_texture_block();
		GameHelperCoreModule.Tileblue = new Tileblue();
		GameHelperCoreModule.Tilewhite = new Tilewhite();
		GameHelperCoreModule.Tileblack = new Tileblack();
		GameHelperCoreModule.Tilered = new Tilered();
		GameHelperCoreModule.Tilebrown = new Tilebrown();
		GameHelperCoreModule.Tileyellow = new Tileyellow();
		GameHelperCoreModule.Tileorange = new Tileorange();
		GameHelperCoreModule.Tilegray = new Tilegray();
		GameHelperCoreModule.Tilegreen = new Tilegreen();
		GameHelperCoreModule.Tilepurple = new Tilepurple();
		GameHelperCoreModule.Tilepink = new Tilepink();
		GameHelperCoreModule.Tilecyan = new Tilecyan();
		GameHelperCoreModule.Tilemagenta = new Tilemagenta();
		GameHelperCoreModule.SEL_2 = new SelectionTool2();
		GameHelperCoreModule.THERMAL_ALLR_HELMET = new GameHelperThermalArmorHelmetItem();
		GameHelperCoreModule.THERMAL_ALLR_CHEST = new GameHelperThermalArmorChestplateItem();
		GameHelperCoreModule.THERMAL_ALLR_LEGGINGS = new GameHelperThermalArmorLeggingsItem();
		GameHelperCoreModule.THERMAL_ALLR_BOOTS = new GameHelperThermalArmorBootsItem();
	}

	public void registerEntities() {
		registerEntity(ThrowableDagger.class, "throwable_dagger", 0);
		registerEntity(GHVillager.class, "gh_villager", 1);
		registerEntity(EntityShotPotion.class, "gh_shot_potion", 2);
	}

	private static void registerEntity(Class<? extends Entity> entityClass, String name, int id) {
		// int entityID = EntityRegistry.findGlobalUniqueEntityId();
		// EntityRegistry.registerGlobalEntityID(entityClass, name, entityID);
		EntityRegistry.registerModEntity(entityClass, name, id, GameHelper.instance, 64, 1, true);
	}

	public void serverStart(FMLServerStartingEvent fmlsse) {
		System.out.println("The server is now starting and just fired the FMLServerStartingEvent");
		GameHelperServer.loadCommand(fmlsse);
		GameHelper.getUtils().startRankSystem();
		// fmlsse.registerServerCommand(new CommandCreateWorld());
	}

	public void addTE() {
		GameRegistry.registerTileEntity(ProtectedChestTileEntity.class, "protected_chest");
		GameRegistry.registerTileEntity(TileEntityEnchantmentAltar.class, "enchantment_altar");
		GameRegistry.registerTileEntity(TileEntityInstantEnchantmentTable.class, "instant_enchantment_table");
		GameRegistry.registerTileEntity(TileEntityUnchantmentTable.class, "unchantment_table");
		GameRegistry.registerTileEntity(ProtectedFurnaceTileEntity.class, "protected_furnace");
	}

	/**
	 * This Event should be used to add new villagers to the
	 * {@link net.wfoas.gh.villager.VillagerRegistrar}
	 */
	public void load(FMLInitializationEvent event, GameHelper gh) {
		FMLLog.log(Level.INFO, "[GameHelper] Loading GameHelper-Mod!");
		GameHelper.TITAN_MODULE.loadServer(event);
		GameHelper.LUCKY_MODULE.initServer(event);
		VillagerRegistrar.loadBuiltInVillagerProfessions();
		registerEntities();
		RecipeManager.addAll();
		addTE();
		// GameRegistry.registerTileEntity(SecChestTileEntity.class,
		// "secChest");
		NetworkRegistry.INSTANCE.registerGuiHandler(GameHelper.instance, new GuiHandler());
	}

	public void postInit(FMLPostInitializationEvent event, GameHelper gh) {
		GameHelper.TITAN_MODULE.postInitServer(event);
		GameHelper.LUCKY_MODULE.postInitServer(event);
	}
}