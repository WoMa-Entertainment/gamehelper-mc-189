package net.wfoas.gh.gui.itemtex;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiItemTexture extends Gui {

	protected static final ResourceLocation buttonTextures = new ResourceLocation("textures/gui/widgets.png");
	/** Button width in pixels */
	public int width;
	/** Button height in pixels */
	public int height;
	/** The x position of this control. */
	public int xPosition;
	/** The y position of this control. */
	public int yPosition;
	/** The string displayed on this control. */
	public ItemStack displayItemStack;
	/** Hides the button completely if false. */
	public boolean visible;
	protected boolean hovered;
	private static final String __OBFID = "CL_00000668";
	public int packedFGColour; // FML

	public GuiItemTexture(int x, int y, ItemStack item) {
		this(x, y, 16, 16, item);
	}

	public GuiItemTexture(int x, int y, int widthIn, int heightIn, ItemStack item) {
		this.width = 200;
		this.height = 20;
		this.visible = true;
		this.xPosition = x;
		this.yPosition = y;
		this.width = widthIn;
		this.height = heightIn;
		this.displayItemStack = item;
	}

	public void setTexture(ItemStack itemStack) {
		this.displayItemStack = itemStack;
	}

	/**
	 * Draws this button to the screen.
	 */
	public void draw(Minecraft mc, int mouseX, int mouseY) {
		if (this.visible) {
			// FontRenderer fontrenderer = mc.fontRendererObj;
			mc.getTextureManager().bindTexture(buttonTextures);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width
					&& mouseY < this.yPosition + this.height;
			// int k = this.getHoverState(this.hovered);
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
			GlStateManager.blendFunc(770, 771);											// color: aarrgg
			this.drawGradientRect(this.xPosition -2, this.yPosition -2, this.xPosition + 20, this.yPosition + 20, 0xffcccccc, 0xffcccccc);
			// this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46
			// + 1 * 20, this.width / 2, this.height);
			// this.drawTexturedModalRect(this.xPosition + this.width / 2,
			// this.yPosition, 200 - this.width / 2,
			// 46 + 1 * 20, this.width / 2, this.height);
			// this.mouseDragged(mc, mouseX, mouseY);
			// int l = 14737632;

			// if (packedFGColour != 0) {
			// l = packedFGColour;
			// } else if (!this.enabled) {
			// l = 10526880;
			// } else if (this.hovered) {
			// l = 16777120;
			// }

			// this.drawCenteredString(fontrenderer, this.displayString,
			// this.xPosition + this.width / 2,
			// this.yPosition + (this.height - 8) / 2, l);
			RenderHelper.enableGUIStandardItemLighting();
			// mc.getTextureManager().bindTexture(overLayTex);
			if (displayItemStack != null)
				mc.getRenderItem().renderItemAndEffectIntoGUI(displayItemStack, this.xPosition + 1, this.yPosition + 1);
			// this.drawTexturedModalRect(this.xPosition + 8, this.yPosition +
			// 8, 0, 0, 16, 16);
			RenderHelper.disableStandardItemLighting();
		}
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}