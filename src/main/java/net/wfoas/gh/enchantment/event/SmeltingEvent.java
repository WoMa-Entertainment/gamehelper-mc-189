package net.wfoas.gh.enchantment.event;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.dropsapi.pdr.EnchantmentFinder;

public class SmeltingEvent {
	@SubscribeEvent
	public void onSmeltEvent(BlockEvent.HarvestDropsEvent event) {
		if (!event.world.isRemote) {
			if (event.harvester == null)
				return;
			ItemStack heldItem = event.harvester.inventory.getCurrentItem();

			if (!GameHelper.getUtils().hasEnchantment(heldItem, EnchantmentFinder.SMELTING.toFMLEnchantment()))
				return;

			if (FurnaceRecipes.instance().getSmeltingResult(new ItemStack(event.state.getBlock())) == null)
				return;

			ItemStack smelted = FurnaceRecipes.instance().getSmeltingResult(new ItemStack(event.state.getBlock()))
					.copy();
			if (GameHelper.getUtils().hasEnchantment(heldItem, EnchantmentFinder.FORTUNE.toFMLEnchantment())) {
				smelted.stackSize = 1 + GameHelper.MOD_RANDOM.nextInt(GameHelper.getUtils()
						.getEnchantmentLevel(heldItem, EnchantmentFinder.FORTUNE.toFMLEnchantment()));
			}

			if (smelted == null)
				return;
			event.drops.clear();
			event.drops.add(smelted);
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
