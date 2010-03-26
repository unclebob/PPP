package com.objectmentor.library;

import com.objectmentor.library.mocks.*;
import com.objectmentor.library.models.*;
import com.objectmentor.library.utils.DateUtil;
import junit.framework.TestCase;

import java.util.Date;

public class LibraryBorrowingTest extends TestCase {
  private Library library;
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
    assertEquals(BorrowedReceipt.OK, library.borrow(copy.getId(), "david").getStatus());
  }

  public void testShouldSetCopyToBorrowed() throws Exception {
    library.borrow(copy.getId(), "david");
    assertTrue(copy.isBorrowed());
  }

  public void testCanNotBorrowBookLibraryDoesNotHave() throws Exception {
    assertEquals(BorrowedReceipt.UNKNOWN_BOOK, library.borrow("isbnWeDontHave", "david").getStatus());
  }

  public void testCanBorrowTwoIfTwoInCatalog() throws Exception {
    dataServices.setBookToReturn(new BookTitle("secondIsbn"));
    BookCopy copy2 = library.acceptBook("secondIsbn");
    assertEquals(BorrowedReceipt.OK, library.borrow(copy.getId(), "david").getStatus());
    assertEquals(BorrowedReceipt.OK, library.borrow(copy2.getId(), "david").getStatus());
  }

  public void testSetReturnDateTwoWeeksFromToday() throws Exception {
    Date now = DateUtil.dateFromString("12/19/2006");
    Date returnDate = DateUtil.dateFromString("1/2/2007");

    library.setTimeSource(new MockTimeSource(now));
    assertEquals(returnDate, library.borrow(copy.getId(), "david").getReturnDate());
  }

  public void testReceiptRemembersBorrowingPatron() throws Exception {
    assertEquals(david, library.borrow(copy.getId(), "david").getBorrower());
  }

  public void testReceiptRemembersBorrowedCopy() throws Exception {
    assertEquals("isbn", library.borrow(copy.getId(), "david").getCopy().getBookTitle().getIsbn());
  }
}
