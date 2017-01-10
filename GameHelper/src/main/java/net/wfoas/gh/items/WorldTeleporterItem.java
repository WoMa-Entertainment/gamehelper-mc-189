package net.wfoas.gh.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.wfoas.gh.network.gui.RemoteGuiOpener;

public class WorldTeleporterItem extends GameHelperModItem {

	public WorldTeleporterItem() {
		super("world_teleporter");
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side,
			float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			// GameHelper.getUtils().openEnchantmentTable((EntityPlayerMP)
			// playerIn,
			// GameHelper.getUtils().clampInt(0, 15, (int) (double)GuiRepair
			// (Math.random() * 15)));
			// GameHelper.getUtils().openAnvil((EntityPlayerMP) playerIn);
			// NetworkHandler.sendToSpecificPlayer(new
			// PacketPlayOpenSetPermDialog((EntityPlayerMP) playerIn),
			// (EntityPlayerMP) playerIn);
			// RemoteGuiOpener.openSetPermDialog((EntityPlayerMP) playerIn);
			// RemoteGuiOpener.open
			// NetworkHandler.sendToSpecificPlayer(new
			// PacketPlayOpenSetPermDialog((EntityPlayerMP) playerIn),
			// (EntityPlayerMP) playerIn);
			// RemoteGuiOpener.openOwnWorldDialog((EntityPlayerMP) playerIn);
			 RemoteGuiOpener.openSetPermDialog((EntityPlayerMP) playerIn);
			// RemoteGuiOpener.openSetPermDialog((EntityPlayerMP) playerIn);
			// GuiEnchantment
			// GameHelper.getUtils().openCraftingTable((EntityPlayerMP)
			// playerIn);net.minecraft.
		}
		return true;
	}

}
