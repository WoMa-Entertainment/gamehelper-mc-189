package net.wfoas.gh.event;

import net.minecraftforge.fml.common.eventhandler.Event;

public abstract class GHEvent extends Event {

	/**
	 * This method will run from the GH-DefaultEventHandler and it will only
	 * run, if the event is not cancelled and if the event passed the whole
	 * MinecraftForge EventBus.
	 */
	public abstract void run();

}
