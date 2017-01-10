package tconstruct.client.tabs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.wfoas.gh.survivaltabs.AbstractHintedSurvivalTab;

public class InventoryTabVanilla extends AbstractHintedSurvivalTab {
	public InventoryTabVanilla() {
		super(0, 0, 0, new ItemStack(Blocks.chest), I18n.format("container.inventory"));
	}

	@Override
	public void onTabClicked() {
		Minecraft.getMinecraft().displayGuiScreen(new GuiInventory(Minecraft.getMinecraft().thePlayer));
	}

	@Override
	public boolean shouldAddToList() {
		return true;
	}
}
