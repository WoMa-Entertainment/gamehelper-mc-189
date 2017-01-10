package net.wfoas.gh.gui.guilist;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiListExtended.IGuiListEntry;
import net.minecraft.client.renderer.GlStateManager;

public class GuiListEntryString implements IGuiListEntry {

	String list;

	public GuiListEntryString(String stringlist) {
		this.list = stringlist;
	}

	public boolean selected;

	@Override
	public void setSelected(int index, int p_178011_2_, int p_178011_3_) {
	}

	public void setSelected(boolean v) {
		selected = v;
	}

	@Override
	public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY,
			boolean isSelected) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		// Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0F, 0.0F, 32, 32,
		// 32.0F, 32.0F);
		// int i2;

		// if ((Minecraft.getMinecraft().gameSettings.touchscreen || isSelected)
		// && this.func_148310_d()) {
		// this.field_148317_a.getTextureManager().bindTexture(field_148316_c);
		// Gui.drawRect(x, y, x + 32, y + 32, -1601138544);
		// GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		// int l1 = mouseX - x;
		// i2 = mouseY - y;
		//
		// if (this.func_148309_e()) {
		// if (l1 < 32) {
		// Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0F, 32.0F, 32, 32,
		// 256.0F, 256.0F);
		// } else {
		// Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0F, 0.0F, 32, 32,
		// 256.0F, 256.0F);
		// }
		// } else {
		// if (this.func_148308_f()) {
		// if (l1 < 16) {
		// Gui.drawModalRectWithCustomSizedTexture(x, y, 32.0F, 32.0F, 32, 32,
		// 256.0F, 256.0F);
		// } else {
		// Gui.drawModalRectWithCustomSizedTexture(x, y, 32.0F, 0.0F, 32, 32,
		// 256.0F, 256.0F);
		// }
		// }
		//
		// if (this.func_148314_g()) {
		// if (l1 < 32 && l1 > 16 && i2 < 16) {
		// Gui.drawModalRectWithCustomSizedTexture(x, y, 96.0F, 32.0F, 32, 32,
		// 256.0F, 256.0F);
		// } else {
		// Gui.drawModalRectWithCustomSizedTexture(x, y, 96.0F, 0.0F, 32, 32,
		// 256.0F, 256.0F);
		// }
		// }
		//
		// if (this.func_148307_h()) {
		// if (l1 < 32 && l1 > 16 && i2 > 16) {
		// Gui.drawModalRectWithCustomSizedTexture(x, y, 64.0F, 32.0F, 32, 32,
		// 256.0F, 256.0F);
		// } else {
		// Gui.drawModalRectWithCustomSizedTexture(x, y, 64.0F, 0.0F, 32, 32,
		// 256.0F, 256.0F);
		// }
		// }
		// }
		// }

		String s = this.list;
		int i2 = Minecraft.getMinecraft().fontRendererObj.getStringWidth(s);

		// if (i2 > 157) {
		// s = this.field_148317_a.fontRendererObj.trimStringToWidth(s,
		// 157 - this.field_148317_a.fontRendererObj.getStringWidth("...")) +
		// "...";
		// }

		Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(s, (float) (x + 32 + 2), (float) (y + 1),
				16777215);
		// List list =
		// this.field_148317_a.fontRendererObj.listFormattedStringToWidth(this.func_148311_a(),
		// 157);
		//
		// for (int j2 = 0; j2 < 2 && j2 < list.size(); ++j2) {
		// this.field_148317_a.fontRendererObj.drawStringWithShadow((String)
		// list.get(j2), (float) (x + 32 + 2),
		// (float) (y + 12 + 10 * j2), 8421504);
		// }
	}

	@Override
	public boolean mousePressed(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
		return true;
	}

	@Override
	public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {

	}

}
