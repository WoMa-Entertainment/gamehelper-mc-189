package net.wfoas.gh.thermalinventory.armor;

import net.minecraft.item.Item;
import net.wfoas.gh.items.GameHelperModItem;

public class GameHelperWarmingChestplateItem extends GameHelperModItem implements ThermalArmorItem {

	public GameHelperWarmingChestplateItem() {
		super("thermal_chestplate_warming");
		this.setMaxStackSize(1);
	}

	@Override
	public ThermalType getThermalType() {
		return ThermalType.WARMING;
	}

}