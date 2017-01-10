package net.wfoas.gh.flowers;

import net.minecraft.creativetab.CreativeTabs;

public class Flowers {
	public static GHModFlower Rose, Paeonia_suffruticosa;

	public static void addFlowers() {
		Rose = new GHModFlower("rose");
		Paeonia_suffruticosa = new GHModFlower("paeonia_suffruticosa");
	}

	public static void updateInitFlowers(CreativeTabs tab) {
		Rose.updateInitEvent(tab);
		Paeonia_suffruticosa.updateInitEvent(tab);
	}
}
