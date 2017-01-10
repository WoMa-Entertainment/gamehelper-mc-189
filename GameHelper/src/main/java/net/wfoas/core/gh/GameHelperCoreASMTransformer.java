package net.wfoas.core.gh;

import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiWinGame;
import net.minecraft.launchwrapper.IClassTransformer;

public class GameHelperCoreASMTransformer implements IClassTransformer {

	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		return basicClass;
	}

}
