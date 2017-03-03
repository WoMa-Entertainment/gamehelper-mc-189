package net.wfoas.gh.survivaltabs;

import net.wfoas.gh.omapi.GHIntAPIHelper;
import net.wfoas.gh.omapi.GameHelperAPI;
import tconstruct.client.tabs.InventoryTabVanilla;
import tconstruct.client.tabs.TabRegistry;

public class SurvivalTabsRegistry {
	public static void registerSurvivalTabs() {
		for (AbstractHintedSurvivalTab tab : GHIntAPIHelper.tabs()) {
			TabRegistry.registerTab(tab);
		}
	}
}