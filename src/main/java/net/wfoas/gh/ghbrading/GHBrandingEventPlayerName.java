package net.wfoas.gh.ghbrading;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.wfoas.gh.dropsapi.pdr.ChatColor;

public class GHBrandingEventPlayerName {
	String glversion;
	public static final String GL = "OpenGL ";
	public static final String MOJANG_BRAND = "Copyright Mojang AB. Do not distribute!";
	public static final String WOMA_BRAND = "Copyright WOMA | WFOAS AG & ";

	public GHBrandingEventPlayerName() {
		glversion = ChatColor.BLUE + GL + GL11.glGetString(GL11.GL_VERSION);
	}

	@SubscribeEvent
	public void render(GuiScreenEvent.DrawScreenEvent i) {
		if (!(i.gui instanceof GuiMainMenu))
			return;
		GuiMainMenu gui = (GuiMainMenu) i.gui;
		String playername = ChatColor.DARK_AQUA + I18n.format("mainmenu.gh.brand.playername") + ChatColor.GOLD
				+ Minecraft.getMinecraft().getSession().getUsername();
		gui.drawString(Minecraft.getMinecraft().fontRendererObj, playername,
				gui.width - Minecraft.getMinecraft().fontRendererObj.getStringWidth(playername) - 2,
				gui.height - (10 + 2 * (Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 1)), -1);
		gui.drawString(Minecraft.getMinecraft().fontRendererObj, glversion,
				gui.width - Minecraft.getMinecraft().fontRendererObj.getStringWidth(glversion) - 2,
				gui.height - (10 + 3 * (Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 1)), -1);
		gui.drawString(Minecraft.getMinecraft().fontRendererObj, WOMA_BRAND,
				gui.width - Minecraft.getMinecraft().fontRendererObj.getStringWidth(WOMA_BRAND) - 2,
				gui.height - (10 + 1 * (Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 1)), -1);
	}

	@SubscribeEvent
	public void renderMenu(GuiScreenEvent.DrawScreenEvent e) {
		if (!(e.gui instanceof GuiIngameMenu))
			return;
		GuiIngameMenu gui = (GuiIngameMenu) e.gui;
		String playername = ChatColor.DARK_AQUA + I18n.format("mainmenu.gh.brand.playername") + ChatColor.GOLD
				+ Minecraft.getMinecraft().getSession().getUsername();
		String servername = ChatColor.GREEN + "";
		String servermotd = ChatColor.AQUA + "";
		String serverip = ChatColor.GOLD + "";
		if (Minecraft.getMinecraft().isIntegratedServerRunning()) {
			servername = servername + I18n.format("gamemenu.gh.info.servername") + "("
					+ I18n.format("gamemenu.gh.info.integratedserver") + ")";
			servermotd = servermotd + I18n.format("gamemenu.gh.info.servermotd") + "("
					+ I18n.format("gamemenu.gh.info.integratedserver") + ")";
			serverip = serverip + I18n.format("gamemenu.gh.info.serverip") + "("
					+ I18n.format("gamemenu.gh.info.integratedserver") + ")";
		} else {
			servername = servername + I18n.format("gamemenu.gh.info.servername")
					+ Minecraft.getMinecraft().getCurrentServerData().serverName;
			servermotd = servermotd + I18n.format("gamemenu.gh.info.servermotd")
					+ Minecraft.getMinecraft().getCurrentServerData().serverMOTD;
			serverip = serverip + I18n.format("gamemenu.gh.info.serverip")
					+ Minecraft.getMinecraft().getCurrentServerData().serverIP;
		}
		gui.drawString(Minecraft.getMinecraft().fontRendererObj, playername,
				gui.width - Minecraft.getMinecraft().fontRendererObj.getStringWidth(playername) - 2,
				gui.height - (10 + 0 * (Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 1)), -1);
		gui.drawString(Minecraft.getMinecraft().fontRendererObj, glversion,
				gui.width - Minecraft.getMinecraft().fontRendererObj.getStringWidth(glversion) - 2,
				gui.height - (10 + 1 * (Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 1)), -1);
		String ping = Minecraft.getMinecraft().isIntegratedServerRunning()
				? ChatColor.YELLOW + "Ping: " + ChatColor.GREEN + "0" + ChatColor.YELLOW + "ms"
				: (ChatColor.YELLOW + "Ping: " + ChatColor.GREEN + ""
						+ Minecraft.getMinecraft().getCurrentServerData().pingToServer + "" + ChatColor.YELLOW + "ms");
		gui.drawString(Minecraft.getMinecraft().fontRendererObj, ping, 2,
				gui.height - (10 + 0 * (Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 1)), 16777215);
		gui.drawString(Minecraft.getMinecraft().fontRendererObj, serverip, 2,
				gui.height - (10 + 1 * (Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 1)), 16777215);
		gui.drawString(Minecraft.getMinecraft().fontRendererObj, servermotd, 2,
				gui.height - (10 + 2 * (Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 1)), 16777215);
		gui.drawString(Minecraft.getMinecraft().fontRendererObj, servername, 2,
				gui.height - (10 + 3 * (Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 1)), 16777215);
		// gui.drawString(Minecraft.getMinecraft().fontRendererObj, text, x,
		// y, color);
		// }
		// gui.drawString(Minecraft.getMinecraft().fontRendererObj,
		// serveraddress,
		// gui.width -
		// Minecraft.getMinecraft().fontRendererObj.getStringWidth(serveraddress)
		// -
		// 2,
		// gui.height - (10 + 0 *
		// (Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 1)), -1);
	}
}
