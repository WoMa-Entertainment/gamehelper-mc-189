package net.wfoas.gh.potionbow;

import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderShotPotion extends RenderSnowball<EntityShotPotion> {
	public RenderShotPotion(RenderManager renderManagerIn, RenderItem itemRendererIn) {
		super(renderManagerIn, Items.potionitem, itemRendererIn);
	}

	public ItemStack func_177082_d(EntityPotion entityIn) {
		return new ItemStack(this.field_177084_a, 1, entityIn.getPotionDamage());
	}
}