package com.objectmentor.library.web.controller;

import com.objectmentor.library.web.framework.ActionResult;
import com.objectmentor.library.web.framework.mocks.MockHttpServletRequest;
import junit.framework.TestCase;

public class LibraryControllerTest extends TestCase {
  private MockHttpServletRequest request;
  private ActionResult getResult;
  private ActionResult postResult;
  private TestableController controller;


  protected void setUp() throws Exception {
    getResult = new ActionResult("get");
    postResult = new ActionResult("post");
    controller = new TestableController();
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

  public class TestableController extends LibraryController {
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
