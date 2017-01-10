package net.wfoas.gh.gui.guilist;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.lwjgl.input.Mouse;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.Language;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiList extends GuiSlot {
	/** A list containing the many different locale language codes. */
	private final java.util.List<String> stringList = Lists.newArrayList();
	/** The map containing the Locale-Language pairs. */
	private final Map languageMap = Maps.newHashMap();
	ActionListener actionListener;
	GuiScreen parent;
	String header;

	public GuiList(List<String> slist, int width, int height, int topIn, int bottomIn, int slotHeightIn,
			ActionListener elementClicked, GuiScreen parent, int leftIn) {
		super(Minecraft.getMinecraft(), width, height, topIn, bottomIn, slotHeightIn);
		stringList.addAll(slist);
		this.actionListener = elementClicked;
		this.parent = parent;
		this.left = leftIn;
		this.right = leftIn + width;
		if (slist.size() != 0)
			elementClicked(0, false, 0, 0);
		// _initelementClicked(0, false, 0, 0, slist);
	}

	public void setHeader(String header) {
		this.header = header;
		this.setHasListHeader(header != null, 16);
	}

	protected int getSize() {
		return this.stringList.size();
	}

	/**
	 * The element in the slot that was clicked, boolean for whether it was
	 * double clicked or not
	 */

	String selectedString;
	int selectedIndex;

	public String getSelectedString() {
		return selectedString;
	}

	public String getEntryByIndex(int index) {
		return this.stringList.get(index);
	}

	protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {
		selectedIndex = slotIndex;
		selectedString = stringList.get(slotIndex);
		actionListener.actionPerformed(this, slotIndex, isDoubleClick, mouseX, mouseY);
	}

	protected void _initelementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY,
			List<String> list) {
		selectedIndex = slotIndex;
		selectedString = list.get(slotIndex);
		actionListener.actionPerformed(this, slotIndex, isDoubleClick, mouseX, mouseY);
	}

	/**
	 * Returns true if the element passed in is currently selected
	 */
	protected boolean isSelected(int slotIndex) {
		return slotIndex == selectedIndex;
	}

	/**
	 * Return the height of the content being scrolled
	 */
	protected int getContentHeight() {
		return this.getSize() * 18;
	}

	public int getListWidth() {
		return this.width;
	}

	public void drawCenteredString(FontRenderer fontRendererIn, String text, int x, int y, int color) {
		fontRendererIn.drawStringWithShadow(text, (float) (x - fontRendererIn.getStringWidth(text) / 2), (float) y,
				color);
	}

	protected void drawBackground() {
		// Gui
		// GuiLanguage.this.drawDefaultBackground();
		Gui.drawRect(left, top, right, bottom, 0xff555555);
	}

	protected void drawSlot(int entryID, int p_180791_2_, int p_180791_3_, int p_180791_4_, int p_180791_5_,
			int p_180791_6_) {
		// GuiLanguage.this.fontRendererObj.setBidiFlag(true);
		if (!(p_180791_3_ + 1 < top) && !(p_180791_3_ + 10 > bottom))
			drawCenteredString(Minecraft.getMinecraft().fontRendererObj, stringList.get(entryID), left + this.width / 2,
					p_180791_3_ + 1, 16777215);
		// GuiLanguage.this.fontRendererObj
		// .setBidiFlag(GuiLanguage.this.languageManager.getCurrentLanguage().isBidirectional());
	}

	public static interface ActionListener {
		public void actionPerformed(GuiList guiList, int slotIndex, boolean isDoubleClick, int mouseX, int mouseY);
	}

	protected void drawListHeader(int p_148129_1_, int p_148129_2_, Tessellator p_148129_3_) {
		if (!(Math.min(this.top + 3, p_148129_2_) < top)) {
			String s = EnumChatFormatting.UNDERLINE + "" + EnumChatFormatting.BOLD + this.header;
			Minecraft.getMinecraft().fontRendererObj.drawString(s,
					p_148129_1_ + this.width / 2 - this.mc.fontRendererObj.getStringWidth(s) / 2,
					Math.min(this.top + 3, p_148129_2_), 16777215);
		}
	}

	protected int getScrollBarX() {
		return this.width + this.left;
	}

	public void drawScreen(int mouseXIn, int mouseYIn, float partialTicks) {
		if (this.field_178041_q) {
			this.mouseX = mouseXIn;
			this.mouseY = mouseYIn;
			this.drawBackground();
			int k = this.getScrollBarX();
			int l = k + 6;
			this.bindAmountScrolled();
			GlStateManager.disableLighting();
			GlStateManager.disableFog();
			Tessellator tessellator = Tessellator.getInstance();
			WorldRenderer worldrenderer = tessellator.getWorldRenderer();
			// this.drawContainerBackground(tessellator);
			int i1 = this.left + this.width / 2 - this.getListWidth() / 2;
			int j1 = this.top + 4 - (int) this.amountScrolled;

			if (this.hasListHeader) {
				this.drawListHeader(i1, j1, tessellator);
			}

			this.drawSelectionBox(i1, j1, mouseXIn, mouseYIn);
			GlStateManager.disableDepth();
			byte b0 = 4;
			// this.overlayBackground(0, this.top, 255, 255);
			// this.overlayBackground(this.bottom, this.height, 255, 255);
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
			GlStateManager.disableAlpha();
			GlStateManager.shadeModel(7425);
			GlStateManager.disableTexture2D();
//			worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
//			worldrenderer.pos((double) this.left, (double) (this.top + i1), 0.0D).tex(0.0D, 1.0D).color(0, 0, 0, 0)
//					.endVertex();
//			worldrenderer.pos((double) this.right, (double) (this.top + i1), 0.0D).tex(1.0D, 1.0D).color(0, 0, 0, 0)
//					.endVertex();
//			worldrenderer.pos((double) this.right, (double) this.top, 0.0D).tex(1.0D, 0.0D).color(0, 0, 0, 255)
//					.endVertex();
//			worldrenderer.pos((double) this.left, (double) this.top, 0.0D).tex(0.0D, 0.0D).color(0, 0, 0, 255)
//					.endVertex();
//			tessellator.draw();
//			worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
//			worldrenderer.pos((double) this.left, (double) this.bottom, 0.0D).tex(0.0D, 1.0D).color(0, 0, 0, 255)
//					.endVertex();
//			worldrenderer.pos((double) this.right, (double) this.bottom, 0.0D).tex(1.0D, 1.0D).color(0, 0, 0, 255)
//					.endVertex();
//			worldrenderer.pos((double) this.right, (double) (this.bottom - i1), 0.0D).tex(1.0D, 0.0D).color(0, 0, 0, 0)
//					.endVertex();
//			worldrenderer.pos((double) this.left, (double) (this.bottom - i1), 0.0D).tex(0.0D, 0.0D).color(0, 0, 0, 0)
//					.endVertex();
//			tessellator.draw();
			int k1 = this.func_148135_f();

			if (k1 > 0) {
				int k11 = (this.bottom - this.top) * (this.bottom - this.top) / this.getContentHeight();
				k11 = MathHelper.clamp_int(k11, 32, this.bottom - this.top - 8);
				int l11 = (int) this.amountScrolled * (this.bottom - this.top - k11) / k1 + this.top;

				if (l11 < this.top) {
					l11 = this.top;
				}
				k = this.left + this.width;
				l = k + 6;
				int i = this.getScrollBarX();
				int j = i + 6;
				worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
				worldrenderer.pos((double) i, (double) this.bottom, 0.0D).tex(0.0D, 1.0D).color(0, 0, 0, 255)
						.endVertex();
				worldrenderer.pos((double) j, (double) this.bottom, 0.0D).tex(1.0D, 1.0D).color(0, 0, 0, 255)
						.endVertex();
				worldrenderer.pos((double) j, (double) this.top, 0.0D).tex(1.0D, 0.0D).color(0, 0, 0, 255).endVertex();
				worldrenderer.pos((double) i, (double) this.top, 0.0D).tex(0.0D, 0.0D).color(0, 0, 0, 255).endVertex();
				tessellator.draw();
				worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
				worldrenderer.pos((double) i, (double) (l11 + k11), 0.0D).tex(0.0D, 1.0D).color(128, 128, 128, 255)
						.endVertex();
				worldrenderer.pos((double) j, (double) (l11 + k11), 0.0D).tex(1.0D, 1.0D).color(128, 128, 128, 255)
						.endVertex();
				worldrenderer.pos((double) j, (double) l11, 0.0D).tex(1.0D, 0.0D).color(128, 128, 128, 255).endVertex();
				worldrenderer.pos((double) i, (double) l11, 0.0D).tex(0.0D, 0.0D).color(128, 128, 128, 255).endVertex();
				tessellator.draw();
				worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
				worldrenderer.pos((double) i, (double) (l11 + k11 - 1), 0.0D).tex(0.0D, 1.0D).color(192, 192, 192, 255)
						.endVertex();
				worldrenderer.pos((double) (j - 1), (double) (l11 + k11 - 1), 0.0D).tex(1.0D, 1.0D)
						.color(192, 192, 192, 255).endVertex();
				worldrenderer.pos((double) (j - 1), (double) l11, 0.0D).tex(1.0D, 0.0D).color(192, 192, 192, 255)
						.endVertex();
				worldrenderer.pos((double) i, (double) l11, 0.0D).tex(0.0D, 0.0D).color(192, 192, 192, 255).endVertex();
				tessellator.draw();
			}

			this.func_148142_b(mouseXIn, mouseYIn);
			GlStateManager.enableTexture2D();
			GlStateManager.shadeModel(7424);
			GlStateManager.enableAlpha();
			GlStateManager.disableBlend();
		}
	}

	protected void drawSelectionBox(int p_148120_1_, int p_148120_2_, int p_148120_3_, int p_148120_4_) {
		int i1 = this.getSize();
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldrenderer = tessellator.getWorldRenderer();

		for (int j1 = 0; j1 < i1; ++j1) {
			int k1 = p_148120_2_ + j1 * this.slotHeight + this.headerPadding;
			int l1 = this.slotHeight;

			if (k1 > this.bottom || k1 + l1 < this.top) {
				this.func_178040_a(j1, p_148120_1_, k1);
				// return;
			}

			if (this.showSelectionBox && this.isSelected(j1) && (!(k1 + 10 > this.bottom || k1 + l1 - 10 < this.top))) {
				int i = this.left + (this.width / 2 - this.getListWidth() / 2);
				int j = this.left + this.width / 2 + this.getListWidth() / 2;
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				GlStateManager.disableTexture2D();
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				GlStateManager.disableTexture2D();
				worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
				worldrenderer.pos((double) i, (double) (k1 + l1 + 2), 0.0D).tex(0.0D, 1.0D).color(128, 128, 128, 255)
						.endVertex();
				worldrenderer.pos((double) j, (double) (k1 + l1 + 2), 0.0D).tex(1.0D, 1.0D).color(128, 128, 128, 255)
						.endVertex();
				worldrenderer.pos((double) j, (double) (k1 - 2), 0.0D).tex(1.0D, 0.0D).color(128, 128, 128, 255)
						.endVertex();
				worldrenderer.pos((double) i, (double) (k1 - 2), 0.0D).tex(0.0D, 0.0D).color(128, 128, 128, 255)
						.endVertex();
				worldrenderer.pos((double) (i + 1), (double) (k1 + l1 + 1), 0.0D).tex(0.0D, 1.0D).color(0, 0, 0, 255)
						.endVertex();
				worldrenderer.pos((double) (j - 1), (double) (k1 + l1 + 1), 0.0D).tex(1.0D, 1.0D).color(0, 0, 0, 255)
						.endVertex();
				worldrenderer.pos((double) (j - 1), (double) (k1 - 1), 0.0D).tex(1.0D, 0.0D).color(0, 0, 0, 255)
						.endVertex();
				worldrenderer.pos((double) (i + 1), (double) (k1 - 1), 0.0D).tex(0.0D, 0.0D).color(0, 0, 0, 255)
						.endVertex();
				tessellator.draw();
				GlStateManager.enableTexture2D();
			}

			this.drawSlot(j1, p_148120_1_, k1, l1, p_148120_3_, p_148120_4_);
		}
	}

	protected void bindAmountScrolled() {
		int i = this.func_148135_f();

		if (i < 0) {
			i /= 2;
		}

		if (!this.field_148163_i && i < 0) {
			i = 0;
		}

		this.amountScrolled = MathHelper.clamp_float(this.amountScrolled, 0.0F, (float) i /*- height + 20*/);
	}

	public void handleMouseInput() {
		if (this.isMouseYWithinSlotBounds(this.mouseY)) {
			if (Mouse.isButtonDown(0) && this.getEnabled()) {
				if (this.initialClickY == -1.0F) {
					boolean flag = true;

					if (this.mouseY >= this.top && this.mouseY <= this.bottom) {
						int i = this.width / 2 - this.getListWidth() / 2 + this.left;
						int j = this.width / 2 + this.getListWidth() / 2 + this.left;
						int k = this.mouseY - this.top - this.headerPadding + (int) this.amountScrolled - 4;
						int l = k / this.slotHeight;

						if (this.mouseX >= i && this.mouseX <= j && l >= 0 && k >= 0 && l < this.getSize()) {
							boolean flag1 = l == this.selectedElement
									&& Minecraft.getSystemTime() - this.lastClicked < 250L;
							this.elementClicked(l, flag1, this.mouseX, this.mouseY);
							this.selectedElement = l;
							this.lastClicked = Minecraft.getSystemTime();
						} else if (this.mouseX >= i && this.mouseX <= j && k < 0) {
							this.func_148132_a(this.mouseX - i, this.mouseY - this.top + (int) this.amountScrolled - 4);
							flag = false;
						}

						int i2 = this.getScrollBarX();
						int i1 = i2 + 6;

						if (this.mouseX >= i2 && this.mouseX <= i1) {
							this.scrollMultiplier = -1.0F;
							int j1 = this.func_148135_f();

							if (j1 < 1) {
								j1 = 1;
							}

							int k1 = (int) ((float) ((this.bottom - this.top) * (this.bottom - this.top))
									/ (float) this.getContentHeight());
							k1 = MathHelper.clamp_int(k1, 32, this.bottom - this.top - 8);
							this.scrollMultiplier /= (float) (this.bottom - this.top - k1) / (float) j1;
						} else {
							this.scrollMultiplier = 1.0F;
						}

						if (flag) {
							this.initialClickY = this.mouseY;
						} else {
							this.initialClickY = -2;
						}
					} else {
						this.initialClickY = -2;
					}
				} else if (this.initialClickY >= 0.0F) {
					this.amountScrolled -= ((float) this.mouseY - this.initialClickY) * this.scrollMultiplier;
					this.initialClickY = this.mouseY;
				}
			} else {
				this.initialClickY = -1;
			}

			int l1 = Mouse.getEventDWheel();

			if (l1 != 0) {
				if (l1 > 0) {
					l1 = -1;
				} else if (l1 < 0) {
					l1 = 1;
				}

				this.amountScrolled += (float) (l1 * this.slotHeight / 2) / 2;
			}
		}
	}
}