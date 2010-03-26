package com.objectmentor.library.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.objectmentor.library.gateways.PatronGateway;
import com.objectmentor.library.models.Patron;

public class PatronsController extends Controller {
  private static final String INVALID_NAME_ERROR_STRING = "you must supply a valid first and last name";
	private static final String MANAGEMENT_RESOURCE_PATH = "patrons/manage.jsp";
	private static final String HISTORY_RESOURCE_PATH    = "patrons/history.jsp";

  public ActionResult manage() {
  	ActionResult result = new ActionResult();
  	if (isPost()) {
	  	handleDeletedPatrons(result);
	  	handleChangedPatrons(result);
	  	handleNewPatron(result);
  	}
  	return finishPatronsRequest(result);
  }

	private ActionResult finishPatronsRequest(ActionResult result) {
    setIncludePath(MANAGEMENT_RESOURCE_PATH);
    PatronGateway patronGateway = getLibrary().getPatronGateway();
    Collection patrons = patronGateway.findAllPatrons();
    getRequest().setAttribute("patrons", patrons);
    return result;
	}

  public ActionResult history() {
    setIncludePath(HISTORY_RESOURCE_PATH);
    return new ActionResult();
  }
  
	private void handleNewPatron(ActionResult result) {
    String firstName     = getParameter("newFirstName");
    String middleInitial = getParameter("newMiddleInitial");
    String lastName      = getParameter("newLastName");
    if (firstName != null && firstName.trim().startsWith("("))  // value was original "help" message to user
    	firstName = "";
    int valid = validatePatronData(true, firstName, middleInitial, lastName);
		if (valid <= 0) {
			if (valid < 0) 
				result.appendToErrorMessage(makeInvalidNameErrorMessage("For the new Patron, ", firstName, middleInitial, lastName));
      return;
    }
    Patron patron = new Patron(firstName, middleInitial, lastName, null, null);
    getLibrary().registerPatron(patron);
    result.appendToInfoMessage("Patron "+patron.getFullName()+" registered.");
	}

	private int validatePatronData(boolean isNewPatron, String firstName, String middleInitial, String lastName) {
		if (isNewPatron && isNullOrEmpty(firstName) && isNullOrEmpty(lastName))
			return 0;		// okay, but ignore entry
		return isNullOrEmpty(firstName) || isNullOrEmpty(lastName) ? -1 : 1;
	}

	private void handleDeletedPatrons(ActionResult result) {
    Collection patrons = getPatrons();
    Iterator iter = patrons.iterator();
    // Save the list of patrons to delete; we can't delete while iterating!
    ArrayList patronsToDelete = new ArrayList();
    while (iter.hasNext()) {
    	Patron patron = (Patron) iter.next();
    	String id = patron.getId();
    	String deleteFlag = getParameter("delete_"+id);
    	if (deleteFlag != null && deleteFlag.equals("on")) {
    		patronsToDelete.add(patron);
    	}
    }
    Iterator diter = patronsToDelete.iterator();
    while (diter.hasNext()) {
    	Patron patron = (Patron) diter.next();
			getLibrary().getPatronGateway().delete(patron.getId());
			result.appendToInfoMessage("Patron "+patron.getFullName()+" ("+patron.getId()+") deleted.");
		}
	}

	private void handleChangedPatrons(ActionResult result) {
    Collection patrons = getPatrons();
    Iterator iter = patrons.iterator();
    while (iter.hasNext()) {
    	Patron patron = (Patron) iter.next();
    	String id = patron.getId();
    	String firstName     = getParameter("firstName_"+id);
    	String middleInitial = getParameter("middleInitial_"+id);
    	String lastName      = getParameter("lastName_"+id);
      int valid = validatePatronData(false, firstName, middleInitial, lastName);
  		if (valid <= 0) {
				result.appendToErrorMessage(makeInvalidNameErrorMessage("For an existing patron (id = "+id+"), ", firstName, middleInitial, lastName));
  		} else {
  			if (!firstName.equals(patron.getFirstName()) ||
  					!lastName.equals(patron.getLastName()) ||
  					!middleInitial.equals(patron.getMiddleInitial())) {
	  			// Replace existing patron. For a more heavyweight object (and one that isn't a value object...)
	  			// you would just want to update values and only when they've actually changed!
	  			PatronGateway patronGateway = getLibrary().getPatronGateway();
	  			patronGateway.delete(id);
	  			Patron patron2 = new Patron(firstName, middleInitial, lastName, patron.getAddress(), patron.getPhone());
					patronGateway.add(patron2);
	  			result.appendToInfoMessage("Patron "+patron2.getFullName()+" updated.");
  			}
  		}
    }
	}

	private String makeInvalidNameErrorMessage(
			String prefix, String firstName,
			String middleInitial, String lastName) {
		StringBuffer buff = new StringBuffer();
		buff.append(prefix).append(INVALID_NAME_ERROR_STRING).append(" (first name: \"").append(firstName);
		buff.append("\", middle initial: \"").append(middleInitial);
		buff.append("\", last name: \"").append(lastName).append("\")");
		return buff.toString();
	}

	private Collection getPatrons() {
		PatronGateway pg = getLibrary().getPatronGateway();
    Collection patrons = pg.findAllPatrons();
		return patrons;
	}

}
