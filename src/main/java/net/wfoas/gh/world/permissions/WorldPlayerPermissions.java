package net.wfoas.gh.world.permissions;

import java.io.Serializable;
import java.util.UUID;

import net.minecraft.nbt.NBTTagCompound;
import net.wfoas.gh.GameHelper;

public class WorldPlayerPermissions implements Serializable {
	private static final long serialVersionUID = -2868646291526771110L;
	boolean visit = false, interact = false, kill = false, destroy = false, build = false, collectitemexp = false;
	String world, player;

	public WorldPlayerPermissions(String worldname, UUID playername) {
		this.world = worldname;
		this.player = playername.toString();
	}

	public void setVisit(boolean flag) {
		this.visit = flag;
	}

	public void setInteract(boolean flag) {
		this.interact = flag;
	}

	public void setKill(boolean flag) {
		this.kill = flag;
	}

	public void setDestroy(boolean flag) {
		this.destroy = flag;
	}

	public void setBuild(boolean flag) {
		this.build = flag;
	}

	public void setCollectItemExp(boolean flag) {
		this.collectitemexp = flag;
	}

	public void setAll(boolean flag) {
		setDestroy(flag);
		setKill(flag);
		setInteract(flag);
		setVisit(flag);
		setBuild(flag);
		setCollectItemExp(flag);
	}

	public boolean canVisit() {
		return visit;
	}

	public boolean canInteract() {
		return interact;
	}

	public boolean canKill() {
		return kill;
	}

	public boolean canDestroy() {
		return destroy;
	}

	public boolean canBuild() {
		return build;
	}

	public boolean canCollectItemsExp() {
		return collectitemexp;
	}

	public String getPlayer() {
		return player;
	}

	public String getWorld() {
		return world;
	}

	public NBTTagCompound writeToNBTTagCompound(NBTTagCompound tag) {
		if (tag == null) {
			GameHelper.println("WPP: NBTTagCompound is null!");
			return null;
		}
		tag.setString("world_name", world);
		tag.setString("player_uuid", player);
		tag.setBoolean("perm_visit", visit);
		tag.setBoolean("perm_interact", interact);
		tag.setBoolean("perm_kill", kill);
		tag.setBoolean("perm_destroy", destroy);
		tag.setBoolean("perm_build", build);
		tag.setBoolean("perm_collect_item_exp", collectitemexp);
		return tag;
	}

	public static WorldPlayerPermissions readFromNBTTagCompound(NBTTagCompound tag) {
		WorldPlayerPermissions wpp = new WorldPlayerPermissions(tag.getString("world_name"),
				UUID.fromString(tag.getString("player_uuid")));
		wpp.setVisit(tag.getBoolean("perm_visit"));
		wpp.setInteract(tag.getBoolean("perm_interact"));
		wpp.setKill(tag.getBoolean("perm_kill"));
		wpp.setDestroy(tag.getBoolean("perm_destroy"));
		wpp.setBuild(tag.getBoolean("perm_build"));
		wpp.setCollectItemExp(tag.getBoolean("perm_collect_item_exp"));
		return wpp;
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof WorldPlayerPermissions))
			return false;
		System.out.println("World:>" + ((WorldPlayerPermissions) obj).world);
		WorldPlayerPermissions other = (WorldPlayerPermissions) obj;
		return other.world.equals(world) && other.player.equals(player);
	}
}