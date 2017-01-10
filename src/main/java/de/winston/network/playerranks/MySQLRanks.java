package de.winston.network.playerranks;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import de.winston.network.sql.MySQLUtils;
import net.minecraft.entity.player.EntityPlayer;

public class MySQLRanks {
	public static boolean playerExists(UUID uuid) {
		try {
			PreparedStatement ps = MySQLUtils.SQL_DB.getConnection()
					.prepareStatement("SELECT Rank FROM ranks WHERE UUID = ?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ps.close();
				rs.close();
				return true;
			} else {
				ps.close();
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void updatePlayer(UUID uuid, PlayerRank pr) {
		if (playerExists(uuid)) {
			try {
				PreparedStatement ps = MySQLUtils.SQL_DB.getConnection()
						.prepareStatement("UPDATE ranks SET Rank = ? WHERE UUID = ?");
				ps.setInt(1, pr.sqlvalue);
				ps.setString(2, uuid.toString());
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				PreparedStatement ps = MySQLUtils.SQL_DB.getConnection()
						.prepareStatement("INSERT INTO ranks (UUID,Rank) VALUES (?,?)");
				ps.setString(1, uuid.toString());
				ps.setInt(2, pr.sqlvalue);
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static PlayerRank getRank(UUID uuid) {
		try {
			PreparedStatement ps = MySQLUtils.SQL_DB.getConnection()
					.prepareStatement("SELECT Rank FROM ranks WHERE UUID = ?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int i = rs.getInt("Rank");
				ps.close();
				rs.close();
				return PlayerRank.parsePlayerRank(i);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return PlayerRank.NORMAL;
	}

	public static void delete(UUID uuid) {
		if (playerExists(uuid)) {
			try {
				PreparedStatement ps = MySQLUtils.SQL_DB.getConnection()
						.prepareStatement("DELETE * FROM ranks WHERE UUID = ?");
				ps.setString(1, uuid.toString());
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("Spieler: " + uuid.toString() + " existiert nicht!");
			return;
		}
	}

	// public static PlayerRank getRanks(Player player){
	// Ranks r = GameHelper.RANK_MAP.get(player.getName());
	// if(r != null)
	// return r;
	// GameHelper.RANK_MAP.put(player.getName(), Ranks.NORMAL);
	// r = GameHelper.RANK_MAP.get(player.getName());
	// return r;
	// }

	public static void changeName(EntityPlayer player) {
		// // ScoreboardManager scm = Bukkit.getServer().getScoreboardManager();
		// Scoreboard scoreboard =
		// MinecraftServer.getServer().getEntityWorld().getScoreboard();
		// ScorePlayerTeam team =
		// player.getWorldScoreboard().getPlayersTeam(player.getName());
		// if (team != null)
		// player.getWorldScoreboard().removePlayerFromTeam(player.getName(),
		// team);
		// ScorePlayerTeam t2 = scoreboard.getTeam(String.valueOf(r));
		// if (t2 == null) {
		//// scoreboard.registerNewTeam(String.valueOf(r));
		// t2 = scoreboard.getTeam(String.valueOf(r));
		// if (r.prefix != null)
		// t2.setNamePrefix(r.prefix);
		// if (r.suffix != null)
		// t2.setNameSuffix(r.suffix);
		// }
		// player.setDisplayName(r.prefix + player.getName() + (r.suffix != null
		// ? r.suffix : ""));
		// PlayerRank r = getRank(player.getUniqueID());
		// if (r.getSQLValue() >= PlayerRank.DEV.getSQLValue()) {
		// GameHelper.setOp(player);
		// }
		//// // ScoreboardManager scm =
		// Bukkit.getServer().getScoreboardManager();
		//// Scoreboard scoreboard =
		// MinecraftServer.getServer().getEntityWorld().getScoreboard();
		//// ScorePlayerTeam team =
		// player.getWorldScoreboard().getPlayersTeam(player.getName());
		//// if (team != null)
		//// player.getWorldScoreboard().removePlayerFromTeam(player.getName(),
		// team);
		//// ScorePlayerTeam t2 = scoreboard.getTeam(String.valueOf(r));
		//// if (t2 == null) {
		////// scoreboard.registerNewTeam(String.valueOf(r));
		//// t2 = scoreboard.getTeam(String.valueOf(r));
		//// if (r.prefix != null)
		//// t2.setNamePrefix(r.prefix);
		//// if (r.suffix != null)
		//// t2.setNameSuffix(r.suffix);
		//// }
		//// player.setDisplayName(r.prefix + player.getName() + (r.suffix !=
		// null ? r.suffix : ""));
		// player.setCustomNameTag(r.prefix + player.getName() + (r.suffix !=
		// null ? r.suffix : ""));
		player.refreshDisplayName();
		// t2.addPlayer(player);
	}

	// @SuppressWarnings("deprecation")
	// private static void setRankName(Player player){
	// if(!(GameHelper.RANK_MAP.containsKey(player.getName()))){
	//// Bukkit.broadcastMessage("retu ");
	// GameHelper.RANK_MAP.put(player.getName(), Ranks.NORMAL);
	// return;
	// }
	// for(Ranks rank : Ranks.values()){
	// if(GameHelper.RANK_MAP.get(player) == rank){
	// Scoreboard scoreboard =
	// Bukkit.getScoreboardManager().getMainScoreboard();
	// Team team = scoreboard.getTeam(String.valueOf(rank));
	// if(team == null){
	// team = scoreboard.registerNewTeam(String.valueOf(rank));
	// team.setPrefix(rank.prefix);
	// team.setSuffix(rank.suffix);
	// team.setCanSeeFriendlyInvisibles(false);
	// }
	// team.setPrefix(rank.prefix);
	//// Bukkit.broadcastMessage("finally " + String.valueOf(rank));
	// team.addPlayer(player);
	//// player.setPlayerListName(rank.prefix + player.getName());
	// player.setDisplayName(rank.prefix + player.getName() + rank.suffix);
	// return;
	// }
	// }
	// }

	// @SuppressWarnings("unused")
	// @Deprecated
	// private static void setNameOP(Player player) {
	// Scoreboard scoreboard = Bukkit.getScoreboardManager()
	// .getMainScoreboard();
	// Team team = scoreboard.getTeam("OP");
	// if (team == null) {
	// team = scoreboard.registerNewTeam("OP");
	// team.setPrefix(ChatColor.DARK_RED + "Admin | ");
	// team.setSuffix("");
	// team.setAllowFriendlyFire(true);
	// team.setCanSeeFriendlyInvisibles(false);
	// }
	//
	// team.addPlayer(player);
	// }

	// @SuppressWarnings("unused")
	// @Deprecated
	// private static void setNameNOP(Player player){
	// Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
	// Team team = scoreboard.getTeam(String.valueOf(Ranks.NORMAL));
	// if (team == null) {
	// team = scoreboard.registerNewTeam(String.valueOf(Ranks.NORMAL));
	// team.setPrefix(ChatColor.GREEN + "");
	// team.setSuffix("");
	// team.setAllowFriendlyFire(true);
	// team.setCanSeeFriendlyInvisibles(false);
	// }
	//
	// team.addPlayer(player);
	// }

	// public static void changeName(Player player){
	// setRankName(player);
	// }
}
