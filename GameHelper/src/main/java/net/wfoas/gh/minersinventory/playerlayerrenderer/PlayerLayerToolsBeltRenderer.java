package net.wfoas.gh.minersinventory.playerlayerrenderer;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class PlayerLayerToolsBeltRenderer implements LayerRenderer<AbstractClientPlayer> {

	private static final ResourceLocation wing_textures = new ResourceLocation("gamehelper",
			"textures/wing_feather.png");
	private final RenderPlayer renderPlayer;
	private int displayList = 0;

	public PlayerLayerToolsBeltRenderer(RenderPlayer renderPlayer) {
		this.renderPlayer = renderPlayer;
	}

	public void doRenderLayer(AbstractClientPlayer entitylivingbaseIn, float p_177141_2_, float p_177141_3_,
			float partialTicks, float p_177141_5_, float p_177141_6_, float p_177141_7_, float scale) {
//		String name = entitylivingbaseIn.getGameProfile().getName();
//		if (ItemAngelRing.clientFlyingPlayers.containsKey(name)) {
//			int tex = ItemAngelRing.clientFlyingPlayers.get(name);
//			if ((tex <= 0) || (tex >= wing_textures.length)) {
//				return;
//			}
//			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//
//			GL11.glPushMatrix();
//
//			ModelRenderer bipedBody = this.renderPlayer.getMainModel().bipedBody;
//			bipedBody.postRender(0.0625F);
//			Minecraft.getMinecraft().renderEngine.bindTexture(wing_textures[tex]);
//
//			float v = (((ModelBox) bipedBody.cubeList.get(0)).posZ2 - ((ModelBox) bipedBody.cubeList.get(0)).posZ1)
//					/ 2.0F;
//
//			GL11.glTranslatef(0.0F, entitylivingbaseIn.isSneaking() ? 0.125F : 0.0F, 0.0625F * v);
//
//			boolean isFlying = entitylivingbaseIn.capabilities.isFlying;
//			if (this.displayList == 0) {
//				Tessellator instance = Tessellator.getInstance();
//				WorldRenderer t = instance.getWorldRenderer();
//				this.displayList = GLAllocation.generateDisplayLists(2);
//				GL11.glNewList(this.displayList, 4864);
//				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//				GL11.glTranslatef(0.0F, -0.3125F, 0.0F);
//				t.begin(7, DefaultVertexFormats.POSITION_TEX);
//				t.pos(0.0D, 0.0D, 0.0D).tex(0.0D, 0.0D).endVertex();
//				t.pos(0.0D, 1.0D, 0.0D).tex(0.0D, 1.0D).endVertex();
//				t.pos(1.0D, 1.0D, 0.0D).tex(1.0D, 1.0D).endVertex();
//				t.pos(1.0D, 0.0D, 0.0D).tex(1.0D, 0.0D).endVertex();
//				instance.draw();
//				GL11.glEndList();
//
//				GL11.glNewList(this.displayList + 1, 4864);
//				t.begin(7, DefaultVertexFormats.POSITION_TEX);
//				GL11.glTranslatef(0.0F, -0.3125F, 0.0F);
//				t.pos(0.0D, 0.0D, 0.0D).tex(0.0D, 0.0D).endVertex();
//				t.pos(0.0D, 1.0D, 0.0D).tex(0.0D, 1.0D).endVertex();
//				t.pos(-1.0D, 1.0D, 0.0D).tex(1.0D, 1.0D).endVertex();
//				t.pos(-1.0D, 0.0D, 0.0D).tex(1.0D, 0.0D).endVertex();
//				instance.draw();
//				GL11.glEndList();
//			}
//			GL11.glPushMatrix();
//			GL11.glCallList(this.displayList);
//			GL11.glPopMatrix();
//			GL11.glPushMatrix();
//			GL11.glCallList(this.displayList + 1);
//			GL11.glPopMatrix();
//			GL11.glPopMatrix();
//		}
	}

	public boolean shouldCombineTextures() {
		return false;
	}

}
