package com.objectmentor.library.web.controller.patrons;

import com.objectmentor.library.models.LoanReceipt;
import com.objectmentor.library.web.controller.*;

public class BooksController extends Controller {

  private LoanReceipt receipt;

  public ActionResult checkin() {
    setIncludePath("patrons/books/checkin.jsp");
    return new ActionResult();
  }

  public ActionResult checkout() {
    setIncludePath("patrons/books/checkout.jsp");
    if (isPost()) {
      String copyId = getParameter("copyId");
      String borrowerId = getParameter("borrowerId");
      receipt = getLibrary().loan(copyId, borrowerId);
      switch (getReceipt().getStatus()) {
        case LoanReceipt.ALREADY_BORROWED:
          return ActionResult.makeErrorHandlerResult("Copy \"" + copyId + "\" is already checked out.");
        case LoanReceipt.DISALLOWED:
          return ActionResult.makeErrorHandlerResult("Copy \"" + copyId + "\" cannot be checked out!");
        case LoanReceipt.NO_SUCH_MEDIA:
          return ActionResult.makeErrorHandlerResult("No book matches copy id \"" + copyId + "\"!");
        case LoanReceipt.NO_SUCH_PATRON:
          return ActionResult.makeErrorHandlerResult("No patron matches id \"" + borrowerId + "\"!");
        case LoanReceipt.OK:
          // Handled at end...
      }
      String title = receipt.getBorrowedCopy().getMedia().getTitle();
      String borrowerName = receipt.getBorrower().getFullName();
      return ActionResult.makeInfoHandlerResult("Copy \"" + title + "\" checked out to borrower \"" + borrowerName + "\".");
    }
    return new ActionResult();
  }

  public LoanReceipt getReceipt() {
    return receipt;
  }

}
