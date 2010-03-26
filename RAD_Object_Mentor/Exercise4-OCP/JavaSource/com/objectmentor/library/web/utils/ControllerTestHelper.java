package com.objectmentor.library.web.utils;

import javax.servlet.http.HttpServletRequest;

import com.objectmentor.library.libraryRules.Library;
import com.objectmentor.library.mocks.MockHttpServletRequest;
import com.objectmentor.library.models.*;
import com.objectmentor.library.web.controller.Application;

public class ControllerTestHelper {
  private Application application;
  private Library library;
  private MediaCopy mediaCopy;
  private Patron patron;

  public ControllerTestHelper() {
    application = new Application();
    library = application.getLibrary();
  }

  public Library createEmptyLibrary() {
    return library;
  }

  public Library createLibraryWithBook() {
    mediaCopy = library.acceptBook("1");
    return library;
  }

  public Library createLibraryWithBookAndPatron() {
    library = createLibraryWithBook();
    patron = new Patron("First", "M", "Last", new Address("street1", "street2", "city", "state", "zip"), "phone");
    library.registerPatron(patron);
    return library;
  }

  public Library createLibraryWithBookAndPatronWhereBookCannotBeBorrowed() {
    mediaCopy = library.acceptBook("2");
    mediaCopy.getMedia().setAgeRestriction(100);
    patron = new Patron("First", "M", "Last", new Address("street1", "street2", "city", "state", "zip"), "phone");
    library.registerPatron(patron);
    return library;
  }

  public MediaCopy getLastMediaCopy() {
    return mediaCopy;
  }

  public Patron getLastPatron() {
    return patron;
  }

  public Application getApplication() {
    return application;
  }
  
  public MockHttpServletRequest createMockRequest(String method) {
    MockHttpServletRequest request = new MockHttpServletRequest(method);
    request.getSession().setAttribute("Application", application);
    return request;
  }

  public void setActionName(HttpServletRequest request, String actionName) {
    request.setAttribute("action_name", actionName);    
  }
}
