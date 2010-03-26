package com.objectmentor.library.web.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.objectmentor.library.utils.StringUtil;
import com.objectmentor.library.web.servlet.ServletHelper;

public class ControllerFinder {

  private final String controllerPackage;

  public ControllerFinder(String controllerPackage) {
    this.controllerPackage = controllerPackage;
  }

  public String controllerClassName(HttpServletRequest request) {
    String path = ServletHelper.requestPath(request);
    path = removeActionName(path);
    List<String> pathParts = split(path);
    String qualifiedName = controllerPackage + "."
        + getControllerSubPackage(pathParts) + "."
        + getControllerName(pathParts) + "Controller";
    return qualifiedName.replaceAll("\\.+", ".");
  }

  private String getControllerName(List<String> pathParts) {
    String controllerName = pathParts.get(pathParts.size() - 1);
    return StringUtil.capitalizeWord(controllerName);
  }

  private String getControllerSubPackage(List<String> pathParts) {
    String controllerSubPackage = StringUtil.join(pathParts, ".");
    int dot = controllerSubPackage.lastIndexOf(".");
    if (dot > 0)
      return controllerSubPackage.substring(0, dot);
    else
      return "";
  }

  private List<String> split(String path) {
    String elements[] = path.split("/");
    return Arrays.asList(elements);
  }

  private String removeActionName(String path) {
    int slash = path.lastIndexOf("/");
    return path.substring(0, slash);
  }

}
