package net.wfoas.gh.dagger.staticdaggers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.wfoas.gh.network.NetworkHandler;
import net.wfoas.gh.network.packet.PacketPlaySyncDaggerRotationToClients;
import net.wfoas.gh.network.packet.PacketPlaySyncDaggerRotationToServer;

public class StaticDagger extends Entity {
	public static final DamageSource DAGGER_DAMAGE_CAUSE = new DamageSource("daggerDamage");
	private static final String __OBFID = "CL_00001722";

	public StaticDagger(World worldIn) {
		super(worldIn);
	}

	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEFINED;
	}

	public void setVelo(double x, double y, double z) {
		this.motionX = x;
		this.motionY = y;
		this.motionZ = z;

		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
			float f = MathHelper.sqrt_double(x * x + z * z);
			this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(x, z) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(y, f) * 180.0D / Math.PI);
		}
	}

	protected ItemStack thrownItem;

	public void saveItemStackToEntityData(ItemStack i) {
		this.thrownItem = i;
	}

	public StaticDagger(World worldIn, EntityLivingBase p_i1774_2_) {
		super(worldIn);
		shootersyaw = p_i1774_2_.rotationYaw;
		shooterspitch = p_i1774_2_.rotationPitch;
		thrower = p_i1774_2_;
	}

	public StaticDagger(World worldIn, double x, double y, double z) {
		// super(worldIn, x, y, z);
		super(worldIn);
		super.setPosition(x, y, z);
	}

	@Override
	public void onUpdate() {
		Entity thise = this;
		super.onUpdate();
		for (int i = 0; i < 4; ++i) {
			float f4 = 0.25F;
			this.worldObj.spawnParticle(EnumParticleTypes.CRIT, this.posX - this.motionX * f4,
					this.posY - this.motionY * f4, this.posZ - this.motionZ * f4, this.motionX, this.motionY,
					this.motionZ, new int[0]);
		}
	}

	// protected void onImpact(MovingObjectPosition p_70184_1_) {
	// if (p_70184_1_.entityHit != null) {
	// p_70184_1_.entityHit.attackEntityFrom(new
	// DamageSourceDagger(getThrower(), this),
	// IronDagger.getDamageIron());
	// }
	//
	// for (int i = 0; i < 8; ++i) {
	// this.worldObj.spawnParticle(EnumParticleTypes.CRIT_MAGIC, this.posX,
	// this.posY, this.posZ, 0.0D, 0.0D, 0.0D,
	// new int[0]);
	// }
	//
	// if (!this.worldObj.isRemote) {
	// if (p_70184_1_.typeOfHit == MovingObjectType.BLOCK) {
	// // this.motionX = 0;
	// // this.motionY = 0;
	// // this.motionZ = 0;
	// // setThrowableHeading(0, 0, 0, 1, 0);
	// inGround = true;
	// // setVelo(0, 0, 0);
	// // this.velocityChanged = true;
	// } else {
	// this.setDead();
	// }
	// }
	// }

	boolean isSetFromPlayer = false;
	float shootersyaw = 0f, shooterspitch = 0f;

	public void syncToClients() {
		NetworkHandler.sendToAllInDimension(new PacketPlaySyncDaggerRotationToClients(worldObj, this.getEntityId(),
				shootersyaw, shooterspitch, thrownItem), worldObj.provider.getDimensionId());
	}

	public void syncToServer() {
		NetworkHandler.sendToServer(
				new PacketPlaySyncDaggerRotationToServer(worldObj, this.getEntityId(), shootersyaw, shooterspitch));
	}

	public void setPlayersYawPitch(float yaw, float pitch) {
		System.out.println("set that mafucking rot");
		if (!isSetFromPlayer) {
			shootersyaw = yaw;
			shooterspitch = pitch;
			// syncToServer();
			// this.setPositionAndRotation(posX, posY, posZ, rotationYaw + yaw,
			// rotationPitch + pitch);
			isSetFromPlayer = true;
		}
		return;
	}

	public float getShootersYaw() {
		return shootersyaw;
	}

	public float getShootersPitch() {
		return shooterspitch;
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer entityIn) {
		if (!this.worldObj.isRemote) {
			// boolean flag = this.canBePickedUp == 1 || this.canBePickedUp == 2
			// && entityIn.capabilities.isCreativeMode;
			boolean flag = true;
			if (flag && !entityIn.inventory.addItemStackToInventory(thrownItem)) {
				flag = false;
			}
			if (flag) {
				this.playSound("random.pop", 0.2F,
						((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
				entityIn.onItemPickup(this, 1);
				this.setDead();
			}
		}
	}

	EntityLivingBase thrower = null;
	String throwerName = null;

	public EntityLivingBase getThrower() {
		if (this.thrower == null && this.throwerName != null && this.throwerName.length() > 0) {
			this.thrower = this.worldObj.getPlayerEntityByName(this.throwerName);
		}

		return this.thrower;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound) {
		// super.writeEntityToNBT(tagCompound);
		tagCompound.setDouble("x", this.posX);
		tagCompound.setDouble("y", this.posY);
		tagCompound.setDouble("z", this.posZ);
		tagCompound.setFloat("shootersyaw", shootersyaw);
		tagCompound.setFloat("shooterspitch", shooterspitch);
		if (thrownItem != null)
			tagCompound.setTag("iron_dagger", thrownItem.writeToNBT(new NBTTagCompound()));
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tagCompound) {
		// super.readEntityFromNBT(tagCompound);
		shootersyaw = tagCompound.getFloat("shootersyaw");
		shooterspitch = tagCompound.getFloat("shooterspitch");
		posX = tagCompound.getDouble("x");
		posY = tagCompound.getDouble("y");
		posZ = tagCompound.getDouble("z");
		if (tagCompound.hasKey("iron_dagger")) {
			thrownItem = ItemStack.loadItemStackFromNBT(tagCompound.getCompoundTag("iron_dagger"));
			System.out.println("has Tag iron_dagger");
		}
	}

	@Override
	protected void entityInit() {

	}

}