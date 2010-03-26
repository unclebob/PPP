package com.objectmentor.library.application.libraryRules;

import com.objectmentor.library.application.BookCatalog;
import com.objectmentor.library.application.CompactDiscCatalog;
import com.objectmentor.library.application.models.*;
import com.objectmentor.library.utils.*;

import java.util.*;

public class Library {
  private final BookCatalog bookCatalog;
  private final CompactDiscCatalog cdCatalog;
  private final ComputerCatalog computerCatalog;

  public Library(
    BookCatalog mediaCatalog, CompactDiscCatalog cdCatalog, ComputerCatalog computerCatalog) {
  	this.bookCatalog = mediaCatalog;
  	this.cdCatalog = cdCatalog;
    this.computerCatalog = computerCatalog;
  }

  public MediaCopy acceptBook(String isbn) {
    return bookCatalog.addBookCopy(isbn);
  }

  public List acceptBooks(String isbn, int numberOfNewBooks) {
    return bookCatalog.addBookCopies(isbn, numberOfNewBooks);
  }

  public LoanReceipt loan(MediaCopy copy, Patron borrower) {
    LoanReceipt loanReceipt = new LoanReceipt(borrower);
    if (!copy.getMedia().canLoan(borrower))
      loanReceipt.setStatus(LoanReceipt.DISALLOWED);
    else if (copy.isLoaned())
      loanReceipt.setStatus(LoanReceipt.ALREADY_BORROWED);
    else {
      copy.setLoaned(loanReceipt);
      loanReceipt.setStatus(LoanReceipt.OK);
      loanReceipt.setLoanedCopy(copy);
      loanReceipt.setDueDate(getDueDate(copy.getMedia()));
    }
    return loanReceipt;
  }

  public ComputerLoanReceipt loanComputer(Computer computer, Patron patron) {
    return new ComputerLoanReceipt(computer, patron);
  }

  private Date getDueDate(Media media) {
    Date now = getTime();
    Calendar c = Calendar.getInstance();
    c.setTime(now);
    c.add(Calendar.DAY_OF_YEAR, media.howManyDaysThisCanBeBorrowed());
    return c.getTime();
  }


  public ReturnReceipt returnCopy(MediaCopy copy) {
    return returnCopy(copy, Collections.EMPTY_LIST);
  }

  public ReturnReceipt returnCopy(MediaCopy copy, List conditions) {
    ReturnReceipt returnReceipt = new ReturnReceipt();
    if (!copy.isLoaned()) {
      returnReceipt.setStatus(ReturnReceipt.UNBORROWED_BOOK);
      returnReceipt.setCopy(copy);
    } else {
      LoanReceipt loanReceipt = copy.getLoanReceipt();
      if (getTime().compareTo(loanReceipt.getDueDate()) > 0) {
        returnReceipt.setStatus(ReturnReceipt.LATE);
        returnReceipt.setFine(calculateFine(loanReceipt));
      } else {
        returnReceipt.setStatus(ReturnReceipt.OK);
      }
      copy.setLoaned(null);
      returnReceipt.setCopy(copy);
      for (int i = 0; i < conditions.size(); i++) {
        ReturnCondition condition = (ReturnCondition) conditions.get(i);
        Money charge = condition.getCharge(copy.getMedia());
        if (charge != Money.ZERO)
          returnReceipt.addCharge(condition, charge);
      }
    }

    return returnReceipt;
  }

  private Date getTime() {
    return TimeSource.time();
  }

  public static Money calculateFine(LoanReceipt receipt) {
    int daysLate = DateUtil.daysLate(TimeSource.time(), receipt.getDueDate());
    if (daysLate <= 0)
      return new Money(0);
    else {
      return receipt.getBorrowedCopy().getMedia().perDiemFine().times(daysLate);
    }
  }

  public MediaCopy acceptCD(String cddbId) {
    return cdCatalog.addCDCopy(cddbId);
  }

  public List acceptCDs(String barCode, int numberOfNewCDs) {
    return cdCatalog.addCDCopies(barCode, numberOfNewCDs);
  }

  public boolean canAcceptComputer(String macAddress, String hostName, String description) {
    return computerCatalog.canAcceptComputer(macAddress, hostName, description);
  }

  public void acceptComputer(String macAddress, String hostName, String description) {
    computerCatalog.acceptComputer(macAddress, hostName, description);
  }
}
