package com.objectmentor.library.libraryRules;

import com.objectmentor.library.application.Application;
import com.objectmentor.library.gateways.ComputerDoesNotExistException;
import com.objectmentor.library.mocks.MockComputerGateway;
import com.objectmentor.library.models.Computer;
import com.objectmentor.library.web.controller.MockServiceProvider;
import junit.framework.TestCase;

public class LibraryAcceptComputerTest extends TestCase {
  Application application;
  ComputerCatalog computerCatalog;
  private MockComputerGateway computerGateway;

  protected void setUp() throws Exception {
    application = new Application(new MockServiceProvider());
    computerCatalog = application.getComputerCatalog();
    computerGateway = (MockComputerGateway) application.getComputerGateway();
  }

  public void testShouldAddIfFoundOnNetwork() {
    computerGateway.shouldSayComputerFoundOnNetwork = true;
    computerCatalog.acceptComputer("mac", "host", "description");
    assertEquals(computerGateway.lastMacAddressAdded, "mac");
  }

  public void testShouldInformIfNotFoundOnNetwork() {
    computerGateway.shouldSayComputerFoundOnNetwork = false;
    assertFalse(computerCatalog.canAcceptComputer("mac", "host", "description"));
  }

  public void testShouldComplainIfIfNotFoundOnNetwork() throws Exception {
    computerGateway.shouldSayComputerFoundOnNetwork = false;
    assertFalse(computerCatalog.canAcceptComputer("mac", "host", "description"));
    try {
      computerCatalog.acceptComputer("mac", "host", "description");
      fail();
    }
    catch (ComputerDoesNotExistException e) {
    }
  }

  public void testShouldStoreInComputerGatewayOnAdd() {
    computerGateway.shouldSayComputerFoundOnNetwork = true;
    computerCatalog.acceptComputer("mac", "host", "description");
    Computer foundComputer = application.getComputerGateway().findComputerById("mac");
    assertEquals("mac", foundComputer.getMacAddress());
    assertEquals("host", foundComputer.getHostName());
    assertEquals("description", foundComputer.getDescription());
  }
}
