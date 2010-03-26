package com.objectmentor.library.web.controller;

import junit.framework.TestCase;

import com.objectmentor.library.libraryRules.TimeSource;
import com.objectmentor.library.mocks.MockHttpServletRequest;
import com.objectmentor.library.utils.DateUtil;
import com.objectmentor.library.web.utils.ControllerTestHelper;

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
    assertEquals(6, app.getPatronGateway().getPatronList().size());
  }

  public void testClearData() throws Exception {
    makeRequest("GET", "loadTestData");
    controller.handle(request);
    makeRequest("GET", "clearData");
    controller.handle(request);
    Application app = (Application) request.getSession().getAttribute("Application");
    assertEquals(0, app.getPatronGateway().getPatronList().size());
    assertEquals(0, app.getMediaGateway().copyCount());
  }

}
