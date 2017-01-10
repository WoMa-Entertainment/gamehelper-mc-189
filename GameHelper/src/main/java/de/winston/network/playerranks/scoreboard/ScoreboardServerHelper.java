package de.winston.network.playerranks.scoreboard;

import de.winston.network.playerranks.PlayerRank;
import net.minecraft.scoreboard.ScorePlayerTeam;

public class ScoreboardServerHelper {

	public void registerTeam(ScorePlayerTeam spt, PlayerRank pr) {
		registerTeam(spt, pr.getPrefix(), pr.getSuffix());
	}

	public void registerTeam(ScorePlayerTeam spt, String prefix, String suffix) {
		if (prefix != null)
			spt.setNamePrefix(prefix);
		if (suffix != null)
			spt.setNameSuffix(suffix);
		spt.setSeeFriendlyInvisiblesEnabled(false);
		spt.setAllowFriendlyFire(false);
	}
}
