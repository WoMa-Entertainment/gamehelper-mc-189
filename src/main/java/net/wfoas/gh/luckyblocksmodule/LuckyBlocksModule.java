package net.wfoas.gh.luckyblocksmodule;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.wfoas.gh.bigsword.BigswordItem;
import net.wfoas.gh.dropsapi.pdr.ChatColor;
import net.wfoas.gh.effectorbs.EffectOrb;
import net.wfoas.gh.effectorbs.EmptyOrb;
import net.wfoas.gh.effectorbs.FireOrb;
import net.wfoas.gh.effectorbs.FuriousOrb;
import net.wfoas.gh.effectorbs.HasteOrb;
import net.wfoas.gh.effectorbs.HealingOrb;
import net.wfoas.gh.effectorbs.JumpingOrb;
import net.wfoas.gh.effectorbs.PoisonOrb;
import net.wfoas.gh.effectorbs.WaterOrb;
import net.wfoas.gh.effectorbs.WindOrb;
import net.wfoas.gh.effectorbs.XRayOrb;
import net.wfoas.gh.items.GameHelperModItem;
import net.wfoas.gh.luckyblocksmodule.items.GoldCoinItem;
import net.wfoas.gh.luckyblocksmodule.items.RainbowGemItem;
import net.wfoas.gh.luckyblocksmodule.items.RainbowSwordItem;

public class LuckyBlocksModule {
	
	public static LuckyBlocksCreativeTab LUCKY_TAB;
	
	public static ToolMaterial RAINBOW_MATERIAL, BIG_RAINBOW;
	
	public static GameHelperModItem RAINBOW_GEM, GOLD_COIN;

	public static RainbowSwordItem RAINBOW_SWORD;
	
	public static BigswordItem RAINBOW_BS;
	
	public static RainbowBlock RAINBOW_BLOCK;
	
	public static EffectOrb EMPTY_ORB, FIRE_ORB, FURIOUS_ORB, HASTE_ORB, HEALING_ORB, JUMPING_ORB, POISON_ORB, WATER_ORB, WIND_ORB, XRAY_ORB;
	
	public static final String PREFIX = ChatColor.GRAY + "[" + ChatColor.GOLD + "LuckyBlocks" + ChatColor.GRAY + "] " + ChatColor.AQUA;
	
	public static LuckyBlock DEFAULT_LUCKY_BLOCK, BRUNNEN_DEFAULT_LUCKY_BLOCK;//iniz
	
	public void preInitServer(FMLPreInitializationEvent event){
		BIG_RAINBOW = EnumHelper.addToolMaterial("BIG_RAINBOW", 2, -1, 6.0F, 16.0F, 14);
		LuckyBlocksModule.RAINBOW_MATERIAL = EnumHelper.addToolMaterial("RAINBOW", 3, -1, 12.0f, 6.0f, 25);
		RAINBOW_GEM = new RainbowGemItem();
		GOLD_COIN = new GoldCoinItem();
		RAINBOW_SWORD = new RainbowSwordItem();
//		DropsCollector default_dc = Drop.searchDrops("DEFAULT");
		DEFAULT_LUCKY_BLOCK = new LuckyBlock("gh_lucky_block", null);
		BRUNNEN_DEFAULT_LUCKY_BLOCK = new LuckyBlock("gh_brunnen_lucky_block", null);
		RAINBOW_BLOCK = new RainbowBlock("rainbow_block");
		RAINBOW_BS = new BigswordItem("rainbow_Bigsword", BIG_RAINBOW);
		EMPTY_ORB = new EmptyOrb();
		FIRE_ORB = new FireOrb();
		FURIOUS_ORB = new FuriousOrb();
		HASTE_ORB = new HasteOrb();
		HEALING_ORB = new HealingOrb();
		JUMPING_ORB = new JumpingOrb();
		POISON_ORB = new PoisonOrb();
		WATER_ORB = new WaterOrb();
		WIND_ORB = new WindOrb();
		XRAY_ORB = new XRayOrb();
	}
	
	public void preInitClient(FMLPreInitializationEvent event){
		
	}
	
	public void registerTab(){
		LUCKY_TAB = new LuckyBlocksCreativeTab();
		RAINBOW_GEM.updateInitEvent(LUCKY_TAB);
		GOLD_COIN.updateInitEvent(LUCKY_TAB);
		RAINBOW_SWORD.updateInitEvent(LUCKY_TAB);
		DEFAULT_LUCKY_BLOCK.updateInitEvent(LUCKY_TAB);
		BRUNNEN_DEFAULT_LUCKY_BLOCK.updateInitEvent(LUCKY_TAB);
		RAINBOW_BLOCK.updateInitEvent(LUCKY_TAB);
		RAINBOW_BS.updateInitEvent(LUCKY_TAB);
		EMPTY_ORB.updateInitEvent(LUCKY_TAB);
		FIRE_ORB.updateInitEvent(LUCKY_TAB);
		FURIOUS_ORB.updateInitEvent(LUCKY_TAB);
		HASTE_ORB.updateInitEvent(LUCKY_TAB);
		HEALING_ORB.updateInitEvent(LUCKY_TAB);
		JUMPING_ORB.updateInitEvent(LUCKY_TAB);
		POISON_ORB.updateInitEvent(LUCKY_TAB);
		WATER_ORB.updateInitEvent(LUCKY_TAB);
		WIND_ORB.updateInitEvent(LUCKY_TAB);
		XRAY_ORB.updateInitEvent(LUCKY_TAB);
		
	}
	
	public void registerRecipes(){
		GameRegistry.addShapelessRecipe(new ItemStack(RAINBOW_GEM), Items.emerald, Items.diamond, Items.gold_ingot, Items.iron_ingot, new ItemStack(Items.dye, 1, 4), Items.quartz, Items.redstone, Items.coal, Item.getItemFromBlock(Blocks.obsidian));
		GameRegistry.addShapedRecipe(new ItemStack(RAINBOW_SWORD), 
				"R",
				"R",
				"S", 
				'R', RAINBOW_GEM,
				'S', Items.stick);
		GameRegistry.addShapedRecipe(new ItemStack(DEFAULT_LUCKY_BLOCK), "GGG", "GSG", "GGG", 'G', Items.gold_ingot, 'S', Item.getItemFromBlock(Blocks.dropper));
		GameRegistry.addShapedRecipe(new ItemStack(RAINBOW_BLOCK), "RRR", "RRR", "RRR", 'R', RAINBOW_GEM);
		GameRegistry.addShapedRecipe(new ItemStack(RAINBOW_BS), "R", "R", "S", 'R', RAINBOW_BLOCK, 'S', Items.stick);
	}
	
	public void initServer(FMLInitializationEvent event){
		registerRecipes();
	}
	
	public void initClient(FMLInitializationEvent event){
		if(event.getSide() == Side.CLIENT){
			registerTab();
		}
	}
	
	public void postInitServer(FMLPostInitializationEvent event){
		
	}
	
	public void postInitClient(FMLPostInitializationEvent event){
		
	}

}
