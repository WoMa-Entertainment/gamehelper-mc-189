package net.wfoas.gh.protected_blocks.tabs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.gui.GuiHandler;
import net.wfoas.gh.network.NetworkHandler;
import net.wfoas.gh.network.packet.PacketPlayGHDynamicOpenGuiWithID;
import net.wfoas.gh.survivaltabs.AbstractHintedSurvivalTab;

public class ProtectedBlockTabChangePermission extends AbstractHintedSurvivalTab {

	int phys_x, phys_y, phys_z;

	public ProtectedBlockTabChangePermission(int id, int posX, int posY, int phys_x, int phys_y, int phys_z) {
		super(id, posX, posY, new ItemStack(Items.paper), I18n.format("gamehelper.protected_block.changepermissions")); // 
		this.phys_x = phys_x;
		this.phys_y = phys_y;
		this.phys_z = phys_z;
	}

	public ProtectedBlockTabChangePermission main() {
		this.enabled = false;
		return this;
	}

	@Override
	public AbstractHintedSurvivalTab clone() {
		return new ProtectedBlockTabChangePermission(super.id, super.xPosition, super.yPosition, phys_x, phys_y,
				phys_z);
	}

	@Override
	public void onTabClicked() {
		NetworkHandler.sendToServer(new PacketPlayGHDynamicOpenGuiWithID(GuiHandler.PROTECTED_GUI_CHANGE_PERMISSIONS,
				phys_x, phys_y, phys_z));
		Minecraft.getMinecraft().thePlayer.openGui(GameHelper.instance, GuiHandler.PROTECTED_GUI_CHANGE_PERMISSIONS,
				Minecraft.getMinecraft().thePlayer.worldObj, phys_x, phys_y, phys_z);
	}

	@Override
	public boolean shouldAddToList() {
		return true;
	}
}
