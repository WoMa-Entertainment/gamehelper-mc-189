package net.wfoas.gh.villager.entity;

import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.wfoas.gh.villager.VillagerRegistrar;

@SideOnly(Side.CLIENT)
public class GHVillagerRenderer extends RenderLiving<GHVillager> {

	public GHVillagerRenderer(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelVillager(0.0F), 0.5F);
		this.addLayer(new LayerCustomHead(this.getMainModel().villagerHead));
	}

	public ModelVillager getMainModel() {
		return (ModelVillager) super.getMainModel();
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called
	 * unless you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(GHVillager entity) {
		// System.out.println("Profession: " + entity.getProfession());
		// System.out.println("GHVProf: " +
//		 VillagerRegistrar.getProfessionById(entity.getProfession()));
//		 System.out.println("Tex: " +
//		 VillagerRegistrar.getProfessionById(entity.getProfession()).getTexture());EntityPlayerMP
		return VillagerRegistrar.getProfessionById(entity.getProfession()).getTexture();
	}

	public void doRender(GHVillager entity, double x, double y, double z, float entityYaw, float partialTicks) {
		bindEntityTexture(entity);
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	/**
	 * Allows the render to do any OpenGL state modifications necessary before
	 * the model is rendered. Args: entityLiving, partialTickTime
	 */
	protected void preRenderCallback(EntityVillager entitylivingbaseIn, float partialTickTime) {
		float f = 0.9375F;

		if (entitylivingbaseIn.getGrowingAge() < 0) {
			f = (float) ((double) f * 0.5D);
			this.shadowSize = 0.25F;
		} else {
			this.shadowSize = 0.5F;
		}

		GlStateManager.scale(f, f, f);
	}
}