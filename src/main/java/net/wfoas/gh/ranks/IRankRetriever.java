package net.wfoas.gh.ranks;

import de.winston.network.playerranks.PlayerRank;
import net.minecraft.entity.player.EntityPlayerMP;

public interface IRankRetriever {
	public PlayerRank getRank(EntityPlayerMP player);

	public void setRank(EntityPlayerMP player, PlayerRank rank);

	public void save();
}
