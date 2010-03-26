package com.objectmentor.library.web.controller;

import java.util.List;

import junit.framework.TestCase;

import com.objectmentor.library.mocks.MockHttpServletRequest;
import com.objectmentor.library.web.utils.ControllerTestHelper;

public class CompactDiscsController_GetTest extends TestCase {

  private MockHttpServletRequest request;
  private CompactDiscsController compactDiscsController;

  protected void setUp() throws Exception {
    ControllerTestHelper helper = new ControllerTestHelper();
    request = helper.createMockRequest("GET");
    helper.setActionName("manage");
    request.setAttribute("action_name", "manage");
    compactDiscsController = new CompactDiscsController();
  }

  public void testShouldProvideListOfCDsToRequest() throws Exception {
    compactDiscsController.handle(request);
    assertNotNull(request.getAttribute("compactDiscs"));
    assertEquals(0, ((List) request.getAttribute("compactDiscs")).size());
  }

}