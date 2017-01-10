package net.wfoas.gh.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.GameHelperCoreModule;
import net.wfoas.gh.dropsapi.pdr.LocationA;
import net.wfoas.gh.minersinventory.MinersInventory;
import net.wfoas.gh.minersinventory.layer.MinersInventoryHelper;

public class PlayerDeathEventMinersInvDrop {

	// @SubscribeEvent
	// public void entityJoint(EntityJoinWorldEvent e) {
	// if (!e.world.isRemote)
	// System.out.println("---S");
	// System.out.println(e.entity.getEntityId());
	// if (e.world.isRemote)
	// System.out.println("---C");
	//
	// }

	@SubscribeEvent
	public void playerDeathAndHasMinersInv(LivingDeathEvent lde) {
		if (lde.entity.worldObj.isRemote)
			return;
		if (lde.entity instanceof EntityPlayer) {
			EntityPlayer ep = (EntityPlayer) lde.entity;
			MinersInventory mi = MinersInventoryHelper.getMinersInventory(ep);
			ItemStack ml = mi.getStackInSlot(MinersInventory.MINERS_HELMET_LIGHT);
			ItemStack mb = mi.getStackInSlot(MinersInventory.MINERS_BACKPACK);
			ItemStack mbe = mi.getStackInSlot(MinersInventory.MINERS_BELT_ITEM_SLOT);
			ItemStack dag = mi.getStackInSlot(MinersInventory.MINERS_DOLCH_LASCHE_ITEM_SLOT);
			mi.setInventorySlotContents(MinersInventory.MINERS_HELMET_LIGHT, null);
			mi.setInventorySlotContents(MinersInventory.MINERS_BACKPACK, null);
			mi.setInventorySlotContents(MinersInventory.MINERS_BELT_ITEM_SLOT, null);
			mi.setInventorySlotContents(MinersInventory.MINERS_DOLCH_LASCHE_ITEM_SLOT, null);
			if (!ep.getEntityData().hasKey(EntityPlayer.PERSISTED_NBT_TAG))
				ep.getEntityData().setTag(EntityPlayer.PERSISTED_NBT_TAG, new NBTTagCompound());
			NBTTagCompound nbttc = new NBTTagCompound();
			if (!GameHelper.getUtils().hasEnchantment(ml, GameHelperCoreModule.ENCH_SOULBOUND)) {
				GameHelper.getUtils().dropItem(ml, new LocationA(lde.entity.getPosition(), lde.entity.worldObj));
			} else {
				nbttc.setTag("slot_minerslight", ml.writeToNBT(new NBTTagCompound()));
			}
			if (!GameHelper.getUtils().hasEnchantment(mb, GameHelperCoreModule.ENCH_SOULBOUND)) {
				GameHelper.getUtils().dropItem(mb, new LocationA(lde.entity.getPosition(), lde.entity.worldObj));
			} else {
				nbttc.setTag("slot_minersbackpack", mb.writeToNBT(new NBTTagCompound()));
			}
			if (!GameHelper.getUtils().hasEnchantment(mbe, GameHelperCoreModule.ENCH_SOULBOUND)) {
				GameHelper.getUtils().dropItem(mbe, new LocationA(lde.entity.getPosition(), lde.entity.worldObj));
			} else {
				nbttc.setTag("slot_minersbelt", mbe.writeToNBT(new NBTTagCompound()));
			}
			if (!GameHelper.getUtils().hasEnchantment(dag, GameHelperCoreModule.ENCH_SOULBOUND)) {
				GameHelper.getUtils().dropItem(dag, new LocationA(lde.entity.getPosition(), lde.entity.worldObj));
			} else {
				nbttc.setTag("slot_minersdagger", dag.writeToNBT(new NBTTagCompound()));
			}
			// nbttc.setTag("slot_minersbackpack", mb.writeToNBT(new
			// NBTTagCompound()));
			// nbttc.setTag("slot_minersbelt", mbe.writeToNBT(new
			// NBTTagCompound()));
			// nbttc.setTag("slot_minersdagger", dag.writeToNBT(new
			// NBTTagCompound()));
			((NBTTagCompound) ep.getEntityData().getTag(EntityPlayer.PERSISTED_NBT_TAG))
					.removeTag("miners_inv_death_soulbound");
			if (nbttc.hasNoTags())
				return;
			((NBTTagCompound) ep.getEntityData().getTag(EntityPlayer.PERSISTED_NBT_TAG))
					.setTag("miners_inv_death_soulbound", nbttc);
			// }
		}
	}

	@SubscribeEvent
	public void playerRespawnMinersInvSoulbound(PlayerRespawnEvent pre) {
		if (pre.player.worldObj.isRemote)
			return;
		NBTTagCompound nbttc = ((NBTTagCompound) ((NBTTagCompound) pre.player.getEntityData()
				.getTag(EntityPlayer.PERSISTED_NBT_TAG)).getTag("miners_inv_death_soulbound"));
		if (nbttc == null)
			return;
		if (nbttc.hasNoTags())
			return;
		MinersInventory mi = MinersInventoryHelper.getMinersInventory(pre.player);
		NBTTagCompound _NBTminerslight = (NBTTagCompound) nbttc.getTag("slot_minerslight");
		NBTTagCompound _NBTbp = (NBTTagCompound) nbttc.getTag("slot_minersbackpack");
		NBTTagCompound _NBTbelt = (NBTTagCompound) nbttc.getTag("slot_minersbelt");
		NBTTagCompound _NBTdagger = (NBTTagCompound) nbttc.getTag("slot_minersdagger");
		if (_NBTminerslight != null) {
			ItemStack minerslight = ItemStack.loadItemStackFromNBT(_NBTminerslight);
			mi.setInventorySlotContents(MinersInventory.MINERS_HELMET_LIGHT, minerslight);
		}
		if (_NBTbp != null) {
			ItemStack minersbp = ItemStack.loadItemStackFromNBT(_NBTbp);
			mi.setInventorySlotContents(MinersInventory.MINERS_BACKPACK, minersbp);
		}
		if (_NBTbelt != null) {
			ItemStack minersbelt = ItemStack.loadItemStackFromNBT(_NBTbelt);
			mi.setInventorySlotContents(MinersInventory.MINERS_BELT_ITEM_SLOT, minersbelt);
		}
		if (_NBTdagger != null) {
			ItemStack dagger = ItemStack.loadItemStackFromNBT(_NBTdagger);
			mi.setInventorySlotContents(MinersInventory.MINERS_DOLCH_LASCHE_ITEM_SLOT, dagger);
		}
	}
}
