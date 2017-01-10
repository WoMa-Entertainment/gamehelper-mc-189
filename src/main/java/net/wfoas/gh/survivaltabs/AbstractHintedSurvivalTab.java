package net.wfoas.gh.survivaltabs;

import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import tconstruct.client.tabs.AbstractTab;

/**
 * This is an extension to the Tinkers' Construct Survival-Tabs Api. This
 * extension draws a hovering text like the creativetab does.
 */
public abstract class AbstractHintedSurvivalTab extends AbstractTab {

	List hintList;

	public AbstractHintedSurvivalTab(int id, int posX, int posY, ItemStack renderStack, String... tooltip) {
		super(id, posX, posY, renderStack);
		this.hintList = Lists.newArrayList(tooltip);
		if (tooltip == null) {
			hintList = null;
		}
		if (tooltip.length == 0)
			hintList = null;
	}

	@Override
	public abstract void onTabClicked();

	@Override
	public abstract boolean shouldAddToList();

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		super.drawButton(mc, mouseX, mouseY);
		// RenderHelper.disableStandardItemLighting();
		// GlStateManager.disableLighting();
		// RenderHelper.enableGUIStandardItemLighting();
		// GlStateManager.colorMask(true, true, true, false);
		// // GlStateManager.enableLighting(); GuiContainer
		// GlStateManager.color(1.0F, 1.0F, 1.0F, 0.0F);
		// GlStateManager.enableAlpha();
		// GlStateManager.enableDepth();
		if (this.visible) {
			boolean inWindow = (this.enabled) && (this.visible) && (mouseX >= this.xPosition)
					&& (mouseY >= this.yPosition) && (mouseX < this.xPosition + this.width)
					&& (mouseY < this.yPosition + this.height);
			if (inWindow) {
				this.drawHoveringText(hintList, mouseX + width, mouseY - height);
			}
		}
		// super.drawButton(mc, mouseX, mouseY);
		// RenderHelper.disableStandardItemLighting();
		// GlStateManager.disableLighting();
		// RenderHelper.enableGUIStandardItemLighting();
		// GlStateManager.colorMask(true, true, true, false);
		// // GlStateManager.enableLighting();
		// GlStateManager.colorMask(true, true, true, false);
		// // GlStateManager.enableLighting(); GuiContainer
		// GlStateManager.color(1.0F, 1.0F, 1.0F, 0.0F);
		// GlStateManager.enableAlpha();
		// GlStateManager.enableDepth();
		// // RenderHelper.disableStandardItemLighting();
		// // GlStateManager.disableLighting();
	}

	public void drawHoveringText(List textLines, int x, int y) {
		drawHoveringText(textLines, x, y, Minecraft.getMinecraft().fontRendererObj);
	}

	public void drawHoveringText(List textLines, int x, int y, FontRenderer font) {
		if (textLines == null)
			return;
		if (!textLines.isEmpty()) {
			GlStateManager.disableRescaleNormal();
			RenderHelper.disableStandardItemLighting();
			GlStateManager.disableLighting();
			GlStateManager.disableDepth();
			int k = 0;
			Iterator iterator = textLines.iterator();

			while (iterator.hasNext()) {
				String s = (String) iterator.next();
				int l = font.getStringWidth(s);

				if (l > k) {
					k = l;
				}
			}

			int j2 = x + 12 - 10;
			int k2 = y - 12 + 20;
			int i1 = 8;

			if (textLines.size() > 1) {
				i1 += (textLines.size() - 1) * 10;
			}

			// if (j2 + k > this.width) {
			// j2 -= 28 + k;
			// }
			//
			// if (k2 + i1 + 6 > this.height) {
			// k2 = this.height - i1 - 6;
			// }

			this.zLevel = 300.0F;
			int j1 = -267386864;
			this.drawGradientRect(j2 - 3, k2 - 4, j2 + k + 3, k2 - 3, j1, j1);
			this.drawGradientRect(j2 - 3, k2 + i1 + 3, j2 + k + 3, k2 + i1 + 4, j1, j1);
			this.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 + i1 + 3, j1, j1);
			this.drawGradientRect(j2 - 4, k2 - 3, j2 - 3, k2 + i1 + 3, j1, j1);
			this.drawGradientRect(j2 + k + 3, k2 - 3, j2 + k + 4, k2 + i1 + 3, j1, j1);
			int k1 = 1347420415;
			int l1 = (k1 & 16711422) >> 1 | k1 & -16777216;
			this.drawGradientRect(j2 - 3, k2 - 3 + 1, j2 - 3 + 1, k2 + i1 + 3 - 1, k1, l1);
			this.drawGradientRect(j2 + k + 2, k2 - 3 + 1, j2 + k + 3, k2 + i1 + 3 - 1, k1, l1);
			this.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 - 3 + 1, k1, k1);
			this.drawGradientRect(j2 - 3, k2 + i1 + 2, j2 + k + 3, k2 + i1 + 3, l1, l1);

			for (int i2 = 0; i2 < textLines.size(); ++i2) {
				String s1 = (String) textLines.get(i2);
				font.drawStringWithShadow(s1, j2, k2, -1);

				// if (i2 == 0) {
				// k2 += 2;
				// }

				k2 += 10;
			}

			this.zLevel = 0.0F;
			GlStateManager.enableDepth();
			GlStateManager.enableRescaleNormal();
			RenderHelper.enableGUIStandardItemLighting();
		}
	}

	@Override
	public AbstractHintedSurvivalTab clone() {
		return new AbstractHintedSurvivalTab(this.id, this.xPosition, this.yPosition, renderStack,
				hintList == null ? (String[]) null : (String[]) hintList.toArray(new String[hintList.size()])) {

			@Override
			public boolean shouldAddToList() {
				return AbstractHintedSurvivalTab.this.shouldAddToList();
			}

			@Override
			public void onTabClicked() {
				AbstractHintedSurvivalTab.this.onTabClicked();
			}
		};
	}

}
