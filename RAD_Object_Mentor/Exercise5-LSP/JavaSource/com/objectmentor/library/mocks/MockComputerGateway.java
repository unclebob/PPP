package com.objectmentor.library.mocks;

import com.objectmentor.library.offline.InMemoryComputerGateway;


public class MockComputerGateway extends InMemoryComputerGateway {

	public String lastMacAddressAdded;
	public boolean shouldSayComputerFoundOnNetwork = true;

	public void acceptComputer(String macAddress, String host,
			String description) {
		lastMacAddressAdded = macAddress;
    super.acceptComputer(macAddress, host, description);
	}

	public boolean isComputerOnNetwork(String macAddress) {
		return shouldSayComputerFoundOnNetwork;
	}



}
