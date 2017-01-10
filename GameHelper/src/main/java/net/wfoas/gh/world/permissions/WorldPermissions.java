package net.wfoas.gh.world.permissions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.multipleworlds.GHWorld;

public class WorldPermissions {

	public static List<WorldPlayerPermissions> worldPlayerPermissions = new ArrayList<WorldPlayerPermissions>();

	public static void load() {
		try {
			load0();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void load0() throws IOException {
		File f = new File(DimensionManager.getWorld(0).getSaveHandler().getWorldDirectory(), "world_permissions.gh");
		if (!f.exists()) {
			f.createNewFile();
			return;
		}
		FileInputStream fis = new FileInputStream(f);
		NBTTagCompound nbttc = CompressedStreamTools.readCompressed(fis);
		NBTTagList nbttl = (NBTTagList) nbttc.getTag("permissions");
		System.out.println("WorldPerms:");
		for (int i = 0; i < nbttl.tagCount(); i++) {
			NBTTagCompound wnbt = nbttl.getCompoundTagAt(i);
			WorldPlayerPermissions wpp = WorldPlayerPermissions.readFromNBTTagCompound(wnbt);
			worldPlayerPermissions.add(wpp);
		}
		System.out.println("finished with WorldPerms");
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
		for (WorldPlayerPermissions wpp : worldPlayerPermissions) {
			nbttl.appendTag(wpp.writeToNBTTagCompound(new NBTTagCompound()));
			// System.out.println("Saving data for multidimensions-worlds: " +
			// entry.getKey());
		}
		File f = new File(DimensionManager.getWorld(0).getSaveHandler().getWorldDirectory(), "world_permissions.gh");
		// System.out.println(f.getAbsolutePath());
		data.setTag("permissions", nbttl);
		FileOutputStream fos = new FileOutputStream(f);
		CompressedStreamTools.writeCompressed(data, fos);
		fos.close();
	}

	public static List<WorldPlayerPermissions> getAllPermissionsForWorld(World w) {
		List<WorldPlayerPermissions> permList = new ArrayList<WorldPlayerPermissions>();
		String worldName = GameHelper.getUtils().getWorldName(w);
		for (WorldPlayerPermissions wpp : worldPlayerPermissions) {
			if (wpp.getWorld().equalsIgnoreCase(worldName)) {
				permList.add(wpp);
			}
		}
		return permList;
	}

	public static List<WorldPlayerPermissions> getAllPermissionsForWorld(GHWorld w) {
		List<WorldPlayerPermissions> permList = new ArrayList<WorldPlayerPermissions>();
		String worldName = w.getName();
		for (WorldPlayerPermissions wpp : worldPlayerPermissions) {
			if (wpp.getWorld().equalsIgnoreCase(worldName)) {
				permList.add(wpp);
			}
		}
		return permList;
	}

	public static List<WorldPlayerPermissions> getAllPermissionsForPlayer(EntityPlayer p) {
		List<WorldPlayerPermissions> permList = new ArrayList<WorldPlayerPermissions>();
		String plName = p.getUniqueID().toString();
		for (WorldPlayerPermissions wpp : worldPlayerPermissions) {
			if (wpp.getPlayer().equalsIgnoreCase(plName)) {
				permList.add(wpp);
			}
		}
		return permList;
	}

	public static WorldPlayerPermissions getForWorldAndPlayer(World w, EntityPlayer p) {
		List<WorldPlayerPermissions> permList = getAllPermissionsForWorld(w);
		String plName = p.getUniqueID().toString();
		for (WorldPlayerPermissions wpp : worldPlayerPermissions) {
			if (wpp.getPlayer().equalsIgnoreCase(plName)) {
				return wpp;
			}
		}
		return null;
	}

	public static WorldPlayerPermissions getForWorldAndPlayer(GHWorld w, EntityPlayer p) {
		List<WorldPlayerPermissions> permList = getAllPermissionsForWorld(w);
		System.out.println("pL " + permList);
		String plName = p.getUniqueID().toString();
		for (WorldPlayerPermissions wpp : worldPlayerPermissions) {
			if (wpp.getPlayer().equalsIgnoreCase(plName)) {
				return wpp;
			}
		}
		WorldPlayerPermissions wp2 = new WorldPlayerPermissions(w.getName(), p.getUniqueID());
		if(!worldPlayerPermissions.contains(wp2))
			worldPlayerPermissions.add(wp2);
		else
			wp2 = worldPlayerPermissions.get(worldPlayerPermissions.indexOf(wp2));
		return wp2;
	}

	public static WorldPlayerPermissions getForWorldAndPlayerOrCreate(World w, EntityPlayer p) {
		List<WorldPlayerPermissions> permList = getAllPermissionsForWorld(w);
		String plName = p.getUniqueID().toString();
		for (WorldPlayerPermissions wpp : worldPlayerPermissions) {
			if (wpp.getPlayer().equalsIgnoreCase(plName)) {
				return wpp;
			}
		}
		WorldPlayerPermissions wpp = new WorldPlayerPermissions(w.getWorldInfo().getWorldName(), p.getUniqueID());
		wpp.setAll(false);
		worldPlayerPermissions.add(wpp);
		return wpp;
	}

	public static WorldPlayerPermissions getForWorldAndPlayerOrCreate(GHWorld w, EntityPlayer p) {
		List<WorldPlayerPermissions> permList = getAllPermissionsForWorld(w);
		String plName = p.getUniqueID().toString();
		for (WorldPlayerPermissions wpp : worldPlayerPermissions) {
			if (wpp.getPlayer().equalsIgnoreCase(plName)) {
				return wpp;
			}
		}
		WorldPlayerPermissions wpp = new WorldPlayerPermissions(w.getName(), p.getUniqueID());
		wpp.setAll(false);
		worldPlayerPermissions.add(wpp);
		return wpp;
	}

	public static boolean canVisit(EntityPlayer p, World w) {
		WorldPlayerPermissions wpp = getForWorldAndPlayer(w, p);
		if (wpp != null)
			return wpp.canVisit();
		return false;
	}

	public static boolean canBuild(EntityPlayer p, World w) {
		WorldPlayerPermissions wpp = getForWorldAndPlayer(w, p);
		if (wpp != null)
			return wpp.canBuild();
		return false;
	}

	public static boolean canDestroy(EntityPlayer p, World w) {
		WorldPlayerPermissions wpp = getForWorldAndPlayer(w, p);
		if (wpp != null)
			return wpp.canDestroy();
		return false;
	}

	public static boolean canCollectItemsAndExp(EntityPlayer p, World w) {
		WorldPlayerPermissions wpp = getForWorldAndPlayer(w, p);
		if (wpp != null)
			return wpp.canCollectItemsExp();
		return false;
	}

	public static boolean canKill(EntityPlayer p, World w) {
		WorldPlayerPermissions wpp = getForWorldAndPlayer(w, p);
		if (wpp != null)
			return wpp.canKill();
		return false;
	}

	public static boolean canInteract(EntityPlayer p, World w) {
		WorldPlayerPermissions wpp = getForWorldAndPlayer(w, p);
		if (wpp != null)
			return wpp.canInteract();
		return false;
	}

	public static boolean canVisit(EntityPlayer p, GHWorld w) {
		WorldPlayerPermissions wpp = getForWorldAndPlayer(w, p);
		if (wpp != null)
			return wpp.canVisit();
		return false;
	}

	public static boolean canBuild(EntityPlayer p, GHWorld w) {
		WorldPlayerPermissions wpp = getForWorldAndPlayer(w, p);
		if (wpp != null)
			return wpp.canBuild();
		return false;
	}

	public static boolean canDestroy(EntityPlayer p, GHWorld w) {
		WorldPlayerPermissions wpp = getForWorldAndPlayer(w, p);
		if (wpp != null)
			return wpp.canDestroy();
		return false;
	}

	public static boolean canCollectItemsAndExp(EntityPlayer p, GHWorld w) {
		WorldPlayerPermissions wpp = getForWorldAndPlayer(w, p);
		if (wpp != null)
			return wpp.canCollectItemsExp();
		return false;
	}

	public static boolean canKill(EntityPlayer p, GHWorld w) {
		WorldPlayerPermissions wpp = getForWorldAndPlayer(w, p);
		if (wpp != null)
			return wpp.canKill();
		return false;
	}

	public static boolean canInteract(EntityPlayer p, GHWorld w) {
		WorldPlayerPermissions wpp = getForWorldAndPlayer(w, p);
		if (wpp != null)
			return wpp.canInteract();
		return false;
	}

	public static void setVisit(EntityPlayer p, World w, boolean flag) {
		getForWorldAndPlayerOrCreate(w, p).setVisit(flag);
	}

	public static void setBuild(EntityPlayer p, World w, boolean flag) {
		getForWorldAndPlayerOrCreate(w, p).setBuild(flag);
	}

	public static void setDestroy(EntityPlayer p, World w, boolean flag) {
		getForWorldAndPlayerOrCreate(w, p).setDestroy(flag);
	}

	public static void setCollectItemsAndExp(EntityPlayer p, World w, boolean flag) {
		getForWorldAndPlayerOrCreate(w, p).setCollectItemExp(flag);
	}

	public static void setKill(EntityPlayer p, World w, boolean flag) {
		getForWorldAndPlayerOrCreate(w, p).setKill(flag);
	}

	public static void setInteract(EntityPlayer p, World w, boolean flag) {
		getForWorldAndPlayerOrCreate(w, p).setInteract(flag);
	}

	public static void setVisit(EntityPlayer p, GHWorld w, boolean flag) {
		getForWorldAndPlayerOrCreate(w, p).setVisit(flag);
	}

	public static void setBuild(EntityPlayer p, GHWorld w, boolean flag) {
		getForWorldAndPlayerOrCreate(w, p).setBuild(flag);
	}

	public static void setDestroy(EntityPlayer p, GHWorld w, boolean flag) {
		getForWorldAndPlayerOrCreate(w, p).setDestroy(flag);
	}

	public static void setCollectItemsAndExp(EntityPlayer p, GHWorld w, boolean flag) {
		getForWorldAndPlayerOrCreate(w, p).setCollectItemExp(flag);
	}

	public static void setKill(EntityPlayer p, GHWorld w, boolean flag) {
		getForWorldAndPlayerOrCreate(w, p).setKill(flag);
	}

	public static void setInteract(EntityPlayer p, GHWorld w, boolean flag) {
		getForWorldAndPlayerOrCreate(w, p).setInteract(flag);
	}
}
