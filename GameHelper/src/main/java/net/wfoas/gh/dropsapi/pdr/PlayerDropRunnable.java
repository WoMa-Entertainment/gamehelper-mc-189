package net.wfoas.gh.dropsapi.pdr;

import net.minecraft.entity.player.EntityPlayer;

public abstract class PlayerDropRunnable {
	static java.util.Random random = new java.util.Random();
//	String name;
	boolean useplayerloc;
//	String usename;

	public PlayerDropRunnable(
//			String name,
			boolean useplayerloc
//			, String usename
			) {
//		this.name = name;
		this.useplayerloc = useplayerloc;
//		this.usename = usename;
	}

//	public String getName() {
//		return name;
//	}

//	public String getUseName() {
//		return usename;
//	}

	public boolean usesPlayerLoc() {
		return useplayerloc;
	}

	public int nextRandom() {
		return random.nextInt(31) + 1;
	}

	public abstract void run(EntityPlayer p, Vec3d l);
}