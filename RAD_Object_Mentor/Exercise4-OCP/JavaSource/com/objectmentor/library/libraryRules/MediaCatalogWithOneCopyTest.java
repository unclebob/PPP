package com.objectmentor.library.libraryRules;

import com.objectmentor.library.mocks.*;
import com.objectmentor.library.models.*;
import com.objectmentor.library.utils.DateUtil;
import junit.framework.TestCase;

import java.util.List;

public class MediaCatalogWithOneCopyTest extends TestCase {

  private MediaCatalog catalog;

  protected void setUp() throws Exception {
    MockIsbnService isbnService = new MockIsbnService();
    MockMediaGateway mockBookRepository = new MockMediaGateway();
    catalog = new MediaCatalog(isbnService, null, mockBookRepository);
    Media book = new Media("ISBN", "War and Peace", "Tolstoy");
    isbnService.setBookToReturn(book);
    catalog.addBookCopy("ISBN");
  }

  public void testFindCopyShouldReturnNullForUnaddedBooks() {
    assertNull("expected null for unrecognized ISBN", catalog.findCopy("NOT ISBN"));
  }

  public void testFindCopyShouldReturnCopyForCorrectId() {
    assertNotNull("expected copy for recognized ISBN", catalog.findCopy("ISBN"));
  }

  public void testContainsShouldReturnTrueForCorrectId() throws Exception {
    assertTrue("expected true for recognized ISBN", catalog.contains("ISBN"));
  }

  public void testContainsShouldReturnFalseForIncorrectISBN() throws Exception {
    assertFalse("expected false for unrecognized Id", catalog.contains("NOT ISBN"));
  }

  public void testFindAvailableCopyReturnsNullWhenCopyIsBorrowed() throws Exception {
    List<?> copies = catalog.findAllCopies("ISBN");
    MediaCopy copy = (MediaCopy) copies.get(0);
    copy.setLoaned(new LoanReceipt(new Patron(DateUtil.dateFromString("1/1/2000"))));
    assertEquals(null, catalog.findAvailableCopy("ISBN"));
  }

  public void testFindAvailableCopyReturnsCopyWhenCopyIsNotBorrowed() throws Exception {
    assertNotNull(catalog.findAvailableCopy("ISBN"));
  }


}
