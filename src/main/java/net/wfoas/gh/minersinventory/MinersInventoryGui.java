package net.wfoas.gh.minersinventory;

import org.lwjgl.util.glu.Project;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class MinersInventoryGui extends GuiContainer {

	public static final ResourceLocation MINERS_INV_TEX = new ResourceLocation("gamehelper",
			"gui/container/miners_inventory.png");

	MinersInventoryContainer minersinv;

	public MinersInventoryGui(MinersInventoryContainer c) {
		super(c);
		this.minersinv = c;
		this.xSize = 176;
		this.ySize = 232;
	}

	public int ysize() {
		return ySize;
	}

	public int xsize() {
		return xSize;
	}
	
	/** The old x position of the mouse pointer */
    private float oldMouseX;
    /** The old y position of the mouse pointer */
    private float oldMouseY;

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		int k = this.guiLeft;
        int l = this.guiTop;
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GuiInventory.drawEntityOnScreen(k + 51, l + 75, 30, (float)(k + 51) - this.oldMouseX, (float)(l + 75 - 50) - this.oldMouseY, this.mc.thePlayer);
		super.drawScreen(par1, par2, par3);
//		GuiInventory.drawEntityOnScreen(22, 6, 1, par1, par2, Minecraft.getMinecraft().thePlayer);
		this.oldMouseX = (float)par1;
        this.oldMouseY = (float)par2;
	}

	@Override
	public void initGui() {
		super.initGui();
		// List<AbstractTab> l = new ArrayList();
		// TabRegistry.recalcAndAddTabsToList(this, l);GuiInventory
		// l.get(0).enabled = true;
		// l.get(1).enabled = false;
		// this.buttonList.addAll(l);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		String s = this.minersinv.minersInv.getName();
		this.fontRendererObj.drawString(s, 82, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory"), 6, this.ySize - 96 + 4 - 1, 4210752);

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(MINERS_INV_TEX);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		GlStateManager.pushMatrix();
		GlStateManager.matrixMode(5889);
		GlStateManager.pushMatrix();
		GlStateManager.loadIdentity();
		ScaledResolution scaledresolution = new ScaledResolution(this.mc);
		GlStateManager.viewport((scaledresolution.getScaledWidth() - 320) / 2 * scaledresolution.getScaleFactor(),
				(scaledresolution.getScaledHeight() - 240) / 2 * scaledresolution.getScaleFactor(),
				320 * scaledresolution.getScaleFactor(), 240 * scaledresolution.getScaleFactor());
		GlStateManager.translate(-0.34F, 0.23F, 0.0F);
		Project.gluPerspective(90.0F, 1.3333334F, 9.0F, 80.0F);
		float f1 = 1.0F;
		GlStateManager.matrixMode(5888);
		GlStateManager.loadIdentity();
		RenderHelper.enableStandardItemLighting();
		GlStateManager.translate(0.0F, 3.3F, -16.0F);
		GlStateManager.scale(f1, f1, f1);
		float f2 = 5.0F;
		GlStateManager.scale(f2, f2, f2);
		GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
		RenderHelper.disableStandardItemLighting();
		GlStateManager.matrixMode(5889);
		GlStateManager.viewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
		GlStateManager.popMatrix();
		GlStateManager.matrixMode(5888);
		GlStateManager.popMatrix();
		RenderHelper.disableStandardItemLighting();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	}
}