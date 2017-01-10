package de.winston.network.sql;

import java.sql.SQLException;

import net.minecraft.server.MinecraftServer;
import net.wfoas.gh.GameHelper;

public class MySQLUtils {
	// public static final String HOST = "localhost", USER = "root", PASS =
	// "WinPlay02Net+";
	// public static final short PORT = 3306;
	public static MySQL SQL_DB = null;

	public static boolean isConnected() {
		return SQL_DB != null && SQL_DB.isConnected();
	}

	public static void disconnect() {
		if (SQL_DB != null)
			SQL_DB.disconnect();
	}

	public static void connect(SQLMod sqlb) {
		if (SQL_DB != null) {
			try {
				SQL_DB.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			SQL_DB = null;
		}
		SQL_DB = new MySQL(GameHelper.instance.CONFIG.getString("sql-address"),
				GameHelper.instance.CONFIG.getString("sql-port"), sqlb.getSQLDataBaseName(),
				GameHelper.instance.CONFIG.getString("sql-user"), GameHelper.instance.CONFIG.getString("sql-pass"));
		try {
			SQL_DB.openConnection();
			System.out.println("[MySQL] connected");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("[MySQL] connection error");
			MinecraftServer.getServer()
					.logSevere("The Mod GameHelper" + GameHelper.MODVER
							+ " could not connect to the MySQL-Server with the credentials: User: "
							+ GameHelper.instance.CONFIG.getString("sql-user") + " Pass: <censored>.");
			MinecraftServer.getServer().logSevere("Please double-check your credentials.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void createSomeTables() {
		try {// UUID lengh in string = 36
				// SQL_DB.update("CREATE TABLE IF NOT EXISTS autonick (UUID
				// VARCHAR(36),NICK BOOLEAN)");//
				// SQL_DB.update("CREATE TABLE IF NOT EXISTS coins (UUID
				// VARCHAR(36),COINS INT)");
			SQL_DB.update("CREATE TABLE IF NOT EXISTS ranks (UUID VARCHAR(36),Rank INT)");
			// SQL_DB.update("CREATE TABLE IF NOT EXISTS playervisibility (UUID
			// VARCHAR(36),Visible INT)");
			// SQL_DB.update("CREATE TABLE IF NOT EXISTS spawnlocations (locname
			// VARCHAR(50),x FLOAT,y FLOAT,z FLOAT)");
			// SQL_DB.update(
			// "CREATE TABLE IF NOT EXISTS friend_settings (UUID
			// VARCHAR(36),friendrequests BOOLEAN,partyrequests
			// BOOLEAN,private_messages BOOLEAN,tptoplayerserver
			// BOOLEAN,online_offline_msgs BOOLEAN,clanrequests BOOLEAN)");
			// SQL_DB.update("CREATE TABLE IF NOT EXISTS friends (UUID
			// VARCHAR(36),friends VARCHAR(18000))");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
