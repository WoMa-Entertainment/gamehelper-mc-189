package net.wfoas.gh.thermalinventory.armor;

import net.minecraft.item.Item;
import net.wfoas.gh.items.GameHelperModItem;

public class GameHelperThermalArmorLeggingsItem extends GameHelperModItem implements ThermalArmorItem {

	public GameHelperThermalArmorLeggingsItem() {
		super("thermal_leggings");
		this.setMaxStackSize(1);
	}

	@Override
	public ThermalType getThermalType() {
		return ThermalType.ALLROUND;
	}

}