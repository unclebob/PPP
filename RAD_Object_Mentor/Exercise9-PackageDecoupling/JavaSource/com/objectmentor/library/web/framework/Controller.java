package com.objectmentor.library.web.framework;

import com.objectmentor.library.web.controller.ActionResult;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.lang.reflect.Method;

public abstract class Controller {
  protected HttpServletRequest request;

  public ActionResult handle(HttpServletRequest request)
    throws ServletException {
    setRequest(request);
    onHandle();
    setDefaultResourcePath();
    if (!(isSupportedHttpMethod()))
      return handleUnsupportedHttpMethod();
    return invokeAction();
  }

  protected abstract void onHandle();

  private void setDefaultResourcePath() {
    this.request.setAttribute("resource_path", "/WEB-INF/pages/template.jsp");
  }

  private boolean isSupportedHttpMethod() {
    return isPost() || isGet();
  }

  private ActionResult invokeAction() throws ServletException {
    try {
      Method method = getClass().getMethod(getActionName(request),
                                           new Class[]{});
      return (ActionResult) method.invoke(this, new Object[]{});
    }
    catch (NoSuchMethodException e) {
      return handleUnsupportedAction(getActionName(request));
    }
    catch (Exception e) {
      e.printStackTrace(System.err);
      throw new ServletException(e);
    }
  }

  private String getActionName(HttpServletRequest request) {
    return (String) request.getAttribute("action_name");
  }

  private void setRequest(HttpServletRequest request) {
    this.request = request;
  }

  private ActionResult handleUnsupportedAction(String actionName) {
    return ActionResult.makeErrorHandlerResult(getClass().getName()
      + " does not have an action named " + actionName);
  }

  protected ActionResult handleUnsupportedHttpMethod() {
    return ActionResult.makeErrorHandlerResult("Unsupported HTTP method: \""
      + getMethod() + "\".");
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

  protected boolean isNullOrEmpty(String string) {
    return string == null || string.trim().length() == 0;
  }

  protected void render(String path) {
    request.setAttribute("include_path", "/WEB-INF/pages/" + path);
  }

  protected void setAttribute(String attribute, Object object) {
    request.setAttribute(attribute, object);
  }

  protected HttpSession getSession() {
    return request.getSession();
  }
}
