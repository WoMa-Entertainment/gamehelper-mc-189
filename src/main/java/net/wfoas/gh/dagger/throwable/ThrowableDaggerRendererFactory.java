package net.wfoas.gh.dagger.throwable;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.wfoas.gh.villager.entity.GHVillager;

public class ThrowableDaggerRendererFactory implements IRenderFactory<ThrowableDagger> {

	@Override
	public Render<? super ThrowableDagger> createRenderFor(RenderManager manager) {
		return new ThrowableDaggerRenderer(manager);
	}
}