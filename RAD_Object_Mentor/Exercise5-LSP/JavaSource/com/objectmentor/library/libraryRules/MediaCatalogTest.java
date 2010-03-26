package com.objectmentor.library.libraryRules;

import com.objectmentor.library.gateways.*;
import com.objectmentor.library.mocks.*;
import com.objectmentor.library.models.*;
import junit.framework.TestCase;

public class MediaCatalogTest extends TestCase {

  private MockIsbnService isbnService;
  private MockMediaGateway mockMediaGateway;
  private MediaCatalog catalog;
  private MockCompactDiscService compactDiscService;

  protected void setUp() throws Exception {
    isbnService = new MockIsbnService();
    compactDiscService = new MockCompactDiscService();
    mockMediaGateway = new MockMediaGateway();
    catalog = new MediaCatalog(isbnService, compactDiscService, mockMediaGateway);
  }

  public void testAddBookShouldLookUpISBN() {
    addBookCopy("ISBN");
    assertEquals("ISBN", isbnService.isbnLastPassedToFind);
  }

  public void testAddBookShouldLookUpCDBarCode() {
    addCDCopy("barCode");
    assertEquals("barCode", compactDiscService.barCodeLastPassedToFind);
  }

  public void testAddBookShouldAddBookReturnedByIsbnService() {
    MediaCopy mediaCopy = addBookCopy("ISBN");
    assertEquals(mockMediaGateway.addedMediaCopy.getMedia(), mediaCopy
      .getMedia());
  }

  public void testAddBookShouldAddCDReturnedByCDService() {
    MediaCopy mediaCopy = addCDCopy("barCode");
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

  public void testUnfoundCD() {
    try {
      catalog.addCDCopy("NON-EXISTENT BarCode");
      fail();
    }
    catch (CdDoesNotExistException e) {
    }
  }

  private MediaCopy addBookCopy(String isbn) {
    isbnService.setBookToReturn(new Book(isbn, "War and Peace", "Tolstoy"));
    return catalog.addBookCopy(isbn);
  }

  private MediaCopy addCDCopy(String barCode) {
    compactDiscService.setCDToReturn(new CompactDisc(barCode, "Double Rainbow", "Joe Henderson"));
    return catalog.addCDCopy(barCode);
  }

}
