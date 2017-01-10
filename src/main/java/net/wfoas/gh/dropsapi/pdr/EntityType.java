package net.wfoas.gh.dropsapi.pdr;

import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public enum EntityType {
	DIAMOND_EGGS, FIREWORK, TNT_PRIMED, LIGHTNING, IRON_GOLEM, SNOW_GOLEM, ENDERMAN, PIGZOMBIE, CREEPER, ENDERMITE, GHAST, WITCH, CAVE_SPIDER, BLAZE, MAGMA_SLIME, SLIME, SILVERFISH, SKELETON, SPIDER, GUARDIAN, WITHER_SKELETON, ZOMBIE, ELDER_GUARDIAN, BOSS_ENDER_DRAGON, BOSS_WITHER, BAT, SHEEP, PIG, COW, CHICKEN, RABBIT, SQUID, MUSHROOM_COW, OZELOT, HORSE, WOLF, VILLAGER, KILLER_BUNNY, GIANT_ZOMBIE, SKELETON_HORSE, ZOMBIE_HORSE;

	public EntityTNTPrimed spawnTNT(int fuse, Vec3d pos, World world) {
		EntityTNTPrimed etntp = new EntityTNTPrimed(world);
		etntp.fuse = fuse;
		etntp.setPositionAndUpdate(pos.x, pos.y, pos.z);
		world.spawnEntityInWorld(etntp);
		return etntp;
	}

	public static void dropItem(ItemStack item, LocationA locA) {
		if (item != null) {
			EntityItem ei = new EntityItem(locA.getWorld(), locA.x, locA.y, locA.z, item.copy());
			ei.onUpdate();
			locA.getWorld().spawnEntityInWorld(ei);
		}
	}

	public static void dropItem(EntityItem item, LocationA locA) {
		// EntityItem ei = new EntityItem(locA.getWorld(), locA.x, locA.y,
		// locA.z, item);
		item.onUpdate();
		locA.getWorld().spawnEntityInWorld(item);
	}

	public static EntityFireworkRocket spawnFirework(World w, Vec3d pos) {
		// ItemStack is = new ItemStack(Items.fireworks);
		// NBTTagCompound nbt = new NBTTagCompound();
		// NBTTagCompound data = new NBTTagCompound();
		// data.setByte("Flight", flight);
		// NBTTagList list = new NBTTagList();
		//
		// data.setTag("Explosions", list);
		// nbt.setTag("Fireworks", data);
		// is.writeToNBT(nbt);
		// EntityFireworkRocket efr = new EntityFireworkRocket(w);
		// efr.s
		// return null;
		return FireworksAPI.createRandomFireworks(w, pos);
	}

	public static Entity spawnEntity(EntityType et, Vec3d pos, World world) {
		Entity entity = null;
		if (et == DIAMOND_EGGS) {
			EntityDiamondEgg diamondeggs = new EntityDiamondEgg(world, pos.x, pos.y, pos.z);
			entity = diamondeggs;
		} else if (et == FIREWORK) {
			entity = spawnFirework(world, pos);
		} else if (et == TNT_PRIMED) {
			EntityTNTPrimed etntp = new EntityTNTPrimed(world);
			entity = etntp;
		} else if (et == LIGHTNING) {
			EntityLightningBolt elb = new EntityLightningBolt(world, pos.x, pos.y, pos.z);
			entity = elb;
		} else if (et == IRON_GOLEM) {
			EntityIronGolem eig = new EntityIronGolem(world);
			entity = eig;
		} else if (et == SNOW_GOLEM) {
			EntitySnowman eig = new EntitySnowman(world);
			entity = eig;
		} else if (et == ENDERMAN) {
			EntityEnderman eig = new EntityEnderman(world);
			entity = eig;
		} else if (et == PIGZOMBIE) {
			EntityPigZombie eig = new EntityPigZombie(world);
			entity = eig;
		} else if (et == CREEPER) {
			EntityCreeper eig = new EntityCreeper(world);
			entity = eig;
		} else if (et == ENDERMITE) {
			EntityEndermite eig = new EntityEndermite(world);
			entity = eig;
		} else if (et == GHAST) {
			EntityGhast eig = new EntityGhast(world);
			entity = eig;
		} else if (et == WITCH) {
			EntityWitch eig = new EntityWitch(world);
			entity = eig;
		} else if (et == CAVE_SPIDER) {
			EntityCaveSpider eig = new EntityCaveSpider(world);
			entity = eig;
		} else if (et == BLAZE) {
			EntityBlaze eig = new EntityBlaze(world);
			entity = eig;
		} else if (et == MAGMA_SLIME) {
			EntityMagmaCube eig = new EntityMagmaCube(world);
			entity = eig;
		} else if (et == SLIME) {
			EntitySlime eig = new EntitySlime(world);
			entity = eig;
		} else if (et == SILVERFISH) {
			EntitySilverfish eig = new EntitySilverfish(world);
			entity = eig;
		} else if (et == SKELETON) {
			EntitySkeleton eig = new EntitySkeleton(world);
			entity = eig;
		} else if (et == SPIDER) {
			EntitySpider eig = new EntitySpider(world);
			entity = eig;
		} else if (et == GUARDIAN) {
			EntityGuardian eig = new EntityGuardian(world);
			entity = eig;
		} else if (et == WITHER_SKELETON) {
			EntitySkeleton eig = new EntitySkeleton(world);
			eig.setSkeletonType(1);
			entity = eig;
		} else if (et == ZOMBIE) {
			EntityZombie eig = new EntityZombie(world);
			entity = eig;
		} else if (et == ELDER_GUARDIAN) {
			EntityGuardian eig = new EntityGuardian(world);
			eig.setElder(true);
			entity = eig;
		} else if (et == BOSS_ENDER_DRAGON) {
			EntityDragon eig = new EntityDragon(world);
			entity = eig;
		} else if (et == BOSS_WITHER) {
			EntityWither eig = new EntityWither(world);
			entity = eig;
		} else if (et == BAT) {
			EntityBat eig = new EntityBat(world);
			entity = eig;
		} else if (et == SHEEP) {
			EntitySheep eig = new EntitySheep(world);
			entity = eig;
		} else if (et == PIG) {
			EntityPig eig = new EntityPig(world);
			entity = eig;
		} else if (et == COW) {
			EntityCow eig = new EntityCow(world);
			entity = eig;
		} else if (et == CHICKEN) {
			EntityChicken eig = new EntityChicken(world);
			entity = eig;
		} else if (et == RABBIT) {
			EntityRabbit eig = new EntityRabbit(world);
			entity = eig;
		} else if (et == SQUID) {
			EntitySquid eig = new EntitySquid(world);
			entity = eig;
		} else if (et == MUSHROOM_COW) {
			EntityMooshroom eig = new EntityMooshroom(world);
			entity = eig;
		} else if (et == OZELOT) {
			EntityOcelot eig = new EntityOcelot(world);
			entity = eig;
		} else if (et == HORSE) {
			EntityHorse eig = new EntityHorse(world);
			entity = eig;
		} else if (et == WOLF) {
			EntityWolf eig = new EntityWolf(world);
			entity = eig;
		} else if (et == VILLAGER) {
			EntityVillager eig = new EntityVillager(world);
			entity = eig;
		} else if (et == KILLER_BUNNY) {
			EntityRabbit eig = new EntityRabbit(world);
			eig.setRabbitType(99);
			entity = eig;
		} else if (et == GIANT_ZOMBIE) {
			EntityGiantZombie eig = new EntityGiantZombie(world);
			entity = eig;
		} else if (et == SKELETON_HORSE) {
			EntityHorse eig = new EntityHorse(world);
			eig.setHorseType(4);
			entity = eig;
		} else if (et == ZOMBIE_HORSE) {
			EntityHorse eig = new EntityHorse(world);
			eig.setHorseType(3);
			entity = eig;
		}

		if (entity == null) {
			System.err.println("Cannot spawn entity! Unknown entity type: " + et);
			return null;
		}
		entity.setPositionAndUpdate(pos.x, pos.y, pos.z);
		world.spawnEntityInWorld(entity);
		return entity;
	}
}
