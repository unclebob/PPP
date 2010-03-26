package com.objectmentor.library.web.controller;

import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.objectmentor.library.libraryRules.Library;

public abstract class Controller {
  
  private HttpServletRequest request;
  private Application application;

  public ActionResult handle(HttpServletRequest request) throws ServletException {
    setRequest(request);
    getApplicationFrom(request);
    addResourcePathTo(request);
    if (!(isSupportedHttpMethod()))
      return handleUnsupportedHttpMethod();
    return invokeAction(request);
  }

  private boolean isSupportedHttpMethod() {
    return isPost() || isGet();
  }

  private ActionResult invokeAction(HttpServletRequest request) throws ServletException {
    try {
      Method method = getClass().getMethod(getActionName(request), new Class[]{});
      return (ActionResult) method.invoke(this, new Object[] {});
    } catch (NoSuchMethodException e) {
      return handleUnsupportedAction(getActionName(request));
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }

  private String getActionName(HttpServletRequest request) {
    return (String) request.getAttribute("action_name");
  }

  private void getApplicationFrom(HttpServletRequest request) {
    this.application = (Application) request.getSession().getAttribute("Application");
  }
  
  private void setRequest(HttpServletRequest request) {
    this.request = request;
  }

  private ActionResult handleUnsupportedAction(String actionName) {
    return ActionResult.makeErrorHandlerResult(getClass().getName() + " does not have an action named " + actionName);
  }

  protected ActionResult handleUnsupportedHttpMethod() {
    return ActionResult.makeErrorHandlerResult("Unsupported HTTP method: \""+getMethod()+"\".");
  }

  private String getMethod() {
    return request.getMethod();
  }

  protected boolean isPost() {
    return request.getMethod().equalsIgnoreCase("POST");
  }

  protected boolean isGet() {
    return request.getMethod().equalsIgnoreCase("GET");
  }
  
  protected HttpServletRequest getRequest() {
    return request;
  }

  protected Library getLibrary() {
    return application.getLibrary();
  }
  
  protected String getParameter(String key) {
    return request.getParameter(key);
  }

  protected String[] getParameterValues(String key) {
    return request.getParameterValues(key);
  }

  protected boolean isNullOrEmpty(String string) {
    return string == null || string.length() == 0;
  }

  private void addResourcePathTo(HttpServletRequest request) {
    request.setAttribute("resource_path", "/WEB-INF/pages/template.jsp");
  }
  
  protected void setIncludePath(String path) {
    getRequest().setAttribute("include_path", "/WEB-INF/pages/" + path);
  }
}
