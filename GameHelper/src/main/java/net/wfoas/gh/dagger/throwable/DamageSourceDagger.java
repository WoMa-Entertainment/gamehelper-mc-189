package net.wfoas.gh.dagger.throwable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;

public class DamageSourceDagger extends DamageSource {

	Entity thrower;
	ThrowableDagger dagger;

	public DamageSourceDagger(ThrowableDagger d) {
		this(null, d);
	}

	public DamageSourceDagger(Entity thrower, ThrowableDagger th) {
		super("daggerDamage");
		this.setProjectile();
		this.thrower = thrower;
		this.dagger = th;
	}

	@Override
	public Entity getEntity() {
		return thrower;
	}

	@Override
	public IChatComponent getDeathMessage(EntityLivingBase p_151519_1_) {
		// EntityLivingBase entitylivingbase1 = p_151519_1_.func_94060_bK();
		Entity killer = thrower;
		// String s = "death.attack." + this.damageType;
		// String s1 = s + ".player";
		return thrower != null && StatCollector.canTranslate("death.attack." + this.getDamageType() + ".playerkill")
				? new ChatComponentTranslation("death.attack." + this.getDamageType() + ".playerkill",
						new Object[] { p_151519_1_.getDisplayName(), thrower.getDisplayName() })
				: new ChatComponentTranslation("death.attack." + this.getDamageType() + ".kill",
						new Object[] { p_151519_1_.getDisplayName() });
	}

}