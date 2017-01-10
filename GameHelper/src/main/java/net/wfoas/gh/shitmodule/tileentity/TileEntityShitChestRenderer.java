package net.wfoas.gh.shitmodule.tileentity;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.wfoas.gh.GameHelper;

@SideOnly(value = Side.CLIENT)
public class TileEntityShitChestRenderer extends TileEntitySpecialRenderer{

	private static final ResourceLocation shitChesttextureNormal = new ResourceLocation(GameHelper.MODID + ":" + "textures/entity/chest/shit_chest.png");
	private ModelChest chestModel = new ModelChest();
	@Override
	public void renderTileEntityAt(TileEntity te, double posX, double posY, double posZ,
			float f, int i) {
		if(!(te instanceof ShitChestTileEntity))
			return;
		ShitChestTileEntity ti = (ShitChestTileEntity)te;
		int j;

        if (!ti.hasWorldObj())
        {
            j = 0;
        }
        else
        {
            Block block = ti.getBlockType();
            j = ti.getBlockMetadata();

//            if (block instanceof BlockChest && j == 0)
//            {
//                ((BlockChest)block).checkForSurroundingChests(ti.getWorld(), p_180538_1_.getPos(), p_180538_1_.getWorld().getBlockState(p_180538_1_.getPos()));
//                j = ti.getBlockMetadata();
//            }

//            ti.checkForAdjacentChests();
        }

//        if (p_180538_1_.adjacentChestZNeg == null && p_180538_1_.adjacentChestXNeg == null)
//        {
//            ModelChest modelchest;
//
////            if (p_180538_1_.adjacentChestXPos == null && p_180538_1_.adjacentChestZPos == null)
////            {
//                modelchest = this.simpleChest;

                if (i >= 0)
                {
                    this.bindTexture(DESTROY_STAGES[i]);
                    GlStateManager.matrixMode(5890);
                    GlStateManager.pushMatrix();
                    GlStateManager.scale(4.0F, 4.0F, 1.0F);
                    GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
                    GlStateManager.matrixMode(5888);
                }
//                else if (p_180538_1_.getChestType() == 1)
//                {
//                    this.bindTexture(textureTrapped);
//                }
//                else if (this.isChristams)
//                {
//                    this.bindTexture(textureChristmas);
//                }
                else
                {
                    this.bindTexture(shitChesttextureNormal);
                }
//            }
//            else
//            {
//                modelchest = this.largeChest;
//
//                if (p_180538_9_ >= 0)
//                {
//                    this.bindTexture(DESTROY_STAGES[p_180538_9_]);
//                    GlStateManager.matrixMode(5890);
//                    GlStateManager.pushMatrix();
//                    GlStateManager.scale(8.0F, 4.0F, 1.0F);
//                    GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
//                    GlStateManager.matrixMode(5888);
//                }
//                else if (p_180538_1_.getChestType() == 1)
//                {
//                    this.bindTexture(textureTrappedDouble);
//                }
//                else if (this.isChristams)
//                {
//                    this.bindTexture(textureChristmasDouble);
//                }
//                else
//                {
//                    this.bindTexture(textureNormalDouble);
//                }
//            }

            GlStateManager.pushMatrix();
            GlStateManager.enableRescaleNormal();

            if (i < 0)
            {
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            }

            GlStateManager.translate((float)posX, (float)posY + 1.0F, (float)posZ + 1.0F);
            GlStateManager.scale(1.0F, -1.0F, -1.0F);
            GlStateManager.translate(0.5F, 0.5F, 0.5F);
            short short1 = 0;

            if (j == 2)
            {
                short1 = 180;
            }

            if (j == 3)
            {
                short1 = 0;
            }

            if (j == 4)
            {
                short1 = 90;
            }

            if (j == 5)
            {
                short1 = -90;
            }

//            if (j == 2 && p_180538_1_.adjacentChestXPos != null)
//            {
//                GlStateManager.translate(1.0F, 0.0F, 0.0F);
//            }
//
//            if (j == 5 && p_180538_1_.adjacentChestZPos != null)
//            {
//                GlStateManager.translate(0.0F, 0.0F, -1.0F);
//            }

            GlStateManager.rotate(short1, 0.0F, 1.0F, 0.0F);
            GlStateManager.translate(-0.5F, -0.5F, -0.5F);
            float f1 = ti.prevLidAngle + (ti.lidAngle - ti.prevLidAngle) * f;
            float f2;

//            if (ti.adjacentChestZNeg != null)
//            {
//                f2 = ti.adjacentChestZNeg.prevLidAngle + (ti.adjacentChestZNeg.lidAngle - ti.adjacentChestZNeg.prevLidAngle) * f;
//
//                if (f2 > f1)
//                {
//                    f1 = f2;
//                }
//            }

//            if (p_180538_1_.adjacentChestXNeg != null)
//            {
//                f2 = p_180538_1_.adjacentChestXNeg.prevLidAngle + (p_180538_1_.adjacentChestXNeg.lidAngle - p_180538_1_.adjacentChestXNeg.prevLidAngle) * p_180538_8_;
//
//                if (f2 > f1)
//                {
//                    f1 = f2;
//                }
//            }

            f1 = 1.0F - f1;
            f1 = 1.0F - f1 * f1 * f1;
            chestModel.chestLid.rotateAngleX = -(f1 * (float)Math.PI / 2.0F);
            chestModel.renderAll();
            GlStateManager.disableRescaleNormal();
            GlStateManager.popMatrix();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            if (i >= 0)
            {
                GlStateManager.matrixMode(5890);
                GlStateManager.popMatrix();
                GlStateManager.matrixMode(5888);
            }
//        }
//		
	}

}
