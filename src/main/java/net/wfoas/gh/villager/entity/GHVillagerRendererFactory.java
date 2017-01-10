package net.wfoas.gh.villager.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class GHVillagerRendererFactory implements IRenderFactory<GHVillager> {

	@Override
	public Render<? super GHVillager> createRenderFor(RenderManager manager) {
		return new GHVillagerRenderer(manager);
	}

}
