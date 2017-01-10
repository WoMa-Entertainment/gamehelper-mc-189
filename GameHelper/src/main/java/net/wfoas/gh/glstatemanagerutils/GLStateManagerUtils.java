package net.wfoas.gh.glstatemanagerutils;

import java.lang.reflect.Field;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.GlStateManager;

public class GLStateManagerUtils {
	// protected static Field textureStateField;
	// protected static Field activeTextureUnitField;
	//
	// static void init() {
	// try {
	// textureStateField = GlStateManager.class.getFields()[15];
	// textureStateField.setAccessible(true);
	// for (Field f : GlStateManager.class.getDeclaredFields()) {
	// if (f.getDeclaringClass().equals(Integer.class)) {
	// activeTextureUnitField = f;
	// break;
	// }
	// }
	// activeTextureUnitField.setAccessible(true);
	// } catch (IllegalArgumentException e) {
	// e.printStackTrace();
	// } catch (SecurityException e) {
	// e.printStackTrace();
	// }
	// }

	public static int getCurrentlyBoundTexture() {
		// try {
		// int activeTexUnit = activeTextureUnitField.getInt(null);
		// gl11.texture
		// } catch (IllegalArgumentException e) {
		// e.printStackTrace();
		// } catch (IllegalAccessException e) {
		// e.printStackTrace();
		// }
		return GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D);
	}
}
