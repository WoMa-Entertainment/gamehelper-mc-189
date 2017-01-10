package de.winston.network.playerranks;

import net.wfoas.gh.dropsapi.pdr.ChatColor;

public enum PlayerRank {
	ADMIN(6, ChatColor.RED.toString() + "Admin|", null), OWNER(8, ChatColor.DARK_RED.toString() + "Owner|",
			null), ADMIN_DEV(7, ChatColor.RED.toString() + "Admin|" + ChatColor.GREEN,
					ChatColor.AQUA.toString() + "|Dev"), OWNER_DEV(9,
							ChatColor.DARK_RED.toString() + "Owner|" + ChatColor.GREEN,
							ChatColor.AQUA.toString() + "|Dev"), PREMIUM(1, ChatColor.GOLD.toString(), null), YT(2,
									ChatColor.DARK_PURPLE.toString(), null), NORMAL(0, ChatColor.GREEN.toString(),
											null), SUPPORTER(3, ChatColor.BLUE.toString() + "Sup|", null), MODERATOR(4,
													ChatColor.RED.toString() + "Mod|",
													null), DEV(5, ChatColor.AQUA + "Dev|", null);
	final int sqlvalue;
	final String prefix, suffix;

	PlayerRank(int sqlvalue, String prefix, String suffix) {
		this.sqlvalue = sqlvalue;
		this.prefix = prefix;
		this.suffix = suffix;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public int getSQLValue() {
		return sqlvalue;
	}

	public static PlayerRank resolveFromInt(int fromsql) {
		for (PlayerRank pr : PlayerRank.values()) {
			if (pr.sqlvalue == fromsql) {
				return pr;
			}
		}
		return PlayerRank.NORMAL;
	}

	public static PlayerRank parsePlayerRank(int sqlvalue) {
		return resolveFromInt(sqlvalue);
	}

	public static PlayerRank parseFromString(String s) {
		for (PlayerRank rank : PlayerRank.values()) {
			if (String.valueOf(rank).equalsIgnoreCase(s)) {
				if (s.equalsIgnoreCase(String.valueOf(rank)))
					return rank;
			}
		}
		return null;
	}
}