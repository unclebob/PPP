package com.objectmentor.library.web.framework;

import com.objectmentor.library.utils.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class ControllerFinder {

  private final String controllerPackage;

  public ControllerFinder(String controllerPackage) {
    this.controllerPackage = controllerPackage;
  }

  public String controllerClassName(HttpServletRequest request) {
    String path = ServletHelper.requestPath(request);
    path = removeActionName(path);
    List pathParts = split(path);
    String qualifiedName = controllerPackage + "."
      + getControllerSubPackage(pathParts) + "."
      + getControllerName(pathParts) + "Controller";
    return qualifiedName.replaceAll("\\.+", ".");
  }

  private String getControllerName(List pathParts) {
    String controllerName = (String) pathParts.get(pathParts.size() - 1);
    return StringUtil.capitalizeWord(controllerName);
  }

  private String getControllerSubPackage(List pathParts) {
    String controllerSubPackage = StringUtil.join(pathParts, ".");
    int dot = controllerSubPackage.lastIndexOf(".");
    if (dot > 0)
      return controllerSubPackage.substring(0, dot);
    else
      return "";
  }

  private List split(String path) {
    String elements[] = path.split("/");
    return Arrays.asList(elements);
  }

  private String removeActionName(String path) {
    int slash = path.lastIndexOf("/");
    return path.substring(0, slash);
  }

}
