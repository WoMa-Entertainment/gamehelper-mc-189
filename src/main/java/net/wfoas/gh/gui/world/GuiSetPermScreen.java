package net.wfoas.gh.gui.world;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.gui.button.OverlayDoubleTexToggleButton;
import net.wfoas.gh.gui.button.OverlayTexToggleButton;
import net.wfoas.gh.gui.guilist.GuiList;
import net.wfoas.gh.items.TradeItems;
import net.wfoas.gh.network.NetworkHandler;
import net.wfoas.gh.network.packet.PacketPlayRequestProfileForPlayerInWorld;
import net.wfoas.gh.network.packet.PacketPlaySendNewProfileToServer;
import net.wfoas.gh.proxies.ClientProxy;

public class GuiSetPermScreen extends GuiScreen implements IGuiPermElement {
	private static final ResourceLocation BACKGROUND = new ResourceLocation("gamehelper", "gui/gui_background.png");
	OverlayTexToggleButton done, visit, build, destroy, collect_items_exp, kill, interact;
	GuiList playerList, worldList;
	boolean initialized = false;

	public static final ItemStack OK = new ItemStack(TradeItems.OK_ITEM),
			NOT_OK = new ItemStack(TradeItems.NOT_OK_ITEM);
	protected int xSize, ySize;

	public GuiSetPermScreen() {
		this.xSize = 248;
		this.ySize = 184;
		this.mc = Minecraft.getMinecraft();
		if (GameHelper.getUtils().setPermScreen != null) {
			Minecraft.getMinecraft().setIngameFocus();
		}
		GameHelper.getUtils().setPermScreen = this;
	}

	public void onGuiClosed() {
		super.onGuiClosed();
		GameHelper.getUtils().setPermScreen = null;
	}

	public void drawBackground() {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(BACKGROUND);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		// int k = this.width / 2 - 30;
		// int l = this.height / 2 - 10;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}

	public void keyTyped(char c, int i) {
		try {
			super.keyTyped(c, i);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mouseClicked(int i, int j, int k) {
		try {
			super.mouseClicked(i, j, k);
			// stringList.handleMouseInput();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	String nameOfWorld;

	public void updateScreen() {
		super.updateScreen();
	}

	String title, playertitle, worldtitle;

	String selectedPlayer, selectedWorld;

	String t_visit, t_destroy, t_build, t_kill, t_collect_items_exp, t_interact;

	public static final String PLAYER_WITH_16_CHAR = "PlayerWith16Char";

	@Override
	public void initGui() {
		super.initGui();
		title = I18n.format("gamehelper.setperm.title");
		playertitle = I18n.format("gamehelper.setperm.playerlist");
		worldtitle = I18n.format("gamehelper.setperm.worldlist");
		t_visit = I18n.format("gamehelper.setperm.visit");
		t_destroy = I18n.format("gamehelper.setperm.destroy");
		t_build = I18n.format("gamehelper.setperm.build");
		t_kill = I18n.format("gamehelper.setperm.kill");
		t_collect_items_exp = I18n.format("gamehelper.setperm.collect_item_exp");
		t_interact = I18n.format("gamehelper.setperm.interact");
		// if (!initialized) {
		// int k = (this.width - this.xSize) / 2;
		// int l = (this.height - this.ySize) / 2;
		int k = this.width / 2 - 100;
		int l = this.height / 2 - 20;
		playerList = new GuiList(ClientProxy.onlinePlayers,
				Minecraft.getMinecraft().fontRendererObj.getStringWidth(PLAYER_WITH_16_CHAR), 25, l + 50, l + 100, 10,
				new GuiList.ActionListener() {
					@Override
					public void actionPerformed(GuiList guiList, int slotIndex, boolean isDoubleClick, int mouseX,
							int mouseY) {
						// System.out.println("Clicked + slotIndex: " +
						// slotIndex + "; ");
						System.out.println(guiList.getSelectedString());
						GuiSetPermScreen.this.selectedPlayer = guiList.getSelectedString();
						if (GuiSetPermScreen.this.selectedWorld == null)
							return;
						PacketPlayRequestProfileForPlayerInWorld packet = new PacketPlayRequestProfileForPlayerInWorld(
								selectedPlayer, selectedWorld);
						NetworkHandler.sendToServer(packet);
					}
				}, this, k);// gamehelper.setperm.playerlist
		// playerList.setHeader(I18n.format("gamehelper.setperm.playerlist"));
		worldList = new GuiList(ClientProxy.ownedWorlds,
				Minecraft.getMinecraft().fontRendererObj.getStringWidth(PLAYER_WITH_16_CHAR), 25, l + 50, l + 100, 10,
				new GuiList.ActionListener() {

					@Override
					public void actionPerformed(GuiList guiList, int slotIndex, boolean isDoubleClick, int mouseX,
							int mouseY) {
						GuiSetPermScreen.this.selectedWorld = guiList.getSelectedString();
						if (GuiSetPermScreen.this.selectedPlayer == null)
							return;
						PacketPlayRequestProfileForPlayerInWorld packet = new PacketPlayRequestProfileForPlayerInWorld(
								selectedPlayer, selectedWorld);
						NetworkHandler.sendToServer(packet);
					}
				}, this, (int) (k + playerList.width * 1.25f));
		visit = new OverlayDoubleTexToggleButton(1, this.width / 2 - 30, this.height / 2 - 60, "", OK, NOT_OK);
		visit.width = 18;
		visit.height = 18;
		build = new OverlayDoubleTexToggleButton(2, this.width / 2 - 30, this.height / 2 - 30, "", OK, NOT_OK);
		build.width = 18;
		build.height = 18;
		destroy = new OverlayDoubleTexToggleButton(3, this.width / 2 - 30, this.height / 2, "", OK, NOT_OK);
		destroy.width = 18;
		destroy.height = 18;
		//
		collect_items_exp = new OverlayDoubleTexToggleButton(4, this.width / 2 + 70 + 10, this.height / 2 - 60, "", OK,
				NOT_OK);
		collect_items_exp.width = 18;
		collect_items_exp.height = 18;
		kill = new OverlayDoubleTexToggleButton(5, this.width / 2 + 70 + 10, this.height / 2 - 30, "", OK, NOT_OK);
		kill.width = 18;
		kill.height = 18;
		interact = new OverlayDoubleTexToggleButton(6, this.width / 2 + 70 + 10, this.height / 2, "", OK, NOT_OK);
		interact.width = 18;
		interact.height = 18;
		done = new OverlayTexToggleButton(0, this.width / 2 + 100, this.height / 2 + 50, "",
				new ItemStack(TradeItems.OK_ITEM));
		done.width = 18;
		done.height = 18;
		this.buttonList.add(visit);
		this.buttonList.add(build);
		this.buttonList.add(destroy);
		this.buttonList.add(collect_items_exp);
		this.buttonList.add(kill);
		this.buttonList.add(interact);
		this.buttonList.add(done);
		// , build, destroy, collect_items_exp, kill, interact
		// }
		initialized = true;
	}

	@Override
	public void actionPerformed(GuiButton gb) {
		if (gb.id == 1) {
			visit.setSelected(!visit.selected);
		}
		if (gb.id == 2) {
			build.setSelected(!build.selected);
		}
		if (gb.id == 3) {
			destroy.setSelected(!destroy.selected);
		}
		if (gb.id == 4) {
			collect_items_exp.setSelected(!collect_items_exp.selected);
		}
		if (gb.id == 5) {
			kill.setSelected(!kill.selected);
		}
		if (gb.id == 6) {
			interact.setSelected(!interact.selected);
		}
		if (gb.id == 0) {
			sendProfileToServer();
			Minecraft.getMinecraft().setIngameFocus();
		}
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void drawScreen(int x, int y, float par3) {
		if (!initialized)
			return;
		this.drawBackground();
		super.drawScreen(x, y, par3);
		Minecraft.getMinecraft().fontRendererObj.drawString(title,
				this.width / 2 - Minecraft.getMinecraft().fontRendererObj.getStringWidth(title) / 2,
				this.height / 2 - 80, 0);
		int l = this.height / 2 - 20;
		if (playerList != null)
			playerList.drawCenteredString(Minecraft.getMinecraft().fontRendererObj, playertitle,
					playerList.left + playerList.width / 2, l + 50 - 10, 0xffffffff);
		else
			System.out.println("playerlist == null");
		if (worldList != null)
			worldList.drawCenteredString(Minecraft.getMinecraft().fontRendererObj, worldtitle,
					worldList.left + worldList.width / 2, l + 50 - 10, 0xffffffff);
		else
			System.out.println("worldlist == null");
		Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(t_visit, this.width / 2 - 30 - 70,
				this.height / 2 - 60 + 4.5f, 0xffffffff);
		Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(t_build, this.width / 2 - 30 - 70,
				this.height / 2 - 30 + 4.5f, 0xffffffff);
		Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(t_destroy, this.width / 2 - 30 - 70,
				this.height / 2 + 4.5f, 0xffffffff);
		Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(t_collect_items_exp, this.width / 2 + 70 - 60,
				this.height / 2 - 60 + 4.5f, 0xffffffff);
		Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(t_kill, this.width / 2 + 70 - 60,
				this.height / 2 - 30 + 4.5f, 0xffffffff);
		Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(t_interact, this.width / 2 + 70 - 60,
				this.height / 2 + 4.5f, 0xffffffff);

		this.playerList.drawScreen(x, y, par3);
		this.playerList.handleMouseInput();
		this.worldList.drawScreen(x, y, par3);
		this.worldList.handleMouseInput();
	}

	public void setProfileForGuiSetPermScreen(boolean visit, boolean destroy, boolean build, boolean kill,
			boolean collect_items_exp, boolean interact) {
		// System.out.println(
		// "V" + visit + "D" + destroy + "V" + build + "K" + kill + "C" +
		// collect_items_exp + "I" + interact);
		// this.visit.selected = !visit;
		// this.destroy.selected = !destroy;
		// this.build.selected = !build;
		// this.kill.selected = !kill;
		// this.collect_items_exp.selected = !collect_items_exp;
		// this.interact.selected = !interact;
		this.visit.setSelected(!visit);
		this.destroy.setSelected(!destroy);
		this.build.setSelected(!build);
		this.kill.setSelected(!kill);
		this.collect_items_exp.setSelected(!collect_items_exp);
		this.interact.setSelected(!interact);
	}

	public void sendProfileToServer() {
		PacketPlaySendNewProfileToServer packet = new PacketPlaySendNewProfileToServer(this.selectedPlayer,
				this.selectedWorld, !visit.isSelected(), !destroy.isSelected(), !build.isSelected(), !kill.isSelected(),
				!collect_items_exp.isSelected(), !interact.isSelected());
		NetworkHandler.sendToServer(packet);
	}
}
