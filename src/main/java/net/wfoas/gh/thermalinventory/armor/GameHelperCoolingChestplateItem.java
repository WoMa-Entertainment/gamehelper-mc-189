package net.wfoas.gh.thermalinventory.armor;

import net.minecraft.item.Item;
import net.wfoas.gh.items.GameHelperModItem;

public class GameHelperCoolingChestplateItem extends GameHelperModItem implements ThermalArmorItem {

	public GameHelperCoolingChestplateItem() {
		super("thermal_chestplate_cooling");
		this.setMaxStackSize(1);
	}

	@Override
	public ThermalType getThermalType() {
		return ThermalType.COOLING;
	}

}