package net.wfoas.gh.thermalinventory.armor;

import net.minecraft.item.Item;
import net.wfoas.gh.items.GameHelperModItem;

public class GameHelperWarmingLeggingsItem extends GameHelperModItem implements ThermalArmorItem {

	public GameHelperWarmingLeggingsItem() {
		super("thermal_leggings_warming");
		this.setMaxStackSize(1);
	}

	@Override
	public ThermalType getThermalType() {
		return ThermalType.WARMING;
	}

}