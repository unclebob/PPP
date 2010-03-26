package com.objectmentor.library.application;

import junit.framework.TestCase;

import com.objectmentor.library.application.gateways.IsbnDoesNotExistException;
import com.objectmentor.library.application.models.Book;
import com.objectmentor.library.application.models.MediaCopy;
import com.objectmentor.library.mocks.MockIsbnService;
import com.objectmentor.library.mocks.MockMediaGateway;

public class BookCatalogTest extends TestCase {

  private MockIsbnService isbnService;
  private MockMediaGateway mockMediaGateway;
  private BookCatalog catalog;

  protected void setUp() throws Exception {
    isbnService = new MockIsbnService();
    mockMediaGateway = new MockMediaGateway();
    catalog = new BookCatalog(isbnService, mockMediaGateway);
  }

  public void testAddBookShouldLookUpISBN() {
    addBookCopy("ISBN");
    assertEquals("ISBN", isbnService.isbnLastPassedToFind);
  }

  public void testAddBookShouldAddBookReturnedByIsbnService() {
    MediaCopy mediaCopy = addBookCopy("ISBN");
    assertEquals(mockMediaGateway.addedMediaCopy.getMedia(), mediaCopy
      .getMedia());
  }

  public void testUnfoundISBN() {
    try {
      catalog.addBookCopy("NON-EXISTENT ISBN");
      fail();
    }
    catch (IsbnDoesNotExistException e) {
    }
  }

  private MediaCopy addBookCopy(String isbn) {
    isbnService.setBookToReturn(new Book(isbn, "War and Peace", "Tolstoy"));
    return catalog.addBookCopy(isbn);
  }

}
