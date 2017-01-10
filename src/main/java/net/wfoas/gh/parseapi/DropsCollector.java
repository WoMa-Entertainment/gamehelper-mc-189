package net.wfoas.gh.parseapi;

import java.util.List;

import net.wfoas.gh.dropsapi.Drop;

public class DropsCollector {
	List<Drop> lucky, neutral, unlucky, brunnen;
	String luckyBlockName;

	public DropsCollector(String luckyBlockName, List<Drop> luckyDrops, List<Drop> neutralDrops,
			List<Drop> unluckyDrops, List<Drop> brunnenDrops) {
		lucky = luckyDrops;
		neutral = neutralDrops;
		unlucky = unluckyDrops;
		brunnen = brunnenDrops;
		this.luckyBlockName = luckyBlockName;
	}

	public List<Drop> getLucky() {
		return lucky;
	}

	public List<Drop> getNeutral() {
		return neutral;
	}

	public List<Drop> getUnlucky() {
		return unlucky;
	}

	public List<Drop> getBrunnen() {
		return brunnen;
	}

	public String getLuckyBlockName() {
		return luckyBlockName;
	}
}
