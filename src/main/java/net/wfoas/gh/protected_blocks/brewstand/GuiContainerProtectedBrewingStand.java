package net.wfoas.gh.protected_blocks.brewstand;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

public class GuiContainerProtectedBrewingStand extends GuiContainer {
	ContainerProtectedBrewingStand proc;

	public GuiContainerProtectedBrewingStand(ContainerProtectedBrewingStand inventorySlotsIn) {
		super(inventorySlotsIn);
		proc = inventorySlotsIn;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

	}
}