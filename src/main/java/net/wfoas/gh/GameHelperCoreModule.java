package net.wfoas.gh;

import java.io.File;

import org.apache.logging.log4j.Level;

import de.winston.develop.debug.CommandDbgScreenshotFolder;
import de.winston.network.playerranks.PlayerRanksCommand;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.wfoas.gh.ads.AdHandler;
import net.wfoas.gh.armor.GHAmethystArmor;
import net.wfoas.gh.armor.GHEmeraldArmor;
import net.wfoas.gh.armor.GHModItemArmor;
import net.wfoas.gh.armor.GHRubyArmor;
import net.wfoas.gh.armor.GHSaphirreArmor;
import net.wfoas.gh.bigsword.BigswordItem;
import net.wfoas.gh.blocks.AmethystBlock;
import net.wfoas.gh.blocks.AmethystOre;
import net.wfoas.gh.blocks.Fassade;
import net.wfoas.gh.blocks.GameHelperModBlock;
import net.wfoas.gh.blocks.GameHelperOrientedModBlock;
import net.wfoas.gh.blocks.IGHModBlock;
import net.wfoas.gh.blocks.No_texture_block;
import net.wfoas.gh.blocks.Platzhalter;
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
import net.wfoas.gh.blocks.Quicksand;
import net.wfoas.gh.blocks.RubyBlock;
import net.wfoas.gh.blocks.RubyOre;
import net.wfoas.gh.blocks.SapphireBlock;
import net.wfoas.gh.blocks.SapphireOre;
import net.wfoas.gh.blocks.Siegel;
import net.wfoas.gh.blocks.Steelblock;
import net.wfoas.gh.blocks.Steinsand1;
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
import net.wfoas.gh.blocks.Weg;
import net.wfoas.gh.blocks.exploding.ExplodingEndstone;
import net.wfoas.gh.blocks.exploding.ExplodingNetherrack;
import net.wfoas.gh.blocks.exploding.GameHelperExplodingStone;
import net.wfoas.gh.blocks.glass.Darkglas;
import net.wfoas.gh.blocks.glass.Darkglasblack;
import net.wfoas.gh.blocks.glass.Darkglasblue;
import net.wfoas.gh.blocks.glass.Darkglasbrown;
import net.wfoas.gh.blocks.glass.Darkglascyan;
import net.wfoas.gh.blocks.glass.Darkglasgray;
import net.wfoas.gh.blocks.glass.Darkglasgreen;
import net.wfoas.gh.blocks.glass.Darkglaslightblue;
import net.wfoas.gh.blocks.glass.Darkglaslime;
import net.wfoas.gh.blocks.glass.Darkglasmagenta;
import net.wfoas.gh.blocks.glass.Darkglasorange;
import net.wfoas.gh.blocks.glass.Darkglaspink;
import net.wfoas.gh.blocks.glass.Darkglaspurple;
import net.wfoas.gh.blocks.glass.Darkglasred;
import net.wfoas.gh.blocks.glass.Darkglassilver;
import net.wfoas.gh.blocks.glass.Darkglasyellow;
import net.wfoas.gh.blocks.glass.GameHelperModGlass;
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
import net.wfoas.gh.blocks.pressureplates.GameHelperModPressurePlate;
import net.wfoas.gh.commands.CommandBuildFly;
import net.wfoas.gh.commands.CommandCreateWorld;
import net.wfoas.gh.commands.CommandGameHelper;
import net.wfoas.gh.commands.CommandHackSec;
import net.wfoas.gh.commands.CommandListWorld;
import net.wfoas.gh.commands.CommandNoclip;
import net.wfoas.gh.commands.CommandOwnWorld;
import net.wfoas.gh.commands.CommandPing;
import net.wfoas.gh.commands.CommandSaveData;
import net.wfoas.gh.commands.CommandSetPerm;
import net.wfoas.gh.commands.CommandSound;
import net.wfoas.gh.commands.CommandToggleNotify;
import net.wfoas.gh.commands.CommandTpx;
import net.wfoas.gh.commands.CommandTpxp;
import net.wfoas.gh.commands.CommandViewPerm;
import net.wfoas.gh.commands.CommandWKick;
import net.wfoas.gh.commands.GHCommand;
import net.wfoas.gh.config.DefaultConfig;
import net.wfoas.gh.config.GHConfig;
import net.wfoas.gh.craftingresearchfacilities.CraftingResearchTable;
import net.wfoas.gh.creativetab.GameHelperTab;
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
import net.wfoas.gh.items.GameHelperModPotionBow;
import net.wfoas.gh.items.GameHelperModSword;
import net.wfoas.gh.items.ItemTelescope;
import net.wfoas.gh.items.MobileWorkbenchItem;
import net.wfoas.gh.items.PotionBow;
import net.wfoas.gh.items.SelectionTool;
import net.wfoas.gh.items.SelectionTool2;
import net.wfoas.gh.items.TradeItems;
import net.wfoas.gh.items.UltraBackpackItem;
import net.wfoas.gh.items.WorldTeleporterItem;
import net.wfoas.gh.items.dimension.DimensionShard;
import net.wfoas.gh.items.tools.ItemGHAxeTool;
import net.wfoas.gh.items.tools.ItemGHHoeTool;
import net.wfoas.gh.items.tools.ItemGHPickaxeTool;
import net.wfoas.gh.items.tools.ItemGHShovelTool;
import net.wfoas.gh.minersinventory.items.IronDagger;
import net.wfoas.gh.minersinventory.items.MinersBackpack;
import net.wfoas.gh.minersinventory.items.MinersDagger;
import net.wfoas.gh.minersinventory.items.MinersHelmetLight;
import net.wfoas.gh.minersinventory.items.MinersToolsBelt;
import net.wfoas.gh.multipleworlds.storage.GHWorldManager;
import net.wfoas.gh.network.securedlogin.timeout.PlayerLoginTimeOut;
import net.wfoas.gh.notifysettings.NotifyTable;
import net.wfoas.gh.omapi.GameHelperAPI;
import net.wfoas.gh.omapi.GameHelperAPIClientSide;
import net.wfoas.gh.omapi.module.GameHelperModuleAbstract;
import net.wfoas.gh.op_anvil.OPAnvil;
import net.wfoas.gh.potionbow.EntityShotPotion;
import net.wfoas.gh.protected_blocks.ProtectedBlocksRegistry;
import net.wfoas.gh.protected_blocks.chest.ProtectedChestTileEntity;
import net.wfoas.gh.protected_blocks.chest.ProtectedChestTileEntityBlock;
import net.wfoas.gh.protected_blocks.furnace.ProtectedFurnaceBlock;
import net.wfoas.gh.protected_blocks.furnace.ProtectedFurnaceTileEntity;
import net.wfoas.gh.proxies.CommonProxy;
import net.wfoas.gh.proxies.LogicalClientEnvironment;
import net.wfoas.gh.recipes.RecipeManager;
import net.wfoas.gh.selectiontool.CommandExportStruct;
import net.wfoas.gh.sound.SoundHandlerGH;
import net.wfoas.gh.survivaltabs.GHTabMinersInv;
import net.wfoas.gh.survivaltabs.GHThermalTab;
import net.wfoas.gh.thermalinventory.armor.GameHelperThermalArmorBootsItem;
import net.wfoas.gh.thermalinventory.armor.GameHelperThermalArmorChestplateItem;
import net.wfoas.gh.thermalinventory.armor.GameHelperThermalArmorHelmetItem;
import net.wfoas.gh.thermalinventory.armor.GameHelperThermalArmorLeggingsItem;
import net.wfoas.gh.unchant.TileEntityUnchantmentTable;
import net.wfoas.gh.unchant.UnchantmentTable;
import net.wfoas.gh.uncraftingtable.UncraftingTable;
import net.wfoas.gh.villager.VillagerRegistrar;
import net.wfoas.gh.villager.entity.GHVillager;
import net.wfoas.gh.worlddimensionsutils.DimensionBlock;
import net.wfoas.gh.worldgenerator.GHWorldGenerator;
import tconstruct.client.tabs.InventoryTabVanilla;

public class GameHelperCoreModule extends GameHelperModuleAbstract {
	@SidedProxy(clientSide = "net.wfoas.gh.proxies.ClientProxy", serverSide = "net.wfoas.gh.proxies.CommonProxy", modId = GameHelper.MODID)
	public static CommonProxy proxy;

	public static GameHelperModBlock Platzhalter;
	public static GameHelperModBlock Siegel;
	public static GameHelperModBlock Steelblock;
	public static GameHelperOrientedModBlock Weg;
	public static GameHelperModBlock Steinsand1;
	public static GameHelperModBlock Fassade;
	public static GameHelperModGlass Darkglas;
	public static GameHelperModGlass Darkglasblack;
	public static GameHelperModGlass Darkglasblue;
	public static GameHelperModGlass Darkglasbrown;
	public static GameHelperModGlass Darkglascyan;
	public static GameHelperModGlass Darkglasgray;
	public static GameHelperModGlass Darkglasgreen;
	public static GameHelperModGlass Darkglaslightblue;
	public static GameHelperModGlass Darkglaslime;
	public static GameHelperModGlass Darkglasmagenta;
	public static GameHelperModGlass Darkglasorange;
	public static GameHelperModGlass Darkglaspink;
	public static GameHelperModGlass Darkglaspurple;
	public static GameHelperModGlass Darkglassilver;
	public static GameHelperModGlass Darkglasyellow;
	public static GameHelperModGlass Darkglasred;
	public static GameHelperModBlock Tilemagenta;
	public static GameHelperModBlock Tilecyan;
	public static GameHelperModBlock Tilepink;
	public static GameHelperModBlock Tilepurple;
	public static GameHelperModBlock Tilegreen;
	public static GameHelperModBlock Tilegray;
	public static GameHelperModBlock Tileorange;
	public static GameHelperModBlock Tileyellow;
	public static GameHelperModBlock Tilebrown;
	public static GameHelperModBlock Tilered;
	public static GameHelperModBlock Tileblack;
	public static GameHelperModBlock Tilewhite;
	public static GameHelperModBlock Tileblue;
	public static GameHelperModGlass Neonglassilver;
	public static GameHelperModGlass Neonglaswhite;
	public static GameHelperModGlass Neonglasblack;
	public static GameHelperModGlass Neonglaspink;
	public static GameHelperModGlass Neonglasorange;
	public static GameHelperModGlass Neonglasmagenta;
	public static GameHelperModGlass Neonglaspurple;
	public static GameHelperModGlass Neonglasred;
	public static GameHelperModGlass Neonglasgreen;
	public static GameHelperModGlass Neonglasgray;
	public static GameHelperModGlass Neonglasyellow;
	public static GameHelperModGlass Neonglascyan;
	public static GameHelperModGlass Neonglasbrown;
	public static GameHelperModGlass Neonglasblue;
	public static GameHelperModGlass Neonglaslightblue;
	public static GameHelperModGlass Neonglaslime;
	public static GameHelperModGlass Neonglas;
	public static GameHelperModBlock No_texture_block;
	public static GameHelperModGlass Pureglas;
	public static GameHelperModGlass Pureglasyellow;
	public static GameHelperModGlass Pureglassilver;
	public static GameHelperModGlass Pureglasred;
	public static GameHelperModGlass Pureglaspurple;
	public static GameHelperModGlass Pureglaspink;
	public static GameHelperModGlass Pureglasorange;
	public static GameHelperModGlass Pureglasmagenta;
	public static GameHelperModGlass Pureglaslime;
	public static GameHelperModGlass Pureglaslightblue;
	public static GameHelperModGlass Pureglasgray;
	public static GameHelperModGlass Pureglasgreen;
	public static GameHelperModGlass Pureglascyan;
	public static GameHelperModGlass Pureglasbrown;
	public static GameHelperModGlass Pureglasblue;
	public static GameHelperModGlass Pureglasblack;
	public static GameHelperModBlock Purewhite;
	public static GameHelperModBlock Purepurple;
	public static GameHelperModBlock Purecyan;
	public static GameHelperModBlock Pureblack;
	public static GameHelperModBlock Purered;
	public static GameHelperModBlock Purepink;
	public static GameHelperModBlock Pureorange;
	public static GameHelperModBlock Puremagenta;
	public static GameHelperModBlock Puregreen;
	public static GameHelperModBlock Puregray;
	public static GameHelperModBlock Purelightblue;
	public static GameHelperModBlock Pureyellow;
	public static GameHelperModBlock Purelime;
	public static GameHelperModBlock Purelightgray;
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

	public static GameHelperModItem DIMENSION_SHARD;

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

	public static GameHelperModItem THERMAL_ALLR_HELMET, THERMAL_ALLR_CHEST, THERMAL_ALLR_LEGGINGS, THERMAL_ALLR_BOOTS;

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

	public static CraftingResearchTable CRAFTING_RESEARCH_TABLE;

	public static Quicksand quicksand;

	public static GameHelperExplodingStone exp_netherrack, exp_endstone;

	public static UncraftingTable UNCRAFTING_TABLE;

	@Override
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
		SEC_FURNACE_LIT.updateInitEvent(TAB_GAMEHELPER);
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
		Purelightgray.updateInitEvent(TAB_GAMEHELPER);
		Purelime.updateInitEvent(TAB_GAMEHELPER);
		Pureyellow.updateInitEvent(TAB_GAMEHELPER);
		Purelightblue.updateInitEvent(TAB_GAMEHELPER);
		Puregray.updateInitEvent(TAB_GAMEHELPER);
		Puregreen.updateInitEvent(TAB_GAMEHELPER);
		Puremagenta.updateInitEvent(TAB_GAMEHELPER);
		Pureorange.updateInitEvent(TAB_GAMEHELPER);
		Purepink.updateInitEvent(TAB_GAMEHELPER);
		Purered.updateInitEvent(TAB_GAMEHELPER);
		Pureblack.updateInitEvent(TAB_GAMEHELPER);
		Purecyan.updateInitEvent(TAB_GAMEHELPER);
		Purepurple.updateInitEvent(TAB_GAMEHELPER);
		Purewhite.updateInitEvent(TAB_GAMEHELPER);
		Pureglasblack.updateInitEvent(TAB_GAMEHELPER);
		Pureglasblue.updateInitEvent(TAB_GAMEHELPER);
		Pureglasbrown.updateInitEvent(TAB_GAMEHELPER);
		Pureglascyan.updateInitEvent(TAB_GAMEHELPER);
		Pureglasgray.updateInitEvent(TAB_GAMEHELPER);
		Pureglasgreen.updateInitEvent(TAB_GAMEHELPER);
		Pureglaslightblue.updateInitEvent(TAB_GAMEHELPER);
		Pureglaslime.updateInitEvent(TAB_GAMEHELPER);
		Pureglasmagenta.updateInitEvent(TAB_GAMEHELPER);
		Pureglasorange.updateInitEvent(TAB_GAMEHELPER);
		Pureglaspink.updateInitEvent(TAB_GAMEHELPER);
		Pureglaspurple.updateInitEvent(TAB_GAMEHELPER);
		Pureglasred.updateInitEvent(TAB_GAMEHELPER);
		Pureglassilver.updateInitEvent(TAB_GAMEHELPER);
		Pureglasyellow.updateInitEvent(TAB_GAMEHELPER);
		Pureglas.updateInitEvent(TAB_GAMEHELPER);
		Neonglassilver.updateInitEvent(TAB_GAMEHELPER);
		Neonglaswhite.updateInitEvent(TAB_GAMEHELPER);
		Neonglasblack.updateInitEvent(TAB_GAMEHELPER);
		Neonglaspink.updateInitEvent(TAB_GAMEHELPER);
		Neonglasorange.updateInitEvent(TAB_GAMEHELPER);
		Neonglasmagenta.updateInitEvent(TAB_GAMEHELPER);
		Neonglaspurple.updateInitEvent(TAB_GAMEHELPER);
		Neonglasred.updateInitEvent(TAB_GAMEHELPER);
		Neonglasgreen.updateInitEvent(TAB_GAMEHELPER);
		Neonglasgray.updateInitEvent(TAB_GAMEHELPER);
		Neonglasyellow.updateInitEvent(TAB_GAMEHELPER);
		Neonglascyan.updateInitEvent(TAB_GAMEHELPER);
		Neonglasbrown.updateInitEvent(TAB_GAMEHELPER);
		Neonglasblue.updateInitEvent(TAB_GAMEHELPER);
		Neonglaslightblue.updateInitEvent(TAB_GAMEHELPER);
		Neonglaslime.updateInitEvent(TAB_GAMEHELPER);
		Neonglas.updateInitEvent(TAB_GAMEHELPER);
		No_texture_block.updateInitEvent(TAB_GAMEHELPER);
		Tileblue.updateInitEvent(TAB_GAMEHELPER);
		Tilewhite.updateInitEvent(TAB_GAMEHELPER);
		Tileblack.updateInitEvent(TAB_GAMEHELPER);
		Tilered.updateInitEvent(TAB_GAMEHELPER);
		Tilebrown.updateInitEvent(TAB_GAMEHELPER);
		Tileyellow.updateInitEvent(TAB_GAMEHELPER);
		Tileorange.updateInitEvent(TAB_GAMEHELPER);
		Tilegray.updateInitEvent(TAB_GAMEHELPER);
		Tilegreen.updateInitEvent(TAB_GAMEHELPER);
		Tilepurple.updateInitEvent(TAB_GAMEHELPER);
		Tilepink.updateInitEvent(TAB_GAMEHELPER);
		Tilecyan.updateInitEvent(TAB_GAMEHELPER);
		Tilemagenta.updateInitEvent(TAB_GAMEHELPER);
		SEL_2.updateInitEvent(TAB_GAMEHELPER);
		THERMAL_ALLR_HELMET.updateInitEvent(TAB_GAMEHELPER);
		THERMAL_ALLR_CHEST.updateInitEvent(TAB_GAMEHELPER);
		THERMAL_ALLR_LEGGINGS.updateInitEvent(TAB_GAMEHELPER);
		THERMAL_ALLR_BOOTS.updateInitEvent(TAB_GAMEHELPER);
		DIMENSION_SHARD.updateInitEvent(TAB_GAMEHELPER);
		quicksand.updateInitEvent(TAB_GAMEHELPER);
		exp_endstone.updateInitEvent(TAB_GAMEHELPER);
		exp_netherrack.updateInitEvent(TAB_GAMEHELPER);
		Darkglas.updateInitEvent(TAB_GAMEHELPER);
		Darkglasblack.updateInitEvent(TAB_GAMEHELPER);
		Darkglasblue.updateInitEvent(TAB_GAMEHELPER);
		Darkglasbrown.updateInitEvent(TAB_GAMEHELPER);
		Darkglascyan.updateInitEvent(TAB_GAMEHELPER);
		Darkglasgray.updateInitEvent(TAB_GAMEHELPER);
		Darkglasgreen.updateInitEvent(TAB_GAMEHELPER);
		Darkglaslightblue.updateInitEvent(TAB_GAMEHELPER);
		Darkglaslime.updateInitEvent(TAB_GAMEHELPER);
		Darkglasmagenta.updateInitEvent(TAB_GAMEHELPER);
		Darkglasorange.updateInitEvent(TAB_GAMEHELPER);
		Darkglaspink.updateInitEvent(TAB_GAMEHELPER);
		Darkglaspurple.updateInitEvent(TAB_GAMEHELPER);
		Darkglassilver.updateInitEvent(TAB_GAMEHELPER);
		Darkglasyellow.updateInitEvent(TAB_GAMEHELPER);
		Darkglasred.updateInitEvent(TAB_GAMEHELPER);
		UNCRAFTING_TABLE.updateInitEvent(TAB_GAMEHELPER);
		Platzhalter.updateInitEvent(TAB_GAMEHELPER);
		Siegel.updateInitEvent(TAB_GAMEHELPER);
		Steelblock.updateInitEvent(TAB_GAMEHELPER);
		Weg.updateInitEvent(TAB_GAMEHELPER);
		Fassade.updateInitEvent(TAB_GAMEHELPER);
		Steinsand1.updateInitEvent(TAB_GAMEHELPER);
		CRAFTING_RESEARCH_TABLE.updateInitEvent(TAB_GAMEHELPER);
	}

	@Override
	public void preInitServer(FMLPreInitializationEvent event) {
		SoundHandlerGH.init();
		SoundHandlerGH.addSound("wow");
		SoundHandlerGH.addSound("nice");
		SoundHandlerGH.addSound("ohyeah");
		if (event.getSide() == Side.CLIENT) {
			GameHelper.instance.structuresDirCl = new File(Minecraft.getMinecraft().mcDataDir, "structures_gh");
			GameHelper.instance.structuresDir = GameHelper.instance.structuresDirCl;
		} else {
			GameHelper.instance.structuresDirSv = new File(MinecraftServer.getServer().getDataDirectory(),
					"structures_gh");
			GameHelper.instance.structuresDir = GameHelper.instance.structuresDirSv;
		}
		GameHelper.instance.structuresDir.mkdirs();
		GameHelper.instance.cfgDataFolder = new File(event.getModConfigurationDirectory(), "gamehelper");
		GameHelper.instance.cfgDataFolder.mkdirs();
		GameHelper.instance.defaultConfig = new DefaultConfig();
		GameHelper.instance.CONFIG = new GHConfig(new File(GameHelper.instance.cfgDataFolder, "config.properties"));
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
		GameHelperCoreModule.CRAFTING_RESEARCH_TABLE = new CraftingResearchTable();
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
		GameHelperCoreModule.DIMENSION_SHARD = new DimensionShard();
		GameHelperCoreModule.quicksand = new Quicksand();
		GameHelperCoreModule.exp_endstone = new ExplodingEndstone();
		GameHelperCoreModule.exp_netherrack = new ExplodingNetherrack();
		GameHelperCoreModule.UNCRAFTING_TABLE = new UncraftingTable();
		GameHelperCoreModule.Darkglas = new Darkglas();
		GameHelperCoreModule.Darkglasblack = new Darkglasblack();
		GameHelperCoreModule.Darkglasblue = new Darkglasblue();
		GameHelperCoreModule.Darkglasbrown = new Darkglasbrown();
		GameHelperCoreModule.Darkglascyan = new Darkglascyan();
		GameHelperCoreModule.Darkglasgray = new Darkglasgray();
		GameHelperCoreModule.Darkglasgreen = new Darkglasgreen();
		GameHelperCoreModule.Darkglaslightblue = new Darkglaslightblue();
		GameHelperCoreModule.Darkglaslime = new Darkglaslime();
		GameHelperCoreModule.Darkglasmagenta = new Darkglasmagenta();
		GameHelperCoreModule.Darkglasorange = new Darkglasorange();
		GameHelperCoreModule.Darkglaspink = new Darkglaspink();
		GameHelperCoreModule.Darkglaspurple = new Darkglaspurple();
		GameHelperCoreModule.Darkglassilver = new Darkglassilver();
		GameHelperCoreModule.Darkglasyellow = new Darkglasyellow();
		GameHelperCoreModule.Darkglasred = new Darkglasred();
		GameHelperCoreModule.Platzhalter = new Platzhalter();
		GameHelperCoreModule.Siegel = new Siegel();
		GameHelperCoreModule.Steelblock = new Steelblock();
		GameHelperCoreModule.Weg = new Weg();
		GameHelperCoreModule.Steinsand1 = new Steinsand1();
		GameHelperCoreModule.Fassade = new Fassade();

		registerCommands();
		if (!(proxy instanceof LogicalClientEnvironment))
			proxy.preInit(event, GameHelper.instance);
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

	public void addTE() {
		GameRegistry.registerTileEntity(ProtectedChestTileEntity.class, "protected_chest");
		GameRegistry.registerTileEntity(TileEntityEnchantmentAltar.class, "enchantment_altar");
		GameRegistry.registerTileEntity(TileEntityInstantEnchantmentTable.class, "instant_enchantment_table");
		GameRegistry.registerTileEntity(TileEntityUnchantmentTable.class, "unchantment_table");
		GameRegistry.registerTileEntity(ProtectedFurnaceTileEntity.class, "protected_furnace");
	}

	@Override
	public void preInitClient(FMLPreInitializationEvent event) {
		GameHelperAPIClientSide clApi = (GameHelperAPIClientSide) GameHelperAPI.ghAPI();
		clApi.injectGHSurvivalTab(new InventoryTabVanilla());
		clApi.injectGHSurvivalTab(new GHTabMinersInv());
		clApi.injectGHSurvivalTab(new GHThermalTab());
		if (proxy instanceof LogicalClientEnvironment)
			proxy.preInit(event, GameHelper.instance);
	}

	@Override
	public void initServer(FMLInitializationEvent event) {
		FMLLog.log(Level.INFO, "[GameHelper] Loading GameHelper-Mod!");
		VillagerRegistrar.loadBuiltInVillagerProfessions();
		registerEntities();
		RecipeManager.addAll();
		addTE();
		if (!(proxy instanceof LogicalClientEnvironment))
			proxy.load(event, GameHelper.instance);
		// GameRegistry.registerTileEntity(SecChestTileEntity.class,
		// "secChest");
		ProtectedBlocksRegistry.addBlock(GuiHandler.PROTECTED_CHEST,
				(ProtectedChestTileEntityBlock) GameHelperCoreModule.SEC_CHEST);
		ProtectedBlocksRegistry.addBlock(GuiHandler.PROTECTED_FURNACE,
				(ProtectedFurnaceBlock) GameHelperCoreModule.SEC_FURNACE,
				(ProtectedFurnaceBlock) GameHelperCoreModule.SEC_FURNACE_LIT);
		NetworkRegistry.INSTANCE.registerGuiHandler(GameHelper.instance, new GuiHandler());
	}

	public void registerCommands() {
		registerSingleCommand(new CommandCreateWorld());
		registerSingleCommand(new CommandTpx());
		registerSingleCommand(new CommandBuildFly());
		registerSingleCommand(new CommandPing());
		registerSingleCommand(new CommandSaveData());
		registerSingleCommand(new CommandListWorld());
		registerSingleCommand(new CommandTpxp());
		registerSingleCommand(new CommandHackSec());
		registerSingleCommand(new CommandGameHelper());
		registerSingleCommand(new CommandOwnWorld());
		registerSingleCommand(new PlayerRanksCommand());
		registerSingleCommand(new CommandSetPerm());
		registerSingleCommand(new CommandViewPerm());
		registerSingleCommand(new CommandExportStruct());
		registerSingleCommand(new CommandNoclip());
		registerSingleCommand(new CommandToggleNotify());
		registerSingleCommand(new CommandDbgScreenshotFolder()); // DEBUG
		registerSingleCommand(new CommandWKick());
		registerSingleCommand(new CommandSound());
	}

	private void registerSingleCommand(GHCommand gh) {
		GameHelperAPI.ghAPI().ghRegisterCommand(gh);
	}

	@Override
	public void initClient(FMLInitializationEvent event) {
		registerTab();
		if (proxy instanceof LogicalClientEnvironment)
			proxy.load(event, GameHelper.instance);
	}

	@Override
	public void postInitServer(FMLPostInitializationEvent event) {
		GHWorldManager.loadWorldTypes();
		if (!(proxy instanceof LogicalClientEnvironment))
			proxy.postInit(event, GameHelper.instance);
	}

	@Override
	public void postInitClient(FMLPostInitializationEvent event) {
		if (proxy instanceof LogicalClientEnvironment)
			proxy.postInit(event, GameHelper.instance);
	}

	@Override
	public void serverStart(FMLServerStartingEvent fmlsse) {
		super.serverStart(fmlsse);
		System.out.println("The server is now starting and just fired the FMLServerStartingEvent");
		GameHelper.getUtils().startRankSystem();
		GHWorldManager.serverStart();
		VillagerRegistrar.sortIntoListOnServerStart();
		AdHandler.enableAdEcoSystem();
		NotifyTable.serverStartUp();
		System.out.println("start PlayerLoginTimeOut");
		PlayerLoginTimeOut.startLoginTimeOutQueueTask();
	}

	@Override
	public void serverStop(FMLServerStoppingEvent event) {
		super.serverStop(event);
		NotifyTable.serverStop();
		PlayerLoginTimeOut.stopServer();
		GameHelper.getUtils().stopRankSystem();
		GHWorldManager.serverStop();
	}
}