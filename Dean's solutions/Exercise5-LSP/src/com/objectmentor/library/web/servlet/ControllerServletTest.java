package com.objectmentor.library.web.servlet;

import javax.servlet.ServletException;

import junit.framework.TestCase;

import com.objectmentor.library.mocks.MockHttpServletRequest;
import com.objectmentor.library.mocks.MockHttpServletResponse;
import com.objectmentor.library.web.controller.TestController;

public class ControllerServletTest extends TestCase {
  private ControllerServlet servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
  private boolean servletException;

  protected void setUp() throws Exception {
    super.setUp();
    servlet = new ControllerServlet();
    response = new MockHttpServletResponse();
    servletException = false;
  }

  public void testDoGet() throws Exception {
    request = new MockHttpServletRequest("GET", "/Library", "/Library/test/action.do");
    try {
      servlet.doGet(request, response);
    }
    catch (ServletException e) {
      servletException = true;
    }
    assertTrue(servletException);
    assertTrue(TestController.getWasCalled);
    assertSame(TestController.theRequest, request);
    assertEquals("action", request.getAttribute("action_name"));
  }

  public void testDoPost() throws Exception {
    request = new MockHttpServletRequest("POST", "/Library", "/Library/test/action.do");
    try {
      servlet.doPost(request, response);
    }
    catch (ServletException e) {
      servletException = true;
    }
    assertTrue(servletException);
    assertTrue(TestController.postWasCalled);
    assertSame(TestController.theRequest, request);
    assertEquals("action", request.getAttribute("action_name"));
  }
  
}
