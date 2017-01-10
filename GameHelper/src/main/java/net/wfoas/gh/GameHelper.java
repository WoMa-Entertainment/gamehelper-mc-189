package net.wfoas.gh;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.Logger;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.wfoas.gh.config.GHConfig;
import net.wfoas.gh.config.IGHConfigDefault;
import net.wfoas.gh.dropsapi.pdr.ChatColor;
import net.wfoas.gh.glstatemanagerutils.GLStateManagerUtils;
import net.wfoas.gh.init.GameHelperInitialisationSteps;
import net.wfoas.gh.luckyblocksmodule.LuckyBlocksModule;
import net.wfoas.gh.multipleworlds.storage.GHWorldManager;
import net.wfoas.gh.proxies.CommonProxy;
import net.wfoas.gh.scheduler.GHScheduler;
import net.wfoas.gh.shitmodule.ShitModule;
import net.wfoas.gh.titanmodule.TitanModule;

@Mod(modid = GameHelper.MODID, name = GameHelper.MODNAME, version = GameHelper.MODVER)
public class GameHelper {
	public static final Charset UTF_8;

	static {
		UTF_8 = Charset.forName("UTF-8");
	}

	public static final String PREFIX = ChatColor.GRAY + "[" + ChatColor.BLUE + "W" + ChatColor.RED + "F"
			+ ChatColor.YELLOW + "O" + ChatColor.GREEN + "A" + ChatColor.DARK_PURPLE + "S" + ChatColor.GRAY + "]"
			+ ChatColor.AQUA + " ";
	public static final String PREFIX_ERROR = PREFIX + ChatColor.RED;
	public static final String PREFIX_SUCCESS = PREFIX + ChatColor.GREEN;
	public static final String PREFIX_PENDING = PREFIX + ChatColor.YELLOW;

	public static Side EVENT_SIDE;

	@SidedProxy(clientSide = "net.wfoas.gh.proxies.ClientProxy", serverSide = "net.wfoas.gh.proxies.CommonProxy")
	public static CommonProxy proxy;
	public static final String MODID = "gamehelper";
	public static final String MODNAME = "GameHelper-Mod";
	public static final String MODVER = "1.2";
	public static final String MOD_USE_NAME = "GameHelper";
	public static final String MINECRAFT = "Minecraft";

	public static final String STABLE_BUILD = ChatColor.GREEN + "Stable";
	public static final String BETA_BUILD = ChatColor.YELLOW + "Beta";
	public static final String DEV_BUILD = ChatColor.RED + "Dev";

	public static final Random MOD_RANDOM = new Random();

	public static TitanModule TITAN_MODULE;
	public static LuckyBlocksModule LUCKY_MODULE;
	public static ShitModule SHIT_MODULE;
	public static GameHelperCoreModule GH_MODULE;

	public static Logger logger;

	public File cfgDataFolder;
	public File structuresDirCl, structuresDirSv, structuresDir;
	public GHConfig CONFIG;
	public IGHConfigDefault defaultConfig;

	public GameHelper() {
		GH_MODULE = new GameHelperCoreModule();
		TITAN_MODULE = new TitanModule();
		LUCKY_MODULE = new LuckyBlocksModule();
		SHIT_MODULE = new ShitModule();
	}

	public static String getBuild() {
		return BETA_BUILD;
	}

	@Instance(value = GameHelper.MODID)
	public static GameHelper instance;

	public static void println(Object o) {
		System.out.println(o);
	}

	public GHScheduler ghscheduler;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		GameHelperInitialisationSteps.preInit(event, proxy);
	}

	@EventHandler
	public void serverStop(FMLServerStoppingEvent event) {
		GameHelperServer.serverStop(event, proxy);
	}

	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		GameHelperServer.serverStart(event, proxy);
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		GameHelperInitialisationSteps.initLoad(event, proxy);
	}

	public static File getDataConfigFolder() {
		return instance.cfgDataFolder;
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event, this);
		GHWorldManager.loadWorldTypes();
		//System.out.println("Thread: " + Thread.currentThread().getName());
	}

	public static GHScheduler getScheduler() {
		return instance.ghscheduler;
	}

	public static GameHelperUtils getUtils() {
		return GameHelperUtils.instance;
	}

	public static Logger getLogger() {
		return logger;
	}
}