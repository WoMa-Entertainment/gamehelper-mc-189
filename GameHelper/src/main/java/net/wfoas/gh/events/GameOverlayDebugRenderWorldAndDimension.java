package net.wfoas.gh.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GameOverlayDebugRenderWorldAndDimension {
	@SideOnly(value = Side.CLIENT)
	@SubscribeEvent
	public void renderGameOverlayDebugScreenWithWorldInformationAndDimensionId(RenderGameOverlayEvent event) {
		if (event.type == ElementType.TEXT && Minecraft.getMinecraft().getRenderManager().getFontRenderer() != null
				&& Minecraft.getMinecraft().gameSettings.showDebugInfo) {
			// Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("",
			// 50, 20, 0xFFFFFF);
			String text = "World: " + Minecraft.getMinecraft().thePlayer.getEntityWorld().getWorldInfo().getWorldName()
					+ " | Dim: " + Minecraft.getMinecraft().thePlayer.dimension;
			int j = Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT;
			int k = Minecraft.getMinecraft().fontRendererObj.getStringWidth(text);
			int l = 2 + j * 15;
			Gui.drawRect(1, l - 1, 2 + k + 1, l + j - 1, -1873784752);
			Minecraft.getMinecraft().fontRendererObj.drawString(text, 2, l, 14737632);
		}
	}
}
