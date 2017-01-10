package assets.gamehelper.textures.other.old_items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants.NBT;

public class BackpackInv extends InventoryBasic{
	
	ItemStack is;
	EntityPlayer ep;

	public BackpackInv(String title, boolean customName, int slotCount, ItemStack is) {
		super(title, customName, slotCount);
		this.is = is;
	}
	
	@Override
	public void openInventory(EntityPlayer ep)
	{
		if((this.is.hasTagCompound()))
			this.readFromNBT(this.is.getTagCompound());
//		ep.displayGUIChest(this);
		this.ep = ep;
	}
	
	@Override
	public void closeInventory(EntityPlayer ep)
	{
		this.writeToNBT(this.is.getTagCompound());
	}
	
	public void readFromNBT(NBTTagCompound compound)
	{
		NBTTagList items = compound.getTagList("ItemBag", NBT.TAG_COMPOUND);
		
		for(int i = 0; i < items.tagCount(); i++)
		{
			NBTTagCompound item = items.getCompoundTagAt(i);
			int slot = item.getInteger("Slot");
			
			// Double-check has loaded correctly.
			if (slot >= 0 && slot < getSizeInventory())
			{
				this.setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
			}
		}
	}
	
	public void writeToNBT(NBTTagCompound compound)
	{
		NBTTagList items = new NBTTagList();
		
		for(int i = 0; i < getSizeInventory(); i++)
		{
			// Don't write empty slots
			if (getStackInSlot(i) != null)
			{
				NBTTagCompound item = new NBTTagCompound();
				item.setInteger("Slot", i);
				
				getStackInSlot(i).writeToNBT(item);
				
				items.appendTag(item);
			}
		}
		compound.setTag("ItemInventory", items);
	}

}
