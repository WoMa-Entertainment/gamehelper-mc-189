package net.wfoas.gh.items;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.potionbow.EntityShotPotion;
import net.wfoas.gh.potionbow.PotionLooseEvent;

public class GameHelperModPotionBow extends GameHelperModItem {
	public static final String[] bowPullIconNameArray = new String[] { "pulling_0", "pulling_1", "pulling_2" };

	public GameHelperModPotionBow(String name) {
		super(name);
		this.maxStackSize = 1;
		this.setMaxDamage(384);
		this.setCreativeTab(CreativeTabs.tabCombat);
	}

	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityPlayer playerIn, int timeLeft) {
		// boolean flag = playerIn.capabilities.isCreativeMode
		// ||
		// EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId,
		// stack) > 0;

		if (playerIn.inventory.hasItem(Items.potionitem) && ItemPotion.isSplash(playerIn.inventory
				.getStackInSlot(
						GameHelper.getUtils().getPlayerInventorySlotContainItem(Items.potionitem, playerIn.inventory))
				.getMetadata())) {
			// int slot =
			int i = this.getMaxItemUseDuration(stack) - timeLeft;
			PotionLooseEvent event = new PotionLooseEvent(playerIn, stack, i);
			if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event))
				return;
			i = event.charge;
			float f = (float) i / 20.0F;
			f = (f * f + f * 2.0F) / 3.0F;

			if ((double) f < 0.1D) {
				return;
			}

			if (f > 1.0F) {
				f = 1.0F;
			}

			// EntityArrow entityarrow = new EntityArrow(worldIn, playerIn, f *
			// 2.0F
			// TODO add textures
			// EntityShotPotion entityarrow = new EntityShotPotion(worldIn,
			// playerIn,
			// playerIn.inventory.getStackInSlot(GameHelper.getUtils()
			// .getPlayerInventorySlotContainItem(Items.potionitem,
			// playerIn.inventory)).getMetadata(),
			// // f * 2f * 10f);
			// 0.5f);
			EntityPotion entityarrow = new EntityPotion(worldIn, playerIn, playerIn.inventory.getStackInSlot(
					GameHelper.getUtils().getPlayerInventorySlotContainItem(Items.potionitem, playerIn.inventory))
					.getMetadata());
			entityarrow.setThrowableHeading(entityarrow.motionX, entityarrow.motionY, entityarrow.motionZ,
					f * 2f * 0.5f, 1.0F);
			// System.out.println("used on site: " +
			// (entityarrow.worldObj.isRemote ? "client" : "server"));

			// if (f == 1.0F) {
			// entityarrow.setIsCritical(true);
			// }

			// int j =
			// EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId,
			// stack);
			//
			//// if (j > 0) {
			//// entityarrow.setDamage(entityarrow.getDamage() + (double) j *
			// 0.5D + 0.5D);
			//// }
			//
			// int k =
			// EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId,
			// stack);
			//
			// if (k > 0) {
			// entityarrow.setKnockbackStrength(k);
			// }

			if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, stack) > 0) {
				entityarrow.setFire(100);
			}

			stack.damageItem(1, playerIn);
			worldIn.playSoundAtEntity(playerIn, "random.bow", 1.0F,
					1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

			// if (!flag) {
			if (!playerIn.capabilities.isCreativeMode)
				playerIn.inventory.consumeInventoryItem(Items.potionitem);
			// }C

			playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);

			if (!worldIn.isRemote) {
				worldIn.spawnEntityInWorld(entityarrow);
			}
		}
	}

	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		return stack;
	}

	public int getMaxItemUseDuration(ItemStack stack) {
		return 72000;
	}

	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
		net.wfoas.gh.potionbow.PotionNockEvent event = new net.wfoas.gh.potionbow.PotionNockEvent(playerIn,
				itemStackIn);
		if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event))
			return event.result;

		if (playerIn.inventory.hasItem(Items.potionitem)) {
			playerIn.setItemInUse(itemStackIn, this.getMaxItemUseDuration(itemStackIn));
		}

		return itemStackIn;
	}

	public int getItemEnchantability() {
		return 2;
	}

	@Override
	public ModelResourceLocation getModel(ItemStack stack, EntityPlayer player, int useRemaining) {
		ModelResourceLocation modelresourcelocation = new ModelResourceLocation(GameHelper.MODID + ":" + getName(),
				"inventory");
		if (stack.getItem() == this && player.getItemInUse() != null) {
			if (useRemaining >= 18) {
				modelresourcelocation = new ModelResourceLocation(GameHelper.MODID + ":" + getName() + "_pull_0",
						"inventory");
			} else if (useRemaining > 13) {
				modelresourcelocation = new ModelResourceLocation(GameHelper.MODID + ":" + getName() + "_pull_1",
						"inventory");
			} else if (useRemaining > 0) {
				modelresourcelocation = new ModelResourceLocation(GameHelper.MODID + ":" + getName() + "_pull_2",
						"inventory");
			}
		}
		return modelresourcelocation;
	}
}