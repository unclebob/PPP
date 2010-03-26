package com.objectmentor.library.application.gateways;

import com.objectmentor.library.application.models.Computer;

public interface ComputerGateway {

  void acceptComputer(String mac, String host, String description);

  boolean isComputerOnNetwork(String macAddress);

  Computer findComputerById(String string);

  Computer findComputerByMacAddress(String macAddress);

  void clear();
}
