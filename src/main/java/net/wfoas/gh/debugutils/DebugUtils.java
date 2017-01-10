package net.wfoas.gh.debugutils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.wfoas.gh.glstatemanagerutils.GLStateManagerUtils;

public class DebugUtils {

	public static void writeDebugTextures() {
		int currBoundTexture = GLStateManagerUtils.getCurrentlyBoundTexture();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().getTextureMapBlocks().getGlTextureId());
		int width = GL11.glGetTexLevelParameteri(GL11.GL_TEXTURE_2D, 0, GL11.GL_TEXTURE_WIDTH);
		int height = GL11.glGetTexLevelParameteri(GL11.GL_TEXTURE_2D, 0, GL11.GL_TEXTURE_HEIGHT);
		ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * 4);
		GL11.glGetTexImage(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, GL11.GL_BYTE, buffer);
		File file = new File("screenshots" + File.separator + "debug_textures" + ".png");
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int i = (x + (width * y)) * 4;
				int r = buffer.get(i) & 0xFF;
				int g = buffer.get(i + 1) & 0xFF;
				int b = buffer.get(i + 2) & 0xFF;
				image.setRGB(x, height - (y + 1), (0xFF << 24) | (r << 16) | (g << 8) | b);
			}
		}
		System.out.println("Saved TextureMap: " + file.getAbsolutePath());
		try {
			ImageIO.write(image, "PNG", file);
		} catch (IOException e) {
		}
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, currBoundTexture);
	}
}
