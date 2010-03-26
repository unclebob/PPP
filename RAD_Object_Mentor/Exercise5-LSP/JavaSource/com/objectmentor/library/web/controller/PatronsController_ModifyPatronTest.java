package com.objectmentor.library.web.controller;

import com.objectmentor.library.gateways.PatronGateway;
import com.objectmentor.library.mocks.MockHttpServletRequest;
import com.objectmentor.library.models.*;
import com.objectmentor.library.web.utils.ControllerTestHelper;
import junit.framework.TestCase;

import javax.servlet.ServletException;

public class PatronsController_ModifyPatronTest extends TestCase {
  private Controller patronsController;
  private MockHttpServletRequest request;
  private PatronGateway patronGateway;
  private Address address;
  private Patron patron;

  protected void setUp() throws Exception {
    ControllerTestHelper helper = new ControllerTestHelper();
    request = helper.createMockRequest("POST");
    helper.setActionName("manage");
    patronsController = new PatronsController();
    address = new Address("street1", "street2", "city", "state", "zip");
    patron = new Patron("Bob", "C", "Martin", address, "");
    patronGateway = helper.getApplication().getPatronGateway();
  }

  public void testModifyPatronSucceedsWithValidData() throws ServletException {
    patron.setId("" + patronGateway.getNextId());
    patronGateway.getPatronMap().put(patron.getId(), patron);
    Patron patron2 = patronGateway.getPatronList().iterator().next();
    request.setParameter("firstName_" + patron2.getId(), "Bob2");
    request.setParameter("middleInitial_" + patron2.getId(), "");
    request.setParameter("lastName_" + patron2.getId(), "Martin2");
    ActionResult result = patronsController.handle(request);
    assertEquals(1, patronGateway.getPatronList().size());
    assertTrue(result.getInfoMessage().length() > 0);
    assertEquals("", result.getWarningMessage());
    assertEquals("", result.getErrorMessage());
  }

  public void testModifyPatronDoesNothingWithIdenticalData() throws ServletException {
    patron.setId("" + patronGateway.getNextId());
    patronGateway.getPatronMap().put(patron.getId(), patron);
    Patron patron2 = patronGateway.getPatronList().iterator().next();
    request.setParameter("firstName_" + patron2.getId(), "Bob");
    request.setParameter("middleInitial_" + patron2.getId(), "C");
    request.setParameter("lastName_" + patron2.getId(), "Martin");
    ActionResult result = patronsController.handle(request);
    assertEquals(1, patronGateway.getPatronList().size());
    assertEquals("", result.getInfoMessage());
    assertEquals("", result.getWarningMessage());
    assertEquals("", result.getErrorMessage());
  }

  public void testModifyPatronFailsWithMissingFirstName() throws ServletException {
    patron.setId("" + patronGateway.getNextId());
    patronGateway.getPatronMap().put(patron.getId(), patron);
    Patron patron2 = patronGateway.getPatronList().iterator().next();
    request.setParameter("firstName_" + patron2.getId(), "");
    request.setParameter("middleInitial_" + patron2.getId(), "C");
    request.setParameter("lastName_" + patron2.getId(), "Martin");
    ActionResult result = patronsController.handle(request);
    assertEquals(1, patronGateway.getPatronList().size());
    assertEquals("", result.getInfoMessage());
    assertEquals("", result.getWarningMessage());
    assertTrue(result.getErrorMessage().length() > 0);
  }

  public void testModifyPatronFailsWithMissingLastName() throws ServletException {
	    patron.setId("" + patronGateway.getNextId());
	    patronGateway.getPatronMap().put(patron.getId(), patron);
    Patron patron2 = patronGateway.getPatronList().iterator().next();
    request.setParameter("firstName_" + patron2.getId(), "Bob2");
    request.setParameter("middleInitial_" + patron2.getId(), "C");
    request.setParameter("lastName_" + patron2.getId(), "");
    ActionResult result = patronsController.handle(request);
    assertEquals(1, patronGateway.getPatronList().size());
    assertEquals("", result.getInfoMessage());
    assertEquals("", result.getWarningMessage());
    assertTrue(result.getErrorMessage().length() > 0);
  }
}
