package net.wfoas.gh.events;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.config.GHConfigKey;
import net.wfoas.gh.enchantment.event.DestructionEvent;
import net.wfoas.gh.enchantment.event.HeadlootEvent;
import net.wfoas.gh.enchantment.event.SmeltingEvent;
import net.wfoas.gh.enchantment.event.SoulboundEvent;
import net.wfoas.gh.enchantment.event.XPBoostEvent;
import net.wfoas.gh.event.DefaultEventHandler;
import net.wfoas.gh.events.otherworldevent.OtherWorldEvents;
import net.wfoas.gh.minersinventory.helmetlight.ClientSideLightHandler;
import net.wfoas.gh.multipleworlds.GHDimensionsWorldSave;
import net.wfoas.gh.network.securedlogin.CanceledEvents;
import net.wfoas.gh.network.securedlogin.PlayerConnectedSecuredLogin;
import net.wfoas.gh.omapi.GameHelperAPI;
import net.wfoas.gh.optionshook.OptionsHook;
import net.wfoas.gh.protected_blocks.CallbackPlaceDestroyProtectedBlocks;
import net.wfoas.gh.sync.SyncModRelevantDataWithClient;

public class EventRegistar {
	public static void registerEventClass() {
		MinecraftForge.EVENT_BUS.register(new DefaultEventHandler());
		MinecraftForge.EVENT_BUS.register(new GHDimensionsWorldSave());
		MinecraftForge.EVENT_BUS.register(new PlayerNameChangeEvent());
		MinecraftForge.EVENT_BUS.register(new SmeltingEvent());
		MinecraftForge.EVENT_BUS.register(new HeadlootEvent());
		MinecraftForge.EVENT_BUS.register(new SoulboundEvent());
		MinecraftForge.EVENT_BUS.register(new DestructionEvent());
		MinecraftForge.EVENT_BUS.register(new XPBoostEvent());
		MinecraftForge.EVENT_BUS.register(new PlayerJoin());
		MinecraftForge.EVENT_BUS.register(GameHelperAPI.ghAPI().ghScheduler());
		MinecraftForge.EVENT_BUS.register(new PlayerDeathEventMinersInvDrop());
		MinecraftForge.EVENT_BUS.register(new PlayerChatModificator());
		if (GameHelper.EVENT_SIDE == Side.CLIENT) {
			MinecraftForge.EVENT_BUS.register(new OptionsHook());
			MinecraftForge.EVENT_BUS.register(new GameOverlayDebugRenderWorldAndDimension());
		}
		MinecraftForge.EVENT_BUS.register(new ClientSideLightHandler());
		MinecraftForge.EVENT_BUS.register(new OtherWorldEvents());
		MinecraftForge.EVENT_BUS.register(new SyncModRelevantDataWithClient());
		if (GameHelper.instance.CONFIG.getBoolean(GHConfigKey.REQ_LOGIN)) {
			MinecraftForge.EVENT_BUS.register(new PlayerConnectedSecuredLogin());
			MinecraftForge.EVENT_BUS.register(new CanceledEvents());
		}
		MinecraftForge.EVENT_BUS.register(new CallbackPlaceDestroyProtectedBlocks());
	}
}
