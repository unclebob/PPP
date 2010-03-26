package com.objectmentor.library.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.objectmentor.library.web.controller.ActionResult;
import com.objectmentor.library.web.controller.Application;
import com.objectmentor.library.web.controller.ControllerFinder;

public class ControllerServlet extends HttpServlet {
  private static final long serialVersionUID = -1028466373959386663L;
  private static final String controllerPackage = "com.objectmentor.library.web.controller";

  private ControllerFinder controllerFinder;

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
    } catch (Exception e) {
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
    if (session.getAttribute("Application") == null)
      session.setAttribute("Application", new Application(true));
  }

}