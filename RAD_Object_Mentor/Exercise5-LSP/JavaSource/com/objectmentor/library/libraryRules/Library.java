package com.objectmentor.library.libraryRules;

import com.objectmentor.library.models.*;
import com.objectmentor.library.utils.DateUtil;

import java.util.*;

public class Library {
  protected MediaCatalog mediaCatalog;
  private final ComputerCatalog computerCatalog;

  public Library(
    MediaCatalog mediaCatalog, ComputerCatalog computerCatalog) {
    this.computerCatalog = computerCatalog;
    this.mediaCatalog = mediaCatalog;
  }

  public MediaCopy acceptBook(String isbn) {
    return mediaCatalog.addBookCopy(isbn);
  }

  public List<MediaCopy> acceptBooks(String isbn, int numberOfNewBooks) {
    return mediaCatalog.addBookCopies(isbn, numberOfNewBooks);
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
    ComputerLoanReceipt receipt = new ComputerLoanReceipt(computer, patron);
    return receipt;
  }

  private Date getDueDate(Media book) {
    Date now = getTime();
    Calendar c = Calendar.getInstance();
    c.setTime(now);
    c.add(Calendar.DAY_OF_YEAR, book.howManyDaysThisCanBeBorrowed());
    return c.getTime();
  }

  public ReturnReceipt returnCopy(MediaCopy copy) {
    return returnCopy(copy, Collections.EMPTY_LIST);
  }

  public ReturnReceipt returnCopy(MediaCopy copy, List<ReturnCondition> conditions) {
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
        ReturnCondition condition = conditions.get(i);
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
    return mediaCatalog.addCDCopy(cddbId);
  }

  public List<MediaCopy> acceptCDs(String barCode, int numberOfNewCDs) {
    return mediaCatalog.addCDCopies(barCode, numberOfNewCDs);
  }

  public boolean canAcceptComputer(String macAddress, String hostName, String description) {
    return computerCatalog.canAcceptComputer(macAddress, hostName, description);
  }

  public void acceptComputer(String macAddress, String hostName, String description) {
    computerCatalog.acceptComputer(macAddress, hostName, description);
  }
}
