package com.objectmentor.library;

import com.objectmentor.library.mocks.*;
import com.objectmentor.library.models.*;
import com.objectmentor.library.utils.DateUtil;
import junit.framework.TestCase;

import java.util.Date;

public class LibraryBorrowingTest extends TestCase {
  private Library library;
  private Receipt receipt;
  private Patron david;
  private BookCopy copy;
  private MockDataServices dataServices;

  protected void setUp() throws Exception {
    dataServices = new MockDataServices();
    library = new Library(dataServices);
    david = new Patron("david");
    dataServices.addPatron(david);
    dataServices.setBookToReturn(new BookTitle("isbn"));
    copy = library.acceptBook("isbn");
  }

  public void testCanBorrowBookThatLibraryHas() throws Exception {
    receipt = library.borrow(copy.getId(), "david");
    assertEquals(Receipt.OK, receipt.getStatus());
  }

  public void testShouldSetCopyToBorrowed() throws Exception {
    receipt = library.borrow(copy.getId(), "david");
    assertTrue(copy.isBorrowed());
  }

  public void testCanNotBorrowBookLibraryDoesNotHave() throws Exception {
    receipt = library.borrow("isbnWeDontHave", "david");
    assertEquals(Receipt.UNKNOWN_BOOK, receipt.getStatus());
  }

  public void testCanBorrowTwoIfTwoInCatalog() throws Exception {
    dataServices.setBookToReturn(new BookTitle("secondIsbn"));
    BookCopy copy2 = library.acceptBook("secondIsbn");
    receipt = library.borrow(copy.getId(), "david");
    assertEquals(Receipt.OK, receipt.getStatus());
    receipt = library.borrow(copy2.getId(), "david");
    assertEquals(Receipt.OK, receipt.getStatus());
  }

  public void testSetReturnDateTwoWeeksFromToday() throws Exception {
    Date now = DateUtil.dateFromString("12/19/2006");
    Date returnDate = DateUtil.dateFromString("1/2/2007");

    library.setTimeSource(new MockTimeSource(now));
    receipt = library.borrow(copy.getId(), "david");
    assertEquals(returnDate, receipt.getDueDate());
  }

  public void testReceiptRemembersBorrowingPatron() throws Exception {
    receipt = library.borrow(copy.getId(), "david");
    assertEquals(david, receipt.getBorrower());
  }

  public void testReceiptRemembersBorrowedCopy() throws Exception {
    receipt = library.borrow(copy.getId(), "david");
    assertEquals("isbn", receipt.getCopy().getBookTitle().getIsbn());
  }
}
