package com.objectmentor.library.web.framework;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.objectmentor.library.application.Application;
import com.objectmentor.library.web.controller.ActionResult;
import com.objectmentor.library.web.controller.OffLineServiceProvider;
import com.objectmentor.library.web.controller.OnLineServiceProvider;
import com.objectmentor.library.web.controller.ServiceProvider;

public class ControllerServlet extends HttpServlet {
  private static final long serialVersionUID = -1028466373959386663L;
  private static final String controllerPackage = "com.objectmentor.library.web.controller";

  private ControllerFinder controllerFinder;
  private boolean runningOnline = true;

  public ControllerServlet() {
    controllerFinder = new ControllerFinder(controllerPackage);
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
    handle(request, response);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
    handle(request, response);
  }

  private void handle(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
    try {
      ensureApplicationIsInSession(request);
      handle(request);
      forward(request, response);
    }
    catch (Exception e) {
      // TODO - put up a nice exception page and make it their problem.
      throw new ServletException(e);
    }
  }

  private void handle(HttpServletRequest request) throws Exception {
    String controllerClassName = controllerFinder.controllerClassName(request);
    ActionResult result = ServletHelper.loadAndCall(controllerClassName, request);
    request.setAttribute("result", result);
  }

  private void forward(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    getServletConfig()
      .getServletContext()
      .getRequestDispatcher((String) request.getAttribute("resource_path"))
      .forward(request, response);
  }

  private void ensureApplicationIsInSession(HttpServletRequest request) {
    HttpSession session = request.getSession(true);
    Application application = (Application) session.getAttribute("Application");
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

  private boolean parseBooleanString(String value) {
    if (value.length() == 0)
      return true;    // defaults to true
    return Boolean.valueOf(value).booleanValue();
  }

  public boolean getRunningOnline() {
    return runningOnline;
  }
}