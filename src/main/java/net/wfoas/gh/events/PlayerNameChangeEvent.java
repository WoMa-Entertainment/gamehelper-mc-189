package net.wfoas.gh.events;

import de.winston.network.playerranks.MySQLRanks;
import de.winston.network.playerranks.PlayerRank;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.wfoas.gh.GameHelper;

public class PlayerNameChangeEvent {

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void refreshPlayerName(PlayerEvent.NameFormat name) {
		// if (GameHelper.EVENT_SIDE == Side.SERVER &&
		// GameHelper.instance.CONFIG.getBoolean("sql")) {
		// PlayerRank r = MySQLRanks.getRank(name.entityPlayer.getUniqueID());
		// if (r.getSQLValue() >= PlayerRank.DEV.getSQLValue()) {
		// GameHelper.getUtils().setOp(name.entityPlayer);
		// }
		// name.displayname = r.getPrefix() + name.entityPlayer.getName()
		// + (r.getSuffix() != null ? r.getSuffix() : "");
		//
		// }
		if (!(name.entityPlayer instanceof EntityPlayerMP))
			return;
		// PlayerRank r =
		name.displayname = GameHelper.getUtils().getRankFormattedName((EntityPlayerMP) name.entityPlayer);
		// name.displayname = r.getPrefix() + name.entityPlayer.getName() +
		// (r.getSuffix() != null ? r.getSuffix() : "");
	}
}
