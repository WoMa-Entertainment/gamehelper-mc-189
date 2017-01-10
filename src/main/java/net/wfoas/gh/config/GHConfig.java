package net.wfoas.gh.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.Properties;

import net.wfoas.gh.GameHelper;

public class GHConfig extends Properties {
	final File cfgFile;

	public GHConfig(File configFile) {
		super();
		BufferedReader fis;
		cfgFile = configFile;
		if (!cfgFile.exists()) {
			try {
				cfgFile.createNewFile();
				setDefault(GameHelper.instance.defaultConfig);
				save();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			fis = new BufferedReader(
					new InputStreamReader(new FileInputStream(configFile), java.nio.charset.StandardCharsets.UTF_8));
			super.load(fis);
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				GHConfig.this.close();
			}
		}));
	}

	public void setDefault(IGHConfigDefault ghcdf) {
		for (Map.Entry<String, String> entries : ghcdf.getPropertiesMap().entrySet()) {
			setString(entries.getKey(), entries.getValue());
		}

	}

	public void setString(String key, String value) {
		super.setProperty(key, value);
	}

	public void setBoolean(String key, boolean value) {
		super.setProperty(key, String.valueOf(value));
	}

	public void setInt(String key, int value) {
		super.setProperty(key, String.valueOf(value));
	}

	public void setShort(String key, short value) {
		super.setProperty(key, String.valueOf(value));
	}

	public void setLong(String key, long value) {
		super.setProperty(key, String.valueOf(value));
	}

	public void setFloat(String key, float value) {
		super.setProperty(key, String.valueOf(value));
	}

	public void setDouble(String key, double value) {
		super.setProperty(key, String.valueOf(value));
	}

	public void setByte(String key, byte value) {
		super.setProperty(key, String.valueOf(value));
	}

	public void setChar(String key, char value) {
		setByte(key, (byte) value);
	}

	public boolean getBoolean(String key) {
		return Boolean.parseBoolean(super.getProperty(key));
	}

	public int getInt(String key) {
		return Integer.parseInt(super.getProperty(key));
	}

	public short getShort(String key) {
		return Short.parseShort(super.getProperty(key));
	}

	public long getLong(String key) {
		return Long.parseLong(super.getProperty(key));
	}

	public float getFloat(String key) {
		return Float.parseFloat(super.getProperty(key));
	}

	public double getDouble(String key) {
		return Double.parseDouble(super.getProperty(key));
	}

	public byte getByte(String key) {
		return Byte.parseByte(super.getProperty(key));
	}

	public char getChar(String key) {
		return (char) getByte(key);
	}

	public String getString(String key) {
		return getProperty(key);
	}

	public boolean useSQL() {
		return getBoolean("sql");
	}

	public void setSQL(boolean use) {
		setBoolean("sql", use);
	}

	public void save() {
		BufferedWriter fos;
		try {
			fos = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(cfgFile), java.nio.charset.StandardCharsets.UTF_8));
			super.store(fos, "GameHelper Config");
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		save();
	}

	public void closeAndDoNotSave() {
	}
}
