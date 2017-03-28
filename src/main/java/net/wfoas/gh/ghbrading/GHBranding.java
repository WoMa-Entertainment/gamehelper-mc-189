package net.wfoas.gh.ghbrading;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.wfoas.core.gh.GameHelperCore;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.dropsapi.pdr.ChatColor;
import net.wfoas.gh.omapi.GameHelperAPI;

public class GHBranding {
	static Field brandingMc, branding;
	static List<String> brandsMc, brands;

	static {
		for (Field f : FMLCommonHandler.class.getDeclaredFields()) {
			if (List.class.isAssignableFrom(f.getType())) {
				ParameterizedType brandListType = (ParameterizedType) f.getGenericType();
				Class<?> brandListClass = (Class<?>) brandListType.getActualTypeArguments()[0];
				if (brandListClass.equals(String.class)) {
					if (brandingMc == null) {
						brandingMc = f;
					} else {
						branding = f;
					}
				}
			}
		}
		brandingMc.setAccessible(true);
		branding.setAccessible(true);
		brandsMc = new ArrayList<String>();
		brands = new ArrayList<String>();
	}

	public static void addBrand(String colored, String plain) {
		brandsMc.add(colored);
		brands.add(plain);
	}

	public static void brandWithGH(String mc) throws IllegalArgumentException, IllegalAccessException {
		MinecraftForge.EVENT_BUS.register(new GHBrandingEventPlayerName());
		brandingMc.set(FMLCommonHandler.instance(), new ArrayList<String>());
		branding.set(FMLCommonHandler.instance(), new ArrayList<String>());
		List<String> brandList = (List) brandingMc.get(FMLCommonHandler.instance());
		List<String> brandNoMCList = (List) branding.get(FMLCommonHandler.instance());
		for (String br : brandsMc) {
			brandList.add(br);
		}
		for (String g : brands) {
			brandNoMCList.add(g);
		}
		brandList.add(ChatColor.RED + mc);
		brandList.add(ChatColor.YELLOW + MinecraftForge.MC_VERSION + " | " + ForgeVersion.getVersion());// MinecraftForge-Version
	}
}
