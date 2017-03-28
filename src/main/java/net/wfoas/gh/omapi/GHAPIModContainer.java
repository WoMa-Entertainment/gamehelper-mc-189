package net.wfoas.gh.omapi;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.wfoas.core.gh.GameHelperCore;
import net.wfoas.gh.dropsapi.pdr.ChatColor;
import net.wfoas.gh.scheduler.GHScheduler;

@Mod(modid = GHAPIModContainer.MODID, name = GHAPIModContainer.MODNAME, version = GHAPIModContainer.MODVER)
public class GHAPIModContainer {

	public static final String MODID = "gamehelper-api";
	public static final String MODNAME = "GameHelper-API";
	public static final String MODVER = "0.1.2-b";

	@SidedProxy(serverSide = "net.wfoas.gh.omapi.GHConstructAPIResolver", clientSide = "net.wfoas.gh.omapi.GHConstructAPIResolverCl")
	static GHConstructAPIResolver apiResolver;

	public static GameHelperAPI api;

	@Instance(value = GHAPIModContainer.MODID)
	public static GHAPIModContainer instance;

	public GHScheduler ghscheduler;

	public static GHScheduler getScheduler() {
		return instance.ghscheduler;
	}

	@EventHandler
	public void construct(FMLConstructionEvent event) {
		if (event.getSide() == Side.CLIENT) {
			api = apiResolver.construct(this);
			GHIntAPIHelper.setInitGHAPI(api);
		} else {
			api = apiResolver.construct(this);
			GHIntAPIHelper.setInitGHAPI(api);
		}
		GameHelperAPI.ghAPI().addBranding(
				ChatColor.AQUA + GameHelperCore.GH_COREMOD_NAME + " " + ChatColor.GREEN
						+ GameHelperCore.GH_COREMOD_VERSION,
				GameHelperCore.GH_COREMOD_NAME + " " + GameHelperCore.GH_COREMOD_VERSION);
		GameHelperAPI.ghAPI().addBranding(
				ChatColor.AQUA + GameHelperAPI.NAME + " " + ChatColor.GREEN + GameHelperAPI.VERSION,
				GameHelperAPI.NAME + " " + GameHelperAPI.VERSION);
	}
}