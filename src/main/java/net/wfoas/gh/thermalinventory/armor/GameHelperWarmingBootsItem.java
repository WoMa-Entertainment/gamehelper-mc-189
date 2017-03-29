package net.wfoas.gh.thermalinventory.armor;

import net.minecraft.item.Item;
import net.wfoas.gh.items.GameHelperModItem;

public class GameHelperWarmingBootsItem extends GameHelperModItem implements ThermalArmorItem {

	public GameHelperWarmingBootsItem() {
		super("thermal_boots_warming");
		this.setMaxStackSize(1);
	}

	@Override
	public ThermalType getThermalType() {
		return ThermalType.WARMING;
	}

}