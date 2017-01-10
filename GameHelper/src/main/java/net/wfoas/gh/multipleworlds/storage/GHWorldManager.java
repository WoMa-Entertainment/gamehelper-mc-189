package net.wfoas.gh.multipleworlds.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.village.VillageCollection;
import net.minecraft.world.WorldManager;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldSettings.GameType;
import net.minecraft.world.WorldType;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.network.ForgeMessage.DimensionRegisterMessage;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.network.FMLEmbeddedChannel;
import net.minecraftforge.fml.common.network.FMLOutboundHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.multipleworlds.GHWorld;
import net.wfoas.gh.multipleworlds.GHWorldSaveHandler;
import net.wfoas.gh.multipleworlds.GHWorldServer;
import net.wfoas.gh.playernameuuid.PlayerNameUUID;
import net.wfoas.gh.villager.FileVillagerProfessionIdMap;
import net.wfoas.gh.world.owner.WorldOwners;
import net.wfoas.gh.world.permissions.WorldPermissions;

public class GHWorldManager {
	protected static Map<String, GHWorld> worlds = new HashMap<String, GHWorld>();
	protected static Map<Integer, GHWorld> worldsByDim = new HashMap<Integer, GHWorld>();
	// protected static Map<String, Integer> worldProviderClasses = new
	// HashMap<String, Integer>();
	protected static Map<String, WorldType> worldTypes = new HashMap<String, WorldType>();
	protected static Map<String, GHWorld> loadedWorlds = new HashMap<String, GHWorld>();
	// protected static ArrayList<WorldServer> worldsToDelete = new
	// ArrayList<WorldServer>();
	// protected static ArrayList<WorldServer> worldsToRemove = new
	// ArrayList<WorldServer>();

	public static List<String> appendices;

	static {
		appendices = new ArrayList<String>();
		appendices.add("_overworld");
		appendices.add("_nether");
		appendices.add("_the_end");
		appendices.add("_minersdim");
	}

	public static String removeDimensionSpecificAppendices(String name) {
		String newname = name;
		for (String s : appendices) {
			newname = GameHelper.getUtils().replaceLast(newname, s, "");
		}
		return newname;
	}

	public static void serverStart() {
		try {
			serverStart0();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("WP");
		WorldPermissions.load();
		System.out.println("Wo");
		WorldOwners.load();
		System.out.println("PNUUID");
		PlayerNameUUID.load();
		System.out.println("END");
	}

	public static List<String> getWorlds() {
		List l = new ArrayList<String>();
		l.addAll(worlds.keySet());
		return l;
	}

	public static List<String> getGHWorldsAndNormalWorld() {
		List l = new ArrayList<String>();
		l.addAll(worlds.keySet());
		l.add(DimensionManager.getWorld(0).getWorldInfo().getWorldName());
		return l;
	}

	public static boolean existsAndIsNonGHWorld(String s) {
		return DimensionManager.getWorld(0).getWorldInfo().getWorldName().equalsIgnoreCase(s);
	}
	
	volatile static String WORLD_DEFAULT_NAME;

	private static void serverStart0() throws IOException {
		worlds = new HashMap<String, GHWorld>();
		worldsByDim = new HashMap<Integer, GHWorld>();
		worldTypes = new HashMap<String, WorldType>();
		WORLD_DEFAULT_NAME = MinecraftServer.getServer().getEntityWorld().getWorldInfo().getWorldName();
		loadWorldTypes();
		// worldProviderClasses = new HashMap<String, Integer>();
		// worldsToDelete = new ArrayList<WorldServer>();
		// worldsToRemove = new ArrayList<WorldServer>();
		File f = new File(DimensionManager.getWorld(0).getSaveHandler().getWorldDirectory(), "dimensions.gh");
		if (!f.exists()) {
			f.createNewFile();
			return;
		}
		// System.out.println(f.getAbsolutePath());
		FileInputStream fis = new FileInputStream(f);
		NBTTagCompound nbttc = CompressedStreamTools.readCompressed(fis);
		NBTTagList nbttl = (NBTTagList) nbttc.getTag("Worlds");
		System.out.println("Worlds:");
		for (int i = 0; i < nbttl.tagCount(); i++) {
			NBTTagCompound wnbt = nbttl.getCompoundTagAt(i);
			GHWorld w = GHWorld.readFromNBT(wnbt);
			if (w != null) {
				System.out.println(
						"- " + w.name + ", WP: " + w.provider + " >Type: " + w.worldType + " @DimID: " + w.dimensionId);
				worlds.put(w.getName(), w);
				worldsByDim.put(w.dimensionId, w);
			}
		}
		for (String s : worlds.keySet())
			System.out.println(s);
	}

	public static void serverStop() {
		try {
			serverStop0();
		} catch (IOException e) {
			e.printStackTrace();
		}
		WorldPermissions.save();
		WorldOwners.save();
		PlayerNameUUID.save();
	}

	public static void save() {
		// System.out.println("Saving data for multidimensions-worlds");
		try {
			save0();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void save0() throws IOException {
		NBTTagCompound data = new NBTTagCompound();
		NBTTagList nbttl = new NBTTagList();
		data.setTag("Worlds", nbttl);
		for (Entry<String, GHWorld> entry : worlds.entrySet()) {
			nbttl.appendTag(entry.getValue().writeToNBT());
			// System.out.println("Saving data for multidimensions-worlds: " +
			// entry.getKey());
		}
		File f = new File(DimensionManager.getWorld(0).getSaveHandler().getWorldDirectory(), "dimensions.gh");
		// System.out.println(f.getAbsolutePath());
		FileOutputStream fos = new FileOutputStream(f);
		CompressedStreamTools.writeCompressed(data, fos);
		fos.close();
	}

	private static void serverStop0() throws IOException {
		NBTTagCompound data = new NBTTagCompound();
		NBTTagList nbttl = new NBTTagList();
		for (Entry<String, GHWorld> entry : worlds.entrySet()) {
			nbttl.appendTag(entry.getValue().writeToNBT());
		}
		data.setTag("Worlds", nbttl);
		File f = new File(DimensionManager.getWorld(0).getSaveHandler().getWorldDirectory(), "dimensions.gh");
		FileOutputStream fos = new FileOutputStream(f);
		CompressedStreamTools.writeCompressed(data, fos);
		fos.close();
	}

	public static final String WORLD_TYPE_DEFAULT = WorldType.DEFAULT.getWorldTypeName(), PROVIDER_NORMAL = "normal",
			PROVIDER_NETHER = "nether", PROVIDER_END = "end", PROVIDER_MINERSDIM = "minersdim";

	public static void createWorld(GHWorld world) {
		if (getGHWorldsAndNormalWorld().contains(world.getName())) {
			throwException("World already exists!");
			return;
		}
		loadWorld(world);
		worlds.put(world.getName(), world);
		world.save();
		GameHelper.getUtils().loadWorldForDimension(world.dimensionId);
		save();
		GameHelper.getUtils().broadcastTranslation("gamehelper.msg.createdworld.success", world.getName(),
				world.getDimensionId());
		// "World " + world.getName() + " @ " + world.getDimensionId() + "
		// successfully created!");
	}

	public static boolean exists(GHWorld world) {
		return worlds.containsKey(world.getName())
				|| MinecraftServer.getServer().getEntityWorld().getWorldInfo().getWorldName().equalsIgnoreCase(world.name);
	}

	public static boolean isWorldAlreadyCreated(GHWorld world) {
		return worlds.containsKey(world.getName());
	}

	public static boolean isWorldAlreadyCreated(String world) {
		return worlds.containsKey(world);
	}

	public static boolean isWorldAlreadyLoaded(GHWorld ghw) {
		return loadedWorlds.containsKey(ghw.getName());
	}

	public static void loadWorld(GHWorld world) {
		if (isWorldAlreadyLoaded(world))
			return;
		if (world.worldLoaded)
			return;
		if (isWorldAlreadyCreated(world)) {
			world = worlds.get(world.getName());
		}
		try {
			System.out.println("Loading GHWorld: " + world);
			world.providerId = getWorldProviderId(world.provider);
			world.worldTypeObj = getWorldTypeByName(world.worldType);
			if (DimensionManager.isDimensionRegistered(world.dimensionId))
				// if (DimensionManager.isDimensionRegistered(-78))
				world.dimensionId = DimensionManager.getNextFreeDimId();
			// else
			// world.dimensionId = -78;
			// world.dimensionId = DimensionManager.getNextFreeDimId();
			// DimensionManager.isDimensionRegistered(-78);
			DimensionManager.registerDimension(world.dimensionId, world.providerId);
			worldsByDim.put(world.dimensionId, world);
			MinecraftServer mcServer = MinecraftServer.getServer();
			WorldServer overworld = DimensionManager.getWorld(0);
			if (overworld == null)
				throw new RuntimeException("Cannot hotload dimension: Overworld is not Loaded!");
			ISaveHandler savehandler = new GHWorldSaveHandler(overworld.getSaveHandler(), world);
			WorldSettings worldSettings = new WorldSettings(world.seed, GameType.SURVIVAL, world.mapFeaturesEnabled,
					false, world.worldTypeObj);
			WorldServer worldServer = new GHWorldServer(mcServer, savehandler, //
					world.name, world.dimensionId, worldSettings, //
					overworld, mcServer.theProfiler, world);
			if (worldServer.villageCollectionObj == null)
				worldServer.villageCollectionObj = new VillageCollection(worldServer);
			// worldServer.init();
			DimensionManager.setWorld(world.dimensionId, worldServer);
			worldServer.provider.setDimension(world.dimensionId);
			worldServer.addWorldAccess(new WorldManager(mcServer, worldServer));
			if (!mcServer.isSinglePlayer())
				worldServer.getWorldInfo().setGameType(mcServer.getGameType());
			// worldServer.theChunkProviderServer.serverChunkGenerator.provideChunk(0,
			// 0);
			// mcServer.func_147139_a(mcServer.func_147135_j());
			mcServer.setDifficultyForAllWorlds(mcServer.getDifficulty());
			world.updateWorldSettings();
			world.worldLoaded = true;
			loadedWorlds.put(world.getName(), world);
			world.error = false;
			MinecraftForge.EVENT_BUS.post(new WorldEvent.Load(worldServer));
			FMLEmbeddedChannel channel = NetworkRegistry.INSTANCE.getChannel("FORGE", Side.SERVER);
			DimensionRegisterMessage msg = new DimensionRegisterMessage(world.dimensionId, world.providerId);
			channel.attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALL);
			channel.writeOutbound(msg);
			System.out.println("Successfully loaded GHWorld: " + world);
		} catch (Exception e) {
			System.err.println("Error during World-Load proccess!");
			world.error = true;
			// throw e;
			e.printStackTrace();
		} finally {
			save();
		}
	}

	public static void importWorld(String name, String provider, String worldType) {
		GHWorld ghworld = new GHWorld(name, provider, worldType);
		loadWorld(ghworld);
	}

	public static void unloadWorld(GHWorld ghw) {
		if (ghw.isLoaded())
			DimensionManager.unloadWorld(ghw.dimensionId);
	}

	private static void throwException(String reason) {
		System.err.println(reason);
	}

	public static String getWorldProviderStringById(int wpID) {
		if (wpID == 0) {
			return PROVIDER_NORMAL;
		} else if (wpID == -1) {
			return PROVIDER_NETHER;
		} else if (wpID == 1) {
			return PROVIDER_END;
		} else if (wpID == 2) {
			return PROVIDER_MINERSDIM;
		} else {
			return null;
		}
	}

	public static int getWorldProviderId(String providerName) {
		if (providerName.toLowerCase().equalsIgnoreCase("normal")) {
			return 0;
		} else if (providerName.toLowerCase().equalsIgnoreCase("nether")) {
			return -1;
		} else if (providerName.toLowerCase().equalsIgnoreCase("end")) {
			return 1;
		} else if (providerName.toLowerCase().equalsIgnoreCase("minersdim")) {
			return 2;
		} else {
			return Integer.MIN_VALUE;
		}
		// switch (providerName.toLowerCase()) {
		// case "normal":
		// return 0;
		// case "nether":
		// return -1;
		// case "end":
		// return 1;
		// case "minerdim":
		// return -2;
		// default:
		// // Integer providerId = worldProviderClasses.get(providerName);
		// // if (providerId == null) {
		// // throwException("There was no WorldProvider found!");
		// return Integer.MIN_VALUE;
		// // }
		// // return providerId;
		// }
	}

	public static WorldType getWorldTypeByName(String worldType) {
		WorldType type = worldTypes.get(worldType.toUpperCase());
		if (type == null) {
			throwException("This world-type was not found.");
			return null;
		}
		return type;
	}

	public static void loadWorldTypes() {
		for (int i = 0; i < WorldType.worldTypes.length; ++i) {
			WorldType type = WorldType.worldTypes[i];
			if (type == null)
				continue;
			String name = type.getWorldTypeName().toUpperCase();
			if (name.equals("DEFAULT_1_1"))
				continue;

			worldTypes.put(name, type);
		}
		System.out.println("Available WorldTypes:");
		for (String worldType : worldTypes.keySet())
			System.out.println("- " + worldType);
	}

	public static GHWorld getLoadedGHWorld(String name) {
		return loadedWorlds.get(name);
	}

	public static GHWorld getGHWorldByDimensionID(int dimID) {
		return worldsByDim.get(dimID);
	}

	public static GHWorld getGHWorldByDimensionIDForce(int dimID, boolean force) {
		if (force) {
			if (getGHWorldByDimensionID(dimID) == null) {
				if (DimensionManager.getWorld(dimID) != null) {
					return new GHWorld("_DIMID:" + dimID, null, null);
				} else {
					return null;
				}
			} else
				return getGHWorldByDimensionID(dimID);
		} else
			return getGHWorldByDimensionID(dimID);
	}
}
