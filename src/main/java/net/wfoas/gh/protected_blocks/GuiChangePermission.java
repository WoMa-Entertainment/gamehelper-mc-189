package net.wfoas.gh.protected_blocks;

import java.security.SecurityPermission;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
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
		// guiLeft = (this.width - this.xSize/4) / 2;
		// guiTop = (this.height - this.ySize/4) / 2;
		this.__phys_posx = physx;
		this.__phys_posy = physy;
		this.__phys_posz = physz;
		this.prot_blc = prot_blc;
	}

	GuiList guiList, guiList22;
	String notInListSel = null, inListSel = null;

	@Override
	public void initGui() {
		super.initGui();
		this.guiLeft = (this.width - this.xSize) / 2;
		this.guiTop = (this.height - this.ySize) / 2;
		AbstractTab ab;
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
		OverlayDoubleTexToggleButton guiButton = new OverlayDoubleTexToggleButton(0, k - 50, l - 50, "§aÖffentlich", OK,
				NOK);
		OverlayDoubleTexToggleButton guiButton2 = new OverlayDoubleTexToggleButton(1, k - 50 - 20, l - 50,
				"§3Zugriff spezifizieren", OK, NOK);
		OverlayDoubleTexToggleButton guiButton3 = new OverlayDoubleTexToggleButton(2, k - 50 - 20, l - 50 - 20,
				"§cPrivat", OK, NOK);
		OverlayTexToggleButton finish = new OverlayTexToggleButton(3, k + 50 + 100 + 50, l + 50, "Fertig", OK);
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
		guiList.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}