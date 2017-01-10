package net.wfoas.gh.minersinventory.playerlayerrenderer;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.EntitySelectors.ArmoredMob;

public class PlayerLayerRendererHeadLight implements LayerRenderer<AbstractClientPlayer> {

	@Override
	public void doRenderLayer(AbstractClientPlayer entitylivingbaseIn, float p_177141_2_, float p_177141_3_,
			float partialTicks, float p_177141_5_, float p_177141_6_, float p_177141_7_, float scale) {
//		net.minecraft.client.renderer.entity.layers.LayerArmorBase<ModelBase>
	}

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}

}
