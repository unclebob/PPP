package com.objectmentor.library.web.controller;

import com.objectmentor.library.application.*;
import com.objectmentor.library.libraryRules.Library;
import com.objectmentor.library.models.*;
import com.objectmentor.library.offline.*;
import com.objectmentor.library.utils.*;

public class DebugController extends LibraryController {
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

    Application app = new Application(new OffLineServiceProvider());
    getSession().setAttribute("Application", app);
    InMemoryIsbnService isbnService = (InMemoryIsbnService) app.getIsbnService();
    isbnService.addBook(new Book("1", "Tomorrowland", "J. Reader. Poobah"));
    isbnService.addBook(new Book("2", "Beast of Burden", "Mick Jagger"));
    isbnService.addBook(new Book("3", "Freebus and Hackoff", "James T. Cork"));
    isbnService.addBook(new Book("4", "My Time in Sphereland", "A. Square"));
    isbnService.addBook(new Book("5", "The world before.", "Big Bang"));
    isbnService.addBook(new Book("6", "An inconvenient lie", "L. B. Ral"));

    Library library = app.getLibrary();
    library.acceptBook("1");
    library.acceptBook("2");
    library.acceptBook("3");
    library.acceptBook("4");
    library.acceptBook("5");
    library.acceptBook("6");

    InMemoryCompactDiscService cdService = (InMemoryCompactDiscService) app.getCompactDiscService();
    cdService.addCd(new CompactDisc("7", "In Through the Out Door", "Led Zeppelin"));
    cdService.addCd(new CompactDisc("8", "Are You Experienced", "Jimmy Hendrix"));
    cdService.addCd(new CompactDisc("9", "Dr. Hee", "Tribal Tech"));
    cdService.addCd(new CompactDisc("10", "A Garota de Ipanema", "Jo‹o Gilberto"));

    library.acceptCD("7");
    library.acceptCD("8");
    library.acceptCD("9");
    library.acceptCD("10");

    PatronRegistrar reg = app.getPatronRegistrar();
    reg.registerPatron(new Patron("Robert", "C", "Martin", null, null));
    reg.registerPatron(new Patron("James", "W", "Grenning", null, null));
    reg.registerPatron(new Patron("David", "A", "Chelimsky", null, null));
    reg.registerPatron(new Patron("Dean", "X", "Wamper", null, null));
    reg.registerPatron(new Patron("Tim", "O", "Ottinger", null, null));
    reg.registerPatron(new Patron("Eric", "W", "Meyer", null, null));

    result.appendToInfoMessage("Test data added.");

    render("welcome.jsp");
    return result;
  }

  public ActionResult clearData() {
    ActionResult result = new ActionResult();

    Application app = new Application(new OnLineServiceProvider());
    getSession().setAttribute("Application", app);

    result.appendToInfoMessage("All data erased.");
    render("welcome.jsp");
    return result;
  }
}
