package net.wfoas.gh.network;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.handshake.NetworkDispatcher;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.wfoas.gh.network.packet.PacketPlayBackpackInventoryPrivacy;
import net.wfoas.gh.network.packet.PacketPlayChangeWorldPermissionsRequest;
import net.wfoas.gh.network.packet.PacketPlayCreateNewWorld;
import net.wfoas.gh.network.packet.PacketPlayEnchantmentAltarApplyEnchantment;
import net.wfoas.gh.network.packet.PacketPlayGHDynamicOpenGuiClientSide;
import net.wfoas.gh.network.packet.PacketPlayGHDynamicOpenGuiWithID;
import net.wfoas.gh.network.packet.PacketPlayListOnlinePlayers;
import net.wfoas.gh.network.packet.PacketPlayListOwnedWorlds;
import net.wfoas.gh.network.packet.PacketPlayOpenClientCreateWorldGui;
import net.wfoas.gh.network.packet.PacketPlayOpenListPermDialog;
import net.wfoas.gh.network.packet.PacketPlayOpenMinersInventory;
import net.wfoas.gh.network.packet.PacketPlayOpenOwnWorldDialog;
import net.wfoas.gh.network.packet.PacketPlayOpenSetPermDialog;
import net.wfoas.gh.network.packet.PacketPlayOpenThermalInventory;
import net.wfoas.gh.network.packet.PacketPlayOwnWorldCollection;
import net.wfoas.gh.network.packet.PacketPlayOwnWorldCollection.PacketPlayListWorldOwnersForAllWorlds;
import net.wfoas.gh.network.packet.PacketPlayOwnWorldCollection.PacketPlayOwnWorldChangeWorldOwner;
import net.wfoas.gh.network.packet.PacketPlayPlayerProfileForWorld;
import net.wfoas.gh.network.packet.PacketPlayRequestProfileForPlayerInWorld;
import net.wfoas.gh.network.packet.PacketPlaySendNewProfileToServer;
import net.wfoas.gh.network.packet.PacketPlaySyncDaggerRotationToClients;
import net.wfoas.gh.network.packet.PacketPlaySyncDaggerRotationToServer;
import net.wfoas.gh.network.packet.PacketPlaySyncVillagerProfessionIds;
import net.wfoas.gh.network.packet.PacketProgressDialogSyncCollection;
import net.wfoas.gh.network.securedlogin.PacketPlayPasswordAuthRequest;
import net.wfoas.gh.network.securedlogin.PacketPlayPasswordCloseDialog;
import net.wfoas.gh.network.securedlogin.PacketPlayPasswordFromPlayer;

public class NetworkHandler {
	public static SimpleNetworkWrapper SNW;
	private static byte netID = 0;

	public static void preInit() {
		/* Force-NotRegistered: PacketPlayOpenPlayersInventory */
		// NetworkDispatcher.FML_DISPATCHER. ForgeMessage
		SNW = NetworkRegistry.INSTANCE.newSimpleChannel("gamehelper");
		SNW.registerMessage(PacketPlayBackpackInventoryPrivacy.PacketBackpackHandler.class,
				PacketPlayBackpackInventoryPrivacy.class, nextPacketId(), Side.SERVER);
		SNW.registerMessage(PacketPlayEnchantmentAltarApplyEnchantment.PacketEnchAltarHandler.class,
				PacketPlayEnchantmentAltarApplyEnchantment.class, nextPacketId(), Side.SERVER);
		SNW.registerMessage(PacketPlayOpenClientCreateWorldGui.PacketPlayOpenClientCreateWorldGuiHandler.class,
				PacketPlayOpenClientCreateWorldGui.class, nextPacketId(), Side.CLIENT);
		SNW.registerMessage(PacketPlayOpenMinersInventory.PacketPlayOpenMinersInventoryHandler.class,
				PacketPlayOpenMinersInventory.class, nextPacketId(), Side.SERVER);
		SNW.registerMessage(PacketPlaySyncDaggerRotationToClients.PacketPlaySyncDaggerRotationHandler.class,
				PacketPlaySyncDaggerRotationToClients.class, nextPacketId(), Side.CLIENT);
		SNW.registerMessage(PacketPlaySyncDaggerRotationToServer.PacketPlaySyncDaggerRotationHandler.class,
				PacketPlaySyncDaggerRotationToServer.class, nextPacketId(), Side.SERVER);
		SNW.registerMessage(PacketPlayCreateNewWorld.PacketPlayCreateNewWorldHandler.class,
				PacketPlayCreateNewWorld.class, nextPacketId(), Side.SERVER);
		SNW.registerMessage(
				PacketProgressDialogSyncCollection.ForceClientToOpenClientProgressDialog.ForceClientToOpenClientProgressDialogHandler.class,
				PacketProgressDialogSyncCollection.ForceClientToOpenClientProgressDialog.class, nextPacketId(),
				Side.CLIENT);
		SNW.registerMessage(
				PacketProgressDialogSyncCollection.ClientSetProgressDialogCloseOption.ClientSetProgressDialogCloseOptionHandler.class,
				PacketProgressDialogSyncCollection.ClientSetProgressDialogCloseOption.class, nextPacketId(),
				Side.CLIENT);
		SNW.registerMessage(
				PacketProgressDialogSyncCollection.ClientSetProgressDialogMessageTitle.ClientSetProgressDialogMessageTitleHandler.class,
				PacketProgressDialogSyncCollection.ClientSetProgressDialogMessageTitle.class, nextPacketId(),
				Side.CLIENT);
		SNW.registerMessage(
				PacketProgressDialogSyncCollection.ClientSetProgressDialogPercentage.ClientSetProgressDialogPercentageHandler.class,
				PacketProgressDialogSyncCollection.ClientSetProgressDialogPercentage.class, nextPacketId(),
				Side.CLIENT);
		SNW.registerMessage(
				PacketProgressDialogSyncCollection.ForceClientToExitClientProgressDialog.ForceClientToExitClientProgressDialogHandler.class,
				PacketProgressDialogSyncCollection.ForceClientToExitClientProgressDialog.class, nextPacketId(),
				Side.CLIENT);
		SNW.registerMessage(PacketPlayOpenSetPermDialog.PacketPlayOpenSetPermDialogHandler.class,
				PacketPlayOpenSetPermDialog.class, nextPacketId(), Side.CLIENT);
		SNW.registerMessage(
				PacketPlayChangeWorldPermissionsRequest.PacketPlayChangeWorldPermissionsRequestHandler.class,
				PacketPlayChangeWorldPermissionsRequest.class, nextPacketId(), Side.SERVER);
		SNW.registerMessage(PacketPlayRequestProfileForPlayerInWorld.PacketPlayRequestOwnedWorldsHandler.class,
				PacketPlayRequestProfileForPlayerInWorld.class, nextPacketId(), Side.SERVER);
		SNW.registerMessage(PacketPlaySendNewProfileToServer.PacketPlaySendNewProfileToServerHandler.class,
				PacketPlaySendNewProfileToServer.class, nextPacketId(), Side.SERVER);
		SNW.registerMessage(PacketPlayPlayerProfileForWorld.PacketPlayPlayerProfileForWorldHandler.class,
				PacketPlayPlayerProfileForWorld.class, nextPacketId(), Side.CLIENT);
		SNW.registerMessage(PacketPlayListOwnedWorlds.PacketPlayListOwnedWorldsHandler.class,
				PacketPlayListOwnedWorlds.class, nextPacketId(), Side.CLIENT);
		SNW.registerMessage(PacketPlayListOnlinePlayers.PacketPlayListOnlinePlayersHandler.class,
				PacketPlayListOnlinePlayers.class, nextPacketId(), Side.CLIENT);
		SNW.registerMessage(PacketPlaySyncVillagerProfessionIds.PacketPlaySyncVillagerProfessionIdsClientHandler.class,
				PacketPlaySyncVillagerProfessionIds.class, nextPacketId(), Side.CLIENT);
		SNW.registerMessage(PacketPlayPasswordAuthRequest.PacketPlayPasswordAuthRequestHandler.class,
				PacketPlayPasswordAuthRequest.class, nextPacketId(), Side.CLIENT);
		SNW.registerMessage(PacketPlayPasswordCloseDialog.PacketPlayPasswordCloseDialogHandler.class,
				PacketPlayPasswordCloseDialog.class, nextPacketId(), Side.CLIENT);
		SNW.registerMessage(PacketPlayPasswordFromPlayer.PacketPlayPasswordFromPlayerHandler.class,
				PacketPlayPasswordFromPlayer.class, nextPacketId(), Side.SERVER);
		SNW.registerMessage(PacketPlayOpenListPermDialog.PacketPlayOpenSetPermDialogHandler.class,
				PacketPlayOpenListPermDialog.class, nextPacketId(), Side.CLIENT);
		SNW.registerMessage(PacketPlayOpenOwnWorldDialog.PacketPlayOpenOwnWorldDialogHandler.class,
				PacketPlayOpenOwnWorldDialog.class, nextPacketId(), Side.CLIENT);
		SNW.registerMessage(PacketPlayOpenThermalInventory.PacketPlayOpenThermalInventoryHandler.class,
				PacketPlayOpenThermalInventory.class, nextPacketId(), Side.SERVER);
		SNW.registerMessage(PacketPlayOwnWorldChangeWorldOwner.PacketPlayOwnWorldChangeWorldOwnerHandler.class,
				PacketPlayOwnWorldChangeWorldOwner.class, nextPacketId(), Side.SERVER);
		SNW.registerMessage(
				PacketPlayOwnWorldCollection.PacketPlayListWorldOwnersForAllWorlds.PacketPlayListWorldOwnersForAllWorldsHandler.class,
				PacketPlayOwnWorldCollection.PacketPlayListWorldOwnersForAllWorlds.class, nextPacketId(), Side.CLIENT);
		SNW.registerMessage(PacketPlayGHDynamicOpenGuiWithID.PacketPlayGHDynamicOpenGuiHandler.class,
				PacketPlayGHDynamicOpenGuiWithID.class, nextPacketId(), Side.SERVER);
		SNW.registerMessage(PacketPlayGHDynamicOpenGuiClientSide.PacketPlayGHDynamicOpenGuiClientSideHandler.class,
				PacketPlayGHDynamicOpenGuiClientSide.class, nextPacketId(), Side.CLIENT);
	}

	public static byte nextPacketId() {
		byte pID = netID;
		netID++;
		return pID;
	}

	public static void sendToServer(IMessage im) {
		SNW.sendToServer(im);
	}

	public static void sendToAllInDimension(IMessage im, int dimID) {
		SNW.sendToDimension(im, dimID);
	}

	public static void sendToSpecificPlayer(IMessage im, EntityPlayerMP ep) {
		SNW.sendTo(im, ep);
	}

	public static void sendToAllPlayers(IMessage im) {
		SNW.sendToAll(im);
	}
}