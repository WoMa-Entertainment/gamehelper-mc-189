package net.wfoas.gh.config;

import java.util.HashMap;
import java.util.Map;

public class DefaultConfig extends IGHConfigDefault implements GHConfigKey {

	Map<String, String> properties;

	public DefaultConfig() {
		properties = new HashMap<String, String>();
		properties.put(USE_SQL, String.valueOf(false));
		properties.put(DB_NAME, "gamehelper");
		properties.put(SQL_ADDRESS, "localhost");
		properties.put(SQL_PORT, "3306");
		properties.put(SQL_USER, "root");
		properties.put(SQL_PASS, "");
		properties.put(REQ_LOGIN, String.valueOf(false)); // PlayerConnectedSecuredLoginTimeOut
		properties.put(LOGIN_PASS, "0000");
		properties.put(USE_TABLIST_HEADER_FOOTER, String.valueOf(false));
		properties.put(TABLIST_HEADER, "gamehelper.errormsg.noheader");
		properties.put(TABLIST_FOOTER, "gamehelper.errormsg.nofooter");
	}

	@Override
	public Map<String, String> getPropertiesMap() {
		return properties;
	}

}
