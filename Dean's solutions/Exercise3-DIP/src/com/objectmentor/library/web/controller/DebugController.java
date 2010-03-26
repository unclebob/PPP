package com.objectmentor.library.web.controller;

import com.objectmentor.library.libraryRules.PatronRegistrar;
import com.objectmentor.library.libraryRules.TimeSource;
import com.objectmentor.library.mocks.MockTimeSource;
import com.objectmentor.library.models.Patron;

public class DebugController extends Controller {
	public ActionResult setDate() throws Exception {
		ActionResult result = new ActionResult();
		if (isGet())
			render("debug/setDate.jsp");
		else if (isPost()) {
			render("welcome.jsp");
			String dateString = getParameter("date");
			TimeSource.timeSource = new MockTimeSource(dateString);
		}
		return result;
	}

	public ActionResult loadTestData() {
		ActionResult result = new ActionResult();

		Application app = new Application();
		getSession().setAttribute("Application", app);

		PatronRegistrar reg = app.getPatronRegistrar();
		registerPatron(reg, new Patron("Robert", "C", "Martin", null, null));
		registerPatron(reg, new Patron("James", "W", "Grenning", null, null));
		registerPatron(reg, new Patron("David", "A", "Chelimsky", null, null));
		registerPatron(reg, new Patron("Dean", "X", "Wamper", null, null));
		registerPatron(reg, new Patron("Tim", "O", "Ottinger", null, null));
		registerPatron(reg, new Patron("Eric", "W", "Meyer", null, null));

		result.appendToInfoMessage("Test data added.");

		render("welcome.jsp");
		return result;
	}

	private void registerPatron(PatronRegistrar reg, Patron patron) {
		patron.setId("" + reg.getPatronGateway().getNextId());
		reg.getPatronGateway().getPatronMap().put(patron.getId(), patron);
	}

	public ActionResult clearData() {
		ActionResult result = new ActionResult();

		Application app = new Application();
		getSession().setAttribute("Application", app);

		result.appendToInfoMessage("All data erased.");
		render("welcome.jsp");
		return result;
	}
}
