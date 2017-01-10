package net.wfoas.gh.world.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.wfoas.gh.multipleworlds.GHWorld;
import net.wfoas.gh.world.owner.WorldOwner;
import net.wfoas.gh.world.owner.WorldOwners;
import net.wfoas.gh.world.permissions.WorldPermissions;
import net.wfoas.gh.world.permissions.WorldPlayerPermissions;

public class WorldPermissionsManager {
	public static boolean getPermission(WorldPermission wp, EntityPlayer ep, World w) {
		if (WorldOwners.isOwner(w, ep))
			return true;
		switch (wp) {
		case BUILD:
			return WorldPermissions.canBuild(ep, w);
		case COLLECT_ITEMS_EXP:
			return WorldPermissions.canCollectItemsAndExp(ep, w);
		case DESTROY:
			return WorldPermissions.canDestroy(ep, w);
		case INTERACT:
			return WorldPermissions.canInteract(ep, w);
		case KILL:
			return WorldPermissions.canKill(ep, w);
		case VISIT:
			return WorldPermissions.canVisit(ep, w);
		default:
			return false;
		}
	}

	public static boolean getPermission(WorldPermission wp, EntityPlayer ep, GHWorld w) {
		if (WorldOwners.isOwner(w, ep))
			return true;
		switch (wp) {
		case BUILD:
			return WorldPermissions.canBuild(ep, w);
		case COLLECT_ITEMS_EXP:
			return WorldPermissions.canCollectItemsAndExp(ep, w);
		case DESTROY:
			return WorldPermissions.canDestroy(ep, w);
		case INTERACT:
			return WorldPermissions.canInteract(ep, w);
		case KILL:
			return WorldPermissions.canKill(ep, w);
		case VISIT:
			return WorldPermissions.canVisit(ep, w);
		default:
			return false;
		}
	}

	public static void setPermission(WorldPermission wp, EntityPlayer ep, World w, boolean flag) {
		WorldPlayerPermissions wpp = WorldPermissions.getForWorldAndPlayer(w, ep);
		switch (wp) {
		case BUILD:
			wpp.setBuild(flag);
			break;
		case COLLECT_ITEMS_EXP:
			wpp.setCollectItemExp(flag);
			break;
		case DESTROY:
			wpp.setDestroy(flag);
			break;
		case INTERACT:
			wpp.setInteract(flag);
			break;
		case KILL:
			wpp.setKill(flag);
			break;
		case VISIT:
			wpp.setVisit(flag);
			break;
		default:
			break;
		}
	}

	public static void setPermission(WorldPermission wp, EntityPlayer ep, GHWorld w, boolean flag) {
		WorldPlayerPermissions wpp = WorldPermissions.getForWorldAndPlayer(w, ep);
		System.out.println("Set perm");
		if (wpp == null)
			return;
		switch (wp) {
		case BUILD:
			wpp.setBuild(flag);
			break;
		case COLLECT_ITEMS_EXP:
			wpp.setCollectItemExp(flag);
			break;
		case DESTROY:
			wpp.setDestroy(flag);
			break;
		case INTERACT:
			wpp.setInteract(flag);
			break;
		case KILL:
			wpp.setKill(flag);
			break;
		case VISIT:
			wpp.setVisit(flag);
			break;
		default:
			break;
		}
		// save wpp
		if (!WorldPermissions.worldPlayerPermissions.contains(wpp))
			WorldPermissions.worldPlayerPermissions.add(wpp);
	}
}
