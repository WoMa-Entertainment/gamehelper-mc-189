package net.wfoas.gh.omapi;

import java.util.Collections;
import java.util.List;

import net.wfoas.gh.survivaltabs.AbstractHintedSurvivalTab;

public class GHIntAPIClientHelper {

	public static List<AbstractHintedSurvivalTab> tabs() {
		return Collections.unmodifiableList(GameHelperAPI.ghAPI().tabs());
	}
}
