package com.objectmentor.library.web.controller;

import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import com.objectmentor.library.gateways.MediaGateway;
import com.objectmentor.library.mocks.MockHttpServletRequest;
import com.objectmentor.library.models.Media;
import com.objectmentor.library.models.MediaCopy;

public class BooksController_DeleteBooksTest extends TestCase {

  private Application application;
  private MediaGateway mockInMemoryMediaGateway;
  private MockHttpServletRequest request;
  private Controller booksController;

  protected void setUp() throws Exception {
    application = new Application();
    request = new MockHttpServletRequest("POST");
    request.setAttribute("action_name", "manage");
    mockInMemoryMediaGateway = (MediaGateway) application.getMediaGateway();
    request.getSession().setAttribute("Application", application);
    booksController = new BooksController();
    mockInMemoryMediaGateway.addCopies(new Media("1", "title1", "author1", Media.BOOK), 1);
    mockInMemoryMediaGateway.addCopies(new Media("2", "title2", "author2", Media.BOOK), 2);
    mockInMemoryMediaGateway.addCopies(new Media("3", "title3", "author3", Media.BOOK), 3);
  }

  public void testDeleteThreeCopiesForValidIsbn() throws Exception {
    assertISBNKnownWithEntries("3", 3);
    assertEquals(6, mockInMemoryMediaGateway.copyCount());
    List copies = mockInMemoryMediaGateway.findAllCopies("3");
    for (Iterator iter = copies.iterator(); iter.hasNext();) {
      MediaCopy copy = (MediaCopy) iter.next();
      String id = copy.getId();
      request.setParameter("delete_" + id, "on");
    }

    ActionResult result = booksController.handle(request);
    assertTrue(mockInMemoryMediaGateway.contains("3"));
    assertISBNKnownWithEntries("3", 0);
    assertEquals(3, mockInMemoryMediaGateway.copyCount());

    assertTrue(result.getInfoMessage().length() > 0);
    assertEquals("", result.getWarningMessage());
    assertEquals("", result.getErrorMessage());
  }

  public void testDeleteCopiesForUnknownIsbnDoesNothing() throws Exception {
    int mediaCount = mockInMemoryMediaGateway.copyCount();
    request.setParameter("delete_100", "on");
    assertEquals(mediaCount, mockInMemoryMediaGateway.copyCount());
    ActionResult result = booksController.handle(request);

    assertEquals("", result.getInfoMessage());
    assertEquals("", result.getWarningMessage());
    assertEquals("", result.getErrorMessage());
  }

  private void assertISBNKnownWithEntries(String isbn, int numberOfCopiesExpected) {
    assertEquals(3, mockInMemoryMediaGateway.mediaCount());
    assertEquals(numberOfCopiesExpected, mockInMemoryMediaGateway.findAllCopies(isbn).size());
  }
}