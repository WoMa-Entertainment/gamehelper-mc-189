package net.wfoas.gh.enchantment.event;

import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.GameHelperCoreModule;
import net.wfoas.gh.GameHelperUtils;
import net.wfoas.gh.dropsapi.pdr.LocationA;

public class HeadlootEvent {
	@SubscribeEvent
	public void onEntityHeadLoot(LivingDeathEvent event) {
		if (!event.entity.worldObj.isRemote) {
			if (event.source.getSourceOfDamage() instanceof EntityPlayer) {
				EntityPlayer ep2 = (EntityPlayer) event.source.getSourceOfDamage();
				if (!event.source.isProjectile()) {
					if (!GameHelper.getUtils().hasEnchantment(ep2.getHeldItem(), GameHelperCoreModule.ENCH_HEADLOOT)) {
						return;
					}
					ItemStack headtodrop = null;
					if (event.entity instanceof EntityPigZombie) {
						headtodrop = Mobhead.ZOMBIE_PIGMAN;
					} else if (event.entity instanceof EntitySkeleton) {
						if (((EntitySkeleton) event.entity).getSkeletonType() == 1) {
							headtodrop = Mobhead.WITHER_SKELETON;
						} else {
							headtodrop = Mobhead.SKELETON;
						}
						// add wither skeleton
					} else if (event.entity instanceof EntityBat) {
					} else if (event.entity instanceof EntityBlaze) {
						headtodrop = Mobhead.BLAZE;
					} else if (event.entity instanceof EntityCaveSpider) {
						headtodrop = Mobhead.CAVE_SPIDER;
					} else if (event.entity instanceof EntityChicken) {
						headtodrop = Mobhead.CHICKEN;
					} else if (event.entity instanceof EntityMooshroom) {
						headtodrop = Mobhead.MUSHROOM_COW;
					} else if (event.entity instanceof EntityCreeper) {
						headtodrop = Mobhead.CREEPER;
					} else if (event.entity instanceof EntityEnderman) {
						headtodrop = Mobhead.ENDERMAN;
					} else if (event.entity instanceof EntityGhast) {
						headtodrop = Mobhead.GHAST;
					} else if (event.entity instanceof EntityGiantZombie) {
						headtodrop = Mobhead.ZOMBIE;
					} else if (event.entity instanceof EntityIronGolem) {
						headtodrop = Mobhead.GOLEM;
					} else if (event.entity instanceof EntityMagmaCube) {
						headtodrop = Mobhead.MAGMA_SLIME;
					} else if (event.entity instanceof EntityCow) {
						headtodrop = Mobhead.COW;
					} else if (event.entity instanceof EntityOcelot) {
						headtodrop = Mobhead.OCELOT;
					} else if (event.entity instanceof EntityPig) {
						headtodrop = Mobhead.PIG;
					} else if (event.entity instanceof EntityZombie) {
						headtodrop = Mobhead.ZOMBIE;
					} else if (event.entity instanceof EntityPlayer) {
						EntityPlayer ep = (EntityPlayer) event.entity;
						headtodrop = Mobhead.getPlayerHead(ep);
					} else if (event.entity instanceof EntitySheep) {
						headtodrop = Mobhead.SHEEP;
					} else if (event.entity instanceof EntitySlime) {
						headtodrop = Mobhead.SLIME;
					} else if (event.entity instanceof EntitySnowman) {
						headtodrop = Mobhead.SNOWMAN;
					} else if (event.entity instanceof EntitySquid) {
						headtodrop = Mobhead.SQUID;
					} else if (event.entity instanceof EntityVillager) {
						headtodrop = Mobhead.VILLAGER;
					}
					if (headtodrop != null)
						GameHelper.getUtils().dropItem(headtodrop, new LocationA(event.entity.worldObj,
								event.entity.posX, event.entity.posY, event.entity.posZ));
				}
			}
			// if (event.source)
			// return;
			// ItemStack heldItem = event.harvester.inventory.getCurrentItem();
			//
			// if (!GameHelper.hasEnchantment(heldItem,
			// EnchantmentFinder.SMELTING.toFMLEnchantment()))
			// return;
			//
			// if (FurnaceRecipes.instance().getSmeltingResult(new
			// ItemStack(event.state.getBlock())) == null)
			// return;
			//
			// ItemStack smelted =
			// FurnaceRecipes.instance().getSmeltingResult(new
			// ItemStack(event.state.getBlock()))
			// .copy();
			//
			// if (smelted == null)
			// return;
			// event.drops.clear();
			// event.drops.add(smelted);
			// System.out.println(event.drops);

			// if(j >= 8 && enchantmentLevel == 1){
			//
			// j = 0;
			// event.harvester.inventory.consumeInventoryItem(Items.coal);
			//
			// }
			//
			// if(j >= 16 && enchantmentLevel == 2){
			//
			// j = 0;
			// event.harvester.inventory.consumeInventoryItem(Items.coal);
			//
			// }

			// System.out.println(event.drops);

		}
	}
}
