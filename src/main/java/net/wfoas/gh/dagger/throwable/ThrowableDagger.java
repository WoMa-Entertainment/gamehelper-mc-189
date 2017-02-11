package net.wfoas.gh.dagger.throwable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import net.wfoas.gh.GameHelperCoreModule;
import net.wfoas.gh.dropsapi.pdr.EntityType;
import net.wfoas.gh.dropsapi.pdr.LocationA;
import net.wfoas.gh.items.ItemDagger;
import net.wfoas.gh.network.NetworkHandler;
import net.wfoas.gh.network.packet.PacketPlaySyncDaggerRotationToClients;
import net.wfoas.gh.network.packet.PacketPlaySyncDaggerRotationToServer;

public class ThrowableDagger extends EntityThrowable {
	public static final DamageSource DAGGER_DAMAGE_CAUSE = new DamageSource("daggerDamage");
	private static final String __OBFID = "CL_00001722";

	public ThrowableDagger(World worldIn) {
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
		this.thrownItem = i.copy();
		// this.thrownItem.stackSize = 1;
		this.getEntityData().setTag("itm_pl_stc", i.serializeNBT());
	}

	public ThrowableDagger(World worldIn, EntityLivingBase p_i1774_2_) {
		super(worldIn, p_i1774_2_);
		shootersyaw = p_i1774_2_.rotationYaw;
		shooterspitch = p_i1774_2_.rotationPitch;
	}

	public ThrowableDagger(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
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

	@Override
	protected void onImpact(MovingObjectPosition p_70184_1_) {
		if (p_70184_1_.entityHit != null) {
			p_70184_1_.entityHit.attackEntityFrom(new DamageSourceDagger(getThrower(), this),
					ItemDagger.getDamageIron());
		}

		for (int i = 0; i < 8; ++i) {
			this.worldObj.spawnParticle(EnumParticleTypes.CRIT_MAGIC, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D,
					new int[0]);
		}

		if (!this.worldObj.isRemote) {
			if (p_70184_1_.typeOfHit == MovingObjectType.BLOCK) {
				ItemStack i = ItemStack.loadItemStackFromNBT(this.getEntityData().getCompoundTag("itm_pl_stc"));
				short dmg = 2;
				if (GameHelperCoreModule.IRON_DAGGER.getMaxDamage() < i.getItemDamage() + dmg) {
					i.damageItem(GameHelperCoreModule.IRON_DAGGER.getMaxDamage() - i.getItemDamage(),
							this.getThrower());
				} else {
					i.damageItem(dmg, this.getThrower());
				}
				this.getEntityData().removeTag("itm_pl_stc");
				this.getEntityData().setTag("itm_pl_stc", i.serializeNBT());
				this.motionX = 0;
				this.motionY = 0;
				this.motionZ = 0;
				setThrowableHeading(0, 0, 0, 1, 0);
				inGround = true;
				setVelo(0, 0, 0);
				this.velocityChanged = true;
				this.setDead();
				if (this.getEntityData().hasKey("itm_pl_stc")) {
					EntityType.dropItem(
							ItemStack.loadItemStackFromNBT(this.getEntityData().getCompoundTag("itm_pl_stc")),
							new LocationA(this.getPosition(), worldObj).getRelative(0, 1, 0));
				}
			} else if (p_70184_1_.typeOfHit == MovingObjectType.ENTITY) {
				ItemStack i = ItemStack.loadItemStackFromNBT(this.getEntityData().getCompoundTag("itm_pl_stc"));
				short dmg = 5;
				if (GameHelperCoreModule.IRON_DAGGER.getMaxDamage() < i.getItemDamage() + dmg) {
					i.damageItem(GameHelperCoreModule.IRON_DAGGER.getMaxDamage() - i.getItemDamage(),
							this.getThrower());
				} else {
					i.damageItem(dmg, this.getThrower());
				}
				this.getEntityData().removeTag("itm_pl_stc");
				this.getEntityData().setTag("itm_pl_stc", i.serializeNBT());
				this.motionX = 0;
				this.motionY = 0;
				this.motionZ = 0;
				setThrowableHeading(0, 0, 0, 1, 0);
				inGround = true;
				setVelo(0, 0, 0);
				this.velocityChanged = true;
				this.setDead();
				if (this.getEntityData().hasKey("itm_pl_stc")) {
					EntityType.dropItem(
							ItemStack.loadItemStackFromNBT(this.getEntityData().getCompoundTag("itm_pl_stc")),
							new LocationA(this.getPosition(), worldObj).getRelative(0, 1, 0));
				}
			}
		}
	}

	boolean isSetFromPlayer = false;
	float shootersyaw = 0f, shooterspitch = 0f;

	public void syncToClients() {
		NetworkHandler.sendToAllInDimension(new PacketPlaySyncDaggerRotationToClients(worldObj, this.getEntityId(),
				shootersyaw, shooterspitch, thrownItem), worldObj.provider.getDimensionId());
	}

	private void syncToClientsButShooter(EntityPlayerMP playp) {
		NetworkHandler.sendToAllInDimensionButOnePlayer(new PacketPlaySyncDaggerRotationToClients(worldObj,
				this.getEntityId(), shootersyaw, shooterspitch, thrownItem), worldObj.provider.getDimensionId(), playp);
	}

	boolean synccompleted = false;

	private void syncToServer() {
		if (!synccompleted) {
			NetworkHandler.sendToServer(
					new PacketPlaySyncDaggerRotationToServer(worldObj, this.getEntityId(), shootersyaw, shooterspitch));
			System.out.println("Synced rotation to server");
			synccompleted = true;
		}
	}

	public void setPlayersYawPitch(float yaw, float pitch) {
		if (!isSetFromPlayer) {
			shootersyaw = yaw;
			shooterspitch = pitch;
			this.getEntityData().setFloat("rot_pl_yaw", yaw);
			this.getEntityData().setFloat("rot_pl_pitch", pitch);
			isSetFromPlayer = true;
		}
		return;
	}

	public float getShootersYaw() {
		// return shootersyaw;
		return getEntityData().getFloat("rot_pl_yaw");
	}

	public float getShootersPitch() {
		// return shooterspitch;
		return getEntityData().getFloat("rot_pl_pit");
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer entityIn) {
		// if (!this.worldObj.isRemote && this.inGround) {
		// boolean flag = this.canBePickedUp == 1 || this.canBePickedUp == 2
		// // && entityIn.capabilities.isCreativeMode;
		// boolean flag = true;
		// if (flag && !entityIn.inventory.addItemStackToInventory(thrownItem))
		// {
		// flag = false;
		// }
		// if (flag) {
		// this.playSound("random.pop", 0.2F,
		// ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) *
		// 2.0F);
		// entityIn.onItemPickup(this, 1);
		// this.setDead();
		// }
		// }
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound) {
		super.writeEntityToNBT(tagCompound);
		tagCompound.setFloat("shootersyaw", shootersyaw);
		tagCompound.setFloat("shooterspitch", shooterspitch);
		if (thrownItem != null)
			tagCompound.setTag("iron_dagger", thrownItem.writeToNBT(new NBTTagCompound()));
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tagCompound) {
		super.readEntityFromNBT(tagCompound);
		shootersyaw = tagCompound.getFloat("shootersyaw");
		shooterspitch = tagCompound.getFloat("shooterspitch");
		if (tagCompound.hasKey("iron_dagger")) {
			thrownItem = ItemStack.loadItemStackFromNBT(tagCompound.getCompoundTag("iron_dagger"));
			System.out.println("has Tag iron_dagger");
		}
	}
}