package net.wfoas.gh.enchantment.event;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.GameHelperCoreModule;

public class SoulboundEvent {
	@SubscribeEvent
	public void playerDie(PlayerDropsEvent lde) {
		if (!lde.entityPlayer.worldObj.isRemote) {
			List<ItemStack> stackList = new ArrayList<ItemStack>();
			Iterator<EntityItem> items = lde.drops.iterator();
			while (items.hasNext()) {
				EntityItem ei = items.next();
				ItemStack is = ei.getEntityItem();
				if (GameHelper.getUtils().hasEnchantment(is, GameHelperCoreModule.ENCH_SOULBOUND)) {
					stackList.add(is.copy());
					items.remove();
					continue;
				}
			}
			if (stackList.isEmpty()) {
				return;
			}
			NBTTagList nbttl = new NBTTagList();
			for (ItemStack is : stackList) {
				if (is != null)
					nbttl.appendTag(is.writeToNBT(new NBTTagCompound()));
			}
			if (!lde.entityPlayer.getEntityData().hasKey(EntityPlayer.PERSISTED_NBT_TAG))
				lde.entityPlayer.getEntityData().setTag(EntityPlayer.PERSISTED_NBT_TAG, new NBTTagCompound());
			lde.entityPlayer.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG)
					.setTag("SoulboundLastDeathItems", nbttl);
		}
	}

	@SubscribeEvent
	public void playerRespawn(PlayerRespawnEvent pre) {
		if (!pre.player.worldObj.isRemote) {
			if (!pre.player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG)
					.hasKey("SoulboundLastDeathItems")) {
				return;
			}
			// System.out.println("gg");
			NBTTagList nbttl = (NBTTagList) pre.player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG)
					.getTag("SoulboundLastDeathItems");
			for (int i = 0; i < nbttl.tagCount(); i++) {
				pre.player.inventory.addItemStackToInventory(ItemStack.loadItemStackFromNBT(nbttl.getCompoundTagAt(i)));
			}
			pre.player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG)
					.removeTag("SoulboundLastDeathItems");
		}
	}
}
