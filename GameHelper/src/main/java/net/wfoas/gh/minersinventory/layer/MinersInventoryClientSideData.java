package net.wfoas.gh.minersinventory.layer;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class MinersInventoryClientSideData {
	public static final byte ID_HEAD_LIGHT = 0, ID_BACKPACK = 1, ID_MINERSBELT = 2, ID_DAGGERSLOT = 3;
	public static Map<EntityPlayer, ItemStack> headLights = new HashMap<EntityPlayer, ItemStack>(),
			backpacks = new HashMap<EntityPlayer, ItemStack>(),
			minersbelts_base = new HashMap<EntityPlayer, ItemStack>(),
			daggerSlot_base = new HashMap<EntityPlayer, ItemStack>();

	public static void disconnectAndDeleteData() {
		headLights.clear();
		backpacks.clear();
		minersbelts_base.clear();
		daggerSlot_base.clear();
	}

	public static void addData(EntityPlayer player, ItemStack stack, byte id) {
		switch (id) {
		case 0:
			if (headLights.containsKey(player))
				headLights.remove(player);
			headLights.put(player, stack);
			return;
		case 1:
			if (backpacks.containsKey(player))
				backpacks.remove(player);
			backpacks.put(player, stack);
			return;
		case 2:
			if (minersbelts_base.containsKey(player))
				minersbelts_base.remove(player);
			minersbelts_base.put(player, stack);
			return;
		case 3:
			if (daggerSlot_base.containsKey(player))
				daggerSlot_base.remove(player);
			daggerSlot_base.put(player, stack);
			return;
		default:
			return;
		}
	}
}
