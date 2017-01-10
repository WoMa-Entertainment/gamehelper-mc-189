package net.wfoas.gh.events.otherworldevent;

import java.awt.ContainerOrderFocusTraversalPolicy;
import java.awt.Desktop.Action;

import net.minecraft.block.BlockButton;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.world.utils.WorldPermission;
import net.wfoas.gh.world.utils.WorldPermissionsManager;

public class OtherWorldEvents {// TODO braucht überarbeitung

	@SubscribeEvent
	public void blockDestroyed(BreakEvent event) {
		if (!event.world.isRemote) {
			if (GameHelper.getUtils().isHacker((EntityPlayerMP) event.getPlayer())) {
				return;
			}
			EntityPlayer player = event.getPlayer();
			World w = event.world;
			if (WorldPermissionsManager.getPermission(WorldPermission.DESTROY, player, w)) {
				;
			} else {
				player.addChatMessage(new ChatComponentTranslation("gamehelper.error.world.nopermission.destroy",
						w.getWorldInfo().getWorldName()));
				event.setCanceled(true);
			}
		}
		return;
	}

	@SubscribeEvent
	public void blockPlaced(PlaceEvent event) {
		if (!event.world.isRemote) {
			if (GameHelper.getUtils().isHacker((EntityPlayerMP) event.player)) {
				return;
			}
			EntityPlayer player = event.player;
			World w = event.world;
			if (WorldPermissionsManager.getPermission(WorldPermission.BUILD, player, w)) {
				return;
			} else {
				player.addChatMessage(new ChatComponentTranslation("gamehelper.error.world.nopermission.build",
						w.getWorldInfo().getWorldName()));
				event.setCanceled(true);
			}
		}
		return;
	}

	@SubscribeEvent
	public void interactWithBlock(PlayerInteractEvent event) {
		if (!event.world.isRemote) {
			if (GameHelper.getUtils().isHacker((EntityPlayerMP) event.entityPlayer)) {
				return;
			}
			EntityPlayer player = event.entityPlayer;
			World w = event.world;
			if (WorldPermissionsManager.getPermission(WorldPermission.INTERACT, player, w)) {
				return;
			} else {
				event.useBlock = Event.Result.DENY;
				if (event.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK
						|| event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR) {
					// event.entityPlayer
					// .addChatMessage(new
					// ChatComponentTranslation("gamehelper.error.world.nopermission.interact"
					// + w.getWorldInfo().getWorldName() + "LEFT_TIGHT_AIR"));
					event.useBlock = Event.Result.DENY;
					return;
				}
				if (event.world.getBlockState(event.pos).getBlock().onBlockActivated(event.world, event.pos,
						event.world.getBlockState(event.pos), event.entityPlayer, EnumFacing.UP, event.pos.getX(),
						event.pos.getY(), event.pos.getZ())) {

				}
				if (event.entityPlayer.inventory.getCurrentItem() == null) {
				} else {
					if (event.entityPlayer.inventory.getCurrentItem().getItem() instanceof ItemBlock) {
						if (WorldPermissionsManager.getPermission(WorldPermission.BUILD, player, w)) {
							;
						} else {
							player.addChatMessage(new ChatComponentTranslation(
									"gamehelper.error.world.nopermission.build", w.getWorldInfo().getWorldName()));
							event.useItem = Event.Result.ALLOW;
							return;
						}
					}
				}
				event.entityPlayer.addChatMessage(new ChatComponentTranslation(
						"gamehelper.error.world.nopermission.interact", w.getWorldInfo().getWorldName()));
			}
		}
		return;
	}

	@SubscribeEvent
	public void attackEntity(LivingAttackEvent event) {
		if (!event.entity.worldObj.isRemote) {
			if (event.source instanceof EntityDamageSource) {
				Entity e = ((EntityDamageSource) (event.source)).getSourceOfDamage();
				if (e instanceof EntityPlayer) {
					EntityPlayer ep = (EntityPlayer) e;
					if (GameHelper.getUtils().isHacker((EntityPlayerMP) ep)) {
						return;
					}
					if (!WorldPermissionsManager.getPermission(WorldPermission.KILL, ep, event.entity.worldObj)) {
						event.setCanceled(true);
						ep.addChatMessage(new ChatComponentTranslation("gamehelper.error.world.nopermission.kill",
								event.entity.worldObj.getWorldInfo().getWorldName()));
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void damageEntity(LivingHurtEvent event) {
		// if (event.source instanceof EntityDamageSource) {
		// Entity e = ((EntityDamageSource) (event.source)).getSourceOfDamage();
		// if (e instanceof EntityPlayer) {
		// EntityPlayer ep = (EntityPlayer) e;
		// if (!WorldPermissionsManager.getPermission(WorldPermission.KILL, ep,
		// event.entity.worldObj)) {
		// // event.ammount = 0f;
		// // event.setCanceled(true);
		// // ep.addChatMessage(new
		// //
		// ChatComponentTranslation("gamehelper.error.world.nopermission.kill",
		// // event.entity.worldObj.getWorldInfo().getWorldName()));
		// }
		// }
		// }
	}

	@SubscribeEvent
	public void collectItems(EntityItemPickupEvent event) {
		if (!event.entityPlayer.worldObj.isRemote) {
			if (GameHelper.getUtils().isHacker((EntityPlayerMP) event.entityPlayer)) {
				return;
			}
			if (!WorldPermissionsManager.getPermission(WorldPermission.COLLECT_ITEMS_EXP, event.entityPlayer,
					event.entityPlayer.worldObj)) {
				event.setCanceled(true);
				event.entityPlayer.addChatMessage(
						new ChatComponentTranslation("gamehelper.error.world.nopermission.collect_items",
								event.entityPlayer.worldObj.getWorldInfo().getWorldName()));
			}
		}
	}

	@SubscribeEvent
	public void collectXp(PlayerPickupXpEvent event) {
		if (!event.entityPlayer.worldObj.isRemote) {
			if (GameHelper.getUtils().isHacker((EntityPlayerMP) event.entityPlayer)) {
				return;
			}
			if (!WorldPermissionsManager.getPermission(WorldPermission.COLLECT_ITEMS_EXP, event.entityPlayer,
					event.entityPlayer.worldObj)) {
				event.setCanceled(true);
				event.entityPlayer
						.addChatMessage(new ChatComponentTranslation("gamehelper.error.world.nopermission.collect_xp",
								event.entityPlayer.worldObj.getWorldInfo().getWorldName()));
			}
		}
	}
}