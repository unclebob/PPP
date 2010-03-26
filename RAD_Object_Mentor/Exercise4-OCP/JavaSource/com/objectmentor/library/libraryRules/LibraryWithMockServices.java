package com.objectmentor.library.libraryRules;

import com.objectmentor.library.mocks.*;

public class LibraryWithMockServices extends Library {

  public LibraryWithMockServices() {
    super(
      new MockIsbnService(),
      new MockCompactDiscService(),
      new MockMediaGateway(),
      new MockPatronGateway(),
      new MockCardPrinter(),
      new MockComputerGateway()
    );
  }

  public MockIsbnService getMockIsbnService() { return (MockIsbnService) getIsbnService(); }

  public MockCompactDiscService getMockCompactDiscService() { return (MockCompactDiscService) getCompactDiscService(); }

  public MockMediaGateway getMockMediaGateway() { return (MockMediaGateway) getMediaGateway(); }

  public MockPatronGateway getMockPatronGateway() { return (MockPatronGateway) getPatronGateway(); }

  public MockCardPrinter getMockCardPrinter() { return (MockCardPrinter) getCardPrinter(); }

  public MockComputerGateway getMockComputerGateway() { return (MockComputerGateway) getComputerGateway(); }


}
