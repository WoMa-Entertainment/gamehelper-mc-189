package net.wfoas.gh.changechanter;

import org.lwjgl.util.glu.Project;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.wfoas.gh.dropsapi.pdr.ChatColor;
import net.wfoas.gh.gui.GuiHandler;

public class ChangeChanterGui extends GuiContainer {
	private static final ResourceLocation ENCHANTMENT_TABLE_GUI_TEXTURE = new ResourceLocation("gamehelper",
			"gui/container/enchantment_altar.png");

	public static final int GUI_ID = GuiHandler.ENCHANTMENT_ALTAR_GUI;
	ChangeChanterContainer container;

	public ChangeChanterGui(InventoryPlayer inventory) {
		super(new ChangeChanterContainer(inventory));
		container = (ChangeChanterContainer) this.inventorySlots;
		this.xSize = 256;
		this.ySize = 194;
	}

	@Override
	public void initGui() {
		super.initGui();
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(ENCHANTMENT_TABLE_GUI_TEXTURE);
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

	public static int colorToOneInt(float r, float g, float b, float a) {
		int red = (int) (r * 255);
		red = red << 16;
		// (color >> 16 & 255);
		int blue = (int) (b * 255);
		blue = blue << 8;
		// int blue = (color >> 8 & 255) * 255.0F;
		int green = (int) (g * 255);
		// green = green << 24;
		// int green = (color & 255) * 255.0F;
		int alpha = (int) (a * 255);
		alpha = alpha << 24;
		// int alpha = (color >> 24 & 255) * 255.0F;
		int color = red & green & blue & alpha;
		return color;
	}

	public static String getName(Enchantment ench) {
		if (ench == null) {
			return ChatColor.GREEN + "-";
		}
		return
		// + I18n.format("text.gamehelper.enchantment")
		StatCollector.translateToLocal(ench.getName());
	}

	@Override
	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.fontRendererObj.drawString(I18n.format("container.enchantmentaltar", new Object[0]), 16, 7, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 16, this.ySize - 96 + 2,
				4210752);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
}
