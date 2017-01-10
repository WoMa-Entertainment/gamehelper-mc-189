package net.wfoas.gh.dropsapi.pdr;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityDiamondEgg extends EntityEgg {
	static{
		rand = new LuckyRandom();
	}
	static LuckyRandom rand;
	public EntityDiamondEgg(World world, double posX, double posY, double posZ) {
		super(world, posX, posY + 5, posZ);
		this.setVelocity(rand.nextDouble() * rand.nextInt(), rand.nextDouble() * rand.nextInt(), rand.nextDouble() * rand.nextInt());
		// func_70012_b(posX, posY + 0.5D, posZ,
		// MathHelper.func_76142_g(this.field_70146_Z.nextFloat() * 360.0F),
		// -90.0F + (this.field_70146_Z.nextInt(20) - 10));
		// float f = 0.4F;
		// this.field_70159_w = (-MathHelper.func_76126_a(this.field_70177_z /
		// 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A /
		// 180.0F * 3.1415927F) * f);
		// this.field_70179_y = (MathHelper.func_76134_b(this.field_70177_z /
		// 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A /
		// 180.0F * 3.1415927F) * f);
		// this.field_70181_x = (-MathHelper.func_76126_a((this.field_70125_A +
		// func_70183_g()) / 180.0F * 3.1415927F) * f);
		// func_70186_c(this.field_70159_w, this.field_70181_x,
		// this.field_70179_y, func_70182_d(), 1.0F);
		// EntityExpBottle
		// ItemAppleGold
		// ItemPotion
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		// PotionEffect
		super.onImpact(mop);
		if (!this.worldObj.isRemote && EntityDiamondEgg.rand.nextInt(3) == 0) {
			EntityItem ei = new EntityItem(worldObj, this.posX, this.posY, this.posZ,
					new ItemStack(Items.diamond, EntityDiamondEgg.rand.nextInt(8)));
		}
	}

	// protected void func_70184_a(MovingObjectPosition
	// par1MovingObjectPosition)
	// {
	// if (par1MovingObjectPosition.field_72308_g != null) {
	// par1MovingObjectPosition.field_72308_g.func_70097_a(DamageSource.func_76356_a(this,
	// func_85052_h()), 0.0F);
	// }
	// if (!this.field_70170_p.field_72995_K) {
	// if (this.field_70146_Z.nextInt(3) == 0)
	// {
	// EntityItem diamond = new EntityItem(this.field_70170_p,
	// this.field_70165_t, this.field_70163_u, this.field_70161_v, new
	// ItemStack(Items.field_151045_i));
	// this.field_70170_p.func_72838_d(diamond);
	// }
	// else
	// {
	// EntityChicken entitychicken = new EntityChicken(this.field_70170_p);
	// entitychicken.func_70873_a(41536);
	// entitychicken.func_70012_b(this.field_70165_t, this.field_70163_u,
	// this.field_70161_v, this.field_70177_z, 0.0F);
	// this.field_70170_p.func_72838_d(entitychicken);
	// }
	// }
	// for (int j = 0; j < 8; j++) {
	// this.field_70170_p.func_175688_a(EnumParticleTypes.SNOWBALL,
	// this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0D, 0.0D,
	// 0.0D, new int[0]);
	// }
	// if (!this.field_70170_p.field_72995_K) {
	// func_70106_y();
	// }
	// }
	//
	// protected float func_70185_h() {
	// return 0.03F;
	// }
	//
	// protected float func_70182_d() {
	// return 0.6F;
	// }
	//
	// protected float func_70183_g() {
	// return 0.0F;
	// }
}