package net.wfoas.gh;

import java.util.ArrayList;
import java.util.List;

import de.winston.develop.debug.CommandDbgScreenshotFolder;
import de.winston.network.playerranks.PlayerRanksCommand;
import net.minecraft.command.CommandBase;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import net.wfoas.gh.ads.AdHandler;
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
import net.wfoas.gh.multipleworlds.storage.GHWorldManager;
import net.wfoas.gh.network.securedlogin.timeout.PlayerLoginTimeOut;
import net.wfoas.gh.notifysettings.NotifyTable;
import net.wfoas.gh.omapi.GHIntAPIHelper;
import net.wfoas.gh.omapi.GameHelperAPI;
import net.wfoas.gh.omapi.module.GameHelperModuleAbstract;
import net.wfoas.gh.proxies.CommonProxy;
import net.wfoas.gh.selectiontool.CommandExportStruct;
import net.wfoas.gh.villager.VillagerRegistrar;

public class GameHelperServer {
	static List<CommandBase> commands;

	public static void serverStart(FMLServerStartingEvent event) {
		commands = new ArrayList<CommandBase>();
		for (GameHelperModuleAbstract module : GHIntAPIHelper.modules()) {
			module.serverStart(event);
		}
		loadCommands(event);
	}

	public static List<CommandBase> getCommand() {
		return commands;
	}

	public static void loadCommands(FMLServerStartingEvent fmlsse) {
		for (GHCommand gh : GHIntAPIHelper.commands()) {
			registerSingleCommand(gh, fmlsse);
		}
	}

	public static void registerSingleCommand(GHCommand cb, FMLServerStartingEvent fmlsse) {
		fmlsse.registerServerCommand(cb);
		commands.add(cb);
	}

	public static void serverStop(FMLServerStoppingEvent event) {
		for (GameHelperModuleAbstract module : GHIntAPIHelper.modules()) {
			module.serverStop(event);
		}
	}
}