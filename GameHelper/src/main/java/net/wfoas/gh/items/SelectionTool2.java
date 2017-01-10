package net.wfoas.gh.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.wfoas.gh.dropsapi.pdr.ChatColor;
import net.wfoas.gh.selectiontool.SelectionProperties;

public class SelectionTool2 extends GameHelperModItem {

	public SelectionTool2() {
		super("gh_selection_tool_2");
	}

	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side,
			float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote)
			return false;
		EntityPlayerMP playerMp = (EntityPlayerMP) playerIn;
		SelectionProperties.setItem("pos_2_x", pos.getX(), playerMp);
		SelectionProperties.setItem("pos_2_y", pos.getY(), playerMp);
		SelectionProperties.setItem("pos_2_z", pos.getZ(), playerMp);
		playerMp.addChatMessage(new ChatComponentText(ChatColor.LIGHT_PURPLE + "Set position 2 at x=" + pos.getX()
				+ " y=" + pos.getY() + " z=" + pos.getZ()));
		if (SelectionProperties.getItem("pos_1_y", playerMp) != -1
				&& SelectionProperties.getItem("pos_2_y", playerMp) != -1) {
			int sx = Math.min(SelectionProperties.getItem("pos_1_x", playerMp),
					SelectionProperties.getItem("pos_2_x", playerMp));
			int lx = Math.max(SelectionProperties.getItem("pos_1_x", playerMp),
					SelectionProperties.getItem("pos_2_x", playerMp));

			int sy = Math.min(SelectionProperties.getItem("pos_1_y", playerMp),
					SelectionProperties.getItem("pos_2_y", playerMp));
			int ly = Math.max(SelectionProperties.getItem("pos_1_y", playerMp),
					SelectionProperties.getItem("pos_2_y", playerMp));

			int sz = Math.min(SelectionProperties.getItem("pos_1_z", playerMp),
					SelectionProperties.getItem("pos_2_z", playerMp));
			int lz = Math.max(SelectionProperties.getItem("pos_1_z", playerMp),
					SelectionProperties.getItem("pos_2_z", playerMp));

			int selected_blocks = (1 + (lx - sx)) * (1 + (ly - sy)) * (1 + (lz - sz));
			// int selected_blocks = ((lx - sx) == 0 ? 1 : (lx - sx)) * ((ly -
			// sy) == 0 ? 1 : (ly - sy))
			// * ((lz - sz) == 0 ? 1 : (lz - sz));
			playerMp.addChatMessage(
					new ChatComponentText(ChatColor.LIGHT_PURPLE + "Selected Blocks: " + selected_blocks));
		}
		return false;
	}
}
