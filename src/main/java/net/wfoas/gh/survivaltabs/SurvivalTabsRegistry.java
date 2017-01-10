package net.wfoas.gh.survivaltabs;

import tconstruct.client.tabs.InventoryTabVanilla;
import tconstruct.client.tabs.TabRegistry;

public class SurvivalTabsRegistry {
	public static void registerSurvivalTabs() {
		TabRegistry.registerTab(new InventoryTabVanilla());
		TabRegistry.registerTab(new GHTabMinersInv());
		TabRegistry.registerTab(new GHThermalTab());
	}
}