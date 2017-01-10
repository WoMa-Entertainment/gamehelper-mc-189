package net.wfoas.gh.gui;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.dropsapi.pdr.ChatColor;
import net.wfoas.gh.gui.button.InvisibleButton;

public class GHProgressDialog extends GuiScreen {

	protected int xSize, ySize;

	private static final ResourceLocation BACKGROUND = new ResourceLocation("gamehelper", "gui/gui_background.png");
	private static final ResourceLocation PROGRESS_BAR = new ResourceLocation("gamehelper", "gui/progress_bar.png");

	protected String title = "...", msg = "...";

	protected float percentage = 0f;

	protected boolean closeable = true;

	public GHProgressDialog() {
		GameHelper.getUtils().progressDialog = this;
		this.xSize = 248;
		this.ySize = 184;
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if (GuiScreen.isShiftKeyDown() && keyCode == 1) {
			close();
		}
		if (closeable)
			super.keyTyped(typedChar, keyCode);
		else
			;
	}

	InvisibleButton dismiss;

	@Override
	public void initGui() {
		super.initGui();
		dismiss = new InvisibleButton(0, this.width / 2 - ySize / 2, this.height / 2 + 60,
				I18n.format("gamehelper.dialog.dismiss"));
		dismiss.xPosition = this.width / 2 - dismiss.width / 2;
		super.buttonList.add(dismiss);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	public void drawBackground() {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(BACKGROUND);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	public void setVisible(boolean visibility) {
		if (!visibility)
			close();
		else
			;
	}

	public void setCloseOption(boolean closeoption) {
		this.closeable = closeoption;
		dismiss.setVisible(closeoption);
	}

	public void setTitle(String title) {
		this.title = I18n.format(title);
	}

	public void setMessage(String msg) {
		this.msg = I18n.format(msg);
	}

	@Override
	public void drawScreen(int x, int y, float par3) {
		this.drawBackground();
		super.drawScreen(x, y, par3);
		this.fontRendererObj.drawString(ChatColor.DARK_GRAY + title, this.width / 2 - 115, this.height / 2 - 85, 0);
		this.fontRendererObj.drawString(ChatColor.DARK_GRAY + msg,
				this.width / 2 - fontRendererObj.getStringWidth(msg) / 2, this.height / 2 - 35, 0);
		this.mc.renderEngine.bindTexture(PROGRESS_BAR);
		this.drawTexturedModalRect(this.width / 2 - 224 / 2, this.height / 2 - 5, 0, 0, 224, 11);
		GlStateManager.color(1, 1, 1, 1);
		this.drawTexturedModalRect(this.width / 2 - 224 / 2 + 1, this.height / 2 - 5 + 1, 0, 11,
				(int) (222 * (percentage)), 9);
		this.fontRendererObj.drawString(ChatColor.DARK_GRAY + String.valueOf((percentage * 100)) + " %",
				this.width / 2 - fontRendererObj.getStringWidth(String.valueOf((percentage * 100)) + " %") / 2,
				this.height / 2 + 10, 0);
	}

	public void close() {
		Minecraft.getMinecraft().setIngameFocus();
		GameHelper.getUtils().progressDialog = null;
	}

	@Override
	public void actionPerformed(GuiButton gb) {
		if (gb.id == 0) {
			if (!closeable)
				return;
			else
				close();
		}
	}

}
