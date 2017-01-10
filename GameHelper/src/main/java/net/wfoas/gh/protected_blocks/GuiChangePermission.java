package net.wfoas.gh.protected_blocks;

import java.security.SecurityPermission;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.wfoas.gh.GameHelperCoreModule;
import net.wfoas.gh.gui.GuiHandler;
import net.wfoas.gh.gui.world.GuiSetPermScreen;
import net.wfoas.gh.protected_blocks.tabs.ProtectedBlockTabChangePermission;
import net.wfoas.gh.protected_blocks.tabs.ProtectedBlockTabManipulateBlock;
import tconstruct.client.tabs.AbstractTab;

public class GuiChangePermission extends GuiScreen {
	private static final ResourceLocation BACKGROUND = new ResourceLocation("gamehelper", "gui/gui_background.png");
	protected int xSize, ySize;
	
	int __phys_posx, __phys_posy, __phys_posz;
	int guiLeft, guiTop;
	
	public GuiChangePermission(int physx, int physy, int physz) {
		this.xSize = 248;
		this.ySize = 184;
		guiLeft = (this.width - this.xSize) / 2;
		guiTop = (this.height - this.ySize) / 2;
		this.__phys_posx = physx;
		this.__phys_posy = physy;
		this.__phys_posz = physz;
	}

	@Override
	public void initGui() {
		super.initGui();
		AbstractTab ab = new ProtectedBlockTabManipulateBlock(0, 0, 0, __phys_posx, __phys_posy, __phys_posz,
				Item.getItemFromBlock((Block) GameHelperCoreModule.SEC_FURNACE), GuiHandler.PROTECTED_FURNACE);
		ab.xPosition = (this.guiLeft + (4 - 2) * 28);
		ab.yPosition = (this.guiTop - 28);
		this.buttonList.add(ab);
		// ownership
		AbstractTab at = new ProtectedBlockTabChangePermission(1, 20, 0, __phys_posx, __phys_posy, __phys_posz).main();
		at.xPosition = (this.guiLeft + (5 - 2) * 28);
		at.yPosition = (this.guiTop - 28);
		this.buttonList.add(at);
	}

	@Override
	public void drawBackground(int tint) {
//		super.drawBackground(tint);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(BACKGROUND);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(BACKGROUND);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public boolean doesGuiPauseGame()
    {
        return false;
    }
}
