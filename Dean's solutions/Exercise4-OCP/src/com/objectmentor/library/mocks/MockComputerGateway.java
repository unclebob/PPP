package com.objectmentor.library.mocks;

import java.util.HashMap;
import java.util.Map;

import com.objectmentor.library.gateways.ComputerGateway;
import com.objectmentor.library.models.Computer;

public class MockComputerGateway implements ComputerGateway {

	public String lastMacAddressAdded;
	public boolean shouldSayComputerFoundOnNetwork = true;
	private Map computers = new HashMap();

	public void acceptComputer(String macAddress, String host,
			String description) {
		lastMacAddressAdded = macAddress;
		computers.put(macAddress, new Computer(macAddress, host, description));
	}

	public boolean isComputerOnNetwork(String macAddress) {
		return shouldSayComputerFoundOnNetwork;
	}

	public Computer findComputerById(String macAddress) {
		return (Computer) computers.get(macAddress);
	}

	public Computer findComputerByMacAddress(String macAddress) {
		return findComputerById(macAddress);
	}

}
