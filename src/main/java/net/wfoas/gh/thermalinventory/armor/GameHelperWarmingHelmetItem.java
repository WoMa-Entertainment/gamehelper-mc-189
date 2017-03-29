package net.wfoas.gh.thermalinventory.armor;

import net.minecraft.item.Item;
import net.wfoas.gh.items.GameHelperModItem;

public class GameHelperWarmingHelmetItem extends GameHelperModItem implements ThermalArmorItem {

	public GameHelperWarmingHelmetItem() {
		super("thermal_helmet_warming");
		this.setMaxStackSize(1);
	}

	@Override
	public ThermalType getThermalType() {
		return ThermalType.WARMING;
	}

}