package com.objectmentor.library.web.controller;

import junit.framework.TestCase;

import com.objectmentor.library.mocks.MockHttpServletRequest;
import com.objectmentor.library.offline.InMemoryMediaGateway;

public class BooksController_ManageTest extends TestCase {

  private Application application;
  private InMemoryMediaGateway mockMediaGateway;
  private MockHttpServletRequest request;
  private Controller booksController;

  protected void setUp() throws Exception {
    application = new Application(new MockServiceProvider());
    request = new MockHttpServletRequest("POST");
    request.setAttribute("action_name", "manage");
    mockMediaGateway = (InMemoryMediaGateway) application.getMediaGateway();
    request.getSession().setAttribute("Application", application);
    booksController = new BooksController();
  }

  public void testAcceptValidIsbn() throws Exception {
    request.setParameter("newIsbn", "1");

    ActionResult result = booksController.handle(request);
    assertTrue(mockMediaGateway.contains("1"));
    assertEquals(1, mockMediaGateway.mediaCount());
    assertTrue(result.getInfoMessage().length() > 0);
    assertEquals("", result.getWarningMessage());
    assertEquals("", result.getErrorMessage());
  }

  public void testMissingIsbn() throws Exception {
    ActionResult result = booksController.handle(request);
    assertEquals(0, mockMediaGateway.mediaCount());
    assertEquals("", result.getInfoMessage());
    assertEquals("", result.getWarningMessage());
    assertTrue(result.getErrorMessage().length() > 0);
  }

  public void testEmptyIsbn() throws Exception {
    request.setParameter("isbn", "");

    ActionResult result = booksController.handle(request);
    assertEquals(0, mockMediaGateway.mediaCount());
    assertEquals("", result.getInfoMessage());
    assertEquals("", result.getWarningMessage());
    assertTrue(result.getErrorMessage().length() > 0);
  }
}