package net.wfoas.gh.network.securedlogin;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.minecart.MinecartInteractEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class CanceledEvents {
	protected void cancelIfNotLoggedIn(EntityPlayer playerMP, Event event) {
		if (playerMP.worldObj.isRemote)
			return;
		if (LoginDataHolder.contains((EntityPlayerMP) playerMP))
			event.setCanceled(true);
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onPlayerChat(ServerChatEvent event) {
		cancelIfNotLoggedIn(event.player, event);
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onPlayerCommand(CommandEvent event) {
		if (!(event.sender instanceof EntityPlayer)) {
			return;
		}
		cancelIfNotLoggedIn((EntityPlayer) event.sender, event);
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onPlayerInteract(PlayerInteractEvent event) {
		cancelIfNotLoggedIn(event.entityPlayer, event);
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onPlayerInteract(EntityInteractEvent event) {
		cancelIfNotLoggedIn(event.entityPlayer, event);
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onPlayerInteract(MinecartInteractEvent event) {
		cancelIfNotLoggedIn(event.player, event);
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onPlayerTossItem(ItemTossEvent event) {
		if (event.player.worldObj.isRemote)
			return;
		if (LoginDataHolder.contains((EntityPlayerMP) event.player)) {
			ItemStack stack = event.entityItem.getEntityItem();
			event.player.inventory.addItemStackToInventory(stack);
			event.setCanceled(true);
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onPlayerPickupItem(EntityItemPickupEvent event) {
		cancelIfNotLoggedIn(event.entityPlayer, event);
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onPlayerHurt(LivingHurtEvent event) {
		if (!(event.entityLiving instanceof EntityPlayer)) {
			return;
		}
		cancelIfNotLoggedIn((EntityPlayer) event.entityLiving, event);
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onPlayerAttack(AttackEntityEvent event) {
		cancelIfNotLoggedIn(event.entityPlayer, event);
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onPlayerMove(PlayerTickEvent e) {
//		cancelIfNotLoggedIn(e.player, e);
	}
}