package net.wfoas.gh.proxies;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.GameHelperCoreModule;
import net.wfoas.gh.config.GHConfig;
import net.wfoas.gh.dagger.throwable.ThrowableDagger;
import net.wfoas.gh.dagger.throwable.ThrowableDaggerRendererFactory;
import net.wfoas.gh.enchaltar.TileEntityEnchantmentAltar;
import net.wfoas.gh.ghbrading.GHBranding;
import net.wfoas.gh.instench.TileEntityInstantEnchantmentTable;
import net.wfoas.gh.omapi.GameHelperAPI;
import net.wfoas.gh.omapi.GameHelperAPIClientSide;
import net.wfoas.gh.playernameuuid.ClientSidePlayerNameUUIDDataBase;
import net.wfoas.gh.potionbow.EntityShotPotion;
import net.wfoas.gh.potionbow.ShotPotionRenderFactory;
import net.wfoas.gh.protected_blocks.chest.ProtectedChestTileEntity;
import net.wfoas.gh.steamconnection.SteamConnection;
import net.wfoas.gh.survivaltabs.GHTabMinersInv;
import net.wfoas.gh.survivaltabs.GHThermalTab;
import net.wfoas.gh.villager.entity.GHVillager;
import net.wfoas.gh.villager.entity.GHVillagerRendererFactory;
import tconstruct.client.tabs.InventoryTabVanilla;
import tconstruct.client.tabs.TabRegistry;

public class ClientProxy extends CommonProxy implements LogicalServerEnvironment, LogicalClientEnvironment {
	public static String minercrafttitle;
	static public File mcDir;
	static public GHConfig client_options_gh;
	volatile static public List<String> ownedWorlds = new ArrayList<String>(), onlinePlayers = new ArrayList<String>(),
			allWorlds = new ArrayList<String>();
	volatile static public ClientSidePlayerNameUUIDDataBase playerNameUUIDdb = null;

	@Override
	public void preInit(FMLPreInitializationEvent event, GameHelper gh) {
		minercrafttitle = Display.getTitle();
		Display.setTitle(minercrafttitle + " [" + GameHelper.MOD_USE_NAME + " " + GameHelper.MODVER + "]");
		mcDir = Minecraft.getMinecraft().mcDataDir;
		File optionsgh = new File(mcDir, "optionsgh.ght");
		try {
			optionsgh.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		client_options_gh = new GHConfig(optionsgh);
		if (client_options_gh.getBoolean("enable_steamoverlay")) {
			client_options_gh.save();
			SteamConnection.loadSteamOverlay();
		} else {
			client_options_gh.setBoolean("enable_steamoverlay", false);
		}
		super.preInit(event, gh);
		if (event.getSide() == Side.CLIENT) {
			ClientRegistry.bindTileEntitySpecialRenderer(ProtectedChestTileEntity.class,
					new net.wfoas.gh.protected_blocks.chest.ProtectedChestTileEntitySpecialRenderer());
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEnchantmentAltar.class,
					new net.wfoas.gh.enchaltar.TileEntityEnchantmentAltarRenderer());
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityInstantEnchantmentTable.class,
					new net.wfoas.gh.instench.TileEntityInstantEnchantmentTableRenderer());
		}
		MinecraftForge.EVENT_BUS.register(new TabRegistry());
		try {
			GHBranding.addGHBranding(minercrafttitle);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		bindRenderer();
		GameHelperAPIClientSide clApi = (GameHelperAPIClientSide) GameHelperAPI.ghAPI();
		clApi.injectGHSurvivalTab(new InventoryTabVanilla());
		clApi.injectGHSurvivalTab(new GHTabMinersInv());
		clApi.injectGHSurvivalTab(new GHThermalTab());
	}

	public void bindRenderer() {
		RenderingRegistry.registerEntityRenderingHandler(GHVillager.class, new GHVillagerRendererFactory());
		RenderingRegistry.registerEntityRenderingHandler(ThrowableDagger.class, new ThrowableDaggerRendererFactory());
		RenderingRegistry.registerEntityRenderingHandler(EntityShotPotion.class, new ShotPotionRenderFactory());
	}

	@Override
	public void load(FMLInitializationEvent event, GameHelper gh) {
		super.load(event, gh);
		ModelBakery.registerItemVariants(GameHelperCoreModule.POTION_BOW,
				new ResourceLocation[] { new ResourceLocation(GameHelper.MODID + ":potion_bow"),
						new ResourceLocation(GameHelper.MODID + ":potion_bow_pull_0"),
						new ResourceLocation(GameHelper.MODID + ":potion_bow_pull_1"),
						new ResourceLocation(GameHelper.MODID + ":potion_bow_pull_2") });
		registerItem(GameHelperCoreModule.POTION_BOW, 0, GameHelper.MODID + ":potion_bow");
		registerItem(GameHelperCoreModule.POTION_BOW, 1, GameHelper.MODID + ":potion_bow_pull_0");
		registerItem(GameHelperCoreModule.POTION_BOW, 2, GameHelper.MODID + ":potion_bow_pull_1");
		registerItem(GameHelperCoreModule.POTION_BOW, 3, GameHelper.MODID + ":potion_bow_pull_2");
	}

	@SideOnly(Side.CLIENT)
	public static void registerItem(Item item, int metadata, String itemName) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, metadata,
				new ModelResourceLocation(itemName, "inventory"));
	}

	@Override
	public void postInit(FMLPostInitializationEvent event, GameHelper gh) {
		super.postInit(event, gh);
	}
}
