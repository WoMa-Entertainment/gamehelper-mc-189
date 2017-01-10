package net.wfoas.gh.villager.entity;

import net.minecraft.util.ResourceLocation;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.wfoas.gh.villager.entity.trades.GHVillagerTrade;

public final class GHVillagerProfession {

	String i18nableName, abstractName;
	ResourceLocation texture;
	MerchantRecipeList startupmerchantRecipeList;
	MerchantRecipeList[] unlockableRecipes;

	public GHVillagerProfession(String abstractname, String i18nprototype, ResourceLocation texture) {
		this.i18nableName = i18nprototype;
		this.abstractName = abstractname;
		this.texture = texture;
	}

	public void setDefaultMerchantRecipeList(MerchantRecipeList list) {
		startupmerchantRecipeList = list;
	}

	public void setUnlockableRecipes(MerchantRecipeList[] recipes) {
		this.unlockableRecipes = recipes == null ? new MerchantRecipeList[0] : recipes;
	}

	public final MerchantRecipeList getMerchantRecipeList() {
		return startupmerchantRecipeList;
	}

	public final MerchantRecipeList getUnlockable(int param) {
		if (param > 0 && param < unlockableRecipes.length)
			return getCopy(unlockableRecipes[param]);
		return null;
	}

	public final MerchantRecipeList getMerchantRecipeListExplicitCopy() {
		MerchantRecipeList list = new MerchantRecipeList();
		for (MerchantRecipe mr : startupmerchantRecipeList) {
			if (mr != null)
				list.add(new GHVillagerTrade(mr));
		}
		return list;
	}

	private final MerchantRecipeList getCopy(MerchantRecipeList list2) {
		MerchantRecipeList list = new MerchantRecipeList();
		for (MerchantRecipe mr : list2) {
			list.add(new GHVillagerTrade(mr));
		}
		return list;
	}

	public String getName() {
		return abstractName;
	}

	public String getUnlocalizedI18nKey() {
		return i18nableName;
	}

	public ResourceLocation getTexture() {
		return texture;
	}
}
