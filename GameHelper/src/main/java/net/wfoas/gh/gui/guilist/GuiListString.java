package net.wfoas.gh.gui.guilist;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.ResourcePackListEntry;
import net.minecraft.util.EnumChatFormatting;

public class GuiListString extends GuiListExtended {
	List<GuiListEntryString> list;
	protected static final String DEFAULT_LIST_HEADER = "gamehelper.gui.list.header";
	String list_header = DEFAULT_LIST_HEADER;

	public GuiListString(Minecraft mcIn, int width, int height, int topIn, int bottomIn, int slotHeightIn,
			List<GuiListEntryString> stringlist) {
		super(mcIn, width, height, topIn, bottomIn, slotHeightIn);
		this.list = stringlist;
	}

	public static GuiListString create(Minecraft mcIn, int width, int height, int topIn, int bottomIn, int slotHeightIn,
			List<String> stringlist) {
		return new GuiListString(mcIn, width, height, topIn, bottomIn, slotHeightIn,
				convertStringListToGuiListEntryStringList(stringlist));
	}

	private static List<GuiListEntryString> convertStringListToGuiListEntryStringList(List<String> stringlist) {
		List<GuiListEntryString> guilist = new ArrayList<GuiListEntryString>();
		for (String toAdd : stringlist) {
			guilist.add(new GuiListEntryString(toAdd));
		}
		return guilist;
	}

	protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {
		for (int i = 0; i < getList().size(); i++) {
			if (i != slotIndex)
				getList().get(i).setSelected(false);
			else
				getList().get(i).setSelected(true);
		}
	}

	protected void func_178040_a(int p_178040_1_, int p_178040_2_, int p_178040_3_) {
		getList().get(p_178040_1_).setSelected(p_178040_1_, p_178040_2_, p_178040_3_);
	}

	protected boolean isSelected(int slotIndex) {
		return false;
	}

	@Override
	public GuiListEntryString getListEntry(int index) {
		return getList().get(index);
	}

	public List<GuiListEntryString> getList() {
		return this.list;
	}

	@Override
	protected int getSize() {
		return this.getList().size();
	}

	protected void drawListHeader(int p_148129_1_, int p_148129_2_, Tessellator p_148129_3_) {
		String s = EnumChatFormatting.UNDERLINE + "" + EnumChatFormatting.BOLD + this.getListHeader();
		this.mc.fontRendererObj.drawString(s,
				p_148129_1_ + this.width / 2 - this.mc.fontRendererObj.getStringWidth(s) / 2,
				Math.min(this.top + 3, p_148129_2_), 16777215);
	}

	protected String getListHeader() {
		return list_header;
	}

	public int getListWidth() {
		return this.width;
	}

	protected int getScrollBarX() {
		return this.right - 6;
	}

}
