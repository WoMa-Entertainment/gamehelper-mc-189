package net.wfoas.gh.omapi;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class MinersInventoryAPI {
	List<Item> minersbeltladderinventoryslot_list;

	protected MinersInventoryAPI() {
		minersbeltladderinventoryslot_list = new ArrayList<Item>();
	}

	public void defaultSetup() {
		minersbeltladderinventoryslot_list.clear();
		minersbeltladderinventoryslot_list.add(Item.getItemFromBlock(Blocks.ladder));
		minersbeltladderinventoryslot_list.add(Item.getItemFromBlock(Blocks.rail));
		minersbeltladderinventoryslot_list.add(Item.getItemFromBlock(Blocks.activator_rail));
		minersbeltladderinventoryslot_list.add(Item.getItemFromBlock(Blocks.golden_rail));
		minersbeltladderinventoryslot_list.add(Item.getItemFromBlock(Blocks.detector_rail));
	}

	public boolean isMinersBeltLadderInvSlotItemValid(Item item) {
		return minersbeltladderinventoryslot_list.contains(item);
	}

	public void addItemToMinersBeltLadderInvSlotValidItemList(Item item) {
		if (item != null)
			minersbeltladderinventoryslot_list.add(item);
	}
}
