package assets.gamehelper.textures.other.old_items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ThrowableDagger extends EntityThrowable {
	private static final String __OBFID = "CL_00001722";

	public ThrowableDagger(World worldIn) {
		super(worldIn);
	}

	protected ItemStack thrownItem;

	public void saveItemStackToEntityData(ItemStack i) {
		// this.getEntityData().setTag("item_dagger", i.writeToNBT(new
		// NBTTagCompound()));
		this.thrownItem = i;
	}

	public ThrowableDagger(World worldIn, EntityLivingBase p_i1774_2_) {
		super(worldIn, p_i1774_2_);
	}

	public ThrowableDagger(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		for (int i = 0; i < 4; ++i) {
			float f4 = 0.25F;
			this.worldObj.spawnParticle(EnumParticleTypes.CRIT, this.posX - this.motionX * f4,
					this.posY - this.motionY * f4, this.posZ - this.motionZ * f4, this.motionX,
					this.motionY, this.motionZ, new int[0]);
		}
	}

	@Override
	protected void onImpact(MovingObjectPosition p_70184_1_) {
		if (p_70184_1_.entityHit != null) {
			p_70184_1_.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 542);
		}

		for (int i = 0; i < 8; ++i) {
			this.worldObj.spawnParticle(EnumParticleTypes.CRIT_MAGIC, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D,
					new int[0]);
		}

		if (!this.worldObj.isRemote) {
			this.setDead();
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound) {
		super.writeEntityToNBT(tagCompound);
		tagCompound.setTag("iron_dagger", thrownItem.writeToNBT(new NBTTagCompound()));
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tagCompund) {
		thrownItem = ItemStack.loadItemStackFromNBT(tagCompund.getCompoundTag("iron_dagger"));
	}

}