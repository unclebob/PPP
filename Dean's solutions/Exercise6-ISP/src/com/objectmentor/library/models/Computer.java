package com.objectmentor.library.models;

public class Computer {

	private final String macAddress;

	private final String hostName;

	private final String description;

	public Computer(String macAddress, String host, String description) {
		this.macAddress = macAddress;
		this.hostName = host;
		this.description = description;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public String getHostName() {
		return hostName;
	}

	public String getDescription() {
		return description;
	}

	public String getId() {
		return this.getMacAddress();
	}

}
