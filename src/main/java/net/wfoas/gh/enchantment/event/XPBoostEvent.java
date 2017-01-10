package net.wfoas.gh.enchantment.event;

import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.GameHelperCoreModule;

public class XPBoostEvent {

	@SubscribeEvent
	public void blockDestroy(BlockEvent.BreakEvent hde) {
		if (!hde.world.isRemote)
			if (GameHelper.getUtils().hasEnchantment(hde.getPlayer().getHeldItem(), GameHelperCoreModule.ENCH_XPBOOST))
				hde.setExpToDrop((int) (hde.getExpToDrop() + 1.25f * hde.getExpToDrop() * GameHelper.getUtils()
						.getEnchantmentLevel(hde.getPlayer().getHeldItem(), GameHelperCoreModule.ENCH_XPBOOST)));
	}

	@SubscribeEvent
	public void entityDeath(LivingExperienceDropEvent hde) {
		if (!hde.entity.worldObj.isRemote)
			if (hde.getAttackingPlayer() != null)
				if (GameHelper.getUtils().hasEnchantment(hde.getAttackingPlayer().getHeldItem(),
						GameHelperCoreModule.ENCH_XPBOOST))
					hde.setDroppedExperience((int) (hde.getOriginalExperience()
							+ 1.25f * hde.getOriginalExperience() * GameHelper.getUtils().getEnchantmentLevel(
									hde.getAttackingPlayer().getHeldItem(), GameHelperCoreModule.ENCH_XPBOOST)));
	}
}
