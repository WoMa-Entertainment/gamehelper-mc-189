package net.wfoas.gh.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.wfoas.gh.multipleworlds.GHWorld;

@Cancelable
public class PlayerChangeWorldEvent extends PlayerChangeDimensionEvent {

	public PlayerChangeWorldEvent(EntityPlayer player, GHWorld worldFrom, GHWorld worldTo) {
		super(player, worldFrom.dimensionId, worldTo);
	}

}
