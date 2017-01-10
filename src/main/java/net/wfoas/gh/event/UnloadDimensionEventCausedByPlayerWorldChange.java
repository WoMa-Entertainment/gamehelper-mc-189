package net.wfoas.gh.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.wfoas.gh.GameHelper;

@Cancelable
public final class UnloadDimensionEventCausedByPlayerWorldChange extends GHEvent {

	final public World world;
	final public int dimension;
	public boolean forceUnload = false;

	public UnloadDimensionEventCausedByPlayerWorldChange(int dimension) {
		this.dimension = dimension;
		world = DimensionManager.getWorld(dimension);
	}

	@Override
	public void run() {
		if (!this.isCanceled())
			eventRun(this);
	}

	public static void eventRun(UnloadDimensionEventCausedByPlayerWorldChange event) {
		if (event.world == null) {
			GameHelper.println("NullWorld cannot be unload. DimensionID: " + event.dimension
					+ "Please send this error to the mod author with a copy of this world to help resolving the bug!");
			return;
		}
		if (event.world.playerEntities.isEmpty()) {
			if (event.dimension == 0) {
				if (event.forceUnload) {
					GameHelper.getUtils().broadcastString("Welt " + event.dimension + " wird entladen...");
					DimensionManager.unloadWorld(event.dimension);
				}
				return;
			}
			GameHelper.getUtils().broadcastString("Welt " + event.dimension + " wird entladen...");
			DimensionManager.unloadWorld(event.dimension);
		}
	}

}
