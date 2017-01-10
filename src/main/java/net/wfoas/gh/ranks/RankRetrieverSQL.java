package net.wfoas.gh.ranks;

import de.winston.network.playerranks.MySQLRanks;
import de.winston.network.playerranks.PlayerRank;
import net.minecraft.entity.player.EntityPlayerMP;

public class RankRetrieverSQL implements IRankRetriever {

	@Override
	public PlayerRank getRank(EntityPlayerMP player) {
		return MySQLRanks.getRank(player.getUniqueID());
	}

	@Override
	public void setRank(EntityPlayerMP player, PlayerRank rank) {
		MySQLRanks.updatePlayer(player.getUniqueID(), rank);
	}

	@Override
	public void save() {
		return;
	}

}
