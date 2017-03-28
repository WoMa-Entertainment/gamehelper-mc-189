package net.wfoas.core.gh;

import java.util.Arrays;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class GameHelperCoreContainer extends DummyModContainer {
	public GameHelperCoreContainer() {
		super(new ModMetadata());
		ModMetadata meta = getMetadata();
		meta.modId = "gamehelper-core";
		meta.name = GameHelperCore.GH_COREMOD_NAME;
		meta.version = GameHelperCore.GH_COREMOD_VERSION;
		meta.credits = "-";
		meta.authorList = Arrays.asList("WinPlay02");
		meta.description = "";
		meta.url = "https://github.com/wfoasm-woma-net/gamehelper-mc-189";
		meta.screenshots = new String[0];
		meta.logoFile = "/assets/gamehelper/gh_core_mod.png";
	}

	@Override
	public boolean registerBus(EventBus bus, LoadController controller) {
		bus.register(this);
		return true;
	}

	@Subscribe
	public void modConstruction(FMLConstructionEvent evt) {

	}

	@Subscribe
	public void preInit(FMLPreInitializationEvent evt) {

	}

	@Subscribe
	public void init(FMLInitializationEvent evt) {

	}

	@Subscribe
	public void postInit(FMLPostInitializationEvent evt) {

	}
}
