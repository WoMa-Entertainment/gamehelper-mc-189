package net.wfoas.gh.potionbow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class ShotPotionRenderFactory implements IRenderFactory<EntityShotPotion> {

	@Override
	public Render<? super EntityShotPotion> createRenderFor(RenderManager manager) {
		return new RenderShotPotion(manager, Minecraft.getMinecraft().getRenderItem());
	}

}
