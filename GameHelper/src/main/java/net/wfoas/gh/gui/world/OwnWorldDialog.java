package net.wfoas.gh.gui.world;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.wfoas.gh.items.TradeItems;

public class OwnWorldDialog extends GuiScreen {
	private static final ResourceLocation BACKGROUND = new ResourceLocation("gamehelper", "gui/gui_background.png");

	public static final ItemStack OK = new ItemStack(TradeItems.OK_ITEM),
			NOT_OK = new ItemStack(TradeItems.NOT_OK_ITEM);

	public void initGui() {
		super.initGui();
	}

}
