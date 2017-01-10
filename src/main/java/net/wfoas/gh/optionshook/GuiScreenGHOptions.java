package net.wfoas.gh.optionshook;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.wfoas.gh.dropsapi.pdr.ChatColor;
import net.wfoas.gh.gui.itemtex.GuiItemTexture;
import net.wfoas.gh.items.TradeItems;
import net.wfoas.gh.proxies.ClientProxy;
import net.wfoas.gh.steamconnection.SteamConnection;

public class GuiScreenGHOptions extends GuiScreen {
	GuiItemTexture steam_enabled, steam_loaded;
	GuiButton enableso, done, loadso;

	GuiScreen parent;

	public GuiScreenGHOptions(GuiScreen parent) {
		this.parent = parent;
	}

	// steam overlay geladen... info
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.fontRendererObj.drawStringWithShadow(ChatColor.WHITE + I18n.format("gamehelper.gui.options.title"),
				this.width / 2 - this.fontRendererObj.getStringWidth(I18n.format("gamehelper.gui.options.title")) / 2,
				15, 0);
		int i = 0;
		this.fontRendererObj.drawStringWithShadow(
				ChatColor.WHITE + I18n.format("gamehelper.gui.steamoverlay_activated"),
				this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 12 + 24 * (i >> 1), 0);
		i = 2;
		this.fontRendererObj.drawStringWithShadow(ChatColor.WHITE + I18n.format("gamehelper.gui.steamoverlay_loaded"),
				this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 12 + 24 * (i >> 1), 0);
		steam_enabled.draw(Minecraft.getMinecraft(), mouseX, mouseY);
		steam_loaded.draw(Minecraft.getMinecraft(), mouseX, mouseY);
	}

	public static final ItemStack TRUE = new ItemStack(TradeItems.OK_ITEM),
			FALSE = new ItemStack(TradeItems.NOT_OK_ITEM);

	public void initGui() {
		int i = 4;
		boolean steam_activated = ClientProxy.client_options_gh.getBoolean("enable_steamoverlay");
		this.buttonList.clear();
		enableso = new GuiButton(1, this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 12 + 24 * (i >> 1), 150, 20,
				I18n.format(!steam_activated ? "gamehelper.gui.button.enable.steam_overlay"
						: "gamehelper.gui.button.disable.steam_overlay"));
		i = 5;
		loadso = new GuiButton(2, this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 12 + 24 * (i >> 1), 150, 20,
				I18n.format(!SteamConnection.isOverlayLoaded() ? "gamehelper.gui.button.load.steam_overlay"
						: "gamehelper.gui.button.steam_overlay.already_loaded"));
		loadso.enabled = !SteamConnection.isOverlayLoaded() && steam_activated;
		this.buttonList.add(loadso);
		// enableso.enabled = !steam_activated;
		this.buttonList.add(enableso);
		this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done")));
		i = 1;
		steam_enabled = new GuiItemTexture(this.width / 2 - 155 + i % 2 * 160 + this.width / 6,
				this.height / 6 - 12 + 24 * (i >> 1), steam_activated ? TRUE : FALSE);
		i = 3;
		steam_loaded = new GuiItemTexture(this.width / 2 - 155 + i % 2 * 160 + this.width / 6,
				this.height / 6 - 12 + 24 * (i >> 1), SteamConnection.isOverlayLoaded() ? TRUE : FALSE);
		// i=2 in drawScreen method
	}

	@Override
	public void actionPerformed(GuiButton gb) {
		if (gb.id == enableso.id) {
			ClientProxy.client_options_gh.setBoolean("enable_steamoverlay",
					!ClientProxy.client_options_gh.getBoolean("enable_steamoverlay"));
			ClientProxy.client_options_gh.save();
			initGui();
		} else if (gb.id == 200) {
			this.mc.displayGuiScreen(new GuiOptions(parent, this.mc.gameSettings));
		} else if (gb.id == loadso.id) {
			SteamConnection.loadSteamOverlay();
			initGui();
		}
	}

}