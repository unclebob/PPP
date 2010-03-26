package com.objectmentor.library.web.controller;

import junit.framework.TestCase;

import com.objectmentor.library.gateways.IsbnDoesNotExistException;
import com.objectmentor.library.gateways.MediaGateway;
import com.objectmentor.library.gateways.PatronGateway;
import com.objectmentor.library.libraryRules.Library;
import com.objectmentor.library.mocks.CardPrinter;
import com.objectmentor.library.mocks.MockCardPrinter;
import com.objectmentor.library.mocks.MockCompactDiscService;
import com.objectmentor.library.mocks.MockComputerGateway;
import com.objectmentor.library.mocks.MockIsbnService;
import com.objectmentor.library.mocks.MockMediaGateway;
import com.objectmentor.library.mocks.MockPatronGateway;
import com.objectmentor.library.models.Book;
import com.objectmentor.library.models.Media;

public class ApplicationTest extends TestCase {
  private MockIsbnService isbnService;
  private MockCompactDiscService compactDiscService;
  private MediaGateway mediaGateway;
  private MockComputerGateway computerGateway;
  private Library library;
	private Application application;

  protected void setUp() throws Exception {
    super.setUp();
    isbnService = new MockIsbnService();
    compactDiscService = new MockCompactDiscService();
    mediaGateway = new MockMediaGateway();
    PatronGateway patronGateway = new MockPatronGateway();
    CardPrinter cardPrinter = new MockCardPrinter();
    computerGateway = new MockComputerGateway();
    application = new Application(isbnService, compactDiscService, mediaGateway, patronGateway, cardPrinter, computerGateway);
		library = application.getLibrary();
  }

  public void testGetLibraryNeverReturnsNull() {
    assertNotNull(library);
  }

  public void testGetMediaGatewayWithEmptyConstructor(){
		assertEquals(mediaGateway, application.getMediaGateway());
  	
  }
  
  public void testAcceptBookThrowsIsbnDoesNotExistExceptionIfIsbnIsNull() {
    try {
      library.acceptBook(null);
      fail();
    }
    catch (IsbnDoesNotExistException e) {}
  }

  public void testAcceptBookThrowsIsbnDoesNotExistExceptionIfIsbnIsEmpty() {
    try {
      library.acceptBook("");
      fail();
    }
    catch (IsbnDoesNotExistException e) {}
  }

  public void testAcceptBookWithValidIsbn() {
    Media book = new Book("ISBN", "War and Peace", "Tolstoy");
    isbnService.setBookToReturn(book);
    library.acceptBook("ISBN");
    assertEquals(1, mediaGateway.mediaCount());
  }
}
