package net.wfoas.gh.dropsapi.pdr;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class LocationA extends Vec3d {

	World w;
	float yaw, pitch;

	public LocationA(Vec3d l, World w) {
		this(w, l.getX(), l.getY(), l.getZ());
	}

	public LocationA(Vec3 l, World w) {
		this(w, l.xCoord, l.yCoord, l.zCoord);
	}

	public LocationA(BlockPos l, World w) {
		this(w, l.getX(), l.getY(), l.getZ());
	}

	public LocationA(World world, double x, double y, double z) {
		super(x, y, z);
		this.w = world;
	}

	public LocationA(World world, double x, double y, double z, float yaw, float pitch) {
		this(world, x, y, z);
		this.yaw = yaw;
		this.pitch = pitch;
	}

	public World getWorld() {
		return w;
	}

	@Override
	public LocationA getRelative(double x, double y, double z) {
		Vec3d rel = super.getRelative(x, y, z);
		return new LocationA(rel, w);
	}

	static Random r = new Random();

	public LocationA createRandomOffset(int max) {
		int randzahlx = r.nextInt(max * 2) - max;
		int randzahly = r.nextInt(max * 2) - max;
		int randzahlz = r.nextInt(max * 2) - max;
		LocationA loc = getRelative(randzahlx, randzahly, randzahlz);
		return loc;
	}

}
