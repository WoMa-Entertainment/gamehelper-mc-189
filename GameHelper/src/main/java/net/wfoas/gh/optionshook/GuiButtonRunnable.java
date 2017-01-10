package net.wfoas.gh.optionshook;

import net.minecraft.client.gui.GuiButton;

public class GuiButtonRunnable extends GuiButton {

	public GuiButtonRunnable(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
	}

	public GuiButtonRunnable(int buttonId, int x, int y, String buttonText) {
		super(buttonId, x, y, buttonText);
	}

}
