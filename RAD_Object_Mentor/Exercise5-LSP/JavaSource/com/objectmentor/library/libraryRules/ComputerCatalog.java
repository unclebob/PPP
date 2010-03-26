package com.objectmentor.library.libraryRules;

import com.objectmentor.library.gateways.*;

public class ComputerCatalog {
  protected ComputerGateway computerGateway;

  public ComputerCatalog(ComputerGateway computerGateway) {
    this.computerGateway = computerGateway;
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
}
