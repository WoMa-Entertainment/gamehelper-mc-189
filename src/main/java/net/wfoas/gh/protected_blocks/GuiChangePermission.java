package net.wfoas.gh.protected_blocks;

import java.io.IOException;
import java.security.SecurityPermission;
import java.util.UUID;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.GameHelperCoreModule;
import net.wfoas.gh.gui.GuiHandler;
import net.wfoas.gh.gui.button.OverlayDoubleTexToggleButton;
import net.wfoas.gh.gui.button.OverlayTexToggleButton;
import net.wfoas.gh.gui.guilist.GuiList;
import net.wfoas.gh.gui.guilist.GuiList.ActionListener;
import net.wfoas.gh.gui.world.GuiSetPermScreen;
import net.wfoas.gh.items.TradeItems;
import net.wfoas.gh.protected_blocks.tabs.ProtectedBlockTabChangePermission;
import net.wfoas.gh.protected_blocks.tabs.ProtectedBlockTabManipulateBlock;
import net.wfoas.gh.proxies.ClientProxy;
import tconstruct.client.tabs.AbstractTab;

public class GuiChangePermission extends GuiScreen {
	final static public ItemStack OK = new ItemStack(TradeItems.OK_ITEM);
	final static public ItemStack NOK = new ItemStack(TradeItems.NOT_OK_ITEM);
	static public final String PLAYER_WITH_16_CHAR = "PlayerWith16Char";
	private static final ResourceLocation BACKGROUND = new ResourceLocation("gamehelper", "gui/gui_background.png");
	protected int xSize, ySize;
	IProtectedBlock prot_blc;

	int __phys_posx, __phys_posy, __phys_posz;
	int guiLeft, guiTop;

	public GuiChangePermission(int physx, int physy, int physz, IProtectedBlock prot_blc) {
		this.xSize = 248;
		this.ySize = 184;
		this.__phys_posx = physx;
		this.__phys_posy = physy;
		this.__phys_posz = physz;
		this.prot_blc = prot_blc;
		System.out.println(prot_blc);
		if (prot_blc != null) {
			prot_blc.addWhiteListedPlayer(UUID.randomUUID());
		}
	}

	GuiList guiList, guiList22;
	String notInListSel = null, inListSel = null;
	public static final String _I18N_PUBLIC = "gamehelper.guichangepermprotblock.public",
			_I18N_SPECIFY = "gamehelper.guichangepermprotblock.spefify_players",
			_I18N_PRIVATE = "gamehelper.guichangepermprotblock.private";
	protected String _i18n_current = "";

	OverlayDoubleTexToggleButton guiButton, guiButton2, guiButton3;
	OverlayTexToggleButton finish;

	@Override
	public void initGui() {
		super.initGui();
		this.guiLeft = (this.width - this.xSize) / 2;
		this.guiTop = (this.height - this.ySize) / 2;
		AbstractTab ab; // TODO id-system for protected blocks and gui
		try {
			ab = new ProtectedBlockTabManipulateBlock(0, 0, 0, __phys_posx, __phys_posy, __phys_posz,
					Item.getItemFromBlock(Minecraft.getMinecraft().theWorld
							.getBlockState(new BlockPos(__phys_posx, __phys_posy, __phys_posz)).getBlock()),
					ProtectedBlocksRegistry.getID(Minecraft.getMinecraft().theWorld
							.getBlockState(new BlockPos(__phys_posx, __phys_posy, __phys_posz)).getBlock()));

		} catch (Throwable z) {
			z.printStackTrace();
			ab = null;
			return;
		}
		if (ab != null)
			ab.xPosition = (this.guiLeft + (4 - 2 + 1) * 28);
		if (ab != null)
			ab.yPosition = (this.guiTop - 28);
		if (ab != null)
			this.buttonList.add(ab);
		// System.out.println(Minecraft.getMinecraft().theWorld
		// .getBlockState(new BlockPos(__phys_posx, __phys_posy,
		// __phys_posz)).getBlock());
		// System.out.println("X=" + __phys_posx + " Y=" + __phys_posy + " Z=" +
		// __phys_posz);
		// ownership
		AbstractTab at = new ProtectedBlockTabChangePermission(1, 20, 0, __phys_posx, __phys_posy, __phys_posz).main();
		at.xPosition = (this.guiLeft + (5 - 2 + 1) * 28);
		at.yPosition = (this.guiTop - 28);
		if (at != null)
			this.buttonList.add(at);
		int k = this.width / 2 - 100;
		int l = this.height / 2 - 20;
		guiButton = new OverlayDoubleTexToggleButton(10, this.width / 2 - 100 + 5, l - 50 - 10, "§aÖffentlich", OK,
				NOK);
		guiButton.setSelected(true);
		guiButton2 = new OverlayDoubleTexToggleButton(11, this.width / 2 - 20 + 10, l - 50 - 10,
				"§3Zugriff spezifizieren", OK, NOK);
		guiButton2.setSelected(false);
		guiButton3 = new OverlayDoubleTexToggleButton(12, this.width / 2 + 70 + 15, l - 50 - 10, "§cPrivat", OK, NOK);
		finish = new OverlayTexToggleButton(13, k + 50 + 100 + 50, l + 50, "Fertig", OK);
		guiButton.width = 18;
		guiButton.height = 18;
		guiButton2.width = 18;
		guiButton2.height = 18;
		guiButton3.width = 18;
		guiButton3.height = 18;
		finish.height = 18;
		finish.width = 18;
		this.buttonList.add(guiButton);
		this.buttonList.add(guiButton2);
		this.buttonList.add(guiButton3);
		this.buttonList.add(finish);
		try {
			GameHelper.getLogger().info("OnlinePlayers:" + ClientProxy.onlinePlayers.size());
			guiList = new GuiList(ClientProxy.onlinePlayers, this.fontRendererObj.getStringWidth(PLAYER_WITH_16_CHAR),
					150, l, l + this.fontRendererObj.getStringWidth(PLAYER_WITH_16_CHAR), 12, new ActionListener() {
						@Override
						public void actionPerformed(GuiList guiList, int slotIndex, boolean isDoubleClick, int mouseX,
								int mouseY) {
							// var
						}
					}, this, k);
			ClientProxy.onlinePlayers.add("wow");
			ClientProxy.onlinePlayers.add("gggasdfi");
			ClientProxy.onlinePlayers.add("aosdfoimpa");
			guiList22 = new GuiList(ClientProxy.onlinePlayers, this.fontRendererObj.getStringWidth(PLAYER_WITH_16_CHAR),
					150, l, l + this.fontRendererObj.getStringWidth(PLAYER_WITH_16_CHAR), 12, new ActionListener() {
						@Override
						public void actionPerformed(GuiList guiList, int slotIndex, boolean isDoubleClick, int mouseX,
								int mouseY) {
							// var
						}
					}, this, k + 20 + (int) (this.fontRendererObj.getStringWidth(PLAYER_WITH_16_CHAR)));

		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	@Override
	public void drawBackground(int tint) {
		// super.drawBackground(tint);
		// GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		// Minecraft.getMinecraft().getTextureManager().bindTexture(BACKGROUND);
		// int k = (this.width - this.xSize) / 2;
		// int l = (this.height - this.ySize) / 2;
		// this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		if (button.id == guiButton.id) {
			guiButton.setSelected(true);
			guiButton2.setSelected(false);
			guiButton3.setSelected(false);
		} else if (button.id == guiButton2.id) {
			guiButton.setSelected(false);
			guiButton2.setSelected(true);
			guiButton3.setSelected(false);
		} else if (button.id == guiButton3.id) {
			guiButton.setSelected(false);
			guiButton2.setSelected(false);
			guiButton3.setSelected(true);
		} else if (button.id == finish.id) {
			Minecraft.getMinecraft().setIngameFocus();
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(BACKGROUND);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		for (int i = 0; i < this.buttonList.size(); ++i) {
			((GuiButton) this.buttonList.get(i)).drawButton(this.mc, mouseX, mouseY);
		}

		for (int j = 0; j < this.labelList.size(); ++j) {
			((GuiLabel) this.labelList.get(j)).drawLabel(this.mc, mouseX, mouseY);
		}
		this.fontRendererObj.drawString(I18n.format(_I18N_PUBLIC), this.width / 2 - 100, this.height / 2 - 50, 0);
		this.fontRendererObj.drawString(I18n.format(_I18N_SPECIFY), this.width / 2 - 15, this.height / 2 - 50, 0);
		this.fontRendererObj.drawString(I18n.format(_I18N_PRIVATE), this.width / 2 + 70, this.height / 2 - 50, 0);
		if (guiList != null) {
			guiList.drawScreen(mouseX, mouseY, partialTicks);
			guiList.handleMouseInput();
		}
		if (guiList22 != null) {
			guiList22.drawScreen(mouseX, mouseY, partialTicks);
			guiList22.handleMouseInput();
		}
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}