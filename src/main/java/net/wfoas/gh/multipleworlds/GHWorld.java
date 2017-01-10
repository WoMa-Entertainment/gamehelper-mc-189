package net.wfoas.gh.multipleworlds;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.gson.annotations.Expose;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.DimensionManager;

public class GHWorld {

	public String name;

	public int dimensionId;

	public String provider;

	public String worldType;

	public List<String> biomes = new ArrayList<String>();

	public long seed;

	// public GameType gameType = GameType.CREATIVE;
	//
	// public EnumDifficulty difficulty = EnumDifficulty.PEACEFUL;
	//
	// public boolean allowHostileCreatures = true;
	//
	// public boolean allowPeacefulCreatures = true;

	public boolean mapFeaturesEnabled = true;

	@Expose(serialize = false)
	public boolean worldLoaded;

	@Expose(serialize = false)
	public boolean error;

	@Expose(serialize = false)
	public int providerId;

	@Expose(serialize = false)
	public WorldType worldTypeObj;

	public GHWorld(String name, String provider, String worldType, long seed) {
		this.name = name;
		this.provider = provider;
		this.worldType = worldType;

		this.seed = seed;
		// this.gameType = MinecraftServer.getServer().getGameType();
		// this.difficulty = MinecraftServer.getServer().func_147135_j();
		// this.allowHostileCreatures = true;
		// this.allowPeacefulCreatures = true;
	}

	public GHWorld(String name, String provider, String worldType) {
		this(name, provider, worldType, new Random().nextLong());
	}

	public void removeAllPlayersFromWorld() {
		WorldServer overworld = MinecraftServer.getServer().worldServerForDimension(0);
		for (EntityPlayerMP player : WorldUtils.getPlayerList()) {
			if (player.dimension == dimensionId) {
				teleport(player, overworld, true);
			}
		}
	}

	public void updateWorldSettings() {
		if (!worldLoaded)
			return;
		// WorldServer worldServer = getWorldServer();
		// worldServer.difficultySetting = difficulty;
		// worldServer.setAllowedSpawnTypes(allowHostileCreatures,
		// allowPeacefulCreatures);
	}

	public String getName() {
		return name;
	}

	public WorldServer getWorldServer() {
		if (!worldLoaded)
			return null;
		return DimensionManager.getWorld(dimensionId);
	}

	public int getDimensionId() {
		return dimensionId;
	}

	public int getProviderId() {
		return providerId;
	}

	public String getProvider() {
		return provider;
	}

	public List<String> getBiomes() {
		return biomes;
	}

	public boolean isError() {
		return error;
	}

	public boolean isLoaded() {
		return worldLoaded;
	}

	public long getSeed() {
		return seed;
	}

	// public GameType getGameType()
	// {
	// return gameType;
	// }
	//
	// public void setGameType(GameType gameType)
	// {
	// this.gameType = gameType;
	// }
	//
	// public EnumDifficulty getDifficulty()
	// {
	// return difficulty;
	// }
	//
	// public void setDifficulty(EnumDifficulty difficulty)
	// {
	// this.difficulty = difficulty;
	// updateWorldSettings();
	// }
	//
	// public boolean isAllowHostileCreatures()
	// {
	// return allowHostileCreatures;
	// }
	//
	// public void setAllowHostileCreatures(boolean allowHostileCreatures)
	// {
	// this.allowHostileCreatures = allowHostileCreatures;
	// updateWorldSettings();
	// }
	//
	// public boolean isAllowPeacefulCreatures()
	// {
	// return allowPeacefulCreatures;
	// }
	//
	// public void setAllowPeacefulCreatures(boolean allowPeacefulCreatures)
	// {
	// this.allowPeacefulCreatures = allowPeacefulCreatures;
	// updateWorldSettings();
	// }

	public void save() {
		getWorldServer().getSaveHandler().flush();
	}

	public void teleport(EntityPlayerMP player, boolean instant) {
		teleport(player, getWorldServer(), instant);
	}

	public static void teleport(EntityPlayerMP player, WorldServer world, boolean instant) {
		teleport(player, world, player.posX, player.posY, player.posZ, instant);
	}

	public static void teleport(EntityPlayerMP player, WorldServer world, double x, double y, double z,
			boolean instant) {
		boolean worldChange = player.worldObj.provider.getDimensionId() != world.provider.getDimensionId();
		if (worldChange)
			displayDepartMessage(player);

		y = WorldUtils.placeInWorld(world, (int) x, (int) y, (int) z);
		TeleportPoint target = new TeleportPoint(world.provider.getDimensionId(), x, y, z, player.rotationPitch,
				player.rotationYaw);
		if (instant)
			WorldUtils.checkedTeleport(player, target);
		else
			WorldUtils.teleport(player, target);

		if (worldChange)
			displayWelcomeMessage(player);
	}

	public NBTTagCompound writeToNBT() {
		NBTTagCompound nbbtc = new NBTTagCompound();
		nbbtc.setString("WorldName", name);
		nbbtc.setString("WorldProvider", provider);
		nbbtc.setString("WorldType", worldType);
		nbbtc.setInteger("DimensionID", dimensionId);
		return nbbtc;
	}

	public static GHWorld readFromNBT(NBTTagCompound nbttc) {
		String name = nbttc.getString("WorldName");
		String prov = nbttc.getString("WorldProvider");
		String type = nbttc.getString("WorldType");
		int dimID = nbttc.getInteger("DimensionID");
		GHWorld ghw = new GHWorld(name, prov, type);
		ghw.dimensionId = dimID;
		if (name.equals("") && prov.equals("") && type.equals("") && dimID == 0)
			return null;
		return ghw;
	}

	public static void displayDepartMessage(EntityPlayerMP player) {
		// String msg = player.worldObj.provider.getDepartMessage();
		// if (msg == null)
		// msg = "Leaving the Overworld.";
		// if (player.dimension > 1 || player.dimension < -1)
		// msg += " (#" + player.dimension + ")";
		// ChatOutputHandler.sendMessage(player, new ChatComponentText(msg));
	}

	public static void displayWelcomeMessage(EntityPlayerMP player) {
		// String msg = player.worldObj.provider.getWelcomeMessage();
		// if (msg == null)
		// msg = "Entering the Overworld.";
		// if (player.dimension > 1 || player.dimension < -1)
		// msg += " (#" + player.dimension + ")";
		// ChatOutputHandler.sendMessage(player, new ChatComponentText(msg));
	}

	public String toString() {
		return "[Name: " + name + " ; Provider: " + provider + " ; Type: " + worldType + " ; Dimension: " + dimensionId
				+ "]";
	}

}