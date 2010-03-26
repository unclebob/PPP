package com.objectmentor.library.web.controller;

public class WelcomeController extends Controller {

  public ActionResult index() {
    setIncludePath("welcome.jsp");
    return new ActionResult();
  }
}
