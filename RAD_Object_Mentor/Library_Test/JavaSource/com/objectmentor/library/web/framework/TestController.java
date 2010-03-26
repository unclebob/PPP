package com.objectmentor.library.web.framework;


import javax.servlet.http.HttpServletRequest;

public class TestController extends Controller {
  public static boolean getWasCalled = false;
  public static boolean postWasCalled = false;
  public static HttpServletRequest theRequest = null;

  public TestController() {
    getWasCalled = false;
    postWasCalled = false;
    theRequest = null;
  }

  protected ActionResult handleGet() {
    getWasCalled = true;
    theRequest = getRequest();
    return new ActionResult("handleGet() called");
  }

  public ActionResult handlePost() {
    postWasCalled = true;
    theRequest = getRequest();
    return new ActionResult("handlePost() called");
  }

  public ActionResult action() {
    if (isGet()) return handleGet();
    if (isPost()) return handlePost();
    return handleUnsupportedHttpMethod();
  }

  public ActionResult bar() {
    if (isGet()) return handleGet();
    if (isPost()) return handlePost();
    return handleUnsupportedHttpMethod();
  }

  protected void onHandle() {
  }
}
