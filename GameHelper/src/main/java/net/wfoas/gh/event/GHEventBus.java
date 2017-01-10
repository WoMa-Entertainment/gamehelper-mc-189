package net.wfoas.gh.event;

import net.minecraftforge.common.MinecraftForge;

public class GHEventBus {
	public static void fireEvent(GHEvent ghe) {
		boolean b = MinecraftForge.EVENT_BUS.post(ghe);
		if (!b) {
			ghe.run();
		}
	}

	public static void registerEventHandler(Object o) {
		MinecraftForge.EVENT_BUS.register(o);
	}

	public static void unregisterEventHandler(Object o) {
		MinecraftForge.EVENT_BUS.unregister(o);
	}
}
