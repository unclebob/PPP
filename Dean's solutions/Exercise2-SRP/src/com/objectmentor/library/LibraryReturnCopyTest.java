package com.objectmentor.library;

import java.util.Date;

import junit.framework.TestCase;

import com.objectmentor.library.mocks.MockDataServices;
import com.objectmentor.library.mocks.MockTimeSource;
import com.objectmentor.library.models.BookCopy;
import com.objectmentor.library.models.BookTitle;
import com.objectmentor.library.models.ReturnReceipt;
import com.objectmentor.library.utils.DateUtil;

public class LibraryReturnCopyTest extends TestCase {
  private Library library;
  private String copyId;
  private BookCopy copy;

  protected void setUp() throws Exception {
    MockDataServices dataServices = new MockDataServices();
    library = new Library(dataServices);
    dataServices.setBookToReturn(new BookTitle("isbn"));
    copy = library.acceptBook("isbn");
    copyId = copy.getId();
  }

  public void testCanReturnOnTimeBookThatWasBorrowed() throws Exception {
    library.borrow(copyId, "dean");
    ReturnReceipt returnReceipt = library.returnReturnReceipt(copyId);
    assertEquals(new Money(0), returnReceipt.getFine());
    assertEquals(copy, returnReceipt.getCopy());
    assertFalse(returnReceipt.getCopy().isBorrowed());
    assertEquals(ReturnReceipt.OK, returnReceipt.getStatus());
  }

  public void testShouldSetStatusToUnknownBookOnAttemptToReturnUnknownBook() throws Exception {
    assertEquals(ReturnReceipt.UNKNOWN_BOOK, library.returnReturnReceipt("unknown terrible string").getStatus());
  }

  public void testShouldSetStatusToUnBorrowedBookOnAttemptToReturnUnBorrowedBook() throws Exception {
    ReturnReceipt returnReceipt = library.returnReturnReceipt(copyId);
    assertEquals(ReturnReceipt.UNBORROWED_BOOK, returnReceipt.getStatus());
    assertEquals(copy, returnReceipt.getCopy());
  }

  public void testShouldHaveFineAndBeLateIfOneDayLate() throws Exception {
    Date borrowDate = DateUtil.dateFromString("12/19/2006");
    Date returnDate = DateUtil.dateFromString("1/3/2007"); // fifteen days later.
    library.setTimeSource(new MockTimeSource(borrowDate));
    library.borrow(copyId, "dean");
    library.setTimeSource(new MockTimeSource(returnDate));
    ReturnReceipt receipt = library.returnReturnReceipt(copyId);

    assertEquals(ReturnReceipt.LATE, receipt.getStatus());
    assertEquals(new Money(50), receipt.getFine());
  }


  public void testShouldHaveFineAndBeLateIfTwoDaysLate() throws Exception {
    Date borrowDate = DateUtil.dateFromString("12/19/2006");
    Date returnDate = DateUtil.dateFromString("1/4/2007"); // sixteen days later.
    library.setTimeSource(new MockTimeSource(borrowDate));
    library.borrow(copyId, "dean");
    library.setTimeSource(new MockTimeSource(returnDate));
    ReturnReceipt receipt = library.returnReturnReceipt(copyId);

    assertEquals(ReturnReceipt.LATE, receipt.getStatus());
    assertEquals(new Money(100), receipt.getFine());
  }

}
