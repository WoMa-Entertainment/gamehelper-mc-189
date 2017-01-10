package net.wfoas.gh.minersinventory.helmetlight;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;

public class LightProperties {
	public static int getItem(String key, EntityPlayer playerMp) {
		return playerMp.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).getInteger(key);
	}

	public static void setItem(String key, int item, EntityPlayer playerMp) {
		playerMp.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).setInteger(key, item);
	}

	public static BlockPos getPlayerPosition(EntityPlayer playerMp) {
		return playerMp.getPosition();
	}
}
