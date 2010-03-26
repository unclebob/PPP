package com.objectmentor.library.web.controller;

import com.objectmentor.library.mocks.*;
import com.objectmentor.library.models.*;
import com.objectmentor.library.offline.InMemoryMediaGateway;

import junit.framework.TestCase;

import java.util.*;

public class BooksController_DeleteBooksTest extends TestCase {

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
    mockMediaGateway.addCopies(new Book("1", "title1", "author1"), 1);
    mockMediaGateway.addCopies(new Book("2", "title2", "author2"), 2);
    mockMediaGateway.addCopies(new Book("3", "title3", "author3"), 3);
  }

  public void testDeleteThreeCopiesForValidIsbn() throws Exception {
    assertISBNKnownWithEntries("3", 3);
    assertEquals(6, mockMediaGateway.copyCount());
    List copies = mockMediaGateway.findAllCopiesByIsbn("3");
    for (Iterator iter = copies.iterator(); iter.hasNext();) {
      MediaCopy copy = (MediaCopy) iter.next();
      String id = copy.getId();
      request.setParameter("delete_" + id, "on");
    }

    ActionResult result = booksController.handle(request);
    assertTrue(mockMediaGateway.contains("3"));
    assertISBNKnownWithEntries("3", 0);
    assertEquals(3, mockMediaGateway.copyCount());

    assertTrue(result.getInfoMessage().length() > 0);
    assertEquals("", result.getWarningMessage());
    assertEquals("", result.getErrorMessage());
  }

  public void testDeleteCopiesForUnknownIsbnDoesNothing() throws Exception {
    int mediaCount = mockMediaGateway.copyCount();
    request.setParameter("delete_100", "on");
    assertEquals(mediaCount, mockMediaGateway.copyCount());
    ActionResult result = booksController.handle(request);

    assertEquals("", result.getInfoMessage());
    assertEquals("", result.getWarningMessage());
    assertEquals("", result.getErrorMessage());
  }

  private void assertISBNKnownWithEntries(String isbn, int numberOfCopiesExpected) {
    assertEquals(3, mockMediaGateway.mediaCount());
    assertEquals(numberOfCopiesExpected, mockMediaGateway.findAllCopiesByIsbn(isbn).size());
  }
}