package net.wfoas.gh.dagger.throwable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.GameHelperCoreModule;

@SideOnly(Side.CLIENT)
public class ThrowableDaggerRenderer extends Render {

	public static ItemStack DAGGER_STACK;

	public ThrowableDaggerRenderer(RenderManager renderManager) {
		super(renderManager);
		DAGGER_STACK = new ItemStack(GameHelperCoreModule.IRON_DAGGER);
	}

	@Override
	public boolean shouldRender(Entity entity, ICamera camera, double camX, double camY, double camZ) {
		return true;
	}

	private static final ResourceLocation textureLocation = new ResourceLocation(
			GameHelper.MODID + ":" + "textures/items/iron_dagger.png");

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return textureLocation;
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
		if (entity instanceof ThrowableDagger)
			this.renderDagger((ThrowableDagger) entity, x, y, z, p_76986_8_, partialTicks);
	}

	public void renderDagger(ThrowableDagger dagger, double x, double y, double z, float _field, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y, (float) z);
		GlStateManager.enableRescaleNormal();
		GlStateManager.scale(0.5F, 0.5F, 0.5F);
		// GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F,
		// 0.0F);
		// GlStateManager.rotate(this.renderManager.playerViewX, 1.0F, 0.0F,
		// 0.0F);{
		{
			if (dagger.getThrower() instanceof EntityPlayer) {
				if (dagger.getThrower().getName().equalsIgnoreCase(Minecraft.getMinecraft().thePlayer.getName())) {
					dagger.setPlayersYawPitch(-this.renderManager.playerViewY, this.renderManager.playerViewX);
					dagger.syncToServer();
				}
			}
		}
		GlStateManager.rotate(dagger.getShootersYaw(), 0, 1, 0);
		GlStateManager.rotate(dagger.getShootersPitch(), 1, 0, 0);
		GlStateManager.rotate(90, 0, 1, 0);
		GlStateManager.rotate(135, 0, 0, 1);// -45 + 180
		this.bindTexture(TextureMap.locationBlocksTexture);
		if (dagger.thrownItem != null)
			Minecraft.getMinecraft().getRenderItem().renderItem(dagger.thrownItem,
					Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getItemModel(dagger.thrownItem));
		else
			Minecraft.getMinecraft().getRenderItem().renderItem(DAGGER_STACK,
					Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getItemModel(DAGGER_STACK));
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
		super.doRender(dagger, x, y, z, _field, partialTicks);
		renderOffsetAABB(dagger.getEntityBoundingBox(), x, y, z);
	}
}
