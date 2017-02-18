package net.wfoas.gh.protected_blocks.tabs;

import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.wfoas.gh.gui.GuiHandler;
import net.wfoas.gh.network.NetworkHandler;
import net.wfoas.gh.network.packet.PacketPlayGHDynamicOpenGuiWithID;
import net.wfoas.gh.survivaltabs.AbstractHintedSurvivalTab;

public class ProtectedBlockTabManipulateBlock extends AbstractHintedSurvivalTab {

	int phys_x, phys_y, phys_z;
	int gui_prot_id;

	public ProtectedBlockTabManipulateBlock(int id, int posX, int posY, int phys_x, int phys_y, int phys_z, Item item,
			int gui_prot_id) {
		super(id, posX, posY, new ItemStack(item), I18n.format("gamehelper.protected_block"));
		this.gui_prot_id = gui_prot_id;
		this.phys_x = phys_x;
		this.phys_y = phys_y;
		this.phys_z = phys_z;
	}

	public ProtectedBlockTabManipulateBlock main() {
		this.enabled = false;
		return this;
	}

	@Override
	public AbstractHintedSurvivalTab clone() {
		return new ProtectedBlockTabManipulateBlock(super.id, super.xPosition, super.yPosition, phys_x, phys_y, phys_z,
				super.renderStack.getItem(), gui_prot_id);
	}

	@Override
	public void onTabClicked() {
		System.out.println("GUI:" + gui_prot_id + "X:" + phys_x + "Y:" + phys_y + "Z:" + phys_z);
		NetworkHandler.sendToServer(new PacketPlayGHDynamicOpenGuiWithID(gui_prot_id, phys_x, phys_y, phys_z));
	}

	@Override
	public boolean shouldAddToList() {
		return true;
	}

}
