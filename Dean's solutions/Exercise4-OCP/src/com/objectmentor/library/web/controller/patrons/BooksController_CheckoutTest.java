package com.objectmentor.library.web.controller.patrons;

import com.objectmentor.library.mocks.MockHttpServletRequest;
import com.objectmentor.library.models.*;
import com.objectmentor.library.web.controller.*;
import com.objectmentor.library.web.utils.ControllerTestHelper;
import junit.framework.TestCase;

public class BooksController_CheckoutTest extends TestCase {
  private ControllerTestHelper helper;
  private MediaCopy media;
  private MockHttpServletRequest request;
  private BooksController booksController;

  protected void setUp() throws Exception {
    super.setUp();
    helper = new ControllerTestHelper();
    request = helper.createMockRequest("POST");
    helper.setActionName(request, "checkout");
    booksController = new BooksController();
  }

  public void testCheckOutExistingBook() throws Exception {
    finishSetupWithBookThatCanBeBorrowed();
    booksController.handle(request);
    LoanReceipt receipt = booksController.getReceipt();
    assertEquals(LoanReceipt.OK, receipt.getStatus());
    assertEquals(helper.getLastPatron(), receipt.getBorrower());
  }

  public void testCheckOutFailsIfBookAlreadyBorrowed() throws Exception {
    finishSetupWithBookThatCanBeBorrowed();
    booksController.handle(request);
    ActionResult result = booksController.handle(request);  // repeat!
    assertTrue(result.getErrorMessage().length() > 0);
    LoanReceipt receipt = booksController.getReceipt();
    assertEquals(LoanReceipt.ALREADY_BORROWED, receipt.getStatus());
  }

  public void testCheckOutFailsIfBookDoesNotExist() throws Exception {
    finishSetupWithBookThatCanBeBorrowed();
    request.setParameter("copyId", "0");
    ActionResult result = booksController.handle(request);

    assertTrue(result.toString(), result.getErrorMessage().length() > 0);
    LoanReceipt receipt = booksController.getReceipt();
    assertEquals(LoanReceipt.NO_SUCH_MEDIA, receipt.getStatus());
  }

  public void testCheckOutFailsIfNoSuchPatron() throws Exception {
    finishSetupWithBookThatCanBeBorrowed();
    request.setParameter("borrowerId", "InvalidPatronId");
    ActionResult result = booksController.handle(request);

    assertTrue(result.toString(), result.getErrorMessage().length() > 0);
    LoanReceipt receipt = booksController.getReceipt();
    assertEquals(LoanReceipt.NO_SUCH_PATRON, receipt.getStatus());
  }


  public void testCheckOutFailsIfBookCannotBeBorrowed() throws Exception {
    finishSetupWithBookThatCannotBeBorrowed();
    ActionResult result = booksController.handle(request);

    assertTrue(result.toString(), result.getErrorMessage().length() > 0);
    LoanReceipt receipt = booksController.getReceipt();
    assertEquals(LoanReceipt.NO_SUCH_MEDIA, receipt.getStatus());
  }

  private void finishSetupWithBookThatCanBeBorrowed() {
    helper.createLibraryWithBookAndPatron();
    media = helper.getLastMediaCopy();
    request.setParameter("copyId", media.getId());
    request.setParameter("borrowerId", helper.getLastPatron().getId());
  }

  private void finishSetupWithBookThatCannotBeBorrowed() {
    helper.createLibraryWithBookAndPatronWhereBookCannotBeBorrowed();
    media = helper.getLastMediaCopy();
    request.setParameter("copyId", "-1");
    request.setParameter("borrowerId", helper.getLastPatron().getId());
  }

}
