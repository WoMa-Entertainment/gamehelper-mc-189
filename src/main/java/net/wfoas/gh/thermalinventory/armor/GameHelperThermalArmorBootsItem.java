package net.wfoas.gh.thermalinventory.armor;

import net.minecraft.item.Item;
import net.wfoas.gh.items.GameHelperModItem;

public class GameHelperThermalArmorBootsItem extends GameHelperModItem implements ThermalArmorItem {

	public GameHelperThermalArmorBootsItem() {
		super("thermal_boots");
		this.setMaxStackSize(1);
	}

	@Override
	public ThermalType getThermalType() {
		return ThermalType.ALLROUND;
	}

}