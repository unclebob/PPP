package com.objectmentor.library.web.controller;

import junit.framework.TestCase;

import com.objectmentor.library.gateways.PatronGateway;
import com.objectmentor.library.mocks.MockHttpServletRequest;
import com.objectmentor.library.web.utils.ControllerTestHelper;

public class PatronsController_NewPatronTest extends TestCase {
  private Controller patronsController;
  private MockHttpServletRequest request;
  private PatronGateway patronGateway;

  protected void setUp() throws Exception {
    ControllerTestHelper helper = new ControllerTestHelper();
    patronGateway = helper.getApplication().getPatronGateway();
    request = helper.createMockRequest("POST");
    helper.setActionName("manage");
    patronsController = new PatronsController();
  }

  public void testNewPatronSucceedsWithAllNameFields() throws Exception {
    request.setParameter("newFirstName", "Bob");
    request.setParameter("newMiddleInitial", "C");
    request.setParameter("newLastName", "Martin");
    ActionResult result = patronsController.handle(request);
    assertEquals(1, patronGateway.getPatronList().size());
    assertTrue(result.getInfoMessage().length() > 0);
    assertEquals("", result.getWarningMessage());
    assertEquals("", result.getErrorMessage());
  }

  public void testNewPatronSucceedsWithFirstAndLastNameFields() throws Exception {
    request.setParameter("newFirstName", "Bob");
    request.setParameter("newLastName", "Martin");
    ActionResult result = patronsController.handle(request);
    assertEquals(1, patronGateway.getPatronList().size());
    assertTrue(result.getInfoMessage().length() > 0);
    assertEquals("", result.getWarningMessage());
    assertEquals("", result.getErrorMessage());
  }

  public void testNewPatronFailsWithNoFirstNameField() throws Exception {
    request.setParameter("newLastName", "Martin");
    ActionResult result = patronsController.handle(request);
    assertEquals(0, patronGateway.getPatronList().size());
    assertEquals("", result.getInfoMessage());
    assertEquals("", result.getWarningMessage());
    assertTrue(result.getErrorMessage().length() > 0);
  }

  public void testNewPatronFailsWithNoLastNameField() throws Exception {
    request.setParameter("newFirstName", "Bob");
    ActionResult result = patronsController.handle(request);
    assertEquals(0, patronGateway.getPatronList().size());
    assertEquals("", result.getInfoMessage());
    assertEquals("", result.getWarningMessage());
    assertTrue(result.getErrorMessage().length() > 0);
  }
}
