package net.wfoas.gh.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.wfoas.gh.multipleworlds.GHWorld;

public class PlayerDimensionTeleportEvent extends PlayerEvent {

	public final int dimensionFrom;

	public final GHWorld worldTo;

	public PlayerDimensionTeleportEvent(EntityPlayer player, int dimFrom, GHWorld dimTo) {
		super(player);
		this.dimensionFrom = dimFrom;
		this.worldTo = dimTo;
	}

}
