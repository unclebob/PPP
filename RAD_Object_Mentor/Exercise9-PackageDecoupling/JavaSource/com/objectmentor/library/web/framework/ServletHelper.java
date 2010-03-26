package com.objectmentor.library.web.framework;

import com.objectmentor.library.utils.StringUtil;
import com.objectmentor.library.web.controller.*;

import javax.servlet.http.HttpServletRequest;

public class ServletHelper {
  private ServletHelper() {}

  public static String requestPath(HttpServletRequest request) {
    String uri = request.getRequestURI();
    uri = StringUtil.chopAtDelimiter(uri, "?");
    uri = StringUtil.chopAtDelimiter(uri, ".");
    uri = uri.replaceAll("/WEB-INF/pages", "");
    uri = uri.substring(request.getContextPath().length());
    return uri.length() == 0 ? "/" : uri;
  }

  public static ActionResult loadAndCall(String qualifiedClass, HttpServletRequest request) throws Exception {
    request.setAttribute("action_name", getActionName(requestPath(request)));
    Class controllerClass = Class.forName(qualifiedClass);
    LibraryController controller = (LibraryController) controllerClass.newInstance();
    return controller.handle(request);
  }

  private static String getActionName(String path) {
    int end = path.length();
    int slash = path.lastIndexOf("/");
    int start = slash >= 0 ? slash : 0;
    return path.substring(start + 1, end);
  }

  //NOTE - this is only used by JSPs, so it won't show up if you search for references
  public static String arrayToString(Object[] array) {
    if (array == null)
      return "<null>";
    StringBuffer buff = new StringBuffer();
    for (int i = 0; i < array.length; i++) {
      buff.append(array[i] != null ? array[i].toString() : "<null>");
      if (i < array.length - 1)
        buff.append(", ");
    }
    return buff.toString();
  }
}
