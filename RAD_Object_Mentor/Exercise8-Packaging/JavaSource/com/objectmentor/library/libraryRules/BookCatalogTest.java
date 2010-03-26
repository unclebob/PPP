package com.objectmentor.library.libraryRules;

import junit.framework.TestCase;

import com.objectmentor.library.gateways.IsbnDoesNotExistException;
import com.objectmentor.library.mocks.MockIsbnService;
import com.objectmentor.library.mocks.MockMediaGateway;
import com.objectmentor.library.models.Book;
import com.objectmentor.library.models.MediaCopy;

public class BookCatalogTest extends TestCase {

  private MockIsbnService mockIsbnService;
  private MockMediaGateway mockMediaGateway;
  private BookCatalog bookCatalog;

  protected void setUp() throws Exception {
    mockIsbnService = new MockIsbnService();
    mockMediaGateway = new MockMediaGateway();
    bookCatalog = new BookCatalog(mockIsbnService, mockMediaGateway);
  }

  public void testAddBookShouldLookUpISBN() {
    addBookCopy("ISBN");
    assertEquals("ISBN", mockIsbnService.isbnLastPassedToFind);
  }

  public void testAddBookShouldAddBookReturnedByIsbnService() {
    MediaCopy mediaCopy = addBookCopy("ISBN");
    assertEquals(mockMediaGateway.addedMediaCopy.getMedia(), mediaCopy
      .getMedia());
  }

  public void testUnfoundISBN() {
    try {
      bookCatalog.addBookCopy("NON-EXISTENT ISBN");
      fail();
    }
    catch (IsbnDoesNotExistException e) {
    }
  }

  private MediaCopy addBookCopy(String isbn) {
    mockIsbnService.setBookToReturn(new Book(isbn, "War and Peace", "Tolstoy"));
    return bookCatalog.addBookCopy(isbn);
  }

}
