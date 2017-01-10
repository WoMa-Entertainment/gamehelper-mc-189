package net.wfoas.gh.villager.entity.trades;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.wfoas.gh.villager.entity.GHVillagerProfession;

public class GHVillagerTrade extends MerchantRecipe {

	public GHVillagerTrade(NBTTagCompound tagCompound) {
		super(tagCompound);
	}

	public GHVillagerTrade(ItemStack buy1, ItemStack buy2, ItemStack sell) {
		super(buy1, buy2, sell);
	}

	public GHVillagerTrade(ItemStack buy1, ItemStack sell) {
		super(buy1, sell);
	}

	public GHVillagerTrade(ItemStack buy1, Item sellItem) {
		super(buy1, sellItem);
	}

	public GHVillagerTrade(ItemStack buy1, ItemStack buy2, ItemStack sell, int toolUsesIn, int maxTradeUsesIn) {
		super(buy1, buy2, sell, toolUsesIn, maxTradeUsesIn);
	}

	public GHVillagerTrade(MerchantRecipe mr) {
		this(mr.getItemToBuy().copy(), (mr.getSecondItemToBuy() != null ? mr.getSecondItemToBuy().copy() : null),
				mr.getItemToSell().copy(), mr.getToolUses(), mr.getMaxTradeUses());
	}

	public static MerchantRecipeList createMerchantTradeList(GHVillagerProfession profession,
			GHVillagerTrade[] recipes) {
		MerchantRecipeList list = new MerchantRecipeList();
		for (GHVillagerTrade ght : recipes) {
			list.add(ght);
		}
		profession.setDefaultMerchantRecipeList(list);
		return list;
	}
}