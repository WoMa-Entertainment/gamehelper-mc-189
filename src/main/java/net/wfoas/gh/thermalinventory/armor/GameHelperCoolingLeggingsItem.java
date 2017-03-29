package net.wfoas.gh.thermalinventory.armor;

import net.minecraft.item.Item;
import net.wfoas.gh.items.GameHelperModItem;

public class GameHelperCoolingLeggingsItem extends GameHelperModItem implements ThermalArmorItem {

	public GameHelperCoolingLeggingsItem() {
		super("thermal_leggings_cooling");
		this.setMaxStackSize(1);
	}

	@Override
	public ThermalType getThermalType() {
		return ThermalType.COOLING;
	}

}