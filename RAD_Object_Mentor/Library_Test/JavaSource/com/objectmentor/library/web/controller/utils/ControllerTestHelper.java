package com.objectmentor.library.web.controller.utils;

import com.objectmentor.library.application.*;
import com.objectmentor.library.application.gateways.MediaGateway;
import com.objectmentor.library.application.libraryRules.Library;
import com.objectmentor.library.application.models.*;
import com.objectmentor.library.mocks.MockServiceProvider;
import com.objectmentor.library.web.framework.mocks.MockHttpServletRequest;

public class ControllerTestHelper {
  private Application application;
  private Library library;
  private MediaCopy mediaCopy;
  private Patron patron;
  private MockHttpServletRequest request;
  private PatronRegistrar patronRegistrar;

  public ControllerTestHelper() {
    application = new Application(new MockServiceProvider());
    library = application.getLibrary();
    patronRegistrar = application.getPatronRegistrar();
  }

  public Library getLibrary() {
    return application.getLibrary();
  }

  public Library loadLibraryWithBook() {
    mediaCopy = library.acceptBook("1");
    return library;
  }

  public Library loadLibraryWithBookAndPatron() {
    library = loadLibraryWithBook();
    patron = new Patron("First", "M", "Last", new Address("street1", "street2", "city", "state", "zip"), "phone");
    patronRegistrar.registerPatron(patron);
    return library;
  }

  public Library loadLibraryWithBookAndPatronWhereBookCannotBeBorrowed() {
    mediaCopy = library.acceptBook("2");
    mediaCopy.getMedia().setAgeRestriction(100);
    patron = new Patron("First", "M", "Last", new Address("street1", "street2", "city", "state", "zip"), "phone");
    patronRegistrar.registerPatron(patron);
    return library;
  }

  public MediaCopy getTheMediaCopy() {
    return mediaCopy;
  }

  public Patron getThePatron() {
    return patron;
  }

  public Application getApplication() {
    return application;
  }

  public MockHttpServletRequest createMockRequest(String method) {
    request = new MockHttpServletRequest(method);
    request.getSession().setAttribute("Application", application);
    return request;
  }

  public void setActionName(String actionName) {
    request.setAttribute("action_name", actionName);
  }

  public MediaGateway getMediaGateway() {
    return application.getMediaGateway();
  }

  public boolean shouldRender(String path) {
    String actual = (String) request.getAttribute("include_path");
    if (actual.endsWith(path))
      return true;
    else {
      System.out.println("should render: " + path + " but was: " + actual);
      return false;
    }
  }

  public PatronRegistrar getPatronRegistrar() {
    return patronRegistrar;
  }
}
