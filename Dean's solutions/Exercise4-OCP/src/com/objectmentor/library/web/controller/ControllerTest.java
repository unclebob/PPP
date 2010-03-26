package com.objectmentor.library.web.controller;

import javax.servlet.http.HttpServletRequest;

import junit.framework.TestCase;

import com.objectmentor.library.mocks.MockHttpServletRequest;

public class ControllerTest extends TestCase {
	private HttpServletRequest request;
  private ActionResult getResult;
  private ActionResult postResult;
  private Controller controller;

  
  protected void setUp() throws Exception {
    getResult  = new ActionResult("get");
    postResult = new ActionResult("post");
    controller = new TestableController();
  }
  
	public void testHandlePost() throws Exception {
		request = new MockHttpServletRequest("POST");
    request.setAttribute("action_name", "someAction");
		ActionResult result = controller.handle(request);
		assertSame(postResult, result);
	}

	public void testHandleGet() throws Exception {
		request = new MockHttpServletRequest("GET");
    request.setAttribute("action_name", "someAction");
		ActionResult result = controller.handle(request);
		assertSame(getResult, result);
	}
  
  private class TestableController extends Controller {
    public ActionResult someAction() {
      if (isPost()) {
        return postResult;
      } else if (isGet()) {
        return getResult;
      } else {
        return null;
      }
    }
    
  }

}
