package com.objectmentor.library;

import java.util.Calendar;
import java.util.Date;

import com.objectmentor.library.data.DataServices;
import com.objectmentor.library.models.BookCopy;
import com.objectmentor.library.models.BorrowedReceipt;
import com.objectmentor.library.models.Patron;
import com.objectmentor.library.models.ReturnReceipt;
import com.objectmentor.library.utils.DateUtil;

public class Library {
  private BookCatalog catalog;
  private TimeSource timeSource;
  private DataServices dataServices;

  public Library(DataServices dataServices) {
    this.dataServices = dataServices;
    catalog = new BookCatalog(dataServices);
    timeSource = new StandardTimeSource();
  }

  public BookCopy acceptBook(String isbn) {
    return catalog.addCopy(isbn);
  }

  public BorrowedReceipt borrow(String copyId, String borrowerId) {
    BookCopy copy = catalog.findCopyById(copyId);
    Patron borrower = dataServices.findPatronById(borrowerId);
    BorrowedReceipt borrowReceipt = new BorrowedReceipt(borrower);
    if (copy == null)
      borrowReceipt.setStatus(BorrowedReceipt.UNKNOWN_BOOK);
    else {
      copy.setBorrowed(borrowReceipt);
      borrowReceipt.setStatus(BorrowedReceipt.OK);
      borrowReceipt.setCopy(copy);
      borrowReceipt.setReturnDate(getReturnDate());
    }
    return borrowReceipt;
  }

  private Date getReturnDate() {
    Date now = timeSource.getTime();
    Calendar c = Calendar.getInstance();
    c.setTime(now);
    c.add(Calendar.DAY_OF_YEAR, 14);
    return c.getTime();
  }

  public void setTimeSource(TimeSource timeSource) {
    this.timeSource = timeSource;
  }

  public ReturnReceipt returnReturnReceipt(String copyId) {
    BookCopy copy = catalog.findCopyById(copyId);
    ReturnReceipt receipt = new ReturnReceipt();
    if (copy == null) {
      receipt.setStatus(ReturnReceipt.UNKNOWN_BOOK);
    } else if (!copy.isBorrowed()) {
      receipt.setStatus(ReturnReceipt.UNBORROWED_BOOK);
      receipt.setCopy(copy);
    } else {
      BorrowedReceipt borrowReceipt = copy.getBorrowReceipt();
      if (timeSource.getTime().compareTo(borrowReceipt.getReturnDate()) > 0) {
        receipt.setStatus(BorrowedReceipt.LATE);
        receipt.setFine(calculateFine(borrowReceipt));
      } else {
        receipt.setStatus(BorrowedReceipt.OK);
      }
      copy.setBorrowed(null);
      receipt.setCopy(copy);
    }

    return receipt;
  }

  private Money calculateFine(BorrowedReceipt receipt) {
    int days = DateUtil.daysLate(timeSource.getTime(), receipt.getReturnDate());
    return new Money(days * 50);
  }
}
