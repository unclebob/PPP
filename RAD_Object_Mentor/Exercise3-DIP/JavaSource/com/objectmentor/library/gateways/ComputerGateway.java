package com.objectmentor.library.gateways;

import java.util.HashMap;
import java.util.Map;

import com.objectmentor.library.models.Computer;

public class ComputerGateway {
  private Map<String, Computer> computers = new HashMap<String, Computer>();

	//FOR TESTING
  public boolean shouldSayComputerFoundOnNetwork;
	public Object lastMacAddressAdded;

  public void acceptComputer(String macAddress, String host, String description) {
  	//FOR TESTING
  	lastMacAddressAdded = macAddress;
    
  	computers.put(macAddress, new Computer(macAddress, host, description));
  }

  public Computer findComputerById(String macAddress) {
    return computers.get(macAddress);
  }

  public Computer findComputerByMacAddress(String macAddress) {
    return findComputerById(macAddress);
  }

  public void clear() {
    computers.clear();
  }

  public boolean isComputerOnNetwork(String macAddress) {
  	//FOR TESTING
    return shouldSayComputerFoundOnNetwork;
  }


}