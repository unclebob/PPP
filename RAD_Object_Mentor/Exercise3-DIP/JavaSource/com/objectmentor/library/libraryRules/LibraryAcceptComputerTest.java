package com.objectmentor.library.libraryRules;

import junit.framework.TestCase;

import com.objectmentor.library.gateways.ComputerDoesNotExistException;
import com.objectmentor.library.models.Computer;
import com.objectmentor.library.web.controller.Application;

public class LibraryAcceptComputerTest extends TestCase {
  Application application;
  ComputerCatalog computerCatalog;

  protected void setUp() throws Exception {
    application = new Application();
    computerCatalog = application.getComputerCatalog();
  }

  public void testShouldAddIfFoundOnNetwork() {
    computerCatalog.getComputerGateway().shouldSayComputerFoundOnNetwork = true;
    computerCatalog.acceptComputer("mac", "host", "description");
    assertEquals(computerCatalog.getComputerGateway().lastMacAddressAdded, "mac");
  }

  public void testShouldInformIfNotFoundOnNetwork() {
  	computerCatalog.getComputerGateway().shouldSayComputerFoundOnNetwork = false;
    assertFalse(computerCatalog.canAcceptComputer("mac", "host", "description"));
  }

  public void testShouldComplainIfIfNotFoundOnNetwork() throws Exception {
  	computerCatalog.getComputerGateway().shouldSayComputerFoundOnNetwork = false;
    assertFalse(computerCatalog.canAcceptComputer("mac", "host", "description"));
    try {
      computerCatalog.acceptComputer("mac", "host", "description");
      fail();
    }
    catch (ComputerDoesNotExistException e) {
    }
  }

  public void testShouldStoreInComputerGatewayOnAdd() {
    computerCatalog.getComputerGateway().shouldSayComputerFoundOnNetwork = true;
    computerCatalog.acceptComputer("mac", "host", "description");
    Computer foundComputer = computerCatalog.getComputerGateway().findComputerById("mac");
    assertEquals("mac", foundComputer.getMacAddress());
    assertEquals("host", foundComputer.getHostName());
    assertEquals("description", foundComputer.getDescription());
  }
}
