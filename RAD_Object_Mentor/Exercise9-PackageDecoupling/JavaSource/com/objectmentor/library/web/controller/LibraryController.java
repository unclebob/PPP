package com.objectmentor.library.web.controller;

import com.objectmentor.library.application.*;
import com.objectmentor.library.libraryRules.Library;
import com.objectmentor.library.web.framework.Controller;

import java.util.*;
import java.util.regex.*;

public abstract class LibraryController extends Controller {

  private Application application;

  protected void onHandle() {application = getApplication();}

  public Application getApplication() {
    return (Application) this.request.getSession().getAttribute("Application");
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
      }
      catch (NumberFormatException e) {
        return 0;
      }
    }
    return defaultValue;
  }

}
