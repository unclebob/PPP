package com.objectmentor.library.web.controller;

import javax.servlet.http.HttpServletRequest;

import junit.framework.TestCase;

import com.objectmentor.library.web.utils.ControllerTestHelper;

public class WelcomeControllerTest extends TestCase {

  public void testIndexShouldRenderWelcomeJSP() throws Exception {
    ControllerTestHelper helper = new ControllerTestHelper();
    HttpServletRequest request = helper.createMockRequest("GET");
    helper.setActionName(request, "index");
    WelcomeController controller = new WelcomeController();
    assertNotNull(controller.handle(request));
    assertTrue(controller.handle(request).empty());
    assertEquals("/WEB-INF/pages/welcome.jsp", request.getAttribute("include_path"));
  }
  
}
