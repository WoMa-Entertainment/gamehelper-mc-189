package net.wfoas.gh.gui.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class OverlayDoubleTexToggleButton extends OverlayTexToggleButton {

	ItemStack _2, _3;

	/**
	 * @param _2ndTex
	 *            : not selected ;
	 * @param _3rdTex
	 *            : selected
	 */
	public OverlayDoubleTexToggleButton(int buttonId, int x, int y, String text, ItemStack _2ndTex, ItemStack _3rdTex) {
		super(buttonId, x, y, text, _2ndTex);
		_2 = _2ndTex;
		_3 = _3rdTex;
	}

	@Override
	public void setSelected(boolean t) {
		super.setSelected(t);
		if (t) {
			super.overLayTex = _3;
		} else {
			super.overLayTex = _2;
		}
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
//			if (isSelected()) {
//				// GlStateManager.disableLighting();
//				// GlStateManager.disableDepth();
//				int j1 = xPosition + 1;
//				int k1 = yPosition + 1;
//				// GlStateManager.colorMask(true, true, true, false);
//				drawRect(j1, k1, j1 + 16, k1 + 16, -2130706433);
//				// GlStateManager.colorMask(true, true, true, true);
//				// GlStateManager.enableLighting();
//				// GlStateManager.enableDepth();
//			}
			drawStepTwo(mc, mouseX, mouseY);
		}
	}

}
