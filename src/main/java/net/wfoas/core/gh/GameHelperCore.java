package net.wfoas.core.gh;

import java.util.Map;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.SortingIndex;

@IFMLLoadingPlugin.SortingIndex(1001)
@IFMLLoadingPlugin.Name(value = GameHelperCore.GH_COREMOD_NAME)
@IFMLLoadingPlugin.MCVersion(value = "1.8.9")
@IFMLLoadingPlugin.TransformerExclusions({ "net.wfoas.core.gh" })
public class GameHelperCore implements IFMLLoadingPlugin {
	public static final String GH_COREMOD_NAME = "GameHelperCore";
	public static final String GH_COREMOD_VERSION = "1.2";

	public GameHelperCore() {
		// System.out.println("Created GameHelperCoreMod Class");EntityHorse
		// MinecraftServer.getServer().
	}

	@Override
	public String[] getASMTransformerClass() {
		return new String[] { GameHelperCoreASMTransformer.class.getName() };
	}

	@Override
	public String getModContainerClass() {
		return GameHelperCoreContainer.class.getName();
	}

	@Override
	public String getSetupClass() {
		return GameHelperCoreSetup.class.getName();
	}

	@Override
	public void injectData(Map<String, Object> data) {
		for (Map.Entry<String, Object> Ntry : data.entrySet()) {
			System.out.println(Ntry.getKey() + ": " + Ntry.getValue());
		}
	}

	@Override
	public String getAccessTransformerClass() {
		// return GameHelperCoreAccessTransformer.class.getName();
		return null;
	}
}
