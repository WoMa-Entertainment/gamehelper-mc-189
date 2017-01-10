package net.wfoas.gh.multipleworlds;

import net.minecraft.profiler.Profiler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;

public class GHWorldServer extends WorldServer {

	private GHWorld world;
	private GHSimpleTeleporter worldTeleporter;

	public GHWorldServer(MinecraftServer mcServer, ISaveHandler saveHandler, String worldname, int dimensionId,
			WorldSettings worldSettings, WorldServer worldServer, Profiler profiler, GHWorld world) {
		super(mcServer, saveHandler, new WorldInfo(worldSettings, worldname), dimensionId, profiler);
		this.mapStorage = worldServer.getMapStorage();
		this.worldScoreboard = worldServer.getScoreboard();
		this.worldTeleporter = new GHSimpleTeleporter(this);
		this.world = world;
	}

	@Override
	public Teleporter getDefaultTeleporter() {
		return this.worldTeleporter;
	}

	@Override
	protected void saveLevel() throws MinecraftException {
		this.perWorldStorage.saveAllData();
		this.saveHandler.saveWorldInfo(this.worldInfo);
	}

	public GHWorld getMultiworld() {
		return world;
	}

}