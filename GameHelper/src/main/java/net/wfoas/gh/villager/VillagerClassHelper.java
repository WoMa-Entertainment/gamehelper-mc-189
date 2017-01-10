package net.wfoas.gh.villager;

import java.lang.reflect.Field;

import net.minecraft.world.gen.structure.StructureVillagePieces;

public class VillagerClassHelper {
	public static Field villagerSpawned;

	public static void initVillagerReflection() {
		boolean counter = false;
		for (Field f : StructureVillagePieces.Village.class.getDeclaredFields()) {
			if (f.getType().equals(Integer.class)) {
				if (!counter) {
					counter = true;
					continue;
				} else {
					counter = false;
					villagerSpawned = f;
					break;
				}
			}
		}
		villagerSpawned.setAccessible(true);
	}

	public static void setVillagerSpawned(StructureVillagePieces.Village part, int value) {
		try {
			villagerSpawned.setInt(part, value);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public static int getVillagerSpawned(StructureVillagePieces.Village part) {
		try {
			return villagerSpawned.getInt(part);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
