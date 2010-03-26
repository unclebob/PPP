package com.objectmentor.library.web.controller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.objectmentor.library.libraryRules.Library;
import com.objectmentor.library.libraryRules.PatronRegistrar;

public abstract class Controller {

  private HttpServletRequest request;
  private Application application;

  public ActionResult handle(HttpServletRequest request)
      throws ServletException {
    setRequest(request);
    application = getApplication();
    setDefaultResourcePath();
    if (!(isSupportedHttpMethod()))
      return handleUnsupportedHttpMethod();
    return invokeAction();
  }

  private void setDefaultResourcePath() {
    this.request.setAttribute("resource_path", "/WEB-INF/pages/template.jsp");
  }

  public Application getApplication() {
    return (Application) this.request.getSession().getAttribute("Application");
  }

  private boolean isSupportedHttpMethod() {
    return isPost() || isGet();
  }

  private ActionResult invokeAction() throws ServletException {
    try {
      Method method = getClass().getMethod(getActionName(request),
          new Class[] {});
      return (ActionResult) method.invoke(this, new Object[] {});
    } catch (NoSuchMethodException e) {
      return handleUnsupportedAction(getActionName(request));
    } catch (Exception e) {
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

  protected Library getLibrary() {
    return application.getLibrary();
  }

  protected PatronRegistrar getPatronRegistrar() {
    return application.getPatronRegistrar();
  }

  protected String getParameter(String key) {
    return request.getParameter(key);
  }

  protected String[] getParameterValues(String key) {
    return request.getParameterValues(key);
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

  protected String makeInvalidIdString(String idString) {
    return "You must specify a valid " + idString;
  }

  protected boolean isValidFormFieldEntry(String field) {
    return !(isNullOrEmpty(field) || isFormDefaultHelpString(field));
  }

  private boolean isFormDefaultHelpString(String field) {
    return field.trim().startsWith("(");
  }

  protected void handleUnknownId(ActionResult result, String idName, String id) {
    result.appendToErrorMessage("The " + idName + " \"" + id
        + "\" does not exist!");
  }

  protected List getIdsFromMatchingParameters(String prefix) {
    Pattern gatherPattern = Pattern.compile("\\A" + prefix + "_(\\w+)\\s*\\Z");
    Set names = getRequest().getParameterMap().keySet();
    List matches = new ArrayList();
    for (Iterator i = names.iterator(); i.hasNext();) {
      String name = (String) i.next();
      Matcher matcher = gatherPattern.matcher(name);
      if (matcher.matches()) {
        matches.add(matcher.group(1));
      }
    }
    return matches;
  }

  protected int getIntegerParameter(String key, int defaultValue) {
    String value = getParameter(key);
    if (isValidFormFieldEntry(value)) {
      try {
        return Integer.parseInt(value);
      } catch (NumberFormatException e) {
        return 0;
      }
    }
    return defaultValue;
  }

}
