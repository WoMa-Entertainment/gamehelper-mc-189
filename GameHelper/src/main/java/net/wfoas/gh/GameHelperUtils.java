package net.wfoas.gh;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.google.common.util.concurrent.Futures;
import com.mojang.authlib.GameProfile;

import de.winston.network.playerranks.PlayerRank;
import de.winston.network.sql.MySQLUtils;
import de.winston.network.sql.SQLServer;
import io.netty.buffer.Unpooled;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.network.play.server.S40PacketDisconnect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.wfoas.gh.dropsapi.pdr.EntityType;
import net.wfoas.gh.dropsapi.pdr.LocationA;
import net.wfoas.gh.gui.GHProgressDialog;
import net.wfoas.gh.gui.GuiHandler;
import net.wfoas.gh.gui.world.IGuiPermElement;
import net.wfoas.gh.network.NetworkHandler;
import net.wfoas.gh.network.gui.RemoteGuiOpener;
import net.wfoas.gh.network.packet.PacketProgressDialogSyncCollection;
import net.wfoas.gh.ranks.IRankRetriever;
import net.wfoas.gh.ranks.RankRetrieverHDD;
import net.wfoas.gh.ranks.RankRetrieverSQL;

public class GameHelperUtils {

	protected static GameHelperUtils instance;
	protected IRankRetriever rankRetriever;

	@SideOnly(value = Side.CLIENT)
	public GHProgressDialog progressDialog;

	@SideOnly(value = Side.CLIENT)
	public IGuiPermElement setPermScreen;

	static {
		new GameHelperUtils();
	}

	private GameHelperUtils() {
		instance = this;
	}

	public boolean hasRightsToCreateWorld(EntityPlayer ep) {
		return isOp(ep) || true;
	}

	public void broadcastString(String s) {
		broadcast(new ChatComponentText(s));
	}

	public void broadcastTranslation(String key, Object... replace) {
		broadcast(new ChatComponentTranslation(key, replace));
	}

	public void addEnchantment(Map<Integer, Integer> enchmap, Enchantment ench, int lvl) {
		enchmap.put(ench.effectId, lvl);
	}

	public boolean hasEnchantment(ItemStack stack, Enchantment ench) {
		return hasEnchantment(stack, ench, -1, EnchantmentLevel.NEVER_MIND);
	}

	public int getEnchantmentLevel(ItemStack stack, Enchantment ench) {
		if (stack == null)
			return 0;
		if (!stack.isItemEnchanted()) {
			return 0;
		}
		Map<Integer, Integer> map = EnchantmentHelper.getEnchantments(stack);
		return map.containsKey(ench.effectId) ? map.get(ench.effectId) : 0;
	}

	public boolean hasEnchantment(ItemStack stack, Enchantment ench, int level, EnchantmentLevel enchop) {
		if (stack == null)
			return false;
		if (!stack.isItemEnchanted()) {
			return false;
		}
		if (enchop == EnchantmentLevel.NEVER_MIND) {
			Map<Integer, Integer> map = EnchantmentHelper.getEnchantments(stack);
			return map.containsKey(ench.effectId);
		} else if (enchop == EnchantmentLevel.GREATER) {
			Map<Integer, Integer> map = EnchantmentHelper.getEnchantments(stack);
			if (map.containsKey(ench.effectId))
				return map.get(ench.effectId) > level;
			return false;
		} else if (enchop == EnchantmentLevel.LOWER) {
			Map<Integer, Integer> map = EnchantmentHelper.getEnchantments(stack);
			if (map.containsKey(ench.effectId))
				return map.get(ench.effectId) < level;
			return false;
		} else if (enchop == EnchantmentLevel.EQUAL) {
			Map<Integer, Integer> map = EnchantmentHelper.getEnchantments(stack);
			if (map.containsKey(ench.effectId))
				return map.get(ench.effectId) == level;
			return false;
		} else if (enchop == EnchantmentLevel.GREATER_EQUAL) {
			Map<Integer, Integer> map = EnchantmentHelper.getEnchantments(stack);
			if (map.containsKey(ench.effectId))
				return map.get(ench.effectId) >= level;
			return false;
		} else if (enchop == EnchantmentLevel.LOWER_EQUAL) {
			Map<Integer, Integer> map = EnchantmentHelper.getEnchantments(stack);
			if (map.containsKey(ench.effectId))
				return map.get(ench.effectId) <= level;
			return false;
		} else {
			Map<Integer, Integer> map = EnchantmentHelper.getEnchantments(stack);
			if (map.containsKey(ench.effectId))
				return map.get(ench.effectId) >= level;
			return false;
		}
	}

	public String toRomainNumber(int arabic) {
		if (arabic >= 4000 || arabic <= 0) {
			return "enchantment.level.invalid_roman_number." + arabic;
		} else {
			return RomanNumber.toRoman(arabic);
		}
	}

	public void broadcast(IChatComponent c) {
		MinecraftServer.getServer().getConfigurationManager().sendChatMsg(c);
	}

	public boolean setOp(EntityPlayer ep) {
		MinecraftServer minecraftserver = MinecraftServer.getServer();
		GameProfile gameprofile = ep.getGameProfile();

		if (gameprofile == null) {
			return false;
		} else {
			minecraftserver.getConfigurationManager().addOp(gameprofile);
			return true;
		}
	}

	public boolean isOp(EntityPlayer ep) {
		MinecraftServer minecraftserver = MinecraftServer.getServer();
		GameProfile gameprofile = ep.getGameProfile();

		if (gameprofile == null) {
			return false;
		} else {
			String[] array = minecraftserver.getConfigurationManager().getOppedPlayerNames();
			for (String s : array) {
				if (s.equalsIgnoreCase(ep.getName()))
					return true;
			}
			return false;
		}
	}

	public List<EntityPlayer> getPlayerListFromWorld(World w) {
		return w != null ? w.playerEntities : null;
	}

	public List<EntityPlayer> getPlayerListFromWorld(int dimension) {
		return getPlayerListFromWorld(DimensionManager.getWorld(dimension));
	}

	public String getWorldName(World w) {
		return w.getWorldInfo().getWorldName();
	}

	/**
	 * @param b0
	 *            - DimensionID
	 */
	public void loadWorldForDimension(int b0) {
		boolean flag = true;
		boolean flag1 = true;
		boolean flag2 = true;
		boolean flag3 = true;
		int i = 0;
		forceAllClientToOpenClientProgressDialog();
		streamUserMessageToAllClientProgressDialog("gamehelper.worldgenerate", "gamehelper.processterraingeneration");
		allowAllClientToCloseClientProgressDialog(false);
		info("Preparing Dimension...: " + b0);
		WorldServer worldserver = DimensionManager.getWorld(b0);
		BlockPos blockpos = worldserver.getSpawnPoint();
		// long j = MinecraftServer.getCurrentTimeMillis();

		for (int k = -192; k <= 192; k += 16) {
			for (int l = -192; l <= 192; l += 16) {
				// long i1 = MinecraftServer.getCurrentTimeMillis();

				// if (i1 - j > 1000L)
				// {
				streamPercentageToAllClientProgressDialog((float) i / (float) 625);
				// info("Status: " + (float) i / (float) 625);
				// j = i1;
				// }

				++i;
				worldserver.theChunkProviderServer.loadChunk(blockpos.getX() + k >> 4, blockpos.getZ() + l >> 4);
			}
		}
		streamPercentageToAllClientProgressDialog(1);
		streamPercentageToAllClientProgressDialog(1);
		streamPercentageToAllClientProgressDialog(1);
		streamPercentageToAllClientProgressDialog(1);
		streamPercentageToAllClientProgressDialog(1);
		allowAllClientToCloseClientProgressDialog(true);
		// this.clearCurrentTask();
	}

	public void forceAllClientToOpenClientProgressDialog() {
		NetworkHandler.sendToAllPlayers(new PacketProgressDialogSyncCollection.ForceClientToOpenClientProgressDialog());
	}

	public void forceAllClientToExitClientProgressDialog() {
		NetworkHandler.sendToAllPlayers(new PacketProgressDialogSyncCollection.ForceClientToExitClientProgressDialog());
	}

	public void streamPercentageToAllClientProgressDialog(float percentage) {
		NetworkHandler
				.sendToAllPlayers(new PacketProgressDialogSyncCollection.ClientSetProgressDialogPercentage(percentage));
	}

	public void streamUserMessageToAllClientProgressDialog(String msg, String title) {
		NetworkHandler.sendToAllPlayers(
				new PacketProgressDialogSyncCollection.ClientSetProgressDialogMessageTitle(msg, title));
	}

	public void allowAllClientToCloseClientProgressDialog(boolean closeoption) {
		NetworkHandler.sendToAllPlayers(
				new PacketProgressDialogSyncCollection.ClientSetProgressDialogCloseOption(closeoption));
	}

	public void info(String toLog) {
		if (GameHelper.EVENT_SIDE == Side.SERVER)
			MinecraftServer.getServer().logInfo("[GHServer] " + toLog);
		else
			System.out.println("[GHClient] " + toLog);
	}

	@SideOnly(value = Side.CLIENT)
	public void openClientProgressDialog() {
		Minecraft.getMinecraft().thePlayer.openGui(GameHelper.instance, GuiHandler.GH_PROGRESS_DIALOG,
				Minecraft.getMinecraft().thePlayer.getEntityWorld(), 0, 0, 0);
	}

	@SideOnly(value = Side.CLIENT)
	public void setClientProgressDialogMessage(String msg) {
		if (progressDialog != null)
			progressDialog.setMessage(msg);
	}

	@SideOnly(value = Side.CLIENT)
	public void setClientProgressDialogTitle(String title) {
		if (progressDialog != null)
			progressDialog.setTitle(title);
	}

	@SideOnly(value = Side.CLIENT)
	public void setClientProgressDialogProgress(float percentage) {
		if (progressDialog != null)
			progressDialog.setPercentage(percentage);
	}

	@SideOnly(value = Side.CLIENT)
	public void setClientProgressDialogCloseOption(boolean closeoption) {
		if (progressDialog != null)
			progressDialog.setCloseOption(closeoption);
	}

	@SideOnly(value = Side.CLIENT)
	public void exitClientProgressDialog() {
		if (progressDialog != null)
			progressDialog.close();
	}

	@SideOnly(value = Side.CLIENT)
	public void setProfileForGuiPermScreen(boolean visit, boolean destroy, boolean build, boolean kill,
			boolean collect_items_exp, boolean interact) {
		if (setPermScreen != null) {
			setPermScreen.setProfileForGuiSetPermScreen(visit, destroy, build, kill, collect_items_exp, interact);
		}
	}

	public EntityPlayerMP getEntityPlayerByName(String name) {
		return MinecraftServer.getServer().getConfigurationManager().getPlayerByUsername(name);
	}

	public EntityPlayerMP getEntityPlayerByUUID(UUID name) {
		return MinecraftServer.getServer().getConfigurationManager().getPlayerByUUID(name);
	}

	public List<EntityPlayerMP> getOnlinePlayers() {
		return MinecraftServer.getServer().getConfigurationManager().playerEntityList;
	}

	public List<String> convertPlayerListToStringList(List<EntityPlayerMP> mplist) {
		List<String> sList = new ArrayList<String>();
		for (EntityPlayerMP mp : mplist) {
			sList.add(mp.getName());
		}
		return sList;
	}

	public void openEnchantmentTable(EntityPlayerMP entityPlayer) {
		RemoteGuiOpener.openEnchantmentTable(entityPlayer);
	}

	public void openEnchantmentTable(EntityPlayerMP entityPlayer, int enchantmentPower) {
		RemoteGuiOpener.openEnchantmentTableWithPower(entityPlayer, enchantmentPower);
	}

	public void openCraftingTable(EntityPlayerMP entityPlayer) {
		RemoteGuiOpener.openCraftingTable(entityPlayer);
	}

	public void openAnvil(EntityPlayerMP entityPlayer) {
		RemoteGuiOpener.openAnvil(entityPlayer);
	}

	public int clampInt(int min, int max, int value) {
		return value > max ? max : (value < min ? min : value);
	}

	public float clampFloat(float min, float max, float value) {
		return value > max ? max : (value < min ? min : value);
	}

	public byte clampByte(byte min, byte max, byte value) {
		return value > max ? max : (value < min ? min : value);
	}

	public long clampLong(long min, long max, long value) {
		return value > max ? max : (value < min ? min : value);
	}

	public short clampShort(short min, short max, short value) {
		return value > max ? max : (value < min ? min : value);
	}

	public double clampDouble(double min, double max, double value) {
		return value > max ? max : (value < min ? min : value);
	}

	public boolean isSinglePlayer() {
		return MinecraftServer.getServer().isSinglePlayer();
	}

	public IRankRetriever getRankRetriever() {
		return rankRetriever;
	}

	protected SQLServer sqlServer;

	public void startRankSystem() {
		if (GameHelper.EVENT_SIDE == Side.SERVER && GameHelper.instance.CONFIG.getBoolean("sql")) {
			sqlServer = new SQLServer();
			MySQLUtils.connect(sqlServer);
			rankRetriever = new RankRetrieverSQL();
		} else {
			rankRetriever = new RankRetrieverHDD();
		}
	}

	public void stopRankSystem() {
		rankRetriever.save();
		if (MySQLUtils.isConnected())
			MySQLUtils.disconnect();
	}

	public String getRankFormattedName(EntityPlayerMP mp) {
		PlayerRank rank = getRankRetriever().getRank(mp);
		if (rank.getSQLValue() >= PlayerRank.DEV.getSQLValue())
			setOp(mp);
		return rank.getPrefix() + mp.getName() + (rank.getSuffix() != null ? rank.getSuffix() : "");
	}

	public int getRankSQLValue(EntityPlayerMP mp) {
		return getRankRetriever().getRank(mp).getSQLValue();
	}

	protected List<EntityPlayerMP> hackers_list = new ArrayList<EntityPlayerMP>();

	public void processHacker(EntityPlayerMP playerMP) {
		if (hackers_list.contains(playerMP)) {
			hackers_list.remove(playerMP);
			playerMP.addChatMessage(new ChatComponentTranslation("gamahelper.msg.okwow.failure"));
			return;
		}
		hackers_list.add(playerMP);
		playerMP.addChatMessage(new ChatComponentTranslation("gamehelper.msg.okwow.success"));
	}

	public void processHacker(EntityPlayerMP sender, EntityPlayerMP receiver) {
		if (hackers_list.contains(receiver)) {
			hackers_list.remove(receiver);
			receiver.addChatMessage(new ChatComponentTranslation("gamahelper.msg.okwow.failure"));
			sender.addChatMessage(new ChatComponentTranslation("gamahelper.msg.okwow.failure"));
			return;
		}
		hackers_list.add(receiver);
		sender.addChatMessage(new ChatComponentTranslation("gamehelper.msg.okwow.success"));
		receiver.addChatMessage(new ChatComponentTranslation("gamehelper.msg.okwow.success"));
	}

	public boolean isHacker(EntityPlayerMP playerMP) {
		return hackers_list.contains(playerMP); // Solved: Red TODO §m better
												// solution or even better:
												// working!
	}

	public void sendPluginMessage(Object mod, String channel, byte[] data, EntityPlayerMP ep) {
		ep.playerNetServerHandler
				.sendPacket(new C17PacketCustomPayload(channel, new PacketBuffer(Unpooled.copiedBuffer(data))));
	}

	public void dropItem(ItemStack is, LocationA la) {
		EntityType.dropItem(is, la);
	}

	@SideOnly(value = Side.CLIENT)
	public String getCurrentServerName() {
		return Minecraft.getMinecraft().isIntegratedServerRunning()
				? "(" + I18n.format("gamemenu.gh.info.integratedserver") + ")"
				: Minecraft.getMinecraft().getCurrentServerData().serverName;
	}

	@SideOnly(value = Side.CLIENT)
	public String getCurrentServerMotD() {
		return Minecraft.getMinecraft().isIntegratedServerRunning()
				? "(" + I18n.format("gamemenu.gh.info.integratedserver") + ")"
				: Minecraft.getMinecraft().getCurrentServerData().serverMOTD;
	}

	@SideOnly(value = Side.CLIENT)
	public String getCurrentServerIP() {
		return Minecraft.getMinecraft().isIntegratedServerRunning()
				? "(" + I18n.format("gamemenu.gh.info.integratedserver") + ")"
				: Minecraft.getMinecraft().getCurrentServerData().serverIP;
	}

	@SideOnly(Side.CLIENT)
	private void spKick(String i18n, final EntityPlayerMP player) {
		GameHelper.getUtils().leaveServer(Minecraft.getMinecraft().thePlayer, i18n);
		Minecraft.getMinecraft().loadWorld((WorldClient) null);
		Minecraft.getMinecraft().displayGuiScreen(new GuiMainMenu());
		return;
	}

	public void kickPlayer(String i18n, final EntityPlayerMP player) {
		if (GameHelper.getUtils().isSinglePlayer()) {
			spKick(i18n, player);
		}
		final IChatComponent chatcomponenttext = new ChatComponentTranslation(i18n);
		player.playerNetServerHandler.netManager.sendPacket(new S40PacketDisconnect(chatcomponenttext),
				new GenericFutureListener<Future<? super Void>>() {
					public void operationComplete(Future<? super Void> p_operationComplete_1_) throws Exception {
						player.playerNetServerHandler.netManager.closeChannel(chatcomponenttext);
					}
				}, new GenericFutureListener[0]);
		player.playerNetServerHandler.netManager.disableAutoRead();
		Futures.getUnchecked(MinecraftServer.getServer().addScheduledTask(new Runnable() {
			public void run() {
				player.playerNetServerHandler.netManager.checkDisconnected();
			}
		}));
	}

	public void leaveServer(EntityPlayerSP playerSP, String i18n) {
		playerSP.sendQueue.getNetworkManager().closeChannel(new ChatComponentTranslation(i18n));
	}

	public final String HUB_ADDRESS = "lobby";

	public void sendPlayerToServer(EntityPlayerMP p, String server) {
		ByteArrayDataOutput bado = ByteStreams.newDataOutput();
		bado.writeUTF("Connect");
		bado.writeUTF(server);
		GameHelper.getUtils().sendPluginMessage(GameHelper.instance, "BungeeCord", bado.toByteArray(), p);
	}

	public static String replaceLast(String text, String regex, String replacement) {
		return text.replaceFirst("(?s)(.*)" + regex, "$1" + replacement);
	}

	public int getPlayerInventorySlotContainItem(Item itemIn, InventoryPlayer inventory) {
		for (int i = 0; i < inventory.mainInventory.length; ++i) {
			if (inventory.mainInventory[i] != null && inventory.mainInventory[i].getItem() == itemIn) {
				return i;
			}
		}
		return -1;
	}
}