package de.winston.network.sql;

import net.wfoas.gh.GameHelper;

public class SQLServer implements SQLMod {

	@Override
	public String getSQLDataBaseName() {
		return GameHelper.instance.CONFIG.getString("db-name");
	}

}
