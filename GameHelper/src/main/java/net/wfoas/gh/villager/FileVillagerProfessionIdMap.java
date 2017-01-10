package net.wfoas.gh.villager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.logging.log4j.Level;

import java.util.Map.Entry;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.common.DimensionManager;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.villager.entity.GHVillagerProfession;

public class FileVillagerProfessionIdMap {
	private static Map<Integer, String> profIdMap = new HashMap<Integer, String>();
	private static int id = 0;

	protected static void sortListAndAssignIds() {
		if (load()) {
			// copy from list
			// copyFromList();
			injectNewEntries();
			copyFromList(); // TODO inject sth if new | check if new then inject
			save();
		} else {
			// sort by hand
			injectNewEntries();
			copyFromList();
			save();
		}
	}

	protected static void copyFromList() {
		VillagerRegistrar.sortedprofessionList.clear();
		for (Map.Entry<Integer, String> ntry : profIdMap.entrySet()) {
			VillagerRegistrar.sortedprofessionList.put(ntry.getKey(),
					VillagerRegistrar.getProfessionByAbstractName(ntry.getValue()));
		}
	}

	private static int nextVillagerProfessionId() {
		int id2 = id;
		id++;
		return id2;
	}

	protected static void injectNewEntries() {
		for (GHVillagerProfession profes : VillagerRegistrar.unsortedProfessionList) {
			if (!profIdMap.containsValue(profes.getName())) {
				int vilProffId = nextVillagerProfessionId();
				if (!profIdMap.containsKey(Integer.valueOf(vilProffId)))
					GameHelper.getLogger().log(Level.INFO,
							"Injected new VillagerProfession: " + profes.getName() + " As ID: " + vilProffId);
				profIdMap.put(vilProffId, profes.getName());
			}
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
		File f = new File(DimensionManager.getWorld(0).getSaveHandler().getWorldDirectory(), "villager_professions.gh");
		NBTTagCompound nbttc = new NBTTagCompound();
		for (Map.Entry<Integer, String> entry : profIdMap.entrySet()) {
			nbttc.setString(entry.getKey().toString(), entry.getValue());
		}
		data.setTag("villager_professions", nbttc);
		data.setInteger("last_villager_id", id);
		FileOutputStream fos = new FileOutputStream(f);
		CompressedStreamTools.writeCompressed(data, fos);
		fos.close();
	}

	private static boolean load() {
		try {
			return loadCreate0();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	private static boolean loadCreate0() throws IOException {
		File f = new File(DimensionManager.getWorld(0).getSaveHandler().getWorldDirectory(), "villager_professions.gh");
		if (!f.exists()) {
			f.createNewFile();
			id = 0;
			profIdMap.clear();
			return false;
		}
		FileInputStream fis = new FileInputStream(f);
		System.out.println(f.getAbsolutePath());
		NBTTagCompound nbttc = CompressedStreamTools.readCompressed(fis);
		fis.close();
		NBTTagCompound nbtvp = nbttc.getCompoundTag("villager_professions");
		Map<Integer, String> map = new HashMap<Integer, String>();
		for (String key : nbtvp.getKeySet()) {
			map.put(Integer.parseInt(key), nbtvp.getString(key));
			System.out.println("Loaded: ID: " + key + " VALUE: " + nbtvp.getString(key));
		}
		id = nbttc.getInteger("last_villager_id");
		profIdMap = map;
		return true;
	}
}
