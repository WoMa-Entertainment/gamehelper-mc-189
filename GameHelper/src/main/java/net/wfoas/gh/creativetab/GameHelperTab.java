package net.wfoas.gh.creativetab;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.wfoas.gh.GameHelperCoreModule;

public class GameHelperTab extends CreativeTabs {

	public GameHelperTab(int index, String label) {
		super(index, label);
	}

	public GameHelperTab(String label) {
		super(label);
	}

	@Override
	public Item getTabIconItem() {
		return GameHelperCoreModule.BACKPACK;
	}

	@Override
	public void displayAllReleventItems(List list) {
		super.displayAllReleventItems(list);
		addEnchBook(GameHelperCoreModule.ENCH_SOULBOUND, list);
		addEnchBook(GameHelperCoreModule.ENCH_DESTRUCTION, list);
		addEnchBook(GameHelperCoreModule.ENCH_HEADLOOT, list);
		addEnchBook(GameHelperCoreModule.ENCH_SMELTING, list);
		addEnchBook(GameHelperCoreModule.ENCH_XPBOOST, list);
		addEnchBook(GameHelperCoreModule.ENCH_ZOOM, list);
	}

	private void addEnchBook(Enchantment enchantment, List list) {
		list.add(Items.enchanted_book
				.getEnchantedItemStack(new EnchantmentData(enchantment, enchantment.getMaxLevel())));
	}

}
