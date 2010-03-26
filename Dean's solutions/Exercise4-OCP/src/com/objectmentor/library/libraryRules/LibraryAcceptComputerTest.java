package com.objectmentor.library.libraryRules;

import com.objectmentor.library.gateways.ComputerDoesNotExistException;
import com.objectmentor.library.models.Computer;
import junit.framework.TestCase;

public class LibraryAcceptComputerTest extends TestCase {
  LibraryWithMockServices library;

  protected void setUp() throws Exception {
    library = new LibraryWithMockServices();
  }

  public void testShouldAddIfFoundOnNetwork() {
    library.getMockComputerGateway().shouldSayComputerFoundOnNetwork = true;
    library.acceptComputer("mac", "host", "description");
    assertEquals(library.getMockComputerGateway().lastMacAddressAdded, "mac");
  }

  public void testShouldInformIfNotFoundOnNetwork() {
    library.getMockComputerGateway().shouldSayComputerFoundOnNetwork = false;
    assertFalse(library.canAcceptComputer("mac", "host", "description"));
  }

  public void testShouldComplainIfIfNotFoundOnNetwork() throws Exception {
    library.getMockComputerGateway().shouldSayComputerFoundOnNetwork = false;
    assertFalse(library.canAcceptComputer("mac", "host", "description"));
    try {
      library.acceptComputer("mac", "host", "description");
      fail();
    }
    catch (ComputerDoesNotExistException e) {
    }
  }

  public void testShouldStoreInComputerGatewayOnAdd() {
    library.getMockComputerGateway().shouldSayComputerFoundOnNetwork = true;
    library.acceptComputer("mac", "host", "description");
    Computer foundComputer = library.getComputerGateway().findComputerById("mac");
    assertEquals("mac", foundComputer.getMacAddress());
    assertEquals("host", foundComputer.getHostName());
    assertEquals("description", foundComputer.getDescription());
  }
}
