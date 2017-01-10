package net.wfoas.gh.oredict;

import net.minecraftforge.oredict.OreDictionary;
import net.wfoas.gh.GameHelperCoreModule;

public class OredictEntries {
	public static void injectEntriesIntoOreDict() { // TODO add more
		OreDictionary.registerOre("oreRuby", GameHelperCoreModule.RUBY_ORE);
		OreDictionary.registerOre("oreSapphire", GameHelperCoreModule.SAPPHIRE_ORE);
	}
}
