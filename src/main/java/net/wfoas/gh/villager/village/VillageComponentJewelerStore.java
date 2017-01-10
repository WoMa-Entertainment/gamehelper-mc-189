package net.wfoas.gh.villager.village;

import java.util.Random;

import net.minecraft.client.gui.GuiOverlayDebug;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.wfoas.gh.events.GameOverlayDebugRenderWorldAndDimension;

public class VillageComponentJewelerStore extends StructureVillagePieces.Village {

	@Override
	public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
		return false;
	}

	protected void spawnVillagers(World worldIn, StructureBoundingBox p_74893_2_, int p_74893_3_, int p_74893_4_,
			int p_74893_5_, int p_74893_6_) {
//		GuiOverlayDebug
	}

}
