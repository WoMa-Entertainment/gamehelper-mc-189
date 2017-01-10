package net.wfoas.gh.gui.ownworld;

import java.io.IOException;
import java.util.Collections;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;

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
import net.wfoas.gh.proxies.ClientProxy;

public class GuiScreenOwnWorld extends GuiScreen {
	private static final ResourceLocation BACKGROUND = new ResourceLocation("gamehelper", "gui/gui_background.png");
	GuiButton addToOwnerList, removeFromOwnerList, closeDialog;
	GuiList playerNotOwnerList, worldList, playerOwnerList;

	public static final ItemStack OK = new ItemStack(TradeItems.OK_ITEM),
			NOT_OK = new ItemStack(TradeItems.NOT_OK_ITEM);
	protected int xSize, ySize;

	public GuiScreenOwnWorld() {
		this.xSize = 248;
		this.ySize = 184;
		this.mc = Minecraft.getMinecraft();
	}

	public void onGuiClosed() {
		super.onGuiClosed();
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

	String title, playerOwnertitle, worldtitle, playerNotOwnerTitle;

	String selectedPlayer, selectedWorld;

	public static final String PLAYER_WITH_16_CHAR = "PlayerWith16Char";

	@Override
	public void initGui() {
		super.initGui();
		title = I18n.format("gamehelper.ownworld.title");
		playerOwnertitle = I18n.format("gamehelper.ownworld.playerlist.owner");
		playerNotOwnerTitle = I18n.format("gamehelper.ownworld.playerlist.notowner");
		worldtitle = I18n.format("gamehelper.ownworld.worldlist");
		int k = this.width / 2 - 100;
		int l = this.height / 2 - 20;
		playerNotOwnerList = new GuiList(Lists.newArrayList(new String[] { "Entry 1" }),
				Minecraft.getMinecraft().fontRendererObj.getStringWidth(PLAYER_WITH_16_CHAR), 25, l + 50, l + 100, 10,
				new GuiList.ActionListener() {
					@Override
					public void actionPerformed(GuiList guiList, int slotIndex, boolean isDoubleClick, int mouseX,
							int mouseY) {
						GuiScreenOwnWorld.this.selectedPlayer = guiList.getSelectedString();
					}
				}, this, k - 15);
		playerOwnerList = new GuiList(
				Lists.newArrayList(new String[] { "Entry 1", "Entry 1", "Entry 1", "Entry 1", "Entry 1", "Entry 1",
						"Entry 1", "Entry 1", "Entry 1", "Entry 1" }),
				Minecraft.getMinecraft().fontRendererObj.getStringWidth(PLAYER_WITH_16_CHAR), 25, l + 50, l + 100, 10,
				new GuiList.ActionListener() {
					@Override
					public void actionPerformed(GuiList guiList, int slotIndex, boolean isDoubleClick, int mouseX,
							int mouseY) {
						GuiScreenOwnWorld.this.selectedPlayer = guiList.getSelectedString();
					}
				}, this, (int) (k + playerNotOwnerList.width * 1.25f) + 15);
		worldList = new GuiList(ClientProxy.allWorlds,
				Minecraft.getMinecraft().fontRendererObj.getStringWidth(PLAYER_WITH_16_CHAR), 25, l - 25, l + 35, 10,
				new GuiList.ActionListener() {

					@Override
					public void actionPerformed(GuiList guiList, int slotIndex, boolean isDoubleClick, int mouseX,
							int mouseY) {
						GuiScreenOwnWorld.this.selectedWorld = guiList.getSelectedString();
						if (GuiScreenOwnWorld.this.selectedPlayer == null)
							return;
						PacketPlayRequestProfileForPlayerInWorld packet = new PacketPlayRequestProfileForPlayerInWorld(
								selectedPlayer, selectedWorld);
						NetworkHandler.sendToServer(packet);
					}
				}, this, (int) (k + playerOwnerList.width / 1.75f + 10));
		// , build, destroy, collect_items_exp, kill, interact
		// }
		addToOwnerList = new GuiButton(1, playerNotOwnerList.left + playerNotOwnerList.width + 10 + 5,
				this.height / 2 + 35, I18n.format("gamehelper.ownworld.add"));
		removeFromOwnerList = new GuiButton(2, playerNotOwnerList.left + playerNotOwnerList.width + 10 + 5,
				this.height / 2 + 55, I18n.format("gamehelper.ownworld.remove"));
		addToOwnerList.width = 25;
		removeFromOwnerList.width = 25;
		closeDialog = new OverlayTexToggleButton(0, k + 175, l - 50, null, OK);
		closeDialog.width = 20;
		closeDialog.height = 20;
		buttonList.add(addToOwnerList);
		buttonList.add(removeFromOwnerList);
		buttonList.add(closeDialog);
	}

	@Override
	public void actionPerformed(GuiButton gb) {
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void drawScreen(int x, int y, float par3) {
		this.drawBackground();
		GL11.glScalef(1, 1, 1);
		super.drawScreen(x, y, par3);
		GL11.glScaled(1, 1, 1);
		Minecraft.getMinecraft().fontRendererObj.drawString(title,
				this.width / 2 - Minecraft.getMinecraft().fontRendererObj.getStringWidth(title) / 2,
				this.height / 2 - 80, 0);
		int l = this.height / 2 - 20;
		playerNotOwnerList.drawCenteredString(Minecraft.getMinecraft().fontRendererObj, playerNotOwnerTitle,
				playerNotOwnerList.left + playerNotOwnerList.width / 2, l + 50 - 10, 0xffffffff);
		playerOwnerList.drawCenteredString(Minecraft.getMinecraft().fontRendererObj, playerOwnertitle,
				playerOwnerList.left + playerOwnerList.width / 2, l + 50 - 10, 0xffffffff);
		worldList.drawCenteredString(Minecraft.getMinecraft().fontRendererObj, worldtitle,
				worldList.left + worldList.width / 2, l - 25 - 10, 0xffffffff);
		this.playerNotOwnerList.drawScreen(x, y, par3);
		this.playerNotOwnerList.handleMouseInput();
		this.playerOwnerList.drawScreen(x, y, par3);
		this.playerOwnerList.handleMouseInput();
		this.worldList.drawScreen(x, y, par3);
		this.worldList.handleMouseInput();
	}
}