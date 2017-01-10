package net.wfoas.gh.event;

import net.wfoas.gh.multipleworlds.GHWorld;
import net.wfoas.gh.multipleworlds.storage.GHWorldManager;

public class LoadDimensionEventCausedByPlayerRequest extends GHEvent {
	// final public World world;
	final public GHWorld dimension;

	public LoadDimensionEventCausedByPlayerRequest(GHWorld dimension) {
		this.dimension = dimension;
		// world = DimensionManager.getWorld(dimension);
	}

	public LoadDimensionEventCausedByPlayerRequest(int dimension) {
		this.dimension = GHWorldManager.getGHWorldByDimensionID(dimension);
		// world = DimensionManager.getWorld(dimension);
	}

	@Override
	public void run() {
		if (!this.isCanceled())
			eventRun(this);
	}

	public static void eventRun(LoadDimensionEventCausedByPlayerRequest event) {
		// if (event.world == null) {
		// GameHelper.println("NullWorld cannot be unload. DimensionID: " +
		// event.dimension
		// + "Please send this error to the mod author with a copy of this world
		// to help resolving the bug!");
		// return;
		// }
		// if (event.world.playerEntities.isEmpty()) {
		// GameHelper.getUtils().broadcast("Welt " + event.dimension + " wird
		// entladen...");
		// DimensionManager.unloadWorld(event.dimension);
		// }
		// f
		// GHWorld ghw =
		// GHWorldManager.getGHWorldByDimensionID(event.dimension);
		if (event.dimension == null) {
			System.err.println("World is null for DimensionID: " + event.dimension + "! No Dimension will be loaded!");
			return;
		}
		GHWorldManager.loadWorld(event.dimension);
	}
}
