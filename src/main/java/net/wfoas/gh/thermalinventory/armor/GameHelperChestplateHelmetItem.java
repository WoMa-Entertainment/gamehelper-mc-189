package net.wfoas.gh.thermalinventory.armor;

import net.minecraft.item.Item;
import net.wfoas.gh.items.GameHelperModItem;

public class GameHelperChestplateHelmetItem extends GameHelperModItem implements ThermalArmorItem {

	public GameHelperChestplateHelmetItem() {
		super("thermal_helmet_cooling");
		this.setMaxStackSize(1);
	}

	@Override
	public ThermalType getThermalType() {
		return ThermalType.COOLING;
	}

}