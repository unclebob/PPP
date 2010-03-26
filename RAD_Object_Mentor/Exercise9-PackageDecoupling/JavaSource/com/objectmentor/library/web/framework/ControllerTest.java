package com.objectmentor.library.web.framework;

import com.objectmentor.library.mocks.MockHttpServletRequest;
import com.objectmentor.library.web.controller.*;
import junit.framework.TestCase;

public class ControllerTest extends TestCase {
  private MockHttpServletRequest request;
  private ActionResult getResult;
  private ActionResult postResult;
  private TestableController controller;


  protected void setUp() throws Exception {
    getResult = new ActionResult("get");
    postResult = new ActionResult("post");
    controller = new TestableController();
  }

  public void testHandlePost() throws Exception {
    createRequest("POST");
    ActionResult result = controller.handle(request);
    assertSame(postResult, result);
  }

  public void testHandleGet() throws Exception {
    createRequest("GET");
    ActionResult result = controller.handle(request);
    assertSame(getResult, result);
  }

  private void createRequest(String method) {
    request = new MockHttpServletRequest(method);
    request.setAttribute("action_name", "someAction");
  }

  public void testGetIntegerParameterWithValidInput() throws Exception {
    createRequest("POST");
    request.setParameter("key", "3");
    controller.handle(request);
    assertEquals(3, controller.getParsedIntegerParameter("key", 0));
  }

  public void testGetIntegerParameterWithNoInput() throws Exception {
    createRequest("POST");
    controller.handle(request);
    assertEquals(0, controller.getParsedIntegerParameter("key", 0));
  }

  public void testGetIntegerParameterWithNonNumericInput() throws Exception {
    createRequest("POST");
    request.setParameter("key", "not a number");
    controller.handle(request);
    assertEquals(0, controller.getParsedIntegerParameter("key", 3));
  }

  private class TestableController extends LibraryController {
    public ActionResult someAction() {
      if (isPost()) {
        return postResult;
      } else if (isGet()) {
        return getResult;
      } else {
        return null;
      }
    }

    public int getParsedIntegerParameter(String key, int defaultValue) {
      return getIntegerParameter(key, defaultValue);
    }
  }

}
