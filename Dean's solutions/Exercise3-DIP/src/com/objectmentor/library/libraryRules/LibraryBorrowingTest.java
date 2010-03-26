package com.objectmentor.library.libraryRules;

import java.util.Date;

import junit.framework.TestCase;

import com.objectmentor.library.mocks.MockTimeSource;
import com.objectmentor.library.models.MediaCopy;
import com.objectmentor.library.models.LoanReceipt;
import com.objectmentor.library.models.Patron;
import com.objectmentor.library.utils.DateUtil;
import com.objectmentor.library.web.controller.Application;

public class LibraryBorrowingTest extends TestCase {
  private Library library;
  private LoanReceipt receipt;
  private Patron patron;
  private MediaCopy copy;

  protected void setUp() throws Exception {
    Application application = new Application();
    library = application.getLibrary();
    patron = new Patron(DateUtil.dateFromString("1/1/2000"));
    patron.setId("" + application.getPatronGateway().getNextId());
    application.getPatronGateway().getPatronMap().put(patron.getId(), patron);
    copy = library.acceptBook("1");
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
    MediaCopy copy2 = library.acceptBook("2");
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
    assertEquals("1", receipt.getBorrowedCopy().getMedia().getId());
  }

  public void testNotOldEnough() throws Exception {
    Date davidIsSixToday = DateUtil.dateFromString("1/1/2006");
    TimeSource.timeSource = new MockTimeSource(davidIsSixToday);
    MediaCopy copy = library.acceptBook("0316769533"); //Catcher in the Rye
    copy.getMedia().setAgeRestriction(10);
    LoanReceipt receipt = library.loan(copy, patron);
    assertEquals(LoanReceipt.DISALLOWED, receipt.getStatus());
  }

  public void testOldEnough() throws Exception {
    Date davidIsElevenToday = DateUtil.dateFromString("1/1/2011");
    TimeSource.timeSource = new MockTimeSource(davidIsElevenToday);
    MediaCopy copy = library.acceptBook("0316769533"); //Catcher in the Rye
    copy.getMedia().setAgeRestriction(10);
    LoanReceipt receipt = library.loan(copy, patron);
    assertEquals(LoanReceipt.OK, receipt.getStatus());
  }
}
