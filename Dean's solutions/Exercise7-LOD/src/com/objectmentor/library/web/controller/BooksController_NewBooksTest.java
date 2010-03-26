package com.objectmentor.library.web.controller;

import junit.framework.TestCase;

import com.objectmentor.library.gateways.BookGateway;
import com.objectmentor.library.mocks.MockHttpServletRequest;
import com.objectmentor.library.models.Book;

public class BooksController_NewBooksTest extends TestCase {

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
  }

  public void testAddNewValidIsbnAddsOneCopy() throws Exception {
    request.setParameter("newIsbn", "1");

    ActionResult result = booksController.handle(request);
    assertTrue(bookGateway.contains("1"));
    assertEquals(1, bookGateway.mediaCount());
    assertEquals(1, bookGateway.copyCount());
    assertTrue(result.getInfoMessage().length() > 0);
    assertEquals("", result.getWarningMessage());
    assertEquals("", result.getErrorMessage());
  }

  public void testAddNewValidIsbnWithEmptyNumberOfCopiesAddsOneCopy() throws Exception {
    request.setParameter("newIsbn", "1");
    request.setParameter("newIsbnNumberOfCopies", "");

    ActionResult result = booksController.handle(request);
    assertTrue(bookGateway.contains("1"));
    assertEquals(1, bookGateway.mediaCount());
    assertEquals(1, bookGateway.copyCount());
    assertTrue(result.getInfoMessage().length() > 0);
    assertEquals("", result.getWarningMessage());
    assertEquals("", result.getErrorMessage());
  }

  public void testAddNewValidIsbnWithMultipleCopies() throws Exception {
    request.setParameter("newIsbn", "1");
    request.setParameter("newIsbnNumberOfCopies", "5");

    ActionResult result = booksController.handle(request);
    assertTrue(bookGateway.contains("1"));
    assertEquals(1, bookGateway.mediaCount());
    assertEquals(5, bookGateway.copyCount());
    assertTrue(result.getInfoMessage().length() > 0);
    assertEquals("", result.getWarningMessage());
    assertEquals("", result.getErrorMessage());
  }

  public void testAddNewValidIsbnWithNonnumericNumberOfCopiesFails() throws Exception {
  	doTestAddNewValidIsbnWithInvalidNumberOfCopies("xx");
  }

  public void testAddNewValidIsbnWithNegativeNumberOfCopiesFails() throws Exception {
  	doTestAddNewValidIsbnWithInvalidNumberOfCopies("-1");
  }
  
  public void testAddNewValidIsbnWithZeroNumberOfCopiesFails() throws Exception {
  	doTestAddNewValidIsbnWithInvalidNumberOfCopies("0");
  }
  
  private void doTestAddNewValidIsbnWithInvalidNumberOfCopies(String numberOfCopies) throws Exception {
    request.setParameter("newIsbn", "1");
    request.setParameter("newIsbnNumberOfCopies", numberOfCopies);

    ActionResult result = booksController.handle(request);
    assertEquals(0, bookGateway.mediaCount());
    assertEquals(0, bookGateway.copyCount());
    assertEquals("", result.getInfoMessage());
    assertEquals("", result.getWarningMessage());
    assertTrue(result.getErrorMessage().length() > 0);
  }

  public void testFailToAddMissingIsbn() throws Exception {
    ActionResult result = booksController.handle(request);
    assertEquals(0, bookGateway.mediaCount());
    assertEquals(0, bookGateway.copyCount());
    assertEquals("", result.getInfoMessage());
    assertEquals("", result.getWarningMessage());
    assertTrue(result.getErrorMessage().length() > 0);
  }

  public void testFailToAddEmptyIsbn() throws Exception {
    request.setParameter("newIsbn", "");

    ActionResult result = booksController.handle(request);
    assertEquals(0, bookGateway.mediaCount());
    assertEquals(0, bookGateway.copyCount());
    assertEquals("", result.getInfoMessage());
    assertEquals("", result.getWarningMessage());
    assertTrue(result.getErrorMessage().length() > 0);
  }

  public void testAddNewCopiesForExistingIsbn() throws Exception {
  	bookGateway.addCopy(new Book("1", "title", "author"));
  	request.setParameter("newCopies_1", "3");
    ActionResult result = booksController.handle(request);
    assertTrue(bookGateway.contains("1"));
    assertEquals(result.toString(), 1, bookGateway.mediaCount());
    assertEquals(result.toString(), 4, bookGateway.findAllCopies("1").size());
    assertTrue(result.getInfoMessage().length() > 0);
    assertEquals("", result.getWarningMessage());
    assertEquals("", result.getErrorMessage());
  }

  public void testAddNewCopiesFailsForEmptyCatalog() throws Exception {
  	request.setParameter("newCopies_1", "3");
    ActionResult result = booksController.handle(request);
    assertEquals(0, bookGateway.mediaCount());
    assertEquals("", result.getInfoMessage());
    assertEquals("", result.getWarningMessage());
    assertTrue(result.getErrorMessage().length() > 0);
  }

  public void testAddNewCopiesIgnoredForNonexistingIsbnInANonEmptyCatalog() throws Exception {
  	bookGateway.addCopy(new Book("1", "title", "author"));
  	request.setParameter("newCopies_2", "3");
    ActionResult result = booksController.handle(request);
    assertEquals(1, bookGateway.mediaCount());
    assertEquals("", result.getInfoMessage());
    assertEquals("", result.getWarningMessage());
    assertEquals("", result.getErrorMessage());
  }

}