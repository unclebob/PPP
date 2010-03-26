package com.objectmentor.library.application.libraryRules;

import com.objectmentor.library.application.gateways.*;

public class ComputerCatalog {
  private ComputerGateway computerGateway;

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
