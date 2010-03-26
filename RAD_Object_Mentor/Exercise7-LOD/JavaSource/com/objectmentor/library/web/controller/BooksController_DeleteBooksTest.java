package com.objectmentor.library.web.controller;

import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import com.objectmentor.library.gateways.BookGateway;
import com.objectmentor.library.mocks.MockHttpServletRequest;
import com.objectmentor.library.models.Book;
import com.objectmentor.library.models.MediaCopy;

public class BooksController_DeleteBooksTest extends TestCase {

  private Application application;
  private BookGateway bookGateway;
  private MockHttpServletRequest request;
  private Controller booksController;

  protected void setUp() throws Exception {
    application = new Application(new MockServiceProvider());
    request = new MockHttpServletRequest("POST");
    request.setAttribute("action_name", "manage");
    bookGateway = application.getBookGateway();
    request.getSession().setAttribute("Application", application);
    booksController = new BooksController();
    bookGateway.addCopies(new Book("1", "title1", "author1"), 1);
    bookGateway.addCopies(new Book("2", "title2", "author2"), 2);
    bookGateway.addCopies(new Book("3", "title3", "author3"), 3);
  }

  public void testDeleteThreeCopiesForValidIsbn() throws Exception {
    assertISBNKnownWithEntries("3", 3);
    assertEquals(6, bookGateway.copyCount());
    List copies = bookGateway.findAllCopies("3");
    for (Iterator iter = copies.iterator(); iter.hasNext();) {
      MediaCopy copy = (MediaCopy) iter.next();
      String id = copy.getId();
      request.setParameter("delete_" + id, "on");
    }

    ActionResult result = booksController.handle(request);
    assertTrue(bookGateway.contains("3"));
    assertISBNKnownWithEntries("3", 0);
    assertEquals(3, bookGateway.copyCount());

    assertTrue(result.getInfoMessage().length() > 0);
    assertEquals("", result.getWarningMessage());
    assertEquals("", result.getErrorMessage());
  }

  public void testDeleteCopiesForUnknownIsbnDoesNothing() throws Exception {
    int mediaCount = bookGateway.copyCount();
    request.setParameter("delete_100", "on");
    assertEquals(mediaCount, bookGateway.copyCount());
    ActionResult result = booksController.handle(request);

    assertEquals("", result.getInfoMessage());
    assertEquals("", result.getWarningMessage());
    assertEquals("", result.getErrorMessage());
  }

  private void assertISBNKnownWithEntries(String isbn, int numberOfCopiesExpected) {
    assertEquals(3, bookGateway.mediaCount());
    assertEquals(numberOfCopiesExpected, bookGateway.findAllCopies(isbn).size());
  }
}