package net.wfoas.gh.notifysettings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.common.DimensionManager;
import net.wfoas.gh.world.permissions.WorldPlayerPermissions;

public final class NotifyTable {

	static Map<String, Boolean> notifyMap;

	public static void serverStartUp() {
		notifyMap = new HashMap<String, Boolean>();
		try {
			serverStartUp0();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected static void serverStartUp0() throws IOException {
		File f = new File(DimensionManager.getWorld(0).getSaveHandler().getWorldDirectory(), "player_notification.gh");
		if (!f.exists()) {
			f.createNewFile();
			return;
		}
		FileInputStream fis = new FileInputStream(f);
		NBTTagCompound nbttc = CompressedStreamTools.readCompressed(fis);
		NBTTagCompound values = nbttc.getCompoundTag("notification_values");
		NBTTagList list = (NBTTagList) nbttc.getTag("player_list");
		for (int i = 0; i < list.tagCount(); i++) {
			String s = list.getStringTagAt(i);
			if (s == null)
				continue;
			if (s.isEmpty())
				continue;
			Boolean value = Boolean.valueOf(values.getBoolean(s));
			notifyMap.put(s, value);
		}
		fis.close();
	}

	public static void serverStop() {
		try {
			serverStop0();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected static void serverStop0() throws IOException {
		NBTTagCompound data = new NBTTagCompound();
		NBTTagCompound values = new NBTTagCompound();
		NBTTagList list = new NBTTagList();
		for (Map.Entry<String, Boolean> entry : notifyMap.entrySet()) {
			list.appendTag(new NBTTagString(entry.getKey()));
			values.setBoolean(entry.getKey(), entry.getValue());
		}
		data.setTag("player_list", list);
		data.setTag("notification_values", values);
		File f = new File(DimensionManager.getWorld(0).getSaveHandler().getWorldDirectory(), "player_notification.gh");
		FileOutputStream fos = new FileOutputStream(f);
		CompressedStreamTools.writeCompressed(data, fos);
		fos.close();
	}

	public static void toggleNotification(EntityPlayerMP playerMP, boolean flag) {
		if (notifyMap.containsKey(playerMP.getUniqueID().toString()))
			notifyMap.remove(playerMP.getUniqueID().toString());
		notifyMap.put(playerMP.getUniqueID().toString(), Boolean.valueOf(flag));
	}

	public static boolean playerWantsNotification(EntityPlayerMP player) {
		if (!notifyMap.containsKey(player.getUniqueID().toString()))
			notifyMap.put(player.getName(), Boolean.valueOf(true));
		else
			return (notifyMap.get(player.getUniqueID().toString())).booleanValue();
		return true;
	}

	public static void notifyPlayer(EntityPlayerMP playerMP, ChatComponentTranslation chat) {
		if (playerWantsNotification(playerMP)) {
			playerMP.addChatComponentMessage(chat);
		}
	}
}