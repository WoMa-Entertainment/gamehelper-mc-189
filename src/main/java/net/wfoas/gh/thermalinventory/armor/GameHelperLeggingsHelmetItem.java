package net.wfoas.gh.thermalinventory.armor;

import net.minecraft.item.Item;
import net.wfoas.gh.items.GameHelperModItem;

public class GameHelperLeggingsHelmetItem extends GameHelperModItem implements ThermalArmorItem {

	public GameHelperLeggingsHelmetItem() {
		super("thermal_leggings_cooling");
		this.setMaxStackSize(1);
	}

	@Override
	public ThermalType getThermalType() {
		return ThermalType.COOLING;
	}

}