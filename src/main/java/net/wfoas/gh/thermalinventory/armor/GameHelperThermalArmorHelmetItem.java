package net.wfoas.gh.thermalinventory.armor;

import net.minecraft.item.Item;
import net.wfoas.gh.items.GameHelperModItem;

public class GameHelperThermalArmorHelmetItem extends GameHelperModItem implements ThermalArmorItem {

	public GameHelperThermalArmorHelmetItem() {
		super("thermal_helmet");
	}

	@Override
	public ThermalType getThermalType() {
		return ThermalType.ALLROUND;
	}

}