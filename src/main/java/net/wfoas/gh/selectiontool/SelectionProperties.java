package net.wfoas.gh.selectiontool;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;

public class SelectionProperties {
	public static int getItem(String key, EntityPlayer playerMp) {
		return playerMp.getEntityData().getInteger(key);
	}

	public static void setItem(String key, int item, EntityPlayer playerMp) {
		playerMp.getEntityData().setInteger(key, item);
	}

	public static BlockPos getPlayerPosition(EntityPlayer playerMp) {
		return playerMp.getPosition();
	}
}
