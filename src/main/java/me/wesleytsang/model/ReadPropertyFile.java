package me.wesleytsang.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertyFile {
	
	protected Properties prop = null;
	
	public ReadPropertyFile() throws IOException {
		prop = new Properties();
		InputStream stream = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\config\\config.properties");
		prop.load(stream);
	}
	
	public String getPath() {
		return prop.getProperty("path");
	}
	
	public String getUserName() {
		return prop.getProperty("userName");
	}

	public String getPassword() {
		return prop.getProperty("password");
	}
	
	public String getMainAM() {
		return prop.getProperty("linkMainAM");
	}
	
	public String getGemAM() {
		return prop.getProperty("linkGemAM");
	}
	
	public String getEnAM() {
		return prop.getProperty("linkEnAM");
	}
	
	public String getMainPM() {
		return prop.getProperty("linkMainPM");
	}
	
	public String getGemPM() {
		return prop.getProperty("linkGemPM");
	}
	
	public String getEnPM() {
		return prop.getProperty("linkEnPM");
	}

	public String getTable() {
		return prop.getProperty("targetTable");
	}
}
