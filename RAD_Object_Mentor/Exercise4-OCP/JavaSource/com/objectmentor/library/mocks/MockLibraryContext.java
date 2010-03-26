package com.objectmentor.library.mocks;

import com.objectmentor.library.libraryRules.Library;

/**
 * @deprecated Use new LibraryWithMockServices() instead
 */
public class MockLibraryContext {
  public static MockIsbnService isbnService = new MockIsbnService();
  public static MockMediaGateway mediaGateway = new MockMediaGateway();
  public static MockPatronGateway patronGateway = new MockPatronGateway();
  public static MockCardPrinter cardPrinter = new MockCardPrinter();
  public static MockCompactDiscService compactDiscService = new MockCompactDiscService();
  public static MockComputerGateway computerGateway = new MockComputerGateway();
  public static Library library = new Library(isbnService,
                                              compactDiscService,
                                              mediaGateway,
                                              patronGateway,
                                              cardPrinter,
                                              computerGateway);
}
