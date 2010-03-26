package com.objectmentor.library.libraryRules;

import com.objectmentor.library.mocks.*;
import com.objectmentor.library.models.*;
import com.objectmentor.library.utils.DateUtil;
import com.objectmentor.library.web.controller.*;
import junit.framework.TestCase;

import java.util.Date;

public class LibraryBorrowingTest extends TestCase {
  private Library library;
  private LoanReceipt receipt;
  private MockIsbnService isbnService;
  private Patron patron;
  private MediaCopy copy;

  protected void setUp() throws Exception {
    Application application = new Application(new MockServiceProvider());
    library = application.getLibrary();
    isbnService = (MockIsbnService) application.getIsbnService();
    patron = new Patron(DateUtil.dateFromString("1/1/2000"));
    patron.setId("" + application.getPatronGateway().getNextId());
    application.getPatronGateway().getPatronMap().put(patron.getId(), patron);
    isbnService.setBookToReturn(new Book("isbn", "War and Peace", "Tolstoy"));
    copy = library.acceptBook("isbn");
  }

  public void testCanBorrowBookThatLibraryHas() throws Exception {
    receipt = library.loan(copy, patron);
    assertEquals(LoanReceipt.OK, receipt.getStatus());
  }

  public void testShouldSetCopyToBorrowed() throws Exception {
    receipt = library.loan(copy, patron);
    assertTrue(copy.isLoaned());
  }

  public void testCantBorrowIfAlreadyBorrowed() throws Exception {
    library.loan(copy, patron);
    receipt = library.loan(copy, patron);

    assertEquals(LoanReceipt.ALREADY_BORROWED, receipt.getStatus());
  }


  public void testCanBorrowTwoIfTwoInCatalog() throws Exception {
    isbnService.setBookToReturn(new Book("secondIsbn", "War and Peace", "Tolstoy"));
    MediaCopy copy2 = library.acceptBook("secondIsbn");
    receipt = library.loan(copy, patron);
    assertEquals(LoanReceipt.OK, receipt.getStatus());
    receipt = library.loan(copy2, patron);
    assertEquals(LoanReceipt.OK, receipt.getStatus());
  }

  public void testSetReturnDateTwoWeeksFromToday() throws Exception {
    Date now = DateUtil.dateFromString("12/19/2006");
    Date returnDate = DateUtil.dateFromString("1/2/2007");

    TimeSource.timeSource = new MockTimeSource(now);
    receipt = library.loan(copy, patron);
    assertEquals(returnDate, receipt.getDueDate());
  }

  public void testReceiptRemembersBorrowingPatron() throws Exception {
    receipt = library.loan(copy, patron);
    assertEquals(patron, receipt.getBorrower());
  }

  public void testReceiptRemembersBorrowedCopy() throws Exception {
    receipt = library.loan(copy, patron);
    assertEquals("isbn", receipt.getBorrowedCopy().getMedia().getId());
  }

  public void testNotOldEnough() throws Exception {
    Date davidIsSixToday = DateUtil.dateFromString("1/1/2006");
    TimeSource.timeSource = new MockTimeSource(davidIsSixToday);
    Media catcherInTheRye = new Book("catcher", "Catcher in the Rye", "Who knows");
    catcherInTheRye.setAgeRestriction(10);
    isbnService.setBookToReturn(catcherInTheRye);
    MediaCopy copy = library.acceptBook("catcher");
    LoanReceipt br = library.loan(copy, patron);
    assertEquals(LoanReceipt.DISALLOWED, br.getStatus());
  }

  public void testOldEnough() throws Exception {
    Date davidIsElevenToday = DateUtil.dateFromString("1/1/2011");
    TimeSource.timeSource = new MockTimeSource(davidIsElevenToday);
    Media catcherInTheRye = new Book("catcher", "Catcher in the Rye", "Who knows");
    catcherInTheRye.setAgeRestriction(10);
    isbnService.setBookToReturn(catcherInTheRye);
    MediaCopy copy = library.acceptBook("catcher");
    LoanReceipt br = library.loan(copy, patron);
    assertEquals(LoanReceipt.OK, br.getStatus());
  }
}
