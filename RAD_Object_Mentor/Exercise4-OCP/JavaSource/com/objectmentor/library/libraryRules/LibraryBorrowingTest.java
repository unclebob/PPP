package com.objectmentor.library.libraryRules;

import java.util.Date;

import junit.framework.TestCase;

import com.objectmentor.library.mocks.MockIsbnService;
import com.objectmentor.library.mocks.MockTimeSource;
import com.objectmentor.library.models.LoanReceipt;
import com.objectmentor.library.models.Media;
import com.objectmentor.library.models.MediaCopy;
import com.objectmentor.library.models.Patron;
import com.objectmentor.library.utils.DateUtil;

public class LibraryBorrowingTest extends TestCase {
  private LibraryWithMockServices library;
  private LoanReceipt receipt;
  private MockIsbnService isbnService;
  private Patron david;
  private MediaCopy copy;

  protected void setUp() throws Exception {
    library = new LibraryWithMockServices();
    isbnService = library.getMockIsbnService();
    david = new Patron(DateUtil.dateFromString("1/1/2000"));
    library.getPatronGateway().add(david);
    isbnService.setBookToReturn(new Media("isbn", "War and Peace", "Tolstoy"));
    copy = library.acceptBook("isbn");
  }

  public void testCanBorrowBookThatLibraryHas() throws Exception {
    receipt = library.loan(copy.getId(), david.getId());
    assertEquals(LoanReceipt.OK, receipt.getStatus());
  }

  public void testCanNotLoanToNullPatron() throws Exception {
    receipt = library.loan(copy.getId(), null);
    assertEquals(LoanReceipt.NO_SUCH_PATRON, receipt.getStatus());
  }


  public void testShouldSetCopyToBorrowed() throws Exception {
    receipt = library.loan(copy.getId(), david.getId());
    assertTrue(copy.isLoaned());
  }

  public void testCanNotBorrowBookLibraryDoesNotHave() throws Exception {
    receipt = library.loan("isbnWeDontHave", david.getId());
    assertEquals(LoanReceipt.NO_SUCH_MEDIA, receipt.getStatus());
  }

  public void testCantBorrowIfAlreadyBorrowed() throws Exception {
    library.loan(copy.getId(), david.getId());
    receipt = library.loan(copy.getId(), david.getId());

    assertEquals(LoanReceipt.ALREADY_BORROWED, receipt.getStatus());
  }


  public void testCanBorrowTwoIfTwoInCatalog() throws Exception {
    isbnService.setBookToReturn(new Media("secondIsbn", "War and Peace", "Tolstoy"));
    MediaCopy copy2 = library.acceptBook("secondIsbn");
    receipt = library.loan(copy.getId(), david.getId());
    assertEquals(LoanReceipt.OK, receipt.getStatus());
    receipt = library.loan(copy2.getId(), david.getId());
    assertEquals(LoanReceipt.OK, receipt.getStatus());
  }

  public void testSetReturnDateTwoWeeksFromToday() throws Exception {
    Date now = DateUtil.dateFromString("12/19/2006");
    Date returnDate = DateUtil.dateFromString("1/2/2007");

    TimeSource.timeSource = new MockTimeSource(now);
    receipt = library.loan(copy.getId(), david.getId());
    assertEquals(returnDate, receipt.getDueDate());
  }

  public void testReceiptRemembersBorrowingPatron() throws Exception {
    receipt = library.loan(copy.getId(), david.getId());
    assertEquals(david, receipt.getBorrower());
  }

  public void testReceiptRemembersBorrowedCopy() throws Exception {
    receipt = library.loan(copy.getId(), david.getId());
    assertEquals("isbn", receipt.getBorrowedCopy().getMedia().getId());
  }

  public void testNotOldEnough() throws Exception {
    Date davidIsSixToday = DateUtil.dateFromString("1/1/2006");
    TimeSource.timeSource = new MockTimeSource(davidIsSixToday);
    Media catcherInTheRye = new Media("catcher", "Catcher in the Rye", "Who knows");
    catcherInTheRye.setAgeRestriction(10);
    isbnService.setBookToReturn(catcherInTheRye);
    MediaCopy copy = library.acceptBook("catcher");
    LoanReceipt br = library.loan(copy.getId(), david.getId());
    assertEquals(LoanReceipt.DISALLOWED, br.getStatus());
  }

  public void testOldEnough() throws Exception {
    Date davidIsElevenToday = DateUtil.dateFromString("1/1/2011");
    TimeSource.timeSource = new MockTimeSource(davidIsElevenToday);
    Media catcherInTheRye = new Media("catcher", "Catcher in the Rye", "Who knows");
    catcherInTheRye.setAgeRestriction(10);
    isbnService.setBookToReturn(catcherInTheRye);
    MediaCopy copy = library.acceptBook("catcher");
    LoanReceipt br = library.loan(copy.getId(), david.getId());
    assertEquals(LoanReceipt.OK, br.getStatus());
  }
}
