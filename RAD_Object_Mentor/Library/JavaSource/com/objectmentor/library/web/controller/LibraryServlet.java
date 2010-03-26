package com.objectmentor.library.web.controller;

import com.objectmentor.library.application.*;
import com.objectmentor.library.offline.OffLineServiceProvider;
import com.objectmentor.library.online.OnLineServiceProvider;
import com.objectmentor.library.web.framework.*;

import javax.servlet.http.*;

public class LibraryServlet extends ControllerServlet {

  private static final long serialVersionUID = -8500128168853426611L;

  private boolean runningOnline = true;

  private static final String controllerPackage = "com.objectmentor.library.web.controller";

  protected ControllerFinder getControllerFinder() {
    return new ControllerFinder(controllerPackage);
  }

  protected void initializeServlet(HttpServletRequest request) {
    ensureApplicationIsInSession(request);
  }

  private void ensureApplicationIsInSession(HttpServletRequest request) {
    HttpSession session = request.getSession(true);
    Application application = (Application) session
      .getAttribute("Application");
    if (application == null) {
      application = new Application(getServiceProvider(request));
      session.setAttribute("Application", application);
    }
  }

  private ServiceProvider getServiceProvider(HttpServletRequest request) {
    String runningOnlineString = request.getParameter("online");
    if (runningOnlineString != null) {
      runningOnline = parseBooleanString(runningOnlineString);
    }
    String runningOfflineString = request.getParameter("offline");
    if (runningOfflineString != null) {
      runningOnline = !(parseBooleanString(runningOfflineString));
    }
    if (runningOnline)
      return new OnLineServiceProvider();
    else
      return new OffLineServiceProvider();
  }

  public boolean getRunningOnline() {
    return runningOnline;
  }
}