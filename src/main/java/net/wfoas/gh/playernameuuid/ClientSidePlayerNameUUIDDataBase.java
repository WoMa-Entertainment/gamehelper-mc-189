package net.wfoas.gh.playernameuuid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.wfoas.gh.network.packet.PacketPlaySyncPlayerNameUUID;

public class ClientSidePlayerNameUUIDDataBase {

	public Map<UUID, String> playerNameUUIDMap = new HashMap<UUID, String>();

	public ClientSidePlayerNameUUIDDataBase(PacketPlaySyncPlayerNameUUID packet) {
		NBTTagList nbttl = (NBTTagList) packet.data.getTag("player_name_uuid_list");
		System.out.println("ClientSidePlayerNameUUIDDb: " + nbttl);
		for (int i = 0; i < nbttl.tagCount(); i++) {
			NBTTagCompound pnuuid = nbttl.getCompoundTagAt(i);
			String name = pnuuid.getString("name");
			UUID uuid = new UUID(pnuuid.getLong("uuid_msb"), pnuuid.getLong("uuid_lsb"));
			playerNameUUIDMap.put(uuid, name);
		}
	}

	public UUID getUUID(String playersName) {
		for (UUID uuid : playerNameUUIDMap.keySet()) {
			String name = playerNameUUIDMap.get(uuid);
			if (name.equalsIgnoreCase(playersName))
				return uuid;
		}
		return null;
	}

	public String getName(UUID playersUUID) {
		return playerNameUUIDMap.get(playersUUID);
	}

	public List<String> playerEverOnlineOnServerUsingGH() {
		List<String> s = new ArrayList<String>();
		s.addAll(playerNameUUIDMap.values());
		return s;
	}

	public List<String> playerNameStringList(List<UUID> uidList) {
		List<String> names = new ArrayList<String>();
		names.add("AS");
		if (uidList == null)
			return names;
		for (UUID uid : uidList) {
			String name = getName(uid);
			if (name != null)
				names.add(name);
		}
		return names;
	}

	public List<UUID> playerUUIDList(List<String> sList) {
		List<UUID> names = new ArrayList<UUID>();
		for (String uid : sList) {
			UUID id = getUUID(uid);
			if (id != null)
				names.add(id);
		}
		return names;
	}
}