package com.objectmentor.library.web.controller;

import com.objectmentor.library.gateways.PatronGateway;
import com.objectmentor.library.mocks.MockHttpServletRequest;
import com.objectmentor.library.models.*;
import com.objectmentor.library.web.utils.ControllerTestHelper;
import junit.framework.TestCase;

import javax.servlet.ServletException;
import java.util.Collection;

public class PatronsController_DeletedPatronTest extends TestCase {
  private Controller patronsController;
  private MockHttpServletRequest request;
  private PatronGateway patronGateway;
  private Address address;
  private Patron patron;

  protected void setUp() throws Exception {
    ControllerTestHelper helper = new ControllerTestHelper();
    patronGateway = helper.getApplication().getPatronGateway();
    request = helper.createMockRequest("POST");
    helper.setActionName("manage");
    patronsController = new PatronsController();
    address = new Address("street1", "street2", "city", "state", "zip");
    patron = new Patron("Bob", "C", "Martin", address, "");
    patron.setId("" + patronGateway.getNextId());
    patronGateway.getPatronMap().put(patron.getId(), patron);
  }

  public void testDeletePatronSucceedsWithValidId() throws ServletException {
    Collection<Patron> patrons = patronGateway.getPatronList();
    assertEquals(1, patrons.size());
    Patron patron2 = patrons.iterator().next();
    request.setParameter("delete_" + patron2.getId(), "on");
    ActionResult result = patronsController.handle(request);
    assertEquals(0, patronGateway.getPatronList().size());
    assertTrue(result.getInfoMessage().length() > 0);
    assertEquals("", result.getWarningMessage());
    assertEquals("", result.getErrorMessage());
  }

  public void testDeletePatronDoesNothingWithInvalidId() throws ServletException {
    Patron patron2 = patronGateway.getPatronList().iterator().next();
//		 Need to add fields for the existing patron, even though we no longer care...
    request.setParameter("firstName_" + patron2.getId(), "Bob");
    request.setParameter("middleInitial_" + patron2.getId(), "C");
    request.setParameter("lastName_" + patron2.getId(), "Martin");
    request.setParameter("delete_0", "on");
    ActionResult result = patronsController.handle(request);
    assertEquals(1, patronGateway.getPatronList().size());
    assertEquals("", result.getInfoMessage());
    assertEquals("", result.getWarningMessage());
    assertEquals("", result.getErrorMessage());
  }
}
