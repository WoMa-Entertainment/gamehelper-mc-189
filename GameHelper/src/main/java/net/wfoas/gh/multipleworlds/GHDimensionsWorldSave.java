package net.wfoas.gh.multipleworlds;

import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.wfoas.gh.multipleworlds.storage.GHWorldManager;

public class GHDimensionsWorldSave {
	@SubscribeEvent
	public void worldSave(WorldEvent.Save saveevent) {
		if (saveevent.world.provider.getDimensionId() == 0) {
			GHWorldManager.save();
		}
	}
}
