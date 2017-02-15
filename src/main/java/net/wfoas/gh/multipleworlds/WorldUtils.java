package net.wfoas.gh.multipleworlds;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.command.server.CommandTeleport;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.network.play.server.S21PacketChunkData;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3i;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.wfoas.gh.GameHelper;

public class WorldUtils {
	public static List<EntityPlayerMP> getPlayerList() {
		MinecraftServer mc = MinecraftServer.getServer();
		return mc == null || mc.getConfigurationManager() == null ? new ArrayList<EntityPlayerMP>()
				: mc.getConfigurationManager().playerEntityList;
	}

	public static boolean isFree(World world, int x, int y, int z, int h) {
		for (int i = 0; i < h; i++) {
			Block block = world.getBlockState(new BlockPos(new Vec3i(x, y, z))).getBlock();
			if (block.getMaterial().isSolid() || block.getMaterial().isLiquid())
				return false;
		}
		return true;
	}

	public static int placeInWorld(World world, int x, int y, int z, int h) {
		if (y >= 0 && isFree(world, x, y, z, h)) {
			while (isFree(world, x, y - 1, z, h) && y > 0)
				y--;
		} else {
			if (y < 0)
				y = 0;
			y++;
			while (y + h < world.getHeight() && !isFree(world, x, y, z, h))
				y++;
		}
		if (y == 0)
			y = world.getHeight() - h;
		return y;
	}

	public static int placeInWorld(World world, int x, int y, int z) {
		return placeInWorld(world, x, y, z, 2);
	}

	public static void teleport(EntityPlayerMP player, TeleportPoint point) {
		if (point.getWorld() == null) {
			DimensionManager.initDimension(point.getDimension());
			if (point.getWorld() == null) {
				// ChatOutputHandler.chatError(player,
				// Translator.translate("Unable to teleport! Target dimension
				// does not exist"));
				System.err.println("Targ-Dim does not exists!");
				return;
			}
		}
		checkedTeleport(player, point);
	}

	public static void checkedTeleport(EntityPlayerMP player, TeleportPoint point) {
		doTeleport(player, point);
	}

	public static void doTeleport(EntityPlayerMP player, TeleportPoint point) {
		if (point.getWorld() == null) {
			System.err.println("World = null!");
			return;
		}
		boolean isRiding = false;
		Entity e = player.ridingEntity;
		isRiding = e != null;
		player.mountEntity(null);
		if (isRiding)
			doTeleportEntity(e, point);

		if (player.dimension != point.getDimension()) {
			GHSimpleTeleporter teleporter = new GHSimpleTeleporter(point.getWorld());
			transferPlayerToDimension(player, point.getDimension(), teleporter);
		} else {
			player.playerNetServerHandler.setPlayerLocation(point.getX(), point.getY(), point.getZ(), point.getYaw(),
					point.getPitch());
		}
		if (isRiding)
			player.mountEntity(e);
	}

	public static void doTeleportEntity(Entity entity, TeleportPoint point) {
		if (entity instanceof EntityPlayerMP) {
			doTeleport((EntityPlayerMP) entity, point);
			return;
		}
		if (entity.dimension != point.getDimension())
			entity.travelToDimension(point.getDimension());
		entity.setLocationAndAngles(point.getX(), point.getY(), point.getZ(), point.getYaw(), point.getPitch());
	}

	public static void transferPlayerToDimension(EntityPlayerMP player, int dimension, Teleporter teleporter) {
		int oldDim = player.dimension;
		MinecraftServer mcServer = MinecraftServer.getServer();

		WorldServer oldWorld = mcServer.worldServerForDimension(player.dimension);
		player.dimension = dimension;
		WorldServer newWorld = mcServer.worldServerForDimension(player.dimension);
		player.playerNetServerHandler.sendPacket(new S07PacketRespawn(player.dimension, newWorld.getDifficulty(),
				newWorld.getWorldInfo().getTerrainType(), player.theItemInWorldManager.getGameType())); // Forge:
																										// Use
																										// new
																										// dimensions
																										// information
		oldWorld.removePlayerEntityDangerously(player);
		player.isDead = false;

		transferEntityToWorld(player, oldDim, oldWorld, newWorld, teleporter);

		mcServer.getConfigurationManager().updateTimeAndWeatherForPlayer(player, oldWorld);
		// player.playerNetServerHandler.setPlayerLocation(player.posX,
		// player.posY, player.posZ, player.rotationYaw,
		// player.rotationPitch);
		BlockPos bp = newWorld.getSpawnPoint();
		if (bp.getX() == 0 && bp.getY() == 0 && bp.getZ() == 0) {
			int y = placeInWorld(newWorld, 0, 0, 0);
			newWorld.setSpawnPoint(new BlockPos(0, y, 0));
			bp = newWorld.getSpawnPoint();
		}
		player.playerNetServerHandler.setPlayerLocation(bp.getX() + 0.5f, bp.getY() + 0.5f, bp.getZ() + 0.5f,
				player.rotationYaw, player.rotationPitch);
		player.theItemInWorldManager.setWorld(newWorld);
		mcServer.getConfigurationManager().updateTimeAndWeatherForPlayer(player, newWorld);
		mcServer.getConfigurationManager().syncPlayerInventory(player);
		Iterator<?> iterator = player.getActivePotionEffects().iterator();
		while (iterator.hasNext()) {
			PotionEffect potioneffect = (PotionEffect) iterator.next();
			player.playerNetServerHandler.sendPacket(new S1DPacketEntityEffect(player.getEntityId(), potioneffect));
		}
		FMLCommonHandler.instance().firePlayerChangedDimensionEvent(player, oldDim, dimension);
	}

	public static void teleportPlayerToDimension(EntityPlayerMP player, int dimension) {
		WorldServer ws = DimensionManager.getWorld(dimension);
		System.out.println("Transfer Entity: WorldServer: " + ws);
		getWorldSpawn(ws);
		BlockPos spawn = ws.getSpawnPoint();
		teleport(player, new TeleportPoint(dimension, spawn, player.rotationPitch, player.rotationYaw));
	}

	public static void getWorldSpawn(WorldServer newWorld) {
		BlockPos spawn = newWorld.getSpawnPoint();
		System.out.println(spawn);
	}

	public static void transferEntityToWorld(Entity entity, int oldDim, WorldServer oldWorld, WorldServer newWorld,
			Teleporter teleporter) {
		if (entity == null) {
			System.out.println("Entity NULL");
		}
		WorldProvider pOld = oldWorld.provider;
		WorldProvider pNew = newWorld.provider;
		double moveFactor = pOld.getMovementFactor() / pNew.getMovementFactor();
		double d0 = entity.posX * moveFactor;
		double d1 = entity.posZ * moveFactor;
		double d3 = entity.posX;
		double d4 = entity.posY;
		double d5 = entity.posZ;
		float f = entity.rotationYaw;
		d0 = MathHelper.clamp_int((int) d0, -29999872, 29999872);
		d1 = MathHelper.clamp_int((int) d1, -29999872, 29999872);
		// if (entity == null) {
		// System.out.println("Entity NULL");
		// }
		if (entity.isEntityAlive()) {
			entity.setLocationAndAngles(d0, entity.posY, d1, entity.rotationYaw, entity.rotationPitch);
			if (teleporter instanceof GHSimpleTeleporter) {
				GHSimpleTeleporter ghst = (GHSimpleTeleporter) teleporter;
				// if (entity == null) {
				// System.out.println("Entity NULL");
				// }
				ghst.placeInPortal(entity, d3, d4, d5, f);
			} else {
				// if (entity == null) {
				// System.out.println("Entity NULL");
				// }
				teleporter.placeInPortal(entity, f);
			}
			newWorld.spawnEntityInWorld(entity);
			newWorld.updateEntityWithOptionalForce(entity, true);
			if (entity instanceof EntityPlayerMP) {
				((EntityPlayerMP) entity).playerNetServerHandler.setPlayerLocation(entity.posX + 500, entity.posY,
						entity.posZ, entity.rotationYaw, entity.rotationPitch);
				final Entity _e1 = entity;
				GameHelper.getScheduler().scheduleSyncDelayedTask(new Runnable() {
					@Override
					public void run() {
						((EntityPlayerMP) _e1).playerNetServerHandler.setPlayerLocation(_e1.posX - 500, _e1.posY + 0.2,
								_e1.posZ, _e1.rotationYaw, _e1.rotationPitch);
					}
				}, 20 * 3l);
			}
			// ((EntityPlayerMP)entity).loadedChunks.clear();
			// CommandTeleport CommandGameRule
		}
		entity.setWorld(newWorld);
	}
}