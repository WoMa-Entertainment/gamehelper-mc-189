package de.winston.network.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL extends SQLDB {
	private final String user;
	private final String database;
	private final String password;
	private final String port;
	private final String hostname;

	public MySQL(String hostname, String port, String username, String password) {
		this(hostname, port, null, username, password);
	}

	public MySQL(String hostname, String port, String database, String username, String password) {
		this.hostname = hostname;
		this.port = port;
		this.database = database;
		this.user = username;
		this.password = password;
	}

	@Override
	public Connection openConnection() throws SQLException, ClassNotFoundException {
		if (checkConnection()) {
			return connection;
		}

		String connectionURL = "jdbc:mysql://" + this.hostname + ":" + this.port;
		// if (database != null) {
		// connectionURL = connectionURL + "/" + this.database;
		// }
		// System.out.println(connectionURL);
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(connectionURL, this.user, this.password);
		this.update("CREATE DATABASE IF NOT EXISTS " + database);
		connection.setCatalog(database);
		return connection;
	}

}