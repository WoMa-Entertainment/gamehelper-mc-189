package net.wfoas.gh.titanmodule;

import net.minecraft.creativetab.CreativeTabs;
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
import net.wfoas.gh.blocks.GameHelperModBlock;
import net.wfoas.gh.items.GameHelperModItem;
import net.wfoas.gh.items.GameHelperModSword;
import net.wfoas.gh.omapi.module.GameHelperModuleAbstract;
import net.wfoas.gh.titanmodule.blocks.CitrinOre;
import net.wfoas.gh.titanmodule.blocks.TitanOre;
import net.wfoas.gh.titanmodule.items.CitrinDust;
import net.wfoas.gh.titanmodule.items.CitrinIngot;
import net.wfoas.gh.titanmodule.items.CitrinSword;
import net.wfoas.gh.titanmodule.items.SwordHandle;
import net.wfoas.gh.titanmodule.items.TitanCrystal;
import net.wfoas.gh.titanmodule.items.TitanDust;
import net.wfoas.gh.titanmodule.items.TitanIngot;
import net.wfoas.gh.titanmodule.items.TitanSword;

public class TitanModule extends GameHelperModuleAbstract {

	public static GameHelperModItem TITAN_CRYSTAL, TITAN_INGOT, TITAN_DUST, SWORD_HANDLE, CITRIN_DUST, CITRIN_INGOT;

	public static GameHelperModSword TITAN_SWORD, CITRIN_SWORD;
	public static GameHelperModBlock TITAN_ORE, CITRIN_ORE;
	public static CreativeTabs TITAN_TAB;
	public static ToolMaterial TITAN_MATERIAL, CITRIN_MATERIAL, NETHER_MATERIAL;

	@Override
	public void registerTab() {
		TITAN_TAB = new TitanCreativeTab();
		TITAN_CRYSTAL.updateInitEvent(TITAN_TAB);
		TITAN_DUST.updateInitEvent(TITAN_TAB);
		TITAN_INGOT.updateInitEvent(TITAN_TAB);
		TITAN_SWORD.updateInitEvent(TITAN_TAB);
		TITAN_ORE.updateInitEvent(TITAN_TAB);
		SWORD_HANDLE.updateInitEvent(TITAN_TAB);
		CITRIN_DUST.updateInitEvent(TITAN_TAB);
		CITRIN_INGOT.updateInitEvent(TITAN_TAB);
		CITRIN_SWORD.updateInitEvent(TITAN_TAB);
		CITRIN_ORE.updateInitEvent(TITAN_TAB);
	}

	public void registerItemsAndBlocks() {
		TitanModule.TITAN_MATERIAL = EnumHelper.addToolMaterial("TITAN", 3, -1, 9.0f, 4.0f, 18);
		TitanModule.CITRIN_MATERIAL = EnumHelper.addToolMaterial("CITRIN", 3, -1, 9.0f, 2.0f, 14);
		TitanModule.TITAN_SWORD = new TitanSword();
		TitanModule.TITAN_CRYSTAL = new TitanCrystal();
		TitanModule.TITAN_INGOT = new TitanIngot();
		TitanModule.TITAN_DUST = new TitanDust();
		TitanModule.TITAN_ORE = new TitanOre();
		TitanModule.SWORD_HANDLE = new SwordHandle();
		TitanModule.CITRIN_DUST = new CitrinDust();
		TitanModule.CITRIN_INGOT = new CitrinIngot();
		TitanModule.CITRIN_SWORD = new CitrinSword();
		TitanModule.CITRIN_ORE = new CitrinOre();
	}

	// @EventHandler
	@Override
	public void preInitServer(FMLPreInitializationEvent event) {
		registerItemsAndBlocks();
	}

	@Override
	public void preInitClient(FMLPreInitializationEvent event) {

	}

	public void registerRecipes() {
		// TITAN_INGOT_NBT.setStackDisplayName("�cTitanbarren");
		GameRegistry.addShapedRecipe(new ItemStack(SWORD_HANDLE), "I", "I", 'I', Items.iron_ingot);
		GameRegistry.addShapedRecipe(new ItemStack(TITAN_SWORD), "T", "T", "I", 'T', TITAN_INGOT, 'I', SWORD_HANDLE);
		GameRegistry.addShapedRecipe(new ItemStack(CITRIN_SWORD), "T", "T", "I", 'T', CITRIN_INGOT, 'I', SWORD_HANDLE);
		GameRegistry.addShapelessRecipe(new ItemStack(TitanModule.TITAN_DUST), Items.diamond,
				Item.getItemFromBlock(Blocks.obsidian), TitanModule.TITAN_CRYSTAL);
		GameRegistry.addSmelting(TITAN_DUST, new ItemStack(TITAN_INGOT), 0.25f);
		GameRegistry.addSmelting(Item.getItemFromBlock(CITRIN_ORE), new ItemStack(CITRIN_INGOT), 0.25f);
	}

	@Override
	public void initServer(FMLInitializationEvent event) {
		registerRecipes();

	}

	@Override
	public void initClient(FMLInitializationEvent event) {
		if (event.getSide() == Side.CLIENT)
			registerTab();
	}

	@Override
	public void postInitServer(FMLPostInitializationEvent event) {
	}

	@Override
	public void postInitClient(FMLPostInitializationEvent event) {

	}
}