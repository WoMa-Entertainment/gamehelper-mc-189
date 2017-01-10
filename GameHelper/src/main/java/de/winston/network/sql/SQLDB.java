package de.winston.network.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public abstract class SQLDB {

	protected Connection connection;

	protected SQLDB() {
		this.connection = null;
	}

	public abstract Connection openConnection() throws SQLException, ClassNotFoundException;

	public boolean checkConnection() throws SQLException {
		return connection != null && !connection.isClosed();
	}

	public boolean isConnected() {
		try {
			return checkConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public boolean closeConnection() throws SQLException {
		if (connection == null) {
			return false;
		}
		if (isConnected()) {
			connection.close();
			System.out.println("[MySQL] disconnected");
			return true;
		}
		return false;
	}

	public boolean disconnect() {
		try {
			return closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public ResultSet querySQL(String query) throws SQLException, ClassNotFoundException {
		if (!checkConnection()) {
			openConnection();
		}

		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(query);
		return result;
	}

	public int updateSQL(String query) throws SQLException, ClassNotFoundException {
		if (!checkConnection()) {
			openConnection();
		}

		Statement statement = connection.createStatement();
		int result = statement.executeUpdate(query);
		statement.close();
		return result;
	}
	
	public void update(String qry) throws SQLException, ClassNotFoundException{
		updateSQL(qry);
	}
	
	public ResultSet getResult(String qry) throws SQLException, ClassNotFoundException{
		return querySQL(qry);
	}
}