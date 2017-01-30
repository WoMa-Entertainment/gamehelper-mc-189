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
import net.wfoas.gh.oredict.OredictEntries;
import net.wfoas.gh.protected_blocks.ProtectedBlocksRegistry;
import net.wfoas.gh.protected_blocks.chest.ProtectedChestTileEntityBlock;
import net.wfoas.gh.protected_blocks.furnace.ProtectedFurnaceBlock;
import net.wfoas.gh.proxies.CommonProxy;
import net.wfoas.gh.scheduler.GHSchedulerClient;
import net.wfoas.gh.scheduler.GHSchedulerServer;

public class GameHelperInitialisationSteps {
	public static void preInit(FMLPreInitializationEvent pre, CommonProxy proxy) {
		if (pre.getSide() == Side.CLIENT) {
			GameHelper.instance.structuresDirCl = new File(Minecraft.getMinecraft().mcDataDir, "structures_gh");
			GameHelper.instance.structuresDir = GameHelper.instance.structuresDirCl;
		} else {
			GameHelper.instance.structuresDirSv = new File(MinecraftServer.getServer().getDataDirectory(),
					"structures_gh");
			GameHelper.instance.structuresDir = GameHelper.instance.structuresDirSv;
		}
		GameHelper.instance.structuresDir.mkdirs();
		GameHelper.logger = pre.getModLog();
		GameHelper.EVENT_SIDE = pre.getSide();
		NetworkHandler.preInit();
		if (GameHelper.EVENT_SIDE == Side.CLIENT)
			GameHelper.instance.ghscheduler = new GHSchedulerClient();
		else
			GameHelper.instance.ghscheduler = new GHSchedulerServer();

		proxy.preInit(pre, GameHelper.instance);
	}

	public static void initLoad(FMLInitializationEvent init, CommonProxy proxy) {
		proxy.load(init, GameHelper.instance);
		GameHelperCoreModule.setupEnchLists();
		OredictEntries.injectEntriesIntoOreDict();
		ProtectedBlocksRegistry.addBlock((ProtectedChestTileEntityBlock) GameHelperCoreModule.SEC_CHEST,
				GuiHandler.PROTECTED_CHEST);
		ProtectedBlocksRegistry.addBlock((ProtectedFurnaceBlock) GameHelperCoreModule.SEC_FURNACE_LIT,
				GuiHandler.PROTECTED_FURNACE);
		ProtectedBlocksRegistry.addBlock((ProtectedFurnaceBlock) GameHelperCoreModule.SEC_FURNACE,
				GuiHandler.PROTECTED_FURNACE);
	}

	public static void postInit(FMLPostInitializationEvent post, CommonProxy proxy) {
		proxy.postInit(post, GameHelper.instance);
	}
}