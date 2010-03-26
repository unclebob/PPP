package com.objectmentor.library.web.controller;

import com.objectmentor.library.web.utils.ControllerTestHelper;
import junit.framework.TestCase;

import javax.servlet.http.HttpServletRequest;

public class WelcomeControllerTest extends TestCase {

  public void testIndexShouldRenderWelcomeJSP() throws Exception {
    ControllerTestHelper helper = new ControllerTestHelper();
    HttpServletRequest request = helper.createMockRequest("GET");
    helper.setActionName("index");
    WelcomeController controller = new WelcomeController();
    assertNotNull(controller.handle(request));
    assertTrue(controller.handle(request).empty());
    assertEquals("/WEB-INF/pages/welcome.jsp", request.getAttribute("include_path"));
  }

}
