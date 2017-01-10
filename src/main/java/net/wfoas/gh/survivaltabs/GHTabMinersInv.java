package net.wfoas.gh.survivaltabs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.gui.GuiHandler;
import net.wfoas.gh.network.NetworkHandler;
import net.wfoas.gh.network.packet.PacketPlayOpenMinersInventory;

public class GHTabMinersInv extends AbstractHintedSurvivalTab {

	public GHTabMinersInv() {
		super(1, 20, 0, new ItemStack(Items.iron_pickaxe), I18n.format("gamehelper.container.minersinv.tab"));
	}

	@Override
	public void onTabClicked() {
		Minecraft.getMinecraft().thePlayer.openGui(GameHelper.instance, GuiHandler.MINERS_INVENTORY,
				Minecraft.getMinecraft().thePlayer.worldObj, 0, 0, 0);
		NetworkHandler.sendToServer(new PacketPlayOpenMinersInventory());// FMLCommonHandler
	}

	@Override
	public boolean shouldAddToList() {
		return true;
	}

}