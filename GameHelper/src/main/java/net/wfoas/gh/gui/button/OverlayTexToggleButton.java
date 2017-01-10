package net.wfoas.gh.gui.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value = Side.CLIENT)
public class OverlayTexToggleButton extends ToggleButton {

	public ItemStack overLayTex;

	public OverlayTexToggleButton(int buttonId, int x, int y, String text, ItemStack _2ndTex) {
		super(buttonId, x, y, text);
		overLayTex = _2ndTex;
	}

	public void setDisplayStringExplicitely(String text) {
		this.displayString = text;
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		if (this.visible) {
			FontRenderer fontrenderer = mc.fontRendererObj;
			mc.getTextureManager().bindTexture(buttonTextures);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width
					&& mouseY < this.yPosition + this.height;
			int k = this.getHoverState(this.hovered);
			// if(isSelected()){
			// k = 2;
			// }
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
			GlStateManager.blendFunc(770, 771);
			this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 + k * 20, this.width / 2, this.height);
			this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2,
					46 + k * 20, this.width / 2, this.height);
			this.mouseDragged(mc, mouseX, mouseY);
			int l = 14737632;

			if (packedFGColour != 0) {
				l = packedFGColour;
			} else if (!this.enabled) {
				l = 10526880;
			} else if (this.hovered) {
				l = 16777120;
			}

			this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2,
					this.yPosition + (this.height - 8) / 2, l);
			if (isSelected()) {
				// GlStateManager.disableLighting();
				// GlStateManager.disableDepth();
				int j1 = xPosition + 1;
				int k1 = yPosition + 1;
				// GlStateManager.colorMask(true, true, true, false);
				drawRect(j1, k1, j1 + 16, k1 + 16, -2130706433);
				// GlStateManager.colorMask(true, true, true, true);
				// GlStateManager.enableLighting();
				// GlStateManager.enableDepth();
			}
			drawStepTwo(mc, mouseX, mouseY);
		}
	}

	// public void drawButton(Minecraft mc, int mouseX, int mouseY) {
	// if (this.visible) {
	// FontRenderer fontrenderer = mc.fontRendererObj;
	// // mc.getTextureManager().bindTexture(BUTTON_TEX);
	// GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	// this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition &&
	// mouseX < this.xPosition + this.width
	// && mouseY < this.yPosition + this.height;
	// int k = this.getHoverState(this.hovered);
	// GlStateManager.enableBlend();
	// GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
	// GlStateManager.blendFunc(770, 771);
	// this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 + k *
	// 20, this.width / 2, this.height);
	// this.drawTexturedModalRect(this.xPosition + this.width / 2,
	// this.yPosition, 200 - this.width / 2,
	// 46 + k * 20, this.width / 2, this.height);
	// this.mouseDragged(mc, mouseX, mouseY);
	// int l = 14737632;
	//
	// if (packedFGColour != 0) {
	// l = packedFGColour;
	// } else if (!this.enabled) {
	// l = 10526880;
	// } else if (this.hovered) {
	// l = 16777120;
	// }
	//
	// this.drawCenteredString(fontrenderer, this.displayString, this.xPosition
	// + this.width / 2,
	// this.yPosition + (this.height - 8) / 2, l);
	//// drawStepTwo(mc, mouseX, mouseY);
	// }
	// }

	@Override
	public void drawStepTwo(Minecraft mc, int mouseX, int mouseY) {
		if (this.visible) {
			// RenderHelper.enableStandardItemLighting();#
			RenderHelper.enableGUIStandardItemLighting();
			// mc.getTextureManager().bindTexture(overLayTex);
			mc.getRenderItem().renderItemAndEffectIntoGUI(overLayTex, this.xPosition + 1, this.yPosition + 1);
			// this.drawTexturedModalRect(this.xPosition + 8, this.yPosition +
			// 8, 0, 0, 16, 16);
			RenderHelper.disableStandardItemLighting();
		}
	}
}
