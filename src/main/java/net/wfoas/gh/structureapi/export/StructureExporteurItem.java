package net.wfoas.gh.structureapi.export;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.wfoas.gh.dropsapi.pdr.Vec3d;
import net.wfoas.gh.items.GameHelperModItem;

public class StructureExporteurItem extends GameHelperModItem {

	@SideOnly(value = Side.SERVER)
	public Map<UUID, DoubleVector3f> map = new HashMap<UUID, DoubleVector3f>();

	public StructureExporteurItem() {
		super("gh_structure_exporteur");
	}
	//commandexport
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side,
			float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			if (!map.containsKey(playerIn.getUniqueID())) {
				map.put(playerIn.getUniqueID(), new DoubleVector3f(-1, -1, -1, -1, -1, -1));
			}
			map.put(playerIn.getUniqueID(), map.get(playerIn.getUniqueID()).setRightSide(new Vec3d(pos.getX(), pos.getY(), pos.getZ())));
		}
		return false;
	}
	
	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player)
    {
		World worldIn = player.worldObj;
		EntityPlayer playerIn = player;
		if (!worldIn.isRemote) {
			if (!map.containsKey(playerIn.getUniqueID())) {
				map.put(playerIn.getUniqueID(), new DoubleVector3f(-1, -1, -1, -1, -1, -1));
			}
			map.put(playerIn.getUniqueID(), map.get(playerIn.getUniqueID()).setLeftSide(new Vec3d(pos.getX(), pos.getY(), pos.getZ())));
		}		
        return true;
    }

}
