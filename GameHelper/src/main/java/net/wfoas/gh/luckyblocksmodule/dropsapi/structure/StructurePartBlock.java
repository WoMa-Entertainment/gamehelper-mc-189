package net.wfoas.gh.luckyblocksmodule.dropsapi.structure;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.wfoas.gh.dropsapi.pdr.LocationA;
import net.wfoas.gh.dropsapi.pdr.Material;

public class StructurePartBlock implements IStructurePart{
	Material mat;
	int x, y, z;
	boolean relative;
	int meta;
	boolean usePlayerLoc;

	public StructurePartBlock(Material mat, int x, int y, int z, boolean relative, int meta, boolean usePlayerLocation) {
		this.mat = mat;
		this.x = x;
		this.y = y;
		this.z = z;
		this.relative = relative;
		this.meta = meta;
		this.usePlayerLoc = usePlayerLocation;
	}
	
	@Override
	public void create(BlockPos bp, EntityPlayer ep){
		if(mat == null){
			System.err.println("The Material was not specified.");
		}
		if(mat.getBlockIfExists() == null){
			System.err.println("There is no Block for this Bukkit-PDR-Material.");
		}
		if(usePlayerLoc){
			create(new LocationA(ep.worldObj, ep.posX, ep.posY, ep.posZ));
		} else {
			create(new LocationA(ep.worldObj, bp.getX(), bp.getY(), bp.getZ()));
		}
	}
	
	@Override
	public void create(LocationA locA){
		if(relative){
			LocationA locA2 = locA.getRelative(x, y, z);
			locA.getWorld().setBlockState(locA2.toBlockPos(), mat.getBlockIfExists().getStateFromMeta(meta));
		} else {
			LocationA locA2 = new LocationA(locA.getWorld(), x, y, z);
			locA2.getWorld().setBlockState(locA2.toBlockPos(), mat.getBlockIfExists().getStateFromMeta(meta));
		}
	}
}
