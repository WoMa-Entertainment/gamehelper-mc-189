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
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.wfoas.gh.GameHelperCoreModule;
import net.wfoas.gh.gui.GuiHandler;
import net.wfoas.gh.gui.guilist.GuiList;
import net.wfoas.gh.gui.world.GuiSetPermScreen;
import net.wfoas.gh.protected_blocks.tabs.ProtectedBlockTabChangePermission;
import net.wfoas.gh.protected_blocks.tabs.ProtectedBlockTabManipulateBlock;
import net.wfoas.gh.proxies.ClientProxy;
import tconstruct.client.tabs.AbstractTab;

public class GuiChangePermission extends GuiScreen {
	private static final ResourceLocation BACKGROUND = new ResourceLocation("gamehelper", "gui/gui_background.png");
	protected int xSize, ySize;

	int __phys_posx, __phys_posy, __phys_posz;
	int guiLeft, guiTop;

	public GuiChangePermission(int physx, int physy, int physz) {
		this.xSize = 248;
		this.ySize = 184;
		// guiLeft = (this.width - this.xSize/4) / 2;
		// guiTop = (this.height - this.ySize/4) / 2;
		this.__phys_posx = physx;
		this.__phys_posy = physy;
		this.__phys_posz = physz;
	}

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
//		System.out.println(Minecraft.getMinecraft().theWorld
//				.getBlockState(new BlockPos(__phys_posx, __phys_posy, __phys_posz)).getBlock());
//		System.out.println("X=" + __phys_posx + " Y=" + __phys_posy + " Z=" + __phys_posz);
		// ownership
		AbstractTab at = new ProtectedBlockTabChangePermission(1, 20, 0, __phys_posx, __phys_posy, __phys_posz).main();
		at.xPosition = (this.guiLeft + (5 - 2 + 1) * 28);
		at.yPosition = (this.guiTop - 28);
		if (at != null)
			this.buttonList.add(at);
		GuiList guiList = new GuiList(ClientProxy.onlinePlayers, 50, 150, 120, 270, 12, null, this, 15);
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
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
