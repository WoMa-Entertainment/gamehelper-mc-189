package net.wfoas.gh.minersinventory;

import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class MinersInventorySlot extends Slot {
	protected final MinersInventory minersInventory;

	public static final ResourceLocation NO_ENTRY = new ResourceLocation("gamehelper",
			"textures/items/not_ok_item.png");

	public MinersInventorySlot(MinersInventory inv, int index, int xPos, int yPos) {
		super(inv, index, xPos, yPos);
		this.minersInventory = inv;
	}

	@Override
	public abstract boolean isItemValid(ItemStack itemstack);

	@SideOnly(value = Side.CLIENT)
	@Override
	public abstract boolean canBeHovered();

}