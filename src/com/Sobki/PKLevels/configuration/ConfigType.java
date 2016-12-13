package com.Sobki.PKLevels.configuration;

public enum ConfigType {
	
	DEFAULT("config.yml"),
	LANGUAGE("Language.yml");
	
	private String string;
	
	ConfigType(String string) {
		this.string = string;
	}
	
	public String toString() {
		return this.string;
	}
}