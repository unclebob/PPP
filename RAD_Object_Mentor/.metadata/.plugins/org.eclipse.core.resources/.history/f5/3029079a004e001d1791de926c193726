package com.objectmentor.library.web.controller;

import java.util.Collection;

import javax.servlet.ServletException;

import junit.framework.TestCase;

import com.objectmentor.library.gateways.PatronGateway;
import com.objectmentor.library.mocks.MockHttpServletRequest;
import com.objectmentor.library.models.Address;
import com.objectmentor.library.models.Patron;
import com.objectmentor.library.web.utils.ControllerTestHelper;

public class PatronsController_DeletedPatronTest extends TestCase {
	private Controller patronsController;
	private MockHttpServletRequest request;
  private PatronGateway patronGateway;
	private Address address;
	private Patron patron;

	protected void setUp() throws Exception {
		ControllerTestHelper helper = new ControllerTestHelper();
		patronGateway = helper.createEmptyLibrary().getPatronGateway();
    request = helper.createMockRequest("POST");
    helper.setActionName(request, "manage");
		patronsController = new PatronsController();
		address = new Address("street1", "street2", "city", "state", "zip");
		patron = new Patron("Bob", "C", "Martin", address, "");
	}

	public void testDeletePatronSucceedsWithValidId() throws ServletException {
		patronGateway.add(patron);
		Collection patrons = patronGateway.findAllPatrons();
		assertEquals(1, patrons.size());
		Patron patron2 = (Patron) patrons.iterator().next();
		request.setParameter("delete_"+patron2.getId(), "on");
    ActionResult result = patronsController.handle(request);
    assertEquals(0, patronGateway.countActive());
    assertTrue(result.getInfoMessage().length() > 0);
    assertEquals("", result.getWarningMessage());
    assertEquals("", result.getErrorMessage());
	}

	public void testDeletePatronDoesNothingWithInvalidId() throws ServletException {
		patronGateway.add(patron);
		Patron patron2 = (Patron) patronGateway.findAllPatrons().iterator().next();
//		 Need to add fields for the existing patron, even though we no longer care...
    request.setParameter("firstName_"+patron2.getId(), "Bob");
    request.setParameter("middleInitial_"+patron2.getId(), "C");
    request.setParameter("lastName_"+patron2.getId(), "Martin");
    request.setParameter("delete_0", "on");
    ActionResult result = patronsController.handle(request);
    assertEquals(1, patronGateway.countActive());
    assertEquals("", result.getInfoMessage());
    assertEquals("", result.getWarningMessage());
    assertEquals("", result.getErrorMessage());
	}
}
