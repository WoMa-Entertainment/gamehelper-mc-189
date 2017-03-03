package tconstruct.client.tabs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public abstract class AbstractTab extends GuiButton {
	public static final int WIDTH = 28, HEIGHT = 32;
	ResourceLocation texture = new ResourceLocation("textures/gui/container/creative_inventory/tabs.png");
	protected ItemStack renderStack;
	RenderItem itemRenderer = Minecraft.getMinecraft().getRenderItem();

	public AbstractTab(int id, int posX, int posY, ItemStack renderStack) {
		super(id, posX, posY, WIDTH, HEIGHT, "");
		this.renderStack = renderStack;
	}

	@Override
	public abstract AbstractTab clone();

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		if (this.visible) {
			if (renderStack == null) {
				// System.out.println("RenderStack == null" + this.getClass());
				return;
			}
			// System.out.println(this.getClass().toString());
			// RenderHelper.disableStandardItemLighting();
			// GlStateManager.disableLighting();
			// // glstatemanager
			// GlStateManager.enableDepth();
			// GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			//
			// int yTexPos = this.enabled ? 3 : 32;
			// int ySize = this.enabled ? 25 : 32;
			// int xOffset = this.id == 2 ? 0 : 1;
			// int yPos = this.yPosition + (this.enabled ? 3 : 0);
			//
			// mc.renderEngine.bindTexture(this.texture);
			// drawTexturedModalRect(this.xPosition, yPos, xOffset * 28,
			// yTexPos,
			// 28, ySize);
			//
			// this.zLevel = 100.0F;
			// this.itemRenderer.zLevel = 100.0F;
			// GL11.glEnable(2896);
			// GL11.glEnable(32826);
			// // GlStateManager.enableLighting();
			// // RenderHelper.enableGUIStandardItemLighting();
			// RenderHelper.enableGUIStandardItemLighting();
			// this.itemRenderer.renderItemAndEffectIntoGUI(this.renderStack,
			// this.xPosition + 6, this.yPosition + 8);
			// this.itemRenderer.renderItemOverlayIntoGUI(mc.fontRendererObj,
			// this.renderStack, this.xPosition + 6,
			// this.yPosition + 8, "");
			// RenderHelper.disableStandardItemLighting();
			// GlStateManager.disableLighting();
			// GL11.glDisable(2896);
			// GL11.glEnable(3042);
			// this.itemRenderer.zLevel = 0.0F;
			// this.zLevel = 0.0F;
			// // GL11.glDisable(GL11.GL_DEPTH_TEST);
			// GlStateManager.disableDepth();
			// GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			// GlStateManager.color(1, 1, 1, 1);
			if (this.itemRenderer == null) {
				this.itemRenderer = Minecraft.getMinecraft().getRenderItem();
				return;
			}
			RenderHelper.disableStandardItemLighting();
			int yTexPos = this.enabled ? 3 : 32;
			int ySize = this.enabled ? 25 : 32;
			int xOffset = this.id == 2 ? 0 : 1;
			int yPos = this.yPosition + (this.enabled ? 3 : 0);

			mc.renderEngine.bindTexture(this.texture);
			drawTexturedModalRect(this.xPosition, yPos, xOffset * 28, yTexPos, 28, ySize);

			RenderHelper.enableGUIStandardItemLighting();
			this.zLevel = 100.0F;
			this.itemRenderer.zLevel = 100.0F;
			// GL11.glEnable(2896); GuiContainer
			// GL11.glEnable(32826);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			this.itemRenderer.renderItemAndEffectIntoGUI(this.renderStack, this.xPosition + 6, this.yPosition + 8);
			this.itemRenderer.renderItemOverlayIntoGUI(mc.fontRendererObj, this.renderStack, this.xPosition + 6,
					this.yPosition + 8, "");
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			// GL11.glDisable(32826);
			// GL11.glDisable(2896);
			// GL11.glEnable(3042); GuiContainer
			this.itemRenderer.zLevel = 0.0F;
			this.zLevel = 0.0F;
			RenderHelper.disableStandardItemLighting();
			// GlStateManager.color(1, 1, 1);
			GlStateManager.colorMask(true, true, true, true);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
		}

	}

	@Override
	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
		boolean inWindow = (this.enabled) && (this.visible) && (mouseX >= this.xPosition) && (mouseY >= this.yPosition)
				&& (mouseX < this.xPosition + this.width) && (mouseY < this.yPosition + this.height);
		if (inWindow) {
			onTabClicked();
		}
		return inWindow;
	}

	public abstract void onTabClicked();

	public abstract boolean shouldAddToList();
}
