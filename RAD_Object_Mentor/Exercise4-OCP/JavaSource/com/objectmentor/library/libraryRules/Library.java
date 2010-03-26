package com.objectmentor.library.libraryRules;

import com.objectmentor.library.gateways.*;
import com.objectmentor.library.mocks.CardPrinter;
import com.objectmentor.library.models.*;
import com.objectmentor.library.utils.DateUtil;

import java.util.*;

public class Library {
  protected MediaCatalog catalog;
  protected PatronGateway patronGateway;
  protected CardPrinter cardPrinter;
  protected IsbnService isbnService;
  protected CompactDiscService compactDiscService;
  protected MediaGateway mediaGateway;
  protected ComputerGateway computerGateway;

  public Library(
    IsbnService isbnService,
    CompactDiscService compactDiscService,
    MediaGateway mediaGateway,
    PatronGateway patronGateway,
    CardPrinter cardPrinter,
    ComputerGateway computerGateway) {
    this.isbnService = isbnService;
    this.compactDiscService = compactDiscService;
    this.mediaGateway = mediaGateway;
    this.patronGateway = patronGateway;
    this.cardPrinter = cardPrinter;
    this.computerGateway = computerGateway;
    catalog = new MediaCatalog(getIsbnService(), getCompactDiscService(),
                               getMediaGateway());
  }

  public MediaCopy acceptBook(String isbn) {
    return catalog.addBookCopy(isbn);
  }

  public List<MediaCopy> acceptBooks(String isbn, int numberOfNewBooks) {
    return catalog.addBookCopies(isbn, numberOfNewBooks);
  }

  public LoanReceipt loan(String copyId, String borrowerId) {
    MediaCopy copy = catalog.findCopyById(copyId);
    Patron borrower = getPatronGateway().findById(borrowerId);
    LoanReceipt loanReceipt = new LoanReceipt(borrower);
    if (copy == null)
      loanReceipt.setStatus(LoanReceipt.NO_SUCH_MEDIA);
    else if (borrower == null)
      loanReceipt.setStatus(LoanReceipt.NO_SUCH_PATRON);
    else if (!copy.getMedia().canLoan(borrower))
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

  public ComputerLoanReceipt loanComputer(String macAddress, String patronId)
    throws PatronDoesNotExistException, ComputerDoesNotExistException {
    Computer computer = computerGateway.findComputerById(macAddress);
    Patron patron = patronGateway.findById(patronId);
    ComputerLoanReceipt receipt = new ComputerLoanReceipt(computer, patron);
    return receipt;
  }

  private Date getDueDate(Media media) {
    Date now = getTime();
    Calendar c = Calendar.getInstance();
    c.setTime(now);
    int daysAllowed = -1;
    switch (media.getTypeCode()) {
      case(Media.BOOK):
        daysAllowed = 14;
        break;

      case(Media.COMPACT_DISC):
        daysAllowed = 5;
        break;
    }
    c.add(Calendar.DAY_OF_YEAR, daysAllowed);
    return c.getTime();
  }

  @SuppressWarnings("unchecked")
public ReturnReceipt returnCopy(String copyId) {
    return returnCopy(copyId, Collections.EMPTY_LIST);
  }

  public ReturnReceipt returnCopy(String copyId, List<ReturnCondition> conditions) {
    MediaCopy copy = catalog.findCopyById(copyId);
    ReturnReceipt returnReceipt = new ReturnReceipt();
    if (copy == null) {
      returnReceipt.setStatus(ReturnReceipt.UNKNOWN_BOOK);
    } else if (!copy.isLoaned()) {
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

  private Money calculateFine(LoanReceipt receipt) {
    int days = DateUtil.daysLate(getTime(), receipt.getDueDate());
    Media media = receipt.getBorrowedCopy().getMedia();
    int perDiemFine = 0;
    switch (media.getTypeCode()) {
      case(Media.BOOK):
        perDiemFine = 50;
        break;
      case(Media.COMPACT_DISC):
        perDiemFine = 150;
        break;
    }
    return new Money(days * perDiemFine);
  }

  public void registerPatron(Patron patron) {
    getPatronGateway().add(patron);
    getCardPrinter().print(patron);
  }

  public MediaCopy acceptCD(String cddbId) {
    return catalog.addCD(cddbId);
  }

  public IsbnService getIsbnService() {
    return isbnService;
  }

  public CompactDiscService getCompactDiscService() {
    return compactDiscService;
  }

  public PatronGateway getPatronGateway() {
    return patronGateway;
  }

  public Collection<Patron> findAllPatrons() {
    return patronGateway.findAllPatrons();
  }

  public void setPatronGateway(PatronGateway patronGateway) {
    this.patronGateway = patronGateway;
  }

  public CardPrinter getCardPrinter() {
    return cardPrinter;
  }

  public void setCardPrinter(CardPrinter cardPrinter) {
    this.cardPrinter = cardPrinter;
  }

  public void setIsbnService(IsbnService isbnService) {
    this.isbnService = isbnService;
  }

  public void setCompactDiscService(CompactDiscService compactDiscService) {
    this.compactDiscService = compactDiscService;
  }

  public MediaGateway getMediaGateway() {
    return mediaGateway;
  }

  public void setMediaGateway(MediaGateway mediaGateway) {
    this.mediaGateway = mediaGateway;
  }

  public ComputerGateway getComputerGateway() {
    return computerGateway;
  }

  public MediaCatalog getCatalog() {
    return catalog;
  }

  public boolean canAcceptComputer(String macAddress, String hostName,
                                   String description) {
    return computerGateway.isComputerOnNetwork(macAddress);
  }

  public void acceptComputer(String macAddress, String hostName,
                             String description) {
    if (!computerGateway.isComputerOnNetwork(macAddress))
      throw new ComputerDoesNotExistException();
    computerGateway.acceptComputer(macAddress, hostName, description);
  }

}
