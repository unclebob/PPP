package com.objectmentor.library.web.controller;

import junit.framework.TestCase;

import com.objectmentor.library.gateways.IsbnDoesNotExistException;
import com.objectmentor.library.libraryRules.Library;

public class ApplicationTest extends TestCase {
  private Library library;
  private Application application;

  protected void setUp() throws Exception {
    super.setUp();
    application = new Application();
    library = application.getLibrary();
  }

  public void testGetLibraryNeverReturnsNull() {
    assertNotNull(library);
  }

  public void testAcceptBookThrowsIsbnDoesNotExistExceptionIfIsbnIsNull() {
    try {
      library.acceptBook(null);
      fail();
    } catch (IsbnDoesNotExistException e) {
    }
  }

  public void testAcceptBookThrowsIsbnDoesNotExistExceptionIfIsbnIsEmpty() {
    try {
      library.acceptBook("");
      fail();
    } catch (IsbnDoesNotExistException e) {
    }
  }

  public void testAcceptBookWithValidIsbn() {
    library.acceptBook("0679600841"); //War and Peace
    assertEquals(1, application.getMediaGateway().mediaCount());
  }

}
