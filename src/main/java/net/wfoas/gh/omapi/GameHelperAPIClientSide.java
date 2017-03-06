package net.wfoas.gh.omapi;

import java.util.ArrayList;
import java.util.List;

import net.wfoas.gh.GameHelper;
import net.wfoas.gh.survivaltabs.AbstractHintedSurvivalTab;

public class GameHelperAPIClientSide extends GameHelperAPI {

	public GameHelperAPIClientSide(GameHelper gh) {
		super(gh);
		tabs = new ArrayList<AbstractHintedSurvivalTab>();
	}

	List<AbstractHintedSurvivalTab> tabs;

	public void injectGHSurvivalTab(AbstractHintedSurvivalTab tab) {
		tabs.add(tab);
	}
}
