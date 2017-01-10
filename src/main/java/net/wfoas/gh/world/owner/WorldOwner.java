package net.wfoas.gh.world.owner;

import net.minecraft.world.World;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.multipleworlds.storage.GHWorldManager;

public class WorldOwner {
	String player, world;

	private WorldOwner(String player, String world) {
		this.player = player;
		this.world = world;
	}

//	public static WorldOwner checkAndGet(String player, String world) {
//		// if (!(GameHelper.WORLD_LIST.contains(world))) {
//		// if (!(Bukkit.getWorlds().contains(world)))
//		// return null;
//		// }
//		boolean flag = false;
//		for (String world2 : GHWorldManager.getGHWorldsAndNormalWorld()) {
//			if (world2.getName().equalsIgnoreCase(world)) {
//				flag = true;
//				break;
//			}
//		}
//		if (!(flag)) {
//			for (String string : GameHelper.WORLD_LIST) {
//				if (string.equalsIgnoreCase(world)) {
//					flag = true;
//					break;
//				}
//			}
//		}
//		if (!(flag))
//			return null;
//		return new WorldOwner(player.toUpperCase(), world.toUpperCase());
//	}

	public String getWorld() {
		return world;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String name) {
		player = name;
	}

	public void setWorld(String name) {
		world = name;
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof WorldOwner))
			return false;
		WorldOwner other = (WorldOwner) obj;
		return (other.player.equalsIgnoreCase(player)) && (other.world.equalsIgnoreCase(world));
	}
}
