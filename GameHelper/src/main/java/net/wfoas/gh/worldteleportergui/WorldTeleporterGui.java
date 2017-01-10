package net.wfoas.gh.worldteleportergui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.wfoas.gh.multipleworlds.GHWorld;
import net.wfoas.gh.multipleworlds.storage.GHWorldManager;

public class WorldTeleporterGui extends GuiScreen {

	@Override
	public void initGui() {
		for (int id : DimensionManager.getIDs()) {
			WorldServer w = DimensionManager.getWorld(id);
			GHWorldManager.isWorldAlreadyCreated(createGHWorldFromDim(id));
		}
	}

	public GHWorld createGHWorldFromDim(int dim) {
		return GHWorldManager.getGHWorldByDimensionID(dim);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {

	}

}
