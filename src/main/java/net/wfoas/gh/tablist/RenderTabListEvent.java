package net.wfoas.gh.tablist;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class RenderTabListEvent {
	public void listerRenderGameOverlayEvent(RenderGameOverlayEvent e) {
		if (e.type == null) {
			if (Minecraft.getMinecraft().gameSettings.keyBindPlayerList.isKeyDown()) {
				RenderTabList.renderPlayerList(e.resolution.getScaledWidth(), e.resolution.getScaledHeight(), e);
			}
		}
	}
}
