package net.wfoas.gh.init;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.GameHelperCoreModule;
import net.wfoas.gh.gui.GuiHandler;
import net.wfoas.gh.network.NetworkHandler;
import net.wfoas.gh.omapi.GHAPIModContainer;
import net.wfoas.gh.omapi.GHIntAPIHelper;
import net.wfoas.gh.omapi.GameHelperAPI;
import net.wfoas.gh.omapi.module.GameHelperModuleAbstract;
import net.wfoas.gh.oredict.OredictEntries;
import net.wfoas.gh.protected_blocks.ProtectedBlocksRegistry;
import net.wfoas.gh.protected_blocks.chest.ProtectedChestTileEntityBlock;
import net.wfoas.gh.protected_blocks.furnace.ProtectedFurnaceBlock;
import net.wfoas.gh.proxies.CommonProxy;
import net.wfoas.gh.scheduler.GHSchedulerClient;
import net.wfoas.gh.scheduler.GHSchedulerServer;
import net.wfoas.gh.survivaltabs.SurvivalTabsRegistry;

public class GameHelperInitialisationSteps {
	public static void preInit(FMLPreInitializationEvent pre) {
		GameHelper.logger = pre.getModLog();
		GameHelper.EVENT_SIDE = pre.getSide();
		NetworkHandler.preInit();
		if (GameHelper.EVENT_SIDE == Side.CLIENT) {
			GHAPIModContainer.instance.ghscheduler = new GHSchedulerClient();
		} else {
			GHAPIModContainer.instance.ghscheduler = new GHSchedulerServer();
		}
		for (GameHelperModuleAbstract module : GHIntAPIHelper.modules()) {
			module.preInitServer(pre);
		}
		if (GameHelper.EVENT_SIDE == Side.CLIENT) {
			for (GameHelperModuleAbstract module : GHIntAPIHelper.modules()) {
				module.preInitClient(pre);
			}
		}
	}

	public static void initLoad(FMLInitializationEvent init) {
		for (GameHelperModuleAbstract module : GHIntAPIHelper.modules()) {
			module.initServer(init);
		}
		if (GameHelper.EVENT_SIDE == Side.CLIENT) {
			SurvivalTabsRegistry.registerSurvivalTabs();
			for (GameHelperModuleAbstract module : GHIntAPIHelper.modules()) {
				module.initClient(init);
			}
		}
		GameHelperAPI.ghAPI().ghEnchantAPI().defaultSetup();
		GameHelperAPI.ghAPI().ghEnchantAPI().rebuildEnchantmentLists();
		GameHelperAPI.ghAPI().ghItemAPI().defaultSetupItemLists();
		GameHelperAPI.ghAPI().ghMinersInventoryAPI().defaultSetup();
		OredictEntries.injectEntriesIntoOreDict();
	}

	public static void postInit(FMLPostInitializationEvent post) {
		for (GameHelperModuleAbstract module : GHIntAPIHelper.modules()) {
			module.postInitServer(post);
		}
		if (GameHelper.EVENT_SIDE == Side.CLIENT) {
			for (GameHelperModuleAbstract module : GHIntAPIHelper.modules()) {
				module.postInitClient(post);
			}
		}
	}
}