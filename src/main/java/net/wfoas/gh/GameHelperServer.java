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
import net.wfoas.gh.multipleworlds.storage.GHWorldManager;
import net.wfoas.gh.network.securedlogin.timeout.PlayerLoginTimeOut;
import net.wfoas.gh.notifysettings.NotifyTable;
import net.wfoas.gh.proxies.CommonProxy;
import net.wfoas.gh.selectiontool.CommandExportStruct;
import net.wfoas.gh.villager.VillagerRegistrar;

public class GameHelperServer {
	static List<CommandBase> commands;

	public static void serverStart(FMLServerStartingEvent event, CommonProxy proxy) {
		commands = new ArrayList<CommandBase>();
		proxy.serverStart(event);
		GHWorldManager.serverStart();
		VillagerRegistrar.sortIntoListOnServerStart();
		AdHandler.enableAdEcoSystem();
		NotifyTable.serverStartUp();
		System.out.println("start PlayerLoginTimeOut");
		PlayerLoginTimeOut.startLoginTimeOutQueueTask();
	}

	public static List<CommandBase> getCommand() {
		return commands;
	}

	public static void loadCommand(FMLServerStartingEvent fmlsse) {
		registerSingleCommand(new CommandCreateWorld(), fmlsse);
		registerSingleCommand(new CommandTpx(), fmlsse);
		registerSingleCommand(new CommandBuildFly(), fmlsse);
		registerSingleCommand(new CommandPing(), fmlsse);
		registerSingleCommand(new CommandSaveData(), fmlsse);
		registerSingleCommand(new CommandListWorld(), fmlsse);
		registerSingleCommand(new CommandTpxp(), fmlsse);
		registerSingleCommand(new CommandHackSec(), fmlsse);
		registerSingleCommand(new CommandGameHelper(), fmlsse);
		registerSingleCommand(new CommandOwnWorld(), fmlsse);
		registerSingleCommand(new PlayerRanksCommand(), fmlsse);
		registerSingleCommand(new CommandSetPerm(), fmlsse);
		registerSingleCommand(new CommandViewPerm(), fmlsse);
		registerSingleCommand(new CommandExportStruct(), fmlsse);
		registerSingleCommand(new CommandNoclip(), fmlsse);
		registerSingleCommand(new CommandToggleNotify(), fmlsse);
		registerSingleCommand(new CommandDbgScreenshotFolder(), fmlsse); // DEBUG
		registerSingleCommand(new CommandWKick(), fmlsse);
		registerSingleCommand(new CommandSound(), fmlsse);
	}

	public static void registerSingleCommand(CommandBase cb, FMLServerStartingEvent fmlsse) {
		fmlsse.registerServerCommand(cb);
		commands.add(cb);
	}

	public static void serverStop(FMLServerStoppingEvent event, CommonProxy proxy) {
		NotifyTable.serverStop();
		PlayerLoginTimeOut.stopServer();
		GameHelper.getUtils().stopRankSystem();
		GHWorldManager.serverStop();
	}
}