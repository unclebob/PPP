package com.objectmentor.library.web.controller;

import com.objectmentor.library.application.Application;
import com.objectmentor.library.utils.*;
import com.objectmentor.library.web.controller.utils.ControllerTestHelper;
import com.objectmentor.library.web.framework.mocks.MockHttpServletRequest;
import junit.framework.TestCase;

public class DebugControllerTest extends TestCase {
  private ControllerTestHelper helper;
  private DebugController controller;
  private MockHttpServletRequest request;

  protected void setUp() throws Exception {
    helper = new ControllerTestHelper();
    controller = new DebugController();
  }

  public void testGetSetDateRendersSetDate() throws Exception {
    makeRequest("GET", "setDate");
    controller.handle(request);
    assertTrue(helper.shouldRender("debug/setDate.jsp"));
  }

  private void makeRequest(String method, String action) {
    request = helper.createMockRequest(method);
    helper.setActionName(action);
  }

  public void testPostSetDateRendersWelcome() throws Exception {
    makeRequest("POST", "setDate");
    request.setParameter("date", "1/1/2005");
    controller.handle(request);
    assertTrue(helper.shouldRender("welcome.jsp"));
    assertEquals(DateUtil.dateFromString("1/1/2005"), TimeSource.time());
  }

  public void testLoadTestData() throws Exception {
    makeRequest("GET", "loadTestData");
    controller.handle(request);
    Application app = (Application) request.getSession().getAttribute("Application");
    assertEquals(6, app.getPatronGateway().countActive());
    assertEquals(10, app.getMediaGateway().copyCount());
  }

  public void testClearData() throws Exception {
    makeRequest("GET", "loadTestData");
    controller.handle(request);
    makeRequest("GET", "clearData");
    controller.handle(request);
    Application app = (Application) request.getSession().getAttribute("Application");
    assertEquals(0, app.getPatronGateway().countActive());
    assertEquals(0, app.getMediaGateway().copyCount());
  }


}
