package net.wfoas.gh.tablist;

import static net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType.PLAYER_LIST;

import java.lang.reflect.Field;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value = Side.CLIENT)
public class RenderTabList {
	// public static Field tabListField = null;
	//
	// static void init() {
	// for (Field f : GuiIngame.class.getDeclaredFields()) {
	// if (f.getType().equals(GuiPlayerTabOverlay.class)) {
	// tabListField = f;
	// }
	// }
	// }

	private static boolean pre(ElementType type, RenderGameOverlayEvent eventParent) {
		return MinecraftForge.EVENT_BUS.post(new RenderGameOverlayEvent.Pre(eventParent, type));
	}

	private static void post(ElementType type, RenderGameOverlayEvent eventParent) {
		MinecraftForge.EVENT_BUS.post(new RenderGameOverlayEvent.Post(eventParent, type));
	}

	protected static void renderPlayerList(int width, int height, RenderGameOverlayEvent eventParent) {
//		GuiPlayerTabOverlay
//		GuiPlayerTabOverlay overlayPlayerList = Minecraft.getMinecraft().ingameGUI.getTabList();
//		Minecraft mc = Minecraft.getMinecraft();
//		ScoreObjective scoreobjective = mc.theWorld.getScoreboard().getObjectiveInDisplaySlot(0);
//		NetHandlerPlayClient handler = mc.thePlayer.sendQueue;
//		boolean onlyRenders = (!mc.isIntegratedServerRunning() || handler.func_175106_d().size() > 1
//				|| scoreobjective != null);
//		if (!onlyRenders && mc.gameSettings.keyBindPlayerList.isKeyDown()) {
//			overlayPlayerList.func_175246_a(true);
//			if (pre(PLAYER_LIST, eventParent))
//				return;
//			overlayPlayerList.func_175249_a(width, mc.theWorld.getScoreboard(), scoreobjective);
//			post(PLAYER_LIST, eventParent);
//		} else {
//			overlayPlayerList.func_175246_a(false);
//		}
	}
}
