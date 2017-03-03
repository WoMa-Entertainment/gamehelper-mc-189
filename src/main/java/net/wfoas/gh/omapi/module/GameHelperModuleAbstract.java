package net.wfoas.gh.omapi.module;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;

public abstract class GameHelperModuleAbstract {
	public abstract void preInitServer(FMLPreInitializationEvent event);

	public abstract void preInitClient(FMLPreInitializationEvent event);

	public abstract void initServer(FMLInitializationEvent event);

	/**
	 * This Event should be used to add new villagers to the
	 * {@link net.wfoas.gh.villager.VillagerRegistrar}
	 */
	public abstract void initClient(FMLInitializationEvent event);

	public abstract void postInitServer(FMLPostInitializationEvent event);

	public abstract void postInitClient(FMLPostInitializationEvent event);

	public abstract void registerTab();

	public void serverStart(FMLServerStartingEvent fmlsse) {
	}
	public void serverStop(FMLServerStoppingEvent event) {
	}
}
