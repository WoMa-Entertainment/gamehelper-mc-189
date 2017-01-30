package net.wfoas.gh.network.gui;

import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.inventory.IInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.S2DPacketOpenWindow;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.village.MerchantRecipeList;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.gui.GuiHandler;
import net.wfoas.gh.network.NetworkHandler;
import net.wfoas.gh.network.packet.PacketPlayGHDynamicOpenGuiClientSide;
import net.wfoas.gh.network.packet.PacketPlayGHDynamicOpenGuiWithID;
import net.wfoas.gh.network.packet.PacketPlayOpenClientCreateWorldGui;
import net.wfoas.gh.network.packet.PacketPlayOpenListPermDialog;
import net.wfoas.gh.network.packet.PacketPlayOpenMinersInventory;
import net.wfoas.gh.network.packet.PacketPlayOpenOwnWorldDialog;
import net.wfoas.gh.network.packet.PacketPlayOpenSetPermDialog;
import net.wfoas.gh.network.packet.PacketProgressDialogSyncCollection.ClientSetProgressDialogMessageTitle;
import net.wfoas.gh.network.packet.PacketProgressDialogSyncCollection.ForceClientToOpenClientProgressDialog;
import net.wfoas.gh.villager.VillagerRegistrar;
import net.wfoas.gh.villager.entity.GHVillager;
import net.wfoas.gh.world.owner.WorldOwners;

public class RemoteGuiOpener {
	public static void openSetPermDialog(EntityPlayerMP playerMP) {
		List<String> worlds = WorldOwners.getAllForPlayer(playerMP);
		if (worlds == null) {
			worlds = new ArrayList<String>();
			worlds.add("World");
		}
		List<String> players = new ArrayList<String>();
		for (EntityPlayerMP entityPlayer : GameHelper.getUtils().getOnlinePlayers()) {
			players.add(entityPlayer.getName());
		}
		if (worlds == null || players == null || players.isEmpty() || worlds.isEmpty()) {
			playerMP.addChatComponentMessage(new ChatComponentTranslation("gamehelper.error.noworldsornoplayers"));
			return;
		}
		NetworkHandler.sendToSpecificPlayer(new PacketPlayOpenSetPermDialog(playerMP), playerMP);
	}

	public static void openProgressDialog(EntityPlayerMP playerMP, String title, String text) {
		NetworkHandler.sendToSpecificPlayer(new ForceClientToOpenClientProgressDialog(), playerMP);
		NetworkHandler.sendToSpecificPlayer(new ClientSetProgressDialogMessageTitle(text, title), playerMP);
	}

	public static void openCreateWorldGui(EntityPlayerMP playerMP) {
		NetworkHandler.sendToSpecificPlayer(new PacketPlayOpenClientCreateWorldGui(), playerMP);
	}

	public static void openMinersInventory(EntityPlayerMP playerMP) {
		NetworkHandler.sendToSpecificPlayer(new PacketPlayOpenMinersInventory(), playerMP);
	}

	public static void openCraftingTable(EntityPlayerMP playerMP) {
		playerMP.openGui(GameHelper.instance, GuiHandler.VANILLA_GH_CRAFTING_TABLE, playerMP.worldObj, 0, 0, 0);
	}

	public static void openEnchantmentTable(EntityPlayerMP playerMP) {
		playerMP.openGui(GameHelper.instance, GuiHandler.VANILLA_GH_ENCHANTMENT_TABLE, playerMP.worldObj, 0, 0, 0);
	}

	public static void openEnchantmentTableWithPower(EntityPlayerMP playerMP, int enchantmentPower) {
		playerMP.openGui(GameHelper.instance, GuiHandler.VANILLA_GH_ENCHANTMENT_TABLE, playerMP.worldObj, 0, -1,
				enchantmentPower);
	}

	public static void openAnvil(EntityPlayerMP playerMP) {
		playerMP.openGui(GameHelper.instance, GuiHandler.VANILLA_GH_ANVIL, playerMP.worldObj, 0, 0, 0);
	}

	public static void openVillagerTradingInventory(EntityPlayerMP playerMP, GHVillager villager) {
		playerMP.getNextWindowId();
		playerMP.openContainer = new ContainerMerchant(playerMP.inventory, villager, playerMP.worldObj);
		playerMP.openContainer.windowId = playerMP.currentWindowId;
		playerMP.openContainer.onCraftGuiOpened(playerMP);
		IInventory iinventory = ((ContainerMerchant) playerMP.openContainer).getMerchantInventory();
		IChatComponent ichatcomponent = new ChatComponentTranslation(
				VillagerRegistrar.getProfessionById(villager.getProfession()).getUnlocalizedI18nKey());
		playerMP.playerNetServerHandler.sendPacket(new S2DPacketOpenWindow(playerMP.currentWindowId,
				"minecraft:villager", ichatcomponent, iinventory.getSizeInventory()));
		MerchantRecipeList merchantrecipelist = villager.getRecipes(playerMP);

		if (merchantrecipelist != null) {
			PacketBuffer packetbuffer = new PacketBuffer(Unpooled.buffer());
			packetbuffer.writeInt(playerMP.currentWindowId);
			merchantrecipelist.writeToBuf(packetbuffer);
			playerMP.playerNetServerHandler.sendPacket(new S3FPacketCustomPayload("MC|TrList", packetbuffer));
		}
	}

	public static void openOwnWorldDialog(EntityPlayerMP epMP) {
		// epMP.openGui(GameHelper.instance, GuiHandler.OWNWORLD_DIALOG,
		// epMP.worldObj, 0, 0, 0);
		NetworkHandler.sendToSpecificPlayer(new PacketPlayOpenOwnWorldDialog(), epMP);
	}

	public static void openViewPermDialog(EntityPlayerMP epMP) {
		NetworkHandler.sendToSpecificPlayer(new PacketPlayOpenListPermDialog(epMP), epMP);
	}

	public static void openRemoteClientGUI(EntityPlayerMP epMP, int __gui_id, int __gui_x, int __gui_y, int __gui_z) {
		NetworkHandler.sendToSpecificPlayer(
				new PacketPlayGHDynamicOpenGuiClientSide(__gui_id, __gui_x, __gui_y, __gui_z), epMP);
	}
}
