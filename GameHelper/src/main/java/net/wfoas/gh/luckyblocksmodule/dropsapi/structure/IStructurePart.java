package net.wfoas.gh.luckyblocksmodule.dropsapi.structure;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.wfoas.gh.dropsapi.pdr.LocationA;

public interface IStructurePart {
	void create(BlockPos bp, EntityPlayer ep);
	
	void create(LocationA locA);
}
