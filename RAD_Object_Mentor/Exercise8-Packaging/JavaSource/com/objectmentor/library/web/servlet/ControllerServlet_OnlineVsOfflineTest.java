package com.objectmentor.library.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;

import junit.framework.TestCase;

import com.objectmentor.library.mocks.MockHttpServletRequest;
import com.objectmentor.library.mocks.MockHttpServletResponse;
import com.objectmentor.library.offline.InMemoryIsbnService;
import com.objectmentor.library.web.controller.Application;

public class ControllerServlet_OnlineVsOfflineTest extends TestCase {
  private ControllerServlet servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;

  protected void setUp() throws Exception {
    super.setUp();
    servlet = new ControllerServlet();
    response = new MockHttpServletResponse();
    request = new MockHttpServletRequest("POST", "/Library",
        "/Library/test/action.do");
  }

  public void testRunningOnlineByDefault() {
    assertTrue(servlet.getRunningOnline());
  }

  public void testRunningOfflineSetThroughRequestParameterWithTrueValue()
      throws Exception {
    request.setParameter("offline", "true");
    doGet();
    assertFalse(servlet.getRunningOnline());
  }

  public void testRunningOfflineSetThroughRequestParameterWithEmptyValueIsTrue()
      throws Exception {
    request.setParameter("offline", "");
    doGet();
    assertFalse(servlet.getRunningOnline());
  }

  public void testRunningOfflineSetThroughRequestParameterWithFalseSetsOnline()
      throws Exception {
    request.setParameter("offline", "false");
    doGet();
    assertTrue(servlet.getRunningOnline());
  }

  public void testRunningOnlineSetThroughRequestParameterWithTrueValue()
      throws Exception {
    request.setParameter("online", "true");
    doGet();
    assertTrue(servlet.getRunningOnline());
  }

  public void testRunningOnlineSetThroughRequestParameterWithEmptyValueIsTrue()
      throws Exception {
    request.setParameter("online", "");
    doGet();
    assertTrue(servlet.getRunningOnline());
  }

  public void testRunningOnlineSetThroughRequestParameterWithFalseSetsOffline()
      throws Exception {
    request = new MockHttpServletRequest("POST", "/Library",
        "/Library/test/action.do");
    request.setParameter("online", "false");
    doGet();
    assertFalse(servlet.getRunningOnline());
  }

  public void testRunningOfflineSetsMockIsbnServiceOnApplicationAndLibraryAndCatalog()
      throws Exception {
    request = new MockHttpServletRequest("POST", "/Library",
        "/Library/test/action.do");
    request.setParameter("offline", "");
    doGet();
    Application application = (Application) request.getSession().getAttribute(
        "Application");
    assertEquals(InMemoryIsbnService.class, application.getIsbnService()
        .getClass());
  }

  private void doGet() throws IOException {
    try {
      servlet.doGet(request, response);
    } catch (ServletException e) {
    }
  }

}
