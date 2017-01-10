package net.wfoas.gh.playernameuuid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.DimensionManager;

public class PlayerNameUUID {

	public static Map<UUID, String> playerNameUUIDMap = new HashMap<UUID, String>();

	public static void load() {
		try {
			load0();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void load0() throws IOException {
		File f = new File(DimensionManager.getWorld(0).getSaveHandler().getWorldDirectory(),
				"player_name_uuid_list.gh");
		if (!f.exists()) {
			f.createNewFile();
			return;
		}
		FileInputStream fis = new FileInputStream(f);
		NBTTagCompound nbttc = CompressedStreamTools.readCompressed(fis);
		NBTTagList nbttl = (NBTTagList) nbttc.getTag("player_name_uuid_list");
		for (int i = 0; i < nbttl.tagCount(); i++) {
			NBTTagCompound pnuuid = nbttl.getCompoundTagAt(i);
			String name = pnuuid.getString("name");
			UUID uuid = new UUID(pnuuid.getLong("uuid_msb"), pnuuid.getLong("uuid_lsb"));
			playerNameUUIDMap.put(uuid, name);
		}
	}

	public static void save() {
		try {
			save0();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void save0() throws IOException {
		NBTTagCompound data = new NBTTagCompound();
		NBTTagList nbttl = new NBTTagList();
		for (Entry<UUID, String> entries : playerNameUUIDMap.entrySet()) {
			NBTTagCompound pnuuid = new NBTTagCompound();
			pnuuid.setString("name", entries.getValue());
			pnuuid.setLong("uuid_msb", entries.getKey().getMostSignificantBits());
			pnuuid.setLong("uuid_lsb", entries.getKey().getLeastSignificantBits());
			nbttl.appendTag(pnuuid);
		}
		File f = new File(DimensionManager.getWorld(0).getSaveHandler().getWorldDirectory(),
				"player_name_uuid_list.gh");
		data.setTag("player_name_uuid_list", nbttl);
		FileOutputStream fos = new FileOutputStream(f);
		CompressedStreamTools.writeCompressed(data, fos);
		fos.close();
	}

	public static void addPlayer(EntityPlayer player) {
		playerNameUUIDMap.put(player.getUniqueID(), player.getName());
	}

	public static void addPlayer(UUID uuid, String name) {
		playerNameUUIDMap.put(uuid, name);
	}

	public static UUID getUUID(String playersName) {
		for (UUID uuid : playerNameUUIDMap.keySet()) {
			String name = playerNameUUIDMap.get(uuid);
			if (name.equalsIgnoreCase(playersName))
				return uuid;
		}
		return null;
	}

	public static String getName(UUID playersUUID) {
		return playerNameUUIDMap.get(playersUUID);
	}

}
