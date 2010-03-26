package com.objectmentor.library.libraryRules;

import com.objectmentor.library.gateways.ComputerDoesNotExistException;
import com.objectmentor.library.gateways.ComputerGateway;

public class ComputerCatalog {
  protected ComputerGateway computerGateway;

  public ComputerCatalog() {
    this.computerGateway = new ComputerGateway();
  }

  public boolean canAcceptComputer(String macAddress,
                                   String hostName,
                                   String description) {
    return computerGateway.isComputerOnNetwork(macAddress);
  }

  public void acceptComputer(String macAddress,
                             String hostName,
                             String description) {
    if (!computerGateway.isComputerOnNetwork(macAddress))
      throw new ComputerDoesNotExistException();
    computerGateway.acceptComputer(macAddress, hostName, description);
  }
  
  public ComputerGateway getComputerGateway() {
  	return computerGateway;
  }
}
