package net.wfoas.gh.survivaltabs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.gui.GuiHandler;
import net.wfoas.gh.network.NetworkHandler;
import net.wfoas.gh.network.packet.PacketPlayOpenMinersInventory;
import net.wfoas.gh.network.packet.PacketPlayOpenThermalInventory;

public class GHThermalTab extends AbstractHintedSurvivalTab {

	public GHThermalTab() {
		super(2, 40, 0, new ItemStack(Blocks.ice), I18n.format("gamehelper.container.thermalinv"));
	}

	@Override
	public void onTabClicked() {
		Minecraft.getMinecraft().thePlayer.openGui(GameHelper.instance, GuiHandler.THERMAL_INVENTORY,
				Minecraft.getMinecraft().thePlayer.worldObj, 0, 0, 0);
		NetworkHandler.sendToServer(new PacketPlayOpenThermalInventory());// FMLCommonHandler
	}

	@Override
	public boolean shouldAddToList() {
		return true;
	}

}
