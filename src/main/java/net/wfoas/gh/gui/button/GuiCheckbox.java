package net.wfoas.gh.gui.button;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.item.ItemStack;
import net.wfoas.gh.gui.itemtex.GuiItemTexture;
import net.wfoas.gh.items.TradeItems;

public class GuiCheckbox extends GuiButton {
	private boolean isChecked;
	static GuiItemTexture CHECKED = new GuiItemTexture(0, 0, new ItemStack(TradeItems.OK_ITEM)),
			NOT_CHECKED = new GuiItemTexture(0, 0, new ItemStack(TradeItems.NOT_OK_ITEM));

	public GuiCheckbox(int id, int xPosition, int yPosition, boolean initialState) {
		super(id, xPosition, yPosition, 18, 18, "");
		isChecked = initialState;
	}

	public GuiCheckbox(int id, int xPosition, int yPosition) {
		this(id, xPosition, yPosition, false);
	}

	public void setChecked(boolean checked) {
		isChecked = checked;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void drawButton(Minecraft par1Minecraft, int xMousePosition, int yMousePosition) {
		int spriteX = 0;
		int spriteY = isChecked ? 18 : 0;
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		CHECKED.xPosition = xPosition;
		CHECKED.yPosition = yPosition;
		// drawGradientRect(xPosition, yPosition, xPosition + 18, yPosition +
		// 18, startColor, endColor);
		// drawTexturedModalRect(xPosition, yPosition, spriteX, spriteY, 17,
		// 17);
	}
}