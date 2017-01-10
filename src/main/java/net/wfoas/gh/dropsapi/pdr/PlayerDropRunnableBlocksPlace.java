package net.wfoas.gh.dropsapi.pdr;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class PlayerDropRunnableBlocksPlace{
	int x, y, z, meta;
	Material blockType;
//	BlockPos bp;

	public PlayerDropRunnableBlocksPlace(int x, int y, int z, Material blockType, int meta) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.blockType = blockType;
		this.meta = meta;
//		bp = new BlockPos(x, y, z);
	}
	
	public void run(World w, BlockPos bp){
		if(blockType.getBlockIfExists() != null){
			if(meta == 0)
				w.setBlockState(new BlockPos(x + bp.getX(), y + bp.getY(), z + bp.getZ()), blockType.getBlockIfExists().getDefaultState());
			else
				w.setBlockState(new BlockPos(x + bp.getX(), y + bp.getY(), z + bp.getZ()), blockType.getBlockIfExists().getStateFromMeta(meta));
		}
		else
			System.err.println("BlockType " + blockType + "is not a BlockType!");
	}

}
