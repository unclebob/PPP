package com.objectmentor.library.offline;

import com.objectmentor.library.application.gateways.ComputerGateway;
import com.objectmentor.library.application.models.Computer;

import java.util.*;

public class InMemoryComputerGateway implements ComputerGateway {
  private Map computers = new HashMap();

  public void acceptComputer(String macAddress, String host, String description) {
    computers.put(macAddress, new Computer(macAddress, host, description));
  }

  public Computer findComputerById(String macAddress) {
    return (Computer) computers.get(macAddress);
  }

  public Computer findComputerByMacAddress(String macAddress) {
    return findComputerById(macAddress);
  }

  public void clear() {
    computers.clear();
  }

  public boolean isComputerOnNetwork(String macAddress) {
    //TODO - IMPLMEMENT ME!
    return false;
  }


}