package com.objectmentor.library.web.framework;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public abstract class ControllerServlet extends HttpServlet {
  private static final long serialVersionUID = -1028466373959386663L;
  private ControllerFinder controllerFinder = null;

  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
    handle(request, response);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
    handle(request, response);
  }

  private void handle(HttpServletRequest request, HttpServletResponse response)
    throws ServletException {
    if (controllerFinder == null)
      controllerFinder = getControllerFinder();
    try {
      initializeServlet(request);
      handle(request);
      forward(request, response);
    }
    catch (Exception e) {
      // TODO - put up a nice exception page and make it their problem.
      throw new ServletException(e);
    }
  }

  protected abstract ControllerFinder getControllerFinder();

  protected abstract void initializeServlet(HttpServletRequest request);

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

  protected boolean parseBooleanString(String value) {
    if (value.length() == 0)
      return true;    // defaults to true
    return Boolean.valueOf(value).booleanValue();
  }
}
