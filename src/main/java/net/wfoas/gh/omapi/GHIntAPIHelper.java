package net.wfoas.gh.omapi;

import java.util.Collections;
import java.util.List;

import net.wfoas.gh.commands.GHCommand;
import net.wfoas.gh.omapi.module.GameHelperModuleAbstract;

public class GHIntAPIHelper {
	static GameHelperAPI api;

	public static void setInitGHAPI(GameHelperAPI gh_api) {
		if (gh_api != null) {
			api = gh_api;
		}
	}

	public static List<GameHelperModuleAbstract> modules() {
		return Collections.unmodifiableList(GameHelperAPI.ghAPI().modules);
	}

	public static List<GameHelperRunnableRegisterTab> tabrequests() {
		return Collections.unmodifiableList(GameHelperAPI.ghAPI().registerReqs);
	}

	public static List<GHCommand> commands() {
		return Collections.unmodifiableList(GameHelperAPI.ghAPI().gh_commands);
	}
}
