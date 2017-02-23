package net.wfoas.gh.thermalinventory.armor;

import net.minecraft.item.Item;
import net.wfoas.gh.items.GameHelperModItem;

public class GameHelperThermalArmorChestplateItem extends GameHelperModItem implements ThermalArmorItem {

	public GameHelperThermalArmorChestplateItem() {
		super("thermal_chestplate");
	}

	@Override
	public ThermalType getThermalType() {
		return ThermalType.ALLROUND;
	}

}