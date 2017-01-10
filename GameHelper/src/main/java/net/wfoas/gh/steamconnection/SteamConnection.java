package net.wfoas.gh.steamconnection;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.lwjgl.LWJGLUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiOverlayDebug;
import net.minecraftforge.fml.client.GuiAccessDenied;
import net.wfoas.gh.proxies.ClientProxy;
import net.wfoas.gh.windowsregistry.WinRegistry;

public class SteamConnection {

	private static boolean overlayLoaded = false;

	public static boolean isOverlayLoaded() {
		return overlayLoaded;
	}

	public static final String STEAM_LIB_64 = "GameOverlayRenderer64";
	public static final String STEAM_LIB = "GameOverlayRenderer";

	// Windows
	public static final String DYNAMIC_LINKED_LIBRARY_EXTENSION = ".dll";
	// public static final String STEAM_PATH_32 = "\\Program Files\\Steam";
	// public static final String STEAM_PATH_64 = "\\Program Files
	// (x86)\\Steam";

	// OS X

	// Linux

	public static String foundAtLocation = null;

	public static void findSteamOverlay() {
		// System.getProperties().list(System.out);
		// System.setProperty("java.library.path",
		// STEAM_PATH + File.pathSeparator +
		// System.getProperty("java.library.path"));
		// System.out.println("java.lib.path: " +
		// System.getProperty("java.library.path"));
		// reloadJavaLibPath();
		if (Minecraft.getMinecraft().isJava64bit()) {
			if (LWJGLUtil.getPlatform() == LWJGLUtil.PLATFORM_WINDOWS) {
				// String systemdrive = System.getenv("systemdrive");
				// if (exists(systemdrive + STEAM_PATH_64 + File.separator +
				// STEAM_LIB_64
				// + DYNAMIC_LINKED_LIBRARY_EXTENSION)) {
				// foundAtLocation = systemdrive + STEAM_PATH_64 +
				// File.separator + STEAM_LIB_64
				// + DYNAMIC_LINKED_LIBRARY_EXTENSION;
				String path = readFromWinRegistry() + File.separator + STEAM_LIB_64 + DYNAMIC_LINKED_LIBRARY_EXTENSION;
				if (exists(path)) {
					foundAtLocation = path;
					return;
				}
				return;
				// }
			} else if (LWJGLUtil.getPlatform() == LWJGLUtil.PLATFORM_LINUX) {

			} else if (LWJGLUtil.getPlatform() == LWJGLUtil.PLATFORM_MACOSX) {

			}
			// only STEAM_LIB_64
		} else {
			if (LWJGLUtil.getPlatform() == LWJGLUtil.PLATFORM_WINDOWS) {
				String path = readFromWinRegistry() + File.separator + STEAM_LIB + DYNAMIC_LINKED_LIBRARY_EXTENSION;
				if (exists(path)) {
					foundAtLocation = path;
					return;
				}
				return;
			} else if (LWJGLUtil.getPlatform() == LWJGLUtil.PLATFORM_LINUX) {

			} else if (LWJGLUtil.getPlatform() == LWJGLUtil.PLATFORM_MACOSX) {

			}
			// only STEAM_LIB_32GuiScreenStartup
		}
	}

	public static void searchSteamOverlayPath() {
		loadPath();
		// if (foundAtLocation != null) {
		// if (exists(foundAtLocation)) {
		// // System.load(foundAtLocation);
		// return;
		// }
		// }
		if (foundAtLocation != null && exists(foundAtLocation)) {
			// savePath();
		} else {
			findSteamOverlay();
			if (foundAtLocation != null && exists(foundAtLocation)) {
				savePath();
				return;
			} else {
				System.err.println("No Steam Overlay was found on Operating System: " + LWJGLUtil.getPlatformName()
						+ " " + (Minecraft.getMinecraft().isJava64bit() ? "x64" : "x86"));
				return;
			}
			// System.err.println("No Steam Overlay was found on Operating
			// System: " + LWJGLUtil.getPlatformName() + " "
			// + (Minecraft.getMinecraft().isJava64bit() ? "x64" : "x86"));
			// return;
		}
	}

	public static void loadSteamOverlay() {
		searchSteamOverlayPath();
		if (foundAtLocation != null)
			try {
				// System.loadLibrary(STEAM_LIB);
				System.load(foundAtLocation);
				overlayLoaded = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public static String readFromWinRegistry() {
		try {
			return WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER, "Software\\Valve\\Steam", "SteamPath");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	// protected static void reloadJavaLibPath() {
	// try {
	// reloadJavaLibPath0();
	// } catch (NoSuchMethodException e) {
	// e.printStackTrace();
	// } catch (SecurityException e) {
	// e.printStackTrace();
	// } catch (IllegalAccessException e) {
	// e.printStackTrace();
	// } catch (IllegalArgumentException e) {
	// e.printStackTrace();
	// } catch (InvocationTargetException e) {
	// e.printStackTrace();
	// } catch (NoSuchFieldException e) {
	// e.printStackTrace();
	// }
	// }

	public static void savePath() {
		ClientProxy.client_options_gh.setString("steam_overlay_path", foundAtLocation);
		ClientProxy.client_options_gh.save();
	}

	public static void loadPath() {
		foundAtLocation = ClientProxy.client_options_gh.getString("steam_overlay_path");
	}
	//
	// private static void reloadJavaLibPath0() throws NoSuchMethodException,
	// SecurityException, IllegalAccessException,
	// IllegalArgumentException, InvocationTargetException, NoSuchFieldException
	// {
	// Method initializePath = ClassLoader.class.getMethod("initializePath",
	// String.class);
	// initializePath.setAccessible(true);
	// ClassLoader cl = SteamConnection.class.getClassLoader();
	// String[] usr_paths = (String[]) initializePath.invoke(null,
	// "java.library.path");
	// String[] sys_paths = (String[]) initializePath.invoke(null,
	// "sun.boot.library.path");
	// Field usr_paths_field = ClassLoader.class.getField("usr_paths");
	// usr_paths_field.setAccessible(true);
	// usr_paths_field.set(null, usr_paths);
	// Field sys_paths_field = ClassLoader.class.getField("usr_paths");
	// sys_paths_field.setAccessible(true);
	// sys_paths_field.set(null, sys_paths);
	// }

	public static boolean exists(String path) {
		File f = new File(path);
		if (f.exists()) {
			f = null;
			return true;
		}
		f = null;
		return false;
	}
}
