package net.wfoas.gh.uncraftingtable;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.blocks.GameHelperModBlock;
import net.wfoas.gh.enchaltar.EnchantmentAltarGui;
import net.wfoas.gh.enchaltar.TileEntityEnchantmentAltar;
import net.wfoas.gh.gui.GuiHandler;

public class UncraftingTable extends GameHelperModBlock {

	public UncraftingTable() {
		super(Material.wood, "uncrafting_table");
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote) {
			return true;
		} else {
			playerIn.openGui(GameHelper.instance, GuiHandler.UNCRAFTING_TABLE_INVENTORY, worldIn, (int) hitX,
					(int) hitY, (int) hitZ);
			return true;
		}
	}

}
