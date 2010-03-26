package com.objectmentor.library.web.controller.patrons;

import java.util.List;

import junit.framework.TestCase;

import com.objectmentor.library.gateways.MediaGateway;
import com.objectmentor.library.libraryRules.Library;
import com.objectmentor.library.libraryRules.TimeSource;
import com.objectmentor.library.mocks.MockHttpServletRequest;
import com.objectmentor.library.mocks.MockTimeSource;
import com.objectmentor.library.models.Patron;
import com.objectmentor.library.web.controller.ActionResult;
import com.objectmentor.library.web.utils.ControllerTestHelper;

public class BooksController_ReturnTest extends TestCase {
  private ControllerTestHelper helper;
  private MockHttpServletRequest request;
  private BooksController booksController;

  protected void setUp() throws Exception {
    helper = new ControllerTestHelper();
    booksController = new BooksController();
  }

  private void makeRequest(String method, String action) {
    request = helper.createMockRequest(method);
    helper.setActionName(action);
  }

  private void postReturn(String action) {
    makeRequest("POST", action);
    request.getSession().setAttribute("transaction", "return");
  }

  public void testGetReturnBookRendersIdentifyPatron() throws Exception {
    makeRequest("GET", "returnBook");
    booksController.handle(request);
    assertTrue(helper.shouldRender("patrons/books/identifyPatron.jsp"));
    assertEquals("return", request.getSession().getAttribute("transaction"));
  }

  public void testPostReturnCopyReturnsBook() throws Exception {
    postReturn("returnCopy");
    Library lib = helper.loadLibraryWithBookAndPatron();
    Patron patron = helper.getThePatron();
    lib.loan(helper.getTheMediaCopy(), patron);
    request.setParameter("copyId", helper.getTheMediaCopy().getId());
    request.getSession().setAttribute("patron", patron);
    booksController.handle(request);
    MediaGateway mediaGateway = helper.getMediaGateway();
    List loanReceipts = mediaGateway.findAllLoanReceiptsFor(helper.getThePatron().getId());
    assertEquals(0, loanReceipts.size());
  }

  public void testPostReturnCopyOfUnborrowedBookGivesError() throws Exception {
    postReturn("returnCopy");
    helper.loadLibraryWithBookAndPatron();
    Patron patron = helper.getThePatron();

    request.setParameter("copyId", helper.getTheMediaCopy().getId());
    request.getSession().setAttribute("patron", patron);
    ActionResult result = booksController.handle(request);

    assertTrue(result.getErrorMessage().length() > 0);
  }

  public void testPostReturnCopyForLateBookGiveWarning() throws Exception {
    postReturn("returnCopy");
    TimeSource.timeSource = new MockTimeSource("1/1/2005");
    Library lib = helper.loadLibraryWithBookAndPatron();
    Patron patron = helper.getThePatron();
    lib.loan(helper.getTheMediaCopy(), patron);
    request.setParameter("copyId", helper.getTheMediaCopy().getId());
    request.getSession().setAttribute("patron", patron);

    TimeSource.timeSource = new MockTimeSource("1/20/2005");
    ActionResult result = booksController.handle(request);
    MediaGateway mediaGateway = helper.getMediaGateway();
    List loanReceipts = mediaGateway.findAllLoanReceiptsFor(helper.getThePatron().getId());
    assertEquals(0, loanReceipts.size());
    assertTrue(result.getWarningMessage().length() > 0);
  }

  public void testPostReturnCopyOfUnknownBookGivesError() throws Exception {
    postReturn("returnCopy");
    helper.loadLibraryWithBookAndPatron();
    Patron patron = helper.getThePatron();

    request.setParameter("copyId", "unknown copy id");
    request.getSession().setAttribute("patron", patron);
    ActionResult result = booksController.handle(request);

    assertTrue(result.getErrorMessage().length() > 0);
  }

}
