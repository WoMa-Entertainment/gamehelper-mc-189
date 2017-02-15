package net.wfoas.gh.world.owner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.wfoas.gh.multipleworlds.GHWorld;

public class WorldOwners {
	private static volatile Map<String, List<UUID>> worldOwnerMap = new HashMap<String, List<UUID>>();
	private static volatile Map<UUID, List<String>> playerOwnerMap = new HashMap<UUID, List<String>>();

	public static void load() {
		try {
			System.out.println("--> Worlds");
			load0Worlds();
			System.out.println("--> Players");
			load0Players();
			System.out.println("<-- End");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void load0Worlds() throws IOException {
		File f = new File(DimensionManager.getWorld(0).getSaveHandler().getWorldDirectory(), "world_owner.gh");
		if (!f.exists()) {
			f.createNewFile();
			return;
		}
		FileInputStream fis = new FileInputStream(f);
		NBTTagCompound nbttc = CompressedStreamTools.readCompressed(fis);
		NBTTagList nbttl = (NBTTagList) nbttc.getTag("world_owner_list");
		System.out.println("fetched the world_owner_list");
		for (int i = 0; i < nbttl.tagCount(); i++) {
			NBTTagCompound wnbt = nbttl.getCompoundTagAt(i);
			String worldname = wnbt.getString("world_name");
			System.out.println("fetched the world_name: " + worldname);
			NBTTagList nbt_owtl = (NBTTagList) wnbt.getTag("owner_list");
			System.out.println("fetched the world_owner_list");
			List<UUID> ownerlist = new ArrayList<UUID>();
			for (int tc = 0; tc < nbt_owtl.tagCount(); tc++) {
				String uuid = nbt_owtl.getStringTagAt(tc);
				System.out.println("found uuid: " + uuid + " for world: " + worldname);
				ownerlist.add(UUID.fromString(uuid));
			}
			worldOwnerMap.put(worldname, ownerlist);
		}
	}

	private static void load0Players() throws IOException {
		File f = new File(DimensionManager.getWorld(0).getSaveHandler().getWorldDirectory(), "player_world_owner.gh");
		if (!f.exists()) {
			f.createNewFile();
			return;
		}
		FileInputStream fis = new FileInputStream(f);
		NBTTagCompound nbttc = CompressedStreamTools.readCompressed(fis);
		NBTTagList nbttl = (NBTTagList) nbttc.getTag("player_owner_list");
		for (int i = 0; i < nbttl.tagCount(); i++) {
			NBTTagCompound wnbt = nbttl.getCompoundTagAt(i);
			UUID worldname = UUID.fromString(wnbt.getString("player_uuid"));
			NBTTagList nbt_owtl = (NBTTagList) wnbt.getTag("world_list");
			List<String> ownerlist = new ArrayList<String>();
			for (int tc = 0; tc < nbt_owtl.tagCount(); tc++) {
				String uuid = nbt_owtl.getStringTagAt(tc);
				ownerlist.add(uuid);
			}
			playerOwnerMap.put(worldname, ownerlist);
		}
	}

	public static void save() {
		try {
			save0Worlds();
			save0Players();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void save0Worlds() throws IOException {
		NBTTagCompound data = new NBTTagCompound();
		NBTTagList nbttl = new NBTTagList();
		for (Entry<String, List<UUID>> entries : worldOwnerMap.entrySet()) {
			NBTTagCompound worldInformation = new NBTTagCompound();
			worldInformation.setString("world_name", entries.getKey());
			NBTTagList uuids = new NBTTagList();
			for (UUID uuid : entries.getValue()) {
				uuids.appendTag(new NBTTagString(uuid.toString()));
			}
			worldInformation.setTag("owner_list", uuids);
			nbttl.appendTag(worldInformation);
		}
		File f = new File(DimensionManager.getWorld(0).getSaveHandler().getWorldDirectory(), "world_owner.gh");
		data.setTag("world_owner_list", nbttl);
		FileOutputStream fos = new FileOutputStream(f);
		CompressedStreamTools.writeCompressed(data, fos);
		// fos.close();
	}

	private static void save0Players() throws IOException {
		NBTTagCompound data = new NBTTagCompound();
		NBTTagList nbttl = new NBTTagList();
		for (Entry<UUID, List<String>> entries : playerOwnerMap.entrySet()) {
			NBTTagCompound worldInformation = new NBTTagCompound();
			worldInformation.setString("player_uuid", entries.getKey().toString());
			NBTTagList uuids = new NBTTagList();
			for (String uuid : entries.getValue()) {
				uuids.appendTag(new NBTTagString(uuid));
			}
			worldInformation.setTag("world_list", uuids);
			nbttl.appendTag(worldInformation);
		}
		File f = new File(DimensionManager.getWorld(0).getSaveHandler().getWorldDirectory(), "player_world_owner.gh");
		data.setTag("player_owner_list", nbttl);
		FileOutputStream fos = new FileOutputStream(f);
		CompressedStreamTools.writeCompressed(data, fos);
		fos.close();
	}

	public static List<UUID> getAllForWorld(World w) {
		return worldOwnerMap.get(w.getWorldInfo().getWorldName());
	}

	public static List<UUID> getAllForWorld(GHWorld w) {
		return worldOwnerMap.get(w.getName());
	}

	public static List<UUID> getAllForWorldOrCreate(World w) {
		if (worldOwnerMap.get(w.getWorldInfo().getWorldName()) == null) {
			worldOwnerMap.put(w.getWorldInfo().getWorldName(), new ArrayList<UUID>());
		}
		return worldOwnerMap.get(w.getWorldInfo().getWorldName());
	}

	public static List<UUID> getAllForWorldOrCreate(GHWorld w) {
		if (worldOwnerMap.get(w.getName()) == null) {
			worldOwnerMap.put(w.getName(), new ArrayList<UUID>());
		}
		return worldOwnerMap.get(w.getName());
	}

	public static List<String> getAllForPlayer(EntityPlayer ep) {
		return playerOwnerMap.get(ep.getUniqueID());
	}

	public static List<String> getAllForPlayerOrCreate(EntityPlayer ep) {
		if (playerOwnerMap.get(ep.getUniqueID()) == null) {
			playerOwnerMap.put(ep.getUniqueID(), new ArrayList<String>());
		}
		return playerOwnerMap.get(ep.getUniqueID());
	}

	public static boolean isOwner(World w, EntityPlayer player) {
		return getAllForWorldOrCreate(w).contains(player.getUniqueID());
	}

	public static boolean isOwner(GHWorld w, EntityPlayer player) {
		return getAllForWorldOrCreate(w).contains(player.getUniqueID());
	}

	public static void setOwner(World w, EntityPlayer player, boolean flag) {
		if (flag) {
			if (!getAllForWorldOrCreate(w).contains(player.getUniqueID()))
				getAllForWorldOrCreate(w).add(player.getUniqueID());
			if (!getAllForPlayerOrCreate(player).contains(w.getWorldInfo().getWorldName()))
				getAllForPlayerOrCreate(player).add(w.getWorldInfo().getWorldName());
		} else {
			if (getAllForWorldOrCreate(w).contains(player.getUniqueID()))
				getAllForWorldOrCreate(w).remove(player.getUniqueID());
			if (getAllForPlayerOrCreate(player).contains(w.getWorldInfo().getWorldName()))
				getAllForPlayerOrCreate(player).remove(w.getWorldInfo().getWorldName());
		}
	}

	public static void setOwner(GHWorld w, EntityPlayer player, boolean flag) {
		if (flag) {
			if (!getAllForWorldOrCreate(w).contains(player.getUniqueID()))
				getAllForWorldOrCreate(w).add(player.getUniqueID());
			if (!getAllForPlayerOrCreate(player).contains(w.getName()))
				getAllForPlayerOrCreate(player).add(w.getName());
		} else {
			if (getAllForWorldOrCreate(w).contains(player.getUniqueID()))
				getAllForWorldOrCreate(w).remove(player.getUniqueID());
			if (getAllForPlayerOrCreate(player).contains(w.getName()))
				getAllForPlayerOrCreate(player).remove(w.getName());
		}
	}
}
